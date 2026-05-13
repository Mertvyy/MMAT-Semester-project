# Build Stage
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# Run Stage
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/cargo-1.7.jar app.jar
COPY packageData.txt packageData.txt
COPY mapData.txt mapData.txt

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
