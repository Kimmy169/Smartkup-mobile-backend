package org.smartkup.smartkup.controller;

import org.smartkup.smartkup.entity.Shop;
import org.smartkup.smartkup.repository.ShopRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping; // CHECK THIS
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shops")
public class ShopController {

    private final ShopRepository shopRepository;

    public ShopController(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    // THIS IS THE LINE CAUSING THE 405 ERROR.
    // It MUST be @GetMapping, not @PostMapping or anything else!
    @GetMapping
    public ResponseEntity<List<Shop>> getAllShops() {
        List<Shop> shops = shopRepository.findAll();
        return ResponseEntity.ok(shops);
    }
}