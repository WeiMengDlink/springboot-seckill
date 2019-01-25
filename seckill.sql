/*
 Navicat Premium Data Transfer

 Source Server         : aliyun-mysql
 Source Server Type    : MySQL
 Source Server Version : 80014
 Source Host           : x.x.x.x:3306
 Source Schema         : seckill

 Target Server Type    : MySQL
 Target Server Version : 80014
 File Encoding         : 65001

 Date: 25/01/2019 21:31:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '订单id，主键',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `ware_id` int(11) NOT NULL COMMENT '商品id',
  `ware_price` decimal(10, 2) NOT NULL COMMENT '商品价格',
  `amount` int(11) NOT NULL COMMENT '商品数量',
  `sum_price` decimal(10, 2) NOT NULL COMMENT '订单总金额',
  `promotion_id` int(11) NOT NULL DEFAULT 0 COMMENT '促销活动id，默认值为0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('2019011100000201', 1011, 7, 20488.92, 1, 20488.92, 0);
INSERT INTO `order_info` VALUES ('2019011100000301', 1011, 8, 6499.00, 3, 19497.00, 0);
INSERT INTO `order_info` VALUES ('2019011100000401', 1011, 8, 6499.00, 3, 19497.00, 0);
INSERT INTO `order_info` VALUES ('2019011200000501', 1011, 8, 2333.00, 1, 2333.00, 1);
INSERT INTO `order_info` VALUES ('2019011200000601', 1011, 8, 2333.00, 1, 2333.00, 1);
INSERT INTO `order_info` VALUES ('2019011200000701', 1011, 12, 6666.60, 2, 13333.20, 2);
INSERT INTO `order_info` VALUES ('2019012500001001', 1011, 8, 2333.00, 1, 2333.00, 1);

-- ----------------------------
-- Table structure for password_info
-- ----------------------------
DROP TABLE IF EXISTS `password_info`;
CREATE TABLE `password_info`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id，自增主键',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '和用户id绑定的加密后的密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of password_info
-- ----------------------------
INSERT INTO `password_info` VALUES (4, 1011, 'UZZxRPoCQN/JCOX2ViS/2A==');
INSERT INTO `password_info` VALUES (5, 1015, 'eS2gKqPgCVfEc4Jh5wYVLQ==');
INSERT INTO `password_info` VALUES (6, 1019, 'xYHIzar5UJ2MvVm+iNBe/w==');
INSERT INTO `password_info` VALUES (7, 1021, 'LcJKfmRiIjUca7/ezOqQKQ==');
INSERT INTO `password_info` VALUES (8, 1022, 'FDMKODM8huGRddxcbOGVIQ==');

-- ----------------------------
-- Table structure for promotion_info
-- ----------------------------
DROP TABLE IF EXISTS `promotion_info`;
CREATE TABLE `promotion_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '促销活动id，自增主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '促销活动标题',
  `ware_id` int(11) NOT NULL DEFAULT 0 COMMENT '促销活动的商品id',
  `ware_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '促销活动的商品价格',
  `start_date` datetime(0) NOT NULL COMMENT '促销活动的开始时间',
  `end_date` datetime(0) NOT NULL COMMENT '促销活动的结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of promotion_info
-- ----------------------------
INSERT INTO `promotion_info` VALUES (1, 'ipad Pro 疯狂秒杀', 8, 2333.00, '2019-01-25 20:00:00', '2019-07-09 23:00:00');
INSERT INTO `promotion_info` VALUES (2, 'Apple iPhone XS Max大降价', 12, 6666.60, '2019-01-25 18:52:00', '2019-07-07 20:00:00');
INSERT INTO `promotion_info` VALUES (3, '2018 新款MacBook Pro大减价', 7, 19999.99, '2019-08-08 08:08:08', '2019-12-12 12:12:12');

-- ----------------------------
-- Table structure for sales_info
-- ----------------------------
DROP TABLE IF EXISTS `sales_info`;
CREATE TABLE `sales_info`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id，自增主键',
  `ware_id` int(11) NOT NULL COMMENT '商品id',
  `sales` int(11) NOT NULL DEFAULT 0 COMMENT '商品销量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sales_info
-- ----------------------------
INSERT INTO `sales_info` VALUES (4, 7, 11);
INSERT INTO `sales_info` VALUES (5, 8, 29);
INSERT INTO `sales_info` VALUES (8, 11, 1);
INSERT INTO `sales_info` VALUES (9, 12, 10);

-- ----------------------------
-- Table structure for sequence_info
-- ----------------------------
DROP TABLE IF EXISTS `sequence_info`;
CREATE TABLE `sequence_info`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '主键，序列的名称',
  `current_value` int(11) NOT NULL DEFAULT 0 COMMENT '当前值， 默认为0',
  `step` int(11) NOT NULL COMMENT '步长值',
  `max_value` int(255) NOT NULL DEFAULT 999999 COMMENT '序列最大值，超过后设置为初始值',
  `init_value` int(255) NOT NULL DEFAULT 0 COMMENT '初始值',
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sequence_info
-- ----------------------------
INSERT INTO `sequence_info` VALUES ('order_sq', 11, 1, 999999, 0);

-- ----------------------------
-- Table structure for stock_info
-- ----------------------------
DROP TABLE IF EXISTS `stock_info`;
CREATE TABLE `stock_info`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id，自增主键',
  `ware_id` int(11) NOT NULL COMMENT '商品id',
  `stock` int(11) NOT NULL DEFAULT 0 COMMENT '商品库存',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stock_info
-- ----------------------------
INSERT INTO `stock_info` VALUES (4, 7, 98);
INSERT INTO `stock_info` VALUES (5, 8, 71);
INSERT INTO `stock_info` VALUES (8, 11, 20);
INSERT INTO `stock_info` VALUES (9, 12, 48);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id，自增主键',
  `account_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `account_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '账号id，不是用户id，如用户微信号、QQ号等',
  `age` tinyint(3) NOT NULL DEFAULT -1 COMMENT '用户年龄（-1表示未设置）',
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '用户性别，f-女，m-男',
  `phone_number` char(11) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '用户手机号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_phone_number`(`phone_number`) USING BTREE COMMENT '命名遵循阿里巴巴开发规范，用户手机号不能重复'
) ENGINE = InnoDB AUTO_INCREMENT = 1020 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1011, '诡术妖姬', '', 30, 'f', '18700000000');
INSERT INTO `user_info` VALUES (1015, '13', '', 12, 'm', '18756000000');
INSERT INTO `user_info` VALUES (1019, '123', '', 120, 'f', '18700000001');
INSERT INTO `user_info` VALUES (1021, '疾风剑豪', '', 23, 'm', '18712345678');
INSERT INTO `user_info` VALUES (1022, '放逐之刃', '', 23, 'f', '18766666666');

-- ----------------------------
-- Table structure for ware_info
-- ----------------------------
DROP TABLE IF EXISTS `ware_info`;
CREATE TABLE `ware_info`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id，自增主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '商品标题',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '商品描述',
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '商品图片url',
  `price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '商品价格',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ware_info
-- ----------------------------
INSERT INTO `ware_info` VALUES (7, 'MacBook Pro 2018', '2018年新款MacBookPro', 'https://img14.360buyimg.com/n0/jfs/t23386/9/1066712099/277967/615ccafb/5b4f0e3aN262237fc.jpg', 20488.92);
INSERT INTO `ware_info` VALUES (8, 'ipad Pro 2018', '2018年新款ipad Pro', 'https://img14.360buyimg.com/n0/jfs/t1/2060/2/13752/217276/5bd8a77dEa1d786e7/d6acfae792866f77.jpg', 6499.00);
INSERT INTO `ware_info` VALUES (11, 'Apple iMac Pro', 'Apple iMac Pro 27英寸一体机（八核Xeon W处理器/32GB内存/1TB 固态硬盘/Vega 56显卡/5K显示屏 MQ2Y2CH/A）', 'https://img14.360buyimg.com/n0/jfs/t1/25849/21/3098/170913/5c232cd1E3113e0e6/94b112c30fd3e116.jpg', 39150.00);
INSERT INTO `ware_info` VALUES (12, 'Apple iPhone XS Max', 'Apple iPhone XS Max (A2103) 256GB 深空灰色 全网通（移动4G优先版） 双卡双待', 'https://img14.360buyimg.com/n0/jfs/t1/3/15/4536/138660/5b997bf8Ed72ebce7/819dcf182d743897.jpg', 9599.00);

SET FOREIGN_KEY_CHECKS = 1;
