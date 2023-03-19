DROP DATABASE IF EXISTS `dimple-config`;

CREATE DATABASE  `dimple-config` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

USE `dimple-config`;

/******************************************/
/*   表名称 = config_info   */
/******************************************/
CREATE TABLE `config_info`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `data_id`            varchar(255) NOT NULL COMMENT 'data_id',
    `group_id`           varchar(255)      DEFAULT NULL,
    `content`            longtext     NOT NULL COMMENT 'content',
    `md5`                varchar(32)       DEFAULT NULL COMMENT 'md5',
    `gmt_create`         datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`       datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `src_user`           text COMMENT 'source user',
    `src_ip`             varchar(50)       DEFAULT NULL COMMENT 'source ip',
    `app_name`           varchar(128)      DEFAULT NULL,
    `tenant_id`          varchar(128)      DEFAULT '' COMMENT '租户字段',
    `c_desc`             varchar(256)      DEFAULT NULL,
    `c_use`              varchar(64)       DEFAULT NULL,
    `effect`             varchar(64)       DEFAULT NULL,
    `type`               varchar(64)       DEFAULT NULL,
    `c_schema`           text,
    `encrypted_data_key` text COMMENT '秘钥',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';
insert into config_info (id, data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip, app_name, tenant_id, c_desc, c_use, effect, type, c_schema, encrypted_data_key)
values  (1, 'application-dev.yml', 'DEFAULT_GROUP', 'spring:
  autoconfigure:
    exclude: com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure
  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher

# feign 配置
feign:
  sentinel:
    enabled: true
  okhttp:
    enabled: true
  httpclient:
    enabled: false
  client:
    config:
      default:
        connectTimeout: 10000
        readTimeout: 10000
  compression:
    request:
      enabled: true
    response:
      enabled: true

# 暴露监控端点
management:
  endpoints:
    web:
      exposure:
        include: '' *''
', 'aaa73b809cfd4d0058893aa13da57806', '2020-05-20 12:00:00', '2022-04-24 10:26:34', 'nacos', '0:0:0:0:0:0:0:1', '', '', '通用配置', 'null', 'null', 'yaml', null, ''),
    (2, 'dimple-gateway-dev.yml', 'DEFAULT_GROUP', 'spring:
  redis:
    host: dimple-redis
    port: 6379
    password: Di^&7so@c@drxMe4
  cloud:
    gateway:
      discovery:
        locator:
          lowerCaseServiceId: true
          enabled: true
      routes:
        # 认证中心
        - id: dimple-auth
          uri: lb://dimple-auth
          predicates:
            - Path=/auth/**
          filters:
            # 验证码处理
            - CacheRequestFilter
            - ValidateCodeFilter
            - StripPrefix=1
        # 定时任务
        - id: dimple-job
          uri: lb://dimple-job
          predicates:
            - Path=/schedule/**
          filters:
            - StripPrefix=1
          # 日志模块
        - id: dimple-log
          uri: lb://dimple-log
          predicates:
            - Path=/log/**
          filters:
            - StripPrefix=1
          # 文件服务
        - id: dimple-file
          uri: lb://dimple-file
          predicates:
            - Path=/file/**
          filters:
            - StripPrefix=1
          # 监控服务
        - id: dimple-monitor
          uri: lb://dimple-monitor
          predicates:
            - Path=/monitor/**
          filters:
            - StripPrefix=1
        # 系统模块
        - id: dimple-system
          uri: lb://dimple-system
          predicates:
            - Path=/system/**
          filters:
            - StripPrefix=1
        # BLOG模块
        - id: dimple-blog
          uri: lb://dimple-blog
          predicates:
            - Path=/blog/**
          filters:
            - StripPrefix=1
        # BLOG-FRONT模块
        - id: dimple-blog-front
          uri: lb://dimple-blog-front
          predicates:
            - Path=/blog-front/**
          filters:
            - StripPrefix=1
        # 代码生成服务
        - id: dimple-gen
          uri: lb://dimple-gen
          predicates:
            - Path=/code/**
          filters:
            - StripPrefix=1

# 安全配置
security:
  # 验证码
  captcha:
    enabled: true
    type: math
  # 防止XSS攻击
  xss:
    enabled: true
    excludeUrls:
      - /system/notice
  # 不校验白名单
  ignore:
    whites:
      - /auth/logout
      - /auth/login
      - /auth/register
      - /*/v2/api-docs
      - /csrf
      - /blog-front/**
', '58f0b035894aafb9f4af51a1670c9bfb', '2020-05-14 14:17:55', '2023-03-06 05:57:26', 'nacos', '0:0:0:0:0:0:0:1', '', '', '网关模块', 'null', 'null', 'yaml', '', ''),
    (3, 'dimple-auth-dev.yml', 'DEFAULT_GROUP', 'spring:
  redis:
    host: dimple-redis
    port: 6379
    password: Di^&7so@c@drxMe4
', '8bd9dada9a94822feeab40de55efced6', '2020-11-20 00:00:00', '2022-09-29 02:48:42', 'nacos', '0:0:0:0:0:0:0:1', '', '', '认证中心', 'null', 'null', 'yaml', '', ''),
    (4, 'dimple-monitor-dev.yml', 'DEFAULT_GROUP', '# spring配置
spring:
  security:
    user:
      name: dimple
      password: 123456
  boot:
    admin:
      ui:
        title: Dimple服务状态监控
  redis:
    host: dimple-redis
    port: 6379
    password: Di^&7so@c@drxMe4
  datasource:
    druid:
      stat-view-servlet:
        enabled: true
        loginUsername: admin
        loginPassword: 123456
    dynamic:
      druid:
        initial-size: 5
        min-idle: 5
        maxActive: 20
        maxWait: 60000
        timeBetweenEvictionRunsMillis: 60000
        minEvictableIdleTimeMillis: 300000
        validationQuery: SELECT 1 FROM DUAL
        testWhileIdle: true
        testOnBorrow: false
        testOnReturn: false
        poolPreparedStatements: true
        maxPoolPreparedStatementPerConnectionSize: 20
        filters: stat,slf4j
        connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000
      datasource:
          # 主库数据源
          master:
            driver-class-name: com.mysql.cj.jdbc.Driver
            url: jdbc:mysql://dimple-mysql:3306/dimple-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
            username: root
            password: Di^&7so@c@drxMe4
          # 从库数据源
          # slave:
            # username:
            # password:
            # url:
            # driver-class-name:

# mybatis配置
mybatis:
    # 搜索指定包别名
    typeAliasesPackage: com.dimple.monitor.service.entity
    # 配置mapper的扫描，找到所有的mapper.xml映射文件
    mapperLocations: classpath:mapper/**/*.xml
# swagger配置
swagger:
  title: 监控模块接口文档
  license: Powered By Dimple
  licenseUrl: https://www.bianxf.com', 'd86ff4c035ece008e552bf216bb1e48d', '2020-11-20 00:00:00', '2023-03-06 06:20:55', 'nacos', '0:0:0:0:0:0:0:1', '', '', '监控中心', 'null', 'null', 'yaml', '', ''),
    (5, 'dimple-system-dev.yml', 'DEFAULT_GROUP', '# spring配置
spring:
  redis:
    host: dimple-redis
    port: 6379
    password: Di^&7so@c@drxMe4
  datasource:
    dynamic:
      druid:
        initial-size: 5
        min-idle: 5
        maxActive: 20
        maxWait: 60000
        timeBetweenEvictionRunsMillis: 60000
        minEvictableIdleTimeMillis: 300000
        validationQuery: SELECT 1 FROM DUAL
        testWhileIdle: true
        testOnBorrow: false
        testOnReturn: false
        poolPreparedStatements: true
        maxPoolPreparedStatementPerConnectionSize: 20
        filters: stat,slf4j
        connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000
      datasource:
          # 主库数据源
          master:
            driver-class-name: com.mysql.cj.jdbc.Driver
            url: jdbc:mysql://dimple-mysql:3306/dimple-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
            username: root
            password: Di^&7so@c@drxMe4
          # 从库数据源
          # slave:
            # username:
            # password:
            # url:
            # driver-class-name:

# mybatis配置
mybatis:
    # 搜索指定包别名
    typeAliasesPackage: com.dimple.system.service.entity
    # 配置mapper的扫描，找到所有的mapper.xml映射文件
    mapperLocations: classpath:mapper/**/*.xml

# swagger配置
swagger:
  title: 系统模块接口文档
  license: Powered By Dimple
  licenseUrl: https://www.bianxf.com', '77dcac20dc4f47d5cbbdc3f33e5710d1', '2020-11-20 00:00:00', '2023-03-06 06:20:29', 'nacos', '0:0:0:0:0:0:0:1', '', '', '系统模块', 'null', 'null', 'yaml', '', ''),
    (6, 'dimple-job-dev.yml', 'DEFAULT_GROUP', '# spring配置
spring:
  redis:
    host: dimple-redis
    port: 6379
    password: Di^&7so@c@drxMe4
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://dimple-mysql:3306/dimple-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
    username: root
    password: Di^&7so@c@drxMe4

# mybatis配置
mybatis:
    # 搜索指定包别名
    typeAliasesPackage: com.dimple.job.service.entity
    # 配置mapper的扫描，找到所有的mapper.xml映射文件
    mapperLocations: classpath:mapper/**/*.xml

# swagger配置
swagger:
  title: 定时任务接口文档
  license: Powered By Dimple
  licenseUrl: https://www.bianxf.com
', 'ac4f96a97eb52fb67cd6e025b84958b3', '2020-11-20 00:00:00', '2023-03-06 06:20:42', 'nacos', '0:0:0:0:0:0:0:1', '', '', '定时任务', 'null', 'null', 'yaml', '', ''),
    (7, 'dimple-file-dev.yml', 'DEFAULT_GROUP', '# spring配置
spring:
  servlet:
    multipart:
      max-file-size: 10MB
      max-request-size: 10MB
  redis:
    host: dimple-redis
    port: 6379
    password: Di^&7so@c@drxMe4
  datasource:
    dynamic:
      druid:
        initial-size: 5
        min-idle: 5
        maxActive: 20
        maxWait: 60000
        timeBetweenEvictionRunsMillis: 60000
        minEvictableIdleTimeMillis: 300000
        validationQuery: SELECT 1 FROM DUAL
        testWhileIdle: true
        testOnBorrow: false
        testOnReturn: false
        poolPreparedStatements: true
        maxPoolPreparedStatementPerConnectionSize: 20
        filters: stat,slf4j
        connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000
      datasource:
          # 主库数据源
          master:
            driver-class-name: com.mysql.cj.jdbc.Driver
            url: jdbc:mysql://dimple-mysql:3306/dimple-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
            username: root
            password: Di^&7so@c@drxMe4
          # 从库数据源
          # slave:
            # username:
            # password:
            # url:
            # driver-class-name:

# mybatis配置
mybatis:
    # 搜索指定包别名
  typeAliasesPackage: com.dimple.file.service.entity
    # 配置mapper的扫描，找到所有的mapper.xml映射文件
  mapperLocations: classpath:mapper/**/*.xml
# swagger配置
swagger:
  title: 文件接口文档
  license: Powered By Dimple
  licenseUrl: https://www.bianxf.com
# 本地文件上传
file:
  local:
    config:
      domain: http://127.0.0.1:9300
      path: D:/dimple/uploadPath
      prefix: /statics
  qiniu:
    config:
      accessKey: qWgbAeR1zOBqjxsnw0xANUBehCEcpwoADnp9oFJ9
      secretKey: EvDiz--aLFdNQ4XOq237itSLqWttApEt9S3RWFNP
      bucket: dimple-file
      domain: http://rrfyveqgo.hn-bkt.clouddn.com
      region: huanan', '8e4de7b3b6e28eb7885ed1bcd1d096bc', '2020-11-20 00:00:00', '2023-03-13 12:36:31', 'nacos', '0:0:0:0:0:0:0:1', '', '', '文件服务', 'null', 'null', 'yaml', '', ''),
    (8, 'sentinel-dimple-gateway', 'DEFAULT_GROUP', '[
    {
        "resource": "dimple-auth",
        "count": 500,
        "grade": 1,
        "limitApp": "default",
        "strategy": 0,
        "controlBehavior": 0
    },
	{
        "resource": "dimple-system",
        "count": 1000,
        "grade": 1,
        "limitApp": "default",
        "strategy": 0,
        "controlBehavior": 0
    },
    {
        "resource": "dimple-log",
        "count": 1000,
        "grade": 1,
        "limitApp": "default",
        "strategy": 0,
        "controlBehavior": 0
    },
    {
        "resource": "dimple-monitor",
        "count": 1000,
        "grade": 1,
        "limitApp": "default",
        "strategy": 0,
        "controlBehavior": 0
    },
	{
        "resource": "dimple-job",
        "count": 300,
        "grade": 1,
        "limitApp": "default",
        "strategy": 0,
        "controlBehavior": 0
    },
    {
        "resource": "dimple-blog",
        "count": 300,
        "grade": 1,
        "limitApp": "default",
        "strategy": 0,
        "controlBehavior": 0
    },
    {
        "resource": "dimple-blog-front",
        "count": 300,
        "grade": 1,
        "limitApp": "default",
        "strategy": 0,
        "controlBehavior": 0
    },
    {
        "resource": "dimple-gen",
        "count": 300,
        "grade": 1,
        "limitApp": "default",
        "strategy": 0,
        "controlBehavior": 0
    }
]', 'bd887503ee7702f7192fa67da1d72942', '2020-11-20 00:00:00', '2023-03-06 05:57:45', 'nacos', '0:0:0:0:0:0:0:1', '', '', '限流策略', 'null', 'null', 'json', '', ''),
    (9, 'dimple-blog-dev.yml', 'DEFAULT_GROUP', '# spring配置
spring:
  redis:
    host: dimple-redis
    port: 6379
    password: Di^&7so@c@drxMe4
  datasource:
    dynamic:
      druid:
        initial-size: 5
        min-idle: 5
        maxActive: 20
        maxWait: 60000
        timeBetweenEvictionRunsMillis: 60000
        minEvictableIdleTimeMillis: 300000
        validationQuery: SELECT 1 FROM DUAL
        testWhileIdle: true
        testOnBorrow: false
        testOnReturn: false
        poolPreparedStatements: true
        maxPoolPreparedStatementPerConnectionSize: 20
        filters: stat,slf4j
        connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000
      datasource:
          # 主库数据源
          master:
            driver-class-name: com.mysql.cj.jdbc.Driver
            url: jdbc:mysql://dimple-mysql:3306/dimple-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
            username: root
            password: Di^&7so@c@drxMe4
          # 从库数据源
          # slave:
            # username:
            # password:
            # url:
            # driver-class-name:

# mybatis配置
mybatis:
    # 搜索指定包别名
    typeAliasesPackage: com.dimple.blog.service.entity
    # 配置mapper的扫描，找到所有的mapper.xml映射文件
    mapperLocations: classpath:mapper/**/*.xml

# swagger配置
swagger:
  title: 系统模块接口文档
  license: Powered By Dimple
  licenseUrl: https://www.bianxf.com', '9f0788d8c80350df38abdfb67d3c8f41', '2022-12-29 07:46:52', '2023-03-06 06:21:11', 'nacos', '0:0:0:0:0:0:0:1', '', '', 'BLOG模块', '', '', 'yaml', '', null),
    (10, 'dimple-gen-dev.yml', 'DEFAULT_GROUP', '# spring配置
spring:
  redis:
    host: dimple-redis
    port: 6379
    password: Di^&7so@c@drxMe4
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://dimple-mysql:3306/dimple-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
    username: root
    password: Di^&7so@c@drxMe4

# mybatis配置
mybatis:
    # 搜索指定包别名
    typeAliasesPackage: com.dimple.gen.service.entity
    # 配置mapper的扫描，找到所有的mapper.xml映射文件
    mapperLocations: classpath:mapper/**/*.xml

# swagger配置
swagger:
  title: 代码生成接口文档
  license: Powered By Dimple
  licenseUrl: https://bianxf.com

# 代码生成
gen:
  # 作者
  author: Dimple
  # 默认生成包路径 system 需改成自己的模块名称 如 system monitor tool
  packageName: com.dimple.blog
  # 自动去除表前缀，默认是false
  autoRemovePre: false
  # 表前缀（生成类名不会包含表前缀，多个用逗号分隔）
  tablePrefix: blog_
', '6314245f573c8a490502bc62c43f59d4', '2020-11-20 00:00:00', '2023-03-06 06:21:23', 'nacos', '0:0:0:0:0:0:0:1', '', '', '代码生成', 'null', 'null', 'yaml', '', ''),
    (19, 'dimple-blog-front-dev.yml', 'DEFAULT_GROUP', '# spring配置
spring:
  redis:
    host: dimple-redis
    port: 6379
    password: Di^&7so@c@drxMe4
  datasource:
    dynamic:
      druid:
        initial-size: 5
        min-idle: 5
        maxActive: 20
        maxWait: 60000
        timeBetweenEvictionRunsMillis: 60000
        minEvictableIdleTimeMillis: 300000
        validationQuery: SELECT 1 FROM DUAL
        testWhileIdle: true
        testOnBorrow: false
        testOnReturn: false
        poolPreparedStatements: true
        maxPoolPreparedStatementPerConnectionSize: 20
        filters: stat,slf4j
        connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000
      datasource:
          # 主库数据源
          master:
            driver-class-name: com.mysql.cj.jdbc.Driver
            url: jdbc:mysql://dimple-mysql:3306/dimple-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
            username: root
            password: Di^&7so@c@drxMe4
          # 从库数据源
          # slave:
            # username:
            # password:
            # url:
            # driver-class-name:

# mybatis配置
mybatis:
    # 搜索指定包别名
    typeAliasesPackage: com.dimple.blog.front.service.entity
    # 配置mapper的扫描，找到所有的mapper.xml映射文件
    mapperLocations: classpath:mapper/**/*.xml
    configuration:
      log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
github:
  oauth:
    adminId: 33685170
    clientId: fca4ba1e780fd9c444b2
    clientSecrets: 1e0513b8fb40e878d57f237f14dd7d95c30cc547
    accessTokenUrl: https://github.com/login/oauth/access_token
    userInfoUrl: https://api.github.com/user
# swagger配置
swagger:
  title: 系统模块接口文档
  license: Powered By Dimple
  licenseUrl: https://www.bianxf.com', '02e33a85c13807bc36a5d2dbd7c5750d', '2023-03-02 07:21:17', '2023-03-15 03:08:15', 'nacos', '0:0:0:0:0:0:0:1', '', '', 'BLOG-FRONT模块', '', '', 'yaml', '', null),
    (25, 'dimple-log-dev.yml', 'DEFAULT_GROUP', '# spring配置
spring:
  redis:
    host: dimple-redis
    port: 6379
    password: Di^&7so@c@drxMe4
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://dimple-mysql:3306/dimple-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
    username: root
    password: Di^&7so@c@drxMe4

# mybatis配置
mybatis:
    # 搜索指定包别名
    typeAliasesPackage: com.dimple.log.service.entity
    # 配置mapper的扫描，找到所有的mapper.xml映射文件
    mapperLocations: classpath:mapper/**/*.xml

# swagger配置
swagger:
  title: 日志接口文档
  license: Powered By Dimple
  licenseUrl: https://www.bianxf.com
', '324df1f93cafac6395257eff87fff9a2', '2023-03-06 05:56:02', '2023-03-06 06:19:09', 'nacos', '0:0:0:0:0:0:0:1', '', '', '日志模块', '', '', 'yaml', '', null);

/******************************************/
/*   表名称 = config_info_aggr   */
/******************************************/
CREATE TABLE `config_info_aggr`
(
    `id`         bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `data_id`    varchar(255) NOT NULL COMMENT 'data_id',
    `group_id`   varchar(255) NOT NULL COMMENT 'group_id',
    `datum_id`   varchar(255) NOT NULL COMMENT 'datum_id',
  `content` longtext NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) DEFAULT NULL,
  `tenant_id` varchar(128) DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='增加租户字段';


/******************************************/
/*   表名称 = config_info_beta   */
/******************************************/
CREATE TABLE `config_info_beta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) DEFAULT NULL COMMENT 'app_name',
  `content` longtext NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COMMENT 'source user',
  `src_ip` varchar(50) DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` text COMMENT '秘钥',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_beta';

/******************************************/
/*   表名称 = config_info_tag   */
/******************************************/
CREATE TABLE `config_info_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) DEFAULT NULL COMMENT 'app_name',
  `content` longtext NOT NULL COMMENT 'content',
  `md5` varchar(32) DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COMMENT 'source user',
  `src_ip` varchar(50) DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_tag';

