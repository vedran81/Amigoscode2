-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.6.7-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table amigo.student
DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `id` bigint(20) NOT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `study_year` int(11) DEFAULT NULL,
  `mentor_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfmk16k9whxemgkowg3mt24m3w` (`mentor_id`),
  CONSTRAINT `FKfmk16k9whxemgkowg3mt24m3w` FOREIGN KEY (`mentor_id`) REFERENCES `mentor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table amigo.student: ~7 rows (approximately)
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
REPLACE INTO `student` (`id`, `date_of_birth`, `email`, `first_name`, `last_name`, `status`, `study_year`, `mentor_id`) VALUES
	(1, '2003-11-11', 'mariam@gmail.com', 'Mariam', 'Jamal', '', 1, NULL),
	(2, '2002-12-01', 'alex@gmail.com', 'Alex', 'Ivancic', 'part-time', 3, 2),
	(5, '2000-05-06', 'ivanz@gmail.com', 'Ivan', 'Zec', '', 1, NULL),
	(6, '1998-06-09', 'vedran.smolec@gmail.com', 'Marta', 'Kos', ' ', 3, 1),
	(7, '2004-01-06', 'mz2@yahoo.com', 'Marko', 'Zoltan', '', 2, NULL),
	(8, '2002-05-12', 'kbah@serv.net', 'Kata', 'Bah', '', 1, NULL),
	(9, '2022-05-12', 'macan@gmail.com', 'Matej', 'Car', 'placa svima kavu kad polozi do kraja', 4, 2);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
