FROM openjdk:17
ADD target/protoFood-DP.jar protoFood-DP.jar
ENTRYPOINT ["java", "-jar", "protoFood-DP.jar"]