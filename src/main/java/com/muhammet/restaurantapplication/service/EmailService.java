package com.muhammet.restaurantapplication.service;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
