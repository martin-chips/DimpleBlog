# 基础镜像
FROM  openjdk:8-jre
# author
MAINTAINER dimple

# 挂载目录
VOLUME /home/dimple
# 创建目录
RUN mkdir -p /home/dimple
# 指定路径
WORKDIR /home/dimple
# 复制jar文件到路径
COPY ./jar/dimple-modules-file-web.jar /home/dimple/dimple-modules-file.jar
# 启动文件服务
ENTRYPOINT ["java","-jar","dimple-modules-file.jar"]