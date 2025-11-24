package com.innosistemas.InnoSistemas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

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

        Server server = getCodespaceServer()
                .orElseGet(this::getLocalServer);
        
        openAPI.servers(List.of(server));
        return openAPI;
    }

    private Optional<Server> getCodespaceServer() {
        String codespaceName = System.getenv("CODESPACE_NAME");
        String portForwardingDomain = System.getenv("GITHUB_CODESPACES_PORT_FORWARDING_DOMAIN");
        
        if (codespaceName != null && portForwardingDomain != null) {
            String codespaceUrl = String.format("https://%s-%d.%s", 
                codespaceName, serverPort, portForwardingDomain);
            
            log.info("✅ Swagger configurado para Codespaces: {}", codespaceUrl);
            
            return Optional.of(new Server()
                .url(codespaceUrl)
                .description("GitHub Codespaces Server"));
        }
        return Optional.empty();
    }

    private Server getLocalServer() {
        String localUrl = "http://localhost:" + serverPort;
        log.info("✅ Swagger configurado para desarrollo local: {}", localUrl);
        
        return new Server()
            .url(localUrl)
            .description("Local Development Server");
    }
}
