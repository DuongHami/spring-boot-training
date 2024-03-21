package com.example.springboottraining.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class LocaleResolverConfig {

    @Bean
    public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("i18n/translation");
        source.setDefaultEncoding("UTF-8");
        return source;
    }

    @Bean
    public LocaleResolver localeResolver(){
        AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        resolver.setDefaultLocale(Locale.GERMAN);
        return resolver;
    }


//    @Bean
//    public SessionLocaleResolver localeResolver(){
//        SessionLocaleResolver slr = new SessionLocaleResolver();
//        slr.setDefaultLocale(Locale.US);
//        return slr;
//    }
//
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor(){
//        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
//        lci.setParamName("name");
//        return lci;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor((localeChangeInterceptor()));
//    }
}
