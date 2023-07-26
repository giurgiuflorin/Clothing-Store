DROP PROCEDURE IF EXISTS update_item_details;

DELIMITER $$
CREATE PROCEDURE update_item_details
(
	id_param INT,
    made_in_param VARCHAR(50),
    eco_percent_param TINYINT,
    iron_temperature_param TINYINT

)
BEGIN
    UPDATE item_details
    SET made_in = IFNULL(made_in, made_in_param),
		eco_percent = IFNULL(eco_percent, eco_percent_param),
        iron_temperature = IFNULL(iron_temperature, iron_temperature_param)
	WHERE items_id = id_param;

    SELECT 1;

END $$
DELIMITER ;