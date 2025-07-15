# --- STAGE 1: Build the JAR ---
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy all project files
COPY . .

# Build the application
RUN mvn clean install -DskipTests

# --- STAGE 2: Run the app ---
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy the JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose Spring Boot default port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
