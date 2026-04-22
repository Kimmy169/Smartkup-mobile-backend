package org.smartkup.smartkup.controller;

import org.smartkup.smartkup.dto.RecipeResponseDTO;
import org.smartkup.smartkup.entity.Recipe;
import org.smartkup.smartkup.entity.RecipeIngredient;
import org.smartkup.smartkup.entity.RecipeStep;
import org.smartkup.smartkup.service.RecipeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService service;

    public RecipeController(RecipeService service) {
        this.service = service;
    }

    // Create the base recipe
    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return service.createRecipe(recipe);
    }

    // Add an ingredient to a recipe
    @PostMapping("/ingredients")
    public RecipeIngredient addIngredient(@RequestBody RecipeIngredient ingredient) {
        return service.addIngredient(ingredient);
    }

    // Add a step to a recipe
    @PostMapping("/steps")
    public RecipeStep addStep(@RequestBody RecipeStep step) {
        return service.addStep(step);
    }

    // Get the fully bundled recipe
    @GetMapping("/{recipeId}")
    public RecipeResponseDTO getFullRecipe(@PathVariable Long recipeId) {
        return service.getFullRecipe(recipeId);
    }
}