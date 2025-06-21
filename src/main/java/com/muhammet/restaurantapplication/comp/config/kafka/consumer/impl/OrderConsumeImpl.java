package com.muhammet.restaurantapplication.comp.config.kafka.consumer.impl;

import com.muhammet.restaurantapplication.comp.config.kafka.consumer.OrderConsumer;
import com.muhammet.restaurantapplication.model.dto.MailModel;
import com.muhammet.restaurantapplication.model.dto.OrderDto;
import com.muhammet.restaurantapplication.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderConsumeImpl implements OrderConsumer {
    private final MailService mailService;

    @KafkaListener(topics = "order.food.created", containerFactory = "orderKafkaListenerContainerFactory", groupId = "group-id")
    @Override
    public void consumeOrderCreated(OrderDto orderDto) {
        mailService.sendOrderCreatedMail(MailModel.builder().subject("Order Created").to(orderDto.getUserDto().getEmail()).build(), orderDto);
    }
}
