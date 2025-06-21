package com.muhammet.restaurantapplication.service.impl;

import com.muhammet.restaurantapplication.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    /*@Value("${spring.mail.username}")
    private String email;*/
    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(to);
        message.setTo(to);
        message.setText(text);
        message.setSubject(subject);

        log.info("Mail sent successfully...");
        javaMailSender.send(message);

    }
}
