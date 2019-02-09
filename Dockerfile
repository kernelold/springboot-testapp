FROM openjdk:8-jdk-alpine as builder 
VOLUME /tmp
RUN apk add gradle curl &&  cd / && \
  curl -L https://api.github.com/repos/kernelold/boot-core/tarball > hello.tar.gz && \
  tar xvf hello.tar.gz && mv kernelold-boot-core-* hello && cd hello && \
  gradle build 


FROM openjdk:8-jdk-alpine  
RUN apk add openssh && mkdir -p /root/.ssh/ && echo 'ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQDJRozryqsuLj/k4Di+fZoaKXea1b8gYhrJIxLpjP4z27fUCxgRLF9WHiT/4eIBaGFe98h1Id5j9W9QI3HqhWsC1Fot/Wx5uAkQhxEpjGOv/JL8RRlYMyk4J7kQwF/z2fOGk8XvevQuOT22oOTAqL8+O7D2xnOxoamDx6kJkOFBBBKAQuJPrEBH8YPTybFeEoQvxac8EHcpwQsNNBm2WW/pof2a+Iq3UhFkFP0n8q6vXhtvGsveLVJJ/drobdOE2MnszMY45Ktl2dACr5+b2unzuwOwnqXY47z2mRDMTa+1RXr4QO30e/i4FkWo+9zIuXEo5LKKpePm6s1muNK/eLHOsVHlt9i0HeyjDeZPPCORut4LskwpLoyz4gcFcak9KqqeWpeAborBhjArEsp5ceKrr5cHOz7fQROwKinvUvPVr9DvGQIUcmK2kDSdXtkrzKmCNlKmOIfsqVzjL5aOi4dJktqL5B6gSMigDHVfo5oT0rVObcuhLc8k023RUE+LF8hkkZWtbqLN2m6E/v4LDcSsxubAw2sjIrrZNVTx/wKWyk1di2Hiva0RCq5E0PQK4OJrZwzL+UWZ52CiPs7Yc5OsdQ0hDCEHAP0Vx3mKC7r3VD1u4wO4xiD3TCTXwCPzMnoHf77wtnZGZcoW0ESl4dG15sh5LfRxt1QJiVtTBw+klw== root@localhost.localdomain ' > /root/.ssh/authorized_keys && chmod 700 /root/.ssh/ && chmod 600 /root/.ssh/authorized_keys ;
COPY --from=builder /hello/build/libs/hello-latest.jar /hello-latest.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/hello-latest.jar"]
