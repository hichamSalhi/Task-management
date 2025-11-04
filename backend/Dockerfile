# === Stage 1: Build the Spring Boot app ===
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY backend/taskmanagement/pom.xml .
COPY backend/taskmanagement/src ./src
RUN mvn -f pom.xml clean package -DskipTests

# === Stage 2: Run the JAR ===
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]