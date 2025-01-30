package com.coderhouse.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {
	
	@Bean
	OpenAPI customOpenAPI () {
		return new OpenAPI()
				.info(new Info()
						.title("API REST Full | Java | CoderHouse")
						.version("3.0.0")
						.description("La API REST proporciona endpoints para gestionar un sistema de ventas en "
								+ "una plataforma comercial. Permite realizar operaciones CRUD "
								+ "(Crear, Leer, Actualizar, Eliminar) para productos, clientes y órdenes de venta."
								+ " Los endpoints permiten listar, agregar, consultar, actualizar y eliminar registros"
								+ " relacionados con los productos disponibles, los clientes registrados y las ventas realizadas."
								+ " Además, la API está documentada utilizando Swagger, lo que facilita la comprensión"
								+ " de los endpoints y su integración por parte de los desarrolladores.")
						.contact(new Contact()
								.name("Tomas Agustin Avendaño")
								.email("tavendaño@mimail.com")
								.url("https://coderhouse.com.ar"))
						.license(new License()
								.name("Licencia")
								.url("https://github.com/Tomi28491/FacturacionSegundaEntregaAvendanioTomas.git"))
						)
						.servers(List.of(new Server()
								.url("http://localhost:8080")
								.description("Servidor Local")));
				
		
	}
}
