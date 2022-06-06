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

-- Dumping structure for table amigo.student_enrolment_list
DROP TABLE IF EXISTS `student_enrolment_list`;
CREATE TABLE IF NOT EXISTS `student_enrolment_list` (
  `student_id` bigint(20) NOT NULL,
  `enrolment_list` bigint(20) NOT NULL,
  UNIQUE KEY `UK_2bx87rl00wghev9qp34kh8hex` (`enrolment_list`),
  KEY `FK5kor54cupb4i1alau25q33yor` (`student_id`),
  CONSTRAINT `FK3snqrifvnsgxbfprsfg1nwnlt` FOREIGN KEY (`enrolment_list`) REFERENCES `enrolment` (`id`),
  CONSTRAINT `FK5kor54cupb4i1alau25q33yor` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table amigo.student_enrolment_list: ~0 rows (approximately)
/*!40000 ALTER TABLE `student_enrolment_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_enrolment_list` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
