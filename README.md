# Prueba Técnica - API de Equipos de Fútbol

Este proyecto es una API RESTful para gestionar equipos de fútbol. Permite realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre equipos, así como autenticación mediante JWT.

## **Características**

- Listar todos los equipos
- Obtener un equipo por ID
- Buscar equipos por nombre
- Crear un nuevo equipo
- Actualizar un equipo existente
- Eliminar un equipo
- Autenticación JWT

## **Tecnologías utilizadas**

- Java
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- Swagger para documentación de API

## **Configuración**

### **Requisitos previos**

- Java JDK 11 o superior
- Maven

### **Pasos para ejecutar**

1. Clonar el repositorio
2. Navegar al directorio del proyecto
3. Ejecutar ***mvn spring-boot:run***

La aplicación estará disponible en ***http://localhost:8080***

## **Documentación de la API**

La documentación de la API está disponible a través de Swagger UI:

```
http://localhost:8080/swagger-ui/index.html#/
```

## Uso de Postman

Se proporciona una colección de Postman para probar la API. Puedes importar los siguientes archivos en Postman:

- Colección: ***prueba-tecnica.postman_collection.json***
- Entorno: ***prueba_tecnica.postman_environment.json***

### Variables de entorno en Postman

- ***host***: http://localhost:8080
- ***token***: Se actualiza automáticamente al hacer login

### Endpoints principales

1. **Listar todos los equipos**
   - Método: GET
   - URL: \`{{host}}/equipos\`
   - No requiere autenticación

2. **Obtener equipo por ID**
   - Método: GET
   - URL: \`{{host}}/equipos/{id}\`
   - Requiere autenticación

3. **Crear equipo**
   - Método: POST
   - URL: \`{{host}}/equipos\`
   - Requiere autenticación
   - Body: JSON con los datos del equipo

4. **Actualizar equipo**
   - Método: PUT
   - URL: \`{{host}}/equipos/{id}\`
   - Requiere autenticación
   - Body: JSON con los datos actualizados del equipo

5. **Eliminar equipo**
   - Método: DELETE
   - URL: \`{{host}}/equipos/{id}\`
   - Requiere autenticación

6. **Autenticación (obtener token JWT)**
   - Método: POST
   - URL: \`{{host}}/auth/login\`
   - Body: JSON con username y password

## Seguridad

La API utiliza autenticación JWT. Para acceder a los endpoints protegidos, se debe incluir el token JWT en el header de la petición:


***Authorization: Bearer {token}***


# Docker
**Construcción de la imagen Docker**

Posicionándose en el directorio donde se encuentra el archivo Dockerfile, ejecute el siguiente comando para crear la imagen:
```
docker build -t prueba-tecnica
```
Este comando compila la imagen a partir de las instrucciones definidas en el Dockerfile y le asigna la etiqueta prueba-tecnica.


**Ejecución de un contenedor basado en la imagen creada**

Una vez que la imagen ha sido creada, utilice el siguiente comando para ejecutar una instancia del contenedor:
```
docker run -p 8080:8080 prueba-tecnica
```
Este comando inicia un contenedor basado en la imagen prueba-tecnica, mapeando el puerto 8080 del contenedor al puerto 8080 en el host.

