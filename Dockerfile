# Etapa de construcción
FROM openjdk:17-jdk as build
WORKDIR /app
COPY . /app
RUN ./mvnw package

# Etapa de ejecución
FROM openjdk:17-jre
LABEL authors="laclavees12345"
LABEL maintainer="Luis Angel Orduna Marquez <isc20350669@gmail.com>"
LABEL version="1.0"
LABEL description="Inventario de Medicamentos Xcaret"
WORKDIR /app
COPY --from=build /app/target/Farmacia-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
