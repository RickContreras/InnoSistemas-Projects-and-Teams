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
        OpenAPI openAPI = new OpenAPI()
                .info(new Info()
                        .title("InnoSistemas API")
                        .version("1.0")
                        .description("API REST para gestión de proyectos y equipos"));

        String name = System.getenv("CODESPACE_NAME");
        String domain = System.getenv("GITHUB_CODESPACES_PORT_FORWARDING_DOMAIN");
        
        Server server;
        if (name != null && domain != null) {
            String url = String.format("https://%s-%d.%s", name, serverPort, domain);
            server = new Server().url(url).description("GitHub Codespaces Server");
            log.info("✅ Swagger configurado para Codespaces: {}", url);
        } else {
            String url = "http://localhost:" + serverPort;
            server = new Server().url(url).description("Local Development Server");
            log.info("✅ Swagger configurado para desarrollo local: {}", url);
        }
        
        openAPI.servers(List.of(server));
        return openAPI;
    }
}
