package org.smartkup.smartkup.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "PantryItems")
public class PantryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pantry_item_id")
    private Long pantryItemId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private BigDecimal quantity;

    private String unit;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "added_at")
    private LocalDateTime addedAt;

    public PantryItem() {}

    public Long getPantryItemId() { return pantryItemId; }
    public void setPantryItemId(Long pantryItemId) { this.pantryItemId = pantryItemId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public BigDecimal getQuantity() { return quantity; }
    public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public LocalDate getExpirationDate() { return expirationDate; }
    public void setExpirationDate(LocalDate expirationDate) { this.expirationDate = expirationDate; }

    public LocalDateTime getAddedAt() { return addedAt; }
    public void setAddedAt(LocalDateTime addedAt) { this.addedAt = addedAt; }
}