package org.smartkup.smartkup.service;

import org.smartkup.smartkup.dto.RecipeResponseDTO;
import org.smartkup.smartkup.entity.Recipe;
import org.smartkup.smartkup.entity.RecipeIngredient;
import org.smartkup.smartkup.entity.RecipeStep;
import org.smartkup.smartkup.repository.RecipeIngredientRepository;
import org.smartkup.smartkup.repository.RecipeRepository;
import org.smartkup.smartkup.repository.RecipeStepRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeStepRepository stepRepository;
    private final RecipeIngredientRepository ingredientRepository;

    public RecipeService(RecipeRepository recipeRepository, RecipeStepRepository stepRepository, RecipeIngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.stepRepository = stepRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public RecipeStep addStep(RecipeStep step) {
        return stepRepository.save(step);
    }

    public RecipeIngredient addIngredient(RecipeIngredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public RecipeResponseDTO getFullRecipe(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        List<RecipeIngredient> ingredients = ingredientRepository.findByRecipeId(recipeId);
        List<RecipeStep> steps = stepRepository.findByRecipeIdOrderByStepNumberAsc(recipeId);

        return new RecipeResponseDTO(recipe, ingredients, steps);
    }
}