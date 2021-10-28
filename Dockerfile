FROM openjdk:8u191-jdk-alpine

VOLUME /tmp

ADD ./target/demo-spring-amqp-fanout-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/app.jar"]