# Usar imagen oficial de Maven con Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# 1. Copiamos PRIMERO el pom.xml para aprovechar la caché de Docker
COPY pom.xml .

# 2. En lugar de buscar la carpeta "/src", copiamos TODO lo que esté en la raíz del proyecto
# Esto incluye carpetas con mayúsculas/minúsculas (src, Src, SRC) de forma automática.
COPY . .

# Compilar omitiendo los tests para el despliegue rápido
RUN mvn clean package -DskipTests

# Paso 2: Imagen ligera para ejecución
FROM eclipse-temurin:21-jre-alpine
ENV LANGUAGE='en_US:en'
WORKDIR /deployments

# Copiar el resultado del paso de build
COPY --from=build /app/target/quarkus-app/lib/ /deployments/lib/
COPY --from=build /app/target/quarkus-app/*.jar /deployments/
COPY --from=build /app/target/quarkus-app/app/ /deployments/app/
COPY --from=build /app/target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080
ENV JAVA_OPTIONS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"

CMD ["java", "-jar", "/deployments/quarkus-run.jar"]