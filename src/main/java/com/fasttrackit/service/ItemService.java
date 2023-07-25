package com.fasttrackit.service;

import com.fasttrackit.entity.Item;
import com.fasttrackit.entity.enumproperties.Category;
import com.fasttrackit.entity.enumproperties.Color;
import com.fasttrackit.entity.enumproperties.Gender;
import com.fasttrackit.entity.enumproperties.Material;
import com.fasttrackit.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> getAllItems(String name, Double minPrice, Double maxPrice,
                                  String description, String category, String gender,
                                  String material, String color) {
        return itemRepository.filterItems(name, minPrice, maxPrice, description, category, gender,
                material, color);
    }


    @Transactional
    public Item updateItemById(int id, String name, Double price, String description,
                               String category, String gender, String material, String color) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item with id [" + id + "] does not exist!"));

        if (name != null) {
            item.setName(name);
        }
        if (price != null) {
            item.setPrice(price);
        }
        if (description != null) {
            item.setDescription(description);
        }
        if (category != null) {
            item.setCategory(Category.valueOf(category));
        }
        if (gender != null) {
            item.setGender(Gender.valueOf(gender));
        }
        if (material != null) {
            item.setMaterial(Material.valueOf(material));
        }
        if (color != null) {
            item.setColor(Color.valueOf(color));
        }

        return itemRepository.save(item);

    }


    // this method will be added to the main search method
    public Optional<Item> getItemById(int id) {
        return itemRepository.findById(id);
    }

}
