FROM openjdk:8-jdk-alpine
COPY target/EchoChainAPI-0.0.1-SNAPSHOT.jar /app/my-app.jar
ADD /src/main/resources/application.properties /app/application.properties
ADD /src/main/resources/application.yml /app/application.yml
ENV AWS_ACCESS_KEY_ID="AKIA4XGGFKSZMZVGG64L"
ENV AWS_SECRET_ACCESS_KEY="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"
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/my-app.jar", "--spring.config.location=classpath:/application.properties,/app/application.yml"]
CMD ["java", "-jar", "my-app.jar"]
