package com.catalisa.apiDoacaoAlimento;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Doação de Alimentos ", version = "1", description = "API De Doação de Alimentos"))
public class ApiDoacaoAlimentoApplication{

	public static void main(String[] args) {
		SpringApplication.run(ApiDoacaoAlimentoApplication.class, args);
	}

}

