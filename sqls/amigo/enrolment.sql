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

-- Dumping structure for table amigo.enrolment
DROP TABLE IF EXISTS `enrolment`;
CREATE TABLE IF NOT EXISTS `enrolment` (
  `id` bigint(20) NOT NULL,
  `grade` int(11) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  `subject_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKquem30hnspsnegde2q2bhvou` (`student_id`),
  KEY `FKaymr316fboiqh7viwmlnw9c98` (`subject_id`),
  CONSTRAINT `FKaymr316fboiqh7viwmlnw9c98` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`),
  CONSTRAINT `FKquem30hnspsnegde2q2bhvou` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table amigo.enrolment: ~7 rows (approximately)
/*!40000 ALTER TABLE `enrolment` DISABLE KEYS */;
REPLACE INTO `enrolment` (`id`, `grade`, `student_id`, `subject_id`) VALUES
	(1, NULL, 1, 1),
	(2, NULL, 9, 16),
	(4, NULL, 7, 5),
	(5, NULL, 7, 6),
	(6, NULL, 2, 5),
	(7, NULL, 8, 1),
	(8, NULL, 5, 1);
/*!40000 ALTER TABLE `enrolment` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
