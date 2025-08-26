
#Config de TodoCode
# Usamos una imagen base con Java 17
FROM openjdk:17-jdk-slim

# Variable para ubicar el jar
ARG JAR_FILE=build/libs/*.jar

# Copiamos el jar dentro del contenedor
COPY ${JAR_FILE} app_cloudinarytest.jar

# Exponemos el puerto 8080 (Spring Boot por defecto)
EXPOSE 8080

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app_cloudinarytest.jar"]


# Usar imagen oficial de Java 17
#FROM eclipse-temurin:17-jdk-jammy

# Crear directorio de la app
#WORKDIR /app

# Copiar archivos de Gradle y configuración
#COPY gradlew .
#COPY gradle gradle
#COPY build.gradle .
#COPY settings.gradle .

#COPY . .

# Dar permisos de ejecución al wrapper de Gradle
#RUN chmod +x gradlew

# Copiar el resto del proyecto
#COPY src src
#COPY application-prod.properties application-prod.properties

# Build del proyecto usando el profile prod
#RUN ./gradlew build -x test -Pprofile=prod

# Exponer el puerto que Render asignará dinámicamente
#EXPOSE 8080

# Comando para arrancar la app usando el .jar generado y el puerto dinámico
#CMD ["java", "-jar", "build/libs/cloudinary_test_backend-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod", "--server.port=$PORT"]
