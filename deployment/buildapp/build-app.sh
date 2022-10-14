#!/usr/bin/env bash
echo "## Building the configuration of ${APP_NAME}..."
echo "## Workspace is ${WORKSPACE}"
echo "############## START ::: BUILD INFORMATION ##############"
echo ""
echo "## Displaying Java Version..."
java -version | head -n 1

echo "## Displaying Maven Version..."
mvn --version | head -n 1

echo "## Displaying GIT Version..."
git --version | head -n 1
echo ""
echo "Git URL      :: ${GIT_URL}"
echo "Git Branch   :: ${GIT_BRANCH}"
echo "Build ID     :: ${BUILD_ID}"
echo "Build Number :: ${BUILD_NUMBER}"
echo "Git Branch   :: ${GIT_BRANCH}"
GIT_COMMITID="$(git log | head -n 1 | cut -w -f 2)"
BUILD_DATE="$(date +%m%d%Y)"
BUILD_TIME="$(date +%H%M%S)"
echo "Build Date   :: ${BUILD_DATE}"
echo "Build Time   :: ${BUILD_TIME}"
echo "Git Commit   :: ${GIT_COMMITID}"
echo ""
echo ""
echo "Start compiling the application"
mvn clean package
JAR_FILE="$(find ./target/*.jar | head -n 1 | cut -d '/' -f 3)"
echo "Compilation Complete :: JAR File is ${JAR_FILE}"
echo "############## END   ::: BUILD INFORMATION ##############"