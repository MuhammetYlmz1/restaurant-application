package com.muhammet.restaurantapplication.service.impl;

import com.muhammet.restaurantapplication.model.dto.MailModel;
import com.muhammet.restaurantapplication.model.dto.OrderDto;
import com.muhammet.restaurantapplication.service.MailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;
    private final FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${app-context}")
    private String appContext;

    @Override
    public void sendTemplateEmail(MailModel mailModel, String templateName, Map<String, Object> model) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(
                            message,
                            MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                            StandardCharsets.UTF_8.name());

            helper.setTo(mailModel.getTo());
            helper.setSubject(mailModel.getSubject());

            // Load the email template
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            Template emailTemplate = configuration.getTemplate(templateName);

            // Generate the email content using the template and the model
            String emailContent = FreeMarkerTemplateUtils.processTemplateIntoString(emailTemplate, model);
            helper.setText(emailContent, true);

            javaMailSender.send(message);
        } catch (Exception e) {
            log.error("error: ", e);
        }
    }

    @Override
    public void sendOrderCreatedMail(MailModel mailModel, OrderDto orderDto) {
        Map<String, Object> model = fillMailModel(orderDto);
        sendTemplateEmail(mailModel, "", model);
    }

    private Map<String, Object> fillMailModel(OrderDto booking) {
        Map<String, Object> model = new HashMap<>();
        model.put("username", booking.getUserDto().getUsername());;
        model.put("restaurant", booking.getBranchDto().getRestaurantName());
        model.put("note", booking.getNote());
        model.put("phone", booking.getPhone());
        return model;
    }
}
