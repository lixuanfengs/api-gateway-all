/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.107
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : 192.168.1.107:3307
 Source Schema         : api-gateway

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 25/08/2023 09:59:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for application_interface
-- ----------------------------
DROP TABLE IF EXISTS `application_interface`;
CREATE TABLE `application_interface`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `system_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '系统标识',
  `interface_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '接口标识',
  `interface_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '接口名称',
  `interface_version` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '接口版本',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx`(`system_id` ASC, `interface_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of application_interface
-- ----------------------------
INSERT INTO `application_interface` VALUES (1, 'api-gateway-test', 'cn.cactusli.gateway.rpc.IActivityBooth', '活动平台', '1.0.0', '2023-06-26 11:01:02', '2023-06-26 11:01:02');

-- ----------------------------
-- Table structure for application_interface_method
-- ----------------------------
DROP TABLE IF EXISTS `application_interface_method`;
CREATE TABLE `application_interface_method`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `system_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '系统标识',
  `interface_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '接口标识',
  `method_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '方法标识',
  `method_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '方法名称',
  `parameter_type` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数类型；(RPC 限定单参数注册)；new String[]{\"java.lang.String\"}、new String[]{\"cn.cactusli.gateway.rpc.dto.XReq\"}',
  `uri` varchar(126) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '网关接口',
  `http_command_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '接口类型；GET、POST、PUT、DELETE',
  `auth` int NULL DEFAULT NULL COMMENT 'true = 1是、false = 0否',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx`(`system_id` ASC, `interface_id` ASC, `method_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of application_interface_method
-- ----------------------------
INSERT INTO `application_interface_method` VALUES (1, 'api-gateway-test', 'cn.cactusli.gateway.rpc.IActivityBooth', 'sayHello', '测试方法', 'java.lang.String', '/cactus/activity/sayHello', 'GET', 0, '2023-06-26 10:59:22', '2023-06-26 10:59:22');
INSERT INTO `application_interface_method` VALUES (2, 'api-gateway-test', 'cn.cactusli.gateway.rpc.IActivityBooth', 'insert', '插入方法', 'cn.cactusli.gateway.rpc.dto.XReq', '/cactus/activity/insert', 'POST', 1, '2023-06-26 10:59:22', '2023-06-26 10:59:22');

-- ----------------------------
-- Table structure for application_system
-- ----------------------------
DROP TABLE IF EXISTS `application_system`;
CREATE TABLE `application_system`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `system_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '系统标识',
  `system_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '系统名称',
  `system_type` varchar(4) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '系统类型；RPC、HTTP',
  `system_registry` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '注册中心',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_systemId`(`system_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of application_system
-- ----------------------------
INSERT INTO `application_system` VALUES (1, 'api-gateway-test', '网关测试系统', 'RPC', 'zookeeper://192.168.1.107:2181', '2023-06-26 10:59:11', '2023-06-26 10:59:11');

-- ----------------------------
-- Table structure for gateway_distribution
-- ----------------------------
DROP TABLE IF EXISTS `gateway_distribution`;
CREATE TABLE `gateway_distribution`  (
  `id` int NOT NULL COMMENT '自增主键',
  `group_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '分组标识',
  `gateway_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '网关标识',
  `system_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '系统标识',
  `system_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '系统名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gateway_distribution
-- ----------------------------
INSERT INTO `gateway_distribution` VALUES (1, '10008', 'api-gateway-g4', 'api-gateway-test', '网关测试系统', '2023-07-28 15:54:58', '2023-07-28 15:55:01');

-- ----------------------------
-- Table structure for gateway_server
-- ----------------------------
DROP TABLE IF EXISTS `gateway_server`;
CREATE TABLE `gateway_server`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `group_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '分组标识',
  `group_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '分组名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gateway_server
-- ----------------------------
INSERT INTO `gateway_server` VALUES (1, '10008', '缺省的');

-- ----------------------------
-- Table structure for gateway_server_detail
-- ----------------------------
DROP TABLE IF EXISTS `gateway_server_detail`;
CREATE TABLE `gateway_server_detail`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `group_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '分组标识',
  `gateway_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '网关标识',
  `gateway_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '网关名称',
  `gateway_address` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '网关地址：127.0.0.1',
  `status` varchar(4) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '服务状态：0不可用、1可使用',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_gateway`(`gateway_id` ASC, `gateway_address` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gateway_server_detail
-- ----------------------------
INSERT INTO `gateway_server_detail` VALUES (10, '10001', 'api-gateway-g1', '电商支付网关', '127.0.0.196', '1', '2022-11-06 12:57:55', '2022-11-06 12:57:55');
INSERT INTO `gateway_server_detail` VALUES (11, '10001', 'api-gateway-g2', '电商支付网关', '127.0.0.197', '1', '2022-11-06 11:37:01', '2022-11-06 11:37:01');
INSERT INTO `gateway_server_detail` VALUES (12, '10001', 'api-gateway-g3', '电商配送网关', '127.0.0.198', '1', '2022-11-06 11:37:01', '2022-11-06 11:37:01');
INSERT INTO `gateway_server_detail` VALUES (14, '10008', 'api-gateway-g4', '电商配送网关', '127.0.0.1', '1', '2023-06-28 10:41:34', '2023-06-28 10:41:34');
INSERT INTO `gateway_server_detail` VALUES (16, '10001', 'api-gateway-g4', '电商配送网关', '127.0.0.1:7399', '1', '2023-08-07 17:19:14', '2023-08-07 17:19:14');

-- ----------------------------
-- Table structure for http_statement
-- ----------------------------
DROP TABLE IF EXISTS `http_statement`;
CREATE TABLE `http_statement`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `application` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '应用名称',
  `interface_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '服务接口；RPC、其他',
  `method_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT ' 服务方法；RPC#method',
  `parameter_type` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '参数类型(RPC 限定单参数注册)；new String[]{\"java.lang.String\"}、new String[]{\"cn.cactusli.gateway.rpc.dto.XReq\"}',
  `uri` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '网关接口',
  `http_command_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '接口类型；GET、POST、PUT、DELETE',
  `auth` int NOT NULL DEFAULT 0 COMMENT 'true = 1是、false = 0否',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of http_statement
-- ----------------------------
INSERT INTO `http_statement` VALUES (1, 'api-gateway-test', 'cn.cactusli.gateway.rpc.IActivityBooth', 'sayHello', 'java.lang.String', '/cactus/activity/sayHello', 'GET', 0, '2023-06-20 14:45:00', '2023-06-20 14:45:00');
INSERT INTO `http_statement` VALUES (2, 'api-gateway-test', 'cn.cactusli.gateway.rpc.IActivityBooth', 'insert', 'cn.cactusli.gateway.rpc.dto.XReq', '/cactus/activity/insert', 'POST', 1, '2023-06-20 14:45:00', '2023-06-20 14:45:00');

SET FOREIGN_KEY_CHECKS = 1;
