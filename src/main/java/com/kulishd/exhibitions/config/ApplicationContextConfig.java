package com.kulishd.exhibitions.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Locale;

@Configuration

public class ApplicationContextConfig {

    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        //viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".html");

        return viewResolver;
    }

    @Bean(name = "messageSource")
    public MessageSource getMessageResource()  {
        ReloadableResourceBundleMessageSource messageResource= new ReloadableResourceBundleMessageSource();

        // Read i18n/messages_xxx.properties file.
        // For example: i18n/messages_en.properties

        messageResource.setBasename("classpath:i18n/messages");
        messageResource.setDefaultEncoding("UTF-8");
        return messageResource;
    }

//    @Bean(name = "localeResolver")
//    public LocaleResolver getLocaleResolver()  {
//        CookieLocaleResolver resolver= new CookieLocaleResolver();
//        resolver.setCookieDomain("myAppLocaleCookie");
//        // 60 minutes
//        resolver.setDefaultLocale(Locale.ENGLISH);
//        resolver.setCookieMaxAge(60*60);
//        return resolver;
//    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }


}
