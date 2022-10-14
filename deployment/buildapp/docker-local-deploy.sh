#!/usr/bin/env bash
echo ""
JAR_FILE="$(find ./target/*.jar | head -n 1 | cut -d '/' -f 3)"
echo ""
echo ""
echo "Current Docker Version"
docker --version
echo ""
echo "Current Directory is $(pwd)"
echo "Current User is $USER"
echo ""
echo "Start Creating Docker Image"
chmod +x ./deployment/buildapp/Dockerfile
DOCKER_TAG="r3app-${BUILD_NUMBER}"

DOCKER_CURR="$(docker container ls -a | head -n 1)"
echo "Current running containers are ${DOCKER_CURR}"

docker build -t ${DOCKER_TAG} --build-arg jar_name=${JAR_FILE} -f ./deployment/buildapp/Dockerfile .
echo "Done Creating Docker Image"
echo ""
echo ""