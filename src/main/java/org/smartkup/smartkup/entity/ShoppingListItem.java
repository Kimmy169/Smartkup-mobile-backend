package org.smartkup.smartkup.entity;

import com.fasterxml.jackson.annotation.JsonProperty; // 1. ADD THIS IMPORT
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "ShoppingListItems")
@Data
public class ShoppingListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long list_item_id;

    @Column(name = "list_id")
    private Long listId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "shop_id")
    private Long shopId;

    private BigDecimal quantity;
    private String unit;

    // 2. ADD THIS ANNOTATION
    @JsonProperty("isPurchased")
    @Column(name = "is_purchased")
    private Boolean purchased;

    @Transient
    private String productName;
}