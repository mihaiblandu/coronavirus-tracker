FROM openjdk:11-jdk-slim
ADD target/*.jar /opt/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app.jar"]