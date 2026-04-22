package org.smartkup.smartkup.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Products")
@Data // This Lombok annotation is the magic that creates getName(), setName(), etc.
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "name")
    private String name;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "default_unit")
    private String defaultUnit;
}