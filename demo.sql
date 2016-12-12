/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50634
Source Host           : 127.0.0.1:3306
Source Database       : poi_system

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2016-12-12 11:06:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for poi_admin
-- ----------------------------
DROP TABLE IF EXISTS `poi_admin`;
CREATE TABLE `poi_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户自增ID',
  `name` varchar(32) NOT NULL COMMENT '用户名',
  `email` varchar(64) NOT NULL COMMENT '邮箱地址',
  `pwd` varchar(32) NOT NULL COMMENT '用户密码',
  `status` enum('0','1') DEFAULT '0' COMMENT '是否可用,1为禁用，0为正常',
  `last_login_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '上次登录时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for poi_customer
-- ----------------------------
DROP TABLE IF EXISTS `poi_customer`;
CREATE TABLE `poi_customer` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `customer_name` varchar(255) DEFAULT NULL COMMENT '客户名称',
  `customer_code` varchar(32) DEFAULT NULL COMMENT '客户代码',
  `customer_key` varchar(32) DEFAULT NULL COMMENT '客户key',
  `customer_last_check_index` int(32) DEFAULT NULL COMMENT '上次检查index位置，index表示客户表中的id',
  `customer_mac` varchar(32) DEFAULT NULL COMMENT '绑定企业mac地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for poi_dca
-- ----------------------------
DROP TABLE IF EXISTS `poi_dca`;
CREATE TABLE `poi_dca` (
  `dca_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'DCA自增ID',
  `dca_name` varchar(64) DEFAULT NULL COMMENT 'DCA名称',
  `healthy_check_interval` int(8) DEFAULT NULL COMMENT '健康检查间隙',
  `sync_data_interval` int(8) DEFAULT NULL COMMENT '同步数据时间间隔',
  `is_active` enum('0','1') DEFAULT '0' COMMENT 'DCA是否禁用，0表示正常，1表示禁用',
  `get_task_interval` int(8) DEFAULT NULL COMMENT '获取任务的时间间隔',
  `log_path` varchar(255) DEFAULT NULL COMMENT 'log存储路径',
  `ip` varchar(16) DEFAULT NULL COMMENT 'IP地址',
  `mac` varchar(32) DEFAULT NULL COMMENT 'MAC地址',
  `status` enum('0','1') DEFAULT '0' COMMENT '状态，0表示离线，1表示在线',
  `customer_id` int(8) DEFAULT NULL COMMENT '所属企业客户ID',
  `register_time` datetime DEFAULT NULL COMMENT '启动时间',
  `last_request_time` datetime DEFAULT NULL COMMENT '上次DCA请求时间',
  PRIMARY KEY (`dca_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_database_dn
-- ----------------------------
DROP TABLE IF EXISTS `t_database_dn`;
CREATE TABLE `t_database_dn` (
  `TAG_NAME` varchar(255) DEFAULT NULL COMMENT '电表标识',
  `DT` datetime DEFAULT NULL COMMENT '插入时间',
  `E` float(50,2) DEFAULT NULL COMMENT '电度',
  `P` float(50,2) DEFAULT NULL COMMENT '有功功率',
  `PF` float(50,2) DEFAULT NULL,
  `IA` float(50,2) DEFAULT NULL,
  `IB` float(50,2) DEFAULT NULL,
  `IC` float(50,2) DEFAULT NULL,
  `THD_A_5` float(50,2) DEFAULT NULL,
  `THD_A_7` float(50,2) DEFAULT NULL,
  `THD_B_5` float(50,2) DEFAULT NULL,
  `THD_B_7` float(50,2) DEFAULT NULL,
  `THD_C_5` float(50,2) DEFAULT NULL,
  `THD_C_7` float(50,2) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `customer_id` int(8) DEFAULT NULL COMMENT '客户ID',
  `source_id` int(16) DEFAULT NULL COMMENT '源库数据ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `source_id` (`source_id`,`customer_id`) USING BTREE,
  UNIQUE KEY `CSTD` (`TAG_NAME`,`DT`,`customer_id`,`source_id`) COMMENT '对数据进行去重的唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=237474 DEFAULT CHARSET=utf8;
