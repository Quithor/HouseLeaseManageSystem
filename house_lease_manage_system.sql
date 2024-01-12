-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 39.105.133.103    Database: house_lease_manage_system
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `discuss`
--

DROP TABLE IF EXISTS `discuss`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discuss` (
  `tenant_id` int NOT NULL,
  `house_id` int NOT NULL,
  `comment_time` datetime DEFAULT NULL,
  `comment_content` text,
  `star` int NOT NULL,
  KEY `FK_discuss` (`tenant_id`),
  KEY `FK_discuss2` (`house_id`),
  CONSTRAINT `FK_discuss`
  FOREIGN KEY (`tenant_id`)
  REFERENCES `tenant` (`tenant_id`)
  ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_discuss2`
  FOREIGN KEY (`house_id`)
  REFERENCES `house` (`house_id`)
  ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discuss`
--

INSERT INTO `discuss` VALUES
    (100001,10105,'2024-01-01 15:35:31','房子特别舒适，但规格还是有点小，个人不是很满意',4),
    (100002,10201,'2024-01-03 16:36:22','我太喜欢这里了，不仅布局好，环境好，还特别大',5),
    (100008,10086,'2024-02-29 14:35:00','这里的房东个个都是人才，说话又好听长得又好看，我超喜欢租这',5),
    (100010,12345,'2024-05-16 15:14:26','听说我家哥哥也租了这，说明这里环境很好',5),
    (100004,11111,'2024-12-12 12:00:00','好，本来想给6星的但是只有5颗所以我就点了第六颗星',1);

--
-- Table structure for table `house`
--

DROP TABLE IF EXISTS `house`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `house` (
  `house_id` int NOT NULL,
  `landlord_id` int NOT NULL,
  `house_rent` double NOT NULL,
  `house_Type` varchar(20) NOT NULL,
  `LeaseStatus` enum('true','false') NOT NULL,
  `house_address` varchar(50) NOT NULL,
  PRIMARY KEY (`house_id`),
  KEY `FK_have` (`landlord_id`),
  CONSTRAINT `FK_have`
  FOREIGN KEY (`landlord_id`)
  REFERENCES `landlord` (`landlord_id`)
  ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house`
--

INSERT INTO `house` VALUES
    (10016,1544658,1425,'3r1h','true','广东省东莞市北环路20号'),
    (10042,1543516,4351,'4r2h','true','广东省东莞市南城大道88号'),
    (10086,1593570,1145.14,'3r1h','false','广东省东莞市朝阳街10号'),
    (10105,1043712,1998.99,'3r1h','false','广东省东莞市麻涌镇沿江西1路7号'),
    (10201,1593570,3560.5,'4r2h','false','广东省广州市员村二横路1号'),
    (11111,1543516,2222.22,'2r2h','false','广东省东莞市大朗镇金朗大道27号'),
    (12345,1043712,1685,'3r1h','false','广东省东莞市长安镇华明路5号'),
    (14444,1544658,2500,'3r2h','true','广东省东莞市东城区东城路39号'),
    (15462,1687564,3000,'4r1h','true','广东省东莞市塘厦镇龙背岭村富民路8号');
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003
    TRIGGER `delete_house_table_row`
    AFTER DELETE ON `house` FOR EACH ROW BEGIN
    DELETE FROM discuss WHERE house_id = OLD.house_id;
    DELETE FROM lease WHERE house_id = OLD.house_id;
    DELETE FROM subscribe WHERE house_id = OLD.house_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Temporary view structure for view `house_comment`
--

