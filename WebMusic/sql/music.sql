/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.1.62-community : Database - music
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`music` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `music`;

/*Table structure for table `musics` */

DROP TABLE IF EXISTS `musics`;

CREATE TABLE `musics` (
  `musicID` varchar(32) NOT NULL,
  `musicName` varchar(256) NOT NULL,
  `musicUrl` varchar(256) NOT NULL,
  `musicPicName` varchar(256) DEFAULT NULL,
  `musicPicUr` varchar(256) DEFAULT NULL,
  `musicPublishDate` date NOT NULL,
  `musicPublishCompany` varchar(256) DEFAULT NULL,
  `musicLanguageType` varchar(2) NOT NULL,
  `musicLyricUrl` varchar(256) DEFAULT NULL,
  `musicAuthor` varchar(32) NOT NULL,
  PRIMARY KEY (`musicID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `musics` */

/*Table structure for table `singer` */

DROP TABLE IF EXISTS `singer`;

CREATE TABLE `singer` (
  `singerID` varchar(32) NOT NULL,
  `singerName` varchar(256) NOT NULL COMMENT '名字',
  `singerNational` varchar(256) NOT NULL COMMENT '国籍',
  `singerHomePlace` varchar(256) NOT NULL COMMENT '出生地',
  `singerJob` varchar(256) NOT NULL COMMENT '职业',
  `singerSummary` text NOT NULL COMMENT '简介',
  PRIMARY KEY (`singerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `singer` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userID` varchar(32) NOT NULL,
  `userName` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nickName` varchar(50) NOT NULL,
  `loginTime` varchar(50) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`userID`,`userName`,`password`,`nickName`,`loginTime`) values ('1','dongjunrong','djr1210843430','djr','2018-07-02'),('70549d7c7dce11e8b23bc5b95167142c','root','root','wolf','2018-07-02'),('93aa24907dce11e8b23bc5b95167142c','root','root','wolf','2018-07-02');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
