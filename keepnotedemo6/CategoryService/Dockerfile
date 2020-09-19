FROM java:8-jre
WORKDIR /usr/src
ENV MONGO_DATABASE=keepnote6
ADD ./target/CategoryService-0.0.1-SNAPSHOT.jar /usr/src/CategoryService-0.0.1-SNAPSHOT.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","CategoryService-0.0.1-SNAPSHOT.jar"]