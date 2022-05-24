#!/bin/bash

set -e


## Production 配置
export BASE_URL=https://studioxxx.xxx.xxx
export SESSION_DOMAIN=xxx.xxx.xxx
export DASHBOARD_URL=https://studioxxx.xxx.xxx/dashboard/workspace
export HEADLESS_MODE=true

APP_PATH="$(cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null && pwd)"
APP_NAME="$(basename "$APP_PATH")"
ROOT_PATH="${APP_PATH}/../../"
MAVEN_CACHE_PATH="${ROOT_PATH}/cache/m2"

if [ ! -d ${MAVEN_CACHE_PATH} ]; then
    mkdir -p ${MAVEN_CACHE_PATH}
fi


docker build . -t ui-test-runner
docker run --rm \
    -w /work \
    -v ${ROOT_PATH}:/work \
    -v ${MAVEN_CACHE_PATH}:/home/seluser/.m2 \
    -v ${ROOT_PATH}/config/settings.xml:/usr/share/maven/conf/settings.xml \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -e MAVEN_OPTS='-XX:+TieredCompilation -XX:TieredStopAtLevel=1' \
    -e BASE_URL=${BASE_URL} \
    -e SESSION_DOMAIN=${SESSION_DOMAIN} \
    -e DASHBOARD_URL=${DASHBOARD_URL} \
    -e DRIVER_WAY=${DRIVER_WAY} \
    ui-test-runner \
    sh -c "cd app/${APP_NAME} && mvn -T 1C test && cd - && chown -R $(id -u):$(id -g) cache app/${APP_NAME}/target"
