# 基础镜像
FROM redis
# author
MAINTAINER dimple

# 挂载目录
VOLUME /home/dimple/redis
# 创建目录
RUN mkdir -p /home/docker/redis/config /home/docker/redis/data
# 指定路径
WORKDIR /home/docker/redis
# 复制conf文件到路径
COPY ./conf/redis.conf /home/docker/redis/config/redis.conf
