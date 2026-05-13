# Build Stage
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# Run Stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/cargo-1.7.jar app.jar
COPY packageData.txt packageData.txt
COPY mapData.txt mapData.txt

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
