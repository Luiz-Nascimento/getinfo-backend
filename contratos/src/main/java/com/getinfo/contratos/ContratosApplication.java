package com.getinfo.contratos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
//#Caso queira testar com banco de dados local, descomente.
//@Profile("dev")
public class ContratosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContratosApplication.class, args);
		System.out.println("\nO servidor está sendo executado em http://localhost:8080 \n");
		
	}

}
