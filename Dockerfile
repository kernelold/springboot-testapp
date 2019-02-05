FROM openjdk:8-jdk-alpine as builder 
VOLUME /tmp
RUN apk add gradle curl &&  cd / && \
  curl -L https://api.github.com/repos/kernelold/boot-core/tarball > hello.tar.gz && \
  tar xvf hello.tar.gz && mv kernelold-boot-core-* hello && cd hello && \
  gradle build 


FROM openjdk:8-jdk-alpine  
COPY --from=builder /hello/build/libs/hello-latest.jar /hello-latest.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/hello-latest.jar"]
