package com.fasttrackit.service;

import com.fasttrackit.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

//    @Query(value = "SELECT * FROM clothing_store_db.items", nativeQuery = true)
    List<Item> findAll();
}
