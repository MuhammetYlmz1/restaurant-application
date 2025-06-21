package com.muhammet.restaurantapplication.service.impl;

import com.muhammet.restaurantapplication.comp.config.kafka.producer.OrderProducer;
import com.muhammet.restaurantapplication.exception.BusinessException.Ex;
import com.muhammet.restaurantapplication.exception.ExceptionUtil;
import com.muhammet.restaurantapplication.model.converter.GetBranchResponseConverterToBranch;
import com.muhammet.restaurantapplication.model.converter.OrderToGetOrderResponseConverter;
import com.muhammet.restaurantapplication.model.dto.*;
import com.muhammet.restaurantapplication.model.entity.*;
import com.muhammet.restaurantapplication.model.enums.OrderStatus;
import com.muhammet.restaurantapplication.model.request.CreateOrderRequest;
import com.muhammet.restaurantapplication.model.response.CreateOrderResponse;
import com.muhammet.restaurantapplication.model.response.GetBranchResponse;
import com.muhammet.restaurantapplication.model.response.GetOrderDetailResponse;
import com.muhammet.restaurantapplication.model.response.GetOrderResponse;
import com.muhammet.restaurantapplication.repository.OrderRepository;
import com.muhammet.restaurantapplication.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderFoodService orderFoodService;
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final BranchService branchService;
    private final FoodService foodService;
    private final ModelMapper modelMapper;
    private final ExceptionUtil exceptionUtil;
    private final EmailService emailService;
    private final OrderProducer orderProducer;
    private final GetBranchResponseConverterToBranch getBranchResponseConverterToBranch;
    private final OrderToGetOrderResponseConverter orderToGetOrderResponseConverter;

    @Override
    public List<GetOrderResponse> getAll() {
        List<Order> orders = orderRepository.findAll();

        List<GetOrderResponse> getOrderResponseList = orders
                .stream()
                .map(orderToGetOrderResponseConverter::map).toList();

        return getOrderResponseList;
    }

    @Override
    public GetOrderResponse getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()){
            throw exceptionUtil.buildException(Ex.ORDER_NOT_FOUND);
        }

        GetOrderResponse getOrderResponse = orderToGetOrderResponseConverter.map(order.get());

        return getOrderResponse;
    }

    @Override
    public GetOrderDetailResponse getOrderDetaild(Long id) {

        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()){
            throw exceptionUtil.buildException(Ex.ORDER_NOT_FOUND);
        }

        GetOrderResponse getOrderResponse = orderToGetOrderResponseConverter.map(order.get());
        List<OrderFoodDto> orderFoodDtos = orderFoodService.getOrderFoodByOrderId(order.get().getId());

        GetOrderDetailResponse getOrderDetailResponse = GetOrderDetailResponse.builder()
                .id(getOrderResponse.getId())
                .branchDTO(getOrderResponse.getBranchDTO())
                .orderFoodDto(orderFoodDtos)
                .orderDate(getOrderResponse.getOrderDate())
                .totalPrice(getOrderResponse.getTotalPrice())
                .note(getOrderResponse.getNote())
                .name(getOrderResponse.getName())
                .surname(getOrderResponse.getSurname())
                .totalProduct(getOrderResponse.getTotalProduct())
                .build();

        return getOrderDetailResponse;
    }

    @Override
    public Boolean cancelOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()){
            throw exceptionUtil.buildException(Ex.ORDER_NOT_FOUND);
        }
        Order existingOrder = order.get();
        existingOrder.setStatus(OrderStatus.CANCELED);
        orderRepository.save(order.get());
        return true;
    }

    @Override
    public Boolean completeOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()){
            throw exceptionUtil.buildException(Ex.ORDER_NOT_FOUND);
        }
        Order existingOrder = order.get();
        existingOrder.setStatus(OrderStatus.ACCEPTED);
        orderRepository.save(existingOrder);
        return true;
    }

    @Override
    public CreateOrderResponse create(CreateOrderRequest request) {
        UserDto userDto = userService.findByUserId(request.getUserId());

        if (Objects.isNull(userDto)){
            throw exceptionUtil.buildException(Ex.USER_NOT_FOUND_EXCEPTION);
        }

        GetBranchResponse getBranchResponse = branchService.getById(request.getBranchId());
        if (Objects.isNull(getBranchResponse)){
            throw exceptionUtil.buildException(Ex.BRANCH_NOT_FOUND_EXCEPTION);
        }

        BranchDto branchDto = modelMapper.map(getBranchResponse, BranchDto.class);
        OrderDto orderDto = requestToOrder(request,userDto,branchDto);
        Order order = getOrderWithFoods(request, orderDto);

        Order savedOrder = orderRepository.save(order);
        sendOrderCreatedEvent(orderDto);
        //emailService.sendSimpleMessage(userDto.getEmail(), "","Siparişiniz oluşturuldu");
        return convertToResponse(savedOrder);
    }

    private void sendOrderCreatedEvent(OrderDto orderDto){
        orderProducer.sendOrderCreatedMail(orderDto);
    }

    @NotNull
    private Order getOrderWithFoods(CreateOrderRequest request, OrderDto orderDto) {
        List<OrderFoodDto> orderFoods = new ArrayList<>();

        // Her bir yemek ID'sine göre fiyat bilgilerini doldurma
        request.getFoods().forEach(foodId -> fillOrderFood(orderDto, foodId, orderFoods));

        // Toplam ürün sayısı ve fiyat hesaplama
        orderDto.setTotalProduct(orderFoods.size());

        BigDecimal totalPrice = orderFoods.stream()
                .map(orderFood -> orderFood.getFood().getPrice()) // Yemeklerin fiyatlarını al
                .reduce(BigDecimal.ZERO, BigDecimal::add); // Toplam fiyatı hesapla

        // Toplam fiyatı OrderDto'ya set etme
        orderDto.setTotalPrice(totalPrice);

        // Önce Order nesnesini oluştur
        Order order = Order.builder()
                .user(modelMapper.map(orderDto.getUserDto(), User.class))  // UserDto'yu User'a dönüştür
                .branch(modelMapper.map(orderDto.getBranchDto(), Branch.class))  // BranchDto'yu Branch'a dönüştür
                .name(orderDto.getName())
                .surname(orderDto.getSurname())
                .phone(orderDto.getPhone())
                .note(orderDto.getNote())
                .totalProduct(orderDto.getTotalProduct())
                .totalPrice(orderDto.getTotalPrice())
                .status(orderDto.getStatus())
                .build();

        // Şimdi orderFood nesnelerine order referansını set et
        List<OrderFood> orderFoodEntities = orderFoods.stream()
                .map(orderFoodDto -> {
                    OrderFood orderFood = new OrderFood();
                    orderFood.setFood(modelMapper.map(orderFoodDto.getFood(), Food.class));  // FoodDTO'yu Food'a dönüştür
                    orderFood.setOrder(order);  // Bu satırda artık order nesnesi set ediliyor
                    return orderFood;
                })
                .collect(Collectors.toList());

        // Order nesnesine orderFood listesini ekle
        order.setOrderFoods(orderFoodEntities);

        return order;
    }


    private void fillOrderFood(OrderDto orderDto, Long foodId, List<OrderFoodDto> orderFoods) {
        FoodDTO food = foodService.findById(foodId);
        OrderFoodDto orderFood = new OrderFoodDto();
        orderFood.setOrderDto(orderDto);
        orderFood.setFood(food);
        orderFoods.add(orderFood);
    }

    private OrderDto requestToOrder(CreateOrderRequest createOrderRequest, UserDto userDto, BranchDto branch){
        return OrderDto.builder()
                .userDto(userDto)
                .name(createOrderRequest.getName())
                .surname(createOrderRequest.getSurname())
                .phone(createOrderRequest.getPhone())
                .branchDto(branch)
                .note(createOrderRequest.getNote())
                .status(OrderStatus.PENDING)
                .build();
    }

    /*private Order convertToOrder(OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getId())
                .user(User.builder()
                        .id(orderDto.getUserDto().getId())
                        .name(orderDto.getUserDto().getName())
                        .surname(orderDto.getUserDto().getSurname())
                        .build())
                .branch(Branch.builder()
                        .id(orderDto.getBranchDto().getId())
                        .name(orderDto.getBranchDto().getName())
                        .build())
                .note(orderDto.getNote())
                .name(orderDto.getName())
                .surname(orderDto.getSurname())
                .phone(orderDto.getPhone())
                .totalProduct(orderDto.getTotalProduct())
                .totalPrice(orderDto.getTotalPrice())
                .status(orderDto.getStatus())
                .orderFoods(orderDto.getFoodDTO().stream().map(foodDto -> OrderFood.builder()
                        .food(Food.builder()
                                .id(foodDto.getId())
                                .foodName(foodDto.getFoodName())
                                .price(foodDto.getPrice())
                                .build())
                        .build()).collect(Collectors.toList()))
                .build();
    }*/


    private CreateOrderResponse convertToResponse(Order order){
        return CreateOrderResponse.builder()
                .name(order.getName())
                .surname(order.getSurname())
                .totalPrice(order.getTotalPrice())
                .build();
    }
}
