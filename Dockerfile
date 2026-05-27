# ETAPA 1: Compilación
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /home/app

# Copiamos todo lo que vino de GitHub
COPY . .

USER root

# Cambiamos la estrategia: Buscamos dónde está el pom.xml, descubrimos su carpeta,
# y le ordenamos a Maven compilar apuntando directamente a ese archivo físico.
RUN ACTUAL_PATH=$(find . -iname "pom.xml" -exec dirname {} \; | head -n 1) && \
    echo "¡Proyecto encontrado en la ruta!: $ACTUAL_PATH" && \
    mvn -f $ACTUAL_PATH/pom.xml clean package -DskipTests -Dquarkus.package.type=fast-jar

# ETAPA 2: Imagen de ejecución oficial de RedHat para Quarkus
FROM registry.access.redhat.com/ubi9/openjdk-21-runtime:1.24
ENV LANGUAGE='en_US:en'
WORKDIR /deployments

# Buscamos y copiamos los artefactos de Quarkus sin importar en qué subcarpeta quedaron
COPY --from=build /home/app/**/target/quarkus-app/ /deployments/

EXPOSE 8080
USER 185
ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"

ENTRYPOINT [ "/opt/jboss/container/java/run/run-java.sh" ]