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

-- Dumping structure for table amigo.subject
DROP TABLE IF EXISTS `subject`;
CREATE TABLE IF NOT EXISTS `subject` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table amigo.subject: ~14 rows (approximately)
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
REPLACE INTO `subject` (`id`, `name`, `year`) VALUES
	(1, 'Matematika I.', 1),
	(2, 'Informatika I.', 1),
	(3, 'Engleski jezik I.', 1),
	(4, 'Psihologija stresa', 1),
	(5, 'Matematika II.', 2),
	(6, 'Termodinamika', 2),
	(7, 'Engleski jezik II.', 2),
	(8, 'Mehanika', 2),
	(9, 'Osnove aerodinamike i mehanike leta', 3),
	(10, 'Sustavi i oprema zrakoplova I.', 3),
	(11, 'Engleski jezik III.', 3),
	(12, 'Psihofiziologija rada', 3),
	(13, 'Sustavi i oprema zrakoplova II.', 4),
	(14, 'Osnove automatskog upravljanja', 4),
	(15, 'Pogon zrakoplova I.', 4),
	(16, 'Hidraulika i pneumatika', 4);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
subject