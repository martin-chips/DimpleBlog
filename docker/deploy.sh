#!/bin/bash
# the project home path
PROJECT_ROOT_PATH="/home/project/DimpleBlog"
# the docker compose home path
PROJECT_DOCKER_PATH="$PROJECT_ROOT_PATH/docker"

base() {
  echo "build and start dimple-mysql/dimple-redis/dimple-nacos"
  docker compose --compatibility up -d dimple-mysql dimple-redis dimple-nacos
}

modules() {
  echo "build and start dimple-modules"
  docker compose --compatibility up -d --build dimple-ui dimple-gateway dimple-auth dimple-modules-system dimple-modules-job dimple-modules-file dimple-modules-monitor dimple-modules-blog dimple-modules-blog-front dimple-modules-log
}

stop() {
  docker compose stop
}

down() {
  echo "will stop all service and rm all images, input Y/yes continue:"
  read input_choose
  if [ "${input_choose,,}" == 'y' ] || [ "${input_choose,,}" == 'yes' ]; then
    stop
    rm
  fi
}

# shellcheck disable=SC2032
rm() {
  docker compose rm
  docker images | grep none | awk '{print $3}' | xargs docker rmi
  docker images | grep dimple | awk '{print $3}' | xargs docker rmi
}

init() {
  echo 'This method only using the first deploy, <CTRL-C> exit and press any key will continue.'
  # shellcheck disable=SC2162
  read any_key
  default_password="Di^&7so@c@drxMe4"
  echo "please input the password:(We strongly recommend that you change the default password: ${default_password} )"
  # shellcheck disable=SC2162
  read input_password
  clean_sql
  clean_html
  clean_jar
  cp_sql
  build
  echo "start change default password to $input_password"
  change_default_password
}

