FROM openjdk:8-jdk-alpine
COPY target/mongo-0.0.1-SNAPSHOT.jar mongo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/mongo-0.0.1-SNAPSHOT.jar"]