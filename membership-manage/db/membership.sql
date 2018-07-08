/*
Navicat MySQL Data Transfer

Source Server         : 39.105.0.169
Source Server Version : 50640
Source Host           : 39.105.0.169:3306
Source Database       : membership

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2018-06-25 21:45:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ms_card
-- ----------------------------
DROP TABLE IF EXISTS `ms_card`;
CREATE TABLE `ms_card` (
  `id` int(16) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `user_id` bigint(16) unsigned NOT NULL COMMENT '用户id',
  `phone_number` varchar(64) DEFAULT '' COMMENT '购买会员卡使用的电话号码',
  `seller_id` bigint(16) unsigned NOT NULL COMMENT '商家id',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `discount` double NOT NULL DEFAULT '0.5' COMMENT '折扣',
  `balance` decimal(10,0) DEFAULT '0' COMMENT '余额',
  `consumption_num` int(8) DEFAULT '0' COMMENT '消费次数',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_seller_id` (`seller_id`) USING BTREE,
  KEY `idx_phone_number` (`phone_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ms_card
-- ----------------------------

-- ----------------------------
-- Table structure for ms_order
-- ----------------------------
DROP TABLE IF EXISTS `ms_order`;
CREATE TABLE `ms_order` (
  `id` int(16) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `user_id` int(16) NOT NULL COMMENT '用户id',
  `seller_id` int(16) NOT NULL COMMENT '商家id',
  `order_number` varchar(64) NOT NULL COMMENT '订单id（唯一）',
  `price_before_discount` decimal(10,0) NOT NULL COMMENT '优惠前价格',
  `price_after_discount` decimal(10,0) NOT NULL COMMENT '优惠后价格',
  `is_paid` bit(1) DEFAULT NULL COMMENT '是否付款',
  `pay_time` datetime DEFAULT NULL COMMENT '付款时间',
  `comment` varchar(2048) DEFAULT '' COMMENT '评价',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_number` (`order_number`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_seller_id` (`seller_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ms_order
-- ----------------------------

-- ----------------------------
-- Table structure for ms_seller
-- ----------------------------
DROP TABLE IF EXISTS `ms_seller`;
CREATE TABLE `ms_seller` (
  `id` int(16) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `qr_code` varchar(255) NOT NULL DEFAULT '' COMMENT '二维码id',
  `brand` varchar(255) NOT NULL DEFAULT '' COMMENT '商家名称即品牌',
  `address` varchar(255) NOT NULL DEFAULT '' COMMENT '商家地址',
  `business` varchar(255) NOT NULL DEFAULT '' COMMENT '主营业务',
  `discount` varchar(255) NOT NULL DEFAULT '5,3,1' COMMENT '可选折扣：5,3,1',
  `popularity` int(8) DEFAULT '0' COMMENT '人气：消费次数',
  `one_star_num` int(8) DEFAULT '0' COMMENT '1星评价数',
  `two_star_num` int(8) DEFAULT '0' COMMENT '2星评价数',
  `three_star_num` int(8) DEFAULT '0' COMMENT '3星评价数',
  `four_star_num` int(8) DEFAULT '0' COMMENT '4星评价数',
  `five_star_num` int(8) DEFAULT '0' COMMENT '5星评价数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_qr_code` (`qr_code`) USING BTREE,
  KEY `index_brand` (`brand`) USING BTREE COMMENT '商家名称索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入驻商家';

-- ----------------------------
-- Records of ms_seller
-- ----------------------------

-- ----------------------------
-- Table structure for ms_service
-- ----------------------------
DROP TABLE IF EXISTS `ms_service`;
CREATE TABLE `ms_service` (
  `id` int(16) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `seller_id` int(16) NOT NULL COMMENT '商家id',
  `service` varchar(255) NOT NULL COMMENT '商家服务种类',
  `average_consumption` decimal(10,0) DEFAULT '0' COMMENT '平均消费',
  `consumption_num` int(16) NOT NULL DEFAULT '0' COMMENT '消费次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ms_service
-- ----------------------------

-- ----------------------------
-- Table structure for ms_user
-- ----------------------------
DROP TABLE IF EXISTS `ms_user`;
CREATE TABLE `ms_user` (
  `id` int(16) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `login_name` varchar(64) NOT NULL DEFAULT '' COMMENT '登录名',
  `password` varchar(45) NOT NULL DEFAULT '123456' COMMENT '密码',
  `phone_number` varchar(64) DEFAULT '' COMMENT '电话号码',
  `email` varchar(64) DEFAULT NULL,
  `gender` varchar(8) DEFAULT '' COMMENT '性别',
  `address` varchar(64) DEFAULT '' COMMENT '住址',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态：1启动; 0禁用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_login_name` (`login_name`) USING BTREE,
  UNIQUE KEY `uk_email` (`email`) USING BTREE,
  UNIQUE KEY `uk_phone_number` (`phone_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ms_user
-- ----------------------------
INSERT INTO `ms_user` VALUES ('4', '2018-06-01 16:06:33', '2018-06-01 16:06:33', 'string', 'string', 'string', 'string', 'string', 'string', '1');
