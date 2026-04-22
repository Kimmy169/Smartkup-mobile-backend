package org.smartkup.smartkup.controller;

import org.smartkup.smartkup.dto.ShoppingListResponseDTO;
import org.smartkup.smartkup.entity.ShoppingList;
import org.smartkup.smartkup.entity.ShoppingListItem;
import org.smartkup.smartkup.service.ShoppingListService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shopping-lists")
public class ShoppingListController {

    private final ShoppingListService service;

    public ShoppingListController(ShoppingListService service) {
        this.service = service;
    }

    // Create a new list
    @PostMapping
    public ShoppingList createList(@RequestBody ShoppingList list) {
        return service.createList(list);
    }

    // Add an item to a list
    @PostMapping("/items")
    public ShoppingListItem addItem(@RequestBody ShoppingListItem item) {
        return service.addItemToList(item);
    }

    // Get the full list with all items
    @GetMapping("/{listId}")
    public ShoppingListResponseDTO getFullList(@PathVariable Long listId) {
        return service.getFullShoppingList(listId);
    }
}