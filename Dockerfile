FROM ubuntu:latest
LABEL authors="laclavees12345"

ENTRYPOINT ["top", "-b"]
LABEL maintainer="Luis Angel Orduna Marquez <isc20350669@gmail.com>"
LABEL version="1.0"
LABEL description="Inventario de Medicamentos Xcaret"
# Usa una imagen base de OpenJDK para Java 17
FROM openjdk:17-jdk

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR construido a la imagen
COPY target/Farmacia-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que se ejecuta tu aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación al iniciar el contenedor
CMD ["java", "-jar", "app.jar"]