FROM openjdk:17
COPY ./target/*.jar order-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/order-service.jar"]