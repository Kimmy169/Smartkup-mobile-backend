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

    /**
     * Adds an item to a list. If the product is new, it creates it.
     * It also updates the global price in the Products table.
     */
    public ShoppingListItem addItemToList(ShoppingListItem item) {
        // Reset ID if it comes as 0 from Android to allow auto-generation
        if (item.getList_item_id() != null && item.getList_item_id() == 0L) {
            item.setList_item_id(null);
        }

        // 1. Resolve the Product (Create if custom name was typed)
        if (item.getProductId() == null || item.getProductId() == 0L) {
            String customName = item.getProductName();
            if (customName == null || customName.trim().isEmpty()) {
                throw new IllegalArgumentException("Product name cannot be empty for new products!");
            }

            Optional<Product> existingProduct = productRepository.findByNameIgnoreCase(customName.trim());

            if (existingProduct.isPresent()) {
                item.setProductId(existingProduct.get().getProductId());
            } else {
                Product newProduct = new Product();
                newProduct.setName(customName.trim());
                newProduct.setDefaultUnit(item.getUnit());
                newProduct.setCategoryId(item.getCategoryId()); // Using ID sent from Android

                // Set initial price if provided
                if (item.getProductPrice() != null) {
                    newProduct.setPrice(item.getProductPrice());
                }

                Product savedProduct = productRepository.save(newProduct);
                item.setProductId(savedProduct.getProductId());
            }
        }

        // 2. Update the Global Product Price
        // This ensures the "current price" in the Products table is always up-to-date
        productRepository.findById(item.getProductId()).ifPresent(product -> {
            if (item.getProductPrice() != null) {
                product.setPrice(item.getProductPrice());
                productRepository.save(product);
            }
        });

        // 3. Clean up shopId (0 means "Anywhere", maps to null in DB)
        if (item.getShopId() != null && item.getShopId() == 0L) {
            item.setShopId(null);
        }

        return itemRepository.save(item);
    }

    /**
     * Fetches the full list and attaches the latest product names and prices
     * from the Products table to the transient fields.
     */
    public ShoppingListResponseDTO getFullShoppingList(Long listId) {
        ShoppingList list = listRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("Shopping List not found"));

        List<ShoppingListItem> items = itemRepository.findByListId(listId);

        // Map the product information (Name and Price) to each item
        for (ShoppingListItem item : items) {
            productRepository.findById(item.getProductId()).ifPresent(product -> {
                item.setProductName(product.getName());
                item.setProductPrice(product.getPrice()); // Pull price from Products table
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

    public List<ShoppingList> getAllLists() {
        return listRepository.findAll();
    }

    public ShoppingList createList(ShoppingList newList) {
        if (newList.getList_id() != null && newList.getList_id() == 0L) {
            newList.setList_id(null);
        }
        if (newList.getUserId() == null) {
            newList.setUserId(1L); // Default user
        }
        newList.setStatus("active");
        return listRepository.save(newList);
    }
}