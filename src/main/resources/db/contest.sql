/*
 Navicat Premium Data Transfer

 Source Server         : hdtyglxt
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : hdtyglxt

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 26/06/2020 13:02:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for contest
-- ----------------------------
DROP TABLE IF EXISTS `contest`;
CREATE TABLE `contest`  (
  `c_id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `c_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `c_apply` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `c_start_date` datetime(0) NULL DEFAULT NULL,
  `c_end_date` datetime(0) NULL DEFAULT NULL,
  `c_gd_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`c_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of contest
-- ----------------------------
INSERT INTO `contest` VALUES (14, '12312', '123213', '2020-06-26 12:59:00', '2020-06-26 12:59:00', NULL);
INSERT INTO `contest` VALUES (15, '12', '', '2020-06-26 12:59:00', '2020-06-26 12:59:00', NULL);

SET FOREIGN_KEY_CHECKS = 1;
