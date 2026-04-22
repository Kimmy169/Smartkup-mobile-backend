package org.smartkup.smartkup.controller;

import org.smartkup.smartkup.entity.Category;
import org.smartkup.smartkup.repository.CategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryRepository repository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    // This is the exact endpoint Android is trying to call!
    @GetMapping
    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    // Allows you to add categories via API if needed later
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return repository.save(category);
    }
}
