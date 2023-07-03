-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema clothing_store
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema clothing_store
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `clothing_store` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `clothing_store` ;

-- -----------------------------------------------------
-- Table `clothing_store`.`items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing_store`.`items` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `price` DECIMAL(5,2) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  `category` VARCHAR(20) NOT NULL,
  `gender` VARCHAR(6) NOT NULL,
  `material` VARCHAR(20) NOT NULL,
  `color` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `clothing_store`.`customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing_store`.`customers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `clothing_store`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing_store`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `customers_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_orders_customers_idx` (`customers_id` ASC) VISIBLE,
  CONSTRAINT `fk_orders_customers`
    FOREIGN KEY (`customers_id`)
    REFERENCES `clothing_store`.`customers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `clothing_store`.`orders_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing_store`.`orders_items` (
  `orders_id` INT NOT NULL,
  `items_id` INT NOT NULL,
  INDEX `fk_orders_items_orders1_idx` (`orders_id` ASC) VISIBLE,
  PRIMARY KEY (`items_id`),
  CONSTRAINT `fk_orders_items_orders1`
    FOREIGN KEY (`orders_id`)
    REFERENCES `clothing_store`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_items_items1`
    FOREIGN KEY (`items_id`)
    REFERENCES `clothing_store`.`items` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `clothing_store`.`item_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing_store`.`item_details` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `made_in` VARCHAR(50) NOT NULL,
  `eco_percent` TINYINT(3) NULL DEFAULT NULL,
  `iron_temperature` TINYINT(3) NULL DEFAULT NULL,
  `items_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_item_details_items1_idx` (`items_id` ASC) VISIBLE,
  CONSTRAINT `fk_item_details_items1`
    FOREIGN KEY (`items_id`)
    REFERENCES `clothing_store`.`items` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
