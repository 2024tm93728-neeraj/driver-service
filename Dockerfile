FROM openjdk:17-jdk-slim
WORKDIR /app
ARG JAR_FILE=target/driver-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} driver-service.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/driver-service.jar"]
