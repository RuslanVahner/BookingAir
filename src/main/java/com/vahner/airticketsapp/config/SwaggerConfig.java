package com.vahner.airticketsapp.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    /**
     * Метод customOpenApi() создает объект OpenAPI для Swagger.
     * Нужно добавить схему безопасности с именем "bearer-key" для использования JWT-токенов.
     * Затем добавить элемент безопасности, указызать "bearer-key".
     *
     * @return Объект OpenAPI с настроенной схемой безопасности.
     */
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key", getSecuritySchemesItem()))
                .addSecurityItem(new SecurityRequirement().addList("bearer-key"));
    }

    /**
     * Закрытый метод getSecuritySchemesItem() создает объект SecurityScheme для схемы безопасности.
     * Нужно указать, что использую тип HTTP, схему "bearer" и формат "JWT".
     *
     * @return Объект SecurityScheme с настроенными правилами безопасности.
     */
    private SecurityScheme getSecuritySchemesItem() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");
    }
}
