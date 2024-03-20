package com.vahner.airticketsapp;

import com.vahner.airticketsapp.generator.JwtProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackageClasses = JwtProvider.class)
public class AirTicketsAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirTicketsAppApplication.class, args);
    }
}