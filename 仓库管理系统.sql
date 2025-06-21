/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : 仓库管理系统

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 12/05/2024 14:21:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `菜单编码` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单编码',
  `菜单名称` varchar(16) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单名字',
  `菜单级别` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单级别',
  `menuParentCode` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单的父code',
  `菜单函数` varchar(16) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '点击触发的函数',
  `所需权限` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '权限 0超级管理员，1表示管理员，2表示普通用户，可以用逗号组合使用',
  `菜单路径` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `菜单图标` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '001', '管理员管理', '1', NULL, 'Admin', 'Root', 'admin/AdminManage.vue', 'el-icon-s-custom');
INSERT INTO `menu` VALUES (2, '002', '用户管理', '1', NULL, 'User', 'Root,管理员', 'user/UserManage.vue', 'el-icon-user-solid');
INSERT INTO `menu` VALUES (3, '003', '仓库管理', '1', NULL, 'Storage', 'Root,管理员', 'storage/StorageManage', 'el-icon-office-building');
INSERT INTO `menu` VALUES (5, '005', '合作伙伴管理', '1', NULL, 'Partner', 'Root,管理员', '合作伙伴后台.vue', 'el-icon-s-custom');
INSERT INTO `menu` VALUES (6, '006', '潜在用户信息', '1', NULL, 'Potential_User', 'Root,管理员', '潜在用户信息.vue', 'el-icon-user');

