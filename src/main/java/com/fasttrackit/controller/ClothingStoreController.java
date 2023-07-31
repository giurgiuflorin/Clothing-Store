package com.fasttrackit.controller;

import com.fasttrackit.entity.Customer;
import com.fasttrackit.entity.Item;
import com.fasttrackit.entity.Order;
import com.fasttrackit.exception.IncorrectData;
import com.fasttrackit.exception.NotFoundException;
import com.fasttrackit.service.CustomerService;
import com.fasttrackit.service.ItemService;
import com.fasttrackit.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/items")
public class ClothingStoreController {

    private final ItemService itemService;
    private final CustomerService customerService;

    private final OrderService orderService;

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

    @DeleteMapping("/deleteItemById")
    public void deleteItem(@RequestParam int id) {

        itemService.deleteItemById(id);
    }

    @PutMapping("/updateItemDetails")
    public void updateItemDetails(Integer id,
                                  @RequestParam String madeIn,
                                  @RequestParam(required = false) Integer ecoPercent,
                                  @RequestParam(required = false) Integer ironTemp) {
        itemService.addItemDetails(id, madeIn, ecoPercent, ironTemp);
    }

    @PutMapping("/updateStock")
    public void updateStockByItemId(@RequestParam int itemId,
                                    @RequestParam int quantity) {
        itemService.updateStockByItemId(itemId, quantity);
    }

    @GetMapping("/stocksLowerThan")
    public List<Item> getItemsWithStockLowerThan(@RequestParam int lowerThan) {
        return itemService.getItemsWithStockLowerThan(lowerThan);
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers(@RequestParam(required = false) Integer id,
                                       @RequestParam(required = false) String firstName,
                                       @RequestParam(required = false) String lastName,
                                       @RequestParam(required = false) String address) {
        return customerService.getCustomers(id, firstName, lastName, address);
    }

    @PostMapping("/addCustomer")
    public void addCustomer(@RequestParam String firstName,
                            @RequestParam String lastName,
                            @RequestParam String address) {
        itemService.addCustomer(firstName, lastName, address);
    }

    @PostMapping("/addCustomerToOrder")
    public void addCustomerToOrder(@RequestParam int customerId) {
        itemService.addCustomerToOrder(customerId);
    }

    @GetMapping("/orders")
    public List<Order> getOrders(@RequestParam(required = false) Integer orderId,
                                 @RequestParam(required = false) Integer customerId) {
         return orderService.getOrders(orderId, customerId);
    }

    @PostMapping("/orders/addItemToOrder")
    public void addItemToOrder(@RequestParam int orderId,
                               @RequestParam int itemId,
                               @RequestParam int quantity) {
        itemService.addItemToOrder(orderId, itemId, quantity);
    }

    @ExceptionHandler(IncorrectData.class)
    public ResponseEntity<String> handleIncorrectDataException(IncorrectData ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }



    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleIncorrectDataException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
