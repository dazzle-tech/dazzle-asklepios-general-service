FROM maven:3.8.6-eclipse-temurin-17-alpine AS build

WORKDIR /app

COPY . /app

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/asklepios-services-1.0.0.jar /app/service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "service.jar"]
