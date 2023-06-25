
DROP SCHEMA IF EXISTS `clothing_store_db`;
CREATE SCHEMA `clothing_store_db`;
USE `clothing_store_db`;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `clothing_store_db`.`item_details` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `made_in` VARCHAR(50) NULL,
  `eco_percent` INT NULL,
  `iron_temperature` INT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=latin1;

CREATE TABLE `clothing_store_db`.`items` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) DEFAULT NULL,
  `price` DOUBLE DEFAULT NULL,
  `description` VARCHAR(500) DEFAULT NULL,
  `size` VARCHAR(3) DEFAULT NULL,
  `category` VARCHAR(50) DEFAULT NULL,
  `gender` VARCHAR(6) DEFAULT NULL,
  `material` VARCHAR(45) DEFAULT NULL,
  `color` VARCHAR(45) DEFAULT NULL,
  `item_details_id` INT DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DETAIL_idx` (`item_details_id`),
  CONSTRAINT `FK_DETAIL` FOREIGN KEY (`item_details_id`) REFERENCES `item_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `clothing_store_db`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `item_id` INT DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_ORDER_ITEM` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `clothing_store_db`.`orders_items` (
  `order_id` INT NOT NULL,
  `item_id` INT NOT NULL,
  PRIMARY KEY (`order_id`, `item_id`),
  CONSTRAINT `FK_ORDER` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FK_ITEM_ORDER` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;