package org.smartkup.smartkup.controller;

import org.smartkup.smartkup.entity.Category;
import org.smartkup.smartkup.entity.PantryItem;
import org.smartkup.smartkup.repository.CategoryRepository;
import org.smartkup.smartkup.repository.PantryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private PantryItemRepository pantryItemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryId}/pantry-items")
    public ResponseEntity<List<PantryItem>> getPantryItemsByCategory(@PathVariable Long categoryId) {

        // Update this line to call the newly named method
        List<PantryItem> items = pantryItemRepository.findByProduct_CategoryId(categoryId);

        if (items.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(items);
    }


}