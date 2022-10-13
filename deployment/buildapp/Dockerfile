FROM amazoncorretto:17.0.4-alpine

ARG jar_name

# RUN chmod +x /greetings.sh

# MAINTAINER r.ryan.rivera@outlook.com
LABEL Jar=$jar_name
LABEL Owner="r.ryan.rivera@outlook.com"
LABEL Description="Spring Boot Application"

EXPOSE 8081/tcp

COPY target/$jar_name "app.jar"
ENTRYPOINT ["java","-jar","/app.jar"]
# CMD ["/greetings.sh"]