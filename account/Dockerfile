FROM eclipse-temurin:17-jdk-jammy
ARG JAR_FILE=account/build/libs/*boot.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8105 20885 8848 9848 8091 7091
#ENTRYPOINT ["java", "-jar", "/app.jar", "–Dspring.profiles.active=dev"]
ENTRYPOINT java --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED --add-opens=java.base/java.util.concurrent=ALL-UNNAMED --add-opens=java.rmi/sun.rmi.transport=ALL-UNNAMED --add-opens java.base/java.lang.reflect=ALL-UNNAMED --add-opens java.base/java.util=ALL-UNNAMED --add-opens java.base/java.math=ALL-UNNAMED -jar /app.jar