FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /usr/src/app
# Al usar WORKDIR, el punto (.) copia el pom de la raíz directo al directorio de trabajo
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

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