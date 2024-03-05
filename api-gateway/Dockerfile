FROM openjdk:17
COPY ./target/*.jar api-gateway.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/api-gateway.jar"]