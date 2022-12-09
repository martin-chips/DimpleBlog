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
cp ../dimple-gateway/target/dimple-gateway.jar ./dimple/gateway/jar

echo "begin copy dimple-auth "
cp ../dimple-auth/target/dimple-auth.jar ./dimple/auth/jar

echo "begin copy dimple-visual "
cp ../dimple-visual/dimple-monitor/target/dimple-visual-monitor.jar  ./dimple/visual/monitor/jar

echo "begin copy dimple-modules-system "
cp ../dimple-modules/dimple-system/target/dimple-modules-system.jar ./dimple/modules/system/jar

echo "begin copy dimple-modules-file "
cp ../dimple-modules/dimple-file/target/dimple-modules-file.jar ./dimple/modules/file/jar

echo "begin copy dimple-modules-job "
cp ../dimple-modules/dimple-job/target/dimple-modules-job.jar ./dimple/modules/job/jar
