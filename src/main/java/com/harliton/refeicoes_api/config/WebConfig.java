package com.harliton.refeicoes_api.config;

import org.springframework.context.annotation.Configuration;
// 1. IMPORTE A ANOTAÇÃO NONNULL
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

     @Override
     // 2. ADICIONE A ANOTAÇÃO AQUI
     public void addCorsMappings(@NonNull CorsRegistry registry) {
          registry.addMapping("/api/**") // Libera apenas endpoints que começam com /api/
                    .allowedOrigins("*") // Permite acesso de QUALQUALQUER origem (app, site, etc)
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                    .allowedHeaders("*") // Permite todos os cabeçalhos
                    .allowCredentials(false); // Não permite credenciais (cookies)
     }
}