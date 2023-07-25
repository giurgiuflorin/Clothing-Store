package com.fasttrackit.controller;

import com.fasttrackit.entity.Item;
import com.fasttrackit.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PutMapping("/updateItem")
    public Item updateItemById(int id,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) Double price,
                               @RequestParam(required = false) String description,
                               @RequestParam(required = false) String category,
                               @RequestParam(required = false) String gender,
                               @RequestParam(required = false) String material,
                               @RequestParam(required = false) String color) {

        return itemService.updateItemById(id, name, price, description, category, gender, material, color);
    }


    // needs to be integrated in the main search method
    @GetMapping("/byid")
    public Optional<Item> getItemById(@RequestParam int id) {
        return itemService.getItemById(id);
    }
}
