package com.getinfo.contratos.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "API de Gestão de Contratos GetInfo",
                version = "0.0.1",
                description = "API RESTful para gerenciar contratos da empresa Getinfo.",
                termsOfService = "http://example.com/terms/",
                contact = @Contact(
                        name = "SQUAD 4",
                        email = "luiz.anascimento@souunit.com.br"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Servidor de Desenvolvimento Local"),
                @Server(url = "https://getinfo-backend-api.onrender.com/", description = "Servidor de Produção")
        }
)

public class OpenAPIConfig {

}