/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50643
 Source Host           : localhost:3306
 Source Schema         : rule_engine

 Target Server Type    : MySQL
 Target Server Version : 50643
 File Encoding         : 65001

 Date: 14/06/2020 21:08:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for method
-- ----------------------------
DROP TABLE IF EXISTS `method`;
CREATE TABLE `method` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '取数方法ID',
  `name` varchar(255) NOT NULL COMMENT '取数方法名称',
  `url` varchar(255) NOT NULL COMMENT '取数接口URL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of method
-- ----------------------------
BEGIN;
INSERT INTO `method` VALUES (1, '获取用户的积分数', 'http://localhost:8080/user/score');
COMMIT;

-- ----------------------------
-- Table structure for rule
-- ----------------------------
DROP TABLE IF EXISTS `rule`;
CREATE TABLE `rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '规则ID',
  `name` varchar(255) NOT NULL COMMENT '规则名称',
  `method_id` bigint(20) NOT NULL COMMENT '取数方法ID',
  `operator` varchar(10) NOT NULL COMMENT '运算符：>、<、>=、<=、==',
  `threshold` int(11) NOT NULL COMMENT '阈值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rule
-- ----------------------------
BEGIN;
INSERT INTO `rule` VALUES (1, '用户积分大于100的', 1, '>', 100);
INSERT INTO `rule` VALUES (2, '用户积分等于0的', 1, '=', 0);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `score` int(11) NOT NULL COMMENT '积分',
  `type` varchar(255) NOT NULL COMMENT '用户类型：管理员admin、用户user',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'zhangsan', '123456', 200, 'user');
INSERT INTO `user` VALUES (2, 'lisi', '123456', 2, 'user');
INSERT INTO `user` VALUES (3, 'wangwu', '123456', 3000, 'user');
INSERT INTO `user` VALUES (4, 'zhouyu', '123456', 8, 'user');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
