FROM openjdk:8-jdk-alpine
VOLUME /tmp
RUN apk add gradle &&  gradle build && find / | grep  hello-latest.jar && pwd && ls -aslt ./
ARG JAR_FILE=./build/libs/hello-latest.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

