-- MySQL dump 10.13  Distrib 5.7.36, for Linux (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.7.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class` (
    `cid` int(11) NOT NULL AUTO_INCREMENT,
    `cname` varchar(255) DEFAULT NULL,
    `tid` int(11) DEFAULT NULL,
    PRIMARY KEY (`cid`),
    UNIQUE KEY `class_cid_uindex` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES (1,'???',1),(2,'???',2);
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `condition_info`
--

DROP TABLE IF EXISTS `condition_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `condition_info` (
    `id` varchar(64) NOT NULL,
    `expression` varchar(2048) CHARACTER SET utf8 DEFAULT NULL,
    `parent_id` varchar(64) DEFAULT NULL COMMENT '自引用id ',
    `result_info` varchar(512) DEFAULT NULL COMMENT '响应信息',
    `context_info` varchar(255) DEFAULT NULL,
    `param_info_ids` varchar(255) DEFAULT NULL,
    `rule_id` varchar(64) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `condition_info_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='条件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `condition_info`
--

LOCK TABLES `condition_info` WRITE;
/*!40000 ALTER TABLE `condition_info` DISABLE KEYS */;
INSERT INTO `condition_info` VALUES ('001','指标.锁定次数<3',NULL,NULL,NULL,NULL,'123'),('002','指标.安全信用>=5',NULL,NULL,NULL,NULL,'123'),('003','指标.设备 = 000001',NULL,NULL,NULL,NULL,'123'),('004','指标.访问时间==null or 指标.访问时间 < new Date()',NULL,NULL,NULL,NULL,'123');
/*!40000 ALTER TABLE `condition_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `param_info`
--

DROP TABLE IF EXISTS `param_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `param_info` (
    `id` varchar(64) NOT NULL,
    `field` varchar(255) DEFAULT NULL COMMENT '字段',
    `title` varchar(255) DEFAULT NULL COMMENT '标题',
    `type` varchar(255) DEFAULT NULL COMMENT '数据类型',
    `c_info` varchar(255) DEFAULT NULL COMMENT '类信息',
    PRIMARY KEY (`id`),
    UNIQUE KEY `paramInfo_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `param_info`
--

LOCK TABLES `param_info` WRITE;
/*!40000 ALTER TABLE `param_info` DISABLE KEYS */;
INSERT INTO `param_info` VALUES ('1','String','String','String',NULL);
/*!40000 ALTER TABLE `param_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rule_info`
--

DROP TABLE IF EXISTS `rule_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rule_info` (
    `id` varchar(64) NOT NULL,
    `name` varchar(255) DEFAULT NULL,
    `status` varchar(64) DEFAULT NULL,
    `seq` int(11) DEFAULT '8',
    `remark` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `rule_info_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rule_info`
--

LOCK TABLES `rule_info` WRITE;
/*!40000 ALTER TABLE `rule_info` DISABLE KEYS */;
INSERT INTO `rule_info` VALUES ('123','test_name','test_status',0,'test');
/*!40000 ALTER TABLE `rule_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
    `sid` int(11) NOT NULL AUTO_INCREMENT,
    `sname` varchar(255) DEFAULT NULL,
    `cid` int(11) DEFAULT NULL,
    PRIMARY KEY (`sid`),
    UNIQUE KEY `student_sid_uindex` (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'??A',1),(2,'??B',1),(3,'??C',1),(4,'??D',2),(5,'??E',2);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
    `tid` int(11) NOT NULL AUTO_INCREMENT,
    `tname` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`tid`),
    UNIQUE KEY `teacher_tid_uindex` (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'??A'),(2,'??B');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_index`
--

DROP TABLE IF EXISTS `user_index`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_index` (
    `id` varchar(255) NOT NULL,
    `user_name` varchar(255) DEFAULT NULL,
    `safe_credit` varchar(255) DEFAULT NULL,
    `verification` varchar(255) DEFAULT NULL,
    `device` varchar(255) DEFAULT NULL,
    `Interview_time` varchar(255) DEFAULT NULL,
    `Lock_num` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_index_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_index`
--

LOCK TABLES `user_index` WRITE;
/*!40000 ALTER TABLE `user_index` DISABLE KEYS */;
INSERT INTO `user_index` VALUES ('1','用户1','5','1','0011001100',NULL,'0');
/*!40000 ALTER TABLE `user_index` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-28  9:27:42
