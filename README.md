# aduana-usuarios-api

## Descripción

Microservicio de gestión de usuarios perteneciente al sistema de Aduana. Actúa como orquestador, consumiendo los endpoints del microservicio base (`aduana-api`) mediante Feign Client. Forma parte de una arquitectura de microservicios desarrollada con Spring Boot.

## Integrantes

| Nombre | Rol |
|--------|-----|
| Joaquín Leiva | Líder Técnico / Arquitecto |
| Octavio Echeverría | Desarrollador Backend Senior |
| Thiara Rojas | Desarrolladora Frontend / UX |
| Luna Bustamante | Desarrolladora Junior / QA |

## Tecnologías utilizadas

- Java 17
- Spring Boot 3.3.5
- Spring Cloud OpenFeign
- Spring Boot Validation
- Lombok
- Maven

## Funcionalidades implementadas

- Listar todos los usuarios
- Buscar usuario por ID
- Crear usuario
- Actualizar usuario
- Eliminar usuario
- Obtener reportes asociados a un usuario

## Endpoints REST

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/usuarios` | Lista todos los usuarios |
| GET | `/api/v1/usuarios/{id}` | Obtiene un usuario por ID |
| POST | `/api/v1/usuarios` | Crea un nuevo usuario |
| PUT | `/api/v1/usuarios/{id}` | Actualiza un usuario existente |
| DELETE | `/api/v1/usuarios/{id}` | Elimina un usuario |
| GET | `/api/v1/usuarios/{id}/reportes` | Obtiene los reportes de un usuario |

## Estructura del proyecto

```
src/main/java/cl/duocuc/aduana_usuarios_api/
├── client/
│   └── AduanaClient.java
├── controller/
│   └── UsuarioController.java
├── dto/
│   ├── ApiResponse.java
│   ├── ReporteResponseDTO.java
│   ├── UsuarioRequestDTO.java
│   └── UsuarioResponseDTO.java
├── service/
│   └── UsuarioService.java
└── AduanaUsuariosApiApplication.java
```

## Pasos para ejecutar

### Requisitos previos

- Java 17 instalado
- Maven instalado
- El microservicio `aduana-api` corriendo en `http://localhost:8080`

### Ejecución

1. Clonar el repositorio:
```bash
git clone https://github.com/JoaquinLeivaDev/aduana-usuarios-api.git
cd aduana-usuarios-api
```

2. Compilar y ejecutar:
```bash
./mvnw spring-boot:run
```

3. El servicio quedará disponible en:
```
http://localhost:8082
```

### Ejemplo de petición

**Crear usuario:**
```bash
POST http://localhost:8082/api/v1/usuarios
Content-Type: application/json

{
  "username": "jleiva",
  "password": "123456",
  "idRol": 1
}
```
