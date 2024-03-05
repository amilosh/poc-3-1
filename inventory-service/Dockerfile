FROM openjdk:17
COPY ./target/*.jar inventory-service.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/inventory-service.jar"]