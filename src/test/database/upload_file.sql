/*
Navicat MySQL Data Transfer

Source Server         : 本地测试
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2018-07-24 11:48:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `upload_file`
-- ----------------------------
DROP TABLE IF EXISTS `upload_file`;
CREATE TABLE `upload_file` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `pdf_file_name` varchar(255) DEFAULT NULL,
  `file_index` int(255) DEFAULT NULL,
  `input_item_id` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `flag` varchar(255) DEFAULT NULL,
  `water_mark_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of upload_file
-- ----------------------------
INSERT INTO `upload_file` VALUES ('1', 'test', '/jj', 'jpg', 'test', null, null, null, '2018-07-24 11:47:32', '1', null);
