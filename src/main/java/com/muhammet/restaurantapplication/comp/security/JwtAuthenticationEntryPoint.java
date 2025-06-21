package com.muhammet.restaurantapplication.comp.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muhammet.restaurantapplication.comp.config.MessageConfig;
import com.muhammet.restaurantapplication.exception.BusinessException.Ex;
import com.muhammet.restaurantapplication.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;
    private final MessageSource messageSource;
    private final MessageConfig messageConfig;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getOutputStream().println(
                objectMapper.writeValueAsString(
                        ApiResponse.builder()
                                .error(messageSource.getMessage(Ex.ACCESS_DENIED_EXCEPTION.getMessage(),
                                                new String[]{
                                                        messageConfig.resolveLocale(request).getLanguage()
                                                },
                                                messageConfig.resolveLocale(request)),
                                        HttpStatus.UNAUTHORIZED
                                ).build().getBody()));
    }
}
