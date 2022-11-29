/*
 Navicat Premium Data Transfer

 Source Server         : sql
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : gymbook

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 23/11/2022 00:31:07
*/


SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ADMINISTRATOR
-- ----------------------------
DROP TABLE IF EXISTS `ADMINISTRATOR`;
CREATE TABLE `ADMINISTRATOR` (
  `Ad_id` int unsigned NOT NULL AUTO_INCREMENT,
  `Admin_name` varchar(64) NOT NULL,
  `Admin_pwd` varchar(64) NOT NULL,
  `Admin_phone` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Admin_email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Activated_status` int unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`Ad_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of ADMINISTRATOR
-- ----------------------------
BEGIN;
INSERT INTO `ADMINISTRATOR` VALUES (3, 'Admin1', '123456', '5850000000', 'xxx@xxx.com', 1);
INSERT INTO `ADMINISTRATOR` VALUES (8, 'Admin2', '123456', '+8612312345678', 'admin2@gymbooking.com', 1);
INSERT INTO `ADMINISTRATOR` VALUES (10, 'admin', 'admin', '12345678123', '333@333.com', 1);
COMMIT;

-- ----------------------------
-- Table structure for RESERVATION_RECORD
-- ----------------------------
DROP TABLE IF EXISTS `RESERVATION_RECORD`;
CREATE TABLE `RESERVATION_RECORD` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Gym_name` varchar(64) NOT NULL,
  `User_id` int NOT NULL,
  `Visit_date` date NOT NULL,
  `Visit_time` varchar(64) NOT NULL,
  `Create_time` datetime NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Gym_name` (`Gym_name`),
  KEY `User_id` (`User_id`),
  KEY `Visit_date` (`Visit_date`),
  CONSTRAINT `Gym_name` FOREIGN KEY (`Gym_name`) REFERENCES `VENUE` (`Gym_name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `User_id` FOREIGN KEY (`User_id`) REFERENCES `USER` (`User_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Visit_date` FOREIGN KEY (`Visit_date`) REFERENCES `VENUE` (`Date`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of RESERVATION_RECORD
-- ----------------------------
BEGIN;
INSERT INTO `RESERVATION_RECORD` VALUES (1, 'venue 1', 1, '2022-11-21', '14:00-15:00', '2022-11-19 14:16:35');
INSERT INTO `RESERVATION_RECORD` VALUES (2, 'venue 1', 1, '2022-11-21', '15:00-16:00', '2022-11-22 20:04:46');
COMMIT;

-- ----------------------------
-- Table structure for SUPER_ADMINISTRATOR
-- ----------------------------
DROP TABLE IF EXISTS `SUPER_ADMINISTRATOR`;
CREATE TABLE `SUPER_ADMINISTRATOR` (
  `Su_id` int unsigned NOT NULL AUTO_INCREMENT,
  `Su_user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Su_user_pwd` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`Su_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of SUPER_ADMINISTRATOR
-- ----------------------------
BEGIN;
INSERT INTO `SUPER_ADMINISTRATOR` VALUES (1, 'SuperAdmin1', '12345678');
INSERT INTO `SUPER_ADMINISTRATOR` VALUES (2, 'SuperAdmin2', '12345678');
INSERT INTO `SUPER_ADMINISTRATOR` VALUES (9, 'SuperAdmin3', '12345678');
INSERT INTO `SUPER_ADMINISTRATOR` VALUES (10, 'su', '123');
COMMIT;

-- ----------------------------
-- Table structure for USER
-- ----------------------------
DROP TABLE IF EXISTS `USER`;
CREATE TABLE `USER` (
  `User_id` int NOT NULL AUTO_INCREMENT,
  `User_name` varchar(64) NOT NULL,
  `User_pwd` varchar(64) NOT NULL,
  `Age` int DEFAULT NULL,
  `Gender` varchar(64) DEFAULT NULL,
  `User_phone` varchar(64) DEFAULT NULL,
  `User_email` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`User_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of USER
-- ----------------------------
BEGIN;
INSERT INTO `USER` VALUES (1, 'User1', '1234', 20, 'Male', '5851111111', 'yyy@yyy.com');
INSERT INTO `USER` VALUES (2, 'User2', '1234', 16, 'Female', '5852222222', 'xxx@xxx.com');
INSERT INTO `USER` VALUES (3, 'fzq', '123', 16, 'Female', '5852222222', 'xxx@xxx.com');
COMMIT;

-- ----------------------------
-- Table structure for VENUE
-- ----------------------------
DROP TABLE IF EXISTS `VENUE`;
CREATE TABLE `VENUE` (
  `Gym_name` varchar(64) NOT NULL,
  `Unit_price` int NOT NULL DEFAULT '0',
  `Gym_status` int NOT NULL DEFAULT '1',
  `Fourteen` int NOT NULL DEFAULT '1',
  `Fifteen` int NOT NULL DEFAULT '1',
  `Sixteen` int NOT NULL DEFAULT '1',
  `Seventeen` int NOT NULL DEFAULT '1',
  `Eighteen` int NOT NULL DEFAULT '1',
  `Nineteen` int NOT NULL DEFAULT '1',
  `Twenty` int NOT NULL DEFAULT '1',
  `Date` date NOT NULL,
  PRIMARY KEY (`Gym_name`,`Date`),
  KEY `Gym_name` (`Gym_name`),
  KEY `Date` (`Date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of VENUE
-- ----------------------------
BEGIN;
INSERT INTO `VENUE` VALUES ('venue 1', 7, 1, 1, 1, 1, 1, 1, 1, 1, '2022-11-20');
INSERT INTO `VENUE` VALUES ('venue 1', 21, 1, 0, 0, 1, 1, 1, 1, 1, '2022-11-21');
INSERT INTO `VENUE` VALUES ('venue 2', 67, 1, 1, 1, 1, 1, 1, 1, 1, '2022-11-20');
INSERT INTO `VENUE` VALUES ('venue 2', 87, 1, 1, 1, 1, 1, 1, 1, 1, '2022-11-21');
INSERT INTO `VENUE` VALUES ('venue 2', 21, 1, 1, 1, 1, 1, 1, 1, 1, '2022-11-22');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
