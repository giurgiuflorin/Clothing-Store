package com.clothingstore.service;

import com.clothingstore.entity.Item;
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

    @Query(value = "call clothing_store.get_item_by_id(:itemId)", nativeQuery = true)
    Item getItemById(@Param("itemId") int itemId);

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


    @Query(value = "call clothing_store.delete_item_and_item_details (:itemId)", nativeQuery = true)
    void deleteById(@Param("itemId") int itemId);


    @Query(value = "call clothing_store.update_item_details (:id, :madeIn, :ecoPercent, :ironTemperature)", nativeQuery = true)
    void updateItemDetails(@Param("id") Integer itemId,
                           @Param("madeIn") String madeIn,
                           @Param("ecoPercent") Integer ecoPercent,
                           @Param("ironTemperature") Integer ironTemp);


    @Query(value = "call update_stock(:itemId, :quantity)", nativeQuery = true)
    void updateStockByItemId(@Param("itemId") int itemId,
                             @Param("quantity") int quantity);



    @Query(value = "call clothing_store.add_customer(:firstName, :lastName, :address)", nativeQuery = true)
    void addCustomer(@Param("firstName") String firstName,
                     @Param("lastName") String lastName,
                     @Param("address") String address);

    @Query(value = "call clothing_store.add_item_to_order(:orderId, :itemId, :quantity)", nativeQuery = true)
    void addItemToOrder(@Param("orderId") int orderId,
                        @Param("itemId") int itemId,
                        @Param("quantity") int quantity);

    @Query(value = "call clothing_store.get_items_with_stock_lower_than(:lowerThan)", nativeQuery = true)
    List<Item> getItemsWithStockLowerThan(@Param("lowerThan") int lowerThan);

    @Query(value = "call clothing_store.remove_item_from_order(:orderId, :itemId, :quantity)", nativeQuery = true)
    void removeItemFromOrder(@Param("orderId") int orderId,
                             @Param("itemId") int itemId,
                             @Param("quantity") int quantity);
}
