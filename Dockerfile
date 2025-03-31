FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

ENTRYPOINT ["top", "-b"]

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:22

COPY --from=build /app/target/webcotaspringboot-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar","app.jar"]