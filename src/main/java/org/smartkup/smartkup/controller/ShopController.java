package org.smartkup.smartkup.controller;

import org.smartkup.smartkup.entity.Shop;
import org.smartkup.smartkup.repository.ShopRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shops")
public class ShopController {

    private final ShopRepository repository;

    public ShopController(ShopRepository repository) {
        this.repository = repository;
    }

    // Get all custom shops for a specific user
    @GetMapping("/user/{userId}")
    public List<Shop> getUserShops(@PathVariable Long userId) {
        return repository.findByUserId(userId);
    }

    // Add a new shop
    @PostMapping
    public Shop createShop(@RequestBody Shop shop) {
        return repository.save(shop);
    }
}