# change the default password in MySQL，Redis，Nacos
change_default_password() {
  sed -i "s/$default_password/$input_password/g" $PROJECT_DOCKER_PATH/redis/conf/redis.conf
  sed -i "s/$default_password/$input_password/g" $PROJECT_DOCKER_PATH/nacos/conf/application.properties
  sed -i "s/$default_password/$input_password/g" $PROJECT_DOCKER_PATH/mysql/db/*
  sed -i "s/$default_password/$input_password/g" $PROJECT_DOCKER_PATH/docker-compose.yml
}

build() {
  echo 'start build the service,press any key continue.'
  # shellcheck disable=SC2162
  read any_key
  build_jar
  build_html
}

build_jar() {
  cd $PROJECT_ROOT_PATH
  mvn clean package -Dmaven.test.skip=true
  echo 'start copy jar '
  cp_jar
}

build_html() {
  echo 'start compile dimple-admin ui'
  cd $PROJECT_ROOT_PATH/dimple-ui-admin
  npm install
  npm run build:prod
  echo 'start compile dimple-blog ui'
  cd $PROJECT_ROOT_PATH/dimple-ui-blog
  npm install
  npm run build
  cp ./dist
  npm install
  cp_html
}

deploy() {
  down
  base
  deploy_jar
  deploy_html
}

deploy_jar() {
  echo "start deploy"
  cd $PROJECT_DOCKER_PATH
  docker compose --compatibility up -d --build dimple-gateway dimple-auth dimple-modules-system dimple-modules-job dimple-modules-file dimple-modules-monitor dimple-modules-blog dimple-modules-blog-front dimple-modules-log
}
deploy_html() {
  echo "start deploy"
  cd $PROJECT_DOCKER_PATH
  docker compose --compatibility up -d --build dimple-ui
}
# clean all docker env.
clean() {
  clean_html
  clean_jar
  clean_sql
}

clean_jar() {
  echo "begin clean jar "
  find $PROJECT_DOCKER_PATH -name *.jar | xargs rm -rf
}

clean_sql() {
  # copy sql
  echo "begin clean sql "
  find $PROJECT_DOCKER_PATH/mysql/db/*.sql | xargs rm -rf
}

clean_html() {
  echo "begin clean html "
  find $PROJECT_DOCKER_PATH/nginx/admin/dist/* | xargs rm -rf
  find $PROJECT_DOCKER_PATH/nginx/blog/dist/* | xargs rm -rf
}

cp_sql() {
  echo "begin cp sql "
  /bin/cp -rf $PROJECT_ROOT_PATH/sql/*.sql $PROJECT_DOCKER_PATH/mysql/db
}

cp_jar() {
  echo "begin copy dimple-gateway "
  /bin/cp -rf $PROJECT_ROOT_PATH/dimple-gateway/target/dimple-gateway.jar $PROJECT_DOCKER_PATH/dimple/gateway/jar

  echo "begin copy dimple-auth "
  /bin/cp -rf $PROJECT_ROOT_PATH/dimple-auth/target/dimple-auth.jar $PROJECT_DOCKER_PATH/dimple/auth/jar

  echo "begin copy dimple-modules-blog "
  /bin/cp -rf $PROJECT_ROOT_PATH/dimple-modules/dimple-blog/dimple-blog-web/target/dimple-modules-blog-web.jar $PROJECT_DOCKER_PATH/dimple/modules/blog/jar

  echo "begin copy dimple-modules-blog-front "
  /bin/cp -rf $PROJECT_ROOT_PATH/dimple-modules/dimple-blog-front/dimple-blog-front-web/target/dimple-modules-blog-front-web.jar $PROJECT_DOCKER_PATH/dimple/modules/blog-front/jar

  echo "begin copy dimple-modules-file "
  /bin/cp -rf $PROJECT_ROOT_PATH/dimple-modules/dimple-file/dimple-file-web/target/dimple-modules-file-web.jar $PROJECT_DOCKER_PATH/dimple/modules/file/jar

  echo "begin copy dimple-modules-job "
  /bin/cp -rf $PROJECT_ROOT_PATH/dimple-modules/dimple-job/dimple-job-web/target/dimple-modules-job-web.jar $PROJECT_DOCKER_PATH/dimple/modules/job/jar

  echo "begin copy dimple-modules-log "
  /bin/cp -rf $PROJECT_ROOT_PATH/dimple-modules/dimple-log/dimple-log-web/target/dimple-modules-log-web.jar $PROJECT_DOCKER_PATH/dimple/modules/log/jar

  echo "begin copy dimple-modules-monitor "
  /bin/cp -rf $PROJECT_ROOT_PATH/dimple-modules/dimple-monitor/dimple-monitor-web/target/dimple-modules-monitor-web.jar $PROJECT_DOCKER_PATH/dimple/modules/monitor/jar

  echo "begin copy dimple-modules-system "
  /bin/cp -rf $PROJECT_ROOT_PATH/dimple-modules/dimple-system/dimple-system-web/target/dimple-modules-system-web.jar $PROJECT_DOCKER_PATH/dimple/modules/system/jar
}

cp_html() {
  # copy html
  echo "begin copy html "
  mkdir -p $PROJECT_DOCKER_PATH/nginx/admin/dist
  /bin/cp -rf $PROJECT_ROOT_PATH/dimple-ui-admin/dist/** $PROJECT_DOCKER_PATH/nginx/admin/dist
  mkdir -p $PROJECT_DOCKER_PATH/node/blog/dist
  /bin/cp -rf $PROJECT_ROOT_PATH/dimple-ui-blog/dist/** $PROJECT_DOCKER_PATH/node/blog/dist
}

cp() {
  cp_sql
  cp_jar
  cp_html
}

bye() {
  echo "Goodbye!"
  exit 0
}

BOLD=$(tput bold)
RESET=$(tput sgr0)

# build : only build jar, dist html... but not call docker run.
# deploy: stop and remove all service, then build jar, dist html ... and finally call docker run.
# 设置选项数组
options=(
  "Init: only first deploy the service can using this method.",
  "Base: deploy MySQL,Naocs,Redis base services.",
  "Modules: deploy all customer modules services. contains HTML and Jar",
  "Stop: Stop all customer modules services",
  "Build: Build all customer modules service,contains jar and html",
  "Build Jar: Only build jar",
  "Build html: Only build html",
  "Deploy: Deploy all customer modules service,contains jar and html",
  "Deploy Jar: Only deploy jar",
  "Deploy html: Only deploy html",
  "Remove: Remove all customer service",
  "Exit"
)
commands=(
  "init"
  "base"
  "modules"
  "stop"
  "build"
  "build_jar"
  "build_html"
  "deploy"
  "deploy_jar"
  "deploy_html"
  "rm"
  "bye"
)

# 设置默认选项
selected=0

# 循环监听用户输入
while true; do
  # 清空屏幕
  tput clear
  printf "%sDimple Deploy System%s\n" "$BOLD" "$RESET"
  printf "\n%s\n" "Please select an option by ↑ and ↓."
  # 打印选项列表
  for i in "${!options[@]}"; do
    if [ $i -eq $selected ]; then
      printf "\n > %s\n" "${options[$i]}"
    else
      printf "\n   %s\n" "${options[$i]}"
    fi
  done

  # 监听用户输入
  read -rsn1 input

  # 根据用户输入更新选项
  if [[ $input == "A" ]] && [ $selected -gt 0 ]; then
    selected=$((selected - 1))
  elif [[ $input == "B" ]] && [ $selected -lt $((${#options[@]} - 1)) ]; then
    selected=$((selected + 1))
  elif [[ $input == "" ]]; then
    break
  fi
done

eval "${commands[$selected]}"
