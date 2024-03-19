package com.vahner.airticketsapp.config;

import com.vahner.airticketsapp.generator.QRCodeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QRCodeConfig {
    @Bean
    public QRCodeGenerator qrCodeGenerator() {
       return new QRCodeGenerator();
    }
}
