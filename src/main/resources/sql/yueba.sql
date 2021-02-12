/*
 Navicat MySQL Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : yueba

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 11/05/2020 00:42:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '账号',
  `password` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '盐',
  `nickname` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
  `face` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `role` int(4) DEFAULT NULL COMMENT '0 用户 1 会员 2管理',
  `male` int(4) DEFAULT NULL COMMENT '性别',
  `age` int(4) DEFAULT NULL COMMENT '年龄',
  `location` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '居住地',
  `money` int(11) DEFAULT NULL COMMENT '收入 元',
  `height` int(11) DEFAULT NULL COMMENT '身高 cm',
  `description` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '简介 不超过2000字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'admin', 'da4799f536c020929a55f2631039118f', '111111', '123', '/image/c35e46a0-11f5-4ef5-aa4e-9811741c64c5.jpg', 0, 0, 22, '类型1', 1000, 231, '神一样的存在');
COMMIT;

-- ----------------------------
-- Table structure for user_vip
-- ----------------------------
DROP TABLE IF EXISTS `user_vip`;
CREATE TABLE `user_vip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户Id',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态 0 等待处理中 1 处理完毕',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of user_vip
-- ----------------------------
BEGIN;
INSERT INTO `user_vip` VALUES (1, 1, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
