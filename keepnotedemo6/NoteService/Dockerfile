FROM java:8-jre
WORKDIR /usr/src
ENV MONGO_DATABASE=keepnote6
ADD ./target/NoteService-0.0.1-SNAPSHOT.jar /usr/src/NoteService-0.0.1-SNAPSHOT.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","NoteService-0.0.1-SNAPSHOT.jar"]