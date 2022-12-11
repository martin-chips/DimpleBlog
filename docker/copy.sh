#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
cp ../sql/*.sql ./mysql/db

# copy html
echo "begin copy html "
cp -r ../dimple-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy dimple-gateway "
cp -f ../dimple-gateway/target/dimple-gateway.jar ./dimple/gateway/jar

echo "begin copy dimple-auth "
cp -f ../dimple-auth/target/dimple-auth.jar ./dimple/auth/jar

echo "begin copy dimple-visual "
cp  -f  ../dimple-visual/dimple-monitor/target/dimple-visual-monitor.jar  ./dimple/visual/monitor/jar

echo "begin copy dimple-modules-system "
cp -f ../dimple-modules/dimple-system/target/dimple-modules-system.jar ./dimple/modules/system/jar

echo "begin copy dimple-modules-file "
cp -f ../dimple-modules/dimple-file/target/dimple-modules-file.jar ./dimple/modules/file/jar

echo "begin copy dimple-modules-job "
cp -f ../dimple-modules/dimple-job/target/dimple-modules-job.jar ./dimple/modules/job/jar


echo "begin copy nacos application.properties"
mkdir -p /home/project/dimple/nacos/conf
cp -f nacos/conf/application.properties /home/project/dimple/nacos/conf

echo "begin copy redis redis.conf"
mkdir -p /home/project/dimple/redis/conf
cp -f redis/conf/redis.conf /home/project/dimple/redis/conf

echo "begin copy nginx nginx.conf"
mkdir -p /home/project/dimple/nginx/conf
cp -f nginx/conf/nginx.conf /home/project/dimple/nginx/conf
