package com.harliton.refeicoes_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Refeições") // O nome da sua API
                        .version("v1.0") // A versão
                        .description("API REST para gerenciamento de categorias e receitas.") // Descrição
                        .termsOfService("http://example.com/terms") // Link para termos de uso
                        .contact(new Contact()
                                .name("Harliton") // Seu nome
                                .url("http://github.com/harliton") // Seu site/github
                                .email("seu-email@exemplo.com")) // Seu email
                        .license(new License()
                                .name("Apache 2.0") // Tipo de licença
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                );
    }
}