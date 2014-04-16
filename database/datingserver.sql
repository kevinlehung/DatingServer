/*
SQLyog Ultimate v9.20 
MySQL - 5.1.68-community-log : Database - datingserver
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`datingserver` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `datingserver`;

/*Table structure for table `file` */

DROP TABLE IF EXISTS `file`;

CREATE TABLE `file` (
  `FILE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FILE_NAME` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PATH` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `CREATED_DATE` datetime NOT NULL,
  PRIMARY KEY (`FILE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `file` */

/*Table structure for table `profile` */

DROP TABLE IF EXISTS `profile`;

CREATE TABLE `profile` (
  `PROFILE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TAGLINE` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `OVERVIEW` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `HOURLY_RATE` int(11) DEFAULT NULL,
  `EXPERIENCE` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `PHOTO_ATTACH_FILE_ID` int(11) DEFAULT NULL,
  `USER_ID` int(11) NOT NULL,
  PRIMARY KEY (`PROFILE_ID`),
  KEY `FK_profile_file_file_id` (`PHOTO_ATTACH_FILE_ID`),
  KEY `FK_profile_user_user_id` (`USER_ID`),
  CONSTRAINT `FK_profile_file_file_id` FOREIGN KEY (`PHOTO_ATTACH_FILE_ID`) REFERENCES `file` (`FILE_ID`),
  CONSTRAINT `FK_profile_user_user_id` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `profile` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(50) DEFAULT NULL,
  `LAST_NAME` varchar(50) DEFAULT NULL,
  `USER_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `USER_EMAIL` varchar(50) NOT NULL,
  `USER_PASSWORD` varchar(200) NOT NULL,
  `DATE_CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `DATE_UPDATED` timestamp NULL DEFAULT NULL,
  `LAST_LOGIN_DATE` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `PASSWORD_LAST_CHANGED_DATE` datetime DEFAULT NULL,
  `LAST_FAILED_LOGIN_DATE` timestamp NULL DEFAULT NULL,
  `FAILED_LOGIN_ATTEMPTS` int(2) DEFAULT '0',
  `PASSWORD_EXPIRED` tinyint(1) DEFAULT '0',
  `ACCOUNT_LOCKED` tinyint(1) DEFAULT '0',
  `API_LOGIN` tinyint(1) NOT NULL DEFAULT '0',
  `ACTIVE` tinyint(1) DEFAULT '1',
  `PASSWORD_HASH` varchar(32) DEFAULT NULL,
  `PASSWORD_HASH_DATE` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `UNIQ_user_email` (`USER_EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
