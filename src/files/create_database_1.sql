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
-- Table `clothing_store`.`customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing_store`.`customers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


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
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing_store`.`item_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing_store`.`item_details` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `made_in` VARCHAR(50) NOT NULL,
  `eco_percent` TINYINT NULL DEFAULT NULL,
  `iron_temperature` TINYINT NULL DEFAULT NULL,
  `items_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_item_details_items_idx` (`items_id` ASC) VISIBLE,
  CONSTRAINT `fk_item_details_items`
    FOREIGN KEY (`items_id`)
    REFERENCES `clothing_store`.`items` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing_store`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing_store`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `customers_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing_store`.`orders_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing_store`.`orders_items` (
  `orders_id` INT NOT NULL,
  `items_id` INT NOT NULL,
  `quantity` TINYINT NOT NULL,
  PRIMARY KEY (`orders_id`, `items_id`),
  INDEX `fk_orders_items_orders1_idx` (`orders_id` ASC) VISIBLE,
  INDEX `fk_orders_items_items1_idx` (`items_id` ASC) VISIBLE,
  CONSTRAINT `fk_orders_items_items1`
    FOREIGN KEY (`items_id`)
    REFERENCES `clothing_store`.`items` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_orders_items_orders1`
    FOREIGN KEY (`orders_id`)
    REFERENCES `clothing_store`.`orders` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing_store`.`stocks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing_store`.`stocks` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `items_id` INT NOT NULL,
  `quantity` INT NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `fk_stocks_items1_idx` (`items_id` ASC) VISIBLE,
  CONSTRAINT `fk_stocks_items1`
    FOREIGN KEY (`items_id`)
    REFERENCES `clothing_store`.`items` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `clothing_store` ;

-- -----------------------------------------------------
-- procedure add_client_to_order
-- -----------------------------------------------------

DROP PROCEDURE IF EXISTS add_client_to_order;
DELIMITER $$
USE `clothing_store`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_client_to_order`(
	IN date_param DATE,
    IN customer_id_param INT

)
BEGIN
	INSERT INTO orders (date, customers_id)
    VALUES (date_param, customer_id_param);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure add_customer
-- -----------------------------------------------------

DROP PROCEDURE IF EXISTS add_customer;
DELIMITER $$
USE `clothing_store`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_customer`(
	IN first_name_param VARCHAR(45),
    IN last_name_param VARCHAR(45),
    IN address_param VARCHAR(500)
)
BEGIN
	INSERT INTO customers(first_name, last_name, address)
    VALUES(first_name_param, last_name_param, address_param);

    SELECT 1;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure add_customer_to_order
-- -----------------------------------------------------

DROP PROCEDURE IF EXISTS add_customer_to_order;
DELIMITER $$
USE `clothing_store`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_customer_to_order`(
    IN customer_id_param INT
)
BEGIN
	INSERT INTO orders (date, customers_id)
    VALUES (curdate(), customer_id_param);
    SELECT 1;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure delete_item_and_item_details
-- -----------------------------------------------------

DROP PROCEDURE IF EXISTS delete_item_and_item_details;
DELIMITER $$
USE `clothing_store`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_item_and_item_details`(
	id_param INT
)
BEGIN
    DELETE FROM items
    WHERE id = id_param;

    DELETE FROM clothing_store.item_details
    WHERE items_id = id_param;

    Select 1;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_customers
-- -----------------------------------------------------

DROP PROCEDURE IF EXISTS get_customers;
DELIMITER $$
USE `clothing_store`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_customers`(
 IN id_param INT,
 IN first_name_param VARCHAR(45),
 IN last_name_param VARCHAR(45),
 IN address_param VARCHAR(500)
)
BEGIN

SELECT *
FROM customers c
WHERE
	(id_param IS NULL OR c.id = id_param)
    AND (first_name_param IS NULL OR LOWER(c.first_name) LIKE (LOWER(CONCAT('%', first_name_param, '%'))))
    AND (last_name_param IS NULL OR LOWER(c.last_name) LIKE (LOWER(CONCAT('%', last_name_param, '%'))))
    AND (address_param IS NULL OR LOWER(c.address) LIKE (LOWER(CONCAT('%', address_param, '%'))));


END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_items
-- -----------------------------------------------------

DROP PROCEDURE IF EXISTS get_items;
DELIMITER $$
USE `clothing_store`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_items`(
	IN p_id INT,
   IN p_name VARCHAR(255),
   IN p_minPrice DECIMAL(5, 2),
   IN p_maxPrice DECIMAL(5, 2),
   IN p_description VARCHAR(500),
   IN p_category VARCHAR(20),
   IN p_gender VARCHAR(6),
   IN p_material VARCHAR(20),
   IN p_color VARCHAR(20)
)
BEGIN
	SELECT * FROM items i
    WHERE

    (p_id IS NULL OR i.id = p_id)
		AND (p_name IS NULL OR LOWER(i.name) LIKE LOWER(CONCAT('%', p_name, '%')))
        AND (p_minPrice IS NULL OR i.price >= p_minPrice)
        AND (p_maxPrice IS NULL OR i.price <= p_maxPrice)
        AND (p_description IS NULL OR LOWER(i.description) LIKE LOWER(CONCAT('%', p_description, '%')))
        AND (p_category IS NULL OR LOWER(i.category) LIKE LOWER(CONCAT('%', p_category, '%')))
        AND (p_gender IS NULL OR LOWER(i.gender) LIKE LOWER(CONCAT('%', p_gender, '%')))
        AND (p_material IS NULL OR LOWER(i.material) LIKE LOWER(CONCAT('%', p_material, '%')))
        AND (p_color IS NULL OR LOWER(i.color) LIKE LOWER(CONCAT('%', p_color, '%')));
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_orders
-- -----------------------------------------------------

DROP PROCEDURE IF EXISTS get_orders;
DELIMITER $$
USE `clothing_store`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_orders`(
	IN order_id_param INT,
    IN customer_id_param INT
)
BEGIN

SELECT o.id,
    o.date,
    o.customers_id,
    oi.items_id,
    i.name,
    oi.quantity,
    oi.quantity * i.price AS total
FROM orders o
JOIN orders_items oi ON o.id = oi.orders_id
JOIN items i ON oi.items_id = i.id
WHERE (order_id_param IS NULL OR o.id = order_id_param)
    AND (customer_id_param IS NULL OR o.customers_id = customer_id_param);

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure update_item
-- -----------------------------------------------------

DROP PROCEDURE IF EXISTS update_item;
DELIMITER $$
USE `clothing_store`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_item`(
	IN p_id INT,
	IN p_name VARCHAR(255),
    IN p_price DECIMAL(5, 2),
    IN p_description VARCHAR(500),
    IN p_category VARCHAR(20),
    IN p_gender VARCHAR(6),
    IN p_material VARCHAR(20),
    IN p_color VARCHAR(20)
)
BEGIN
	UPDATE clothing_store.items i
    SET
		i.name = IFNULL(i.name, p_name),
        i.price = IFNULL(i.price, p_price),
        i.description = IFNULL(i.description, p_description),
        i.category = IFNULL(i.category, p_category),
        i.gender = IFNULL(i.gender, p_gender),
        i.material = IFNULL(i.material, p_material),
        i.color = IFNULL(i.color, p_color)
	WHERE i.id = p_id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure update_item_details
-- -----------------------------------------------------

DROP PROCEDURE IF EXISTS update_item_details;
DELIMITER $$
USE `clothing_store`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_item_details`(
    IN id_param INT,
    IN made_in_param VARCHAR(50),
    IN eco_percent_param TINYINT,
    IN iron_temperature_param TINYINT
)
BEGIN
    -- Check if item_details record exists for the given items_id
    DECLARE details_id INT;

    SELECT id INTO details_id
    FROM item_details
    WHERE items_id = id_param;

    -- If item_details record exists, update the details
    IF details_id IS NOT NULL THEN
        UPDATE item_details
        SET made_in = IFNULL(made_in_param, made_in),
            eco_percent = IFNULL(eco_percent_param, eco_percent),
            iron_temperature = IFNULL(iron_temperature_param, iron_temperature)
        WHERE items_id = id_param;
    ELSE
        -- If item_details record does not exist, insert a new record
        INSERT INTO item_details (made_in, eco_percent, iron_temperature, items_id)
        VALUES (made_in_param, eco_percent_param, iron_temperature_param, id_param);
    END IF;

    SELECT 1;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure update_stock
-- -----------------------------------------------------

DROP PROCEDURE IF EXISTS update_stock;
DELIMITER $$
USE `clothing_store`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_stock`(
	IN itemId_param INT,
    IN quantity_param INT
)
BEGIN
	UPDATE stocks
    SET quantity = quantity_param
    WHERE items_id = itemId_param;

    SELECT 1;
END$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
