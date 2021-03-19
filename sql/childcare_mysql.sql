--
-- Table structure for table `carer`
--

DROP TABLE IF EXISTS `carer`;
CREATE TABLE `carer` (
  `id` bigint NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
INSERT INTO `hibernate_sequence` VALUES (17);
UNLOCK TABLES;

--
-- Table structure for table `meeting`
--

DROP TABLE IF EXISTS `meeting`;
CREATE TABLE `meeting` (
  `id` bigint NOT NULL,
  `init_date` datetime NOT NULL,
  `observations` varchar(255) DEFAULT NULL,
  `time_requested` int NOT NULL,
  `id_giver` bigint NOT NULL,
  `id_requester` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg2v90xgnlulglkkyvddtsegfy` (`id_giver`),
  KEY `FKs54105y6k4s4d5e60uqtqtawb` (`id_requester`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES `carer` WRITE;
INSERT INTO `carer` VALUES (6,'Luis','Ochoa'),(7,'Jose','López Guzmendi'),(8,'Diego','García Egea'),(9,'Mario','Tenorio Rute'),(13,'Juan','García Pérez'),(14,'Belén','Rueda Hilario'),(15,'Laura','Villena Cabo'),(16,'Paula','Almendralejo Ocaña');

LOCK TABLES `meeting` WRITE;
INSERT INTO `meeting` VALUES (10,'2021-03-18 19:21:06','Tarde de cine',40,6,7),(11,'2021-03-18 19:21:19','Curso de cocina',150,9,8),(12,'2021-03-18 19:21:54','Cena con su pareja',65,7,9);
UNLOCK TABLES;

