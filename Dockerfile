FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/fruit-order-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENV SPRING_DATA_MONGODB_URI=mongodb://mongo:27017/fruit_orders_db

ENTRYPOINT ["java", "-jar", "app.jar"]