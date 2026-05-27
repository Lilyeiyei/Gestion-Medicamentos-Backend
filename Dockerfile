# ETAPA 1: Compilación
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /home/app

# Copiamos absolutamente todo lo que venga de GitHub sin validar nada aún
COPY . .

USER root

# Tu script estrella: Busca el pom.xml donde sea que esté y acomoda el proyecto
RUN ACTUAL_PATH=$(find . -iname "pom.xml" -exec dirname {} \; | head -n 1) && \
    echo "Proyecto encontrado en: $ACTUAL_PATH" && \
    cp -r $ACTUAL_PATH/. . || true && \
    mvn clean package -DskipTests

# ETAPA 2: Imagen de ejecución oficial de RedHat para Quarkus
FROM registry.access.redhat.com/ubi9/openjdk-21-runtime:1.24
ENV LANGUAGE='en_US:en'

# Copiamos la carpeta completa de la aplicación generada
COPY --from=build /home/app/target/quarkus-app/ /deployments/

EXPOSE 8080
USER 185
ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"

ENTRYPOINT [ "/opt/jboss/container/java/run/run-java.sh" ]