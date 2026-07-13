package cl.duoc.ms_pacientes_db.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/v3/api-docs/**").allowedOrigins("*").allowedMethods("GET");
        registry.addMapping("/swagger-ui/**").allowedOrigins("*").allowedMethods("GET");
    }
}
