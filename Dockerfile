FROM eclipse-temurin:17-jdk-jammy
ARG JAR_FILE=web/build/libs/*boot.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app.jar", "–Dspring.profiles.active=prod"]