# =============================
# STAGE 1: Extract Dependencies
# =============================
FROM eclipse-temurin:21-jdk-alpine AS deps

# Location of the JAR FILE (build by Maven)
COPY target/*.jar app.jar

# Unpack the Jar (Spring Boot layered jar caching)
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

# =============================
# STAGE 2: Runtime Container
# =============================
FROM eclipse-temurin:21-jdk-alpine

LABEL  maintainer="Benjamin Feuerborn <b.feuerborn03@gmail.com>"

# Volume for temp files
VOLUME /tmp

# Copy dependencies and classes
ARG DEPENDENCY=/target/dependency
COPY --from=deps ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=deps ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=deps ${DEPENDENCY}/BOOT-INF/classes /app

# Run with container optimized JVM settings
ENTRYPOINT ["java", "-cp", "/app:app/lib/*", "com.watchfriends.userservice.UserServiceApplication"]