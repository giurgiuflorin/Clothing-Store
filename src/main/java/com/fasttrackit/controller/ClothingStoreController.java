package com.fasttrackit.controller;

import com.fasttrackit.entity.Item;
import com.fasttrackit.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/items")
public class ClothingStoreController {

    private final ItemService itemService;

    @GetMapping
    public List<Item> getItems(@RequestParam(required = false) String name,
                               @RequestParam(required = false) Double minPrice,
                               @RequestParam(required = false) Double maxPrice,
                               @RequestParam(required = false) String description,
                               @RequestParam(required = false) String category,
                               @RequestParam(required = false) String gender,
                               @RequestParam(required = false) String material,
                               @RequestParam(required = false) String color) {

        return itemService.getAllItems(name, minPrice, maxPrice, description, category, gender, material, color);
    }
}
