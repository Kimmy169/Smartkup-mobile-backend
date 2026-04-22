package org.smartkup.smartkup.repository;

import org.smartkup.smartkup.entity.RecipeStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecipeStepRepository extends JpaRepository<RecipeStep, Long> {
    // Automatically sorts the steps by stepNumber!
    List<RecipeStep> findByRecipeIdOrderByStepNumberAsc(Long recipeId);
}