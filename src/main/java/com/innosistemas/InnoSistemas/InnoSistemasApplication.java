package com.innosistemas.InnoSistemas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class InnoSistemasApplication {

	private static final Logger logger = LoggerFactory.getLogger(InnoSistemasApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(InnoSistemasApplication.class, args);
	}

	@Bean
	CommandLineRunner verifyDatabaseConnection(DataSource dataSource) {
		return args -> {
			try (Connection connection = dataSource.getConnection()) {
				logger.info("[DB] Conexión exitosa a la base de datos: {}", connection.getMetaData().getURL());
			} catch (Exception ex) {
				logger.error("[DB] Error conectando a la base de datos: {}", ex.getMessage(), ex);
			}
		};
	}
}
