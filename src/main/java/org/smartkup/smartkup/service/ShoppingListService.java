package org.smartkup.smartkup.service;

import org.smartkup.smartkup.dto.ShoppingListResponseDTO;
import org.smartkup.smartkup.entity.ShoppingList;
import org.smartkup.smartkup.entity.ShoppingListItem;
import org.smartkup.smartkup.repository.ShoppingListRepository;
import org.smartkup.smartkup.repository.ShoppingListItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingListService {

    private final ShoppingListRepository listRepository;
    private final ShoppingListItemRepository itemRepository;

    public ShoppingListService(ShoppingListRepository listRepository, ShoppingListItemRepository itemRepository) {
        this.listRepository = listRepository;
        this.itemRepository = itemRepository;
    }

    // Creates a new empty list
    public ShoppingList createList(ShoppingList newList) {
        return listRepository.save(newList);
    }

    // Adds an item to an existing list
    public ShoppingListItem addItemToList(ShoppingListItem item) {
        return itemRepository.save(item);
    }

    // Fetches the List AND all its items using the DTO
    public ShoppingListResponseDTO getFullShoppingList(Long listId) {
        ShoppingList list = listRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("List not found"));

        List<ShoppingListItem> items = itemRepository.findByListId(listId);

        return new ShoppingListResponseDTO(list, items);
    }
}