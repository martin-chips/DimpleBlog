# DimpleBlog
#### 介绍

`Dimple Blog`是基于SpringBoot2搭建的个人博客系统。

### 项目说明
1. 首先项目是开源的，近期，已经有很多朋友在反映在“闲鱼”或者其他平台上有售卖源码的情况，再次严正声明：源码开源，但禁止任何人以任何方式进行售卖。  
2. 安装软件前，请仔细阅读下面的**安装教程**。更希望加我好友朋友是为了探讨技术，互相成长或者适当红包打赏，**而不是以私聊为目的的打扰**。任何有关项目的问题，请提issue。  
3. 如果觉得项目还可以，希望能够star支持一下，谢谢。
4. **使用本系统的（包含基于本系统进行修改的项目），请在显眼位置说明基于DimpleBlog并给出GitHub链接。**

#### 打赏名单
上线以来，收到很多朋友的好友申请，感谢你们对系统做出了肯定和打赏，非常感谢你们。

|序号| 用户账号  |打赏方式|打赏金额|打赏时间|
|--|--|--|--|--|
|1  |꧁奔跑吧少年꧂ |微信 |30.00 |2019-05-18|
|2|青春浮华一场梦|微信|40.00| 2019-05-12|
|3|两个线程|微信|10.00|2019-04-25|
|4|JackRen|微信|10.00|2019-04-22|
|5|南桥先生|微信|10.00|2019-02-20|
|6|无名之辈|微信|9.99|2019-02-04||
|7|184******14|支付宝|200.00|2019-05-22|
|8|*粤浩|支付宝|20.00|2019-04-30|
|9|*恒|支付宝|25.00|2019-03-20|
|10|我语丶|微信|10.00|2019-08-13|

`排名不分时间先后，赞赏金额多少`

### 体验链接地址

