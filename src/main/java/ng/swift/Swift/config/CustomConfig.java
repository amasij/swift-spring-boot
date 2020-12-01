package ng.swift.Swift.config;

import ng.swift.Swift.service.PhoneNumberService;
import ng.swift.Swift.serviceImpl.PhoneNumberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class CustomConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PhoneNumberService phoneNumberService() {
        return new PhoneNumberServiceImpl();
    }
}
