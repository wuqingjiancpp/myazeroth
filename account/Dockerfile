FROM eclipse-temurin:17-jdk-jammy
ARG JAR_FILE=account/build/libs/*boot.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8105
ENTRYPOINT ["java", "-jar", "/app.jar", "–Dspring.profiles.active=prod"]