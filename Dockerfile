FROM openjdk:17

COPY ./ /opt
WORKDIR /opt

ENTRYPOINT ["java", "-jar", "build/libs/message-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080/tcp
