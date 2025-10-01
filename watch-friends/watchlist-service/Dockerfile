# =============================
# STAGE 1: Extract Dependencies
# =============================
FROM eclipse-temurin:21-jdk AS deps

# Location of the JAR FILE (built by Maven)
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Unpack the Jar (Spring Boot layered jar caching)
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

# =============================
# STAGE 2: Runtime Container
# =============================
FROM eclipse-temurin:21-jdk

LABEL maintainer="Benjamin Feuerborn <b.feuerborn03@gmail.com>"

# Volume for temp files
VOLUME /tmp

# Copy dependencies and classes
ARG DEPENDENCY=/target/dependency
COPY --from=deps ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=deps ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=deps ${DEPENDENCY}/BOOT-INF/classes /app

# Default port (can override per-service)
EXPOSE 8083

# Run with container-optimized JVM settings
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75.0", "-cp", "app:app/lib/*", "org.springframework.boot.loader.launch.JarLauncher"]