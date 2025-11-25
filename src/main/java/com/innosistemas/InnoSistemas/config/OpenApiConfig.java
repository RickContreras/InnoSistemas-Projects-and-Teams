package com.innosistemas.InnoSistemas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Configuration
@Slf4j
public class OpenApiConfig {

    @Value("${server.port:8080}")
    private int serverPort;

    @Bean
    public OpenAPI customOpenAPI() {
        String name = System.getenv("CODESPACE_NAME");
        String domain = System.getenv("GITHUB_CODESPACES_PORT_FORWARDING_DOMAIN");
        
        String url = (name != null && domain != null)
            ? String.format("https://%s-%d.%s", name, serverPort, domain)
            : "http://localhost:" + serverPort;
        
        String description = (name != null) ? "GitHub Codespaces Server" : "Local Development Server";
        log.info("✅ Swagger configurado: {}", url);
        
        return new OpenAPI()
            .info(new Info()
                .title("InnoSistemas API")
                .version("1.0")
                .description("API REST para gestión de proyectos y equipos"))
            .servers(List.of(new Server().url(url).description(description)));
    }
}
