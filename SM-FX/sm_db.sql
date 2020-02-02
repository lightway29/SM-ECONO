CREATE DATABASE  IF NOT EXISTS `sm_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `sm_db`;
-- MySQL dump 10.13  Distrib 8.0.19, for macos10.15 (x86_64)
--
-- Host: localhost    Database: sm_db
-- ------------------------------------------------------
-- Server version	5.6.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_code` varchar(45) NOT NULL,
  `customer_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`customer_code`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'ABL','AKBAR BROTHERS (PVT) LTD'),(2,'ABX','AKBAR BROTHERS EXPORTS'),(3,'AHEH','ALI HAJ ESMAEILI HOSSEINABADI'),(4,'ALMAR','ALMAR TEAS (PVT) LTD'),(63,'ALOY','ALOY EXPO (PVT) LTD.'),(5,'ANS','ANSELL LANKA (PVT) LTD'),(6,'AS','ANVERALLY & SONS (PVT) LTD'),(7,'ASL','ABIDALLY SONS (PVT) LTD'),(8,'BGV','BEHESHT GHANDIL VO '),(64,'BTEX','BLACK TEA EXPORTS (PVT) LTD'),(92,'CAI','CEYLON AGRO INDUSTRIES LTD'),(93,'CBL','CEYLON BISCUITS LTD'),(94,'CBLF','C B L FOODS INTERNATIONAL (PVT) LTD '),(65,'CBT','CEYLON BLACK TEA CO. (PVT) LTD'),(95,'CCS','CEYLON COLD STORES PLC'),(9,'CEYTRA','CEYTRA LIMITED'),(10,'CFT','CEYLON FRESH TEAS (PVT) LTD'),(11,'CGT','CEYON GREENFIELD TEAS PVT LTD'),(12,'CHAS','CHAS PHAYLEY & CO LTD'),(96,'CHERISH','CHERISH BISCUITS (PVT) LTD'),(66,'COS','COSMOVEDA CEYLON (PVT) LTD'),(67,'CRL','CENTRAL RUBBER (PVT) LTD'),(68,'CTB','CEYLON TEA BAGS (PVT) LTD'),(13,'CTM','CEYLON TEA MARKETING LTD'),(14,'CTP','CTP PRE-PACKED EXPORTS (PTE) LTD'),(15,'CTPL','CRYSTAL TEA  (INDIA) PRIVATE LIMITED'),(69,'CTS','CEYLON TEA SERVICES PLC'),(70,'DASATHA','DASATHA EXPORT & IMPORT INTERNATIONAL (PVT) LTD'),(97,'DEL','DELMAGE DISTRIDUTORS (PVT) LTD'),(16,'DIP','DIPPED PRODUCTS PLC'),(71,'DPLPG','D P L PREMIER GLOVES LTD'),(17,'DPLUG','D P L UNIVERSAL GLOVES LTD '),(18,'EMPIRE','EMPIRE TEAS (PVT) LTD'),(19,'FTL','FALCON TRADING (PVT) LTD'),(20,'GDE','GEE DEE ENTERPRISES LTD'),(21,'GHE','GREEN HORIZON ENTERPRISES (PVT) LTD'),(22,'GROS','GROSSART (PVT) LTD'),(23,'GS','GEORGE STEURAT TEAS  (PRIVATE) LIMITE'),(24,'HARI','HARRISONS (COLOMBO) LTD'),(88,'HET','HERITAGE TEAS (PVT) LTD'),(25,'HRP','HANWELLA RUBBER PRODUCTS LIMITED'),(72,'HTC','HIGHLAND TEA & COMMODITIES (PVT) LTD'),(73,'HTEX','HEVANLY TEA EXPORTS (PVT) LTD'),(26,'HTPS','HARITAGE TEAS PREMIUM SERVICES (PVT)LTD'),(27,'HVA','HVA LANKA EXPORTS (PVT) LTD '),(28,'HVAF','HVA FOOD PLC '),(89,'HVAFL','HVA FOODS PLC '),(29,'HYSON','HYSON TEAS (PVT) LTD'),(30,'ICL','INDUSTRIAL CLOTHINGS LTD (PP-DIV)'),(31,'IHPL','INDUSTRIAL HAND PROTECTION LIMITED '),(32,'IMPRA','IMPERIAL TEA EXPORTS (PVT) LTD'),(98,'IPL','ICARUS (PVT) LTD'),(33,'ITPL','IMPERIAL TEAS (PVT) LTD'),(74,'JBCP','J.B. COIR PADS (PVT) LTD.'),(34,'JBT','JAFFERJEE BROTHERS'),(35,'JBTB','JAFFERJEE BROTHERS TEA BAGS DEPARTMENT'),(36,'JBXB','JAFFERJEE BROTHERS (EXPORTS) LTD - BANBURY DEPARTM'),(37,'JBXR','JAFFERJEE BROTHERS (EXPORTS) LTD - RUBBER BANDS'),(75,'JCE','J C ENTERPRISES (PVT) LTD '),(76,'JVG','J.V.GOKAL CEYLON (PVT) LTD'),(38,'KAU','KAUSHALYA TEA EXPORT COMPANY'),(99,'LAH','LAHIRU ENTERPRISES'),(77,'LAL','LALAN RUBBERS (PVT) LTD'),(78,'LALA','LALAN RUBBERS (PVT) LTD - AVISAWELLA'),(39,'LALW','LALAN RUBBERS (PVT) LTD - WARAKAPOLA'),(79,'LANB','LALAN RUBBERS (PVT) LTD - BIYAGAMA'),(80,'LG','LALAN GLOVES (PRIVATE) LIMITED'),(81,'LHI','LANKA HEADWEAR INTERNATIONAL (PVT) LTD'),(100,'LL','LITTLE LION ASSOCIATES (PVT) LTD'),(101,'LVF','LANKA VALUE FOODS (PVT) LTD '),(40,'MAB','MABROC TEAS (PVT) LTD'),(82,'MAH','MAHMOOD TEA INTERNATIONAL (PVT) LTD'),(102,'MAL','MALIBAN BISCUIT MANUFACTORIES LTD'),(83,'MAPA','MAPA LALAN (PVT) LTD'),(41,'MAX -','MAXIMUS (PVT) LTD'),(42,'MEH','M.E.H INDUSTRIES (PVT) LTD'),(43,'MIPL','MALTRAS INTERNATIONAL (PVT) LTD'),(103,'MOTHA','MOTHA CONFECTIONERY WORKS (PVT) LTD'),(84,'MVP','MALWATTE VALLEY PLANTATIONS PLC '),(44,'NEO','NEOPREX (PVT) LTD'),(45,'NP','NATURES SPRIDE (PVT) LTD'),(46,'PIV','PRAVENTURE INTERNATIONAL (PVT) LTD'),(47,'PNO','POLINOR'),(48,'PSL','PRIME SAFETY LIMITED'),(104,'PURI','PURITAS LTD'),(49,'QT','QUICK TEA (PVT) LTD'),(85,'RAN','RANFER TEAS (PVT) LTD'),(86,'RANEX','RANFER EXPORTS (PVT) LTD'),(50,'RANFER','RANFER (PVT) LTD'),(105,'RC','RANCRISP MARKETING (PVT) LTD '),(106,'RCC','RANCRISP CASHEW'),(51,'REZA','REZA SEYLANI TEA (PVT) LTD'),(52,'RPE','RICHARD PIERIS EXPORTS LTD'),(53,'SAFE','M.F.SAFEWAY IMPEX (PVT) LTD '),(54,'SAM','SAMSON INTERNATIONAL LTD'),(107,'SEL','STASSEN EXPORTS LIMITED'),(90,'SIL','STASSEN INTERNATIONAL LIMITED'),(91,'SNF','STASSEN NATURAL FOODS (PVT) LTD'),(108,'SSOE','SWISS SINGAPORE OVERSEAS ENTP PTE LTD'),(55,'STC','SENOK TEA CEYLON PVT LTD'),(56,'STS','SHORE TO SHORE (PVT) LTD'),(57,'SUN','SUNSHINE TEA PVT LTD'),(58,'UC','UNION COMMODITIES TEAS (PVT) LTD'),(59,'UCEX','UNION COMMODITIES EXPORTS (PVT) LTD'),(60,'UCT','UNION COMMODITIES (PVT) LTD.'),(61,'UNIW','UNIWORLD TEAS (PVT) LTD'),(87,'WAT','WATAWALA PLANTATIONS PLC '),(109,'WIJEYA','WIJAYA PRODUCTS (PVT) LTD'),(62,'WW','WORKWEAR LANKA (PVT) LTD');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `general_log`
--

DROP TABLE IF EXISTS `general_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `general_log` (
  `event_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_host` mediumtext NOT NULL,
  `thread_id` bigint(21) unsigned NOT NULL,
  `server_id` int(10) unsigned NOT NULL,
  `command_type` varchar(64) NOT NULL,
  `argument` mediumtext NOT NULL
) ENGINE=CSV DEFAULT CHARSET=utf8 COMMENT='General log';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `general_log`
--

