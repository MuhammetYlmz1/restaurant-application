package com.muhammet.restaurantapplication.comp.config.kafka.consumer;

import com.muhammet.restaurantapplication.model.dto.OrderDto;

public interface OrderConsumer {

    void consumeOrderCreated(OrderDto orderDto);
}
