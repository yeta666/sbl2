/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50544
Source Host           : localhost:3306
Source Database       : sbl2

Target Server Type    : MYSQL
Target Server Version : 50544
File Encoding         : 65001

Date: 2018-05-17 21:57:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for function
-- ----------------------------
DROP TABLE IF EXISTS `function`;
CREATE TABLE `function` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `parentId` int(11) NOT NULL,
  `url` varchar(50) NOT NULL,
  `level` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of function
-- ----------------------------
INSERT INTO `function` VALUES ('1', '作业管理', '-1', '/homework', '1');
INSERT INTO `function` VALUES ('2', '学生管理', '-1', '/student', '1');
INSERT INTO `function` VALUES ('3', '老师管理', '-1', '/teacher', '1');
INSERT INTO `function` VALUES ('4', '我的作业', '1', '/homework/myHomework', '2');
INSERT INTO `function` VALUES ('5', '学生信息管理', '2', '/student/studentInfo', '2');
INSERT INTO `function` VALUES ('6', '老师信息管理', '3', '/teacher/teacherInfo', '2');
INSERT INTO `function` VALUES ('7', '老师考勤管理', '3', '/teacher/teacherCheckingIn', '2');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '学生');
INSERT INTO `role` VALUES ('2', '老师');
INSERT INTO `role` VALUES ('3', '管理员');

-- ----------------------------
-- Table structure for role_function
-- ----------------------------
DROP TABLE IF EXISTS `role_function`;
CREATE TABLE `role_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL,
  `functionId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_function
-- ----------------------------
INSERT INTO `role_function` VALUES ('1', '1', '1');
INSERT INTO `role_function` VALUES ('2', '2', '1');
INSERT INTO `role_function` VALUES ('3', '2', '2');
INSERT INTO `role_function` VALUES ('4', '3', '1');
INSERT INTO `role_function` VALUES ('5', '3', '2');
INSERT INTO `role_function` VALUES ('6', '3', '3');
INSERT INTO `role_function` VALUES ('7', '1', '4');
INSERT INTO `role_function` VALUES ('8', '2', '5');
INSERT INTO `role_function` VALUES ('9', '3', '6');
INSERT INTO `role_function` VALUES ('10', '2', '4');
INSERT INTO `role_function` VALUES ('11', '3', '4');
INSERT INTO `role_function` VALUES ('12', '3', '5');
INSERT INTO `role_function` VALUES ('14', '3', '7');

-- ----------------------------
-- Table structure for seckill
-- ----------------------------
DROP TABLE IF EXISTS `seckill`;
CREATE TABLE `seckill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `number` int(11) NOT NULL,
  `startTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `endTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seckill
-- ----------------------------
INSERT INTO `seckill` VALUES ('1', '1元秒杀iphoneX', '6', '2018-05-17 15:51:02', '2018-05-18 15:51:07', '2018-05-17 18:59:31');
INSERT INTO `seckill` VALUES ('2', '2元秒杀iphone8', '20', '2018-05-17 15:51:57', '2018-05-18 15:52:00', '2018-05-17 17:16:55');
INSERT INTO `seckill` VALUES ('3', '3元秒杀iphone7', '30', '2018-05-17 15:52:26', '2018-05-18 15:52:29', '2018-05-17 17:16:56');

-- ----------------------------
-- Table structure for seckill_successed
-- ----------------------------
DROP TABLE IF EXISTS `seckill_successed`;
CREATE TABLE `seckill_successed` (
  `seckillId` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `state` tinyint(1) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`username`,`seckillId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seckill_successed
-- ----------------------------
INSERT INTO `seckill_successed` VALUES ('1', 'yeta1', 'yeta1', '1', '2018-05-17 19:29:25');
INSERT INTO `seckill_successed` VALUES ('1', 'yeta2', 'yeta2', '1', '2018-05-17 19:37:53');
INSERT INTO `seckill_successed` VALUES ('2', 'yeta2', 'yeta2', '1', '2018-05-17 19:31:32');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `state` tinyint(1) unsigned DEFAULT NULL,
  `code` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('4', 'yeta1', 'yeta1', 'yeta1', '1806672430@qq.com', '1', '05d55cd5e35040eb9ef87f2256313ce8');
INSERT INTO `user` VALUES ('5', 'admin', 'admin', 'admin', 'admin@admin.com', '1', 'admin');
INSERT INTO `user` VALUES ('6', 'student', 'student', 'student', 'student@student.com', '1', 'student');
INSERT INTO `user` VALUES ('7', 'teacher', 'teacher', 'teacher', 'teacher@teacher.com', '1', 'teacher');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '5', '3');
INSERT INTO `user_role` VALUES ('2', '6', '1');
INSERT INTO `user_role` VALUES ('3', '7', '2');
