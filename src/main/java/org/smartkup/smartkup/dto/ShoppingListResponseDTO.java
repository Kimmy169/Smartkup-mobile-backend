package org.smartkup.smartkup.dto;

import lombok.Data;
import org.smartkup.smartkup.entity.ShoppingList;
import org.smartkup.smartkup.entity.ShoppingListItem;
import java.util.List;

@Data
public class ShoppingListResponseDTO {
    private ShoppingList listDetails;
    private List<ShoppingListItem> items;

    public ShoppingListResponseDTO(ShoppingList listDetails, List<ShoppingListItem> items) {
        this.listDetails = listDetails;
        this.items = items;
    }
}