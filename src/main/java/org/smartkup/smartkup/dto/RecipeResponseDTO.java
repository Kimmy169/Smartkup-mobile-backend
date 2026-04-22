package org.smartkup.smartkup.dto;

import lombok.Data;
import org.smartkup.smartkup.entity.Recipe;
import org.smartkup.smartkup.entity.RecipeIngredient;
import org.smartkup.smartkup.entity.RecipeStep;

import java.util.List;

@Data
public class RecipeResponseDTO {
    private Recipe recipeDetails;
    private List<RecipeIngredient> ingredients;
    private List<RecipeStep> steps;

    public RecipeResponseDTO(Recipe recipeDetails, List<RecipeIngredient> ingredients, List<RecipeStep> steps) {
        this.recipeDetails = recipeDetails;
        this.ingredients = ingredients;
        this.steps = steps;
    }
}