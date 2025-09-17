# Use Maven image to build
FROM maven:3.9.4-openjdk-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Use JRE to run
FROM openjdk:21-jre-slim
WORKDIR /app
COPY --from=build /app/target/calculadora-java-1.0-SNAPSHOT-shaded.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]