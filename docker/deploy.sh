#!/bin/bash
# the project home path
PROJECT_ROOT_PATH="/home/project/DimpleBlog"
# the docker compose home path
PROJECT_DOCKER_PATH="$PROJECT_ROOT_PATH/docker"

# build and start the base environment
base() {
  echo "build and start dimple-mysql/dimple-redis/dimple-nacos..."
  docker compose --compatibility up -d dimple-mysql dimple-redis dimple-nacos
}

stop() {
  echo "start stop services..."
  docker compose stop
}

start() {
  echo "start services..."
  docker compose start
}

down() {
  echo "start stop all service..."
  stop
  echo "start rm all services, input y/yes continue:"
  read input_choose
  if [ "${input_choose,,}" == 'y' ] || [ "${input_choose,,}" == 'yes' ]; then
    rm_services
  fi
}

# shellcheck disable=SC2032
rm_services() {
  docker compose rm
  echo "start rm 'none' images..."
  docker images | grep none | awk '{print $3}' | xargs docker rmi
  echo "start rm 'dimple-modules/dimple-auth/dimple-gateway' images..."
  docker images | grep dimple-modules | awk '{print $3}' | xargs docker rmi
  docker images | grep dimple-auth | awk '{print $3}' | xargs docker rmi
  docker images | grep dimple-gateway | awk '{print $3}' | xargs docker rmi
  echo "start rm 'dimple-ui/dimple-node' images..."
  docker images | grep dimple-node | awk '{print $3}' | xargs docker rmi
  docker images | grep dimple-ui | awk '{print $3}' | xargs docker rmi
}


init_env() {
  echo 'This method only using the first deploy, <CTRL-C> exit and press any key will continue...'
  # shellcheck disable=SC2162
  read any_key
  clean_sql
  clean_UI
  clean_jar
  cp_sql
  change_default_password
  build
}

build(){
  build_jar
  build_UI
}

# change the default password in MySQL，Redis，Nacos
change_default_password() {
  default_password="Di^&7so@c@drxMe4"
  echo "please input the password(default is ${default_password}), this password will be used to Redis/MySQL"
  # shellcheck disable=SC2162
  read input_password
  default_nacos_username="default_nacos_username"
  echo "please input nacos username(default is $default_nacos_username), this username will be used to nacos discovery,config and sentinel"
  read input_nacos_username
  default_nacos_password="default_nacos_password"
  echo "please input nacos password(default is default_nacos_password), this password will be used to nacos discovery,config and sentinel"
  read input_nacos_password
  echo "=========================================================================================================================="
  echo "||You should create the user with username:$input_nacos_username password:$input_nacos_password when you start services.||"
  echo "=====================================================================================================================+++++"
  sleep 3s
  sed -i "s/$default_password/$input_password/g" $PROJECT_DOCKER_PATH/redis/conf/redis.conf
  sed -i "s/$default_password/$input_password/g" $PROJECT_DOCKER_PATH/nacos/conf/application.properties
  sed -i "s/$default_password/$input_password/g" $PROJECT_DOCKER_PATH/mysql/db/*
  sed -i "s/$default_password/$input_password/g" $PROJECT_DOCKER_PATH/docker-compose.yml

  sed -i "s/$default_nacos_username/$input_nacos_username/g" $PROJECT_DOCKER_PATH/docker-compose.yml
  sed -i "s/$default_nacos_password/$input_nacos_password/g" $PROJECT_DOCKER_PATH/docker-compose.yml
}
build_jar() {
  clean_jar
  echo "start build jar ..."
  cd $PROJECT_ROOT_PATH
  mvn clean package -Dmaven.test.skip=true
  cp_jar
}

build_UI() {
  clean_UI
  echo 'start compile dimple-admin ui'
  cd $PROJECT_ROOT_PATH/dimple-ui-admin
  npm install
  npm run build:prod
  echo 'start compile dimple-blog ui'
  cd $PROJECT_ROOT_PATH/dimple-ui-blog
  npm install
  npm run build
  cp_UI
}

clean_jar() {
  echo "begin clean jar "
  find $PROJECT_DOCKER_PATH -name *.jar | xargs rm -rf
}

clean_sql() {
  # copy sql
  echo "begin clean sql... "
  find $PROJECT_DOCKER_PATH/mysql/db/*.sql | xargs rm -rf
}

clean_UI() {
  echo "begin clean UI... "
  find $PROJECT_DOCKER_PATH/nginx/admin/dist/* | xargs rm -rf
  find $PROJECT_DOCKER_PATH/node/blog/dist/* | xargs rm -rf
}

build_deploy(){
  build_deploy_jar
  build_deploy_UI
}

build_deploy_jar(){
  build_jar
  deploy_jar
}

build_deploy_UI(){
  build_UI
  deploy_UI
}

deploy(){
  deploy_jar
  deploy_UI
}

deploy_jar() {
  echo "start jar deploy..."
  cd $PROJECT_DOCKER_PATH
  docker compose --compatibility up -d --build dimple-gateway dimple-auth dimple-modules-system dimple-modules-job dimple-modules-file dimple-modules-monitor dimple-modules-blog dimple-modules-blog-front dimple-modules-log
}

deploy_UI() {
  echo "start UI deploy ..."
  cd $PROJECT_DOCKER_PATH/node/blog/dist
  echo "start build blog..."
  npm install
  cd $PROJECT_DOCKER_PATH
  docker compose --compatibility up -d --build dimple-ui dimple-node
}

cp_sql() {
  echo "begin cp sql "
  /bin/cp -rf $PROJECT_ROOT_PATH/sql/*.sql $PROJECT_DOCKER_PATH/mysql/db
}

cp_UI() {
  # copy UI
  echo "begin copy UI "
  mkdir -p $PROJECT_DOCKER_PATH/nginx/admin/dist
  /bin/cp -rf $PROJECT_ROOT_PATH/dimple-ui-admin/dist/** $PROJECT_DOCKER_PATH/nginx/admin/dist
  mkdir -p $PROJECT_DOCKER_PATH/node/blog/dist
  /bin/cp -rf $PROJECT_ROOT_PATH/dimple-ui-blog/dist/** $PROJECT_DOCKER_PATH/node/blog/dist
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

bye() {
  echo "Goodbye!"
  exit 0
}


BOLD=$(tput bold)
RESET=$(tput sgr0)

# build : only build jar, dist UI... but not call docker run.
# deploy: stop and remove all service, then build jar, dist UI ... and finally call docker run.
# 设置选项数组
options=(
  "Base: deploy MySQL,Naocs,Redis base services.",
  "Start: Start all customer modules services",
  "Stop: Stop all customer modules services",
  "Init: only first deploy the service can using this method.",
  "Build_Deploy: Build and Deploy all Jar and UI.",
  "Build_Deploy_Jar: Build and Deploy all Jar.",
  "Build_Deploy_UI: Build and Deploy UI.",
  "Build: Build all customer modules service,contains jar and UI",
  "Build Jar: Only build jar",
  "Build UI: Only build UI",
  "Deploy: Deploy all customer modules service,contains jar and UI",
  "Deploy Jar: Only deploy jar",
  "Deploy UI: Only deploy UI",
  "Remove: Remove all customer service",
  "Exit"
)
commands=(
  "base"
  "start"
  "stop"
  "init_env"
  "build_deploy"
  "build_deploy_jar"
  "build_deploy_UI"
  "build"
  "build_jar"
  "build_UI"
  "deploy"
  "deploy_jar"
  "deploy_UI"
  "rm_services"
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