# Start from an OpenJDK image
FROM openjdk:17-jdk-jammy

# Set the working directory in the container
WORKDIR /app

# Copy the built Spring Boot application JAR into the container
COPY target/*.jar app.jar

# Expose the port your app runs on (default for Spring Boot is 8080)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
