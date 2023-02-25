FROM eclipse-temurin:17-jdk-jammy
ARG JAR_FILE=rocketmq-consumer/build/libs/*boot.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8108
ENTRYPOINT java --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED --add-opens=java.base/java.util.concurrent=ALL-UNNAMED --add-opens=java.rmi/sun.rmi.transport=ALL-UNNAMED --add-opens java.base/java.lang.reflect=ALL-UNNAMED --add-opens java.base/java.util=ALL-UNNAMED --add-opens java.base/java.math=ALL-UNNAMED -jar /app.jar