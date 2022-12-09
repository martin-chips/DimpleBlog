# 基础镜像
FROM nginx
# author
MAINTAINER dimple

# 挂载目录
VOLUME /home/docker/projects/dimple-ui
# 创建目录
RUN mkdir -p /home/docker/projects/dimple-ui
# 指定路径
WORKDIR /home/docker/projects/dimple-ui
# 复制conf文件到路径
COPY ./conf/nginx.conf /etc/nginx/nginx.conf
# 复制html文件到路径
COPY ./html/dist /home/docker/projects/dimple-ui
