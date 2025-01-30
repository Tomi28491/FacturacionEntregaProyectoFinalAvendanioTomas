# commercialManager

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-brightgreen)](https://spring.io/projects/spring-boot)
[![Swagger UI](https://img.shields.io/badge/Swagger-API-blue)](http://localhost:8080/swagger-ui/index.html)
[![Java](https://img.shields.io/badge/Java-21-orange)](https://openjdk.org/)

## Descripción

**Commercial Manager** es una API REST diseñada para gestionar ventas en una plataforma de comercio. Proporciona endpoints para realizar operaciones **CRUD** (Crear, Leer, Actualizar y Eliminar) relacionadas con productos, clientes y ventas. La API está documentada utilizando **Swagger**, facilitando la comprensión y el uso de los endpoints.

### Funcionalidades principales:

- **Productos**: Gestión de productos disponibles para la venta.
- **Clientes**: Administración de información de los clientes.
- **Ventas**: Registro y control de las ventas realizadas.
- **Documentación interactiva**: Swagger UI para visualizar y probar los endpoints.

---

## Tecnologías utilizadas

- **Java 21**
- **Spring Boot 3.4.1**
- **Spring Data JPA** para la persistencia.
- **SpringDoc OpenAPI** para la documentación de la API.
- **MySQL** como base de datos relacional.
- **Maven** como herramienta de construcción.

---

## Instalación y configuración

### Prerrequisitos
1. Tener instalado:
   - Java 21
   - Maven
   - MySQL
2. Configurar la base de datos:
   - Crear una base de datos llamada `commercialManager` (puedes modificarla en `application.properties`).
   - Configurar las credenciales de la base de datos en el archivo `src/main/resources/application.properties`:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/commercialManager
     spring.datasource.username=tu_usuario
     spring.datasource.password=tu_contraseña
     ```

### Instrucciones
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/commercial-manager.git


Uso
Swagger UI
Una vez que la aplicación esté en ejecución, puedes acceder a la documentación interactiva de la API en:

http://localhost:8080/swagger-ui/index.html

Endpoints principales

Recurso	Método	Descripción	Ejemplo de URL

/productos	GET	Listar todos los productos	http://localhost:8080/productos

/productos	POST	Crear un nuevo producto	http://localhost:8080/productos

/clientes	GET	Listar todos los clientes	http://localhost:8080/clientes

/ventas	POST	Registrar una nueva venta	http://localhost:8080/ventas

Estructura del proyecto

src

├── main

│  ├── java/com/coderhouse

│   │   ├── config       # Configuración general del proyecto (Swagger.)

│   │   ├── controller   # Controladores REST

│   │   ├── dtos         # Clases para el intercambio de datos (Data Transfer Objects)

│   │   ├── model        # Entidades del modelo de datos

│   │   ├── repository   # Repositorios JPA

│   │   ├── service      # Lógica de negocio

│   │   └── CommercialManagerApplication.java # Clase principal

│   └── resources

│       └── application.properties # Configuración de la aplicación 

└── test
    ├── java/com/coderhouse # Tests unitarios y de integración
    
Autor
Tu Nombre
📧 Correo: avendaoagustin@gmail.com
💼 LinkedIn: linkedin.com/in/tomas-agustin-avendaño-21a834241
