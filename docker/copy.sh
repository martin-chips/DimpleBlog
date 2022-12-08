#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
cp ../sql/dimple_20210908.sql ./mysql/db
cp ../sql/dimple_config_20220114.sql ./mysql/db

# copy html
echo "begin copy html "
cp -r ../dimple-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy dimple-gateway "
cp ../dimple-gateway/target/dimple-gateway.jar ./ruoyi/gateway/jar

echo "begin copy dimple-auth "
cp ../dimple-auth/target/dimple-auth.jar ./ruoyi/auth/jar

echo "begin copy dimple-visual "
cp ../dimple-visual/dimple-monitor/target/dimple-visual-monitor.jar  ./ruoyi/visual/monitor/jar

echo "begin copy dimple-modules-system "
cp ../dimple-modules/dimple-system/target/dimple-modules-system.jar ./ruoyi/modules/system/jar

echo "begin copy dimple-modules-file "
cp ../dimple-modules/dimple-file/target/dimple-modules-file.jar ./ruoyi/modules/file/jar

echo "begin copy dimple-modules-job "
cp ../dimple-modules/dimple-job/target/dimple-modules-job.jar ./ruoyi/modules/job/jar

echo "begin copy dimple-modules-gen "
cp ../dimple-modules/dimple-gen/target/dimple-modules-gen.jar ./ruoyi/modules/gen/jar

