# 빌드 스테이지
FROM openjdk:21-jdk-slim AS build

WORKDIR /app

COPY ./backend/gradlew ./gradlew
COPY ./backend/gradle ./gradle
COPY ./backend/build.gradle.kts ./build.gradle.kts
COPY ./backend/settings.gradle.kts ./settings.gradle.kts
COPY ./backend/src ./src

RUN chmod +x gradlew && ./gradlew clean build --no-daemon

# 런타임 스테이지
FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]