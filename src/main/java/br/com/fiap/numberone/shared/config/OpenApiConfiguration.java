package br.com.fiap.numberone.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfiguration {

	@Bean
	OpenAPI openAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("PosTech15SOAT API")
				.version("v1")
				.description("Base tecnica inicial do sistema da oficina com JWT, Flyway e padrao de erros."))
			.components(new Components()
				.addSecuritySchemes("bearerAuth", new SecurityScheme()
					.type(SecurityScheme.Type.HTTP)
					.scheme("bearer")
					.bearerFormat("JWT")
					.description("JWT emitido pelo endpoint /api/public/auth/login")))
			.addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
	}
}
