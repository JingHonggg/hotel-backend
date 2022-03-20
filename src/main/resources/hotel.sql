/*
 Navicat Premium Data Transfer

 Source Server         : 本地环境
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : hotel

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 21/03/2022 02:50:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for background
-- ----------------------------
DROP TABLE IF EXISTS `background`;
CREATE TABLE `background`  (
  `back_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`back_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '超级管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of background
-- ----------------------------
INSERT INTO `background` VALUES ('admin', '123456');

-- ----------------------------
-- Table structure for check_customer_relation
-- ----------------------------
DROP TABLE IF EXISTS `check_customer_relation`;
CREATE TABLE `check_customer_relation`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `check_in_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '入住信息id',
  `customer_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '入住客户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1505606208928862211 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '入住客人与入住信息对应表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of check_customer_relation
-- ----------------------------
INSERT INTO `check_customer_relation` VALUES (1505572168270872578, '1505571889588731906', '1505571959486808066', '2022-03-20 23:49:11', '2022-03-20 23:49:11');
INSERT INTO `check_customer_relation` VALUES (1505585556602486786, '1505571889588731906', '1505585556531183617', '2022-03-21 00:42:23', '2022-03-21 00:42:23');
INSERT INTO `check_customer_relation` VALUES (1505606208928862210, '1505606208043864065', '1505606208157110274', '2022-03-21 02:04:27', '2022-03-21 02:04:27');

-- ----------------------------
-- Table structure for check_in
-- ----------------------------
DROP TABLE IF EXISTS `check_in`;
CREATE TABLE `check_in`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `room_number` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '房间编号',
  `registrant` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '开房人',
  `cash_pledge` decimal(10, 2) NOT NULL COMMENT '押金',
  `should_pay` decimal(10, 2) NOT NULL COMMENT '应付金额',
  `real_pay` decimal(10, 2) NOT NULL COMMENT '实付金额',
  `in_date` datetime NOT NULL COMMENT '入住时间',
  `out_date` datetime NOT NULL COMMENT '离店时间',
  `created_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '入住信息登记人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已离店',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1505606208043864066 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '入住信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of check_in
-- ----------------------------
INSERT INTO `check_in` VALUES (1505571889588731906, '101', 'CB', 100.00, 200.00, 300.00, '2022-03-20 16:00:00', '2022-03-22 16:00:00', 'admin', '2022-03-20 00:00:00', '2022-03-21 00:00:00', b'1');
INSERT INTO `check_in` VALUES (1505606208043864065, '333', 'adad', 123.00, 123.00, 123.00, '2022-03-20 16:00:00', '2022-03-22 16:00:00', '123', '2022-03-21 00:00:00', '2022-03-21 00:00:00', b'1');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客人姓名',
  `phone` bigint NOT NULL COMMENT '客人手机号',
  `id_number` bigint NOT NULL COMMENT '客人身份证号码',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`, `id_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1505606208157110275 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '入住客人表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1505571959486808066, 'C1', 12312312312, 123123123, '2022-03-20 23:48:21', '2022-03-20 23:48:21');
INSERT INTO `customer` VALUES (1505585556531183617, 'C2', 22222222222, 123123122, '2022-03-21 00:42:23', '2022-03-21 00:43:20');
INSERT INTO `customer` VALUES (1505606208157110274, '11', 22, 123123121, '2022-03-21 02:04:27', '2022-03-21 02:04:27');

-- ----------------------------
-- Table structure for front
-- ----------------------------
DROP TABLE IF EXISTS `front`;
CREATE TABLE `front`  (
  `front_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '前台管理员用户名',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '前台管理员姓名',
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '前台管理员密码',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前台管理员手机号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`front_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '前台管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of front
-- ----------------------------
INSERT INTO `front` VALUES ('0001', 'front1', '123456', '13322222222', '2022-03-17 01:28:51', '2022-03-19 15:38:40');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `room_number` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '房间编号',
  `room_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '房间类型',
  `room_status` tinyint NOT NULL DEFAULT 1 COMMENT '房间状态 1-空闲中 2-打扫中 3-入住中',
  `location` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '房间位置',
  `size` int NOT NULL COMMENT '房间容纳人数',
  `price` decimal(10, 2) NOT NULL COMMENT '当日房价',
  `facility` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间设施',
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件图片地址',
  `created_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建录入人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '房间表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (1, '101', 'A', 1, 'floor 2', 2, 200.00, '齐全', NULL, 'admin', '2022-03-19 00:00:00', '2022-03-19 00:00:00');
INSERT INTO `room` VALUES (2, '102', 'A', 1, 'floor 2', 2, 180.00, NULL, NULL, 'admin', '2022-03-19 00:00:00', '2022-03-19 00:00:00');
INSERT INTO `room` VALUES (3, '103', 'A', 1, 'floor 2', 2, 180.00, NULL, NULL, 'admin', '2022-03-19 15:45:22', '2022-03-19 17:01:22');
INSERT INTO `room` VALUES (4, '104', 'A', 1, 'floor 2', 2, 180.00, NULL, NULL, 'admin', '2022-03-19 15:45:22', '2022-03-19 17:01:24');
INSERT INTO `room` VALUES (5, '105', 'A', 1, 'floor 2', 2, 180.00, 'okok', 'E:\\ProjectSpace\\Hotel\\hotel-backend\\roomPicture\\1647798537659.jpg', 'admin', '2022-03-19 00:00:00', '2022-03-19 00:00:00');

SET FOREIGN_KEY_CHECKS = 1;
