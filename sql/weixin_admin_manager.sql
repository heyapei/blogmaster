/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云
 Source Server Type    : MySQL
 Source Server Version : 50173
 Source Host           : 115.29.178.227:3306
 Source Schema         : weixin_admin_manager

 Target Server Type    : MySQL
 Target Server Version : 50173
 File Encoding         : 65001

 Date: 21/06/2020 22:44:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_resources
-- ----------------------------
DROP TABLE IF EXISTS `admin_resources`;
CREATE TABLE `admin_resources`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `res_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源url',
  `type` int(11) NULL DEFAULT NULL COMMENT '资源类型   1:菜单    2：按钮',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父资源',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_resources
-- ----------------------------
INSERT INTO `admin_resources` VALUES (1, '系统设置', '/system', 0, 0, 1);
INSERT INTO `admin_resources` VALUES (2, '用户管理', '/usersPage', 1, 1, 2);
INSERT INTO `admin_resources` VALUES (3, '角色管理', '/rolesPage', 1, 1, 3);
INSERT INTO `admin_resources` VALUES (4, '资源管理', '/resourcesPage', 1, 1, 4);
INSERT INTO `admin_resources` VALUES (5, '添加用户', '/users/add', 2, 2, 5);

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES (1, '管理员');
INSERT INTO `admin_role` VALUES (2, '普通用户');
INSERT INTO `admin_role` VALUES (3, '超级管理员');

-- ----------------------------
-- Table structure for admin_role_resources
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_resources`;
CREATE TABLE `admin_role_resources`  (
  `role_id` int(11) NOT NULL,
  `resources_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`, `resources_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_role_resources
-- ----------------------------
INSERT INTO `admin_role_resources` VALUES (1, 2);
INSERT INTO `admin_role_resources` VALUES (1, 3);
INSERT INTO `admin_role_resources` VALUES (1, 4);
INSERT INTO `admin_role_resources` VALUES (1, 5);

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(33) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `pass_word` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `enable` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否启用 0启用1禁用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES (1, 'admin', '14e1b600b1fd579f47433b88e8d85291', 0, '2020-06-21 17:40:31');
INSERT INTO `admin_user` VALUES (2, 'user1', '90e66e36e3135a91d298177d4389851e', 1, '2020-06-21 11:00:00');

-- ----------------------------
-- Table structure for admin_user_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_role`;
CREATE TABLE `admin_user_role`  (
  `user_id` int(11) NULL DEFAULT NULL,
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_user_role
-- ----------------------------
INSERT INTO `admin_user_role` VALUES (1, '1');
INSERT INTO `admin_user_role` VALUES (2, '2');

SET FOREIGN_KEY_CHECKS = 1;
