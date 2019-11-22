/*
SQLyog Enterprise v13.1.1 (64 bit)
MySQL - 5.7.26-log : Database - dimpleblog-vue
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dimpleblog-vue` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `dimpleblog-vue`;

/*Table structure for table `bg_blog` */

DROP TABLE IF EXISTS `bg_blog`;

CREATE TABLE `bg_blog` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                           `category_id` bigint(20) DEFAULT NULL COMMENT '分类id',
                           `title` varchar(150) DEFAULT NULL COMMENT '文章标题',
                           `summary` varchar(256) DEFAULT NULL COMMENT '摘要',
                           `header_img` varchar(256) DEFAULT NULL COMMENT '封面图片地址',
                           `content` text COMMENT '正文内容',
                           `status` tinyint(4) DEFAULT NULL COMMENT '文章状态,1表示已经发表,2表示草稿箱',
                           `comment` tinyint(4) DEFAULT NULL COMMENT '是否允许评论,1表示允许,0表示不允许',
                           `support` tinyint(4) DEFAULT '0' COMMENT '推荐',
                           `weight` bigint(20) DEFAULT '1' COMMENT '权重',
                           `like` bigint(20) DEFAULT '0' COMMENT '点赞数',
                           `click` bigint(20) DEFAULT '0' COMMENT '点击数',
                           `create_by` varchar(150) DEFAULT NULL,
                           `create_time` datetime DEFAULT NULL,
                           `update_by` varchar(150) DEFAULT NULL,
                           `update_time` datetime DEFAULT NULL,
                           `delete_time` datetime DEFAULT NULL,
                           `delete_by` varchar(150) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='博客表';

/*Data for the table `bg_blog` */

insert  into `bg_blog`(`id`,`category_id`,`title`,`summary`,`header_img`,`content`,`status`,`comment`,`support`,`weight`,`like`,`click`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_time`,`delete_by`) values
(1,937,'22222222222222','\r\n一、全站封禁：如您在尝试登录帐号时，提示您“因安全问题被限制登录”，则您的帐号因违反《百度用户协议》被全站封禁，无法解封；二、产品线封禁：如您的帐号可正常登录，但被百度单产品线封禁，请您前往该产品线，了解封禁原因并进行线上申诉。三、产品线封禁解封办法：','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9','1213213',1,0,1,1,0,0,'admin','2019-11-19 15:56:27','admin','2019-11-13 15:02:45',NULL,NULL),
(2,937,'测试文章','这是一个摘要,峨眉山处于多种自然要素的交汇地区，区系成分复杂，生物种类丰富，特有物种繁多，保存有完整的亚热带植被体系，有植物3200多种，约占中国植物物种总数的1/10。峨眉山还是多种稀有动物的栖居地，动物种类达2300多种。山路沿途有较多猴群，常结队向游人讨食，为该山一大特色。',NULL,'<p>这是文章<span style=\"color: #00ff00;\">的内容,我们</span></p>',1,1,0,1,0,0,'admin','2019-11-19 15:56:25','admin','2019-11-13 15:02:48',NULL,NULL),
(3,938,'这是一个标题','这是文章的摘要,这是一个在压力','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9','<p>这是文章的内容</p>\n<p>&nbsp;</p>\n<p><span style=\"color: #008000;\">还可以有HTML</span></p>',1,0,1,4,0,0,'admin','2019-11-19 15:56:24','admin','2019-11-13 15:02:49',NULL,NULL),
(4,937,'这是标题','这是摘要,这是一个摘要这shi yige 摘要','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9','<p><span style=\"color: #00ff00;\">这是一个HTML呀&nbsp;</span></p>',1,1,1,3,0,0,'admin','2019-11-02 12:36:31','admin','2019-11-12 17:10:02',NULL,NULL),
(5,937,'222222222222222222','22222222222222222222','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9','<p>222222222222222222222222222222222</p>',1,1,1,1,0,0,'admin','2019-11-19 15:56:28','admin','2019-11-13 14:40:30',NULL,NULL),
(6,937,'222222222222222222','22222222222222222222','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9','<p>222222222222222222222222222222222</p>',1,1,1,1,0,300,'admin','2019-11-19 15:56:28','admin','2019-11-13 14:40:30',NULL,NULL),
(7,938,'222222222222222222','22222222222222222222','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9','<p>222222222222222222222222222222222</p>',1,1,1,1,0,0,'admin','2019-11-19 15:56:28','admin','2019-11-13 14:40:30',NULL,NULL),
(8,937,'222222222222222222','22222222222222222222','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9','<p>222222222222222222222222222222222</p>',1,1,0,1,0,0,'admin','2019-11-19 15:56:28','admin','2019-11-13 14:40:30',NULL,NULL),
(9,937,'22222222222222','\r\n一、全站封禁：如您在尝试登录帐号时，提示您“因安全问题被限制登录”，则您的帐号因违反《百度用户协议》被全站封禁，无法解封；二、产品线封禁：如您的帐号可正常登录，但被百度单产品线封禁，请您前往该产品线，了解封禁原因并进行线上申诉。三、产品线封禁解封办法：','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9','1213213',1,0,1,1,0,10,'admin','2019-11-19 15:56:27','admin','2019-11-13 15:02:45',NULL,NULL),
(10,937,'22222222222222','\r\n一、全站封禁：如您在尝试登录帐号时，提示您“因安全问题被限制登录”，则您的帐号因违反《百度用户协议》被全站封禁，无法解封；二、产品线封禁：如您的帐号可正常登录，但被百度单产品线封禁，请您前往该产品线，了解封禁原因并进行线上申诉。三、产品线封禁解封办法：','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9','1213213',1,0,1,1,0,500,'admin','2019-11-19 15:56:27','admin','2019-11-13 15:02:45',NULL,NULL),
(11,938,'22222222222222','\r\n一、全站封禁：如您在尝试登录帐号时，提示您“因安全问题被限制登录”，则您的帐号因违反《百度用户协议》被全站封禁，无法解封；二、产品线封禁：如您的帐号可正常登录，但被百度单产品线封禁，请您前往该产品线，了解封禁原因并进行线上申诉。三、产品线封禁解封办法：','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9','1213213',1,0,1,1,0,0,'admin','2019-11-19 15:56:27','admin','2019-11-13 15:02:45',NULL,NULL),
(12,937,'22222222222222','\r\n一、全站封禁：如您在尝试登录帐号时，提示您“因安全问题被限制登录”，则您的帐号因违反《百度用户协议》被全站封禁，无法解封；二、产品线封禁：如您的帐号可正常登录，但被百度单产品线封禁，请您前往该产品线，了解封禁原因并进行线上申诉。三、产品线封禁解封办法：','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9','1213213',1,0,1,1,0,0,'admin','2019-11-19 15:56:27','admin','2019-11-13 15:02:45',NULL,NULL),
(13,937,'22222222222222','\r\n一、全站封禁：如您在尝试登录帐号时，提示您“因安全问题被限制登录”，则您的帐号因违反《百度用户协议》被全站封禁，无法解封；二、产品线封禁：如您的帐号可正常登录，但被百度单产品线封禁，请您前往该产品线，了解封禁原因并进行线上申诉。三、产品线封禁解封办法：','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9','1213213',1,0,1,1,0,0,'admin','2019-11-19 15:56:27','admin','2019-11-13 15:02:45',NULL,NULL),
(14,937,'22222222222222','\r\n一、全站封禁：如您在尝试登录帐号时，提示您“因安全问题被限制登录”，则您的帐号因违反《百度用户协议》被全站封禁，无法解封；二、产品线封禁：如您的帐号可正常登录，但被百度单产品线封禁，请您前往该产品线，了解封禁原因并进行线上申诉。三、产品线封禁解封办法：','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9','1213213',1,0,1,1,0,0,'admin','2019-11-19 15:56:27','admin','2019-11-13 15:02:45',NULL,NULL),
(15,935,'22222222222222','\r\n一、全站封禁：如您在尝试登录帐号时，提示您“因安全问题被限制登录”，则您的帐号因违反《百度用户协议》被全站封禁，无法解封；二、产品线封禁：如您的帐号可正常登录，但被百度单产品线封禁，请您前往该产品线，了解封禁原因并进行线上申诉。三、产品线封禁解封办法：','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9','1213213',1,0,1,1,0,0,'admin','2019-11-19 15:56:27','admin','2019-11-13 15:02:45',NULL,NULL),
(16,937,'22222222222222','\r\n一、全站封禁：如您在尝试登录帐号时，提示您“因安全问题被限制登录”，则您的帐号因违反《百度用户协议》被全站封禁，无法解封；二、产品线封禁：如您的帐号可正常登录，但被百度单产品线封禁，请您前往该产品线，了解封禁原因并进行线上申诉。三、产品线封禁解封办法：','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9','1213213',1,0,1,1,0,0,'admin','2019-11-19 15:56:27','admin','2019-11-13 15:02:45',NULL,NULL),
(17,937,'22222222222222','\r\n一、全站封禁：如您在尝试登录帐号时，提示您“因安全问题被限制登录”，则您的帐号因违反《百度用户协议》被全站封禁，无法解封；二、产品线封禁：如您的帐号可正常登录，但被百度单产品线封禁，请您前往该产品线，了解封禁原因并进行线上申诉。三、产品线封禁解封办法：','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9','1213213',1,0,1,1,0,0,'admin','2018-11-01 15:56:27','admin','2019-11-13 15:02:45',NULL,NULL),
(18,937,'22222222222222','\r\n一、全站封禁：如您在尝试登录帐号时，提示您“因安全问题被限制登录”，则您的帐号因违反《百度用户协议》被全站封禁，无法解封；二、产品线封禁：如您的帐号可正常登录，但被百度单产品线封禁，请您前往该产品线，了解封禁原因并进行线上申诉。三、产品线封禁解封办法：','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9','1213213',1,0,1,1,0,0,'admin','2018-08-19 15:56:27','admin','2019-11-13 15:02:45',NULL,NULL),
(19,938,'22222222222222','\r\n一、全站封禁：如您在尝试登录帐号时，提示您“因安全问题被限制登录”，则您的帐号因违反《百度用户协议》被全站封禁，无法解封；二、产品线封禁：如您的帐号可正常登录，但被百度单产品线封禁，请您前往该产品线，了解封禁原因并进行线上申诉。三、产品线封禁解封办法：','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9','1213213',1,0,1,1,0,0,'admin','2017-11-19 15:56:27','admin','2019-11-13 15:02:45',NULL,NULL),
(20,935,'22222222222222','\r\n一、全站封禁：如您在尝试登录帐号时，提示您“因安全问题被限制登录”，则您的帐号因违反《百度用户协议》被全站封禁，无法解封；二、产品线封禁：如您的帐号可正常登录，但被百度单产品线封禁，请您前往该产品线，了解封禁原因并进行线上申诉。三、产品线封禁解封办法：','http://images.bianxiaofeng.com/75c0c7d78482671b30e81f3978bbb5f9','1213213',1,0,1,1,0,0,'admin','2018-11-19 15:56:27','admin','2019-11-13 15:02:45',NULL,NULL);

/*Table structure for table `bg_blog_tag` */

DROP TABLE IF EXISTS `bg_blog_tag`;

CREATE TABLE `bg_blog_tag` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `blog_id` bigint(20) DEFAULT NULL,
                               `tag_id` bigint(20) DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='blog和tag的关联表';

/*Data for the table `bg_blog_tag` */

insert  into `bg_blog_tag`(`id`,`blog_id`,`tag_id`) values
(1,5,1),
(2,5,2);

/*Table structure for table `bg_category` */

DROP TABLE IF EXISTS `bg_category`;

CREATE TABLE `bg_category` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `title` varchar(128) DEFAULT NULL COMMENT '分类名称',
                               `description` varchar(256) DEFAULT NULL COMMENT '描述',
                               `support` tinyint(4) DEFAULT NULL COMMENT '是否推荐',
                               `create_by` varchar(128) DEFAULT NULL,
                               `create_time` datetime DEFAULT NULL,
                               `update_by` varchar(128) DEFAULT NULL,
                               `update_time` datetime DEFAULT NULL,
                               `delete_by` varchar(128) DEFAULT NULL,
                               `delete_time` datetime DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8 COMMENT='分类表';

/*Data for the table `bg_category` */

insert  into `bg_category`(`id`,`title`,`description`,`support`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`) values
(935,'5ddde5f7-c080-4b9a-9e59-3c318349f12d','5afd0a5b-f933-4bbc-9ce4-98fc99713e52',1,'admin','2019-11-12 16:41:37',NULL,NULL,NULL,NULL),
(936,'11c5044a-4907-4e36-970b-0e79b540eb4f','54a52bc9-6c5f-4ee9-bcef-a110d3d8f8c3',1,'admin','2019-11-12 16:41:38',NULL,NULL,NULL,NULL),
(937,'6d08a2de-8d1b-4c94-8e42-8ffcd7c63b08','f4095459-5056-4ae9-a51a-116b02649fdc',1,'admin','2019-11-12 16:41:38',NULL,NULL,NULL,NULL),
(938,'bdabc04b-be04-4fd1-a550-7a9957c189d8','bb76799d-6d6e-45c9-a3ff-b94486434d54',1,'admin','2019-11-12 16:41:38',NULL,NULL,NULL,NULL);

