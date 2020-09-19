FROM java:8-jre
WORKDIR /usr/src
ENV MYSQL_DATABASE=keepnote6
ADD ./target/UserAuthentication-Service-0.0.1-SNAPSHOT.jar /usr/src/UserAuthentication-Service-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","UserAuthentication-Service-0.0.1-SNAPSHOT.jar"]