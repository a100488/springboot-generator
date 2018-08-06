/*
Navicat MySQL Data Transfer

Source Server         : 本地mysql
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : fss

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2018-08-06 16:19:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_backend_api
-- ----------------------------
DROP TABLE IF EXISTS `t_backend_api`;
CREATE TABLE `t_backend_api` (
  `id` bigint(20) NOT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `path` varchar(500) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL COMMENT 'HttpMethod',
  `summary` varchar(500) DEFAULT NULL,
  `operation_id` varchar(255) DEFAULT NULL,
  `UPDATE_USER` bigint(20) DEFAULT NULL,
  `UPDATE_TIME` bigint(20) DEFAULT NULL,
  `INSERT_USER` bigint(20) DEFAULT NULL,
  `INSERT_TIME` bigint(20) DEFAULT NULL,
  `DELETE_FLAG` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_employee
-- ----------------------------
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE `t_employee` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `parent_id` bigint(20) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `sort` int(10) DEFAULT '0' COMMENT '排序',
  `link` varchar(200) DEFAULT NULL COMMENT '路由',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `menu_type` char(1) NOT NULL COMMENT '菜单类型(0普通菜单 1 日期框 2 组合下拉框)',
  `left_show` tinyint(1) NOT NULL COMMENT '是否在左侧菜单中显示',
  `is_show` tinyint(1) DEFAULT NULL,
  `UPDATE_USER` bigint(20) DEFAULT NULL,
  `UPDATE_TIME` bigint(20) DEFAULT NULL,
  `INSERT_USER` bigint(20) DEFAULT NULL,
  `INSERT_TIME` bigint(20) DEFAULT NULL,
  `DELETE_FLAG` char(1) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sys_menu_parent_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Table structure for t_menu_backend_api
-- ----------------------------
DROP TABLE IF EXISTS `t_menu_backend_api`;
CREATE TABLE `t_menu_backend_api` (
  `menu_id` bigint(20) NOT NULL,
  `backend_api_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `code` varchar(255) DEFAULT NULL COMMENT '角色权限标识',
  `UPDATE_USER` bigint(20) DEFAULT NULL,
  `UPDATE_TIME` bigint(20) DEFAULT NULL,
  `INSERT_USER` bigint(20) DEFAULT NULL,
  `INSERT_TIME` bigint(20) DEFAULT NULL,
  `DELETE_FLAG` char(1) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sys_role_enname` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色编号',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-菜单';

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL,
  `USER_NAME` varchar(30) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `REAL_NAME` varchar(30) DEFAULT NULL,
  `UPDATE_USER` bigint(20) DEFAULT NULL,
  `UPDATE_TIME` bigint(20) DEFAULT NULL,
  `INSERT_USER` bigint(20) DEFAULT NULL,
  `INSERT_TIME` bigint(20) DEFAULT NULL,
  `DELETE_FLAG` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户编号',
  `role_id` bigint(20) NOT NULL COMMENT '角色编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色';
