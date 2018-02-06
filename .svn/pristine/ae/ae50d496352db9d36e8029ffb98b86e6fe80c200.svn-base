/*
Navicat MySQL Data Transfer

Source Server         : Abstract
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : teamwork

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-04-05 17:52:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_invite
-- ----------------------------
DROP TABLE IF EXISTS `tb_invite`;
CREATE TABLE `tb_invite` (
  `id` varchar(64) NOT NULL,
  `inviterId` varchar(64) NOT NULL,
  `inviteTime` varchar(255) NOT NULL,
  `inviteeId` varchar(255) DEFAULT NULL,
  `inviteeName` varchar(255) NOT NULL,
  `inviteeEMail` varchar(255) NOT NULL,
  `teamId` varchar(255) NOT NULL,
  `inviteStatus` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_invite
-- ----------------------------
INSERT INTO `tb_invite` VALUES ('8a1edd19-d9be-4710-b2f8-83937f3c8926', '2a833296-69f6-46b6-a139-74281f7bf0bb', '2017-04-05 10:02:40', '8eb750b6-f154-46e0-83ef-183f164405fc', 'heisenberg', 'heisenberg_123@sina.com', 'acc9d661-2c4a-4be6-a28b-dbccb43426c3', '1');

-- ----------------------------
-- Table structure for tb_position
-- ----------------------------
DROP TABLE IF EXISTS `tb_position`;
CREATE TABLE `tb_position` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_position
-- ----------------------------

-- ----------------------------
-- Table structure for tb_station
-- ----------------------------
DROP TABLE IF EXISTS `tb_station`;
CREATE TABLE `tb_station` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL,
  `stationlevel` int(4) NOT NULL,
  `parentId` varchar(64) DEFAULT NULL,
  `stationCode` varchar(24) NOT NULL,
  `sort` int(4) DEFAULT NULL,
  `remark` text,
  `status` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_station
-- ----------------------------
INSERT INTO `tb_station` VALUES ('23d1d14e-b29d-41a1-9b94-e89bbe8d5455', '董事', '11', '6b7eb2ea-7612-4b5e-ac6f-b577ac490bcd', '0003', '1', '', '1');
INSERT INTO `tb_station` VALUES ('25446197-4d36-4334-b544-4451f908adb2', '董事长', '0', '', '0001', '1', '', '1');
INSERT INTO `tb_station` VALUES ('3398dc46-a9d5-4900-9ad6-31661b692e73', '总经理', '0', '6b7eb2ea-7612-4b5e-ac6f-b577ac490bcd', '0003', '2', '', '1');
INSERT INTO `tb_station` VALUES ('6b7eb2ea-7612-4b5e-ac6f-b577ac490bcd', '副董事长', '1', '25446197-4d36-4334-b544-4451f908adb2', '0002', '1', '', '1');

-- ----------------------------
-- Table structure for tb_team
-- ----------------------------
DROP TABLE IF EXISTS `tb_team`;
CREATE TABLE `tb_team` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL,
  `teamLevel` int(4) NOT NULL,
  `parentId` varchar(64) DEFAULT NULL,
  `teamCode` varchar(24) NOT NULL,
  `sort` int(4) DEFAULT NULL,
  `remark` text,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_team
-- ----------------------------
INSERT INTO `tb_team` VALUES ('1d651322-ff11-427e-9df7-dd1f7adaa0a1', '海豹突击队', '1', 'acc9d661-2c4a-4be6-a28b-dbccb43426c3', '0003', '2', '', '1');
INSERT INTO `tb_team` VALUES ('acc9d661-2c4a-4be6-a28b-dbccb43426c3', '城管大队', '0', '', '0001', '1', '', '1');
INSERT INTO `tb_team` VALUES ('b6c722aa-6dca-4525-b462-68dfebc95d2b', '扫黄一队', '11', 'fac1e470-f8f7-4bb1-a05f-05ea34d0aea3', '0004', '4', '', '1');
INSERT INTO `tb_team` VALUES ('fac1e470-f8f7-4bb1-a05f-05ea34d0aea3', '扫黄大队', '1', 'acc9d661-2c4a-4be6-a28b-dbccb43426c3', '0002', '2', '', '1');

-- ----------------------------
-- Table structure for tb_teammate_name
-- ----------------------------
DROP TABLE IF EXISTS `tb_teammate_name`;
CREATE TABLE `tb_teammate_name` (
  `userId` varchar(255) NOT NULL,
  `teammateId` varchar(255) DEFAULT NULL,
  `teammateName` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_teammate_name
-- ----------------------------
INSERT INTO `tb_teammate_name` VALUES ('2a833296-69f6-46b6-a139-74281f7bf0bb', '8eb750b6-f154-46e0-83ef-183f164405fc', 'heisenberg');

-- ----------------------------
-- Table structure for tb_teammember
-- ----------------------------
DROP TABLE IF EXISTS `tb_teammember`;
CREATE TABLE `tb_teammember` (
  `memberId` varchar(255) NOT NULL,
  `teamId` varchar(255) NOT NULL,
  `depId` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_teammember
-- ----------------------------
INSERT INTO `tb_teammember` VALUES ('8eb750b6-f154-46e0-83ef-183f164405fc', 'acc9d661-2c4a-4be6-a28b-dbccb43426c3', 'acc9d661-2c4a-4be6-a28b-dbccb43426c3');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` varchar(64) NOT NULL,
  `email` varchar(64) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `password` varchar(64) NOT NULL,
  `mainStation` varchar(64) DEFAULT NULL,
  `otherStation` varchar(64) DEFAULT NULL,
  `isalive` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('2a833296-69f6-46b6-a139-74281f7bf0bb', '992047948@qq.com', '刘润枝', '123', '23d1d14e-b29d-41a1-9b94-e89bbe8d5455', '25446197-4d36-4334-b544-4451f908adb2', '1');
INSERT INTO `tb_user` VALUES ('8eb750b6-f154-46e0-83ef-183f164405fc', 'heisenberg_123@sina.com', 'heisenberg_123@sina.com', '841784', '3398dc46-a9d5-4900-9ad6-31661b692e73', null, '1');

-- ----------------------------
-- Table structure for tb_validate_code
-- ----------------------------
DROP TABLE IF EXISTS `tb_validate_code`;
CREATE TABLE `tb_validate_code` (
  `email` varchar(64) NOT NULL,
  `validate_code` varchar(24) NOT NULL,
  `type` varchar(24) NOT NULL,
  `time_out` varchar(24) NOT NULL,
  PRIMARY KEY (`email`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_validate_code
-- ----------------------------

-- ----------------------------
-- Event structure for validate_code_timeout_delete
-- ----------------------------
DROP EVENT IF EXISTS `validate_code_timeout_delete`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` EVENT `validate_code_timeout_delete` ON SCHEDULE EVERY 1 MINUTE STARTS '2017-03-06 23:01:19' ON COMPLETION NOT PRESERVE ENABLE DO delete from tb_validate_code where date(time_out) < NOW()
;;
DELIMITER ;
