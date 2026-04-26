# 🌌 Astrodata API

Astrodata API es un servicio backend desarrollado con **Spring Boot** para la gestión y exposición de datos astronómicos. El proyecto sigue buenas prácticas de arquitectura, configuración por perfiles y empaquetado para producción.

Actualmente, los datos cósmicos que expone la API **provienen de un archivo `dump.sql`**, utilizado para poblar la base de datos inicial de forma controlada durante el desarrollo. En futuras versiones, este enfoque será reemplazado progresivamente por la **ingesta dinámica de datos** mediante el consumo de **APIs oficiales de agencias espaciales**, como:

*   NASA Open APIs: <https://api.nasa.gov/>
*   ESA Open Data Portal: <https://data.esa.int/>

Esto permitirá mantener la información más actualizada, ampliar el volumen de datos disponibles y reducir la dependencia de cargas manuales.

Este proyecto fue inicialmente generado con **Bootify.io**, que proporciona una base sólida para aplicaciones Spring Boot. Puedes encontrar consejos generales sobre la estructura del proyecto y los siguientes pasos aquí:  
<https://bootify.io/next-steps/>

***

## Tecnologías principales

*   Java 17+
*   Spring Boot
*   Spring Data JPA
*   Maven
*   Lombok
*   Base de datos relacional (configurable)
*   Docker (opcional para despliegue)

***

## Configuración del entorno de desarrollo

### 1. Configuración de la base de datos

Configura la conexión a tu base de datos local en:

    src/main/resources/application.properties

O, preferiblemente, crea un archivo específico para desarrollo:

    src/main/resources/application-local.properties

Esto permite mantener separadas las configuraciones de desarrollo y producción.

***

### 2. Perfil de ejecución

Durante el desarrollo se recomienda usar el perfil **local**.

#### IntelliJ IDEA

Añade el siguiente parámetro en la configuración de ejecución:

    -Dspring.profiles.active=local

(Recuerda habilitar primero *Modify options → VM options*).

***

### 3. Lombok

Este proyecto utiliza **Lombok** para reducir código boilerplate.

Asegúrate de:

*   Tener habilitado *Annotation Processing*

Guía completa:  
<https://bootify.io/next-steps/spring-boot-with-lombok.html>

***

### 4. Ejecutar la aplicación

Una vez iniciada, la API estará disponible en:

    http://localhost:8080

***

## Documentación de la API (Swagger / OpenAPI)

La API expone documentación interactiva mediante **Swagger UI**, lo que permite explorar y probar los endpoints disponibles directamente desde el navegador.

Una vez la aplicación esté en ejecución, puedes acceder a Swagger en:

    http://localhost:8080/swagger-ui/index.html

Desde esta interfaz podrás:

*   Consultar todos los endpoints disponibles
*   Ver modelos de datos y esquemas
*   Probar peticiones REST en tiempo real

***

## Build del proyecto

Para compilar el proyecto:

```bash
./mvnw clean package
```

El artefacto `.jar` se generará en el directorio `target/`.

***

## Ejecución en producción

Ejecuta la aplicación usando el perfil `production`:

```bash
java -Dspring.profiles.active=production -jar target/astrodata-api-0.0.1-SNAPSHOT.jar
```

Asegúrate de que las variables de entorno y la base de datos de producción estén correctamente configuradas.

***

## Docker (opcional)

Puedes construir una imagen Docker usando el plugin de Spring Boot:

```bash
./mvnw spring-boot:build-image \
  -Dspring-boot.build-image.imageName=me.abollo/astrodata-api
```

Al ejecutar el contenedor, recuerda definir el perfil activo:

    SPRING_PROFILES_ACTIVE=production

***

## Lecturas recomendadas

*   Maven  
    <https://maven.apache.org/guides/index.html>

*   Spring Boot Reference  
    <https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/>

*   Spring Data JPA  
    <https://docs.spring.io/spring-data/jpa/reference/jpa.html>
