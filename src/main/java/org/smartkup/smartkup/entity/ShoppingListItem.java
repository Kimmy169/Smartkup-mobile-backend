package org.smartkup.smartkup.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ShoppingListItems")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(precision = 10, scale = 2)
    private BigDecimal quantity;

    @Column(length = 20)
    private String unit;

    @Column(name = "estimated_price", precision = 10, scale = 2)
    private BigDecimal estimatedPrice;

    @Column(name = "actual_price", precision = 10, scale = 2)
    private BigDecimal actualPrice;

    @Column(name = "is_purchased")
    private Boolean isPurchased = false;

    @Column(name = "added_from_pantry")
    private Boolean addedFromPantry = false;

    @Column(name = "added_at", insertable = false, updatable = false)
    private LocalDateTime addedAt;
}
