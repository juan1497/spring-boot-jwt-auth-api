# 🔐 API de Autenticación con Spring Boot y JWT

API REST para autenticación de usuarios construida con Spring Boot, Spring Security y JWT.  
Permite registro, login y acceso a endpoints protegidos mediante autenticación stateless.

---

## 🚀 Funcionalidades

- Registro de usuarios (signup)
- Login con generación de token JWT
- Encriptación de contraseñas con BCrypt
- Autenticación sin sesiones (stateless)
- Protección de endpoints con Spring Security
- Manejo global de excepciones
- Integración con PostgreSQL
- Arquitectura por capas (Controller, Service, Repository)

---

## 🧰 Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Security
- JWT (jjwt)
- PostgreSQL
- Maven

---

## 🔑 Flujo de autenticación

1. El usuario inicia sesión:

POST /api/auth/login

2. El servidor responde con:

- Access Token (JWT)
- Información del usuario

3. El cliente envía el token en cada petición:

Authorization: Bearer <token>

4. El backend valida el token mediante un filtro personalizado

---

## 📦 Endpoints

### 🔓 Públicos

| Método | Endpoint         | Descripción        |
| ------ | ---------------- | ------------------ |
| POST   | /api/auth/signup | Registrar usuario  |
| POST   | /api/auth/login  | Autenticar usuario |

---

### 🔒 Protegidos

| Método | Endpoint   | Descripción                 |
| ------ | ---------- | --------------------------- |
| GET    | /api/users | Requiere token válido (JWT) |

---

## ⚙️ Variables de entorno

La aplicación utiliza variables de entorno para la configuración:

DB_URL=jdbc:postgresql://...
DB_USERNAME=...
DB_PASSWORD=...

JWT_SECRET=tu_clave_secreta
JWT_EXPIRATION=86400000

---

## 🗄️ Base de datos

- PostgreSQL desplegado en Render
- Las tablas se crean automáticamente usando Hibernate (ddl-auto=update)
- Los roles se insertan manualmente

---

## 🛠️ Ejecución local

git clone https://github.com/tu-usuario/spring-boot-jwt-auth-api.git
cd spring-boot-jwt-auth-api

Configura las variables de entorno o el application.properties.

Ejecuta la aplicación:

mvn spring-boot:run

---

## 🌐 Despliegue

El proyecto está desplegado en Render.

---

## 🧠 Mejoras futuras

- Implementación de refresh token
- Autorización basada en roles (RBAC)
- Migraciones con Flyway
- Dockerización
- Documentación con Swagger

---
