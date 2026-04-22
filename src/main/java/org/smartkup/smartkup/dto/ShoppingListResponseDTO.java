package org.smartkup.smartkup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.smartkup.smartkup.entity.ShoppingList;
import org.smartkup.smartkup.entity.ShoppingListItem;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingListResponseDTO {
    private ShoppingList listDetails;
    private List<ShoppingListItem> items;
}