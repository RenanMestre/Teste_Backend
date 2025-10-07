package com.api.Teste;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        
        // Permitir o domínio do seu frontend (substitua pela sua URL do Netlify)
        config.setAllowedOrigins(Arrays.asList("https://ornate-paprenjak-59e320.netlify.app"));
        
        // Permitir os métodos que seu front usa
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // Permitir todos os cabeçalhos que podem ser enviados pelo frontend
        config.setAllowedHeaders(Arrays.asList("*"));
        
        // Permite que o navegador envie cookies e headers de autenticação (se necessário)
        config.setAllowCredentials(true);

        // Configura a URL que terá essa política de CORS
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // para todas as rotas

        return new CorsFilter(source);
    }
}

