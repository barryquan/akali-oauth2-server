/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : akali_oauth2_server

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 18/01/2020 15:25:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customer_resources
-- ----------------------------
DROP TABLE IF EXISTS `customer_resources`;
CREATE TABLE `customer_resources`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `is_active` bit(1) NULL DEFAULT NULL,
  `modified_date` datetime(0) NULL DEFAULT NULL,
  `resource_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `resource_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_resources
-- ----------------------------
INSERT INTO `customer_resources` VALUES (1, '2020-01-18 13:44:45', b'1', '2020-01-18 13:44:48', 'sync-data-service', '同步服务');

-- ----------------------------
-- Table structure for customer_role_scop
-- ----------------------------
DROP TABLE IF EXISTS `customer_role_scop`;
CREATE TABLE `customer_role_scop`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `is_active` bit(1) NULL DEFAULT NULL,
  `modified_date` datetime(0) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  `scop_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKfwyh6cnjjp43vtwasbo8cqdrf`(`role_id`) USING BTREE,
  INDEX `FKb8yvbg1l8pqqryybd1hbx1lfv`(`scop_id`) USING BTREE,
  CONSTRAINT `FKb8yvbg1l8pqqryybd1hbx1lfv` FOREIGN KEY (`scop_id`) REFERENCES `customer_scop` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKfwyh6cnjjp43vtwasbo8cqdrf` FOREIGN KEY (`role_id`) REFERENCES `oauth_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_role_scop
-- ----------------------------
INSERT INTO `customer_role_scop` VALUES (1, '2020-01-18 14:22:35', b'1', '2020-01-18 14:22:37', 1, 1);
INSERT INTO `customer_role_scop` VALUES (2, '2020-01-18 14:22:45', b'1', '2020-01-18 14:22:48', 1, 2);

-- ----------------------------
-- Table structure for customer_scop
-- ----------------------------
DROP TABLE IF EXISTS `customer_scop`;
CREATE TABLE `customer_scop`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `is_active` bit(1) NULL DEFAULT NULL,
  `modified_date` datetime(0) NULL DEFAULT NULL,
  `scop_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `scop_perms` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_scop
-- ----------------------------
INSERT INTO `customer_scop` VALUES (1, '2020-01-18 14:20:26', b'1', '2020-01-18 14:20:28', '新增', 'user.add');
INSERT INTO `customer_scop` VALUES (2, '2020-01-18 14:20:49', b'1', '2020-01-18 14:20:53', '更新', 'user.update');

-- ----------------------------
-- Table structure for grant_types
-- ----------------------------
DROP TABLE IF EXISTS `grant_types`;
CREATE TABLE `grant_types`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `is_active` bit(1) NULL DEFAULT NULL,
  `modified_date` datetime(0) NULL DEFAULT NULL,
  `grant_type_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `grant_type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grant_types
-- ----------------------------
INSERT INTO `grant_types` VALUES (1, '2020-01-18 13:42:07', b'1', '2020-01-18 13:42:12', 'refresh_token', '刷新token模式');
INSERT INTO `grant_types` VALUES (2, '2020-01-18 13:42:50', b'1', '2020-01-18 13:42:52', 'password', '密码模式');
INSERT INTO `grant_types` VALUES (3, '2020-01-18 13:43:12', b'1', '2020-01-18 13:43:09', 'authorization_code', '授权码模式');

-- ----------------------------
-- Table structure for oauth_client
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client`;
CREATE TABLE `oauth_client`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `is_active` bit(1) NULL DEFAULT NULL,
  `modified_date` datetime(0) NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `client_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `redirect_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_6tiq1b1cchcusg2t6oe5mv8qw`(`client_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client
-- ----------------------------
INSERT INTO `oauth_client` VALUES (1, '2020-01-18 13:43:38', b'1', '2020-01-18 13:43:41', 86400, 'oauth2', 'oauth2', 'https://barryquan.github.io', 86400);

