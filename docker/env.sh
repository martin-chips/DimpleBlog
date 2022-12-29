#!/bin/sh

PROJECT_ROOT_PATH="/home/project/DimpleBlog"
PROJECT_DOCKER_PATH="$PROJECT_ROOT_PATH/docker"

# cp all dependence into docker env dir.
# using /bin/cp replace cp command, because the cp command has been alias as 'cp -i', can't using the option '-rf'
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}

# clean all docker env.
clean() {
  clean_html
  clean_jar
  clean_sql
}

clean_jar() {
    echo "begin clean jar "
    # delete all jar files under the current path
    find $PROJECT_DOCKER_PATH -name *.jar | xargs rm -rf
}

clean_sql() {
  # copy sql
  echo "begin clean sql "
  rm -rf $PROJECT_DOCKER_PATH/mysql/db/*
}

clean_html() {
  echo "begin clean html "
  rm -rf $PROJECT_DOCKER_PATH/nginx/html/dist/*
}

cp_sql() {
  echo "begin cp sql "
  /bin/cp -rf $PROJECT_ROOT_PATH/sql/*.sql $PROJECT_DOCKER_PATH/mysql/db
  mkdir -p $PROJECT_DOCKER_PATH/mysql/sql
  /bin/cp -rf $PROJECT_ROOT_PATH/sql/*.sql $PROJECT_DOCKER_PATH/mysql/sql
}

cp_jar() {
  echo "begin copy dimple-gateway "
  /bin/cp -rf  $PROJECT_ROOT_PATH/dimple-gateway/target/dimple-gateway.jar $PROJECT_DOCKER_PATH/dimple/gateway/jar

  echo "begin copy dimple-auth "
  /bin/cp -rf  $PROJECT_ROOT_PATH/dimple-auth/target/dimple-auth.jar $PROJECT_DOCKER_PATH/dimple/auth/jar

  echo "begin copy dimple-visual "
  /bin/cp -rf   $PROJECT_ROOT_PATH/dimple-visual/dimple-monitor/target/dimple-visual-monitor.jar  $PROJECT_DOCKER_PATH/dimple/visual/monitor/jar

  echo "begin copy dimple-modules-system "
  /bin/cp -rf  $PROJECT_ROOT_PATH/dimple-modules/dimple-system/dimple-system-web/target/dimple-modules-system.jar $PROJECT_DOCKER_PATH/dimple/modules/system/jar

  echo "begin copy dimple-modules-blog "
  /bin/cp -rf  $PROJECT_ROOT_PATH/dimple-modules/dimple-blog/dimple-blog-web/target/dimple-modules-blog.jar $PROJECT_DOCKER_PATH/dimple/modules/blog/jar

  echo "begin copy dimple-modules-file "
  /bin/cp -rf  $PROJECT_ROOT_PATH/dimple-modules/dimple-file/dimple-file-web/target/dimple-modules-file.jar $PROJECT_DOCKER_PATH/dimple/modules/file/jar

  echo "begin copy dimple-modules-job "
  /bin/cp -rf  $PROJECT_ROOT_PATH/dimple-modules/dimple-job/dimple-job-web/target/dimple-modules-job-web.jar $PROJECT_DOCKER_PATH/dimple/modules/job/jar
}

cp_html() {
  # copy html
  echo "begin copy html "
  mkdir -p $PROJECT_DOCKER_PATH/nginx/html/dist
  /bin/cp -rf $PROJECT_ROOT_PATH/dimple-ui/dist/** $PROJECT_DOCKER_PATH/nginx/html/dist
}


cp() {
  cp_sql
  cp_jar
  cp_html
}

case "$1" in
"cp_html")
  cp_html
  ;;
"cp_jar")
  cp_jar
  ;;
"cp_sql")
  cp_sql
  ;;
"clean_sql")
  clean_sql
  ;;
"clean_jar")
  clean_jar
  ;;
"clean_html")
  clean_html
  ;;
"rm")
  clean
  ;;
"cp")
  cp
  ;;
*)
  usage
  ;;
esac