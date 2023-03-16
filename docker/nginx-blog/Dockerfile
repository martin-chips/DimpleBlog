# 基础镜像
FROM nginx
# author
MAINTAINER dimple

# 挂载目录
VOLUME /home/project/dimple-ui-blog
# 创建目录
RUN mkdir -p /home/project/dimple-ui-blog
# 指定路径
WORKDIR /home/project/dimple-ui-blog
# 复制conf文件到路径
COPY ./conf/nginx.conf /etc/nginx/nginx.conf
# 复制html文件到路径
COPY ./html/dist /home/project/dimple-ui-blog
