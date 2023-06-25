package com.fasttrackit.controller;

import com.fasttrackit.entity.Item;
import com.fasttrackit.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("items")
public class ItemController {

    private ItemService itemService;

    @GetMapping
    public List<Item> getAll() {
       return itemService.getAllItems();
    }
}
