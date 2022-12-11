# 基础镜像
FROM redis
# author
MAINTAINER dimple

# 挂载目录
VOLUME /home/dimple/redis
# 创建目录
RUN mkdir -p /home/project/redis/conf /home/project/redis/data
# 指定路径
WORKDIR /home/project/redis
# 复制conf文件到路径
COPY ./conf/redis.conf /home/redis/conf/redis.conf
