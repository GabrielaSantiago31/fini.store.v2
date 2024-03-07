package io.github.GabrielaSantiago31.fini.store.v2.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(
				contact = @Contact(
						name = "Gabriela de S. Santiago",
						email = "gabrielas.santiago@hotmail.com",
						url = "https://github.com/GabrielaSantiago31"
				),
				description = "OpenApi documentation for Fini Store API",
				title = "OpenApi specification - Fini Store",
				version = "2.0",
				license = @License(
						name = "MIT License",
						url = "http://chooseal"
				),
				termsOfService = "Terms of service"
		),
		servers = {
				@Server(
						description = "Local ENV",
						url = "http://localhost:8080"
				),
				@Server(
						description = "Prod ENV",
						url = "https://github.com/GabrielaSantiago31"
				)
		},
		security = {
				@SecurityRequirement(
						name = "bearerAuth"
				)
		}
)

@SecurityScheme(
		name = "bearerAuth",
		description = "JWT auth description",
		scheme = "bearer",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}
