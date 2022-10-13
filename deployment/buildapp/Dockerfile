FROM openjdk:8-jdk-alpine


ARG build_number
ENV git_build_number $build_number

ARG jar_name
ENV script_jar_name $jar_name

# RUN chmod +x /greetings.sh

MAINTAINER r.ryan.rivera@outlook.com

COPY target/$jar_name "message-server-1.0.0.jar.jar"
ENTRYPOINT ["java","-jar","/message-server-1.0.0.jar"]
# CMD ["/greetings.sh"]