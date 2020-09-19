FROM java:8-jre
WORKDIR /usr/src
ENV MONGO_DATABASE=keepnote6
ADD ./target/UserService-0.0.1-SNAPSHOT.jar /usr/src/UserService-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","UserService-0.0.1-SNAPSHOT.jar"]