/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50561
 Source Host           : localhost:3306
 Source Schema         : dimple_blog

 Target Server Type    : MySQL
 Target Server Version : 50561
 File Encoding         : 65001

 Date: 03/12/2018 07:44:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `article_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '博客的主键',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '博客所属分类',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `summary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '摘要',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态，1表示已发表，2表示在草稿箱，3表示在垃圾箱',
  `support` tinyint(1) NULL DEFAULT NULL COMMENT '是否推荐',
  `tags` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `click` int(11) NULL DEFAULT NULL COMMENT '点击次数',
  `up` tinyint(4) NULL DEFAULT NULL COMMENT '置顶量',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`article_id`) USING BTREE,
  INDEX `category_id`(`category_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类的id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类的名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for link
-- ----------------------------
DROP TABLE IF EXISTS `links`;
CREATE TABLE `links`  (
  `link_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '友链的标题',
  `display` tinyint(1) NULL DEFAULT NULL COMMENT '是否显示(1表示显示，0表示不显示)',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '是否已经处理(1表示已经处理，0表示没有处理)',
  `weight` int(11) NULL DEFAULT NULL COMMENT '权重',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '友链链接地址',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '友链的描述',
  `click` int(11) NULL DEFAULT NULL COMMENT '友链点击数',
  `available` tinyint(1) NULL DEFAULT NULL COMMENT '友链是否可用（定时任务查询）',
  PRIMARY KEY (`link_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 596 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of link
-- ----------------------------
INSERT INTO `links` VALUES (522, 'qqq', 0, NULL, 11, '2018-11-28 15:02:36', '1414112', '111', NULL, NULL);
INSERT INTO `links` VALUES (425, '测试Title791', NULL, NULL, 100, '2018-12-01 15:21:14', 'www.ff217.com', '测试Description99a-6', NULL, 0);
INSERT INTO `links` VALUES (437, '测试Title59f', NULL, NULL, 21, '2018-12-01 15:21:14', 'www.29b2d.com121', '测试Descriptionfce-4', NULL, 0);
INSERT INTO `links` VALUES (438, '测试Title24d', 1, 1, 22, '2018-11-23 09:46:06', 'www.eb39b.com', '测试Description3a4-4', NULL, NULL);
INSERT INTO `links` VALUES (439, '测试Title921', 0, 1, 23, '2018-11-23 09:46:06', 'www.ecff1.com', '测试Description85f-6', NULL, NULL);
INSERT INTO `links` VALUES (440, '测试Title167', 1, 1, 24, '2018-11-23 09:46:06', 'www.f2010.com', '测试Description2ef-c', NULL, NULL);
INSERT INTO `links` VALUES (595, '123', 0, 0, 123, '2018-12-01 22:18:05', '123', '1232312', NULL, NULL);
INSERT INTO `links` VALUES (443, '测试Titlefe3', 0, 1, 2745456, '2018-12-01 23:47:00', 'www.6cd38.com', '测试Descriptionea7-a', NULL, NULL);
INSERT INTO `links` VALUES (444, '测试Titlef08', 0, 1, 28, '2018-11-23 09:46:06', 'www.6fab2.com', '测试Description756-1', NULL, NULL);
INSERT INTO `links` VALUES (455, '测试Title167', 1, 1, 39, '2018-11-23 09:46:06', 'www.a2198.com', '测试Descriptionaad-9', NULL, NULL);
INSERT INTO `links` VALUES (456, '测试Title031', 0, 1, 40, '2018-11-23 09:46:06', 'www.b3882.com', '测试Descriptionbff-f', NULL, NULL);
INSERT INTO `links` VALUES (457, '测试Titlec2c', 1, 1, 41, '2018-11-23 09:46:06', 'www.6418e.com', '测试Descriptionf85-1', NULL, NULL);
INSERT INTO `links` VALUES (458, '测试Title46a', 0, 1, 42, '2018-11-23 09:46:06', 'www.9901e.com', '测试Descriptiona8d-0', NULL, NULL);
INSERT INTO `links` VALUES (459, '测试Titlebe1', 1, 1, 43, '2018-11-23 09:46:06', 'www.9e044.com', '测试Descriptionef6-6', NULL, NULL);
INSERT INTO `links` VALUES (460, '测试Titleee0', 1, 1, 44, '2018-11-23 09:46:06', 'www.4e845.com', '测试Description5e3-a', NULL, NULL);
INSERT INTO `links` VALUES (461, '测试Title105', 1, 1, 45, '2018-11-23 09:46:06', 'www.7fcdf.com', '测试Descriptionbc4-3', NULL, NULL);
INSERT INTO `links` VALUES (462, '测试Title298', 0, 1, 46, '2018-11-23 09:46:06', 'www.975a5.com', '测试Description765-f', NULL, NULL);
INSERT INTO `links` VALUES (463, '测试Title578', 1, 1, 47, '2018-11-23 09:46:06', 'www.5a6ea.com', '测试Descriptionff7-9', NULL, NULL);
INSERT INTO `links` VALUES (464, '测试Title1d4', 0, 1, 48, '2018-11-23 09:46:06', 'www.7294f.com', '测试Descriptiondcd-e', NULL, NULL);
INSERT INTO `links` VALUES (465, '测试Titlecf1', 0, 1, 49, '2018-11-23 09:46:06', 'www.2175d.com', '测试Description339-a', NULL, NULL);
INSERT INTO `links` VALUES (466, '测试Title770', 1, 1, 50, '2018-11-23 09:46:06', 'www.9f2e2.com', '测试Description222-e', NULL, NULL);
INSERT INTO `links` VALUES (467, '测试Titlef60', 1, 1, 51, '2018-11-23 09:46:06', 'www.51125.com', '测试Description44f-1', NULL, NULL);
INSERT INTO `links` VALUES (468, '测试Title211', 1, 1, 52, '2018-11-23 09:46:06', 'www.92d37.com', '测试Description3e6-2', NULL, NULL);
INSERT INTO `links` VALUES (469, '测试Title576', 0, 1, 53, '2018-11-23 09:46:06', 'www.6b494.com', '测试Descriptiond4c-8', NULL, NULL);
INSERT INTO `links` VALUES (470, '测试Title2ba', 0, 1, 54, '2018-11-23 09:46:06', 'www.056d9.com', '测试Description453-f', NULL, NULL);
INSERT INTO `links` VALUES (471, '测试Titled3b', 0, 1, 55, '2018-11-23 09:46:06', 'www.88643.com', '测试Descriptionaa6-2', NULL, NULL);
INSERT INTO `links` VALUES (472, '测试Titleedf', 1, 1, 56, '2018-11-23 09:46:06', 'www.8148c.com', '测试Description79a-a', NULL, NULL);
INSERT INTO `links` VALUES (473, '测试Title728', 0, 1, 57, '2018-11-23 09:46:06', 'www.56675.com', '测试Description414-c', NULL, NULL);
INSERT INTO `links` VALUES (474, '测试Titlee70', 1, 1, 58, '2018-11-23 09:46:06', 'www.03245.com', '测试Description482-4', NULL, NULL);
INSERT INTO `links` VALUES (475, '测试Title0fd', 0, 1, 59, '2018-11-23 09:46:06', 'www.62473.com', '测试Descriptione85-8', NULL, NULL);
INSERT INTO `links` VALUES (476, '测试Titled45', 0, 1, 60, '2018-11-23 09:46:06', 'www.549df.com', '测试Description98a-b', NULL, NULL);
INSERT INTO `links` VALUES (478, '测试Title699', 1, 1, 62, '2018-11-23 09:46:06', 'www.c2ee7.com', '测试Description53f-4', NULL, NULL);
INSERT INTO `links` VALUES (479, '测试Titlecb8', 0, 1, 63, '2018-11-23 09:46:06', 'www.d1e9e.com', '测试Description121-6', NULL, NULL);
INSERT INTO `links` VALUES (480, '测试Title598', 1, 1, 64, '2018-11-23 09:46:06', 'www.4feb9.com', '测试Description558-a', NULL, NULL);
INSERT INTO `links` VALUES (481, '测试Title3e7', 0, 1, 65, '2018-11-23 09:46:06', 'www.25b1f.com', '测试Descriptiond7b-f', NULL, NULL);
INSERT INTO `links` VALUES (482, '测试Title9f9', 0, 1, 66, '2018-11-23 09:46:06', 'www.f5c95.com', '测试Descriptionb1f-9', NULL, NULL);
INSERT INTO `links` VALUES (483, '测试Title243', 0, 1, 67, '2018-11-23 09:46:06', 'www.da007.com', '测试Description48e-a', NULL, NULL);
INSERT INTO `links` VALUES (484, '测试Title2fd', 0, 1, 68, '2018-11-23 09:46:06', 'www.765fc.com', '测试Description440-b', NULL, NULL);
INSERT INTO `links` VALUES (485, '测试Title8be', 1, 1, 69, '2018-11-23 09:46:06', 'www.7850c.com', '测试Description054-d', NULL, NULL);
INSERT INTO `links` VALUES (486, '测试Titlef92', 0, 1, 70, '2018-11-23 09:46:06', 'www.ca4b8.com', '测试Descriptionf9c-5', NULL, NULL);
INSERT INTO `links` VALUES (487, '测试Titleaa5', 1, 1, 71, '2018-11-23 09:46:06', 'www.40da7.com', '测试Description510-8', NULL, NULL);
INSERT INTO `links` VALUES (488, '测试Title833', 0, 1, 72, '2018-11-23 09:46:06', 'www.8bf8f.com', '测试Description911-b', NULL, NULL);
INSERT INTO `links` VALUES (489, '测试Title1cc', 1, 1, 73, '2018-11-23 09:46:06', 'www.74a9f.com', '测试Description5ef-4', NULL, NULL);
INSERT INTO `links` VALUES (490, '测试Title7ff', 0, 1, 74, '2018-11-23 09:46:06', 'www.07aa0.com', '测试Descriptionc54-0', NULL, NULL);
INSERT INTO `links` VALUES (491, '测试Titleb38', 1, 1, 75, '2018-11-23 09:46:06', 'www.d3ed9.com', '测试Description8a6-1', NULL, NULL);
INSERT INTO `links` VALUES (492, '测试Title685', 0, 1, 76, '2018-11-23 09:46:06', 'www.2605a.com', '测试Description764-3', NULL, NULL);
INSERT INTO `links` VALUES (493, '测试Titleefe', 1, 1, 77, '2018-11-23 09:46:06', 'www.291cf.com', '测试Description600-7', NULL, NULL);
INSERT INTO `links` VALUES (494, '测试Title74c', 0, 1, 78, '2018-11-23 09:46:06', 'www.1ac55.com', '测试Descriptionc06-4', NULL, NULL);
INSERT INTO `links` VALUES (495, '测试Title0e6', 1, 1, 79, '2018-11-23 09:46:06', 'www.c40c4.com', '测试Descriptionf20-f', NULL, NULL);
INSERT INTO `links` VALUES (496, '测试Title4b3', 0, 1, 80, '2018-11-23 09:46:06', 'www.55aec.com', '测试Description216-1', NULL, NULL);
INSERT INTO `links` VALUES (497, '测试Titleebc', 1, 1, 81, '2018-11-23 09:46:06', 'www.d1afb.com', '测试Description64b-0', NULL, NULL);
INSERT INTO `links` VALUES (498, '测试Titlef8f', 0, 1, 82, '2018-11-23 09:46:06', 'www.59f8c.com', '测试Descriptiona7b-6', NULL, NULL);
INSERT INTO `links` VALUES (499, '测试Title4e2', 1, 1, 83, '2018-11-23 09:46:06', 'www.716b5.com', '测试Description6cb-8', NULL, NULL);
INSERT INTO `links` VALUES (500, '测试Titleb60', 0, 1, 84, '2018-11-23 09:46:06', 'www.b71f5.com', '测试Description387-7', NULL, NULL);
INSERT INTO `links` VALUES (501, '测试Titled16', 1, 1, 85, '2018-11-23 09:46:06', 'www.16f9e.com', '测试Descriptionb50-0', NULL, NULL);
INSERT INTO `links` VALUES (502, '测试Titlec67', 0, 1, 86, '2018-11-23 09:46:06', 'www.86cf5.com', '测试Description25e-5', NULL, NULL);
INSERT INTO `links` VALUES (503, '测试Titlea9e11111111', 1, 1, 87, '2018-12-01 23:48:28', 'www.50caa.com', '测试Description7f9-7', NULL, NULL);
INSERT INTO `links` VALUES (504, '测试Title281', 0, 1, 88, '2018-11-23 09:46:06', 'www.25725.com', '测试Descriptiond3a-0', NULL, NULL);
INSERT INTO `links` VALUES (505, '测试Title0d7', 1, 1, 89, '2018-11-23 09:46:06', 'www.1701d.com', '测试Description960-3', NULL, NULL);
INSERT INTO `links` VALUES (506, '测试Titled1a', 0, 1, 90, '2018-11-23 09:46:06', 'www.f7f18.com', '测试Descriptione55-e', NULL, NULL);
INSERT INTO `links` VALUES (507, '测试Title773', 1, 1, 91, '2018-11-23 09:46:06', 'www.4940e.com', '测试Description9ce-a', NULL, NULL);
INSERT INTO `links` VALUES (508, '测试Title153', 0, 1, 92, '2018-11-23 09:46:06', 'www.24396.com', '测试Description3a4-c', NULL, NULL);
INSERT INTO `links` VALUES (509, '测试Title468111', 1, 1, 93, '2018-12-01 23:49:24', 'www.6e15c.com二位', '测试Description6ae-c', NULL, NULL);
INSERT INTO `links` VALUES (510, '测试Title4a4', 0, 1, 94, '2018-11-23 09:46:06', 'www.626a6.com', '测试Descriptionfa4-9', NULL, NULL);
INSERT INTO `links` VALUES (511, '测试Title241', 1, 1, 95, '2018-11-23 09:46:06', 'www.69eb2.com', '测试Descriptionb13-4', NULL, NULL);
INSERT INTO `links` VALUES (512, '测试Titleadd', 0, 1, 96, '2018-11-23 09:46:06', 'www.a3823.com', '测试Description019-d', NULL, NULL);
INSERT INTO `links` VALUES (513, '测试Title965', 1, 1, 97, '2018-11-23 09:46:06', 'www.ec973.com', '测试Description865-e', NULL, NULL);
INSERT INTO `links` VALUES (514, '测试Titlef22', 0, 1, 98, '2018-11-23 09:46:06', 'www.57487.com', '测试Descriptionf81-e', NULL, NULL);
INSERT INTO `links` VALUES (515, '测试Title0c2', 1, 1, 99, '2018-11-23 09:46:06', 'www.69f6f.com', '测试Description8b9-c', NULL, NULL);

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log`  (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '登录日志记录的主键',
  `login_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录的用户名称	',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '登录的状态（1表示登录成功，0表示失败，2表示退出成功',
  `ip_address` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录的IP地址',
  `login_location` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录的地理位置\n',
  `browser` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录的浏览器类型',
  `os` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录的设备的类型',
  `msg` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录的信息\n',
  `login_time` datetime NULL DEFAULT NULL COMMENT '登录的时间',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 140 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录日志记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of login_log
-- ----------------------------
INSERT INTO `login_log` VALUES (134, 'admin', 0, '0:0:0:0:0:0:0:1', NULL, 'Chrome', 'Windows 10', '身份认证异常', '2018-12-01 22:02:56');
INSERT INTO `login_log` VALUES (135, 'admin', 0, '0:0:0:0:0:0:0:1', NULL, 'Chrome', 'Windows 10', '身份认证异常', '2018-12-01 22:03:01');
INSERT INTO `login_log` VALUES (136, 'admin', 0, '0:0:0:0:0:0:0:1', '', 'Chrome', 'Windows 10', '登录成功', '2018-12-01 22:03:17');
INSERT INTO `login_log` VALUES (137, 'admin', 0, '0:0:0:0:0:0:0:1', '', 'Chrome', 'Windows 10', '登录成功', '2018-12-01 23:06:04');
INSERT INTO `login_log` VALUES (138, 'admin', 0, '0:0:0:0:0:0:0:1', '', 'Chrome', 'Windows 10', '登录成功', '2018-12-02 11:06:51');
INSERT INTO `login_log` VALUES (139, 'admin', 0, '0:0:0:0:0:0:0:1', NULL, 'Chrome', 'Windows 10', '登录成功', '2018-12-02 11:06:52');

-- ----------------------------
-- Table structure for operator_log
-- ----------------------------
DROP TABLE IF EXISTS `operator_log`;
CREATE TABLE `operator_log`  (
  `operId` int(11) NOT NULL AUTO_INCREMENT COMMENT '操作序号\n',
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作模块名称',
  `operator_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作类型\n',
  `method` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求的方法',
  `channel` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来源渠道',
  `operator_url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求的URL',
  `operator_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人员',
  `operator_ip` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作的IP地址',
  `operator_location` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作的地点',
  `operator_param` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求的参数',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态（1、true表示正常，0，false表示异常）',
  `error_msg` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '错误消息',
  `operator_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`operId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户操作记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of operator_log
-- ----------------------------
INSERT INTO `operator_log` VALUES (1, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"qqq\"],\"description\":[\"324324\"],\"url\":[\"1414\"],\"display\":[\"true\"],\"weight\":[\"11\"]}', 1, NULL, NULL);
INSERT INTO `operator_log` VALUES (2, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '10.17.80.59', 'XX 内网IP', '{\"title\":[\"s\"],\"description\":[\"324324\"],\"url\":[\"32432\"],\"display\":[\"\"],\"weight\":[\"11\"]}', 1, NULL, '2018-11-29 12:33:35');
INSERT INTO `operator_log` VALUES (3, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '10.17.80.59', 'XX 内网IP', '{\"title\":[\"222\"],\"description\":[\"222\"],\"url\":[\"222\"],\"display\":[\"\"],\"weight\":[\"222\"]}', 1, NULL, '2018-11-29 12:35:06');
INSERT INTO `operator_log` VALUES (4, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '10.17.80.59', 'XX 内网IP', '{\"title\":[\"qqq\"],\"description\":[\"324324\"],\"url\":[\"32432\"],\"display\":[\"\"],\"weight\":[\"11\"]}', 1, NULL, '2018-11-29 12:36:16');
INSERT INTO `operator_log` VALUES (5, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"s\"],\"description\":[\"324324\"],\"url\":[\"32432\"],\"display\":[\"\"],\"weight\":[\"11\"]}', 1, NULL, '2018-11-29 12:38:53');
INSERT INTO `operator_log` VALUES (6, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"凄凄切切\"],\"description\":[\"去去去\"],\"url\":[\"去去去\"],\"display\":[\"\"],\"weight\":[\"11\"]}', 1, NULL, '2018-11-29 12:44:34');
INSERT INTO `operator_log` VALUES (7, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"1\"],\"description\":[\"1\"],\"url\":[\"1\"],\"display\":[\"\"],\"weight\":[\"1\"]}', 1, NULL, '2018-11-29 12:48:23');
INSERT INTO `operator_log` VALUES (8, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"1\"],\"description\":[\"www\"],\"url\":[\"www\"],\"display\":[\"true\"],\"weight\":[\"1\"]}', 1, NULL, '2018-11-29 12:50:26');
INSERT INTO `operator_log` VALUES (9, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"问问\"],\"description\":[\"www\"],\"url\":[\"www\"],\"display\":[\"\"],\"weight\":[\"1\"]}', 1, NULL, '2018-11-29 12:51:25');
INSERT INTO `operator_log` VALUES (10, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"66\"],\"description\":[\"666\"],\"url\":[\"666\"],\"display\":[\"true\"],\"weight\":[\"666\"]}', 1, NULL, '2018-11-29 12:58:50');
INSERT INTO `operator_log` VALUES (11, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"1\"],\"description\":[\"1\"],\"url\":[\"1\"],\"display\":[\"\"],\"weight\":[\"1\"]}', 1, NULL, '2018-11-29 13:02:53');
INSERT INTO `operator_log` VALUES (12, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"1\"],\"description\":[\"1\"],\"url\":[\"1\"],\"display\":[\"\"],\"weight\":[\"1\"]}', 1, NULL, '2018-11-29 13:03:09');
INSERT INTO `operator_log` VALUES (13, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"1\"],\"description\":[\"1\"],\"url\":[\"1\"],\"display\":[\"\"],\"weight\":[\"1\"]}', 1, NULL, '2018-11-29 13:04:38');
INSERT INTO `operator_log` VALUES (14, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{}', 1, NULL, '2018-11-29 13:07:10');
INSERT INTO `operator_log` VALUES (15, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{}', 1, NULL, '2018-11-29 13:42:19');
INSERT INTO `operator_log` VALUES (16, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{}', 1, NULL, '2018-11-29 13:42:47');
INSERT INTO `operator_log` VALUES (17, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{}', 1, NULL, '2018-11-29 13:43:18');
INSERT INTO `operator_log` VALUES (18, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{}', 1, NULL, '2018-11-29 13:46:25');
INSERT INTO `operator_log` VALUES (19, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{}', 1, NULL, '2018-11-29 13:46:38');
INSERT INTO `operator_log` VALUES (20, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{}', 1, NULL, '2018-11-29 13:46:44');
INSERT INTO `operator_log` VALUES (21, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{}', 1, NULL, '2018-11-29 13:46:53');
INSERT INTO `operator_log` VALUES (22, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{}', 1, NULL, '2018-11-29 13:47:05');
INSERT INTO `operator_log` VALUES (23, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{}', 1, NULL, '2018-11-29 13:47:10');
INSERT INTO `operator_log` VALUES (24, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{}', 1, NULL, '2018-11-29 13:49:29');
INSERT INTO `operator_log` VALUES (25, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{}', 1, NULL, '2018-11-29 13:50:31');
INSERT INTO `operator_log` VALUES (26, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{}', 1, NULL, '2018-11-29 13:50:44');
INSERT INTO `operator_log` VALUES (27, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{}', 1, NULL, '2018-11-29 13:53:32');
INSERT INTO `operator_log` VALUES (28, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{}', 1, NULL, '2018-11-29 13:53:45');
INSERT INTO `operator_log` VALUES (29, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"问问\"],\"description\":[\"www\"],\"url\":[\"www\"],\"display\":[\"\"],\"weight\":[\"123\"]}', 1, NULL, '2018-11-29 13:54:53');
INSERT INTO `operator_log` VALUES (30, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"问问\"],\"description\":[\"www\"],\"url\":[\"www\"],\"display\":[\"\"],\"weight\":[\"1\"]}', 1, NULL, '2018-11-29 13:56:27');
INSERT INTO `operator_log` VALUES (31, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"问问\"],\"description\":[\"www\"],\"url\":[\"www\"],\"display\":[\"\"],\"weight\":[\"1\"]}', 1, NULL, '2018-11-29 13:58:01');
INSERT INTO `operator_log` VALUES (32, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"问问\"],\"description\":[\"www\"],\"url\":[\"www\"],\"display\":[\"\"],\"weight\":[\"1\"]}', 1, NULL, '2018-11-29 13:58:26');
INSERT INTO `operator_log` VALUES (33, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"是i\"],\"description\":[\"UI㔿\"],\"url\":[\"热热我\"],\"display\":[\"\"],\"weight\":[\"111\"]}', 1, NULL, '2018-11-29 14:00:14');
INSERT INTO `operator_log` VALUES (34, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"44\"],\"description\":[\"444\"],\"url\":[\"44\"],\"display\":[\"true\"],\"weight\":[\"44\"]}', 1, NULL, '2018-11-29 14:00:34');
INSERT INTO `operator_log` VALUES (35, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', NULL, '{\"title\":[\"123\"],\"description\":[\"123\"],\"url\":[\"132\"],\"display\":[\"\"],\"weight\":[\"1\"]}', 1, NULL, '2018-11-29 14:08:25');
INSERT INTO `operator_log` VALUES (36, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"111\"],\"description\":[\"111\"],\"url\":[\"111\"],\"display\":[\"\"],\"weight\":[\"111\"]}', 1, NULL, '2018-11-29 14:08:53');
INSERT INTO `operator_log` VALUES (37, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"二位\"],\"description\":[\"11\"],\"url\":[\"111\"],\"display\":[\"\"],\"weight\":[\"111\"]}', 1, NULL, '2018-11-29 14:11:31');
INSERT INTO `operator_log` VALUES (38, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"11\"],\"description\":[\"11\"],\"url\":[\"111\"],\"display\":[\"\"],\"weight\":[\"111\"]}', 1, NULL, '2018-11-29 14:12:01');
INSERT INTO `operator_log` VALUES (39, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"111\"],\"description\":[\"11\"],\"url\":[\"111\"],\"display\":[\"\"],\"weight\":[\"111\"]}', 1, NULL, '2018-11-29 14:14:51');
INSERT INTO `operator_log` VALUES (40, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"123·\"],\"description\":[\"123\"],\"url\":[\"132\"],\"display\":[\"\"],\"weight\":[\"13\"]}', 1, NULL, '2018-11-29 14:15:17');
INSERT INTO `operator_log` VALUES (41, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"123·\"],\"description\":[\"123\"],\"url\":[\"132\"],\"display\":[\"\"],\"weight\":[\"13\"]}', 1, NULL, '2018-11-29 14:15:51');
INSERT INTO `operator_log` VALUES (42, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"123\"],\"description\":[\"132\"],\"url\":[\"1\"],\"display\":[\"\"],\"weight\":[\"32\"]}', 1, NULL, '2018-11-29 14:16:09');
INSERT INTO `operator_log` VALUES (43, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"123·\"],\"description\":[\"123\"],\"url\":[\"132\"],\"display\":[\"false\"],\"weight\":[\"13\"]}', 1, NULL, '2018-11-29 14:18:18');
INSERT INTO `operator_log` VALUES (44, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"123·\"],\"description\":[\"123\"],\"url\":[\"132\"],\"display\":[\"\"],\"weight\":[\"13\"]}', 1, NULL, '2018-11-29 14:21:25');
INSERT INTO `operator_log` VALUES (45, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"\"],\"description\":[\"\"],\"url\":[\"\"],\"display\":[\"\"],\"weight\":[\"\"]}', 1, NULL, '2018-11-29 14:42:16');
INSERT INTO `operator_log` VALUES (46, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"123\"],\"description\":[\"12\"],\"url\":[\"132\"],\"display\":[\"true\"],\"weight\":[\"31\"]}', 1, NULL, '2018-11-29 14:42:20');
INSERT INTO `operator_log` VALUES (47, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"1223\"],\"description\":[\"1223\"],\"url\":[\"1323\"],\"display\":[\"\"],\"weight\":[\"11\"]}', 1, NULL, '2018-11-29 14:43:53');
INSERT INTO `operator_log` VALUES (48, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"1123\"],\"description\":[\"132\"],\"url\":[\"132\"],\"display\":[\"\"],\"weight\":[\"123\"]}', 1, NULL, '2018-11-29 14:48:14');
INSERT INTO `operator_log` VALUES (49, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"1\"],\"description\":[\"1\"],\"url\":[\"1\"],\"display\":[\"\"],\"weight\":[\"1\"]}', 1, NULL, '2018-11-29 14:49:00');
INSERT INTO `operator_log` VALUES (50, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"1\"],\"description\":[\"0\"],\"url\":[\"0\"],\"display\":[\"\"],\"weight\":[\"0\"]}', 1, NULL, '2018-11-29 14:51:23');
INSERT INTO `operator_log` VALUES (51, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"1\"],\"description\":[\"1\"],\"url\":[\"1\"],\"display\":[\"\"],\"weight\":[\"1\"]}', 1, NULL, '2018-11-29 14:51:48');
INSERT INTO `operator_log` VALUES (52, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"1\"],\"description\":[\"1\"],\"url\":[\"1\"],\"display\":[\"\"],\"weight\":[\"1\"]}', 1, NULL, '2018-11-29 14:55:22');
INSERT INTO `operator_log` VALUES (53, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"1\"],\"description\":[\"1\"],\"url\":[\"1\"],\"display\":[\"\"],\"weight\":[\"1\"]}', 1, NULL, '2018-11-29 14:56:42');
INSERT INTO `operator_log` VALUES (54, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"available\":[\"true\"],\"description\":[\"1\"],\"display\":[\"false\"],\"status\":[\"true\"],\"title\":[\"1\"],\"url\":[\"1\"]}', 1, NULL, '2018-11-29 15:00:17');
INSERT INTO `operator_log` VALUES (55, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"1\"],\"description\":[\"1\"],\"url\":[\"1\"],\"display\":[\"\"],\"weight\":[\"1\"]}', 1, NULL, '2018-11-29 15:01:48');
INSERT INTO `operator_log` VALUES (56, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"1\"],\"description\":[\"1\"],\"url\":[\"1\"],\"display\":[\"\"],\"weight\":[\"1\"]}', 1, NULL, '2018-11-29 15:06:35');
INSERT INTO `operator_log` VALUES (57, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"123·\"],\"description\":[\"1\"],\"url\":[\"1\"],\"display\":[\"\"],\"weight\":[\"1\"]}', 1, NULL, '2018-11-29 15:10:44');
INSERT INTO `operator_log` VALUES (58, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"111\"],\"description\":[\"111\"],\"url\":[\"111\"],\"display\":[\"\"],\"weight\":[\"111\"]}', 1, NULL, '2018-11-29 15:12:16');
INSERT INTO `operator_log` VALUES (59, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"梵蒂冈 \"],\"description\":[\" 风格\"],\"url\":[\" 梵蒂冈\"],\"display\":[\"\"],\"weight\":[\"111\"]}', 1, NULL, '2018-11-29 15:13:43');
INSERT INTO `operator_log` VALUES (60, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"二位\"],\"description\":[\" 我去qw\"],\"url\":[\"请问 \"],\"display\":[\"\"],\"weight\":[\"11\"]}', 1, NULL, '2018-11-29 15:15:28');
INSERT INTO `operator_log` VALUES (61, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"123·\"],\"description\":[\"123\"],\"url\":[\"132\"],\"display\":[\"\"],\"weight\":[\"1\"]}', 1, NULL, '2018-11-29 15:16:25');
INSERT INTO `operator_log` VALUES (62, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"回滚\"],\"description\":[\" 固话f\"],\"url\":[\"飞飞hh\"],\"display\":[\"\"],\"weight\":[\"111\"]}', 1, NULL, '2018-11-29 15:17:02');
INSERT INTO `operator_log` VALUES (63, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"111\"],\"description\":[\"111\"],\"url\":[\"111\"],\"display\":[\"\"],\"weight\":[\"111\"]}', 1, NULL, '2018-11-29 15:18:17');
INSERT INTO `operator_log` VALUES (64, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"222\"],\"description\":[\"22\"],\"url\":[\"222\"],\"display\":[\"\"],\"weight\":[\"222\"]}', 1, NULL, '2018-11-29 15:21:22');
INSERT INTO `operator_log` VALUES (65, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"1231\"],\"description\":[\"13213\"],\"url\":[\"321313\"],\"display\":[\"\"],\"weight\":[\"133\"]}', 1, NULL, '2018-11-29 15:21:32');
INSERT INTO `operator_log` VALUES (66, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"111\"],\"description\":[\"111\"],\"url\":[\"1111\"],\"display\":[\"\"],\"weight\":[\"111\"]}', 1, NULL, '2018-11-29 15:21:58');
INSERT INTO `operator_log` VALUES (67, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"linkId\":[\"\"],\"title\":[\"ewqe \"],\"description\":[\"e wqe wq\"],\"url\":[\" wq \"],\"display\":[\"\"],\"weight\":[\"111\"]}', 1, NULL, '2018-11-29 15:23:15');
INSERT INTO `operator_log` VALUES (68, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"linkId\":[\"\"],\"title\":[\"2321\"],\"description\":[\"3123\"],\"url\":[\"12321\"],\"display\":[\"\"],\"weight\":[\"313\"]}', 1, NULL, '2018-11-29 15:24:10');
INSERT INTO `operator_log` VALUES (69, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"linkId\":[\"\"],\"title\":[\"123\"],\"description\":[\"132\"],\"url\":[\"1\"],\"display\":[\"\"],\"weight\":[\"1\"]}', 1, NULL, '2018-11-29 15:25:05');
INSERT INTO `operator_log` VALUES (70, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"linkId\":[\"\"],\"title\":[\"12\"],\"description\":[\"313\"],\"url\":[\"213\"],\"display\":[\"\"],\"weight\":[\"13\"]}', 1, NULL, '2018-11-29 15:25:32');
INSERT INTO `operator_log` VALUES (71, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"linkId\":[\"\"],\"title\":[\"123\"],\"description\":[\"132\"],\"url\":[\"12\"],\"display\":[\"\"],\"weight\":[\"321\"]}', 1, NULL, '2018-11-29 15:25:40');
INSERT INTO `operator_log` VALUES (72, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/linkss', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"linkId\":[\"\"],\"title\":[\"123\"],\"description\":[\"132\"],\"url\":[\"132\"],\"display\":[\"\"],\"weight\":[\"132\"]}', 1, NULL, '2018-11-29 15:26:33');
INSERT INTO `operator_log` VALUES (73, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"123·1\"],\"description\":[\"221\"],\"url\":[\"213\"],\"display\":[\"\"],\"weight\":[\"22\"]}', 1, NULL, '2018-11-29 15:35:52');
INSERT INTO `operator_log` VALUES (74, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"123\"],\"description\":[\"132\"],\"url\":[\"1\"],\"display\":[\"\"],\"weight\":[\"12\"]}', 1, NULL, '2018-11-29 15:36:23');
INSERT INTO `operator_log` VALUES (75, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"1\"],\"description\":[\"1\"],\"url\":[\"1\"],\"display\":[\"\"],\"weight\":[\"21\"]}', 1, NULL, '2018-11-29 15:36:31');
INSERT INTO `operator_log` VALUES (76, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"112\"],\"description\":[\"1\"],\"url\":[\"1\"],\"display\":[\"\"],\"weight\":[\"213\"]}', 1, NULL, '2018-11-29 15:37:04');
INSERT INTO `operator_log` VALUES (77, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"123\"],\"description\":[\"1\"],\"url\":[\"321\"],\"display\":[\"\"],\"weight\":[\"3\"]}', 1, NULL, '2018-11-29 15:37:25');
INSERT INTO `operator_log` VALUES (78, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"123\"],\"description\":[\"123\"],\"url\":[\"132\"],\"display\":[\"\"],\"weight\":[\"132\"]}', 1, NULL, '2018-11-29 15:41:03');
INSERT INTO `operator_log` VALUES (79, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"123\"],\"description\":[\"123\"],\"url\":[\"123\"],\"display\":[\"\"],\"weight\":[\"123\"]}', 1, NULL, '2018-11-29 15:41:41');
INSERT INTO `operator_log` VALUES (80, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"12\"],\"description\":[\"3132\"],\"url\":[\"\"],\"display\":[\"\"],\"weight\":[\"123\"]}', 1, NULL, '2018-11-29 15:42:47');
INSERT INTO `operator_log` VALUES (81, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"12\"],\"description\":[\"3132\"],\"url\":[\"121\"],\"display\":[\"\"],\"weight\":[\"123\"]}', 1, NULL, '2018-11-29 15:42:51');
INSERT INTO `operator_log` VALUES (82, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"123\"],\"description\":[\"123\"],\"url\":[\"132\"],\"display\":[\"true\"],\"weight\":[\"13\"]}', 1, NULL, '2018-11-30 10:17:35');
INSERT INTO `operator_log` VALUES (83, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '1', '/link', '我是管理员', '0:0:0:0:0:0:0:1', '', '{\"title\":[\"111\"],\"description\":[\"11\"],\"url\":[\"11\"],\"display\":[\"true\"],\"weight\":[\"11\"]}', 1, NULL, '2018-12-01 11:25:58');
INSERT INTO `operator_log` VALUES (84, '新增友链', '1', 'com.dimple.controller.LinksController.addLink()', '', '/link', '我是管理员', '0:0:0:0:0:0:0:1', NULL, '{\"title\":[\"123\"],\"description\":[\"123\"],\"url\":[\"123\"],\"display\":[\"true\"],\"weight\":[\"123\"]}', 1, NULL, '2018-12-01 22:05:06');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限的id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限的名称，如：user:update',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限的显示名称，如：用户更新',
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, 'user:update', '更新用户');
INSERT INTO `permission` VALUES (2, 'user:delete', '删除用户');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色的id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色的名称，如：admin',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色的描述，如：管理员',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin', '管理员');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色的id',
  `permission_id` int(11) NOT NULL COMMENT '权限的id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKf8yllw1ecvwqy3ehyxawqa1qp`(`permission_id`) USING BTREE,
  INDEX `FKa6jx8n8xkesmjmv6jqug6bg68`(`role_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, 1, 1);
INSERT INTO `role_permission` VALUES (2, 1, 2);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户的id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户的姓名，如：张三',
  `user_login_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户的登录名称，如：zhangsan',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐',
  `locked` tinyint(1) NULL DEFAULT NULL COMMENT '是否锁定',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '性别（1代表男，0代表女）',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '我是管理员', 'admin', 'd108b56a6bbea62e2f68f3049f208d30', 'uuid', 0, 'bianxiaofeng@sohu.com', '18582872818', 1);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1);

-- ----------------------------
-- Table structure for visitor
-- ----------------------------
DROP TABLE IF EXISTS `visitor`;
CREATE TABLE `visitor`  (
  `visitor_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '游客的id',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '游客的登录地址',
  `browser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '游客登录的浏览器',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '游客登录的ip',
  `locked` tinyint(4) NULL DEFAULT NULL COMMENT '游客账户是否被锁定',
  `visit_time` datetime NULL DEFAULT NULL COMMENT '游客访问时间',
  PRIMARY KEY (`visitor_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