DROP TABLE IF EXISTS `house_comment`;
/*!50001 DROP VIEW IF EXISTS `house_comment`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `house_comment` AS SELECT 
 1 AS `房子编码`,
 1 AS `房子地址`,
 1 AS `房东姓名`,
 1 AS `租户评论`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `housecircumstance`
--

DROP TABLE IF EXISTS `housecircumstance`;
/*!50001 DROP VIEW IF EXISTS `housecircumstance`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `house_circumstance` AS SELECT
 1 AS `房子编码`,
 1 AS `房子是否空置`,
 1 AS `房租（元/月）`,
 1 AS `房屋地址`,
 1 AS `预约时间`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `landlord`
--

DROP TABLE IF EXISTS `landlord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `landlord` (
  `landlord_id` int NOT NULL,
  `landlord_name` varchar(50) NOT NULL,
  `landlord_password` varchar(50) NOT NULL,
  `landlord_age` int DEFAULT NULL,
  `landlord_sex` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`landlord_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `landlord`
--

INSERT INTO `landlord` VALUES
    (1043712,'Xilan','147258',20,'man'),
    (1543516,'tim','114514',20,'woman'),
    (1544658,'tony','164857',24,'woman'),
    (1593570,'anny','136546',25,'woman'),
    (1687564,'steve','166945',40,'woman');
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/
/*!50003 TRIGGER `delete_landlord_table_row`
    AFTER DELETE ON `landlord`
    FOR EACH ROW
    begin
        delete from house where landlord_id=old.landlord_id;
    end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `lease`
--

DROP TABLE IF EXISTS `lease`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lease` (
  `house_id` int NOT NULL,
  `tenant_id` int NOT NULL,
  `signing_time` datetime DEFAULT NULL,
  `begin_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `deposit` double DEFAULT NULL,
  KEY `FK_Lease` (`house_id`),
  KEY `FK_Lease2` (`tenant_id`),
  CONSTRAINT `FK_Lease`
  FOREIGN KEY (`house_id`)
  REFERENCES `house` (`house_id`)
  ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Lease2`
  FOREIGN KEY (`tenant_id`)
  REFERENCES `tenant` (`tenant_id`)
  ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lease`
--

INSERT INTO `lease` VALUES
    (10105,100001,'2019-12-20 14:38:49','2020-01-01 14:38:48','2020-12-01 14:38:49',998.99),
    (10201,100002,'2018-06-07 15:08:22','2018-06-08 15:08:22','2020-06-08 15:08:22',2500.5),
    (11111,100004,'2019-12-14 16:28:49','2019-12-15 06:34:12','2021-12-01 14:31:00',100.33),
    (12345,100010,'2020-11-30 08:42:34','2020-12-01 09:00:00','2024-01-07 16:04:00',100.44),
    (10086,100008,'2021-05-01 09:40:12','2021-06-01 10:00:00','2024-06-01 14:51:26',100.55),
    (11111,100005,'2021-04-01 10:42:30','2021-11-11 06:00:00','2030-12-12 16:24:30',100.66);
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/
    /*!50017 DEFINER=`root`@`%`*/
    /*!50003 TRIGGER `house_null`
    AFTER INSERT ON `lease`
    FOR EACH ROW begin
    update house
    set LeaseStatus = 'false'
    where house_id = new.house_id;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `subscribe`
--

DROP TABLE IF EXISTS `subscribe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subscribe` (
  `tenant_id` int NOT NULL,
  `house_id` int NOT NULL,
  `reservation_time` datetime DEFAULT NULL,
  KEY `FK_subscribe` (`tenant_id`),
  KEY `FK_subscribe2` (`house_id`),
  CONSTRAINT `FK_subscribe`
  FOREIGN KEY (`tenant_id`)
  REFERENCES `tenant` (`tenant_id`)
  ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_subscribe2`
  FOREIGN KEY (`house_id`)
  REFERENCES `house` (`house_id`)
  ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscribe`
--

INSERT INTO `subscribe` VALUES
    (100001,10105,'2019-12-15 14:41:13'),
    (100002,10201,'2018-06-05 15:07:43'),
    (100005,11111,'2021-04-01 09:42:30'),
    (100008,10086,'2021-05-01 08:40:12'),
    (100010,12345,'2020-11-30 07:42:34'),
    (100004,11111,'2019-12-14 15:28:49');

--
-- Table structure for table `tenant`
--

DROP TABLE IF EXISTS `tenant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant` (
  `tenant_id` int NOT NULL,
  `tenant_name` varchar(50) NOT NULL,
  `tenant_password` varchar(50) NOT NULL,
  `tenant_age` int DEFAULT NULL,
  `tenant_sex` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant`
--

INSERT INTO `tenant` VALUES
    (100001,'顾倾城','134560465',24,'woman'),
    (100002,'wliiam','147258369',18,'man'),
    (100003,'小红','135462467',20,'woman'),
    (100004,'tom','146868452',23,'man'),
    (100005,'jerry','125464552',24,'man'),
    (100006,'小智','215653585',27,'man'),
    (100007,'小帅','wozhenshuai',19,'man'),
    (100008,'小美','wozhenmei',20,'woman'),
    (100009,'大黑','wobuhei666',67,'man'),
    (100010,'大壮','wozhenzhuang999',45,'man');
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/
    /*!50017 DEFINER=`root`@`%`*/
    /*!50003 TRIGGER `delete_tenant_table_row`
    AFTER DELETE ON `tenant`
    FOR EACH ROW BEGIN
    DELETE FROM discuss WHERE tenant_id = OLD.tenant_id;
    DELETE FROM lease WHERE tenant_id = OLD.tenant_id;
    DELETE FROM subscribe WHERE subscribe.tenant_id = OLD.tenant_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Temporary view structure for view `tenant_and_landlord`
--

DROP TABLE IF EXISTS `tenant_and_landlord`;
/*!50001 DROP VIEW IF EXISTS `tenant_and_landlord`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `tenant_and_landlord` AS SELECT 
 1 AS `租客`,
 1 AS `房东`,
 1 AS `房子编码`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `tenant_basical_info`
--

DROP TABLE IF EXISTS `tenant_basical_info`;
/*!50001 DROP VIEW IF EXISTS `tenant_basical_info`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `tenant_basical_info` AS SELECT 
 1 AS `租户账号`,
 1 AS `租户年龄`,
 1 AS `租户性别`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `house_comment`
--

/*!50001 DROP VIEW IF EXISTS `house_comment`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `house_comment` AS
    select `house`.`house_id` AS `房子编码`,
           `house`.`house_address` AS `房子地址`,
           `landlord`.`landlord_name` AS `房东姓名`,
           `discuss`.`comment_content` AS `租户评论`
    from ((`discuss` join `house`) join `landlord`)
    where ((`house`.`house_id` = `discuss`.`house_id`)
       and (`landlord`.`landlord_id` = `house`.`landlord_id`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `house_circumstance`
--

/*!50001 DROP VIEW IF EXISTS `house_circumstance`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `house_circumstance` AS
    select `house`.`house_id` AS `房子编码`,
           `house`.`LeaseStatus` AS `房子是否空置`,
           `house`.`house_rent` AS `房租(元/月)`,
           `house`.`house_address` AS `房屋地址`,
           `subscribe`.`reservation_time` AS `预约时间`
    from (`house` join `subscribe`)
    where (`house`.`house_id` = `subscribe`.`house_id`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `tenant_and_landlord`
--

/*!50001 DROP VIEW IF EXISTS `tenant_and_landlord`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `tenant_and_landlord` AS
    select distinct `tenant`.`tenant_name` AS `租客`,
                    `landlord`.`landlord_name` AS `房东`,
                    `lease`.`house_id` AS `房子编码`
    from (((`tenant` join `landlord`) join `house`) join `lease`)
    where ((`landlord`.`landlord_id` = `house`.`landlord_id`)
       and (`lease`.`house_id` = `house`.`house_id`)
       and (`lease`.`tenant_id` = `tenant`.`tenant_id`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `tenant_basical_info`
--

/*!50001 DROP VIEW IF EXISTS `tenant_basical_info`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `tenant_basical_info` AS
    select `tenant`.`tenant_id` AS `租户账号`,
           `tenant`.`tenant_age` AS `租户年龄`,
           `tenant`.`tenant_sex` AS `租户性别`
    from `tenant` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-07 16:20:44
