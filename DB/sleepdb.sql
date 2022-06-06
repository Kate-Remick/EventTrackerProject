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
-- Table `sleepdb`.`evening_activity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sleepdb`.`evening_activity` ;

CREATE TABLE IF NOT EXISTS `sleepdb`.`evening_activity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sleepdb`.`workout`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sleepdb`.`workout` ;

CREATE TABLE IF NOT EXISTS `sleepdb`.`workout` (
  `id` INT NOT NULL,
  `time_of_day` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sleepdb`.`sleep_period`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sleepdb`.`sleep_period` ;

CREATE TABLE IF NOT EXISTS `sleepdb`.`sleep_period` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `start` DATETIME NULL,
  `end` VARCHAR(45) NULL,
  `duration` DECIMAL(2) NULL,
  `had_alcohol` TINYINT NOT NULL,
  `large_dinner` TINYINT NOT NULL,
  `excercised` TINYINT NOT NULL,
  `took_nap` TINYINT NULL,
  `quality` INT NULL,
  `evening_activity_id` INT NOT NULL,
  `workout_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sleep_period_evening_activity1_idx` (`evening_activity_id` ASC),
  INDEX `fk_sleep_period_workout1_idx` (`workout_id` ASC),
  CONSTRAINT `fk_sleep_period_evening_activity1`
    FOREIGN KEY (`evening_activity_id`)
    REFERENCES `sleepdb`.`evening_activity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sleep_period_workout1`
    FOREIGN KEY (`workout_id`)
    REFERENCES `sleepdb`.`workout` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
-- Data for table `sleepdb`.`evening_activity`
-- -----------------------------------------------------
START TRANSACTION;
USE `sleepdb`;
INSERT INTO `sleepdb`.`evening_activity` (`id`, `name`) VALUES (1, 'Read a Book');
INSERT INTO `sleepdb`.`evening_activity` (`id`, `name`) VALUES (2, 'Painting');
INSERT INTO `sleepdb`.`evening_activity` (`id`, `name`) VALUES (3, 'Video Games');
INSERT INTO `sleepdb`.`evening_activity` (`id`, `name`) VALUES (4, 'Coding');
INSERT INTO `sleepdb`.`evening_activity` (`id`, `name`) VALUES (5, 'Work');
INSERT INTO `sleepdb`.`evening_activity` (`id`, `name`) VALUES (6, 'Cleaning');
INSERT INTO `sleepdb`.`evening_activity` (`id`, `name`) VALUES (7, 'Yard Work');
INSERT INTO `sleepdb`.`evening_activity` (`id`, `name`) VALUES (8, 'Watched TV Show');
INSERT INTO `sleepdb`.`evening_activity` (`id`, `name`) VALUES (9, 'Watched Movie');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sleepdb`.`workout`
-- -----------------------------------------------------
START TRANSACTION;
USE `sleepdb`;
INSERT INTO `sleepdb`.`workout` (`id`, `time_of_day`) VALUES (1, 'Morning');
INSERT INTO `sleepdb`.`workout` (`id`, `time_of_day`) VALUES (2, 'Noon');
INSERT INTO `sleepdb`.`workout` (`id`, `time_of_day`) VALUES (3, 'Afternoon');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sleepdb`.`sleep_period`
-- -----------------------------------------------------
START TRANSACTION;
USE `sleepdb`;
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (1, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 0, 0, 1, 0, 7, 1, 1);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (2, '2022-05-28 22:05:00', '2022-05-30 06:10:00', 8.10, 0, 1, 1, 0, 7, 2, 2);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (3, '2022-05-27 22:05:00', '2022-05-30 06:10:00', 8.10, 0, 1, 1, 0, 8, 2, 3);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (4, '2022-05-26 22:05:00', '2022-05-30 06:10:00', 8.10, 0, 1, 1, 0, 8, 2, 2);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (5, '2022-05-25 22:05:00', '2022-05-30 06:10:00', 8.10, 0, 1, 1, 0, 8, 3, 2);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (6, '2022-05-24 22:05:00', '2022-05-30 06:10:00', 8.10, 1, 1, 1, 0, 7, 3, 3);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (7, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 1, 0, 1, 0, 7, 4, NULL);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (8, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 1, 0, 1, 0, 6, 4, NULL);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (9, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 1, 0, 1, 0, 4, 4, NULL);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (10, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 0, 0, 1, 0, 4, 6, NULL);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (11, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 0, 1, 1, 0, 4, 6, 2);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (12, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 0, 1, 1, 0, 5, 7, 2);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (13, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 0, 1, 0, 0, 2, 7, 3);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (14, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 1, 1, 0, 0, 3, 1, 1);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (15, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 0, 0, 0, 1, 2, 4, NULL);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (16, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 0, 0, 1, 0, 4, 3, NULL);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (17, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 0, 0, 1, 0, 5, 4, NULL);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (18, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 0, 0, 1, 0, 5, 6, NULL);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (19, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 0, 1, 1, 0, 5, 7, 3);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (20, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 0, 1, 1, 0, 7, 3, 2);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (21, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 0, 1, 0, 0, 7, 4, NULL);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (22, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 0, 0, 0, 0, 5, 4, NULL);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (23, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 1, 0, 0, 0, 4, 1, NULL);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (24, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 0, 0, 1, 0, 3, 2, 2);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (25, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 0, 0, 1, 0, 5, 2, 2);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (26, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 0, 0, 1, 0, 6, 1, 1);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (27, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 0, 0, 1, 0, 7, 3, 2);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (28, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 0, 0, 0, 0, 5, 4, NULL);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (29, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 0, 0, 1, 0, 2, 2, 1);
INSERT INTO `sleepdb`.`sleep_period` (`id`, `start`, `end`, `duration`, `had_alcohol`, `large_dinner`, `excercised`, `took_nap`, `quality`, `evening_activity_id`, `workout_id`) VALUES (30, '2022-05-29 22:05:00', '2022-05-30 06:10:00', 8.10, 0, 0, 1, 0, 7, 2, 2);

COMMIT;

