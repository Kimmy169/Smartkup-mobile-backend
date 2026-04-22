package org.smartkup.smartkup.service;

import org.smartkup.smartkup.dto.ShoppingListResponseDTO;
import org.smartkup.smartkup.entity.Product;
import org.smartkup.smartkup.entity.ShoppingList;
import org.smartkup.smartkup.entity.ShoppingListItem;
import org.smartkup.smartkup.repository.ShoppingListRepository;
import org.smartkup.smartkup.repository.ShoppingListItemRepository;
import org.smartkup.smartkup.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListService {

    private final ShoppingListRepository listRepository;
    private final ShoppingListItemRepository itemRepository;
    private final ProductRepository productRepository;

    public ShoppingListService(ShoppingListRepository listRepository,
                               ShoppingListItemRepository itemRepository,
                               ProductRepository productRepository) {
        this.listRepository = listRepository;
        this.itemRepository = itemRepository;
        this.productRepository = productRepository;
    }

    // --- THE UPGRADED ADD METHOD ---
    public ShoppingListItem addItemToList(ShoppingListItem item) {

        if (item.getList_item_id() != null && item.getList_item_id() == 0L) {
            item.setList_item_id(null);
        }

        // 1. Did the user type a custom product name instead of picking from the list?
        if (item.getProductId() == null || item.getProductId() == 0L) {

            String customName = item.getProductName();
            if (customName == null || customName.trim().isEmpty()) {
                throw new IllegalArgumentException("Product name cannot be empty for new products!");
            }

            // 2. Check if it actually exists in the DB already (maybe they typed it perfectly)
            Optional<Product> existingProduct = productRepository.findByNameIgnoreCase(customName.trim());

            if (existingProduct.isPresent()) {
                // It exists! Just use the real ID.
                item.setProductId(existingProduct.get().getProductId());
            } else {
                // 3. It's truly new. Create it in the Products table!
                Product newProduct = new Product();
                newProduct.setName(customName.trim());
                newProduct.setDefaultUnit(item.getUnit());
                // We default to a generic category (e.g., Category 1) if we don't know it yet
                newProduct.setCategoryId(1L);

                // Save it to MySQL to generate a real product_id
                Product savedProduct = productRepository.save(newProduct);

                // Assign that brand new ID to our shopping list item
                item.setProductId(savedProduct.getProductId());
            }
        }

        // 4. Clean up the shopId (if they selected "Anywhere" / 0, set to null so DB is happy)
        if (item.getShopId() != null && item.getShopId() == 0L) {
            item.setShopId(null);
        }

        // 5. Finally, save the row to the ShoppingListItems table!
        return itemRepository.save(item);
    }

    // --- YOUR EXISTING FETCH METHOD ---
    public ShoppingListResponseDTO getFullShoppingList(Long listId) {
        ShoppingList list = listRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("Shopping List not found"));

        List<ShoppingListItem> items = itemRepository.findByListId(listId);

        for (ShoppingListItem item : items) {
            productRepository.findById(item.getProductId()).ifPresent(product -> {
                item.setProductName(product.getName());
            });
        }

        return new ShoppingListResponseDTO(list, items);
    }
    public void toggleItemStatus(Long itemId, Boolean isPurchased) {
        ShoppingListItem item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setPurchased(isPurchased);
        itemRepository.save(item);
    }
}