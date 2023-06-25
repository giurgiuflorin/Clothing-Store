package com.fasttrackit.service;

import com.fasttrackit.entity.Item;
import com.fasttrackit.entity.ItemDetails;
import com.fasttrackit.entity.enumproperties.*;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {

    private ItemRepository itemRepository;

    @PostConstruct
    private void populateDB() {
        Item item1 = new Item("Summer Dress", 15.00, "Summer cotton dress with elastic waistband",
                Size.M, Category.DRESS, Gender.FEMALE, Material.COTTON, Color.BLACK);
        Item item2 = new Item("Long Denim Skirt", 11.75, "Long denim skirt with embroidery", Size.L, Category.SKIRT,
                Gender.FEMALE, Material.DENIM, Color.BLACK);


        ItemDetails itemDetails1 = new ItemDetails("Vietnam", 70, 60);
        ItemDetails itemDetails2 = new ItemDetails("China", 65, 55);


        item1.setItemDetails(itemDetails1);
        itemRepository.save(item1);
        item2.setItemDetails(itemDetails2);
        itemRepository.save(item2);

    }

    public List<Item> getAllItems() {

        return itemRepository.findAll();
    }
}
