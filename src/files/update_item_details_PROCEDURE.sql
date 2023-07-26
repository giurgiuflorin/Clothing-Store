
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
        SET made_in = IFNULL(made_in, made_in_param),
            eco_percent = IFNULL(eco_percent, eco_percent_param),
            iron_temperature = IFNULL(iron_temperature, iron_temperature_param)
        WHERE items_id = id_param;
    ELSE
        -- If item_details record does not exist, insert a new record
        INSERT INTO item_details (made_in, eco_percent, iron_temperature, items_id)
        VALUES (made_in_param, eco_percent_param, iron_temperature_param, id_param);
    END IF;

    SELECT 1;

END$$
DELIMITER ;
