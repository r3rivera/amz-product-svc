FROM amazoncorretto:17.0.4-alpine

ARG build_info

LABEL Owner="r.ryan.rivera@outlook.com"
LABEL Description="Spring Boot Application"
LABEL Build=$build_info

EXPOSE 8081/tcp
COPY target/$jar_name "app.jar"
ENTRYPOINT ["java","-jar","/app.jar"]
# CMD ["/greetings.sh"]