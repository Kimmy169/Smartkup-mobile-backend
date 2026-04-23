package org.smartkup.smartkup.repository;

import org.smartkup.smartkup.entity.PantryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PantryItemRepository extends JpaRepository<PantryItem, Long> {

    // Changed "Category_Id" to "CategoryId" to match your Product entity!
    List<PantryItem> findByProduct_CategoryId(Long categoryId);
}