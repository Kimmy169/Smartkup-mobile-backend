package org.smartkup.smartkup.controller;

import org.smartkup.smartkup.entity.Product;
import org.smartkup.smartkup.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository repository;

    // Constructor Injection (Spring Boot automatically provides the repository)
    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    // GET request to fetch all products
    @GetMapping
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // POST request to add a new product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return repository.save(product);
    }
}
