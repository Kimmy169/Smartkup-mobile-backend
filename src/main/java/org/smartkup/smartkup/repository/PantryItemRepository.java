package org.smartkup.smartkup.repository;

import org.smartkup.smartkup.entity.PantryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PantryItemRepository extends JpaRepository<PantryItem, Long> {
    List<PantryItem> findByProduct_CategoryId(Long categoryId);
}