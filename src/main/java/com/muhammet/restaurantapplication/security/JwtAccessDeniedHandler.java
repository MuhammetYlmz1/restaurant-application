package com.muhammet.restaurantapplication.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muhammet.restaurantapplication.comp.config.MessageConfig;
import com.muhammet.restaurantapplication.exception.BusinessException;
import com.muhammet.restaurantapplication.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;
    private final MessageSource messageSource;
    private final MessageConfig localeResolver;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getOutputStream().println(
                objectMapper.writeValueAsString(
                        ApiResponse.builder()
                                .error(messageSource.getMessage(
                                        BusinessException.Ex.FORBIDDEN_EXCEPTION.getMessage(),
                                        new String[] {
                                                localeResolver.resolveLocale(request).getLanguage()
                                        },
                                        localeResolver.resolveLocale(request)),
                                        HttpStatus.FORBIDDEN)
                                .build()
                                .getBody()));
    }
}
