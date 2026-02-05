# syntax=docker/dockerfile:1.6

#############################
# 1) Build stage
#############################
FROM maven:3.9.8-eclipse-temurin-21 AS builder
WORKDIR /workspace

COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 \
    mvn -q -DskipTests dependency:go-offline

COPY src ./src
RUN --mount=type=cache,target=/root/.m2 \
    mvn -q -DskipTests package

#############################
# 2) Runtime stage
#############################
FROM eclipse-temurin:21-jre
RUN groupadd -g 10001 spring && useradd -g spring -u 10001 -m spring
USER spring:spring
WORKDIR /app

# Expose correct port and point the client to analogue-service (overridable)
ENV SERVER_PORT=8082 \
    SPRING_MAIN_BANNER_MODE=off \
    JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0" \
    ANALOGUE_BASE_URL="http://analogue-service:8081"

COPY --from=builder /workspace/target/*.jar /app/app.jar

EXPOSE 8082
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
