/*
SQLyog Enterprise v13.1.1 (64 bit)
MySQL - 5.7.24-0ubuntu0.16.04.1-log : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `test`;

/*Table structure for table `bg_comment` */

DROP TABLE IF EXISTS `bg_comment`;

CREATE TABLE `bg_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(128) DEFAULT NULL COMMENT 'QQ昵称',
  `qq_num` varchar(128) DEFAULT NULL COMMENT 'QQ号码',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱地址',
  `avatar` varchar(128) DEFAULT NULL COMMENT 'QQ头像地址',
  `content` varchar(1024) DEFAULT NULL COMMENT '留言',
  `reply_id` int(11) DEFAULT NULL COMMENT '回复的上一个留言的id',
  `notice` int(1) DEFAULT NULL COMMENT '收到评论是否邮件通知',
  `url` varchar(128) DEFAULT NULL COMMENT '评论的页面地址',
  `page_id` int(11) DEFAULT NULL COMMENT '页面id',
  `good` int(11) DEFAULT NULL COMMENT '点赞数',
  `bad` int(11) DEFAULT NULL COMMENT '踩数',
  `ip` varchar(128) DEFAULT NULL COMMENT 'IP 地址',
  `location` varchar(128) DEFAULT NULL COMMENT '地理位置',
  `os` varchar(128) DEFAULT NULL COMMENT '操作系统',
  `browser` varchar(128) DEFAULT NULL COMMENT '浏览器类型',
  `create_by` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(128) DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='留言';

/*Data for the table `bg_comment` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
