/*
SQLyog Job Agent Version 10.0 Beta1 Copyright(c) Webyog Inc. All Rights Reserved.


MySQL - 5.5.5-10.1.9-MariaDB : Database - amc
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Database structure for database `amc` */

CREATE DATABASE /*!32312 IF NOT EXISTS*/`amc` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `amc`;

/*Table structure for table `customer_tbl` */

DROP TABLE IF EXISTS `customer_tbl`;

CREATE TABLE `customer_tbl` (
  `c_code` int(11) NOT NULL,
  `c_name` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `c_address` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `c_area` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `c_contact_no` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`c_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `customer_tbl` */

insert  into `customer_tbl` values (1,'ALi','Max Tech Computer Khan Plaza Nishat Chowk Mingor, Swat, Kpk.','Kabal','111'),(2,'Bilal','Kanju','Mingora','2233');

/*Table structure for table `login_table` */

DROP TABLE IF EXISTS `login_table`;

CREATE TABLE `login_table` (
  `login_id` int(5) NOT NULL AUTO_INCREMENT,
  `login_email` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `login_password` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `login_table` */

insert  into `login_table` values (1,'admin@gmail.com','pass');

/*Table structure for table `order_main_tbl` */

DROP TABLE IF EXISTS `order_main_tbl`;

CREATE TABLE `order_main_tbl` (
  `order_no` int(11) NOT NULL AUTO_INCREMENT,
  `order_date` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `c_code` int(11) DEFAULT NULL,
  `order_status` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`order_no`),
  KEY `c_code` (`c_code`),
  CONSTRAINT `order_main_tbl_ibfk_1` FOREIGN KEY (`c_code`) REFERENCES `customer_tbl` (`c_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `order_main_tbl` */

insert  into `order_main_tbl` values (1,'11/22/2018',1,1),(2,'11/23/2018',2,0),(3,'11/23/2018',2,1),(5,'11/23/2020',2,0),(6,'11/23/2020',1,0),(7,'11/23/2020',1,0),(8,'11/23/2020',1,0);

/*Table structure for table `product_tbl` */

DROP TABLE IF EXISTS `product_tbl`;

CREATE TABLE `product_tbl` (
  `p_code` int(11) NOT NULL,
  `p_description` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `p_tp` double DEFAULT NULL,
  PRIMARY KEY (`p_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `product_tbl` */

insert  into `product_tbl` values (1,'Panadol',200),(2,'Paramin',300),(3,'Calpol',400),(501,'CAMOPLEX SUPER INJ 2ML     ',39.19),(502,'CAMOZYME SYRUP 120ML       ',55.25),(503,'CIPROCAM 10S 250MG        ',85),(504,'CIPROCAM 10S 500MG        ',170),(505,'FOLIC ACID ABS 100S       ',25.5),(506,'LORIVAL 3MG 30S           ',65.13),(507,'MECOCAM 500MCG TABS        ',467.5),(508,'MECOCAM INJ 1MLX10         ',212.5),(509,'MECOCAM TAB 2*10           ',93.5),(510,'MENDOZAPLEX INJ. 25ML      ',106.25),(511,'OPENAIR 10MG TAB           ',178.5),(512,'POLORAN TAB                ',74.38),(513,'POLORON 60ML SYP           ',74.38),(514,'SINDOSE FORTE              ',42.5),(515,'SINDOSE SYP                ',19.55),(516,'SINDOSE TABS               ',44.2),(517,'SIROLINE SYRUP 120ML       ',55.25),(518,'THIAVIT CAP 20S           ',46.75),(519,'THIAVIT INJ 25X3ML         ',190),(520,'TRANSCAM 500MG CAPS        ',212.5),(521,'TRANSCAM 500MG INJ         ',425),(522,'TRANSCAM CAP 250MG         ',55.25),(523,'TRANSCAM INJ 250MG         ',255),(524,'QfIN 250MG TAB             ',85),(525,'QfIN 500MG TAB             ',148.75),(526,'CAMOPLEX LYSINE 120ML      ',55.25),(527,'CAMOVIT-M CAP. 20          ',51.89),(528,'AZOCAM 250MG CAPS          ',187),(529,'AZOCAM SYP                 ',127.5),(530,'CAMOVIT-L DROP 20ML        ',37.19),(531,'HEPTOCAM 120ML             ',107.53),(532,'HEPTOCAM CAP               ',245.44),(533,'HEPTOCAM SUSP. 60ML        ',60.78),(534,'HYDROLYTE ORAL             ',68),(535,'LAXACAM DROPS              ',24.31),(536,'MALAGON AL DRY SYP 60ML    ',153),(537,'MALAGON SYP                ',34),(538,'MALAGON TABS               ',51),(539,'MALAGON-AL FORT TAB        ',224.4),(540,'MOLTON-S 120ML SYP         ',110.5),(541,'MOLTON-S 250ML             ',205.11),(542,'NOMIT ORAL 60MLSYP         ',35.7),(543,'NOMIT TAB                  ',182.75),(544,'RONOVIT CAP  30          ',56.78),(545,'VITA 6 TAB                 ',38.25),(546,'ESSOCAM 20MG CAP           ',101.15),(547,'ESSOCAM 40MG CAP           ',168.3),(548,'AZOCAM DS 500MG CAP        ',106.25);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
