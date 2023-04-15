-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema taskmanager
-- -----------------------------------------------------

DROP SCHEMA IF EXISTS `taskmanager`;

-- -----------------------------------------------------
-- Schema taskmanager
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `taskmanager` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `taskmanager` ;

-- -----------------------------------------------------
-- Table `taskmanager`.`employees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taskmanager`.`employees` (
  `e_id` INT NOT NULL AUTO_INCREMENT,
  `e_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`e_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `taskmanager`.`tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taskmanager`.`tasks` (
  `t_id` INT NOT NULL AUTO_INCREMENT,
  `t_completion_date` DATETIME(6) NULL DEFAULT NULL,
  `t_end_date` DATETIME(6) NULL DEFAULT NULL,
  `t_is_completed` BIT(1) NULL DEFAULT NULL,
  `t_name` VARCHAR(255) NULL DEFAULT NULL,
  `t_start_date` DATETIME(6) NULL DEFAULT NULL,
  `t_employee` INT NOT NULL,
  PRIMARY KEY (`t_id`),
  INDEX `FK9a2cygj4d7n6akyrcyvoevlfk` (`t_employee` ASC) VISIBLE,
  CONSTRAINT `FK9a2cygj4d7n6akyrcyvoevlfk`
    FOREIGN KEY (`t_employee`)
    REFERENCES `taskmanager`.`employees` (`e_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
