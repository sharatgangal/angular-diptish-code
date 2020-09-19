FROM java:8-jre
WORKDIR /usr/src
ENV MONGO_DATABASE=keepnote6
ADD ./target/ReminderService-0.0.1-SNAPSHOT.jar /usr/src/ReminderService-0.0.1-SNAPSHOT.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","ReminderService-0.0.1-SNAPSHOT.jar"]