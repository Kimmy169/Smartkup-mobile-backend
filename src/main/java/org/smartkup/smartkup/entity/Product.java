package org.smartkup.smartkup.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "Products")
@Data // Automatically generates getters, setters, toString, and equals
@NoArgsConstructor // Required by JPA
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_Id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "default_unit", length = 20)
    private String defaultUnit;
}