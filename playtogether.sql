/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : playtogether

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 14/10/2020 15:02:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for extension
-- ----------------------------
DROP TABLE IF EXISTS `extension`;
CREATE TABLE `extension`  (
  `EID` int(11) NOT NULL AUTO_INCREMENT,
  `Etype` int(11) NOT NULL,
  `Ename` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `UID` int(11) NOT NULL,
  `Jnum` int(11) NOT NULL,
  `location` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `statrTime` date NULL DEFAULT NULL,
  PRIMARY KEY (`EID`) USING BTREE,
  INDEX `UID`(`UID`) USING BTREE,
  CONSTRAINT `extension_ibfk_1` FOREIGN KEY (`UID`) REFERENCES `user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of extension
-- ----------------------------

-- ----------------------------
-- Table structure for ue
-- ----------------------------
DROP TABLE IF EXISTS `ue`;
CREATE TABLE `ue`  (
  `UID` int(11) NOT NULL,
  `EID` int(11) NOT NULL,
  PRIMARY KEY (`UID`, `EID`) USING BTREE,
  INDEX `EID`(`EID`) USING BTREE,
  CONSTRAINT `ue_ibfk_1` FOREIGN KEY (`UID`) REFERENCES `user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ue_ibfk_2` FOREIGN KEY (`EID`) REFERENCES `extension` (`EID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ue
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int(10) NOT NULL AUTO_INCREMENT,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `join_num` int(11) NULL DEFAULT NULL,
  `create_num` int(11) NULL DEFAULT NULL,
  `state` int(11) NOT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sid` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE INDEX `Email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1109 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1004, '$2a$10$0CL9xhJ5iL6A..CI0MJiH.dslwM922hMvzhdGbvCIU4uZK7Di0biu', '张三', 0, 0, 0, '1473054623@qq.com', 'null', NULL);
INSERT INTO `user` VALUES (1101, '$2a$10$Ag8YTt2oQ43SEbRRDjKeCe5btfD6llJepAi8Nbpo.EIa5p.bIRy6u', 'chase', 0, 0, 0, 'ssuxue@163.com', 'https://pic2.zhimg.com/80/v2-95f8a6c02321bd8672c7b0ff5be37cdb_720w.jpg', NULL);
INSERT INTO `user` VALUES (1111, '$2a$10$5mDLo6Zy6ocKhsrqa/QxKuYsAEGlGAQxsR1Tk1UioOGHtWOIdqEc.', '小熊饼干', 0, 0, 0, '805048477@qq.com', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
