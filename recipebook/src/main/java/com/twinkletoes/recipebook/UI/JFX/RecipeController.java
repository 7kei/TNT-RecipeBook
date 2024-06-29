package com.twinkletoes.recipebook.UI.JFX;

import com.twinkletoes.recipebook.Base.Recipe;
import com.twinkletoes.recipebook.Base.UserAccess;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class RecipeController {

    @FXML private Label recipeName;
    @FXML private Label recipeDescription;
    @FXML private Label recipeAccessLevel;
    @FXML private VBox ingredientsVbox;
    @FXML private VBox stepsVbox;

    void init(Recipe recipe) {
        recipeName.setText(recipe.getName());
        recipeDescription.setText(recipe.getDescription());
        recipeAccessLevel.setText(UserAccess.toString(recipe.getRecipeAccessLevel()));

        for (String ingredient : recipe.getIngredients()) {
            Label label = new Label("- "+ingredient);
            ingredientsVbox.getChildren().add(label);
        }

        for (String step : recipe.getSteps()) {
            Label label = new Label("- "+step);
            stepsVbox.getChildren().add(label);
        }
    }
}
