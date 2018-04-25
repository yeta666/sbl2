/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50544
Source Host           : localhost:3306
Source Database       : sbl2

Target Server Type    : MYSQL
Target Server Version : 50544
File Encoding         : 65001

Date: 2018-04-24 11:29:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'yeta111');
INSERT INTO `user` VALUES ('2', 'yeta2');
INSERT INTO `user` VALUES ('3', 'yeta3');
INSERT INTO `user` VALUES ('4', 'yeta4');
INSERT INTO `user` VALUES ('5', 'ray1');
INSERT INTO `user` VALUES ('6', 'ray2');
INSERT INTO `user` VALUES ('7', 'yeta7');
INSERT INTO `user` VALUES ('11', 'yeta11');
INSERT INTO `user` VALUES ('12', 'yeta11');