[边小丰的个人博客](http://www.bianxiaofeng.com/)

管理员账号：`test` ,管理员密码： `123456`

### 图片展示
#### 前台
![mCHAij.png](https://s2.ax1x.com/2019/08/13/mCHAij.png)

![mCHFoQ.png](https://s2.ax1x.com/2019/08/13/mCHFoQ.png)

![mCHPeS.png](https://s2.ax1x.com/2019/08/13/mCHPeS.png)

![mCH9L8.png](https://s2.ax1x.com/2019/08/13/mCH9L8.png)

![mCHidg.png](https://s2.ax1x.com/2019/08/13/mCHidg.png)

![mCHEJs.png](https://s2.ax1x.com/2019/08/13/mCHEJs.png)

![mCHVWn.png](https://s2.ax1x.com/2019/08/13/mCHVWn.png)

#### 后台（仅列举部分）
##### 登录界面
![kdqQln.md.png](https://s2.ax1x.com/2019/04/10/ATcWkt.png)
##### 
![mC7B2q.png](https://s2.ax1x.com/2019/08/13/mC7B2q.png)

![mC7Dx0.png](https://s2.ax1x.com/2019/08/13/mC7Dx0.png)

![mC7wPs.png](https://s2.ax1x.com/2019/08/13/mC7wPs.png)

![mC70Gn.png](https://s2.ax1x.com/2019/08/13/mC70Gn.png)

![mC7a5j.png](https://s2.ax1x.com/2019/08/13/mC7a5j.png)

![mC7yrT.png](https://s2.ax1x.com/2019/08/13/mC7yrT.png)

![mC7sMV.png](https://s2.ax1x.com/2019/08/13/mC7sMV.png)

![mC76qU.png](https://s2.ax1x.com/2019/08/13/mC76qU.png)

![mC7gZF.png](https://s2.ax1x.com/2019/08/13/mC7gZF.png)

![mC72a4.png](https://s2.ax1x.com/2019/08/13/mC72a4.png)

![mC7RIJ.png](https://s2.ax1x.com/2019/08/13/mC7RIJ.png)

![mC7hGR.png](https://s2.ax1x.com/2019/08/13/mC7hGR.png)

![mC7fi9.png](https://s2.ax1x.com/2019/08/13/mC7fi9.png)

![mC74R1.png](https://s2.ax1x.com/2019/08/13/mC74R1.png)

![mC75xx.png](https://s2.ax1x.com/2019/08/13/mC75xx.png)

![mC7oM6.png](https://s2.ax1x.com/2019/08/13/mC7oM6.png)

![mC7TsK.png](https://s2.ax1x.com/2019/08/13/mC7TsK.png)

![mC7bZD.png](https://s2.ax1x.com/2019/08/13/mC7bZD.png)

![mC77qO.png](https://s2.ax1x.com/2019/08/13/mC77qO.png)

![mC7qde.png](https://s2.ax1x.com/2019/08/13/mC7qde.png)

![mC7LIH.png](https://s2.ax1x.com/2019/08/13/mC7LIH.png)

#### 软件架构



#### 安装教程

1. 修改application-druid.yml中的数据库连接信息：
```
    type: com.alibaba.druid.pool.DruidDataSource
    username: 用户名
    password: 你的数据库面貌
    url:你的数据库连接地址
    driver-class-name: com.mysql.jdbc.Driver
```
2. 创建数据库`test`，运行SQL文件（在`resources`下的`sql`文件夹下），附赠上万条数据。

3. 配置**七牛云**图库accessKey、secretKey、bucket、path、imageMaxSize、imageMaxNameLength等信息：
```
  qiniuyun:
    accessKey: tq8X9efPviPasgmoz6kcIzDuE_v4T-UJQXchcWgz
    secretKey: Yejcd3g3Z_iOmu_jW6hB-euwxqbyqvJkslZzoXrV
    bucket: uploadtest
    path: http://pkxwyzsng.bkt.clouddn.com
    imageMaxSize: 52428800
    imageMaxNameLength: 200
```
4. 配置**百度站长**平台push的URL

```
  #百度站长Push 的URL
  baidu:
    pushUrl: http://data.zz.baidu.com/urls?site=www.bianxiaofeng.com&token=LqRHUkKxK89qudvA
```

5. 配置**百度AI**内容审核(评论系统需要用到)
```
  baidu:
    clientId: 
    clientSecret: 
```

6. 配置Redis

7. 使用Maven打包项目。

8. 使用命令`java -jar +jar包名`启动项目。

#### 使用说明

1. 项目目前还在开发中，以后也会一直维护下去。
2. 项目的开发过程中，参考了很多大佬经验，由于年代久远，未能记住各位大佬的链接地址。如大佬有看到该项目，请与我联系：`bianxiaofeng@sohu.com`，以便及时表示感谢。


#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

系统使用的技术不完全列举如下：

##### Server端使用技术

|序号  | 技术名称  |说明|链接地址 |
|--|--|--|--|
|1| SpringBoot2| 项目主体框架|[SpringBoot](http://spring.io/projects/spring-boot/) |
|2|Mybatis|持久层框架|[Mybatis](http://www.mybatis.org/mybatis-3/)
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
#### 前端页面技术
|序号  | 技术名称  |说明|链接地址 |
|--|--|--|--|
|1| animate.css| 特效样式|[animate](https://daneden.github.io/animate.css/) |
|2|blueimpGallery|图片浏览框架|[blueimpGallery](http://blueimp.github.io/Gallery/)|
|3|bootstrap大家族|前端样式|包括：[bootstrap-fileinput-4.5.2](http://blueimp.github.io/Gallery/);  [Bootstrap Table](https://bootstrap-table.com/)；[bootstrap-tagsinput](http://bootstrap-tagsinput.github.io/bootstrap-tagsinput/examples/)；bootstrap-treetable；|
| 4| echarts|图表| [echarts](https://echarts.baidu.com/)
| 5|font-awesome|字体库|[font-awesome](fontawesome.dashgame.com/)
| 6| iCheck |美观的选择input|[iCheck ](https://xdsoft.net/jquery-plugins/icheck/)
| 7| image-picker|图片选择器|[image-picker](https://ionicframework.com/docs/native/image-picker/)
| 8|jquery |没说的| 
| 9| layer|优秀的前端弹出层|[Layer](http://layer.layui.com/)
| 10| layui|经典模块化前端框架|[layui](https://www.layui.com/)
|11|summernote|编辑器[summernote](https://summernote.org/)
|12|sweetalert|好看的提示框架|[sweetalert](https://sweetalert.bootcss.com/)
|13|webUploader|文件上传框架|[webUploader](http://fex.baidu.com/webuploader/)
|14|blockUI|遮罩层|[druid](http://malsup.com/jquery/block/)
|15|fullscreen|全屏|[fullscreen](https://fullscreenmedia.co/)

