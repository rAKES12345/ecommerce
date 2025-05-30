FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/ecommerce-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 (Spring Boot default, but you will bind dynamically)
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
