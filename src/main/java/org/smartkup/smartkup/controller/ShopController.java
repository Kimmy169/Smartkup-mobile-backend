package org.smartkup.smartkup.controller;

import org.smartkup.smartkup.entity.Shop;
import org.smartkup.smartkup.repository.ShopRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public ResponseEntity<List<Shop>> getAllShops() {
        List<Shop> shops = shopRepository.findAll();
        return ResponseEntity.ok(shops);
    }
}