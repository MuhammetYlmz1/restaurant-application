package com.muhammet.restaurantapplication.comp.config.kafka.producer;

import com.muhammet.restaurantapplication.model.dto.OrderDto;

public interface OrderProducer {

    void sendOrderCreatedMail(OrderDto orderDto);
}
