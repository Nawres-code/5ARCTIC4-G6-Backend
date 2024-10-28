package com.Parking.GestionParking.email;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailServiceConfig {
    @Bean
    public EmailService emailService() {
        return new EmailServiceImpl();
    }
}