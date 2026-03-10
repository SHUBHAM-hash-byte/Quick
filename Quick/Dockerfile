FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# Maven build ke baad JAR 'target' folder mein banti hai
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]