/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : dimple_blog

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 23/11/2018 10:14:31
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
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态，1表示已发表，2表示在草稿箱，3表示在垃圾箱',
  `support` tinyint(1) NULL DEFAULT NULL COMMENT '是否推荐',
  `tags` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `click` int(11) NULL DEFAULT NULL COMMENT '点击次数',
  `up` tinyint(4) NULL DEFAULT NULL COMMENT '置顶量',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
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
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for links
-- ----------------------------
DROP TABLE IF EXISTS `links`;
CREATE TABLE `links`  (
  `link_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '友链的标题',
  `display` tinyint(1) NULL DEFAULT NULL COMMENT '是否显示(1表示显示，0表示不显示)',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '是否已经处理(1表示已经处理，0表示没有处理)',
  `weight` int(11) NULL DEFAULT NULL COMMENT '权重',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '友链链接地址',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '友链的描述',
  `click` int(11) NULL DEFAULT NULL COMMENT '友链点击数',
  `available` tinyint(1) NULL DEFAULT NULL COMMENT '友链是否可用（定时任务查询）',
  PRIMARY KEY (`link_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 518 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of links
-- ----------------------------
INSERT INTO `links` VALUES (416, '测试Titleab2', 0, 1, 0, '2018-11-23 09:46:06', 'www.29834.com', '测试Description587-4', 1, NULL);
INSERT INTO `links` VALUES (417, '测试Title1b2', 1, 1, 1, '2018-11-23 09:46:06', 'www.5326a.com', '测试Description587-1', 1, 1);
INSERT INTO `links` VALUES (418, '测试Titledec', 0, 1, 2, '2018-11-23 09:46:06', 'www.be639.com', '测试Description520-0', NULL, NULL);
INSERT INTO `links` VALUES (419, '测试Title516', 1, 1, 3, '2018-11-23 09:46:06', 'www.7934b.com', '测试Description193-3', NULL, NULL);
INSERT INTO `links` VALUES (420, '测试Titledd7', 0, 1, 4, '2018-11-23 09:46:06', 'www.d7777.com', '测试Descriptiond60-1', NULL, NULL);
INSERT INTO `links` VALUES (421, '测试Titled80', 1, 1, 5, '2018-11-23 09:46:06', 'www.facaa.com', '测试Descriptionafc-8', NULL, NULL);
INSERT INTO `links` VALUES (422, '测试Title841', 0, 1, 6, '2018-11-23 09:46:06', 'www.ad28a.com', '测试Description4da-f', NULL, NULL);
INSERT INTO `links` VALUES (423, '测试Titlec47', 1, 1, 7, '2018-11-23 09:46:06', 'www.27db2.com', '测试Descriptionba8-1', NULL, NULL);
INSERT INTO `links` VALUES (424, '测试Titlef13', 0, 1, 8, '2018-11-23 09:46:06', 'www.839b5.com', '测试Description94f-3', NULL, NULL);
INSERT INTO `links` VALUES (425, '测试Title791', 1, 1, 9, '2018-11-23 09:46:06', 'www.ff217.com', '测试Description99a-6', NULL, NULL);
INSERT INTO `links` VALUES (426, '测试Titlef52', 0, 1, 10, '2018-11-23 09:46:06', 'www.00079.com', '测试Descriptiond04-4', NULL, NULL);
INSERT INTO `links` VALUES (427, '测试Title3b3', 1, 1, 11, '2018-11-23 09:46:06', 'www.76603.com', '测试Description5b7-8', NULL, NULL);
INSERT INTO `links` VALUES (428, '测试Title952', 0, 1, 12, '2018-11-23 09:46:06', 'www.81f90.com', '测试Descriptioncba-b', NULL, NULL);
INSERT INTO `links` VALUES (429, '测试Title58b', 1, 1, 13, '2018-11-23 09:46:06', 'www.f85a9.com', '测试Descriptionfce-b', NULL, NULL);
INSERT INTO `links` VALUES (430, '测试Titleac1', 0, 1, 14, '2018-11-23 09:46:06', 'www.78dca.com', '测试Description7a4-8', NULL, NULL);
INSERT INTO `links` VALUES (431, '测试Title72c', 1, 1, 15, '2018-11-23 09:46:06', 'www.8d9a6.com', '测试Description33e-4', NULL, NULL);
INSERT INTO `links` VALUES (432, '测试Title91c', 0, 1, 16, '2018-11-23 09:46:06', 'www.47b9a.com', '测试Descriptioncdd-0', NULL, NULL);
INSERT INTO `links` VALUES (433, '测试Title231', 1, 1, 17, '2018-11-23 09:46:06', 'www.f0e6b.com', '测试Description26b-7', NULL, NULL);
INSERT INTO `links` VALUES (434, '测试Titleeb0', 0, 1, 18, '2018-11-23 09:46:06', 'www.fc97b.com', '测试Description83e-6', NULL, NULL);
INSERT INTO `links` VALUES (435, '测试Title764', 1, 1, 19, '2018-11-23 09:46:06', 'www.00e3b.com', '测试Description3d2-c', NULL, NULL);
INSERT INTO `links` VALUES (436, '测试Title56a', 0, 1, 20, '2018-11-23 09:46:06', 'www.2699e.com', '测试Description3c9-8', NULL, NULL);
INSERT INTO `links` VALUES (437, '测试Title59f', 1, 1, 21, '2018-11-23 09:46:06', 'www.29b2d.com', '测试Descriptionfce-4', NULL, NULL);
INSERT INTO `links` VALUES (438, '测试Title24d', 0, 1, 22, '2018-11-23 09:46:06', 'www.eb39b.com', '测试Description3a4-4', NULL, NULL);
INSERT INTO `links` VALUES (439, '测试Title921', 1, 1, 23, '2018-11-23 09:46:06', 'www.ecff1.com', '测试Description85f-6', NULL, NULL);
INSERT INTO `links` VALUES (440, '测试Title167', 0, 1, 24, '2018-11-23 09:46:06', 'www.f2010.com', '测试Description2ef-c', NULL, NULL);
INSERT INTO `links` VALUES (441, '测试Title9fa', 1, 1, 25, '2018-11-23 09:46:06', 'www.260c6.com', '测试Description180-0', NULL, NULL);
INSERT INTO `links` VALUES (442, '测试Title941', 0, 1, 26, '2018-11-23 09:46:06', 'www.6e68e.com', '测试Description7b6-1', NULL, NULL);
INSERT INTO `links` VALUES (443, '测试Titlefe3', 1, 1, 27, '2018-11-23 09:46:06', 'www.6cd38.com', '测试Descriptionea7-a', NULL, NULL);
INSERT INTO `links` VALUES (444, '测试Titlef08', 0, 1, 28, '2018-11-23 09:46:06', 'www.6fab2.com', '测试Description756-1', NULL, NULL);
INSERT INTO `links` VALUES (445, '测试Title4e2', 1, 1, 29, '2018-11-23 09:46:06', 'www.edf4c.com', '测试Descriptionc66-5', NULL, NULL);
INSERT INTO `links` VALUES (446, '测试Title609', 0, 1, 30, '2018-11-23 09:46:06', 'www.56518.com', '测试Descriptioncdb-8', NULL, NULL);
INSERT INTO `links` VALUES (447, '测试Title63a', 1, 1, 31, '2018-11-23 09:46:06', 'www.78702.com', '测试Description4e7-0', NULL, NULL);
INSERT INTO `links` VALUES (448, '测试Titleb31', 0, 1, 32, '2018-11-23 09:46:06', 'www.07e29.com', '测试Descriptiondbc-6', NULL, NULL);
INSERT INTO `links` VALUES (449, '测试Titled16', 1, 1, 33, '2018-11-23 09:46:06', 'www.7990a.com', '测试Descriptionf12-5', NULL, NULL);
INSERT INTO `links` VALUES (450, '测试Titleda7', 0, 1, 34, '2018-11-23 09:46:06', 'www.b3a92.com', '测试Descriptionfe4-e', NULL, NULL);
INSERT INTO `links` VALUES (451, '测试Title6ed', 1, 1, 35, '2018-11-23 09:46:06', 'www.e5806.com', '测试Descriptiond7c-a', NULL, NULL);
INSERT INTO `links` VALUES (452, '测试Title28d', 0, 1, 36, '2018-11-23 09:46:06', 'www.309f9.com', '测试Descriptionb8b-7', NULL, NULL);
INSERT INTO `links` VALUES (453, '测试Title327', 1, 1, 37, '2018-11-23 09:46:06', 'www.21afd.com', '测试Description711-8', NULL, NULL);
INSERT INTO `links` VALUES (454, '测试Title87d', 0, 1, 38, '2018-11-23 09:46:06', 'www.a46e4.com', '测试Descriptiond5a-a', NULL, NULL);
INSERT INTO `links` VALUES (455, '测试Title167', 1, 1, 39, '2018-11-23 09:46:06', 'www.a2198.com', '测试Descriptionaad-9', NULL, NULL);
INSERT INTO `links` VALUES (456, '测试Title031', 0, 1, 40, '2018-11-23 09:46:06', 'www.b3882.com', '测试Descriptionbff-f', NULL, NULL);
INSERT INTO `links` VALUES (457, '测试Titlec2c', 1, 1, 41, '2018-11-23 09:46:06', 'www.6418e.com', '测试Descriptionf85-1', NULL, NULL);
INSERT INTO `links` VALUES (458, '测试Title46a', 0, 1, 42, '2018-11-23 09:46:06', 'www.9901e.com', '测试Descriptiona8d-0', NULL, NULL);
INSERT INTO `links` VALUES (459, '测试Titlebe1', 1, 1, 43, '2018-11-23 09:46:06', 'www.9e044.com', '测试Descriptionef6-6', NULL, NULL);
INSERT INTO `links` VALUES (460, '测试Titleee0', 0, 1, 44, '2018-11-23 09:46:06', 'www.4e845.com', '测试Description5e3-a', NULL, NULL);
INSERT INTO `links` VALUES (461, '测试Title105', 1, 1, 45, '2018-11-23 09:46:06', 'www.7fcdf.com', '测试Descriptionbc4-3', NULL, NULL);
INSERT INTO `links` VALUES (462, '测试Title298', 0, 1, 46, '2018-11-23 09:46:06', 'www.975a5.com', '测试Description765-f', NULL, NULL);
INSERT INTO `links` VALUES (463, '测试Title578', 1, 1, 47, '2018-11-23 09:46:06', 'www.5a6ea.com', '测试Descriptionff7-9', NULL, NULL);
INSERT INTO `links` VALUES (464, '测试Title1d4', 0, 1, 48, '2018-11-23 09:46:06', 'www.7294f.com', '测试Descriptiondcd-e', NULL, NULL);
INSERT INTO `links` VALUES (465, '测试Titlecf1', 1, 1, 49, '2018-11-23 09:46:06', 'www.2175d.com', '测试Description339-a', NULL, NULL);
INSERT INTO `links` VALUES (466, '测试Title770', 0, 1, 50, '2018-11-23 09:46:06', 'www.9f2e2.com', '测试Description222-e', NULL, NULL);
INSERT INTO `links` VALUES (467, '测试Titlef60', 1, 1, 51, '2018-11-23 09:46:06', 'www.51125.com', '测试Description44f-1', NULL, NULL);
INSERT INTO `links` VALUES (468, '测试Title211', 0, 1, 52, '2018-11-23 09:46:06', 'www.92d37.com', '测试Description3e6-2', NULL, NULL);
INSERT INTO `links` VALUES (469, '测试Title576', 1, 1, 53, '2018-11-23 09:46:06', 'www.6b494.com', '测试Descriptiond4c-8', NULL, NULL);
INSERT INTO `links` VALUES (470, '测试Title2ba', 0, 1, 54, '2018-11-23 09:46:06', 'www.056d9.com', '测试Description453-f', NULL, NULL);
INSERT INTO `links` VALUES (471, '测试Titled3b', 1, 1, 55, '2018-11-23 09:46:06', 'www.88643.com', '测试Descriptionaa6-2', NULL, NULL);
INSERT INTO `links` VALUES (472, '测试Titleedf', 0, 1, 56, '2018-11-23 09:46:06', 'www.8148c.com', '测试Description79a-a', NULL, NULL);
INSERT INTO `links` VALUES (473, '测试Title728', 1, 1, 57, '2018-11-23 09:46:06', 'www.56675.com', '测试Description414-c', NULL, NULL);
INSERT INTO `links` VALUES (474, '测试Titlee70', 0, 1, 58, '2018-11-23 09:46:06', 'www.03245.com', '测试Description482-4', NULL, NULL);
INSERT INTO `links` VALUES (475, '测试Title0fd', 1, 1, 59, '2018-11-23 09:46:06', 'www.62473.com', '测试Descriptione85-8', NULL, NULL);
INSERT INTO `links` VALUES (476, '测试Titled45', 0, 1, 60, '2018-11-23 09:46:06', 'www.549df.com', '测试Description98a-b', NULL, NULL);
INSERT INTO `links` VALUES (477, '测试Titleb6d', 1, 1, 61, '2018-11-23 09:46:06', 'www.75161.com', '测试Descriptiondd7-f', NULL, NULL);
INSERT INTO `links` VALUES (478, '测试Title699', 0, 1, 62, '2018-11-23 09:46:06', 'www.c2ee7.com', '测试Description53f-4', NULL, NULL);
INSERT INTO `links` VALUES (479, '测试Titlecb8', 1, 1, 63, '2018-11-23 09:46:06', 'www.d1e9e.com', '测试Description121-6', NULL, NULL);
INSERT INTO `links` VALUES (480, '测试Title598', 0, 1, 64, '2018-11-23 09:46:06', 'www.4feb9.com', '测试Description558-a', NULL, NULL);
INSERT INTO `links` VALUES (481, '测试Title3e7', 1, 1, 65, '2018-11-23 09:46:06', 'www.25b1f.com', '测试Descriptiond7b-f', NULL, NULL);
INSERT INTO `links` VALUES (482, '测试Title9f9', 0, 1, 66, '2018-11-23 09:46:06', 'www.f5c95.com', '测试Descriptionb1f-9', NULL, NULL);
INSERT INTO `links` VALUES (483, '测试Title243', 1, 1, 67, '2018-11-23 09:46:06', 'www.da007.com', '测试Description48e-a', NULL, NULL);
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
INSERT INTO `links` VALUES (503, '测试Titlea9e', 1, 1, 87, '2018-11-23 09:46:06', 'www.50caa.com', '测试Description7f9-7', NULL, NULL);
INSERT INTO `links` VALUES (504, '测试Title281', 0, 1, 88, '2018-11-23 09:46:06', 'www.25725.com', '测试Descriptiond3a-0', NULL, NULL);
INSERT INTO `links` VALUES (505, '测试Title0d7', 1, 1, 89, '2018-11-23 09:46:06', 'www.1701d.com', '测试Description960-3', NULL, NULL);
INSERT INTO `links` VALUES (506, '测试Titled1a', 0, 1, 90, '2018-11-23 09:46:06', 'www.f7f18.com', '测试Descriptione55-e', NULL, NULL);
INSERT INTO `links` VALUES (507, '测试Title773', 1, 1, 91, '2018-11-23 09:46:06', 'www.4940e.com', '测试Description9ce-a', NULL, NULL);
INSERT INTO `links` VALUES (508, '测试Title153', 0, 1, 92, '2018-11-23 09:46:06', 'www.24396.com', '测试Description3a4-c', NULL, NULL);
INSERT INTO `links` VALUES (509, '测试Title468', 1, 1, 93, '2018-11-23 09:46:06', 'www.6e15c.com', '测试Description6ae-c', NULL, NULL);
INSERT INTO `links` VALUES (510, '测试Title4a4', 0, 1, 94, '2018-11-23 09:46:06', 'www.626a6.com', '测试Descriptionfa4-9', NULL, NULL);
INSERT INTO `links` VALUES (511, '测试Title241', 1, 1, 95, '2018-11-23 09:46:06', 'www.69eb2.com', '测试Descriptionb13-4', NULL, NULL);
INSERT INTO `links` VALUES (512, '测试Titleadd', 0, 1, 96, '2018-11-23 09:46:06', 'www.a3823.com', '测试Description019-d', NULL, NULL);
INSERT INTO `links` VALUES (513, '测试Title965', 1, 1, 97, '2018-11-23 09:46:06', 'www.ec973.com', '测试Description865-e', NULL, NULL);
INSERT INTO `links` VALUES (514, '测试Titlef22', 0, 1, 98, '2018-11-23 09:46:06', 'www.57487.com', '测试Descriptionf81-e', NULL, NULL);
INSERT INTO `links` VALUES (515, '测试Title0c2', 1, 1, 99, '2018-11-23 09:46:06', 'www.69f6f.com', '测试Description8b9-c', NULL, NULL);
INSERT INTO `links` VALUES (516, '555', 1, 1, 11, '2018-11-23 09:46:52', '111', '1111', 1, 1);
INSERT INTO `links` VALUES (517, '111', 1, 1, 11, '2018-11-23 09:53:14', '111', '111', 1, 1);

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
  `locked` tinyint(4) NULL DEFAULT NULL COMMENT '是否锁定',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '我是管理员', 'admin', 'd108b56a6bbea62e2f68f3049f208d30', 'uuid', 0);

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
  `visit_time` datetime(0) NULL DEFAULT NULL COMMENT '游客访问时间',
  PRIMARY KEY (`visitor_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
