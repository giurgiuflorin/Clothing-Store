package com.fasttrackit.service;

import com.fasttrackit.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findAll();

    @Query(value = "select i from Item i where " +
            "(:name = null or lower(i.name) like lower(concat('$', :name, '$'))) " +
            "and (:minPrice = null or i.price >= :minPrice) " +
            "and (:maxPrice = null or i.price <= :maxPrice) " +
            "and (:description = null or lower(i.description) like lower(concat('%', :description, '%'))) " +
            "and (:category = null or lower(i.category) like lower(concat('%', :category, '%'))) " +
            "and (:gender = null or lower(i.gender) like lower(concat('%', :gender, '%'))) " +
            "and (:material = null or lower(i.material) like lower(concat('%', :material, '%'))) " +
            "and (:color = null or lower(i.color) like lower(concat('%', :color, '%')))")
    List<Item> filterItems(@Param("name") String name,
                           @Param("minPrice") Double minPrice,
                           @Param("maxPrice") Double maxPrice,
                           @Param("description") String description,
                           @Param("category") String category,
                           @Param("gender") String gender,
                           @Param("material") String material,
                           @Param("color") String color);
}
