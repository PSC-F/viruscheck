/*



Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-02-18 14:10:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student` (
  `id` varchar(30) NOT NULL COMMENT '学号',
  `name` varchar(30) DEFAULT NULL COMMENT '学生姓名',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `phone` varchar(30) DEFAULT NULL COMMENT '手机号',
  `address` varchar(100) DEFAULT NULL COMMENT '测量地址',
  `className` varchar(40) DEFAULT NULL COMMENT '专业班级',
  `isDanger` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生基本信息表\r\n';

-- ----------------------------
-- Table structure for tb_temp
-- ----------------------------
DROP TABLE IF EXISTS `tb_temp`;
CREATE TABLE `tb_temp` (
  `ID` int(255) NOT NULL AUTO_INCREMENT,
  `studentId` varchar(30) NOT NULL,
  `tempAM` varchar(30) NOT NULL COMMENT '上午体温',
  `tempPM` varchar(30) NOT NULL COMMENT '下午体温',
  `des` varchar(100) DEFAULT NULL COMMENT '异常信息',
  `sysDate` date NOT NULL COMMENT '日期',
  PRIMARY KEY (`ID`),
  KEY `fk_temp_student` (`studentId`),
  CONSTRAINT `fk_temp_student` FOREIGN KEY (`studentId`) REFERENCES `tb_student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=194 DEFAULT CHARSET=utf8;
