package com.example.springboottraining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

@SpringBootApplication
public class SpringBootTrainingApplication {

    public static void main(String[] args) {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("i18n/translation");
//        messageSource.setDefaultEncoding("UTF-8");
//        System.out.println(messageSource.getMessage("error.id-not-found", null, Locale.GERMAN));
        SpringApplication.run(SpringBootTrainingApplication.class, args);
    }

}
