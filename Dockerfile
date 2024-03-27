FROM openjdk:17-jdk-slim as build

WORKDIR /app

COPY gradlew .
RUN chmod +x ./gradlew
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

COPY src src

RUN ./gradlew bootJar

FROM openjdk:17-jdk-slim

COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
