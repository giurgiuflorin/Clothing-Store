DROP PROCEDURE IF EXISTS get_items;

DELIMITER $$
CREATE PROCEDURE get_items
(
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
END $$
DELIMITER ;