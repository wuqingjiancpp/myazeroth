FROM eclipse-temurin:17-jdk-jammy
ARG JAR_FILE=account/build/libs/*boot.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8105 20885
ENTRYPOINT ["java", "--add-opens java.base/java.lang.reflect=ALL-UNNAMED","--add-opens java.base/java.lang=ALL-UNNAMED","--add-opens java.base/java.math=ALL-UNNAMED", "-jar", "/app.jar", "–Dspring.profiles.active=prod"]