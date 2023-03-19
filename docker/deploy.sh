#!/bin/sh

# 使用说明，用来提示输入参数
usage() {
  echo "Usage: sh 执行脚本.sh [port|base|modules|stop|rm]"
  exit 1
}

# 开启所需端口
port() {
  firewall-cmd --add-port=80/tcp --permanent
  firewall-cmd --add-port=8080/tcp --permanent
  firewall-cmd --add-port=8848/tcp --permanent
  firewall-cmd --add-port=9848/tcp --permanent
  firewall-cmd --add-port=9849/tcp --permanent
  firewall-cmd --add-port=6379/tcp --permanent
  firewall-cmd --add-port=3306/tcp --permanent
  firewall-cmd --add-port=9100/tcp --permanent
  firewall-cmd --add-port=9200/tcp --permanent
  firewall-cmd --add-port=9201/tcp --permanent
  firewall-cmd --add-port=9202/tcp --permanent
  firewall-cmd --add-port=9203/tcp --permanent
  firewall-cmd --add-port=9300/tcp --permanent
  firewall-cmd --add-port=9401/tcp --permanent
  service firewalld restart
}

# The Base Environment (Required)
# nacos will start failed when deploy first time because mysql start slowly, may you should restart nacos when checked nacos start failed using
# 'docker compose logs dimple-nacos'.
# restart nacos by using 'docker compose restart dimple-nacos'
base() {
  docker compose --compatibility up -d dimple-mysql dimple-redis dimple-nacos
}

# The Program Modules (Required)
modules() {
  docker compose --compatibility up -d --build dimple-ui dimple-gateway dimple-auth dimple-modules-system dimple-modules-job dimple-modules-file dimple-modules-monitor dimple-modules-blog dimple-modules-blog-front dimple-modules-log
}

# Stop all modules which contains base environment and program modules.
stop() {
  docker compose stop
}

# Remove all modules which contains base environment and program modules.
rm() {
  docker compose rm
  docker images|grep none|awk '{print $3}'|xargs docker rmi
  docker images|grep dimple|awk '{print $3}'|xargs docker rmi
}

# Build the maven jar package and compile the UI, then copy all file to the dir.
init() {
  echo 'Add the execute permission to the env script'
  chmod +x env.sh
  ./env.sh clean
  build
  default_password="Di^&7so@c@drxMe4"
  echo "please input the password:(We strongly recommend that you change the default password: ${default_password} )"
  read input_password
  echo "start change default password to $input_password"
  sed -i "s/$default_password/$input_password/g" ./redis/conf/redis.conf
  sed -i "s/$default_password/$input_password/g" ./nacos/conf/application.properties
  sed -i "s/$default_password/$input_password/g" ./mysql/db/*
  sed -i "s/$default_password/$input_password/g" ./docker-compose.yml
}

build(){
  echo 'start build maven jar package'
  cd ..
  mvn clean package -Dmaven.test.skip=true
  echo 'start compile ui'
  cd dimple-ui-admin
  npm install
  npm run build:prod
  cd ..
  cd dimple-ui-blog
  npm install
  npm run build:prod
  echo 'start copy files'
  cd ../docker
  ./env.sh cp_jar
  ./env.sh cp_html
}

# 根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
"port")
  port
  ;;
"start")
  start
  ;;
"base")
  base
  ;;
"down")
  down
  ;;
"init")
  init
  ;;
"modules")
  modules
  ;;
"stop")
  stop
  ;;
"rm")
  rm
  ;;
*)
  usage
  ;;
esac
