# DimpleBlog

#### 介绍

`Dimple Blog`是基于SpringBoot2搭建的个人博客系统。系统使用的技术不完全列举如下：

|序号  | 技术名称  |说明|链接地址 |
|--|--|--|--|
|1| SpringBoot2| 项目主体框架|[SpringBoot](http://spring.io/projects/spring-boot/) |
|2|Spring Data JPA|持久层框架|[JPA](http://spring.io/projects/spring-data-jpa)
|3|SpringBoot Cache|Spring缓存层框架||
| 4| Redis|缓存| [Redis](https://redis.io/)
| 5|Mysql  |数据库|[Mysql](https://www.mysql.com/)
| 6| Apache Shiro |权限控制安全框架|[Shiro](http://shiro.apache.org/)
| 7| Lombok|注解神器|[lomkok](https://www.projectlombok.org/)
| 8|七牛云 |图床| [七牛云](https://www.qiniu.com/)
| 9| Swagger|API接口工具|[Swagger](https://swagger.io/)
| 10| fastjson|JSON转换工具|[fastjson](https://archive.codeplex.com/?p=fastjson)
|11|UserAgentUtils|系统信息获取工具|[bitwalker](https://www.bitwalker.eu/software/user-agent-utils)
|12|kaptcha|前谷歌code插件|[penggle](http://www.oddjack.com/?certs=penggle/kaptcha)
|13|oshi-core|系统监控插件|[oshii](https://github.com/oshi/oshi)
|14|druid|数据库连接池|[druid](http://druid.io/)
|15|pegdown|markdown转为HTML工具|[pegdown](https://github.com/sirthias/pegdown/)
### 体验链接地址

目前 www.bianxiaofeng.com 域名备案尚未完成，目前只能通过IP地址访问。  

[边小丰的个人博客](http://47.112.14.207/)

管理员账号：`admin` ,管理员密码： `123456`
### 图片展示

    note： 请手动忽略图片中的有些图片链接不显示（因为我造的假数据，有些图片失效是正常的）

![后台界面](https://s2.ax1x.com/2019/01/25/km46jH.png)

![前台界面](https://s2.ax1x.com/2019/01/25/km5lqA.md.png)[![knkl9K.png](https://s2.ax1x.com/2019/01/25/knkl9K.png)](https://imgchr.com/i/knkl9K)

[![knkMh6.png](https://s2.ax1x.com/2019/01/25/knkMh6.png)](https://imgchr.com/i/knkMh6)

[![knk3cD.png](https://s2.ax1x.com/2019/01/25/knk3cD.png)](https://imgchr.com/i/knk3cD)

[![knk11O.png](https://s2.ax1x.com/2019/01/25/knk11O.png)](https://imgchr.com/i/knk11O)

[![knkKtx.png](https://s2.ax1x.com/2019/01/25/knkKtx.png)](https://imgchr.com/i/knkKtx)


#### 软件架构



#### 安装教程

1. 修改application.yml中的数据库连接信息：
```
    type: com.alibaba.druid.pool.DruidDataSource
    username: 你的数据库连接地址
    password: 你的数据库面貌
    url:你的数据库URL
    driver-class-name: com.mysql.jdbc.Driver
```
2. 创建数据库`dimple_blog`，运行SQL文件（在`resources`下的`sql`文件夹下）。由于使用JPA会自动创建表，你也可以不运行SQL文件，**当时需要数据库**。
3. 配置七牛云图库accessKey、secretKey、bucket、path、imageMaxSize、imageMaxNameLength等信息：
```
  qiniuyun:
    accessKey: tq8X9efPviPasgmoz6kcIzDuE_v4T-UJQXchcWgz
    secretKey: Yejcd3g3Z_iOmu_jW6hB-euwxqbyqvJkslZzoXrV
    bucket: uploadtest
    path: http://pkxwyzsng.bkt.clouddn.com
    imageMaxSize: 52428800
    imageMaxNameLength: 200
```
4. 配置百度站长平台push的URL

```
  #百度站长Push 的URL
  baidu:
    pushUrl: http://data.zz.baidu.com/urls?site=www.bianxiaofeng.com&token=LqRHUkKxK89qudvA
```
5. 使用Maven打包项目。
6. 使用命令`java -jar +jar包名`启动项目。

#### 使用说明

1. 项目目前还在开发中，以后也会一直维护下去。
2. 项目的开发过程中，参考了很多大佬经验，由于年代久远，未能记住各位大佬的链接地址。如大佬有看到该项目，请与我联系：`bianxiaofeng@sohu.com`，以便及时表示感谢。


#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

