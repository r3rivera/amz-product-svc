FROM openjdk:8-jdk-alpine

ARG jar_name

# RUN chmod +x /greetings.sh

MAINTAINER r.ryan.rivera@outlook.com

COPY target/$jar_name "app.jar"
ENTRYPOINT ["java","-jar","/app.jar"]
# CMD ["/greetings.sh"]