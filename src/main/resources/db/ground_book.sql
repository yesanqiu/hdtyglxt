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

 Date: 08/06/2020 20:30:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ground_book
-- ----------------------------
DROP TABLE IF EXISTS `ground_book`;
CREATE TABLE `ground_book`  (
  `g_gid` int(11) NOT NULL,
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `bstart_time` datetime NOT NULL,
  `bend_time` datetime NOT NULL,
  `userid` int(11) NOT NULL,
  `bcost` decimal(10, 2) NOT NULL,
  `bisuse` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '0未使用，1使用',
  PRIMARY KEY (`bid`) USING BTREE,
  INDEX `ground_book_ground_gid_fk`(`g_gid`) USING BTREE,
  CONSTRAINT `ground_book_ground_gid_fk` FOREIGN KEY (`g_gid`) REFERENCES `ground` (`gid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ground_book
-- ----------------------------
INSERT INTO `ground_book` VALUES (4, 4, '2020-06-07 09:00:00', '2020-06-07 10:00:00', 1, 30.00, '0');
INSERT INTO `ground_book` VALUES (4, 5, '2020-06-08 17:00:00', '2020-06-08 18:00:00', 1, 30.00, '0');
INSERT INTO `ground_book` VALUES (4, 9, '2020-06-08 09:00:00', '2020-06-08 10:00:00', 1, 30.00, '0');
INSERT INTO `ground_book` VALUES (4, 10, '2020-06-08 19:00:00', '2020-06-08 20:00:00', 9, 30.00, '0');
INSERT INTO `ground_book` VALUES (4, 11, '2020-06-09 09:00:00', '2020-06-09 10:00:00', 9, 30.00, '0');

SET FOREIGN_KEY_CHECKS = 1;
