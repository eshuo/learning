/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 04/07/2022 15:17:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for condition_info
-- ----------------------------
DROP TABLE IF EXISTS `condition_info`;
CREATE TABLE `condition_info`  (
    `id` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
    `expression` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `parent_id` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '自引用id ',
    `result_info` varchar(512) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '响应信息',
    `context_info` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
    `param_info_ids` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
    `rule_id` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `condition_info_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '条件表';

-- ----------------------------
-- Records of condition_info
-- ----------------------------
BEGIN;
INSERT INTO `condition_info` (`id`, `expression`, `parent_id`, `result_info`, `context_info`, `param_info_ids`, `rule_id`) VALUES ('001', '指标.锁定次数<3', NULL, NULL, NULL, '3', '123'), ('002', '指标.安全信用>=5', NULL, NULL, NULL, '1', '123'), ('003', '指标.设备 = 000001', NULL, NULL, NULL, NULL, '123'), ('004', '指标.访问时间==null or 指标.访问时间 < new Date()', NULL, NULL, NULL, NULL, '123'), ('005', '指标.认证方式 = 1', '001', NULL, NULL, '1,2,3', NULL), ('011', '认证方式 == 1', NULL, NULL, NULL, '2', NULL), ('012', '安全信用 == 高', NULL, NULL, NULL, '1', NULL), ('013', '锁定次数 >= 3', NULL, NULL, NULL, '3', NULL), ('014', '常用IP in [\'127.0.0.1\',\'192.168.1.1\']', NULL, NULL, NULL, '4', NULL), ('088', '行为节点 == \'xingwei\'', NULL, NULL, NULL, '5', NULL);
COMMIT;

-- ----------------------------
-- Table structure for param_info
-- ----------------------------
DROP TABLE IF EXISTS `param_info`;
CREATE TABLE `param_info`  (
    `id` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
    `fie_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '字段',
    `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
    `type` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '数据类型',
    `c_info` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '类信息',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `paramInfo_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci;

-- ----------------------------
-- Records of param_info
-- ----------------------------
BEGIN;
INSERT INTO `param_info` (`id`, `fie_id`, `title`, `type`, `c_info`) VALUES ('0', 'rule', '规则', 'String', NULL), ('1', 'safe_credit', '安全信用', 'String', NULL), ('2', 'verification', '认证方式', 'String', NULL), ('3', 'lock_num', '锁定次数', 'String', NULL), ('4', 'used_ip', '常用IP', 'String', NULL), ('5', 'used_node', '行为节点', 'Array', NULL);
COMMIT;

-- ----------------------------
-- Table structure for rule_info
-- ----------------------------
DROP TABLE IF EXISTS `rule_info`;
CREATE TABLE `rule_info`  (
    `id` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
    `name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
    `status` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
    `seq` int(11) NULL DEFAULT 8,
    `remark` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
    `express` varchar(2048) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `rule_info_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci;

-- ----------------------------
-- Records of rule_info
-- ----------------------------
BEGIN;
INSERT INTO `rule_info` (`id`, `name`, `status`, `seq`, `remark`, `express`) VALUES ('123', 'test_name', 'test_status', 0, 'test', NULL);
COMMIT;

-- ----------------------------
-- Table structure for u_index
-- ----------------------------
DROP TABLE IF EXISTS `u_index`;
CREATE TABLE `u_index`  (
    `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `index_ids` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `index_info` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of u_index
-- ----------------------------
BEGIN;
INSERT INTO `u_index` (`id`, `user_name`, `index_ids`, `index_info`) VALUES ('1', 'zhangsan', '0,1', '{\"0\":\"66\",\"1\":\"3\"}'), ('2', 'lisi', '1,2,3,4', '{\"1\":\"中\",\"2\":\"1\",\"3\":\"5\",\"4\":\"127.0.0.1\"}'), ('3', 'wangwu', '1,2,3,4,5', '{\"1\":\"中\",\"2\":\"1\",\"3\":\"5\",\"4\":\"127.0.0.1\",\"5\":\"xingwei\"}');
COMMIT;

-- ----------------------------
-- Table structure for user_index
-- ----------------------------
DROP TABLE IF EXISTS `user_index`;
CREATE TABLE `user_index`  (
    `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `safe_credit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `verification` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `device` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `Interview_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `Lock_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `user_index_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of user_index
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
