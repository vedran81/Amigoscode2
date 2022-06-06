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

-- Dumping structure for table amigo.mentor_student_list
DROP TABLE IF EXISTS `mentor_student_list`;
CREATE TABLE IF NOT EXISTS `mentor_student_list` (
  `mentor_id` bigint(20) NOT NULL,
  `student_list` bigint(20) NOT NULL,
  UNIQUE KEY `UK_605icojk8m5tdpl7chwmx49wm` (`student_list`),
  KEY `FKbtc8d5y1qiuuh3clvbxp2whdh` (`mentor_id`),
  CONSTRAINT `FK9n5ulw8g7kimulwndl0malypp` FOREIGN KEY (`student_list`) REFERENCES `student` (`id`),
  CONSTRAINT `FKbtc8d5y1qiuuh3clvbxp2whdh` FOREIGN KEY (`mentor_id`) REFERENCES `mentor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table amigo.mentor_student_list: ~0 rows (approximately)
/*!40000 ALTER TABLE `mentor_student_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `mentor_student_list` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
