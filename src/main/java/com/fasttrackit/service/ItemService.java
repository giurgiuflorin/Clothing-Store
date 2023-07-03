package com.fasttrackit.service;

import com.fasttrackit.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
