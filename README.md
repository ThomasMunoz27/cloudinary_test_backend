# ☁️ Cloudinary Test - Backend

Este es el **Backend** del proyecto **Cloudinary Test**, desarrollado con **Java + Spring Boot**.  
El backend expone una **API REST** que permite gestionar usuarios, autenticación con **JWT**, subida de imágenes a **Cloudinary** y operaciones CRUD sobre imágenes, usuarios y categorías.  

---

## 🚀 Tecnologías utilizadas

- ☕ **Java 17+**
- 🌱 **Spring Boot**
  - Spring Web
  - Spring Data JPA
  - Spring Security (JWT)
- 🗄️ **MySQL** (base de datos)
- 🔑 **JWT (JSON Web Token)** para autenticación
- ☁️ **Cloudinary SDK** para almacenamiento de imágenes en la nube
- 📦 **Gradle** como gestor de dependencias

---

## ⚙️ Requisitos previos

Antes de ejecutar este proyecto, necesitas:

- [Java 17+](https://adoptium.net/)  
- [Gradle](https://gradle.org/) (opcional si usas wrapper)  
- [MySQL](https://dev.mysql.com/) corriendo en local o en servidor  
- Una cuenta en [Cloudinary](https://cloudinary.com/) con credenciales de acceso  

---

## 📦 Instalación

Clona el repositorio:

git clone https://github.com/ThomasMunoz27/cloudinary_test_backend.git
cd cloudinary_test_backend

./gradlew build

## 🔑 Configuración

El proyecto requiere algunas variables de entorno/configuración para funcionar correctamente:

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/cloudinary_test?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD
spring.jpa.hibernate.ddl-auto=update

# JWT
jwt.secret=TU_SECRETO_JWT
jwt.expiration=3600000

# Cloudinary
cloudinary.cloud_name=TU_CLOUD_NAME
cloudinary.api_key=TU_API_KEY
cloudinary.api_secret=TU_API_SECRET

📸 Funcionalidades principales

🔐 Autenticación con JWT (Registro / Login de usuarios y admin)

🖼️ Subir imágenes a Cloudinary

📂 CRUD completo de imágenes, categorías, comentarios y usuarios

🔒 Seguridad con roles y privilegios (admin / usuario)

⚡ Integración con frontend vía API REST

🗄️ Persistencia con MySQL

## 🧑‍💻 Autor

👤 Thomas Muñoz

##📜 Licencia

Este proyecto es de uso libre con fines educativos y demostrativos. 🚀
