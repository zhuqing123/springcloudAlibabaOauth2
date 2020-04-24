/*
 Navicat Premium Data Transfer

 Source Server         : 75.51.47.123（测试）
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 75.51.47.123:3306
 Source Schema         : yammii

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 09/04/2020 18:03:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支持的授权方式',
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL COMMENT 'access_token有效期（单位秒）',
  `refresh_token_validity` int(11) NULL DEFAULT NULL COMMENT 'refresh_token有效期（单位秒）',
  `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'oauth2的client表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('app', NULL, '$2a$10$fyVSmsxviicFFL/uI0Uha.RhTsGKtZnho9Pz9x5CwvfqwcaT3qXg2', 'app', 'authorization_code,password,refresh_token', NULL, 'ROLE_APP', 2592000, 31536000, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('background', NULL, '$2a$10$ICERxct8oihUnkj6vHNQjejctaZzOILlsjGx0yyT.hoI5uwBotnsC', 'background', 'authorization_code,password,refresh_token', NULL, 'ROLE_BACKGROUND', 2592000, 31536000, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('pos', NULL, '$2a$10$vU9qkuhuog27vU9.IjodH.Sd2NDsVEibE7f4zSYcGBXBOnt8hPCxq', 'pos', 'authorization_code,password,refresh_token', NULL, 'ROLE_POS', 2592000, 31536000, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('superadmin', NULL, '$2a$10$L8f1YNELGABtZwiYJxMmPOfhGorAVzTSRT72DNCyk1oBG92IAnB8i', 'superadmin', 'authorization_code,password,refresh_token', NULL, 'ROLE_SUPERADMIN', 2592000, 31536000, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('system', NULL, '$2a$10$L6PBjVUSZZ/A/UQvHfWvKeoXIkpk.FfVisRSFqipMCbMfXS5rQQ.m', 'server', 'authorization_code,password,refresh_token', NULL, NULL, 2592000, 31536000, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
