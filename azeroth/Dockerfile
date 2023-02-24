FROM eclipse-temurin:17-jdk-jammy
ARG JAR_FILE=azeroth/build/libs/*boot.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8104 20884
ENTRYPOINT ["java", "-jar", "/app.jar", "–Dspring.profiles.active=prod"]