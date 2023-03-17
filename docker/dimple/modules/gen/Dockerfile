# 基础镜像
FROM  openjdk:8-jre
# author
MAINTAINER dimple

ENV JAVA_OPTS=""

# 挂载目录
VOLUME /home/dimple
# 创建目录
RUN mkdir -p /home/dimple
# 指定路径
WORKDIR /home/dimple
# 复制jar文件到路径
COPY ./jar/dimple-modules-gen-web.jar /home/dimple/dimple-modules-gen.jar
# 启动文件服务
CMD ["sh", "-c", "java $JAVA_OPTS -jar dimple-modules-gen.jar"]