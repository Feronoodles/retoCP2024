package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@OpenAPIDefinition(servers = {@io.swagger.v3.oas.annotations.servers.Server(url = "/", description = "any description of Server URL"), @io.swagger.v3.oas.annotations.servers.Server(url = "/myapp", description = "any description of your app")})
public class RetoCineplanetApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetoCineplanetApplication.class, args);
	}


	@Bean
	public OpenAPI myOpenAPI() {
		Contact contact = new Contact();
		contact.setEmail("ferourlich@gmail.com");
		contact.setName("Fernando");
		contact.setUrl("https://my-awesome-api.com");

		Server localServer = new Server();
		localServer.setUrl("http://localhost:8000");
		localServer.setDescription("Server URL in Local environment");

		Server productionServer = new Server();
		productionServer.setUrl("https://my-awesome-api.com");
		productionServer.setDescription("Server URL in Production environment");

		License mitLicense = new License()
				.name("MIT License")
				.url("https://choosealicense.com/licenses/mit/");

		Info info = new Info()
				.title("TASK MANAGER API")
				.contact(contact)
				.version("1.0")
				.description("This API exposes endpoints for users to manage their tasks.")
				.termsOfService("https://my-awesome-api.com/terms")
				.license(mitLicense);

		return new OpenAPI()
				.info(info)
				.servers(List.of(localServer, productionServer))
				.components(new Components()
						.addSecuritySchemes("bearer-key",
								new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
	}
}
