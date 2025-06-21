package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.model.dto.MailModel;
import com.muhammet.restaurantapplication.model.dto.OrderDto;

import java.util.Map;

public interface MailService {
    void sendTemplateEmail(MailModel mailModel, String templateName, Map<String, Object> model);

    void sendOrderCreatedMail(MailModel mailModel, OrderDto orderDto);
}
