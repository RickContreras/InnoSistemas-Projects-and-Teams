package com.innosistemas.InnoSistemas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OpenApiConfig {

    private static final Logger logger = LoggerFactory.getLogger(OpenApiConfig.class);

    @Value("${server.port:8080}")
    private int serverPort;

    @Bean
    public OpenAPI customOpenAPI() {
        OpenAPI openAPI = new OpenAPI()
                .info(new Info()
                        .title("InnoSistemas API")
                        .version("1.0")
                        .description("API REST para gestión de proyectos y equipos"));

        List<Server> servers = new ArrayList<>();
        
        // Detectar si estamos en Codespaces
        String codespaceName = System.getenv("CODESPACE_NAME");
        String portForwardingDomain = System.getenv("GITHUB_CODESPACES_PORT_FORWARDING_DOMAIN");
        
        if (codespaceName != null && portForwardingDomain != null) {
            // Construir URL de Codespaces
            String codespaceUrl = String.format("https://%s-%d.%s", 
                codespaceName, 
                serverPort, 
                portForwardingDomain);
            servers.add(new Server()
                .url(codespaceUrl)
                .description("GitHub Codespaces Server"));
            
            logger.info("✅ Swagger configurado para Codespaces: {}", codespaceUrl);
        } else {
            // URL local por defecto
            String localUrl = "http://localhost:" + serverPort;
            servers.add(new Server()
                .url(localUrl)
                .description("Local Development Server"));
            
            logger.info("✅ Swagger configurado para desarrollo local: {}", localUrl);
        }
        
        openAPI.servers(servers);
        return openAPI;
    }
}
