FROM openjdk:17
ADD target/protoFood-API.jar protoFood-API.jar
ENTRYPOINT ["java", "-jar", "protoFood-API.jar"]