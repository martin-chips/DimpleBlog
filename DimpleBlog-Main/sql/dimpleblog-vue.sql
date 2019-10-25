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
  `create_by` varchar(150) DEFAULT NULL,
  `update_by` varchar(150) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_by` varchar(150) DEFAULT NULL,
  `delete_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客表';

/*Data for the table `bg_blog` */

/*Table structure for table `gen_table` */

DROP TABLE IF EXISTS `gen_table`;

CREATE TABLE `gen_table` (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) DEFAULT '' COMMENT '表描述',
  `class_name` varchar(100) DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) DEFAULT NULL COMMENT '生成功能作者',
  `options` varchar(1000) DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代码生成业务表';

/*Data for the table `gen_table` */

/*Table structure for table `gen_table_column` */

DROP TABLE IF EXISTS `gen_table_column`;

CREATE TABLE `gen_table_column` (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) DEFAULT '' COMMENT '字典类型',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代码生成业务表字段';

/*Data for the table `gen_table_column` */

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='系统黑名单';

/*Data for the table `sys_blacklist` */

insert  into `sys_blacklist`(`id`,`ip`,`description`,`intercept_count`,`last_access_url`,`last_access_time`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`) values 
(1,'11321','1',NULL,NULL,NULL,'admin','2019-10-24 18:39:37',NULL,NULL,NULL,NULL),
(2,'45640','232',NULL,NULL,NULL,'admin','2019-10-24 18:44:05',NULL,NULL,NULL,NULL),
(3,'45640','232',NULL,NULL,NULL,'admin','2019-10-24 18:44:05',NULL,NULL,NULL,NULL);

/*Table structure for table `sys_config` */

DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config` (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `delete_by` varchar(64) DEFAULT '',
  `delete_time` datetime DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='参数配置表';

/*Data for the table `sys_config` */

insert  into `sys_config`(`config_id`,`config_name`,`config_key`,`config_value`,`config_type`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`,`remark`) values 
(1,'主框架页-默认皮肤样式名称','sys.index.skinName','skin-blue','Y','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow'),
(2,'用户管理-账号初始密码','sys.user.initPassword','123456','Y','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'初始化密码 123456'),
(3,'主框架页-侧边栏主题','sys.index.sideTheme','theme-dark','Y','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'深色主题theme-dark，浅色主题theme-light'),
(4,'12112','12121','12121','Y','admin','2019-10-23 14:41:55','',NULL,'admin','2019-10-23 14:43:06',NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='字典数据表';

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
(28,2,'失败','1','sys_common_status','','danger','N','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'停用状态');

/*Table structure for table `sys_dict_type` */

DROP TABLE IF EXISTS `sys_dict_type`;

CREATE TABLE `sys_dict_type` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
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
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='字典类型表';

/*Data for the table `sys_dict_type` */

insert  into `sys_dict_type`(`dict_id`,`dict_name`,`dict_type`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`,`remark`) values 
(1,'用户性别','sys_user_sex','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'用户性别列表'),
(2,'菜单状态','sys_show_hide','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'菜单状态列表'),
(3,'系统开关','sys_normal_disable','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'系统开关列表'),
(4,'任务状态','sys_job_status','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'任务状态列表'),
(5,'任务分组','sys_job_group','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'任务分组列表'),
(6,'系统是否','sys_yes_no','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'系统是否列表'),
(7,'通知类型','sys_notice_type','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'通知类型列表'),
(8,'通知状态','sys_notice_status','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'通知状态列表'),
(9,'操作类型','sys_oper_type','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'操作类型列表'),
(10,'系统状态','sys_common_status','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'登录状态列表');

/*Table structure for table `sys_job` */

DROP TABLE IF EXISTS `sys_job`;

CREATE TABLE `sys_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `delete_by` varchar(64) DEFAULT '' COMMENT '刪除者',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`,`job_name`,`job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='定时任务调度表';

/*Data for the table `sys_job` */

insert  into `sys_job`(`job_id`,`job_name`,`job_group`,`invoke_target`,`cron_expression`,`misfire_policy`,`concurrent`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`,`remark`) values 
(1,'系统默认（无参）','DEFAULT','ryTask.ryNoParams','0/10 * * * * ?','3','1','1','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(2,'系统默认（有参）','DEFAULT','ryTask.ryParams(\'ry\')','0/15 * * * * ?','3','1','1','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,''),
(3,'系统默认（多参）','DEFAULT','ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)','0/20 * * * * ?','3','1','1','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'');

/*Table structure for table `sys_job_log` */

DROP TABLE IF EXISTS `sys_job_log`;

CREATE TABLE `sys_job_log` (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) DEFAULT NULL COMMENT '日志信息',
  `status` char(1) DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) DEFAULT '' COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务调度日志表';

/*Data for the table `sys_job_log` */

/*Table structure for table `sys_login_log` */

DROP TABLE IF EXISTS `sys_login_log`;

CREATE TABLE `sys_login_log` (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8 COMMENT='系统访问记录';

/*Data for the table `sys_login_log` */

insert  into `sys_login_log`(`info_id`,`user_name`,`ipaddr`,`login_location`,`browser`,`os`,`status`,`msg`,`login_time`) values 
(100,'admin','127.0.0.1','内网IP','Chrome','Windows 10','1','验证码已失效','2019-10-10 15:57:28'),
(101,'admin','127.0.0.1','内网IP','Chrome','Windows 10','1','用户不存在/密码错误','2019-10-10 15:57:32'),
(102,'admin','127.0.0.1','内网IP','Chrome','Windows 10','0','登录成功','2019-10-10 15:57:41'),
(103,'admin','127.0.0.1','内网IP','Chrome','Windows 10','1','用户不存在/密码错误','2019-10-22 16:38:23'),
(104,'admin','127.0.0.1','内网IP','Chrome','Windows 10','1','验证码已失效','2019-10-22 16:38:31'),
(105,'admin','127.0.0.1','内网IP','Chrome','Windows 10','0','登录成功','2019-10-22 16:38:35'),
(106,'admin','127.0.0.1','内网IP','Chrome','Windows 10','1','用户不存在/密码错误','2019-10-22 18:07:57'),
(107,'admin','127.0.0.1','内网IP','Chrome','Windows 10','1','验证码已失效','2019-10-22 18:08:09'),
(108,'admin','127.0.0.1','内网IP','Chrome','Windows 10','1','验证码已失效','2019-10-22 18:08:13'),
(109,'admin','127.0.0.1','内网IP','Chrome','Windows 10','0','登录成功','2019-10-22 18:08:19'),
(110,'admin','127.0.0.1','内网IP','Chrome','Windows 10','1','用户不存在/密码错误','2019-10-23 10:13:51'),
(111,'admin','127.0.0.1','内网IP','Chrome','Windows 10','0','登录成功','2019-10-23 10:14:02'),
(112,'admin','127.0.0.1','内网IP','Chrome','Windows 10','0','登录成功','2019-10-23 10:21:34'),
(113,'admin','127.0.0.1','内网IP','Chrome','Windows 10','0','登录成功','2019-10-23 11:04:40'),
(114,'admin','127.0.0.1','内网IP','Chrome','Windows 10','0','登录成功','2019-10-23 13:56:10'),
(115,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2019-10-23 18:00:56'),
(116,'admin','10.17.80.181','内网IP','Chrome 8','Windows 10','0','登录成功','2019-10-23 18:02:10'),
(117,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2019-10-24 10:18:51'),
(118,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2019-10-24 10:24:15'),
(119,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2019-10-24 12:07:48'),
(120,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2019-10-24 13:51:55'),
(121,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2019-10-24 13:51:59'),
(122,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2019-10-24 13:52:04'),
(123,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2019-10-24 15:48:19'),
(124,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2019-10-24 15:48:24'),
(125,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2019-10-24 17:57:34'),
(126,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2019-10-25 14:17:32'),
(127,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2019-10-25 14:28:32'),
(128,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','退出成功','2019-10-25 14:43:36'),
(129,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','1','验证码已失效','2019-10-25 14:44:34'),
(130,'admin','127.0.0.1','内网IP','Chrome 8','Windows 10','0','登录成功','2019-10-25 14:44:40');

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
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
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1072 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`is_frame`,`menu_type`,`visible`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`,`remark`) values 
(1,'系统管理',0,5,'system',NULL,1,'M','0','','system','admin','2018-03-16 11:33:00','admin','2019-10-24 10:33:09','',NULL,'系统管理目录'),
(2,'系统监控',0,4,'monitor',NULL,1,'M','0','','monitor','admin','2018-03-16 11:33:00','admin','2019-10-24 10:33:16','',NULL,'系统监控目录'),
(3,'系统工具',0,3,'tool',NULL,1,'M','0','','tool','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','admin','2019-10-24 16:56:08','系统工具目录'),
(100,'用户管理',1,1,'user','system/user/index',1,'C','0','system:user:list','user','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'用户管理菜单'),
(101,'角色管理',1,2,'role','system/role/index',1,'C','0','system:role:list','peoples','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'角色管理菜单'),
(102,'菜单管理',1,3,'menu','system/menu/index',1,'C','0','system:menu:list','tree-table','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'菜单管理菜单'),
(105,'字典管理',1,6,'dict','system/dict/index',1,'C','0','system:dict:list','dict','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'字典管理菜单'),
(106,'参数设置',1,7,'config','system/config/index',1,'C','0','system:config:list','edit','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'参数设置菜单'),
(107,'通知公告',1,8,'notice','system/notice/index',1,'C','0','system:notice:list','message','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'通知公告菜单'),
(108,'日志管理',2,9,'log','system/log/index',1,'M','0','','log','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'日志管理菜单'),
(109,'在线用户',2,1,'online','monitor/online/index',1,'C','0','monitor:online:list','online','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'在线用户菜单'),
(110,'定时任务',2,2,'job','monitor/job/index',1,'C','0','monitor:job:list','job','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'定时任务菜单'),
(111,'数据监控',2,3,'druid','monitor/druid/index',1,'C','0','monitor:druid:list','druid','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'数据监控菜单'),
(112,'服务监控',2,4,'server','monitor/server/index',1,'C','0','monitor:server:list','server','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'服务监控菜单'),
(113,'表单构建',3,1,'build','tool/build/index',1,'C','0','tool:build:list','build','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','admin','2019-10-24 16:49:46','表单构建菜单'),
(114,'代码生成',3,2,'server','tool/gen/index',1,'C','0','tool:gen:list','code','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','admin','2019-10-24 16:53:53','代码生成菜单'),
(115,'系统接口',2,3,'swagger','monitor/swagger/index',1,'C','0','tool:swagger:list','swagger','admin','2018-03-16 11:33:00','admin','2019-10-24 10:21:02','',NULL,'系统接口菜单'),
(500,'操作日志',108,1,'operateLog','monitor/operateLog/index',1,'C','0','monitor:operlog:list','form','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'操作日志菜单'),
(501,'登录日志',108,2,'loginLog','monitor/loginLog/index',1,'C','0','monitor:logininfor:list','logininfor','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'登录日志菜单'),
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
(1055,'生成查询',114,1,'#','',1,'F','0','tool:gen:query','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','admin','2019-10-24 16:49:54',''),
(1056,'生成修改',114,2,'#','',1,'F','0','tool:gen:edit','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','admin','2019-10-24 16:49:56',''),
(1057,'生成删除',114,3,'#','',1,'F','0','tool:gen:remove','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','admin','2019-10-24 16:49:57',''),
(1058,'预览代码',114,4,'#','',1,'F','0','tool:gen:preview','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','admin','2019-10-24 16:49:59',''),
(1059,'生成代码',114,5,'#','',1,'F','0','tool:gen:code','#','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','admin','2019-10-24 16:50:01',''),
(1066,'博客管理',0,1,'',NULL,1,'M','0',NULL,'nested','admin','2019-10-24 10:25:45','',NULL,'',NULL,''),
(1067,'博文管理',1066,1,'/blog/blog','blog/blog/index',1,'C','0','blog:blog:list','dict','admin','2019-10-24 10:26:41','admin','2019-10-24 14:39:17','',NULL,''),
(1068,'评论管理',1066,4,'/blog/comment','blog/comment/index',1,'C','0','blog:comment:list','component','admin','2019-10-24 10:31:20','',NULL,'',NULL,''),
(1069,'类别管理',1066,2,'/blog/category','blog/category/index',1,'C','0','blog:category:list','download','admin','2019-10-24 10:32:17','',NULL,'',NULL,''),
(1070,'标签管理',1066,3,'/blog/tag','blog/tag/index',1,'C','0','blog:tag:list','education','admin','2019-10-24 10:32:49','',NULL,'',NULL,''),
(1071,'黑名单',2,6,'/monitor/blacklist','monitor/blacklist/index',1,'C','0','monitor:blacklist:list','drag','admin','2019-10-24 16:49:08','',NULL,'',NULL,'');

/*Table structure for table `sys_notice` */

DROP TABLE IF EXISTS `sys_notice`;

CREATE TABLE `sys_notice` (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
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
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='通知公告表';

/*Data for the table `sys_notice` */

insert  into `sys_notice`(`notice_id`,`notice_title`,`notice_type`,`notice_content`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`,`remark`) values 
(1,'温馨提醒：2018-07-01 若依新版本发布啦','2','<p>新版本内容1123<img src=\"http://localhost:8080/profile/upload/2019/10/10/35492d57cfc519445c1dfcb6119a9c1a.jpg\"></p>','0','admin','2018-03-16 11:33:00','admin','2019-10-10 15:58:20','',NULL,'管理员'),
(2,'维护通知：2018-07-01 若依系统凌晨维护','1','维护内容','0','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'管理员');

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
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=186 DEFAULT CHARSET=utf8 COMMENT='操作日志记录';

/*Data for the table `sys_operate_log` */

insert  into `sys_operate_log`(`id`,`title`,`business_type`,`method`,`request_method`,`operator_type`,`operate_name`,`url`,`ip`,`location`,`param`,`json_result`,`status`,`error_msg`,`operate_time`) values
(102,'部门管理',2,'com.dimple.project.system.controller.SysDeptController.edit()','PUT',1,'admin','/system/dept','127.0.0.1','内网IP','{\"deptName\":\"研发部门\",\"leader\":\"若依\",\"deptId\":103,\"orderNum\":\"3\",\"delFlag\":\"0\",\"params\":{},\"parentId\":101,\"createBy\":\"admin\",\"children\":[],\"createTime\":1521171180000,\"phone\":\"15888888888\",\"updateBy\":\"admin\",\"ancestors\":\"0,100,101\",\"email\":\"123@qq.com\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-22 18:08:48'),
(103,'在线用户',3,'com.dimple.project.monitor.controller.SysUserOnlineController.forceLogout()','DELETE',1,NULL,'/monitor/online/6b63b515-4967-4998-a05f-167833b8db6e','127.0.0.1','内网IP','{tokenId=6b63b515-4967-4998-a05f-167833b8db6e}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 10:14:18'),
(104,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/103','127.0.0.1','内网IP','{menuId=103}','{\"msg\":\"存在子菜单,不允许删除\",\"code\":500}',0,NULL,'2019-10-23 11:05:26'),
(105,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/1017','127.0.0.1','内网IP','{menuId=1017}','{\"msg\":\"菜单已分配,不允许删除\",\"code\":500}',0,NULL,'2019-10-23 11:05:31'),
(106,'角色管理',2,'com.dimple.project.system.controller.SysRoleController.edit()','PUT',1,'admin','/system/role','127.0.0.1','内网IP','{\"flag\":false,\"roleId\":2,\"admin\":false,\"remark\":\"普通角色\",\"dataScope\":\"2\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"2\",\"createTime\":1521171180000,\"updateBy\":\"admin\",\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"menuIds\":[2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,3,113,114,1055,1056,1057,1058,1059,115,4],\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 11:05:52'),
(107,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/1017','127.0.0.1','内网IP','{menuId=1017}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 11:06:08'),
(108,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/1018','127.0.0.1','内网IP','{menuId=1018}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 11:06:11'),
(109,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/1019','127.0.0.1','内网IP','{menuId=1019}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 11:06:13'),
(110,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/1020','127.0.0.1','内网IP','{menuId=1020}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 11:06:16'),
(111,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/103','127.0.0.1','内网IP','{menuId=103}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 11:06:19'),
(112,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/1021','127.0.0.1','内网IP','{menuId=1021}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 11:06:25'),
(113,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/1022','127.0.0.1','内网IP','{menuId=1022}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 11:06:29'),
(114,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/1023','127.0.0.1','内网IP','{menuId=1023}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 11:06:31'),
(115,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/1024','127.0.0.1','内网IP','{menuId=1024}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 11:06:34'),
(116,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/1025','127.0.0.1','内网IP','{menuId=1025}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 11:06:37'),
(117,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/104','127.0.0.1','内网IP','{menuId=104}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 11:06:40'),
(118,'用户管理',2,'com.dimple.project.system.controller.SysUserController.resetPwd()','PUT',1,'admin','/system/user/resetPwd','127.0.0.1','内网IP','{\"admin\":false,\"params\":{},\"userId\":2,\"password\":\"$2a$10$2812FRZsYtEDSw8tZX060uJF57iL.3L561feptCCby.2pc9ZeyXqe\",\"updateBy\":\"admin\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 14:09:48'),
(119,'参数管理',1,'com.dimple.project.system.controller.SysConfigController.add()','POST',1,'admin','/system/config','127.0.0.1','内网IP','{\"configName\":\"12112\",\"configType\":\"Y\",\"configValue\":\"12121\",\"params\":{},\"configKey\":\"12121\",\"createBy\":\"admin\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 14:41:55'),
(120,'参数管理',3,'com.dimple.project.system.controller.SysConfigController.remove()','DELETE',1,'admin','/system/config/4','127.0.0.1','内网IP','{configId=4}','null',1,'\r\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Truncated incorrect DOUBLE value: \'admin\'\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: update sys_config set delete_by =? and delete_time= sysdate()  where config_id = ?\r\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Truncated incorrect DOUBLE value: \'admin\'\n; Data truncation: Truncated incorrect DOUBLE value: \'admin\'; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Truncated incorrect DOUBLE value: \'admin\'','2019-10-23 14:41:59'),
(121,'参数管理',3,'com.dimple.project.system.controller.SysConfigController.remove()','DELETE',1,'admin','/system/config/4','127.0.0.1','内网IP','{configId=4}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 14:43:06'),
(122,'角色管理',1,'com.dimple.project.system.controller.SysRoleController.add()','POST',1,'admin','/system/role','127.0.0.1','内网IP','{\"flag\":false,\"roleId\":3,\"admin\":false,\"params\":{},\"roleSort\":\"1\",\"createBy\":\"admin\",\"roleKey\":\"11\",\"roleName\":\"111\",\"menuIds\":[2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112],\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 15:45:44'),
(123,'角色管理',2,'com.dimple.project.system.controller.SysRoleController.changeStatus()','PUT',1,'admin','/system/role/changeStatus','127.0.0.1','内网IP','{\"flag\":false,\"roleId\":3,\"admin\":false,\"params\":{},\"updateBy\":\"admin\",\"status\":\"1\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 15:46:07'),
(124,'角色管理',2,'com.dimple.project.system.controller.SysRoleController.changeStatus()','PUT',1,'admin','/system/role/changeStatus','127.0.0.1','内网IP','{\"flag\":false,\"roleId\":3,\"admin\":false,\"params\":{},\"updateBy\":\"admin\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 15:46:13'),
(125,'角色管理',3,'com.dimple.project.system.controller.SysRoleController.remove()','DELETE',1,'admin','/system/role/3','127.0.0.1','内网IP','{roleId=3}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 15:46:18'),
(126,'角色管理',2,'com.dimple.project.system.controller.SysRoleController.edit()','PUT',1,'admin','/system/role','127.0.0.1','内网IP','{\"flag\":false,\"roleId\":1,\"admin\":true,\"remark\":\"管理员\",\"params\":{},\"roleSort\":\"1\",\"createTime\":1521171180000,\"roleKey\":\"admin\",\"roleName\":\"管理员\",\"menuIds\":[2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112],\"status\":\"0\"}','null',1,'不允许操作超级管理员角色','2019-10-23 15:46:32'),
(127,'角色管理',2,'com.dimple.project.system.controller.SysRoleController.edit()','PUT',1,'admin','/system/role','127.0.0.1','内网IP','{\"flag\":false,\"roleId\":2,\"admin\":false,\"remark\":\"普通角色\",\"params\":{},\"roleSort\":\"2\",\"createTime\":1521171180000,\"updateBy\":\"admin\",\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"menuIds\":[1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,108,500,1040,1041,1042,501,1043,1044,1045,3,113,114,1055,1056,1057,1058,1059,115,4],\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 15:46:38'),
(128,'菜单管理',1,'com.dimple.project.system.controller.SysMenuController.add()','POST',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"theme\",\"orderNum\":\"1\",\"menuName\":\"uyt\",\"params\":{},\"path\":\"11\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"M\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 16:04:53'),
(129,'菜单管理',2,'com.dimple.project.system.controller.SysMenuController.edit()','PUT',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"1\",\"icon\":\"theme\",\"orderNum\":\"1\",\"menuName\":\"uyt\",\"params\":{},\"parentId\":0,\"path\":\"11\",\"children\":[],\"createTime\":1571817893000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1060,\"menuType\":\"M\",\"perms\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 16:05:09'),
(130,'角色管理',2,'com.dimple.project.system.controller.SysRoleController.edit()','PUT',1,'admin','/system/role','127.0.0.1','内网IP','{\"flag\":false,\"roleId\":1,\"admin\":true,\"remark\":\"管理员\",\"params\":{},\"roleSort\":\"1\",\"createTime\":1521171180000,\"roleKey\":\"admin\",\"roleName\":\"管理员\",\"menuIds\":[1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,108,500,1040,1041,1042,501,1043,1044,1045,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,3,113,114,1055,1056,1057,1058,1059,115,4],\"status\":\"0\"}','null',1,'不允许操作超级管理员角色','2019-10-23 16:07:46'),
(131,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/1060','127.0.0.1','内网IP','{menuId=1060}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 16:09:50'),
(132,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/1','127.0.0.1','内网IP','{menuId=1}','{\"msg\":\"存在子菜单,不允许删除\",\"code\":500}',0,NULL,'2019-10-23 16:09:55'),
(133,'用户管理',3,'com.dimple.project.system.controller.SysUserController.remove()','DELETE',1,'admin','/system/user/3','127.0.0.1','内网IP','{userId=3}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 16:52:26'),
(134,'菜单管理',1,'com.dimple.project.system.controller.SysMenuController.add()','POST',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"chart\",\"orderNum\":\"1\",\"menuName\":\"11\",\"params\":{},\"component\":\"/blog/example/create\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"C\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 17:18:55'),
(135,'菜单管理',2,'com.dimple.project.system.controller.SysMenuController.edit()','PUT',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"chart\",\"orderNum\":\"1\",\"menuName\":\"11\",\"params\":{},\"parentId\":0,\"path\":\"/ll\",\"component\":\"/blog/example/create\",\"children\":[],\"createTime\":1571822335000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1061,\"menuType\":\"C\",\"perms\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 17:27:23'),
(136,'菜单管理',2,'com.dimple.project.system.controller.SysMenuController.edit()','PUT',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"chart\",\"orderNum\":\"1\",\"menuName\":\"11\",\"params\":{},\"parentId\":0,\"path\":\"/ll\",\"component\":\"/blog/create\",\"children\":[],\"createTime\":1571822335000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1061,\"menuType\":\"C\",\"perms\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 17:28:29'),
(137,'菜单管理',2,'com.dimple.project.system.controller.SysMenuController.edit()','PUT',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"chart\",\"orderNum\":\"1\",\"menuName\":\"11\",\"params\":{},\"parentId\":0,\"path\":\"/ll\",\"component\":\"/blog/create\",\"children\":[],\"createTime\":1571822335000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1061,\"menuType\":\"M\",\"perms\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 17:30:12'),
(138,'菜单管理',2,'com.dimple.project.system.controller.SysMenuController.edit()','PUT',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"chart\",\"orderNum\":\"1\",\"menuName\":\"test\",\"params\":{},\"parentId\":0,\"path\":\"/test\",\"component\":\"/blog/create\",\"children\":[],\"createTime\":1571822335000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1061,\"menuType\":\"M\",\"perms\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 17:30:39'),
(139,'菜单管理',2,'com.dimple.project.system.controller.SysMenuController.edit()','PUT',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"chart\",\"orderNum\":\"1\",\"menuName\":\"test\",\"params\":{},\"parentId\":0,\"path\":\"/test\",\"component\":\"\",\"children\":[],\"createTime\":1571822335000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1061,\"menuType\":\"M\",\"perms\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 17:31:14'),
(140,'菜单管理',2,'com.dimple.project.system.controller.SysMenuController.edit()','PUT',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"chart\",\"orderNum\":\"1\",\"menuName\":\"test\",\"params\":{},\"parentId\":0,\"path\":\"/test\",\"component\":\"\",\"children\":[],\"createTime\":1571822335000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1061,\"menuType\":\"C\",\"perms\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 17:31:25'),
(141,'菜单管理',2,'com.dimple.project.system.controller.SysMenuController.edit()','PUT',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"chart\",\"orderNum\":\"1\",\"menuName\":\"test\",\"params\":{},\"parentId\":0,\"path\":\"/test\",\"component\":\"\",\"children\":[],\"createTime\":1571822335000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1061,\"menuType\":\"C\",\"perms\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 17:31:30'),
(142,'菜单管理',2,'com.dimple.project.system.controller.SysMenuController.edit()','PUT',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"chart\",\"orderNum\":\"1\",\"menuName\":\"test\",\"params\":{},\"parentId\":0,\"path\":\"/test2\",\"component\":\"/blog/create\",\"children\":[],\"createTime\":1571822335000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1061,\"menuType\":\"C\",\"perms\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 17:31:56'),
(143,'菜单管理',2,'com.dimple.project.system.controller.SysMenuController.edit()','PUT',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"chart\",\"orderNum\":\"1\",\"menuName\":\"test\",\"params\":{},\"parentId\":0,\"path\":\"/test\\\\\",\"component\":\"/blog/create\",\"children\":[],\"createTime\":1571822335000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1061,\"menuType\":\"C\",\"perms\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 17:32:16'),
(144,'菜单管理',2,'com.dimple.project.system.controller.SysMenuController.edit()','PUT',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"chart\",\"orderNum\":\"1\",\"menuName\":\"test\",\"params\":{},\"parentId\":0,\"path\":\"/test\\\\\",\"component\":\"\",\"children\":[],\"createTime\":1571822335000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1061,\"menuType\":\"C\",\"perms\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 17:32:57'),
(145,'菜单管理',2,'com.dimple.project.system.controller.SysMenuController.edit()','PUT',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"chart\",\"orderNum\":\"1\",\"menuName\":\"test\",\"params\":{},\"parentId\":0,\"path\":\"/test\\\\\",\"component\":\"\",\"children\":[],\"createTime\":1571822335000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1061,\"menuType\":\"C\",\"perms\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 17:32:57'),
(146,'菜单管理',1,'com.dimple.project.system.controller.SysMenuController.add()','POST',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"component\",\"orderNum\":\"1\",\"menuName\":\"测试\",\"params\":{},\"parentId\":1061,\"path\":\"/blog\",\"component\":\"/blog/create\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"C\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 17:33:56'),
(147,'菜单管理',2,'com.dimple.project.system.controller.SysMenuController.edit()','PUT',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"chart\",\"orderNum\":\"1\",\"menuName\":\"test\",\"params\":{},\"parentId\":0,\"path\":\"/test\\\\\",\"children\":[],\"createTime\":1571822335000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1061,\"menuType\":\"M\",\"perms\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 17:34:24'),
(148,'菜单管理',1,'com.dimple.project.system.controller.SysMenuController.add()','POST',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"dict\",\"orderNum\":\"1\",\"menuName\":\"测试\",\"params\":{},\"parentId\":1061,\"component\":\"/test/index\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"C\"}','{\"msg\":\"新增菜单\'测试\'失败，菜单名称已存在\",\"code\":500}',0,NULL,'2019-10-23 17:35:50'),
(149,'菜单管理',1,'com.dimple.project.system.controller.SysMenuController.add()','POST',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"dict\",\"orderNum\":\"1\",\"menuName\":\"测试1\",\"params\":{},\"parentId\":1061,\"component\":\"/test/index\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"C\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 17:35:54'),
(150,'菜单管理',1,'com.dimple.project.system.controller.SysMenuController.add()','POST',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"dashboard\",\"orderNum\":\"6\",\"menuName\":\"测试\",\"params\":{},\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"M\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 17:55:42'),
(151,'菜单管理',1,'com.dimple.project.system.controller.SysMenuController.add()','POST',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"chart\",\"orderNum\":\"1\",\"menuName\":\"blog\",\"params\":{},\"parentId\":1064,\"component\":\"test/blog/index\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"C\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-23 17:59:11'),
(152,'菜单管理',2,'com.dimple.project.system.controller.SysMenuController.edit()','PUT',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"swagger\",\"orderNum\":\"3\",\"menuName\":\"系统接口\",\"params\":{},\"parentId\":2,\"path\":\"swagger\",\"component\":\"tool/swagger/index\",\"children\":[],\"createTime\":1521171180000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":115,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 10:20:14'),
(153,'菜单管理',2,'com.dimple.project.system.controller.SysMenuController.edit()','PUT',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"swagger\",\"orderNum\":\"3\",\"menuName\":\"系统接口\",\"params\":{},\"parentId\":2,\"path\":\"swagger\",\"component\":\"monitor/swagger/index\",\"children\":[],\"createTime\":1521171180000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":115,\"menuType\":\"C\",\"perms\":\"tool:swagger:list\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 10:21:02'),
(154,'菜单管理',1,'com.dimple.project.system.controller.SysMenuController.add()','POST',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"nested\",\"orderNum\":\"1\",\"menuName\":\"博客管理\",\"params\":{},\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"M\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 10:25:45'),
(155,'菜单管理',1,'com.dimple.project.system.controller.SysMenuController.add()','POST',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"dict\",\"orderNum\":\"1\",\"menuName\":\"博文管理\",\"params\":{},\"parentId\":1066,\"path\":\"/blog/blog\",\"component\":\"blog/blog/index\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"C\",\"perms\":\"blog:blog:list\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 10:26:41'),
(156,'菜单管理',1,'com.dimple.project.system.controller.SysMenuController.add()','POST',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"component\",\"orderNum\":\"4\",\"menuName\":\"评论管理\",\"params\":{},\"parentId\":1066,\"path\":\"/blog/comment\",\"component\":\"blog/comment/index\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"C\",\"perms\":\"blog:comment:list\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 10:31:20'),
(157,'菜单管理',1,'com.dimple.project.system.controller.SysMenuController.add()','POST',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"download\",\"orderNum\":\"2\",\"menuName\":\"类别管理\",\"params\":{},\"parentId\":1066,\"path\":\"/blog/category\",\"component\":\"blog/category/index\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"C\",\"perms\":\"blog:category:list\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 10:32:17'),
(158,'菜单管理',1,'com.dimple.project.system.controller.SysMenuController.add()','POST',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"education\",\"orderNum\":\"3\",\"menuName\":\"标签管理\",\"params\":{},\"parentId\":1066,\"path\":\"/blog/tag\",\"component\":\"blog/tag/index\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"C\",\"perms\":\"blog:tag:list\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 10:32:49'),
(159,'菜单管理',2,'com.dimple.project.system.controller.SysMenuController.edit()','PUT',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"system\",\"orderNum\":\"5\",\"menuName\":\"系统管理\",\"params\":{},\"parentId\":0,\"path\":\"system\",\"children\":[],\"createTime\":1521171180000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1,\"menuType\":\"M\",\"perms\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 10:33:09'),
(160,'菜单管理',2,'com.dimple.project.system.controller.SysMenuController.edit()','PUT',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"monitor\",\"orderNum\":\"4\",\"menuName\":\"系统监控\",\"params\":{},\"parentId\":0,\"path\":\"monitor\",\"children\":[],\"createTime\":1521171180000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2,\"menuType\":\"M\",\"perms\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 10:33:16'),
(161,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/4','127.0.0.1','内网IP','{menuId=4}','{\"msg\":\"菜单已分配,不允许删除\",\"code\":500}',0,NULL,'2019-10-24 10:33:20'),
(162,'菜单管理',2,'com.dimple.project.system.controller.SysMenuController.edit()','PUT',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"dict\",\"orderNum\":\"1\",\"menuName\":\"博文管理\",\"params\":{},\"parentId\":1066,\"path\":\"/blog/blog\",\"component\":\"blog/blog/create\",\"children\":[],\"createTime\":1571884001000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1067,\"menuType\":\"C\",\"perms\":\"blog:blog:list\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 10:36:59'),
(163,'菜单管理',2,'com.dimple.project.system.controller.SysMenuController.edit()','PUT',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"dict\",\"orderNum\":\"1\",\"menuName\":\"博文管理\",\"params\":{},\"parentId\":1066,\"path\":\"/blog/blog\",\"component\":\"blog/blog/index\",\"children\":[],\"createTime\":1571884001000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1067,\"menuType\":\"C\",\"perms\":\"blog:blog:list\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 14:39:17'),
(164,'角色管理',2,'com.dimple.project.system.controller.SysRoleController.changeStatus()','PUT',1,'admin','/system/role/changeStatus','127.0.0.1','内网IP','{\"flag\":false,\"roleId\":1,\"admin\":true,\"params\":{},\"status\":\"1\"}','null',1,'不允许操作超级管理员角色','2019-10-24 16:45:36'),
(165,'角色管理',2,'com.dimple.project.system.controller.SysRoleController.changeStatus()','PUT',1,'admin','/system/role/changeStatus','127.0.0.1','内网IP','{\"flag\":false,\"roleId\":2,\"admin\":false,\"params\":{},\"updateBy\":\"admin\",\"status\":\"1\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 16:45:37'),
(166,'菜单管理',1,'com.dimple.project.system.controller.SysMenuController.add()','POST',1,'admin','/system/menu','127.0.0.1','内网IP','{\"visible\":\"0\",\"icon\":\"drag\",\"orderNum\":\"6\",\"menuName\":\"黑名单\",\"params\":{},\"parentId\":2,\"path\":\"/monitor/blacklist\",\"component\":\"monitor/blacklist/index\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"C\",\"perms\":\"monitor:blacklist:list\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 16:49:08'),
(167,'角色管理',2,'com.dimple.project.system.controller.SysRoleController.edit()','PUT',1,'admin','/system/role','127.0.0.1','内网IP','{\"flag\":false,\"roleId\":2,\"admin\":false,\"remark\":\"普通角色\",\"params\":{},\"roleSort\":\"2\",\"createTime\":1521171180000,\"updateBy\":\"admin\",\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"menuIds\":[115,108,500,1040,1041,1042,501,1043,1044,1045,4,1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,2],\"status\":\"1\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 16:49:40'),
(168,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/113','127.0.0.1','内网IP','{menuId=113}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 16:49:46'),
(169,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/114','127.0.0.1','内网IP','{menuId=114}','{\"msg\":\"存在子菜单,不允许删除\",\"code\":500}',0,NULL,'2019-10-24 16:49:49'),
(170,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/1055','127.0.0.1','内网IP','{menuId=1055}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 16:49:54'),
(171,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/1056','127.0.0.1','内网IP','{menuId=1056}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 16:49:56'),
(172,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/1057','127.0.0.1','内网IP','{menuId=1057}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 16:49:57'),
(173,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/1058','127.0.0.1','内网IP','{menuId=1058}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 16:49:59'),
(174,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/1059','127.0.0.1','内网IP','{menuId=1059}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 16:50:01'),
(175,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/114','127.0.0.1','内网IP','{menuId=114}','{\"msg\":\"存在子菜单,不允许删除\",\"code\":500}',0,NULL,'2019-10-24 16:50:02'),
(176,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/114','127.0.0.1','内网IP','{menuId=114}','{\"msg\":\"存在子菜单,不允许删除\",\"code\":500}',0,NULL,'2019-10-24 16:50:07'),
(177,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/114','127.0.0.1','内网IP','{menuId=114}','{\"msg\":\"存在子菜单,不允许删除\",\"code\":500}',0,NULL,'2019-10-24 16:50:29'),
(178,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/114','127.0.0.1','内网IP','{menuId=114}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 16:53:53'),
(179,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/3','127.0.0.1','内网IP','{menuId=3}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 16:56:08'),
(180,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/4','127.0.0.1','内网IP','{menuId=4}','{\"msg\":\"菜单已分配,不允许删除\",\"code\":500}',0,NULL,'2019-10-24 16:56:51'),
(181,'角色管理',2,'com.dimple.project.system.controller.SysRoleController.edit()','PUT',1,'admin','/system/role','127.0.0.1','内网IP','{\"flag\":false,\"roleId\":2,\"admin\":false,\"remark\":\"普通角色\",\"params\":{},\"roleSort\":\"2\",\"createTime\":1521171180000,\"updateBy\":\"admin\",\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"menuIds\":[115,108,500,1040,1041,1042,501,1043,1044,1045,1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,2],\"status\":\"1\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 16:57:01'),
(182,'菜单管理',3,'com.dimple.project.system.controller.SysMenuController.remove()','DELETE',1,'admin','/system/menu/4','127.0.0.1','内网IP','{menuId=4}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 16:57:11'),
(183,'黑名单管理',1,'com.dimple.project.monitor.controller.SysBlacklistController.add()','POST',1,'admin','/monitor/blacklist','127.0.0.1','内网IP','{\"ip\":\"11321\",\"description\":\"1\",\"params\":{},\"createBy\":\"admin\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 18:39:37'),
(184,'黑名单管理',1,'com.dimple.project.monitor.controller.SysBlacklistController.add()','POST',1,'admin','/monitor/blacklist','127.0.0.1','内网IP','{\"ip\":\"45640\",\"description\":\"232\",\"params\":{},\"createBy\":\"admin\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 18:44:05'),
(185,'黑名单管理',1,'com.dimple.project.monitor.controller.SysBlacklistController.add()','POST',1,'admin','/monitor/blacklist','127.0.0.1','内网IP','{\"ip\":\"45640\",\"description\":\"232\",\"params\":{},\"createBy\":\"admin\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2019-10-24 18:44:05');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
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
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`role_key`,`role_sort`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`,`remark`) values 
(1,'管理员','admin',1,'0','admin','2018-03-16 11:33:00','23','2018-03-16 11:33:00','',NULL,'管理员'),
(2,'普通角色','common',2,'1','admin','2018-03-16 11:33:00','admin','2019-10-24 16:57:01','',NULL,'普通角色'),
(3,'111','11',1,'0','admin','2019-10-23 15:45:44','admin','2019-10-23 15:46:13','admin','2019-10-23 15:46:18',NULL);

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
(3,1054);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
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
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`user_id`,`dept_id`,`user_name`,`nick_name`,`user_type`,`email`,`phonenumber`,`sex`,`avatar`,`password`,`status`,`login_ip`,`login_date`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_by`,`delete_time`,`remark`) values 
(1,103,'admin','若依','00','ry@163.com','15888888888','1','','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','127.0.0.1','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','ry','2018-03-16 11:33:00','',NULL,'管理员'),
(2,105,'ry','若依','00','ry@qq.com','15666666666','1','','$2a$10$hSL8f.30KJkGq7nZ4W9.W.mgKQUmxq2Twq4o7a2FlWDiP6yVpC95S','0','127.0.0.1','2018-03-16 11:33:00','admin','2018-03-16 11:33:00','admin','2019-10-23 15:41:58','',NULL,'测试员'),
(3,NULL,'admin1','11','00','network@artech.com','15923652363','0','','$2a$10$hPifjlDc.5sEbalGlp4agOmlungpzq0tUpk62X04iJvaToyFaKLse','0','',NULL,'admin','2019-10-23 15:44:49','',NULL,'admin','2019-10-23 16:52:26','111');

/*Table structure for table `sys_user_online` */

DROP TABLE IF EXISTS `sys_user_online`;

CREATE TABLE `sys_user_online` (
  `sessionId` varchar(50) NOT NULL DEFAULT '' COMMENT '用户会话id',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
  `start_timestamp` datetime DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int(5) DEFAULT '0' COMMENT '超时时间，单位为分钟',
  PRIMARY KEY (`sessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在线用户记录';

/*Data for the table `sys_user_online` */

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

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