/******************************************/
/*   表名称 = config_tags_relation   */
/******************************************/
CREATE TABLE `config_tags_relation` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';

/******************************************/
/*   表名称 = group_capacity   */
/******************************************/
CREATE TABLE `group_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';

/******************************************/
/*   表名称 = his_config_info   */
/******************************************/
CREATE TABLE `his_config_info` (
  `id` bigint(64) unsigned NOT NULL,
  `nid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) NOT NULL,
  `group_id` varchar(128) NOT NULL,
  `app_name` varchar(128) DEFAULT NULL COMMENT 'app_name',
  `content` longtext NOT NULL,
  `md5` varchar(32) DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text,
  `src_ip` varchar(50) DEFAULT NULL,
  `op_type` char(10) DEFAULT NULL,
  `tenant_id` varchar(128) DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` text COMMENT '秘钥',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多租户改造';


/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = tenant_capacity   */
/******************************************/
CREATE TABLE `tenant_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户容量信息表';


CREATE TABLE `tenant_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) default '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) default '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';

CREATE TABLE `users` (
	`username` varchar(50) NOT NULL PRIMARY KEY,
	`password` varchar(500) NOT NULL,
	`enabled` boolean NOT NULL
);

CREATE TABLE `roles` (
	`username` varchar(50) NOT NULL,
	`role` varchar(50) NOT NULL,
	UNIQUE INDEX `idx_user_role` (`username` ASC, `role` ASC) USING BTREE
);

CREATE TABLE `permissions` (
    `role` varchar(50) NOT NULL,
    `resource` varchar(255) NOT NULL,
    `action` varchar(8) NOT NULL,
    UNIQUE INDEX `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
);

INSERT INTO users (username, password, enabled) VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', TRUE);

INSERT INTO roles (username, role) VALUES ('nacos', 'ROLE_ADMIN');
