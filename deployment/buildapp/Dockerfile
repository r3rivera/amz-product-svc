FROM openjdk:8-jdk-alpine

ARG jar_name
ENV script_jar_name $jar_name


MAINTAINER r.ryan.rivera@outlook.com

COPY target/docker-message-server-1.0.0.jar message-server-1.0.0.jar
# ENTRYPOINT ["java","-jar","/message-server-1.0.0.jar"]