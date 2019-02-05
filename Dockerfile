FROM openjdk:8-jdk-alpine
VOLUME /tmp
RUN apk add gradle curl && \
  curl -L https://api.github.com/repos/kernelold/boot-core/tarball > hello.tar.gz && \
  tar xvf hello.tar.gz && mv kernelold-boot-core-* hello && cd hello && \
  gradle build 
ARG JAR_FILE=./build/libs/hello-latest.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

