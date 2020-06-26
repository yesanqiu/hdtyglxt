/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : hdtyglxt

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 08/06/2020 20:30:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ground
-- ----------------------------
DROP TABLE IF EXISTS `ground`;
CREATE TABLE `ground`  (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `gtype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gapplication` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `glocation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gstatus` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '0开放,1关闭',
  `gcost` decimal(10, 2) NOT NULL,
  PRIMARY KEY (`gid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ground
-- ----------------------------
INSERT INTO `ground` VALUES (4, '网球场', '比赛和上课用地', '东区体育场', '0', 30.00);
INSERT INTO `ground` VALUES (5, '乒乓球室', '比赛和上课用地', '体育馆三楼', '0', 20.00);
INSERT INTO `ground` VALUES (6, '羽毛球室', '比赛和上课用地', '体育馆三楼', '1', 20.00);
INSERT INTO `ground` VALUES (7, '篮球场', '比赛和上课用地', '体育馆一楼', '0', 20.00);
INSERT INTO `ground` VALUES (8, '排球场', '比赛和上课用地', '体育馆一楼', '0', 50.00);
INSERT INTO `ground` VALUES (9, '篮球场', '比赛用地', '体育馆二楼', '0', 60.00);
INSERT INTO `ground` VALUES (10, '篮球场', '上课用地', '东区二饭一号篮球场', '0', 10.00);
INSERT INTO `ground` VALUES (11, '羽毛球场', '上课和比赛用地', '东区一号羽毛球场', '0', 10.00);

SET FOREIGN_KEY_CHECKS = 1;
