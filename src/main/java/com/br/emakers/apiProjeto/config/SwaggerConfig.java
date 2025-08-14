package com.br.emakers.apiProjeto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@OpenAPIDefinition
@Configuration

public class SwaggerConfig {

    @Bean
    public OpenAPI springOpenApi() {
          final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                    .info(new Info().title("Api Projeto Trainee")
                    .summary("Api Emakers")
                    .description("ApiRest de uma Biblioteca, que cadastra pessoas e livros e é capaz de fazer emprestimos de livros")
                    .version("1.0"))
                    .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                    .components(new Components().addSecuritySchemes(securitySchemeName,
                        new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }               


}
