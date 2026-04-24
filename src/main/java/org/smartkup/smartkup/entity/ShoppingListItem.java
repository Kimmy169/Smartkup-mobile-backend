package org.smartkup.smartkup.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("isPurchased")
    @Column(name = "is_purchased")
    private Boolean purchased;

    @Transient
    private String productName;

    @Transient
    @com.fasterxml.jackson.annotation.JsonProperty("categoryId")
    private Long categoryId;

    @Transient
    private java.math.BigDecimal productPrice;
}