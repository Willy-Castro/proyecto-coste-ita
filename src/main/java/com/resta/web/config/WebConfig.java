package com.resta.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @SuppressWarnings("deprecation")
    @Bean
    public LocaleResolver localeResolver() {// Establece el idioma por defecto y guarda
        CookieLocaleResolver slr = new CookieLocaleResolver();
        slr.setDefaultLocale(Locale.forLanguageTag("es"));
        slr.setCookieName("lang");
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {// Detecta el idioma seleccionado por el lang
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
