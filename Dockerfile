# Stage 1: Build the JAR
FROM maven:3.9.4-eclipse-temurin-17-alpine AS build
WORKDIR /app

# Copy pom and source code
COPY pom.xml .
COPY src ./src

# Build the app (adjust if you want tests skipped)
RUN mvn clean package -DskipTests

# Stage 2: Run the JAR
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/ecommerce-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 (your app should use $PORT from env)
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
