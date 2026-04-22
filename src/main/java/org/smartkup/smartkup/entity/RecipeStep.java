package org.smartkup.smartkup.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "RecipeSteps")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long step_id;

    @Column(name = "recipe_id")
    private Long recipeId;

    @Column(name = "step_number", nullable = false)
    private Integer stepNumber;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String instruction;

    @Column(name = "step_image_url", columnDefinition = "TEXT")
    private String stepImageUrl;
}