FROM eclipse-temurin:17
WORKDIR /app
EXPOSE 9001
ADD target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