-- ----------------------------
-- Table structure for storage
-- ----------------------------
DROP TABLE IF EXISTS `storage`;
CREATE TABLE `storage`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `仓库编号` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `仓库名称` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '仓库名',
  `仓库地区` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `仓库管理者uid` int NOT NULL DEFAULT 1,
  `仓库类别` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `仓库状态` enum('已租赁','已过期','未租赁') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `备注` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  `仓库公告` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `仓库优点` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `仓库缺点` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `租赁开始时间` date NULL DEFAULT NULL,
  `租赁结束时间` date NULL DEFAULT NULL,
  `img0` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `img1` varchar(2555) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `仓库管理者uid`) USING BTREE,
  INDEX `仓库管理者uid`(`仓库管理者uid` ASC) USING BTREE,
  CONSTRAINT `storage_ibfk_1` FOREIGN KEY (`仓库管理者uid`) REFERENCES `用户数据` (`UID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of storage
-- ----------------------------
INSERT INTO `storage` VALUES (1, 'GC001', '藁城1号仓', '藁城区', 2, '快递服务', '未租赁', NULL, NULL, '空间大', '位置偏僻', '2024-01-28', '2024-01-31', '藁城区/1号仓库/3D数字孪生静态图.jpg', '藁城区/1号仓库/solutionAIOTBanner.jpg');
INSERT INTO `storage` VALUES (3, 'YH001', '裕华1号仓', '裕华区', 3, '仓储1', '已租赁', '', '', '毗邻市区', '价格较高', '2024-01-31', '2024-02-23', '裕华区/1号仓库/solutionBanner (1).jpg', '裕华区/1号仓库/solutionBanner (2).jpg');
INSERT INTO `storage` VALUES (9, 'ZD001', '正定1号仓', '正定县', 1, '快递', '已租赁', '', '', '', '', '2021-01-03', '2024-05-03', '正定县/1号仓库/1.jpg', '正定县/1号仓库/2.jpg');
INSERT INTO `storage` VALUES (10, 'ZD002', '正定2号仓', '正定县', 1, '', '已过期', '', '', '', '', '2020-01-03', '2021-03-02', '正定县/1号仓库/2.jpg', '正定县/1号仓库/1.jpg');

-- ----------------------------
-- Table structure for 合作伙伴
-- ----------------------------
DROP TABLE IF EXISTS `合作伙伴`;
CREATE TABLE `合作伙伴`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `logosrc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `httpsrc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of 合作伙伴
-- ----------------------------
INSERT INTO `合作伙伴` VALUES (1, '京东快递', '京东快递.jpg', 'https://www.jdl.com/');
INSERT INTO `合作伙伴` VALUES (2, '京东', '京东.png', 'https://www.jd.com/');
INSERT INTO `合作伙伴` VALUES (3, '中通快递', '中通快递.jpg', 'https://www.zto.com/');
INSERT INTO `合作伙伴` VALUES (4, '唯品会', '唯品会.webp', 'https://www.vip.com/nav/53174452.html');
INSERT INTO `合作伙伴` VALUES (5, '苏宁易购', '苏宁易购.jpg', 'https://www.suning.com/');
INSERT INTO `合作伙伴` VALUES (6, 'ElementUI', 'ElementUI.svg', 'https://element.eleme.cn/#/zh-CN');
INSERT INTO `合作伙伴` VALUES (7, '亚马逊', '亚马逊.png', 'https://www.amazon.cn/');
INSERT INTO `合作伙伴` VALUES (8, '顺丰速递', '顺丰.png', 'https://www.sf-express.com/');

-- ----------------------------
-- Table structure for 服务展示数据
-- ----------------------------
DROP TABLE IF EXISTS `服务展示数据`;
CREATE TABLE `服务展示数据`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `dis` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of 服务展示数据
-- ----------------------------
INSERT INTO `服务展示数据` VALUES (1, '云RCS', '地图编辑、智能调度、机器人控制、生产监控');
INSERT INTO `服务展示数据` VALUES (2, 'IoT Core', '设备管理、数据采集、双向控制、远程升级');
INSERT INTO `服务展示数据` VALUES (3, '规则引擎', '数据转发、数据分析、实时告警、设备联动');
INSERT INTO `服务展示数据` VALUES (4, 'AI分析服务', 'AI盯货、AI盘点、AI人员定位、AI设备运维');

-- ----------------------------
-- Table structure for 潜在用户细腻
-- ----------------------------
DROP TABLE IF EXISTS `潜在用户细腻`;
CREATE TABLE `潜在用户细腻`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `姓名` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `电话` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `租赁开始时间` date NULL DEFAULT NULL,
  `租赁结束时间` date NULL DEFAULT NULL,
  `问题` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of 潜在用户细腻
-- ----------------------------
INSERT INTO `潜在用户细腻` VALUES (1, '刘', '176', NULL, NULL, '');
INSERT INTO `潜在用户细腻` VALUES (16, '罚恶色', '2524', '2023-12-14', '2024-01-26', '打法');
INSERT INTO `潜在用户细腻` VALUES (17, '刘赫祥', '176222222', '2023-12-01', '2024-01-31', '放大');
INSERT INTO `潜在用户细腻` VALUES (22, 'fs', NULL, '2024-01-02', '2024-02-06', 'dfsa');
INSERT INTO `潜在用户细腻` VALUES (25, '溜溜溜', NULL, '2024-05-08', '2024-06-21', '发test');
INSERT INTO `潜在用户细腻` VALUES (26, 'rrrrr', NULL, '2024-05-08', '2024-06-21', '发test');
INSERT INTO `潜在用户细腻` VALUES (27, '六六', NULL, '2024-05-01', '2024-06-27', '营业外往往是血案是否');

-- ----------------------------
-- Table structure for 用户数据
-- ----------------------------
DROP TABLE IF EXISTS `用户数据`;
CREATE TABLE `用户数据`  (
  `UID` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `用户名称` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '名字',
  `密码` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '密码',
  `年龄` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '年龄',
  `性别` enum('男','女','其他') CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '男' COMMENT '性别',
  `电话` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '电话',
  `级别` enum('Root','管理员','普通账户') CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '普通账户' COMMENT 'Root，管理员，普通账户',
  `账户是否有效` enum('是','否') CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '是' COMMENT '账户是否有效',
  PRIMARY KEY (`UID`) USING BTREE,
  INDEX `UID`(`UID` ASC, `用户名称` ASC) USING BTREE,
  INDEX `用户名称`(`用户名称` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8549 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of 用户数据
-- ----------------------------
INSERT INTO `用户数据` VALUES (1, '刘赫祥', '123', '21', '男', '176', 'Root', '是');
INSERT INTO `用户数据` VALUES (2, '王娟娟', '123', '23', '女', '185', '管理员', '是');
INSERT INTO `用户数据` VALUES (3, '邢煜秉', '123', '22', '男', '188', '管理员', '否');
INSERT INTO `用户数据` VALUES (8518, '张慨然', '123', '21', '男', '198', '管理员', '是');
INSERT INTO `用户数据` VALUES (8519, '王延涛', '123', '21', '男', '1559', '管理员', '否');
INSERT INTO `用户数据` VALUES (8529, 'test', '132', '323', '男', '000', '普通账户', '否');
INSERT INTO `用户数据` VALUES (8533, 'ttt', '321', '21', '男', '11111111111', '普通账户', '否');
INSERT INTO `用户数据` VALUES (8538, '啦啦啦', '123', '22', '男', '176', '普通账户', '是');
INSERT INTO `用户数据` VALUES (8540, '小小', '123', '22', '男', '176', '普通账户', '是');
INSERT INTO `用户数据` VALUES (8541, '测试', '123', '22', '男', '176', 'Root', '否');
INSERT INTO `用户数据` VALUES (8542, '三幺四', '314', '21', '男', '17628655265', '普通账户', '是');
INSERT INTO `用户数据` VALUES (8544, '', '', NULL, '男', '', '普通账户', '是');
INSERT INTO `用户数据` VALUES (8545, '', '', NULL, '男', '', '普通账户', '是');
INSERT INTO `用户数据` VALUES (8546, '朱天宇', '123', '21', '女', '13343020755', '管理员', '是');
INSERT INTO `用户数据` VALUES (8547, '杀杀杀', '123', '21', '男', '13343020756', '普通账户', '否');
INSERT INTO `用户数据` VALUES (8548, '哈哈哈', '123', '21', '男', '13343020759', '普通账户', '是');

-- ----------------------------
-- Table structure for 解决方案
-- ----------------------------
DROP TABLE IF EXISTS `解决方案`;
CREATE TABLE `解决方案`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标题',
  `dis` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '内容',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of 解决方案
-- ----------------------------
INSERT INTO `解决方案` VALUES (1, '智能仓数字孪生方案', '通过3D建模技术还原真实智能仓场景，实时采集设备和业务数据，将作业过程、设备运行情况进行多维可视化展示，实现仓库管理的快速决策。', 's_card1.png');
INSERT INTO `解决方案` VALUES (2, 'AIOT监管仓方案', '利用物联网、AI等技术对仓库进行改造，实现货物及其存储环境的智能管理，以保证货物在仓的“防换”、“防丢”、“防损”。', 's_card2.png');
INSERT INTO `解决方案` VALUES (3, '自动分播机器人方案', '针对B2B与B2C场景中小件商品多流向分播难的问题，引入自动分播墙替代人工作业，提高分播作业效率与正确率，降低人工成本。', 's_card3.png');
INSERT INTO `解决方案` VALUES (4, '仓储碳计算及环境\r\n仓储碳计算及环境<br>安全监控方案', '集成仓储场景能耗管理、碳管理、环境监测等功能，提升能源利用效率，建立可持续发展治理体系，落地仓储物流行业绿色低碳实践。', 's_card4.png');

SET FOREIGN_KEY_CHECKS = 1;
