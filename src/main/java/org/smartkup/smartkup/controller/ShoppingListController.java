package org.smartkup.smartkup.controller;

import org.smartkup.smartkup.dto.ShoppingListResponseDTO;
import org.smartkup.smartkup.entity.ShoppingList;
import org.smartkup.smartkup.entity.ShoppingListItem;
import org.smartkup.smartkup.service.ShoppingListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shopping-lists")
public class ShoppingListController {

    private final ShoppingListService service;

    public ShoppingListController(ShoppingListService service) {
        this.service = service;
    }

    @GetMapping("/{listId}")
    public ResponseEntity<ShoppingListResponseDTO> getFullList(@PathVariable Long listId) {
        try {
            ShoppingListResponseDTO response = service.getFullShoppingList(listId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/items")
    public ResponseEntity<ShoppingListItem> addItem(@RequestBody ShoppingListItem item) {
        try {
            ShoppingListItem savedItem = service.addItemToList(item);
            return ResponseEntity.ok(savedItem);
        } catch (IllegalArgumentException e) {
            System.out.println("--- BAD REQUEST ERROR ---");
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            System.out.println("--- DATABASE SAVE ERROR ---");
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/items/{itemId}/toggle")
    public ResponseEntity<Void> toggleItemStatus(@PathVariable Long itemId, @RequestParam Boolean isPurchased) {
        service.toggleItemStatus(itemId, isPurchased);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ShoppingList>> getAllLists() {
        return ResponseEntity.ok(service.getAllLists());
    }

    @PostMapping
    public ResponseEntity<ShoppingList> createList(@RequestBody ShoppingList list) {
        return ResponseEntity.ok(service.createList(list));
    }
}