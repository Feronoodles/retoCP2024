package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.List;

@SpringBootApplication
@OpenAPIDefinition(servers = {@io.swagger.v3.oas.annotations.servers.Server(url = "/", description = "any description of Server URL"), @io.swagger.v3.oas.annotations.servers.Server(url = "/myapp", description = "any description of your app")})
public class RetoCineplanetApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetoCineplanetApplication.class, args);
	}



}
