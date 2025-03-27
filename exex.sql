-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: exams
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `discipline`
--

DROP TABLE IF EXISTS `discipline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discipline` (
  `faculty_name` varchar(255) DEFAULT NULL,
  `Lgroup` int DEFAULT NULL,
  `faculty_number` int DEFAULT NULL,
  `grade_in_discipline` float DEFAULT NULL,
  `Course` int DEFAULT NULL,
  `ID` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discipline`
--

LOCK TABLES `discipline` WRITE;
/*!40000 ALTER TABLE `discipline` DISABLE KEYS */;
INSERT INTO `discipline` VALUES ('CS',2,178,5,2,1),('CI',1,125,3,3,2),('TC',6,101,4,1,3),('MT',3,156,3,4,4),('TP',1,89,5,2,5),('MC',4,148,3,1,6),('BT',5,171,4,3,7);
/*!40000 ALTER TABLE `discipline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foreign_keys`
--

DROP TABLE IF EXISTS `foreign_keys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foreign_keys` (
  `discipline_id` int DEFAULT NULL,
  `students_id` int DEFAULT NULL,
  KEY `discipline_id` (`discipline_id`),
  KEY `students_id` (`students_id`),
  CONSTRAINT `foreign_keys_ibfk_1` FOREIGN KEY (`discipline_id`) REFERENCES `discipline` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `foreign_keys_ibfk_2` FOREIGN KEY (`students_id`) REFERENCES `students` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foreign_keys`
--

LOCK TABLES `foreign_keys` WRITE;
/*!40000 ALTER TABLE `foreign_keys` DISABLE KEYS */;
INSERT INTO `foreign_keys` VALUES (1,1),(2,2),(3,4),(4,3),(5,5),(6,6),(7,7);
/*!40000 ALTER TABLE `foreign_keys` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `gradebook_number` int DEFAULT NULL,
  `faculty_name` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `Lgroup` int DEFAULT NULL,
  `faculty_number` int DEFAULT NULL,
  `grade_in_discipline1` float DEFAULT NULL,
  `grade_in_discipline2` float DEFAULT NULL,
  `grade_in_discipline3` float DEFAULT NULL,
  `Course` int DEFAULT NULL,
  `ID` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (250,'CS','URII','SEREDNII','OLEGOVICH',2,178,5,5,5,2,1),(530,'CI','ULIANA','MELNIK','STEPANIWNA',1,125,3,4,3,3,2),(11,'MT','LUBOMIR','TRUSH','IVANOVICH',3,156,3,3,3,1,3),(136,'TC','STAS','SADOVII','VOLODIMIROVICH',6,101,4,3,4,1,4),(127,'TP','JONH','WHEEK','BAKLAZHANOVICH',1,89,5,4,5,2,5),(333,'MC','LADY','DOWN','RIPTIDOVICH',4,148,3,4,3,1,6),(12,'BT','MARK','MASK','ILONOVICH',5,171,4,4,4,1,7);
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-27 12:48:42
