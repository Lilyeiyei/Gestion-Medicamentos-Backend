# ─────────────────────────────────────────────
# STAGE 1: Build con Maven + JDK 21
# ─────────────────────────────────────────────
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# Copiar archivos de Maven primero (aprovecha el cache de capas)
COPY pom.xml .
COPY .mvn/ .mvn/
COPY mvnw .

# Dar permisos al wrapper
RUN chmod +x mvnw

# Descargar dependencias en capa separada (cache-friendly)
RUN ./mvnw dependency:go-offline -q

# Copiar el código fuente
COPY src/ src/

# Build del JAR en modo JVM (fast-jar de Quarkus)
RUN ./mvnw package -DskipTests -Dquarkus.package.jar.type=fast-jar

# ─────────────────────────────────────────────
# STAGE 2: Runtime liviano con JRE 21
# ─────────────────────────────────────────────
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copiar el output del build (fast-jar de Quarkus)
COPY --from=builder /app/target/quarkus-app/lib/ ./lib/
COPY --from=builder /app/target/quarkus-app/*.jar ./
COPY --from=builder /app/target/quarkus-app/app/ ./app/
COPY --from=builder /app/target/quarkus-app/quarkus/ ./quarkus/

# Puerto que expone Quarkus (default 8080)
EXPOSE 8080

# Variable de entorno para Render
ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"

# Comando de arranque
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar quarkus-run.jar"]