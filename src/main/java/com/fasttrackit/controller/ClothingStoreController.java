package com.fasttrackit.controller;

import com.fasttrackit.entity.Item;
import com.fasttrackit.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/items")
public class ClothingStoreController {

    private final ItemService itemService;

    @GetMapping
    public List<Item> getItems(@RequestParam(required = false) Integer id,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) Double minPrice,
                               @RequestParam(required = false) Double maxPrice,
                               @RequestParam(required = false) String description,
                               @RequestParam(required = false) String category,
                               @RequestParam(required = false) String gender,
                               @RequestParam(required = false) String material,
                               @RequestParam(required = false) String color) {

        return itemService.getAllItems(id, name, minPrice, maxPrice, description, category, gender, material, color);
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

    @PutMapping("/updateItemDetails")
    public void updateItemDetails(Integer id,
                                         @RequestParam String madeIn,
                                         @RequestParam(required = false) Integer ecoPercent,
                                         @RequestParam(required = false) Integer ironTemp) {
        itemService.addItemDetails(id, madeIn, ecoPercent, ironTemp);
    }

    @PostMapping("/addItem")
    public Item addItem(@RequestParam String name,
                        @RequestParam double price,
                        @RequestParam String description,
                        @RequestParam String category,
                        @RequestParam String gender,
                        @RequestParam String material,
                        @RequestParam String color) {
        return itemService.addItem(name, price, description, category, gender, material, color);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable int id) {

        itemService.deleteItemById(id);
    }

}
