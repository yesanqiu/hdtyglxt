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

 Date: 08/06/2020 20:30:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gb_time
-- ----------------------------
DROP TABLE IF EXISTS `gb_time`;
CREATE TABLE `gb_time`  (
  `gbid` int(11) NOT NULL AUTO_INCREMENT,
  `gb_gid` int(11) NOT NULL,
  `starttime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `endtime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gbstatus` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`gbid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of gb_time
-- ----------------------------
INSERT INTO `gb_time` VALUES (2, 4, '9:00:00', '10:00:00', '0');
INSERT INTO `gb_time` VALUES (3, 4, '11:00:00', '12:00:00', '0');
INSERT INTO `gb_time` VALUES (4, 4, '15:00:00', '16:00:00', '0');
INSERT INTO `gb_time` VALUES (5, 4, '17:00:00', '18:00:00', '0');
INSERT INTO `gb_time` VALUES (6, 4, '19:00:00', '20:00:00', '0');
INSERT INTO `gb_time` VALUES (7, 4, '21:00:00', '22:00:00', '0');
INSERT INTO `gb_time` VALUES (8, 5, '9:00:00', '10:00:00', '0');
INSERT INTO `gb_time` VALUES (9, 5, '11:00:00', '12:00:00', '0');
INSERT INTO `gb_time` VALUES (10, 5, '15:00:00', '16:00:00', '0');
INSERT INTO `gb_time` VALUES (11, 5, '17:00:00', '18:00:00', '0');
INSERT INTO `gb_time` VALUES (12, 5, '19:00:00', '20:00:00', '0');
INSERT INTO `gb_time` VALUES (13, 5, '21:00:00', '22:00:00', '0');
INSERT INTO `gb_time` VALUES (14, 6, '9:00:00', '10:00:00', '0');
INSERT INTO `gb_time` VALUES (15, 6, '11:00:00', '12:00:00', '0');
INSERT INTO `gb_time` VALUES (16, 6, '15:00:00', '16:00:00', '0');
INSERT INTO `gb_time` VALUES (17, 6, '17:00:00', '18:00:00', '0');
INSERT INTO `gb_time` VALUES (18, 6, '19:00:00', '20:00:00', '0');
INSERT INTO `gb_time` VALUES (19, 6, '21:00:00', '22:00:00', '0');
INSERT INTO `gb_time` VALUES (20, 7, '9:00:00', '10:00:00', '0');
INSERT INTO `gb_time` VALUES (21, 7, '11:00:00', '12:00:00', '0');
INSERT INTO `gb_time` VALUES (22, 7, '15:00:00', '16:00:00', '0');
INSERT INTO `gb_time` VALUES (23, 7, '17:00:00', '18:00:00', '0');
INSERT INTO `gb_time` VALUES (24, 7, '19:00:00', '20:00:00', '0');
INSERT INTO `gb_time` VALUES (25, 7, '21:00:00', '22:00:00', '0');
INSERT INTO `gb_time` VALUES (26, 8, '9:00:00', '10:00:00', '0');
INSERT INTO `gb_time` VALUES (27, 8, '11:00:00', '12:00:00', '0');
INSERT INTO `gb_time` VALUES (28, 8, '15:00:00', '16:00:00', '0');
INSERT INTO `gb_time` VALUES (29, 8, '17:00:00', '18:00:00', '0');
INSERT INTO `gb_time` VALUES (30, 8, '19:00:00', '20:00:00', '0');
INSERT INTO `gb_time` VALUES (31, 8, '21:00:00', '22:00:00', '0');
INSERT INTO `gb_time` VALUES (32, 9, '9:00:00', '10:00:00', '0');
INSERT INTO `gb_time` VALUES (33, 9, '11:00:00', '12:00:00', '0');
INSERT INTO `gb_time` VALUES (34, 9, '15:00:00', '16:00:00', '0');
INSERT INTO `gb_time` VALUES (35, 9, '17:00:00', '18:00:00', '0');
INSERT INTO `gb_time` VALUES (36, 9, '19:00:00', '20:00:00', '0');
INSERT INTO `gb_time` VALUES (37, 9, '21:00:00', '22:00:00', '0');
INSERT INTO `gb_time` VALUES (38, 10, '9:00:00', '10:00:00', '0');
INSERT INTO `gb_time` VALUES (39, 10, '11:00:00', '12:00:00', '0');
INSERT INTO `gb_time` VALUES (40, 10, '15:00:00', '16:00:00', '0');
INSERT INTO `gb_time` VALUES (41, 10, '17:00:00', '18:00:00', '0');
INSERT INTO `gb_time` VALUES (42, 10, '19:00:00', '20:00:00', '0');
INSERT INTO `gb_time` VALUES (43, 10, '21:00:00', '22:00:00', '0');
INSERT INTO `gb_time` VALUES (44, 11, '9:00:00', '10:00:00', '0');
INSERT INTO `gb_time` VALUES (45, 11, '11:00:00', '12:00:00', '0');
INSERT INTO `gb_time` VALUES (46, 11, '15:00:00', '16:00:00', '0');
INSERT INTO `gb_time` VALUES (47, 11, '17:00:00', '18:00:00', '0');
INSERT INTO `gb_time` VALUES (48, 11, '19:00:00', '20:00:00', '0');
INSERT INTO `gb_time` VALUES (49, 11, '21:00:00', '22:00:00', '0');
INSERT INTO `gb_time` VALUES (50, 12, '9:00:00', '10:00:00', '0');
INSERT INTO `gb_time` VALUES (51, 12, '11:00:00', '12:00:00', '0');
INSERT INTO `gb_time` VALUES (52, 12, '15:00:00', '16:00:00', '0');
INSERT INTO `gb_time` VALUES (53, 12, '17:00:00', '18:00:00', '0');
INSERT INTO `gb_time` VALUES (54, 12, '19:00:00', '20:00:00', '0');
INSERT INTO `gb_time` VALUES (55, 12, '21:00:00', '22:00:00', '0');

SET FOREIGN_KEY_CHECKS = 1;
