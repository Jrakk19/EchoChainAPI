FROM openjdk:8-jdk-alpine
COPY target/EchoChainAPI-0.0.1-SNAPSHOT.jar /app/my-app.jar
ADD /src/main/resources/application.properties /app/application.properties
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java" ,"-Djava.security.egd=file:/dev/./urandom --spring.config.location=classpath:file:/app/application-properties","-jar","/app/my-app.jar"]
CMD ["java", "-jar", "my-app.jar"]
