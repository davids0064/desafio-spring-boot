package com.nuevospa.gestion.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "BearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "Token JWT para acceder a los endpoints protegidos."
)
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de Tareas - Nuevo SPA")
                        .version("1.0.0")
                        .description("API RESTful para la creación, consulta, actualización y eliminación de tareas.")
                        .contact(new Contact()
                                .name("David Salamanca S.")
                                .email("david.salamancasia8702@gmail.com")
                        )
                );
    }

}
