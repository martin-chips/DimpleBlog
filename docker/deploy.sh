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
  service firewalld restart
}

# 启动基础环境（必须）
base() {
  docker compose up -d dimple-mysql dimple-redis dimple-nacos
}

# 启动程序模块（必须）
modules() {
  docker compose up -d --build dimple-nginx dimple-gateway dimple-auth dimple-modules-system dimple-modules-job dimple-modules-file dimple-visual-monitor
}

# 关闭所有环境/模块
stop() {
  docker compose stop
}

# 删除所有环境/模块
rm() {
  docker compose rm
}

init() {
  cd ..
  mvn clean package -Dmaven.test.skip=true
  cd -
  cd ../dimple-ui
  npm install
  npm run build:prod
  cd -
  chmod +x copy.sh
  ./copy.sh cp
}

# 根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
"port")
  port
  ;;
"base")
  base
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
