package com.fasttrackit.service;

import com.fasttrackit.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

//    @Query(value = "select i from Item i where " +
//            "(:name = null or lower(i.name) like lower(concat('%', :name, '%'))) " +
//            "and (:minPrice = null or i.price >= :minPrice) " +
//            "and (:maxPrice = null or i.price <= :maxPrice) " +
//            "and (:description = null or lower(i.description) like lower(concat('%', :description, '%'))) " +
//            "and (:category = null or lower(i.category) like lower(concat('%', :category, '%'))) " +
//            "and (:gender = null or lower(i.gender) like lower(concat('%', :gender, '%'))) " +
//            "and (:material = null or lower(i.material) like lower(concat('%', :material, '%'))) " +
//            "and (:color = null or lower(i.color) like lower(concat('%', :color, '%')))")
    @Query(value = "call clothing_store.get_items(:name, :minPrice, :maxPrice, :description," +
            ":category, :gender, :material, :color)", nativeQuery = true)
    List<Item> filterItems(@Param("name") String name,
                           @Param("minPrice") Double minPrice,
                           @Param("maxPrice") Double maxPrice,
                           @Param("description") String description,
                           @Param("category") String category,
                           @Param("gender") String gender,
                           @Param("material") String material,
                           @Param("color") String color);
//
//    @Query(value =
//            "UPDATE `clothing_store`.`items` SET " +
//                    "`name` = CASE WHEN :name IS NOT NULL THEN :name ELSE `name` END, " +
//                    "`price` = CASE WHEN :price IS NOT NULL THEN :price ELSE `price` END, " +
//                    "`description` = CASE WHEN :description IS NOT NULL THEN :description ELSE `description` END, " +
//                    "`category` = CASE WHEN :category IS NOT NULL THEN :category ELSE `category` END, " +
//                    "`gender` = CASE WHEN :gender IS NOT NULL THEN :gender ELSE `gender` END, " +
//                    "`material` = CASE WHEN :material IS NOT NULL THEN :material ELSE `material` END, " +
//                    "`color` = CASE WHEN :color IS NOT NULL THEN :color ELSE `color` END " +
//                    "WHERE `id` = :id", nativeQuery = true)

    @Query(value = "call clothing_store.update_item(:id, :name, :price, :description," +
            " :category, :gender, :material, :color)", nativeQuery = true)
    Item updateItemById(@Param("id") int id,
                        @Param("name") String name,
                        @Param("price") Double price,
                        @Param("description") String description,
                        @Param("category") String category,
                        @Param("gender") String gender,
                        @Param("material") String material,
                        @Param("color") String color);


    // this method will be added to the main search method
    Item getItemById(int id);
}
