# 使用官方 Node.js 14 镜像作为基础镜像
FROM node:14

# 设置工作目录
WORKDIR /home/dimple/projects/dimple-ui-blog/

RUN npm install pm2 -g
# 暴露容器端口
EXPOSE 8820

# 启动应用程序
CMD ["npm", "run","pm2:prod"]
