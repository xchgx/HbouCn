/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50541
Source Host           : localhost:3306
Source Database       : hboucn

Target Server Type    : MYSQL
Target Server Version : 50541
File Encoding         : 65001

Date: 2015-03-07 14:33:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_hukou
-- ----------------------------
DROP TABLE IF EXISTS `t_hukou`;
CREATE TABLE `t_hukou` (
  `sfz` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `sno` varchar(255) NOT NULL,
  `sex` varchar(255) NOT NULL,
  `profession` varchar(255) NOT NULL,
  `where` varchar(255) NOT NULL,
  `company` varchar(255) NOT NULL,
  PRIMARY KEY (`sfz`),
  UNIQUE KEY `nounique` (`sno`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