/*Table structure for table `bg_comment` */

DROP TABLE IF EXISTS `bg_comment`;

CREATE TABLE `bg_comment` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `nick_name` varchar(128) DEFAULT NULL COMMENT '昵称',
                              `email` varchar(128) DEFAULT NULL COMMENT 'Email地址',
                              `ip` varchar(50) DEFAULT NULL COMMENT 'IP地址\n',
                              `location` varchar(128) DEFAULT NULL COMMENT '地理位置',
                              `os` varchar(128) DEFAULT NULL COMMENT '系统',
                              `browser` varchar(128) DEFAULT NULL COMMENT '浏览器',
                              `parent_id` bigint(20) DEFAULT NULL COMMENT '父评论的id',
                              `qq_num` varchar(128) DEFAULT NULL COMMENT 'QQ号码',
                              `avatar` varchar(256) DEFAULT NULL COMMENT '头像地址',
                              `page_id` bigint(20) DEFAULT NULL COMMENT '页面ID',
                              `url` varchar(128) DEFAULT NULL COMMENT '页面的URL\n',
                              `display` tinyint(4) DEFAULT NULL COMMENT '1表示显示,0表示不显示',
                              `good` bigint(20) DEFAULT '0' COMMENT '点赞\n',
                              `bad` bigint(20) DEFAULT '0' COMMENT '踩',
                              `content` varchar(2048) DEFAULT NULL COMMENT '评论内容',
                              `create_by` varchar(128) DEFAULT NULL,
                              `create_time` datetime DEFAULT NULL,
                              `update_by` varchar(128) DEFAULT NULL,
                              `update_time` datetime DEFAULT NULL,
                              `delete_by` varchar(128) DEFAULT NULL,
                              `delete_time` datetime DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='评论表';

/*Data for the table `bg_comment` */

insert  into `bg_comment`(`id`,`nick_name`,`email`,`ip`,`location`,`os`,`browser`,`parent_id`,`qq_num`,`avatar`,`page_id`,`url`,`display`,`good`,`bad`,`content`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`) values
(1,'测试','bianxiaofeng@sohu.com','127.0.0.1','背景','windows','Chrome',NULL,'726567707','',4,NULL,1,11,11,'this is a content','admin','2019-10-30 16:37:33',NULL,NULL,'admin','2019-11-08 20:21:53'),
(2,'测试',NULL,NULL,'sas',NULL,NULL,NULL,NULL,NULL,4,'',NULL,0,0,'112113112',NULL,NULL,NULL,NULL,'admin','2019-11-12 18:40:26'),
(3,'大傻蛋啥','1111',NULL,NULL,NULL,NULL,NULL,NULL,NULL,19,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `bg_tag` */

DROP TABLE IF EXISTS `bg_tag`;

CREATE TABLE `bg_tag` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `color` varchar(32) DEFAULT NULL COMMENT '标签轮廓颜色',
                          `title` varchar(128) DEFAULT NULL COMMENT '标签名',
                          `create_by` varchar(128) DEFAULT NULL,
                          `create_time` datetime DEFAULT NULL,
                          `update_by` varchar(128) DEFAULT NULL,
                          `update_time` datetime DEFAULT NULL,
                          `delete_by` varchar(128) DEFAULT NULL,
                          `delete_time` datetime DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='博客标签表';

/*Data for the table `bg_tag` */

insert  into `bg_tag`(`id`,`color`,`title`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`) values
(1,'1211','title1','admin','2019-11-22 10:11:32',NULL,NULL,NULL,NULL),
(2,'rgba(7, 7, 7, 1)','title2','admin','2019-11-22 10:12:47',NULL,'2019-11-22 12:32:57',NULL,NULL),
(3,NULL,'title3','admin','2019-11-22 12:20:30',NULL,'2019-11-22 12:21:52',NULL,NULL),
(4,'rgba(174, 67, 67, 1)','title4','admin','2019-11-22 12:20:41',NULL,'2019-11-22 12:24:47',NULL,NULL);

/*Table structure for table `sys_blacklist` */

DROP TABLE IF EXISTS `sys_blacklist`;

CREATE TABLE `sys_blacklist` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                 `ip` varchar(128) DEFAULT NULL COMMENT 'ip地址',
                                 `description` varchar(256) DEFAULT NULL COMMENT '封禁描述',
                                 `intercept_count` bigint(20) DEFAULT NULL COMMENT '封禁后再访问次数',
                                 `last_access_url` varchar(256) DEFAULT NULL COMMENT '上次访问的url',
                                 `last_access_time` datetime DEFAULT NULL COMMENT '上次访问时间',
                                 `create_by` varchar(150) DEFAULT NULL,
                                 `create_time` datetime DEFAULT NULL,
                                 `update_by` varchar(150) DEFAULT NULL,
                                 `update_time` datetime DEFAULT NULL,
                                 `delete_by` varchar(150) DEFAULT NULL,
                                 `delete_time` datetime DEFAULT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='系统黑名单';

