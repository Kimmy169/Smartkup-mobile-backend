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

    public ShoppingListItem addItemToList(ShoppingListItem item) {

        if (item.getList_item_id() != null && item.getList_item_id() == 0L) {
            item.setList_item_id(null);
        }

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
                newProduct.setCategoryId(item.getCategoryId());

                if (item.getProductPrice() != null) {
                    newProduct.setPrice(item.getProductPrice());
                }

                Product savedProduct = productRepository.save(newProduct);
                item.setProductId(savedProduct.getProductId());
            }
        }

        productRepository.findById(item.getProductId()).ifPresent(product -> {
            if (item.getProductPrice() != null) {
                product.setPrice(item.getProductPrice());
                productRepository.save(product);
            }
        });

        if (item.getShopId() != null && item.getShopId() == 0L) {
            item.setShopId(null);
        }

        return itemRepository.save(item);
    }

    public ShoppingListResponseDTO getFullShoppingList(Long listId) {
        ShoppingList list = listRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("Shopping List not found"));

        List<ShoppingListItem> items = itemRepository.findByListId(listId);

        for (ShoppingListItem item : items) {
            productRepository.findById(item.getProductId()).ifPresent(product -> {
                item.setProductName(product.getName());
                item.setProductPrice(product.getPrice());
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
            newList.setUserId(1L);
        }
        newList.setStatus("active");
        return listRepository.save(newList);
    }
}