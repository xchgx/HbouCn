/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : hboucn

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2014-12-08 13:10:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_teacher
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jgh` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `sex` varchar(255) NOT NULL,
  `age` int(11) NOT NULL,
  `position` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `employeeId` int(11) DEFAULT NULL,
  `schoolId` int(11) DEFAULT NULL,
  `teacherId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_iane886xkg55iprm1oggvc2c9` (`jgh`),
  KEY `FK_fm67ep7ximsr19p2av54i787j` (`employeeId`),
  KEY `FK_quvdivq0u8mnsreyrvghe20ff` (`schoolId`),
  KEY `FK_a5uh0l9x201bggpypxe0fs2s3` (`teacherId`),
  CONSTRAINT `FK_a5uh0l9x201bggpypxe0fs2s3` FOREIGN KEY (`teacherId`) REFERENCES `t_employee` (`id`),
  CONSTRAINT `FK_fm67ep7ximsr19p2av54i787j` FOREIGN KEY (`employeeId`) REFERENCES `t_employee` (`id`),
  CONSTRAINT `FK_quvdivq0u8mnsreyrvghe20ff` FOREIGN KEY (`schoolId`) REFERENCES `t_school` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_teacher
-- ----------------------------
INSERT INTO `t_teacher` VALUES ('1', '20070613', '陈刚', '男', '31', '主任', '15527183314', '招生办主任', null, null, null);
INSERT INTO `t_teacher` VALUES ('2', '20110614', '唐驰', '男', '25', '职员', '13554097880', '招生办', null, null, null);
INSERT INTO `t_teacher` VALUES ('3', '19860404', '杨洁 ', '', '0', '', '', '信息机电工程学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('4', '19860905', '乐群 ', '', '0', '', '', '信息机电工程学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('5', '19870102', '何永胜 ', '', '0', '', '', '思想政治理论课部 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('6', '19871007', '吴彤 ', '', '0', '', '', '现代管理学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('7', '19880603', '徐惠芳 ', '', '0', '', '', '财经学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('8', '19890101', '方立亚 ', '', '0', '', '', '财经学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('9', '19940801', '蒋小茹 ', '', '0', '', '', '财经学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('10', '19940905', '陈运涛 ', '', '0', '', '', '计算机教研室 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('11', '19950301', '胡秀花 ', '', '0', '', '', '财经学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('12', '19960302', '钱香玲 ', '', '0', '', '', '现代管理学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('13', '19961108', '黄国荣 ', '', '0', '', '', '计算机教研室 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('14', '19970201', '游爱华 ', '', '0', '', '', '现代管理学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('15', '19980202', '刘晓东 ', '', '0', '', '', '现代管理学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('16', '19980504', '张莉 ', '', '0', '', '', '现代管理学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('17', '19990201', '李新 ', '', '0', '', '', '信息机电工程学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('18', '19991105', '刘燕 ', '', '0', '', '', '计算机教研室 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('19', '20000101', '陈新民 ', '', '0', '', '', '信息机电工程学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('20', '20010403', '游敏 ', '', '0', '', '', '信息机电工程学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('21', '20010605', '封丽萍 ', '', '0', '', '', '外语教研室 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('22', '20030906', '杨劲涛 ', '', '0', '', '', '信息机电工程学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('23', '20040904', '钱翠 ', '', '0', '', '', '财经学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('24', '20050805', '彭历 ', '', '0', '', '', '现代管理学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('25', '20050910', '肖海莲 ', '', '0', '', '', '现代管理学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('26', '20060304', '许蕾 ', '', '0', '', '', '外语教研室 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('27', '20060614', '王笃学 ', '', '0', '', '', '财经学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('28', '200606YS05 ', '郝晓莹 ', '', '0', '', '', '人文艺术学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('29', '200606YS06 ', '邱文卿 ', '', '0', '', '', '艺术学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('30', '20060806', '桂杉杉 ', '', '0', '', '', '外语教研室 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('31', '20060819', '周琳 ', '', '0', '', '', '信息机电工程学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('32', '20060922', '艾娜 ', '', '0', '', '', '财经学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('33', '20070301', '窦洽 ', '', '0', '', '', '计算机教研室 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('34', '20070819', '张乔乔 ', '', '0', '', '', '思想政治理论课部 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('35', '20070827', '梁华银 ', '', '0', '', '', '信息机电工程学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('36', '20071047', '彭沛 ', '', '0', '', '', '信息机电工程学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('37', '20071048', '陈志忠 ', '', '0', '', '', '计算机教研室 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('38', '20080206', '秦茵 ', '', '0', '', '', '外语教研室 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('39', '20080207', '罗远娟 ', '', '0', '', '', '外语教研室 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('40', '20080816', '郭世帅 ', '', '0', '', '', '信息机电工程学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('41', '20080817', '陈静 ', '', '0', '', '', '现代管理学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('42', '20080818', '刘畅 ', '', '0', '', '', '财经学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('43', '20080819', '彭志权 ', '', '0', '', '', '现代管理学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('44', '20090812', '鲍林娟 ', '', '0', '', '', '思想政治理论课部 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('45', '20090817', '卢舸 ', '', '0', '', '', '信息机电工程学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('46', '20090818', '马俊 ', '', '0', '', '', '信息机电工程学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('47', '20090821', '何敏 ', '', '0', '', '', '外语教研室 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('48', '20090826', '朱晓庆 ', '', '0', '', '', '计算机教研室 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('49', '20100920', '刘觅 ', '', '0', '', '', '现代管理学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('50', '20109029', '刘渊志 ', '', '0', '', '', '财经学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('51', '20110303', '李阳 ', '', '0', '', '', '计算机教研室 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('52', '201105RJ06 ', '刘伟 ', '', '0', '', '', '软件与服务外包工程学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('53', '201106RJ07 ', '陈智 ', '', '0', '', '', '软件与服务外包工程学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('54', '20110808', '邓书杰 ', '', '0', '', '', '财经学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('55', '20110809', '贺晶 ', '', '0', '', '', '财经学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('56', '20110811', '黄庆芳 ', '', '0', '', '', '现代管理学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('57', '20110812', '张雷 ', '', '0', '', '', '财经学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('58', '20110915', '汪妙凡 ', '', '0', '', '', '信息机电工程学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('59', '201201YS09 ', '张平 ', '', '0', '', '', '人文艺术学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('60', '20120201', '赵敏 ', '', '0', '', '', '现代管理学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('61', '20120712', '唐驰 ', '', '0', '', '', '计算机教研室 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('62', '201207RJ10 ', '吴静 ', '', '0', '', '', '软件与服务外包工程学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('63', '20130802', '陈露露 ', '', '0', '', '', '现代管理学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('64', '20130803', '张泽宙 ', '', '0', '', '', '人文艺术学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('65', '20130804', '王丹 ', '', '0', '', '', '人文艺术学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('66', '20131206', '曾德清 ', '', '0', '', '', '信息机电工程学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('67', '20131207', '袁建征 ', '', '0', '', '', '信息机电工程学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('68', '20131208', '胡明飞 ', '', '0', '', '', '信息机电工程学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('69', '20131209', '王文琳 ', '', '0', '', '', '人文艺术学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('70', '20131210', '张小磊 ', '', '0', '', '', '信息机电工程学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('71', '20131211', '钟华 ', '', '0', '', '', '信息机电工程学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('72', '20140301', '邵铃 ', '', '0', '', '', '艺术学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('73', '20140302', '王伟 ', '', '0', '', '', '艺术学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('74', '20140303', '刘辉 ', '', '0', '', '', '财经学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('75', '20140304', '宁红 ', '', '0', '', '', '外语教研室 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('76', '20140305', '张笑贤 ', '', '0', '', '', '现代管理学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('77', '20140306', '周蓓 ', '', '0', '', '', '现代管理学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('78', '20140308', '张晓毓 ', '', '0', '', '', '信息机电工程学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('79', '20140309', '邓哲 ', '', '0', '', '', '信息机电工程学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('80', '20140310', '陈瑞 ', '', '0', '', '', '人文艺术学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('81', '20140912', '张航程 ', '', '0', '', '', '现代管理学院 ', null, null, null);
INSERT INTO `t_teacher` VALUES ('82', 'YJ0005 ', '曾乐 ', '', '0', '', '', '软件与服务外包工程学院 ', null, null, null);
