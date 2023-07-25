DROP PROCEDURE IF EXISTS update_item;

DELIMITER $$
CREATE PROCEDURE update_item
(
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
END $$
DELIMITER ;