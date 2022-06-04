-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema sleepdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sleepdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sleepdb` DEFAULT CHARACTER SET utf8 ;
USE `sleepdb` ;

-- -----------------------------------------------------
-- Table `sleepdb`.`sleep_period`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sleepdb`.`sleep_period` ;

CREATE TABLE IF NOT EXISTS `sleepdb`.`sleep_period` (
  `id` INT NOT NULL,
  `start` DATETIME NULL,
  `end` VARCHAR(45) NULL,
  `duration` DECIMAL(2) NULL,
  `had_alchohol` TINYINT NOT NULL,
  `large_dinner` TINYINT NOT NULL,
  `excercised` TINYINT NOT NULL,
  `evening_activity` VARCHAR(45) NULL,
  `took_nap` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS sleepuser;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'sleepuser' IDENTIFIED BY 'sleepuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `sleepdb`.* TO 'sleepuser';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `sleepdb`.`sleep_period`
-- -----------------------------------------------------
START TRANSACTION;
USE `sleepdb`;
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alchohol`, `large_dinner`, `excercised`, `evening_activity`, `took_nap`) VALUES (1, NULL, NULL, 8.25, 0, 0, 0, 'Read Book', 0);

COMMIT;