/*Data for the table `sys_blacklist` */

insert  into `sys_blacklist`(`id`,`ip`,`description`,`intercept_count`,`last_access_url`,`last_access_time`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`) values
(1,'11321','1',NULL,NULL,NULL,'admin','2019-10-24 18:39:37',NULL,NULL,'admin','2019-10-28 17:12:02'),
(2,'45640','232',NULL,NULL,NULL,'admin','2019-10-24 18:44:05',NULL,NULL,'admin','2019-10-28 17:12:06'),
(3,'45640','232',NULL,NULL,NULL,'admin','2019-10-24 18:44:05',NULL,NULL,'admin','2019-10-28 17:12:09'),
(4,'121321','121312',NULL,NULL,NULL,'admin','2019-10-28 17:12:14',NULL,NULL,'admin','2019-10-28 17:12:16'),
(5,'11111111111','11111111111',NULL,NULL,NULL,'admin','2019-11-06 12:03:56',NULL,NULL,'admin','2019-11-06 12:09:29'),
(6,'1111111','11111111111111',NULL,NULL,NULL,'admin','2019-11-06 12:11:37',NULL,NULL,'admin','2019-11-06 12:12:23'),
(7,'1111111111','1111111111111',NULL,NULL,NULL,'admin','2019-11-06 12:13:50',NULL,NULL,'admin','2019-11-06 12:13:53'),
(8,'111111','1111111',NULL,NULL,NULL,'admin','2019-11-06 12:13:58',NULL,NULL,'admin','2019-11-06 12:14:00'),
(9,'1111111111','111111111111',NULL,NULL,NULL,'admin','2019-11-06 12:14:33',NULL,NULL,'admin','2019-11-06 12:14:37'),
(10,'127.22.32.3','1231',NULL,NULL,NULL,'admin','2019-11-08 18:59:19',NULL,NULL,'admin','2019-11-08 19:00:23'),
(11,'195.23.23.22','1513',NULL,NULL,NULL,'admin','2019-11-08 19:00:16',NULL,NULL,'admin','2019-11-08 19:00:42'),
(12,'12.0.0.1','111111111111111111',NULL,NULL,NULL,'admin','2019-11-12 17:59:23',NULL,NULL,'admin','2019-11-12 17:59:28'),
(13,'127.0.0.1','123231',NULL,NULL,NULL,'admin','2019-11-12 17:59:35',NULL,NULL,'admin','2019-11-12 17:59:43');

/*Table structure for table `sys_carousel` */

DROP TABLE IF EXISTS `sys_carousel`;

CREATE TABLE `sys_carousel` (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                `description` varchar(512) DEFAULT NULL COMMENT '显示文本',
                                `click` bigint(20) DEFAULT NULL COMMENT '点击次数',
                                `img_url` varchar(256) DEFAULT NULL COMMENT '图片URL',
                                `display` tinyint(4) DEFAULT NULL COMMENT '是否显示',
                                `target` tinyint(4) DEFAULT NULL COMMENT '是否当前窗口打开',
                                `url` varchar(128) DEFAULT NULL COMMENT '链接地址',
                                `title` varchar(128) DEFAULT NULL COMMENT 'header',
                                `create_by` varchar(128) DEFAULT NULL,
                                `create_time` datetime DEFAULT NULL,
                                `update_by` varchar(128) DEFAULT NULL,
                                `delete_by` varchar(128) DEFAULT NULL,
                                `update_time` datetime DEFAULT NULL,
                                `delete_time` datetime DEFAULT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='轮播图';

/*Data for the table `sys_carousel` */

insert  into `sys_carousel`(`id`,`description`,`click`,`img_url`,`display`,`target`,`url`,`title`,`create_by`,`create_time`,`update_by`,`delete_by`,`update_time`,`delete_time`) values
(1,'http://bianxiaofeng.com',NULL,'http://bianxiaofeng.com',1,1,NULL,NULL,'admin',NULL,NULL,'admin',NULL,'2019-11-12 18:09:34');

/*Table structure for table `sys_config` */

DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config` (
                              `id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
                              `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
                              `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
                              `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                              `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                              `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                              `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                              `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                              `delete_by` varchar(64) DEFAULT '',
                              `delete_time` datetime DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='参数配置表';

/*Data for the table `sys_config` */

insert  into `sys_config`(`id`,`config_key`,`config_value`,`remark`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`) values
(1,'sys.index.skinName','skin-blue','蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL),
(2,'sys.user.initPassword','123456','初始化密码 123456','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL),
(3,'sys.index.sideTheme','theme-dark','深色主题theme-dark，浅色主题theme-light','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','admin','2019-11-06 14:39:11'),
(4,'12121','12121',NULL,'admin','2019-10-23 14:41:55','',NULL,'admin','2019-10-23 14:43:06'),
(5,'qiNiuConfig','{\"accessKey\":\"adminBRR72W1AwFtqbAKVkpNIQl0vNzwqwqwq2UIhNsWT6MjpMG\",\"bucket\":\"dimpleblog\",\"host\":\" q0jwwt1l6.bkt.clouddn.com\",\"secretKey\":\"qtiuY6dS7bKEMBTqwqwq_nhBnJYC_Bytos07TQ_dDXjM4\",\"type\":\"公开\",\"zone\":\"华南\"}',NULL,'','2019-11-07 16:12:00','',NULL,'',NULL);

/*Table structure for table `sys_dict_data` */

DROP TABLE IF EXISTS `sys_dict_data`;

CREATE TABLE `sys_dict_data` (
                                 `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
                                 `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
                                 `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
                                 `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
                                 `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
                                 `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
                                 `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
                                 `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
                                 `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                 `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                                 `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 `delete_by` varchar(64) DEFAULT '' COMMENT '刪除者',
                                 `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
                                 `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                                 PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='字典数据表';

/*Data for the table `sys_dict_data` */

insert  into `sys_dict_data`(`dict_code`,`dict_sort`,`dict_label`,`dict_value`,`dict_type`,`css_class`,`list_class`,`is_default`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`,`remark`) values
(1,1,'男','0','sys_user_sex','','','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'性别男'),
(2,2,'女','1','sys_user_sex','','','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'性别女'),
(3,3,'未知','2','sys_user_sex','','','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'性别未知'),
(4,1,'显示','0','sys_show_hide','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'显示菜单'),
(5,2,'隐藏','1','sys_show_hide','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'隐藏菜单'),
(6,1,'正常','0','sys_normal_disable','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'正常状态'),
(7,2,'停用','1','sys_normal_disable','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'停用状态'),
(8,1,'正常','0','sys_job_status','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'正常状态'),
(9,2,'暂停','1','sys_job_status','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'停用状态'),
(10,1,'默认','DEFAULT','sys_job_group','','','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'默认分组'),
(11,2,'系统','SYSTEM','sys_job_group','','','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'系统分组'),
(12,1,'是','Y','sys_yes_no','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'系统默认是'),
(13,2,'否','N','sys_yes_no','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'系统默认否'),
(14,1,'通知','1','sys_notice_type','','warning','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'通知'),
(15,2,'公告','2','sys_notice_type','','success','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'公告'),
(16,1,'正常','0','sys_notice_status','','primary','Y','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'正常状态'),
(17,2,'关闭','1','sys_notice_status','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'关闭状态'),
(18,1,'新增','1','sys_oper_type','','info','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'新增操作'),
(19,2,'修改','2','sys_oper_type','','info','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'修改操作'),
(20,3,'删除','3','sys_oper_type','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'删除操作'),
(21,4,'授权','4','sys_oper_type','','primary','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'授权操作'),
(22,5,'导出','5','sys_oper_type','','warning','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'导出操作'),
(23,6,'导入','6','sys_oper_type','','warning','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'导入操作'),
(24,7,'强退','7','sys_oper_type','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'强退操作'),
(25,8,'生成代码','8','sys_oper_type','','warning','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'生成操作'),
(26,9,'清空数据','9','sys_oper_type','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'清空操作'),
(27,1,'成功','0','sys_common_status','','primary','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'正常状态'),
(28,2,'失败','1','sys_common_status','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'停用状态'),
(29,1,'草稿','false','bg_blog_status',NULL,NULL,'N','0','admin','2019-10-31 14:33:38','admin','2019-10-31 14:35:03','',NULL,'草稿箱'),
(30,2,'发布','true','bg_blog_status',NULL,NULL,'N','0','admin','2019-10-31 14:34:45','admin','2019-10-31 14:34:53','',NULL,'已发布'),
(31,1,'是','true','bg_blog_support',NULL,NULL,'N','0','admin','2019-10-31 14:46:49','',NULL,'',NULL,'推荐'),
(32,2,'否','false','bg_blog_support',NULL,NULL,'N','0','admin','2019-10-31 14:47:22','',NULL,'',NULL,'不推荐'),
(33,1,'开启','true','bg_blog_comment',NULL,NULL,'N','0','admin','2019-10-31 18:38:35','',NULL,'',NULL,'开启'),
(34,2,'关闭','false','bg_blog_comment',NULL,NULL,'N','0','admin','2019-10-31 18:38:53','',NULL,'',NULL,'关闭');

/*Table structure for table `sys_dict_type` */

DROP TABLE IF EXISTS `sys_dict_type`;

CREATE TABLE `sys_dict_type` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
                                 `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
                                 `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
                                 `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                 `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                                 `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 `delete_by` varchar(64) DEFAULT '' COMMENT '刪除者',
                                 `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
                                 `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='字典类型表';

/*Data for the table `sys_dict_type` */

insert  into `sys_dict_type`(`id`,`dict_name`,`dict_type`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`,`remark`) values
(1,'用户性别','sys_user_sex','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'用户性别列表'),
(2,'菜单状态','sys_show_hide','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'菜单状态列表'),
(3,'系统开关','sys_normal_disable','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'系统开关列表'),
(4,'任务状态','sys_job_status','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'任务状态列表'),
(5,'任务分组','sys_job_group','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'任务分组列表'),
(6,'系统是否','sys_yes_no','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'系统是否列表'),
(7,'通知类型','sys_notice_type','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'通知类型列表'),
(8,'通知状态','sys_notice_status','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'通知状态列表'),
(9,'操作类型','sys_oper_type','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'操作类型列表'),
(10,'系统状态','sys_common_status','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'登录状态列表'),
(11,'博客状态','bg_blog_status','0','admin','2019-10-31 14:15:39','',NULL,'',NULL,'博客状态列表'),
(12,'博客推荐','bg_blog_support','0','admin','2019-10-31 14:46:33','',NULL,'',NULL,'博客推荐列表'),
(13,'博客评论','bg_blog_comment','0','admin','2019-10-31 18:38:12','',NULL,'',NULL,'博客评论列表');

/*Table structure for table `sys_link` */

DROP TABLE IF EXISTS `sys_link`;

CREATE TABLE `sys_link` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                            `title` varchar(128) DEFAULT NULL COMMENT '友链名称',
                            `url` varchar(128) DEFAULT NULL COMMENT '友链地址',
                            `description` varchar(512) DEFAULT NULL COMMENT '友链描述',
                            `header_img` varchar(128) DEFAULT NULL COMMENT '网站图片',
                            `status` tinyint(4) DEFAULT '0' COMMENT '1表示审核通过,0表示未审核',
                            `display` tinyint(4) DEFAULT '0' COMMENT '是否显示友链',
                            `email` varchar(128) DEFAULT NULL COMMENT '站长邮箱地址',
                            `weight` bigint(20) DEFAULT '0' COMMENT '权重',
                            `create_by` varchar(128) DEFAULT NULL,
                            `create_time` datetime DEFAULT NULL,
                            `update_by` varchar(128) DEFAULT NULL,
                            `update_time` datetime DEFAULT NULL,
                            `delete_by` varchar(128) DEFAULT NULL,
                            `delete_time` datetime DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='友链表';

/*Data for the table `sys_link` */

insert  into `sys_link`(`id`,`title`,`url`,`description`,`header_img`,`status`,`display`,`email`,`weight`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`) values
(1,'1232222222222222','https://bianxiaofeng.com/front/images/ava.png','ewew','https://bianxiaofeng.com/front/images/ava.png',1,1,'21@email.com',122,'admin','2019-10-29 12:21:10','admin','2019-10-30 15:59:28',NULL,NULL),
(2,'111','http://www.bianxiaofeng.com','1111','https://bianxiaofeng.com/front/images/ava.png',1,1,'biianxiaofeng@sohu.com',11,'admin','2019-11-08 22:40:25','admin','2019-11-12 18:20:25',NULL,NULL);

/*Table structure for table `sys_login_log` */

DROP TABLE IF EXISTS `sys_login_log`;

CREATE TABLE `sys_login_log` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
                                 `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
                                 `ip` varchar(50) DEFAULT '' COMMENT '登录IP地址',
                                 `location` varchar(255) DEFAULT '' COMMENT '登录地点',
                                 `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
                                 `os` varchar(50) DEFAULT '' COMMENT '操作系统',
                                 `status` tinyint(4) DEFAULT '0' COMMENT '登录状态（1成功 0失败）',
                                 `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
                                 `create_time` datetime DEFAULT NULL COMMENT '访问时间',
                                 `create_by` varchar(128) DEFAULT NULL,
                                 `delete_by` varchar(128) DEFAULT NULL,
                                 `delete_time` datetime DEFAULT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='系统访问记录';

/*Data for the table `sys_login_log` */

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
                            `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
                            `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
                            `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
                            `path` varchar(200) DEFAULT '' COMMENT '路由地址',
                            `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
                            `is_frame` int(1) DEFAULT '1' COMMENT '是否为外链（0是 1否）',
                            `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（0目录 1菜单 2按钮）',
                            `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
                            `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
                            `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
                            `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            `delete_by` varchar(64) DEFAULT '' COMMENT '刪除者',
                            `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
                            `remark` varchar(500) DEFAULT '' COMMENT '备注',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1101 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`is_frame`,`menu_type`,`visible`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`,`remark`) values
(1,'系统管理',0,5,'system',NULL,1,'M','0','','system','admin','2018-03-16 11:33:00','admin','2019-10-24 10:33:09','',NULL,'系统管理目录'),
(2,'系统监控',0,4,'monitor',NULL,1,'M','0','','monitor','admin','2018-03-16 11:33:00','admin','2019-10-24 10:33:16','',NULL,'系统监控目录'),
(100,'用户管理',1,1,'user','system/user/index',1,'C','0','system:user:list','user','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'用户管理菜单'),
(101,'角色管理',1,2,'role','system/role/index',1,'C','0','system:role:list','peoples','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'角色管理菜单'),
(102,'菜单管理',1,3,'menu','system/menu/index',1,'C','0','system:menu:list','tree-table','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'菜单管理菜单'),
(105,'字典管理',1,6,'dict','system/dict/index',1,'C','0','system:dict:list','dict','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'字典管理菜单'),
(106,'参数设置',1,7,'config','system/config/index',1,'C','0','system:config:list','edit','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'参数设置菜单'),
(107,'通知公告',1,8,'notice','system/notice/index',1,'C','0','system:notice:list','message','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'通知公告菜单'),
(108,'日志管理',0,1,'log','',1,'M','0','','log','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'日志管理菜单'),
(109,'在线用户',2,1,'online','monitor/online/index',1,'C','0','monitor:online:list','online','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'在线用户菜单'),
(110,'定时任务',1095,2,'tool/quartz','tool/quartz/index',1,'C','0','monitor:job:list','job','admin','2018-03-16 11:33:00','admin','2019-11-07 21:43:20','',NULL,'定时任务菜单'),
(111,'数据监控',2,3,'druid','monitor/druid/index',1,'C','0','monitor:druid:list','druid','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'数据监控菜单'),
(112,'服务监控',2,4,'server','monitor/server/index',1,'C','0','monitor:server:list','server','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'服务监控菜单'),
(115,'系统接口',2,3,'swagger','monitor/swagger/index',1,'C','0','tool:swagger:list','swagger','admin','2018-03-16 11:33:00','admin','2019-10-24 10:21:02','',NULL,'系统接口菜单'),
(500,'操作日志',108,1,'operateLog','log/operateLog/index',1,'C','0','monitor:operlog:list','form','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'操作日志菜单'),
(501,'登录日志',108,2,'loginLog','log/loginLog/index',1,'C','0','monitor:logininfor:list','logininfor','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'登录日志菜单'),
(1001,'用户查询',100,1,'','',1,'F','0','system:user:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1002,'用户新增',100,2,'','',1,'F','0','system:user:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1003,'用户修改',100,3,'','',1,'F','0','system:user:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1004,'用户删除',100,4,'','',1,'F','0','system:user:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1005,'用户导出',100,5,'','',1,'F','0','system:user:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1006,'用户导入',100,6,'','',1,'F','0','system:user:import','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1007,'重置密码',100,7,'','',1,'F','0','system:user:resetPwd','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1008,'角色查询',101,1,'','',1,'F','0','system:role:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1009,'角色新增',101,2,'','',1,'F','0','system:role:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1010,'角色修改',101,3,'','',1,'F','0','system:role:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1011,'角色删除',101,4,'','',1,'F','0','system:role:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1012,'角色导出',101,5,'','',1,'F','0','system:role:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1013,'菜单查询',102,1,'','',1,'F','0','system:menu:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1014,'菜单新增',102,2,'','',1,'F','0','system:menu:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1015,'菜单修改',102,3,'','',1,'F','0','system:menu:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1016,'菜单删除',102,4,'','',1,'F','0','system:menu:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1026,'字典查询',105,1,'#','',1,'F','0','system:dict:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1027,'字典新增',105,2,'#','',1,'F','0','system:dict:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1028,'字典修改',105,3,'#','',1,'F','0','system:dict:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1029,'字典删除',105,4,'#','',1,'F','0','system:dict:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1030,'字典导出',105,5,'#','',1,'F','0','system:dict:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1031,'参数查询',106,1,'#','',1,'F','0','system:config:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1032,'参数新增',106,2,'#','',1,'F','0','system:config:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1033,'参数修改',106,3,'#','',1,'F','0','system:config:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1034,'参数删除',106,4,'#','',1,'F','0','system:config:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1035,'参数导出',106,5,'#','',1,'F','0','system:config:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1036,'公告查询',107,1,'#','',1,'F','0','system:notice:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1037,'公告新增',107,2,'#','',1,'F','0','system:notice:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1038,'公告修改',107,3,'#','',1,'F','0','system:notice:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1039,'公告删除',107,4,'#','',1,'F','0','system:notice:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1040,'操作查询',500,1,'#','',1,'F','0','monitor:operlog:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1041,'操作删除',500,2,'#','',1,'F','0','monitor:operlog:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1042,'日志导出',500,4,'#','',1,'F','0','monitor:operlog:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1043,'登录查询',501,1,'#','',1,'F','0','monitor:logininfor:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1044,'登录删除',501,2,'#','',1,'F','0','monitor:logininfor:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1045,'日志导出',501,3,'#','',1,'F','0','monitor:logininfor:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1046,'在线查询',109,1,'#','',1,'F','0','monitor:online:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1047,'批量强退',109,2,'#','',1,'F','0','monitor:online:batchLogout','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1048,'单条强退',109,3,'#','',1,'F','0','monitor:online:forceLogout','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1049,'任务查询',110,1,'#','',1,'F','0','monitor:job:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1050,'任务新增',110,2,'#','',1,'F','0','monitor:job:add','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1051,'任务修改',110,3,'#','',1,'F','0','monitor:job:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1052,'任务删除',110,4,'#','',1,'F','0','monitor:job:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1053,'状态修改',110,5,'#','',1,'F','0','monitor:job:changeStatus','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1054,'任务导出',110,7,'#','',1,'F','0','monitor:job:export','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(1066,'博客管理',0,1,'',NULL,1,'M','0','','nested','admin','2019-10-24 10:25:45','admin','2019-11-06 11:05:47','',NULL,''),
(1067,'博文管理',1066,1,'/blog/blog','blog/blog/index',1,'C','0','blog:blog:list','dict','admin','2019-10-24 10:26:41','admin','2019-10-24 14:39:17','',NULL,''),
(1068,'评论管理',1066,4,'/blog/comment','blog/comment/index',1,'C','0','blog:comment:list','component','admin','2019-10-24 10:31:20','',NULL,'',NULL,''),
(1069,'类别管理',1066,2,'/blog/category','blog/category/index',1,'C','0','blog:category:list','download','admin','2019-10-24 10:32:17','',NULL,'',NULL,''),
(1071,'黑名单',2,6,'/monitor/blacklist','monitor/blacklist/index',1,'C','0','monitor:blacklist:list','drag','admin','2019-10-24 16:49:08','',NULL,'',NULL,''),
(1072,'友链管理',1,6,'/system/link','system/link/index',1,'C','0','system:link:list','404','admin','2019-10-29 11:27:42','',NULL,'',NULL,''),
(1073,'博文查询',1067,1,'',NULL,1,'F','0','blog:blog:query','#','admin','2019-11-01 17:25:38','',NULL,'',NULL,''),
(1074,'博文修改',1067,2,'',NULL,1,'F','0','blog:blog:edit','#','admin','2019-11-01 17:25:56','',NULL,'',NULL,''),
(1075,'博文新增',1067,3,'',NULL,1,'F','0','blog:blog:add','#','admin','2019-11-01 17:26:14','',NULL,'',NULL,''),
(1076,'博文删除',1067,4,'',NULL,1,'F','0','blog:blog:remove','#','admin','2019-11-01 17:26:39','',NULL,'',NULL,''),
(1077,'类别新增',1069,1,'',NULL,1,'F','0','blog:category:add','#','admin','2019-11-01 17:27:09','',NULL,'',NULL,''),
(1078,'类别删除',1069,2,'',NULL,1,'F','0','blog:category:remove','#','admin','2019-11-01 17:27:27','',NULL,'',NULL,''),
(1079,'分类查询',1069,3,'',NULL,1,'F','0','blog:category:query','#','admin','2019-11-01 17:27:49','',NULL,'',NULL,''),
(1080,'分类修改',1069,4,'',NULL,1,'F','0','blog:category:edit','#','admin','2019-11-01 17:28:09','',NULL,'',NULL,''),
(1081,'评论新增',1068,1,'',NULL,1,'F','0','blog:comment:add','#','admin','2019-11-01 17:28:28','',NULL,'',NULL,''),
(1082,'评论删除',1068,2,'',NULL,1,'F','0','blog:comment:remove','#','admin','2019-11-01 17:28:50','',NULL,'',NULL,''),
(1083,'评论修改',1068,3,'',NULL,1,'F','0','blog:comment:edit','#','admin','2019-11-01 17:29:32','',NULL,'',NULL,''),
(1084,'评论查询',1068,4,'',NULL,1,'F','0','blog:comment:query','#','admin','2019-11-01 17:29:51','',NULL,'',NULL,''),
(1085,'友链新增',1072,1,'',NULL,1,'F','0','system:link:add','#','admin','2019-11-01 17:30:17','',NULL,'',NULL,''),
(1086,'友链删除',1072,2,'',NULL,1,'F','0','system:link:remove','#','admin','2019-11-01 17:30:34','',NULL,'',NULL,''),
(1087,'友链查询',1072,3,'',NULL,1,'F','0','system:link:query','#','admin','2019-11-01 17:30:52','',NULL,'',NULL,''),
(1088,'友链修改',1072,4,'',NULL,1,'F','0','system:link:edit','#','admin','2019-11-01 17:31:08','',NULL,'',NULL,''),
(1089,'黑名单新增',1071,1,'',NULL,1,'F','0','monitor:blacklist:add','#','admin','2019-11-01 17:31:37','',NULL,'',NULL,''),
(1090,'黑名单修改',1071,2,'',NULL,1,'F','0','monitor:blacklist:edit','#','admin','2019-11-01 17:31:53','',NULL,'',NULL,''),
(1091,'黑名单删除',1071,3,'',NULL,1,'F','0','monitor:blacklist:remove','#','admin','2019-11-01 17:32:10','',NULL,'',NULL,''),
(1092,'黑名单查询',1071,4,'',NULL,1,'F','0','monitor:blacklist:query','#','admin','2019-11-01 17:32:24','',NULL,'',NULL,''),
(1093,'轮播图管理',1,7,'/system/carousel','system/carousel/index',1,'C','0','system:carousel:list','dict','admin','2019-11-04 11:11:02','',NULL,'',NULL,''),
(1094,'实时日志',2,6,'/monitor/log','monitor/RealTimeLog/index',1,'C','0',NULL,'education','admin','2019-11-05 18:16:20','',NULL,'',NULL,''),
(1095,'系统工具',0,2,'tool',NULL,1,'M','0',NULL,'education','admin','2019-11-06 10:53:11','',NULL,'',NULL,''),
(1097,'存储管理',1095,2,'tool/storage','tool/storage/index',1,'C','0','tool:storage:list','eye-open','admin','2019-11-06 10:54:28','admin','2019-11-07 12:07:52','',NULL,''),
(1098,'图床管理',1095,3,'tool/pictures','tool/pictures/index',1,'C','0','tool:pictures:list','date','admin','2019-11-06 10:55:34','admin','2019-11-06 10:58:18','',NULL,''),
(1099,'任务日志',108,3,'log/quartz','log/quartzLog/index',1,'C','0','','dict','admin','2019-11-08 16:23:22','admin','2019-11-08 16:23:37','',NULL,''),
(1100,'标签管理',1066,6,'/blog/tag','blog/tag/index',1,'C','0','','chart','admin','2019-11-13 11:03:47','admin','2019-11-22 12:18:08','',NULL,'');

/*Table structure for table `sys_notice` */

DROP TABLE IF EXISTS `sys_notice`;

CREATE TABLE `sys_notice` (
                              `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
                              `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
                              `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
                              `notice_content` varchar(2000) DEFAULT NULL COMMENT '公告内容',
                              `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
                              `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                              `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                              `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                              `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                              `delete_by` varchar(64) DEFAULT '' COMMENT '刪除者',
                              `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
                              `remark` varchar(255) DEFAULT NULL COMMENT '备注',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='通知公告表';

/*Data for the table `sys_notice` */

insert  into `sys_notice`(`id`,`notice_title`,`notice_type`,`notice_content`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`,`remark`) values
(1,'温馨提醒：2018-07-01 若依新版本发布啦','2','<p>新版本内容1123<img src=\"http://localhost:8080/profile/upload/2019/10/10/35492d57cfc519445c1dfcb6119a9c1a.jpg\"></p>','0','admin','2018-03-16 11:33:00','admin','2019-10-10 15:58:20','admin','2019-11-02 00:59:03','管理员'),
(2,'维护通知：2018-07-01 若依系统凌晨维护','1','维护内容','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','admin','2019-11-02 00:59:05','管理员'),
(3,'1111111111','1','<p>11111111111111</p>','0','admin','2019-11-12 18:28:04','',NULL,'admin','2019-11-12 18:33:21',NULL);

/*Table structure for table `sys_operate_log` */

DROP TABLE IF EXISTS `sys_operate_log`;

CREATE TABLE `sys_operate_log` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
                                   `title` varchar(50) DEFAULT '' COMMENT '模块标题',
                                   `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
                                   `method` varchar(100) DEFAULT '' COMMENT '方法名称',
                                   `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
                                   `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
                                   `operate_name` varchar(50) DEFAULT '' COMMENT '操作人员',
                                   `url` varchar(255) DEFAULT '' COMMENT '请求URL',
                                   `ip` varchar(50) DEFAULT '' COMMENT '主机地址',
                                   `location` varchar(255) DEFAULT '' COMMENT '操作地点',
                                   `param` varchar(2000) DEFAULT '' COMMENT '请求参数',
                                   `json_result` varchar(2000) DEFAULT '' COMMENT '返回参数',
                                   `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
                                   `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
                                   `cost` bigint(20) DEFAULT NULL COMMENT '耗时,单位毫秒',
                                   `create_time` datetime DEFAULT NULL COMMENT '操作时间',
                                   `create_by` varchar(128) DEFAULT NULL,
                                   `delete_by` varchar(128) DEFAULT NULL,
                                   `delete_time` datetime DEFAULT NULL,
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8 COMMENT='操作日志记录';

/*Data for the table `sys_operate_log` */

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
                            `role_name` varchar(30) NOT NULL COMMENT '角色名称',
                            `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
                            `role_sort` int(4) NOT NULL COMMENT '显示顺序',
                            `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
                            `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            `delete_by` varchar(64) DEFAULT '' COMMENT '刪除者',
                            `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
                            `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`role_name`,`role_key`,`role_sort`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`,`remark`) values
(1,'管理员','admin',1,'0','admin','2018-03-16 11:33:00','23','2018-03-16 11:33:00','',NULL,'管理员'),
(2,'普通角色','common',2,'0','admin','2018-03-16 11:33:00','admin','2019-11-12 19:11:06','',NULL,'普通角色'),
(3,'111','11',1,'0','admin','2019-10-23 15:45:44','admin','2019-10-23 15:46:13','admin','2019-10-23 15:46:18',NULL),
(4,'1111','111111111111111',1,'0','admin','2019-11-12 18:35:47','',NULL,'admin','2019-11-12 18:50:24','111');

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
                                 `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                                 `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
                                 PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_id`,`menu_id`) values
(2,1),
(2,2),
(2,100),
(2,101),
(2,102),
(2,105),
(2,106),
(2,107),
(2,108),
(2,110),
(2,115),
(2,500),
(2,501),
(2,1001),
(2,1002),
(2,1003),
(2,1004),
(2,1005),
(2,1006),
(2,1007),
(2,1008),
(2,1009),
(2,1010),
(2,1011),
(2,1012),
(2,1013),
(2,1014),
(2,1015),
(2,1016),
(2,1026),
(2,1027),
(2,1028),
(2,1029),
(2,1030),
(2,1031),
(2,1032),
(2,1033),
(2,1034),
(2,1035),
(2,1036),
(2,1037),
(2,1038),
(2,1039),
(2,1040),
(2,1041),
(2,1042),
(2,1043),
(2,1044),
(2,1045),
(2,1049),
(2,1050),
(2,1051),
(2,1052),
(2,1053),
(2,1054),
(2,1066),
(2,1067),
(2,1068),
(2,1069),
(2,1073),
(2,1074),
(2,1075),
(2,1076),
(2,1077),
(2,1078),
(2,1079),
(2,1080),
(2,1081),
(2,1082),
(2,1083),
(2,1084),
(2,1095),
(2,1097),
(2,1098),
(3,2),
(3,109),
(3,110),
(3,111),
(3,112),
(3,1046),
(3,1047),
(3,1048),
(3,1049),
(3,1050),
(3,1051),
(3,1052),
(3,1053),
(3,1054),
(4,1066),
(4,1067),
(4,1068),
(4,1069),
(4,1073),
(4,1074),
(4,1075),
(4,1076),
(4,1077),
(4,1078),
(4,1079),
(4,1080),
(4,1081),
(4,1082),
(4,1083),
(4,1084);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                            `user_name` varchar(30) NOT NULL COMMENT '用户账号',
                            `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
                            `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
                            `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
                            `phone` varchar(11) DEFAULT '' COMMENT '手机号码',
                            `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
                            `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
                            `password` varchar(100) DEFAULT '' COMMENT '密码',
                            `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
                            `login_ip` varchar(50) DEFAULT '' COMMENT '最后登陆IP',
                            `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
                            `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            `delete_by` varchar(64) DEFAULT '' COMMENT '刪除者',
                            `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
                            `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`user_name`,`nick_name`,`user_type`,`email`,`phone`,`sex`,`avatar`,`password`,`status`,`login_ip`,`login_date`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`,`remark`) values
(1,'admin','Dimple','00','ry@163.com','15822222222','1','hthz_9.jpeg','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','127.0.0.1','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','ry','2019-11-08 13:46:22','',NULL,'管理员'),
(2,'test','测试','00','te1st@qq.com','15666666666','0','default.jpg','$2a$10$hSL8f.30KJkGq7nZ4W9.W.mgKQUmxq2Twq4o7a2FlWDiP6yVpC95S','0','127.0.0.1','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','admin','2019-11-12 19:08:13','',NULL,'测试员111'),
(3,'admin1','11','00','network@artech.com','15923652363','0','default.jpg','$2a$10$hPifjlDc.5sEbalGlp4agOmlungpzq0tUpk62X04iJvaToyFaKLse','0','',NULL,'admin','2019-10-23 15:44:49','',NULL,'admin','2019-10-23 16:52:26','111'),
(6,'222','12121','00','eew@s.com','18582872818','0','','$2a$10$E.yDpz0iIxA3KWssnmmZO./7dYK/ZCmkzQsjI1mJco7/yMDXXgYoK','0','',NULL,'admin','2019-11-12 18:59:17','',NULL,'admin','2019-11-12 19:06:06','22');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
                                 `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                 `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                                 PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values
(1,1),
(2,2);

/*Table structure for table `tool_local_storage` */

DROP TABLE IF EXISTS `tool_local_storage`;

CREATE TABLE `tool_local_storage` (
                                      `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                      `real_name` varchar(256) DEFAULT NULL COMMENT '文件真实名称',
                                      `name` varchar(256) DEFAULT NULL COMMENT '文件名',
                                      `suffix` varchar(128) DEFAULT NULL COMMENT '后缀',
                                      `path` varchar(256) DEFAULT NULL COMMENT '路径',
                                      `type` varchar(128) DEFAULT NULL COMMENT '文件类型',
                                      `size` varchar(128) DEFAULT NULL COMMENT '大小',
                                      `create_by` varchar(128) DEFAULT NULL,
                                      `create_time` datetime DEFAULT NULL,
                                      `update_time` datetime DEFAULT NULL,
                                      `update_by` varchar(128) DEFAULT NULL,
                                      `delete_by` varchar(128) DEFAULT NULL,
                                      `delete_time` datetime DEFAULT NULL,
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='本地存储';

/*Data for the table `tool_local_storage` */

insert  into `tool_local_storage`(`id`,`real_name`,`name`,`suffix`,`path`,`type`,`size`,`create_by`,`create_time`,`update_time`,`update_by`,`delete_by`,`delete_time`) values
(1,'api-ms-win-core-interlocked-l1-1-020191107125502.dll','api-ms-win-core-interlocked-l1-1-0','dll','D:\\dimple\\dimpleBlog\\file\\upload其他\\api-ms-win-core-interlocked-l1-1-020191107125502.dll','其他','18.26KB','admin','2019-11-07 12:55:02',NULL,NULL,'admin','2019-11-07 13:35:36'),
(2,'api-ms-win-core-handle-l1-1-020191107013547.dll','api-ms-win-core-handle-l1-1-0','dll','D:\\dimple\\dimpleBlog\\file\\upload其他\\api-ms-win-core-handle-l1-1-020191107013547.dll','其他','17.76KB','admin','2019-11-07 13:35:47',NULL,NULL,'admin','2019-11-07 13:39:51'),
(3,'api-ms-win-core-handle-l1-1-020191107013717.dll','api-ms-win-core-handle-l1-1-0','dll','D:\\dimple\\dimpleBlog\\file\\upload其他\\api-ms-win-core-handle-l1-1-020191107013717.dll','其他','17.76KB','admin','2019-11-07 13:37:17',NULL,NULL,'admin','2019-11-07 13:39:52'),
(4,'api-ms-win-crt-time-l1-1-020191107013940.dll','api-ms-win-crt-time-l1-1-0','dll','D:\\dimple\\dimpleBlog\\file\\upload其他\\api-ms-win-crt-time-l1-1-020191107013940.dll','其他','20.26KB','admin','2019-11-07 13:39:40',NULL,NULL,'admin','2019-11-07 13:39:53'),
(5,'api-ms-win-core-heap-l1-1-020191107014008.dll','765','dll','D:\\dimple\\dimpleBlog\\file\\upload其他\\api-ms-win-core-heap-l1-1-020191107014008.dll','其他','17.76KB','admin','2019-11-07 13:40:08','2019-11-07 13:41:07','admin','admin','2019-11-07 14:11:29'),
(6,'computer-operation_120191107014326.png','computer-operation_1','png','D:\\dimple\\dimpleBlog\\file\\upload图片\\computer-operation_120191107014326.png','图片','2.72KB','admin','2019-11-07 13:43:26',NULL,NULL,'admin','2019-11-07 14:11:28'),
(7,'Free-Converter.com-recycle_bin_file-7832421020191107021136.ico','Free-Converter.com-recycle_bin_file-78324210','ico','D:\\dimple\\dimpleBlog\\file\\其他\\Free-Converter.com-recycle_bin_file-7832421020191107021136.ico','其他','11.52KB','admin','2019-11-07 14:11:36',NULL,NULL,'admin','2019-11-07 14:11:54'),
(8,'Free-Converter.com-computer-operation_1-6317412920191107021235.ico','Free-Converter.com-computer-operation_1-63174129','ico','D:\\dimple\\dimpleBlog\\file\\其他\\Free-Converter.com-computer-operation_1-6317412920191107021235.ico','其他','222.81KB','admin','2019-11-07 14:12:35',NULL,NULL,'admin','2019-11-07 14:19:42'),
(9,'Free-Converter.com-recycle_bin_file-7832421020191107021955.ico','Free-Converter.com-recycle_bin_file-78324210','ico','D:\\dimple\\dimpleBlog\\file\\其他\\Free-Converter.com-recycle_bin_file-7832421020191107021955.ico','其他','11.52KB','admin','2019-11-07 14:19:55',NULL,NULL,'admin','2019-11-07 14:20:03'),
(10,'person20191107022311.png','person','png','D:\\dimple\\dimpleBlog\\file\\图片\\person20191107022311.png','图片','12.71KB','admin','2019-11-07 14:23:11',NULL,NULL,'admin','2019-11-07 14:44:04'),
(11,'navicat-keygen-for-x6420191107024414.zip','navicat-keygen-for-x64111','zip','D:\\dimple\\dimpleBlog\\file\\其他\\navicat-keygen-for-x6420191107024414.zip','其他','2.01MB','admin','2019-11-07 14:44:14','2019-11-07 14:44:41','admin','admin','2019-11-07 14:44:54'),
(12,'nginx-1.17.320191107025254.zip','nginx-1.17.3','zip','D:\\dimple\\dimpleBlog\\file\\其他\\nginx-1.17.320191107025254.zip','其他','1.62MB','admin','2019-11-07 14:52:54',NULL,NULL,'admin','2019-11-07 16:19:08'),
(13,'persons20191107052650.png','persons','png','D:\\dimple\\dimpleBlog\\file\\图片\\persons20191107052650.png','图片','9.63KB','admin','2019-11-07 17:26:50',NULL,NULL,'admin','2019-11-07 17:28:53'),
(14,'computer-operation_120191107052702.png','computer-operation_1','png','D:\\dimple\\dimpleBlog\\file\\图片\\computer-operation_120191107052702.png','图片','2.72KB','admin','2019-11-07 17:27:02',NULL,NULL,'admin','2019-11-07 17:28:55');

/*Table structure for table `tool_qi_niu_content` */

DROP TABLE IF EXISTS `tool_qi_niu_content`;

CREATE TABLE `tool_qi_niu_content` (
                                       `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                       `name` varchar(128) DEFAULT NULL COMMENT '七牛云文件名称',
                                       `bucket` varchar(128) DEFAULT NULL COMMENT '空间',
                                       `size` varchar(64) DEFAULT NULL COMMENT '文件大小',
                                       `url` varchar(128) DEFAULT NULL COMMENT '文件访问地址',
                                       `suffix` varchar(64) DEFAULT NULL COMMENT '文件后缀',
                                       `type` varchar(64) DEFAULT NULL COMMENT '类型',
                                       `create_by` varchar(128) DEFAULT NULL,
                                       `create_time` datetime DEFAULT NULL,
                                       `update_time` datetime DEFAULT NULL,
                                       `update_by` varchar(128) DEFAULT NULL,
                                       `delete_by` varchar(128) DEFAULT NULL,
                                       `delete_time` datetime DEFAULT NULL,
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='七牛云数据本地缓存';

/*Data for the table `tool_qi_niu_content` */

/*Table structure for table `tool_quartz_job` */

DROP TABLE IF EXISTS `tool_quartz_job`;

CREATE TABLE `tool_quartz_job` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                   `job_name` varchar(255) DEFAULT NULL COMMENT '任务名称',
                                   `bean_name` varchar(255) DEFAULT NULL COMMENT 'Spring Bean名称',
                                   `method_name` varchar(255) DEFAULT NULL COMMENT '方法名称',
                                   `method_params` varchar(255) DEFAULT NULL COMMENT '参数',
                                   `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron 表达式',
                                   `status` tinyint(4) DEFAULT NULL COMMENT '状态:true表示运行,false表示暂停',
                                   `remark` varchar(255) DEFAULT NULL COMMENT '备注',
                                   `create_time` datetime DEFAULT NULL COMMENT '创建日期',
                                   `create_by` varchar(128) DEFAULT NULL,
                                   `update_by` varchar(128) DEFAULT NULL,
                                   `update_time` datetime DEFAULT NULL,
                                   `delete_by` varchar(128) DEFAULT NULL,
                                   `delete_time` datetime DEFAULT NULL,
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='定时任务表';

/*Data for the table `tool_quartz_job` */

insert  into `tool_quartz_job`(`id`,`job_name`,`bean_name`,`method_name`,`method_params`,`cron_expression`,`status`,`remark`,`create_time`,`create_by`,`update_by`,`update_time`,`delete_by`,`delete_time`) values
(1,'更新访客记录','visitsTask','run',NULL,'0 0 0 * * ?',0,'每日0点创建新的访客记录','2019-01-08 14:53:31',NULL,NULL,'2019-11-12 18:41:03',NULL,NULL),
(2,'测试1','testTask','run1','test','0/5 * * * * ?',0,'带参测试，多参使用json','2019-08-22 14:08:29',NULL,NULL,'2019-11-12 18:47:24',NULL,NULL),
(3,'测试','testTask','run','','0/5 * * * * ?',0,'不带参测试','2019-09-26 16:44:39',NULL,NULL,'2019-11-08 09:41:26',NULL,NULL);

/*Table structure for table `tool_quartz_log` */

DROP TABLE IF EXISTS `tool_quartz_log`;

CREATE TABLE `tool_quartz_log` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                   `job_name` varchar(255) DEFAULT NULL COMMENT '任务名称',
                                   `bean_name` varchar(255) DEFAULT NULL COMMENT 'Bean的名称',
                                   `method_name` varchar(255) DEFAULT NULL COMMENT '方法名称',
                                   `method_params` varchar(255) DEFAULT NULL COMMENT '参数',
                                   `cron_expression` varchar(255) DEFAULT NULL COMMENT 'corn表达式',
                                   `exception` text COMMENT '异常信息',
                                   `status` tinyint(4) DEFAULT NULL COMMENT 'true表示成功,false表示失败',
                                   `cost` bigint(20) DEFAULT NULL COMMENT '耗时',
                                   `create_time` datetime DEFAULT NULL,
                                   `create_by` varchar(128) DEFAULT NULL,
                                   `delete_time` datetime DEFAULT NULL,
                                   `delete_by` varchar(128) DEFAULT NULL,
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1126 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='Job日志';

/*Data for the table `tool_quartz_log` */

insert  into `tool_quartz_log`(`id`,`job_name`,`bean_name`,`method_name`,`method_params`,`cron_expression`,`exception`,`status`,`cost`,`create_time`,`create_by`,`delete_time`,`delete_by`) values
(1001,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,20,'2019-11-08 16:37:05',NULL,'2019-11-12 17:56:26','admin'),
(1002,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,2,'2019-11-08 16:37:10',NULL,'2019-11-12 17:56:26','admin'),
(1003,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-08 16:37:15',NULL,'2019-11-12 17:56:26','admin'),
(1004,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,2,'2019-11-08 16:37:20',NULL,'2019-11-12 17:56:26','admin'),
(1005,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-08 16:37:25',NULL,'2019-11-12 17:56:26','admin'),
(1006,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,3,'2019-11-08 16:37:30',NULL,'2019-11-12 17:56:26','admin'),
(1007,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-08 16:37:35',NULL,'2019-11-12 17:56:26','admin'),
(1008,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-08 16:37:40',NULL,'2019-11-12 17:56:26','admin'),
(1009,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,2,'2019-11-08 16:37:45',NULL,'2019-11-12 17:56:26','admin'),
(1010,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-08 16:37:50',NULL,'2019-11-12 17:56:26','admin'),
(1011,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-08 16:37:55',NULL,'2019-11-12 17:56:26','admin'),
(1012,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,3,'2019-11-08 16:38:00',NULL,'2019-11-12 17:56:26','admin'),
(1013,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-08 16:38:05',NULL,'2019-11-12 17:56:26','admin'),
(1014,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-08 16:38:10',NULL,'2019-11-12 17:56:26','admin'),
(1015,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-08 16:38:15',NULL,'2019-11-12 17:56:26','admin'),
(1016,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,3,'2019-11-08 16:38:20',NULL,'2019-11-12 17:56:26','admin'),
(1017,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-08 16:38:25',NULL,'2019-11-12 17:56:26','admin'),
(1018,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-08 16:38:30',NULL,'2019-11-12 17:56:26','admin'),
(1019,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-08 16:38:35',NULL,'2019-11-12 17:56:26','admin'),
(1020,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-08 16:38:40',NULL,'2019-11-12 17:56:26','admin'),
(1021,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-08 16:38:45',NULL,'2019-11-12 17:56:26','admin'),
(1022,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-08 16:38:50',NULL,'2019-11-12 17:56:26','admin'),
(1023,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-08 16:38:55',NULL,'2019-11-12 17:56:26','admin'),
(1024,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,3,'2019-11-08 16:39:00',NULL,'2019-11-12 17:56:26','admin'),
(1025,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-08 16:39:05',NULL,'2019-11-12 17:56:26','admin'),
(1026,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-08 16:39:10',NULL,'2019-11-12 17:56:26','admin'),
(1027,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,10,'2019-11-08 16:39:15',NULL,'2019-11-12 17:56:26','admin'),
(1028,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,6,'2019-11-08 16:39:20',NULL,'2019-11-12 17:56:26','admin'),
(1029,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,449,'2019-11-08 16:39:25',NULL,'2019-11-12 17:56:26','admin'),
(1030,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,24,'2019-11-08 16:39:30',NULL,'2019-11-12 17:56:26','admin'),
(1031,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,2,'2019-11-08 16:39:35',NULL,'2019-11-12 17:56:26','admin'),
(1032,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,17,'2019-11-08 16:39:40',NULL,'2019-11-12 17:56:26','admin'),
(1033,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-08 16:39:45',NULL,'2019-11-12 17:56:26','admin'),
(1034,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-08 16:39:50',NULL,'2019-11-12 17:56:26','admin'),
(1035,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,17,'2019-11-08 16:39:55',NULL,'2019-11-12 17:56:26','admin'),
(1036,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,14,'2019-11-08 16:40:00',NULL,'2019-11-12 17:56:26','admin'),
(1037,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,15,'2019-11-08 16:40:05',NULL,'2019-11-12 17:56:26','admin'),
(1038,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,16,'2019-11-08 16:40:10',NULL,'2019-11-12 17:56:26','admin'),
(1039,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,124,'2019-11-08 16:40:16',NULL,'2019-11-12 17:56:26','admin'),
(1040,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,86,'2019-11-08 16:40:20',NULL,'2019-11-12 17:56:26','admin'),
(1041,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,6,'2019-11-08 16:40:25',NULL,'2019-11-12 17:56:26','admin'),
(1042,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-08 16:40:30',NULL,'2019-11-12 17:56:26','admin'),
(1043,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,2,'2019-11-08 16:40:35',NULL,'2019-11-12 17:56:26','admin'),
(1044,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,3,'2019-11-08 16:40:40',NULL,'2019-11-12 17:56:26','admin'),
(1045,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,10,'2019-11-08 16:40:45',NULL,'2019-11-12 17:56:26','admin'),
(1046,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,NULL,'2019-11-08 16:40:50',NULL,'2019-11-12 17:56:26','admin'),
(1047,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,NULL,'2019-11-08 16:40:55',NULL,'2019-11-12 17:56:26','admin'),
(1048,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-08 16:41:00',NULL,'2019-11-12 17:56:26','admin'),
(1049,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,NULL,'2019-11-08 16:41:05',NULL,'2019-11-12 17:56:26','admin'),
(1050,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,NULL,'2019-11-08 16:41:10',NULL,'2019-11-12 17:56:26','admin'),
(1051,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,NULL,'2019-11-08 16:41:15',NULL,'2019-11-12 17:56:26','admin'),
(1052,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,NULL,'2019-11-08 16:41:20',NULL,'2019-11-12 17:56:26','admin'),
(1053,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,2,'2019-11-12 18:41:05',NULL,NULL,NULL),
(1054,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,2,'2019-11-12 18:41:10',NULL,NULL,NULL),
(1055,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:41:15',NULL,NULL,NULL),
(1056,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,NULL,'2019-11-12 18:41:20',NULL,NULL,NULL),
(1057,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:41:25',NULL,NULL,NULL),
(1058,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:41:30',NULL,NULL,NULL),
(1059,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:41:35',NULL,NULL,NULL),
(1060,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,2,'2019-11-12 18:41:40',NULL,NULL,NULL),
(1061,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:41:45',NULL,NULL,NULL),
(1062,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:41:50',NULL,NULL,NULL),
(1063,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,12,'2019-11-12 18:41:55',NULL,NULL,NULL),
(1064,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:42:00',NULL,NULL,NULL),
(1065,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:42:05',NULL,NULL,NULL),
(1066,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:42:10',NULL,NULL,NULL),
(1067,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,212,'2019-11-12 18:42:15',NULL,NULL,NULL),
(1068,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:42:20',NULL,NULL,NULL),
(1069,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:42:25',NULL,NULL,NULL),
(1070,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,2,'2019-11-12 18:42:30',NULL,NULL,NULL),
(1071,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,NULL,'2019-11-12 18:42:35',NULL,NULL,NULL),
(1072,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:42:40',NULL,NULL,NULL),
(1073,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,2,'2019-11-12 18:42:45',NULL,NULL,NULL),
(1074,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:42:50',NULL,NULL,NULL),
(1075,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,2,'2019-11-12 18:42:55',NULL,NULL,NULL),
(1076,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,6,'2019-11-12 18:43:00',NULL,NULL,NULL),
(1077,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:43:05',NULL,NULL,NULL),
(1078,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,2,'2019-11-12 18:43:10',NULL,NULL,NULL),
(1079,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,NULL,'2019-11-12 18:43:15',NULL,NULL,NULL),
(1080,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:43:20',NULL,NULL,NULL),
(1081,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:43:25',NULL,NULL,NULL),
(1082,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:43:30',NULL,NULL,NULL),
(1083,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:43:35',NULL,NULL,NULL),
(1084,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:43:40',NULL,NULL,NULL),
(1085,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:43:45',NULL,NULL,NULL),
(1086,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:43:50',NULL,NULL,NULL),
(1087,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,3,'2019-11-12 18:43:55',NULL,NULL,NULL),
(1088,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,2,'2019-11-12 18:44:00',NULL,NULL,NULL),
(1089,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:44:05',NULL,NULL,NULL),
(1090,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:44:10',NULL,NULL,NULL),
(1091,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:44:15',NULL,NULL,NULL),
(1092,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:44:20',NULL,NULL,NULL),
(1093,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,2,'2019-11-12 18:44:25',NULL,NULL,NULL),
(1094,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:44:30',NULL,NULL,NULL),
(1095,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:44:35',NULL,NULL,NULL),
(1096,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:44:40',NULL,NULL,NULL),
(1097,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,55,'2019-11-12 18:44:45',NULL,NULL,NULL),
(1098,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,2,'2019-11-12 18:44:50',NULL,NULL,NULL),
(1099,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:44:55',NULL,NULL,NULL),
(1100,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,18,'2019-11-12 18:45:00',NULL,NULL,NULL),
(1101,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:45:05',NULL,NULL,NULL),
(1102,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:45:10',NULL,NULL,NULL),
(1103,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,NULL,'2019-11-12 18:45:15',NULL,NULL,NULL),
(1104,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,NULL,'2019-11-12 18:45:20',NULL,NULL,NULL),
(1105,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,NULL,'2019-11-12 18:45:25',NULL,NULL,NULL),
(1106,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,NULL,'2019-11-12 18:45:30',NULL,NULL,NULL),
(1107,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,13,'2019-11-12 18:45:35',NULL,NULL,NULL),
(1108,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,4,'2019-11-12 18:45:55',NULL,NULL,NULL),
(1109,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:46:00',NULL,NULL,NULL),
(1110,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:46:05',NULL,NULL,NULL),
(1111,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:46:10',NULL,NULL,NULL),
(1112,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,2,'2019-11-12 18:46:15',NULL,NULL,NULL),
(1113,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:46:20',NULL,NULL,NULL),
(1114,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,2,'2019-11-12 18:46:25',NULL,NULL,NULL),
(1115,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:46:30',NULL,NULL,NULL),
(1116,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:46:35',NULL,NULL,NULL),
(1117,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:46:40',NULL,NULL,NULL),
(1118,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:46:45',NULL,NULL,NULL),
(1119,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:46:50',NULL,NULL,NULL),
(1120,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,29,'2019-11-12 18:46:55',NULL,NULL,NULL),
(1121,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:47:00',NULL,NULL,NULL),
(1122,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,2,'2019-11-12 18:47:05',NULL,NULL,NULL),
(1123,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,2,'2019-11-12 18:47:10',NULL,NULL,NULL),
(1124,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:47:15',NULL,NULL,NULL),
(1125,'测试1','testTask','run1','test','0/5 * * * * ?',NULL,1,1,'2019-11-12 18:47:20',NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
