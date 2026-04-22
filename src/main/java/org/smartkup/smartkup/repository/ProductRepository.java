package org.smartkup.smartkup.repository;

import org.smartkup.smartkup.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Allows us to search if a product already exists by typing its name
    Optional<Product> findByNameIgnoreCase(String name);
}