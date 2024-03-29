package com.clothingstore.service;

import com.clothingstore.entity.Item;
import com.clothingstore.entity.enumproperties.Category;
import com.clothingstore.entity.enumproperties.Color;
import com.clothingstore.entity.enumproperties.Gender;
import com.clothingstore.entity.enumproperties.Material;
import com.clothingstore.exception.IncorrectData;
import com.clothingstore.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    public List<Item> getAllItems(Integer id, String name, Double minPrice, Double maxPrice,
                                  String description, String category, String gender,
                                  String material, String color) {
        return itemRepository.filterItems(id, name, minPrice, maxPrice, description, category, gender,
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
            item.setCategory(Category.valueOf(category.toUpperCase()));
        }
        if (gender != null) {
            item.setGender(Gender.valueOf(gender.toUpperCase()));
        }
        if (material != null) {
            item.setMaterial(Material.valueOf(material.toUpperCase()));
        }
        if (color != null) {
            item.setColor(Color.valueOf(color.toUpperCase()));
        }

        return itemRepository.save(item);

    }

    @Transactional
    public Item addItem(String name, double price, String description, String category,
                        String gender, String material, String color) {

        Category category1 = Category.valueOf(category.toUpperCase());
        Gender gender1 = Gender.valueOf(gender.toUpperCase());
        Material material1 = Material.valueOf(material.toUpperCase());
        Color color1 = Color.valueOf(color.toUpperCase());

        Item item = new Item(name, price, description, category1, gender1, material1, color1);
        return itemRepository.save(item);
    }

    @Transactional
    public void deleteItemById(int id) {

        if (!itemRepository.existsById(id)) {
            throw new NotFoundException("Item with id [" + id + "] does not exist.");
        }

        Optional<Item> item = itemRepository.findById(id);

        itemRepository.deleteById(id);
    }

    @Transactional
    public void addItemDetails(Integer id, String madeIn, Integer ecoPercent, Integer ironTemp) {

        if (!itemRepository.existsById(id)) {
            throw new NotFoundException("Item with id [" + id + "] does not exist!");
        }

        if (ecoPercent != null && (ecoPercent < 0 || ecoPercent > 100)) {
            throw new IncorrectData("EcoPercent must have a value between 0 and 100!");
        }

        if (ironTemp != null && (ironTemp < 0 || ironTemp > 140)) {
            throw new IncorrectData("IronPercent must have a value between 0 and 140!");
        }


        itemRepository.updateItemDetails(id, madeIn, ecoPercent, ironTemp);
    }

    @Transactional
    public void updateStockByItemId(int itemId, int quantity) {

        if (quantity < 0 || quantity > 50) {
            throw new IncorrectData("Stock can not be negative or bigger than 50!");
        }

        if (!itemRepository.existsById(itemId)) {
            throw new NotFoundException("Item with id [" + itemId + "] does not exist!");
        }

        if (!itemRepository.existsById(itemId)) {
            throw new NotFoundException("Item with id [" + itemId + "] does not exist!");
        }

        itemRepository.updateStockByItemId(itemId, quantity);
    }




    @Transactional
    public void addCustomer(String firstName, String lastName, String address) {

        itemRepository.addCustomer(firstName, lastName, address);
    }

    private Item getItemById(int itemId) {
        return itemRepository.getItemById(itemId);
    }

    @Transactional
    public void addItemToOrder(int orderId, int itemId, int quantity) {

        if (getItemById(itemId) == null) {
            throw new NotFoundException("Item with id [" + itemId + "] does not exist!");
        }

        if (!orderRepository.existsById(orderId)) {
            throw new NotFoundException("Order with id [" + orderId + "] does not exist!");
        }
        Item item = itemRepository.filterItems(itemId, null, null, null, null, null, null,
                null, null).get(0);
        int quantityInStock = item.getStock().getQuantity();

        if (quantity > quantityInStock) {
            throw new IncorrectData("Not enough stock for item [" + itemId + "]. Available stock: " + quantityInStock);
        }
        itemRepository.addItemToOrder(orderId, itemId, quantity);
    }

    public List<Item> getItemsWithStockLowerThan(int lowerThan) {
       return itemRepository.getItemsWithStockLowerThan(lowerThan);
    }

    public void removeItemFromOrder(int orderId, int itemId, int quantity) {

        itemRepository.removeItemFromOrder(orderId, itemId, quantity);
    }
}
