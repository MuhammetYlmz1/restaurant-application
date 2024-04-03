package com.muhammet.restaurantapplication.comp.config;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Configuration
public class MessageConfig extends AcceptHeaderLocaleResolver {

    private final List<Locale> locales = Arrays.asList(new Locale("en"), new Locale("tr"));

    @NotNull
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String language = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
        return getLocale(language);
    }
    private Locale getLocale(String language) {
        return  StringUtils.isEmpty(language)
                ? new Locale("tr", "TR")
                : Locale.lookup(Locale.LanguageRange.parse(language), locales);
    }
}
