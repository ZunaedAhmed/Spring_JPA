FROM maven:3.10.1-jdk-21 AS build
WORKDIR /workspace
COPY . /workspace
RUN mvn -B -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
