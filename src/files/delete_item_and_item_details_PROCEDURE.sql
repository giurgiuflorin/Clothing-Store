DROP PROCEDURE IF EXISTS delete_item_and_item_details;

DELIMITER $$
CREATE PROCEDURE delete_item_and_item_details
(
	id_param INT
)
BEGIN 
    DELETE FROM items
    WHERE id = id_param;
    
    DELETE FROM clothing_store.item_details
    WHERE items_id = id_param;
    
    Select 1;
END $$
DELIMITER ;