LOCK TABLES `general_log` WRITE;
/*!40000 ALTER TABLE `general_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `general_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `machine`
--

DROP TABLE IF EXISTS `machine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `machine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `machine_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `machine`
--

LOCK TABLES `machine` WRITE;
/*!40000 ALTER TABLE `machine` DISABLE KEYS */;
INSERT INTO `machine` VALUES (1,'Extruder'),(2,'Printing 6 Colour'),(3,'Printing 8 Colour'),(4,'Laminator'),(5,'Slitter'),(6,'Pouch Maker'),(7,'Box Pouch Machine'),(8,'Side Seal Machine'),(9,'Bottom Seal Machine'),(10,'Handle Machine');
/*!40000 ALTER TABLE `machine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nid` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `description` varchar(100) NOT NULL,
  `ui` varchar(45) NOT NULL,
  `added_user` varchar(45) DEFAULT NULL,
  `date_added` date NOT NULL,
  `date_resolved` date DEFAULT NULL,
  `is_resolved` int(11) NOT NULL DEFAULT '0',
  `resolved_user` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_notify_notify_idx` (`type`),
  CONSTRAINT `fk_notify_notify` FOREIGN KEY (`type`) REFERENCES `user_notification_type` (`type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `printer`
--

DROP TABLE IF EXISTS `printer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `printer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_printer_printer_type_idx` (`type`),
  CONSTRAINT `fk_printer_printer_type` FOREIGN KEY (`type`) REFERENCES `printer_type` (`type`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `printer`
--

LOCK TABLES `printer` WRITE;
/*!40000 ALTER TABLE `printer` DISABLE KEYS */;
INSERT INTO `printer` VALUES (1,'PRN0001','Microsoft XPS Document Writer','Banquet','Ultra fast Invisible printing');
/*!40000 ALTER TABLE `printer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `printer_report`
--

DROP TABLE IF EXISTS `printer_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `printer_report` (
  `pid` varchar(45) NOT NULL,
  `rid` varchar(45) NOT NULL,
  PRIMARY KEY (`pid`,`rid`),
  KEY `fk_pr_r_idx` (`rid`),
  CONSTRAINT `fk_pr_p` FOREIGN KEY (`pid`) REFERENCES `printer` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pr_r` FOREIGN KEY (`rid`) REFERENCES `report` (`rid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `printer_report`
--

LOCK TABLES `printer_report` WRITE;
/*!40000 ALTER TABLE `printer_report` DISABLE KEYS */;
INSERT INTO `printer_report` VALUES ('PRN0001','RPT0001');
/*!40000 ALTER TABLE `printer_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `printer_type`
--

DROP TABLE IF EXISTS `printer_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `printer_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`type`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `printer_type`
--

LOCK TABLES `printer_type` WRITE;
/*!40000 ALTER TABLE `printer_type` DISABLE KEYS */;
INSERT INTO `printer_type` VALUES (1,'Alacarte'),(2,'Banquet'),(3,'Room'),(4,'Stock');
/*!40000 ALTER TABLE `printer_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reel`
--

DROP TABLE IF EXISTS `reel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reel_code` varchar(45) NOT NULL,
  `serial_number` varchar(45) NOT NULL,
  `item_name` varchar(45) NOT NULL,
  `item_des` varchar(45) DEFAULT NULL,
  `gsm` double DEFAULT NULL,
  `reel_width` double DEFAULT NULL,
  `reel_diameter` double DEFAULT NULL,
  `reel_number` double DEFAULT NULL,
  `initial_weight` double DEFAULT NULL,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `qty` int(11) DEFAULT NULL,
  `size` double DEFAULT NULL,
  `current_weight` double DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  `reel_fb` varchar(45) DEFAULT NULL,
  `label_print_count` varchar(45) NOT NULL DEFAULT '0',
  `reel_liner` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`reel_code`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reel`
--

LOCK TABLES `reel` WRITE;
/*!40000 ALTER TABLE `reel` DISABLE KEYS */;
INSERT INTO `reel` VALUES (1,'REL0001','SE20380293842098','Paper Roll','Thickness target paper',900,250,500,22344,1000,'2020-02-01 11:24:38',1,5,900,1,'B','0','K');
/*!40000 ALTER TABLE `reel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reel_log`
--

DROP TABLE IF EXISTS `reel_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reel_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reel_code` varchar(45) NOT NULL,
  `time_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `weight` double NOT NULL,
  `flag` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `reel_log_fk1_idx` (`reel_code`),
  CONSTRAINT `reel_log_fk1` FOREIGN KEY (`reel_code`) REFERENCES `reel` (`reel_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reel_log`
--

LOCK TABLES `reel_log` WRITE;
/*!40000 ALTER TABLE `reel_log` DISABLE KEYS */;
INSERT INTO `reel_log` VALUES (1,'REL0001','2020-02-02 03:15:25',200,0);
/*!40000 ALTER TABLE `reel_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `url` varchar(200) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `is_Delete_privilege` int(11) DEFAULT '1',
  `is_Network` int(11) DEFAULT '1',
  PRIMARY KEY (`rid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_repot_report_type_idx` (`type`),
  CONSTRAINT `fk_repot_report_type_idx` FOREIGN KEY (`type`) REFERENCES `report_type` (`type`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (1,'RPT0001','Profit And Loss Report','Finance','.//Reports//Profit&Loss.jasper',0,1,1),(32,'RPT0002','Stock Report','Stock','.//Reports//Stock.jasper',0,1,1);
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_reg`
--

DROP TABLE IF EXISTS `report_reg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report_reg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `report_id` varchar(45) NOT NULL,
  `printer_id` varchar(45) NOT NULL,
  `status` int(11) DEFAULT '0',
  `user_id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `report_reg_fk1` (`report_id`),
  KEY `report_reg_fk2` (`printer_id`),
  KEY `report_reg_fk3` (`user_id`),
  CONSTRAINT `report_reg_fk1` FOREIGN KEY (`report_id`) REFERENCES `report` (`rid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `report_reg_fk2` FOREIGN KEY (`printer_id`) REFERENCES `printer` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `report_reg_fk3` FOREIGN KEY (`user_id`) REFERENCES `user` (`EID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_reg`
--

LOCK TABLES `report_reg` WRITE;
/*!40000 ALTER TABLE `report_reg` DISABLE KEYS */;
/*!40000 ALTER TABLE `report_reg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_type`
--

DROP TABLE IF EXISTS `report_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`type`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_type`
--

LOCK TABLES `report_type` WRITE;
/*!40000 ALTER TABLE `report_type` DISABLE KEYS */;
INSERT INTO `report_type` VALUES (1,'Finance'),(2,'Stock');
/*!40000 ALTER TABLE `report_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scale`
--

DROP TABLE IF EXISTS `scale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scale` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `weight_scale_id` varchar(45) NOT NULL,
  `scale_id` varchar(45) NOT NULL,
  `customer_code` varchar(45) NOT NULL,
  `description` varchar(300) DEFAULT NULL,
  `reel_no` varchar(45) DEFAULT NULL,
  `job_no` varchar(45) DEFAULT NULL,
  `size` varchar(45) DEFAULT NULL,
  `length` varchar(45) DEFAULT NULL,
  `width` varchar(45) DEFAULT NULL,
  `epf_no` varchar(45) DEFAULT NULL,
  `batch_no` varchar(45) DEFAULT NULL,
  `machine` varchar(200) DEFAULT NULL,
  `gauge` varchar(200) DEFAULT NULL,
  `qty` double DEFAULT NULL,
  `gross_weight` double DEFAULT NULL,
  `net_weight` double DEFAULT NULL,
  `core_weight` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time_stamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `film` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`weight_scale_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `scale_fk1_idx` (`scale_id`),
  KEY `scale_fk2_idx` (`customer_code`),
  CONSTRAINT `scale_fk1` FOREIGN KEY (`scale_id`) REFERENCES `scale_register` (`scale_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `scale_fk2` FOREIGN KEY (`customer_code`) REFERENCES `customer` (`customer_code`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scale`
--

LOCK TABLES `scale` WRITE;
/*!40000 ALTER TABLE `scale` DISABLE KEYS */;
/*!40000 ALTER TABLE `scale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scale_register`
--

DROP TABLE IF EXISTS `scale_register`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scale_register` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `scale_id` varchar(45) NOT NULL,
  `scale_name` varchar(45) DEFAULT NULL,
  `com_port` varchar(45) DEFAULT NULL,
  `board_rate` int(11) DEFAULT NULL,
  PRIMARY KEY (`scale_id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scale_register`
--

LOCK TABLES `scale_register` WRITE;
/*!40000 ALTER TABLE `scale_register` DISABLE KEYS */;
INSERT INTO `scale_register` VALUES (3,'SCA0001','New Scale','/dev/cu.usbserial-1410',9600);
/*!40000 ALTER TABLE `scale_register` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `server_config`
--

DROP TABLE IF EXISTS `server_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `server_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(20) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `server_config`
--

LOCK TABLES `server_config` WRITE;
/*!40000 ALTER TABLE `server_config` DISABLE KEYS */;
INSERT INTO `server_config` VALUES (1,'127.0.0.1',1099);
/*!40000 ALTER TABLE `server_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `size`
--

DROP TABLE IF EXISTS `size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `size` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `size` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `size`
--

LOCK TABLES `size` WRITE;
/*!40000 ALTER TABLE `size` DISABLE KEYS */;
INSERT INTO `size` VALUES (1,'5'),(2,'6'),(3,'7'),(4,'8'),(5,'9'),(6,'10'),(7,'11'),(8,'12'),(9,'EG'),(10,'G'),(11,'GL'),(12,'GM'),(13,'K'),(14,'L'),(15,'LL'),(16,'M'),(17,'P'),(18,'PL'),(19,'PM'),(20,'S'),(21,'XG'),(22,'GG'),(23,'XL'),(24,'XL+'),(25,'XP'),(26,'XS'),(27,'XXG'),(28,'XXL'),(29,'XXL+'),(30,'6/7'),(31,'7/8'),(32,'8/9'),(33,'9/10'),(34,'10/11'),(35,'11/12');
/*!40000 ALTER TABLE `size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slow_log`
--

DROP TABLE IF EXISTS `slow_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `slow_log` (
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_host` mediumtext NOT NULL,
  `query_time` time NOT NULL,
  `lock_time` time NOT NULL,
  `rows_sent` int(11) NOT NULL,
  `rows_examined` int(11) NOT NULL,
  `db` varchar(512) NOT NULL,
  `last_insert_id` int(11) NOT NULL,
  `insert_id` int(11) NOT NULL,
  `server_id` int(10) unsigned NOT NULL,
  `sql_text` mediumtext NOT NULL,
  `thread_id` bigint(21) unsigned NOT NULL
) ENGINE=CSV DEFAULT CHARSET=utf8 COMMENT='Slow log';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slow_log`
--

LOCK TABLES `slow_log` WRITE;
/*!40000 ALTER TABLE `slow_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `slow_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `EID` varchar(45) NOT NULL,
  `title` varchar(10) NOT NULL,
  `name` varchar(200) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `spec_code` varchar(45) DEFAULT NULL,
  `flag` varchar(45) DEFAULT NULL,
  `category_type` varchar(45) NOT NULL,
  `is_canceled` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`EID`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  KEY `usertype` (`flag`),
  KEY `categoryFK_idx` (`category_type`),
  KEY `user` (`flag`),
  KEY `user_fk2` (`category_type`),
  CONSTRAINT `user_fk1` FOREIGN KEY (`flag`) REFERENCES `user_type` (`type`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `user_fk2` FOREIGN KEY (`category_type`) REFERENCES `user_sub_type` (`type`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (8,'EM0004','Mr.','Saiton','saiton','3CRO+GzZlkI=',NULL,'Manager','Administrator',0),(9,'EM0005','Mr.','admin','admin','3CRO+GzZlkI=',NULL,'Ultra User','Administrator',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_notification_type`
--

DROP TABLE IF EXISTS `user_notification_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_notification_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`type`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_notification_type`
--

LOCK TABLES `user_notification_type` WRITE;
/*!40000 ALTER TABLE `user_notification_type` DISABLE KEYS */;
INSERT INTO `user_notification_type` VALUES (8,'Stock');
/*!40000 ALTER TABLE `user_notification_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_notifications`
--

DROP TABLE IF EXISTS `user_notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_notifications` (
  `EID` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `show` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`EID`,`type`),
  KEY `fkusernotf2_idx` (`type`),
  CONSTRAINT `fkusernotf1` FOREIGN KEY (`EID`) REFERENCES `user` (`EID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fkusernotf2` FOREIGN KEY (`type`) REFERENCES `user_notification_type` (`type`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_notifications`
--

LOCK TABLES `user_notifications` WRITE;
/*!40000 ALTER TABLE `user_notifications` DISABLE KEYS */;
INSERT INTO `user_notifications` VALUES ('EM0005','Stock',0);
/*!40000 ALTER TABLE `user_notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_permission_type`
--

DROP TABLE IF EXISTS `user_permission_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_permission_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`type`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_permission_type`
--

LOCK TABLES `user_permission_type` WRITE;
/*!40000 ALTER TABLE `user_permission_type` DISABLE KEYS */;
INSERT INTO `user_permission_type` VALUES (73,'User Registration'),(128,'Weight Scale'),(129,'Report Generator'),(130,'Report Registration'),(131,'Report Settings'),(132,'Printer Registration'),(133,'Scale Registration'),(134,'Search Weight Scale'),(135,'Customer Registration'),(136,'Reel Requisition');
/*!40000 ALTER TABLE `user_permission_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_permissions`
--

DROP TABLE IF EXISTS `user_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_permissions` (
  `EID` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `allow_insert` int(11) NOT NULL DEFAULT '0',
  `allow_update` int(11) NOT NULL DEFAULT '0',
  `allow_delete` int(11) NOT NULL DEFAULT '0',
  `allow_view` int(11) NOT NULL DEFAULT '0',
  `preview_preference` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`EID`,`type`),
  KEY `fkuserperf2_idx` (`type`),
  CONSTRAINT `fkuserperf1` FOREIGN KEY (`EID`) REFERENCES `user` (`EID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fkuserperf2` FOREIGN KEY (`type`) REFERENCES `user_permission_type` (`type`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_permissions`
--

LOCK TABLES `user_permissions` WRITE;
/*!40000 ALTER TABLE `user_permissions` DISABLE KEYS */;
INSERT INTO `user_permissions` VALUES ('EM0004','Customer Registration',1,1,1,1,0),('EM0004','Printer Registration',1,1,1,1,0),('EM0004','Report Generator',1,1,1,1,0),('EM0004','Report Registration',1,1,1,1,0),('EM0004','Report Settings',1,1,1,1,0),('EM0004','Scale Registration',1,1,1,1,0),('EM0004','Search Weight Scale',1,1,1,1,0),('EM0004','User Registration',1,1,1,1,0),('EM0004','Weight Scale',1,1,1,1,0),('EM0005','Customer Registration',1,1,1,1,0),('EM0005','Printer Registration',1,1,1,1,0),('EM0005','Reel Requisition',1,1,1,1,0),('EM0005','Report Generator',1,1,1,1,0),('EM0005','Report Registration',1,1,1,1,0),('EM0005','Report Settings',1,1,1,1,0),('EM0005','Scale Registration',1,1,1,1,0),('EM0005','Search Weight Scale',1,1,1,1,0),('EM0005','User Registration',1,1,1,1,0),('EM0005','Weight Scale',1,1,1,1,0);
/*!40000 ALTER TABLE `user_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_sub_type`
--

DROP TABLE IF EXISTS `user_sub_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_sub_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`type`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_sub_type`
--

LOCK TABLES `user_sub_type` WRITE;
/*!40000 ALTER TABLE `user_sub_type` DISABLE KEYS */;
INSERT INTO `user_sub_type` VALUES (1,'Stock'),(2,'Administrator'),(3,'Staff');
/*!40000 ALTER TABLE `user_sub_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`type`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
INSERT INTO `user_type` VALUES (1,'Ultra User'),(2,'Super User'),(3,'Accountant'),(4,'Manager'),(5,'Walker');
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-02 10:22:45
