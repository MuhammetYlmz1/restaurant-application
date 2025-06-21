package com.muhammet.restaurantapplication.comp.config.kafka.producer.impl;

import com.muhammet.restaurantapplication.comp.config.kafka.producer.OrderProducer;
import com.muhammet.restaurantapplication.model.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducerImpl implements OrderProducer {
    private final KafkaTemplate<String, OrderDto> kafkaTemplate;

    private static final String topicNameCreated = "order.food.created";

    @Override
    public void sendOrderCreatedMail(OrderDto orderDto) {
        kafkaTemplate.send(topicNameCreated, orderDto);
    }
}
