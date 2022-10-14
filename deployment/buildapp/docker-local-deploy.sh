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
DOCKER_TAG="r3app"

echo "Stopping an existing docker container..."
DOCKER_CTR_STOP="$(docker container stop ${DOCKER_TAG} | cut -d ':' -f 2)"
echo "Stop Status :: ${DOCKER_CTR_STOP}"

echo "Removing stopped container..."
DOCKER_CTR_RM="$(docker container rm ${DOCKER_TAG})"
echo "Remove Status :: ${DOCKER_CTR_RM}"

DOCKER_CURR="$(docker image rm ${DOCKER_TAG})"
echo "Removing existing docker images..."
echo "Remove Status :: ${DOCKER_CURR}"

GIT_COMMIT_LABEL="$(git log | head -n 1 | cut -d '/' -f 2)"

docker build -t ${DOCKER_TAG} --build-arg build_info="${JAR_FILE} ${GIT_COMMIT_LABEL}-${BUILD_NUMBER}" --no-cache -f ./deployment/buildapp/Dockerfile .
echo "Done Creating Docker Image"
echo ""
echo ""
echo "Deploying a new docker container...."
DOCKER_UUID="$(docker container run -p 8081:8081 -d --name ${DOCKER_TAG} ${DOCKER_TAG})"
echo "New Docker ID deployed :: ${DOCKER_UUID}"
