package com.fasttrackit.service;

import com.fasttrackit.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query(value = "call clothing_store.get_items(:id, :name, :minPrice, :maxPrice, :description," +
            ":category, :gender, :material, :color)", nativeQuery = true)
    List<Item> filterItems(@Param("id") Integer id,
                           @Param("name") String name,
                           @Param("minPrice") Double minPrice,
                           @Param("maxPrice") Double maxPrice,
                           @Param("description") String description,
                           @Param("category") String category,
                           @Param("gender") String gender,
                           @Param("material") String material,
                           @Param("color") String color);

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
