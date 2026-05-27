# Paso 1: Usar una imagen oficial de Maven con Java 21 para compilar el proyecto
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvc -f /usr/src/app/pom.xml clean package -DskipTests

# Paso 2: Usar una imagen ligera de Java 21 para correr la aplicación
FROM eclipse-temurin:21-jre-alpine
ENV LANGUAGE='en_US:en'

# Crear el directorio de la aplicación
WORKDIR /deployments

# Copiar las dependencias y el JAR generado desde el paso de compilación
COPY --from=build /usr/src/app/target/quarkus-app/lib/ /deployments/lib/
COPY --from=build /usr/src/app/target/quarkus-app/*.jar /deployments/
COPY --from=build /usr/src/app/target/quarkus-app/app/ /deployments/app/
COPY --from=build /usr/src/app/target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080
ENV JAVA_OPTIONS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"

CMD ["java", "-jar", "/deployments/quarkus-run.jar"]