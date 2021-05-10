FROM openjdk:11.0.11-oraclelinux7
ARG JAR_FILE=target/phoneNumberValidtor-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
COPY sample.db sample.db
EXPOSE 12345
ENTRYPOINT ["java","-jar","/app.jar"]