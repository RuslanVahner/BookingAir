package com.vahner.airticketsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AirTicketsAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirTicketsAppApplication.class, args);
        //  http://localhost:8080/swagger-ui-custom.html
        //  passenger123
        //  1403
    }
}