FROM openjdk:17-alpine
WORKDIR /app
ADD staging/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