-- ----------------------------
-- Table structure for oauth_client_grant_types
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_grant_types`;
CREATE TABLE `oauth_client_grant_types`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `is_active` bit(1) NULL DEFAULT NULL,
  `modified_date` datetime(0) NULL DEFAULT NULL,
  `grant_types_id` int(11) NULL DEFAULT NULL,
  `oauth_client_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKsvyx91s2qkphid5pygkjn9x6j`(`grant_types_id`) USING BTREE,
  INDEX `FK8qbwj4slenf2jpm9hhqo2ssi7`(`oauth_client_id`) USING BTREE,
  CONSTRAINT `FK8qbwj4slenf2jpm9hhqo2ssi7` FOREIGN KEY (`oauth_client_id`) REFERENCES `oauth_client` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKsvyx91s2qkphid5pygkjn9x6j` FOREIGN KEY (`grant_types_id`) REFERENCES `grant_types` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_grant_types
-- ----------------------------
INSERT INTO `oauth_client_grant_types` VALUES (1, '2020-01-18 13:48:42', b'1', '2020-01-18 13:48:45', 1, 1);
INSERT INTO `oauth_client_grant_types` VALUES (2, '2020-01-18 13:48:52', b'1', '2020-01-18 13:48:54', 2, 1);
INSERT INTO `oauth_client_grant_types` VALUES (3, '2020-01-18 13:49:05', b'1', '2020-01-18 13:49:07', 3, 1);

-- ----------------------------
-- Table structure for oauth_client_resources
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_resources`;
CREATE TABLE `oauth_client_resources`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `is_active` bit(1) NULL DEFAULT NULL,
  `modified_date` datetime(0) NULL DEFAULT NULL,
  `customer_resource_id` int(11) NULL DEFAULT NULL,
  `oauth_client_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKeqt4hq4igtb3urm0hrb0l9dbw`(`customer_resource_id`) USING BTREE,
  INDEX `FK1s3mdkk8c9e2hphmn3lfidwi0`(`oauth_client_id`) USING BTREE,
  CONSTRAINT `FK1s3mdkk8c9e2hphmn3lfidwi0` FOREIGN KEY (`oauth_client_id`) REFERENCES `oauth_client` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKeqt4hq4igtb3urm0hrb0l9dbw` FOREIGN KEY (`customer_resource_id`) REFERENCES `customer_resources` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_resources
-- ----------------------------
INSERT INTO `oauth_client_resources` VALUES (1, '2020-01-18 13:45:18', b'1', '2020-01-18 13:45:21', 1, 1);

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `is_active` bit(1) NULL DEFAULT NULL,
  `modified_date` datetime(0) NULL DEFAULT NULL,
  `authentication` longblob NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_role
-- ----------------------------
DROP TABLE IF EXISTS `oauth_role`;
CREATE TABLE `oauth_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `is_active` bit(1) NULL DEFAULT NULL,
  `modified_date` datetime(0) NULL DEFAULT NULL,
  `role_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_role
-- ----------------------------
INSERT INTO `oauth_role` VALUES (1, '2020-01-18 14:21:35', b'1', '2020-01-18 14:21:39', 'ROLE_ADMIN', '管理员');

-- ----------------------------
-- Table structure for oauth_user
-- ----------------------------
DROP TABLE IF EXISTS `oauth_user`;
CREATE TABLE `oauth_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `is_active` bit(1) NULL DEFAULT NULL,
  `modified_date` datetime(0) NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_user
-- ----------------------------
INSERT INTO `oauth_user` VALUES (1, '2020-01-18 13:41:44', b'1', '2020-01-18 13:41:47', '$2a$10$DKHjTFLBjzKni1BJ0vCOv.QG1EL2tTKYfrjYKAVnPpQv4x6Zllu02', 'user');

-- ----------------------------
-- Table structure for oauth_user_role
-- ----------------------------
DROP TABLE IF EXISTS `oauth_user_role`;
CREATE TABLE `oauth_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `is_active` bit(1) NULL DEFAULT NULL,
  `modified_date` datetime(0) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK8wl1eq77d6noru05ccao0jody`(`role_id`) USING BTREE,
  INDEX `FKbbbedu4u7x785lopv8mbttppw`(`user_id`) USING BTREE,
  CONSTRAINT `FK8wl1eq77d6noru05ccao0jody` FOREIGN KEY (`role_id`) REFERENCES `oauth_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKbbbedu4u7x785lopv8mbttppw` FOREIGN KEY (`user_id`) REFERENCES `oauth_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_user_role
-- ----------------------------
INSERT INTO `oauth_user_role` VALUES (1, '2020-01-18 14:22:03', b'1', '2020-01-18 14:22:06', 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
