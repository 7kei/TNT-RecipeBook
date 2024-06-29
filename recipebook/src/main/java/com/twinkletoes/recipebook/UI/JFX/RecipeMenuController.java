package com.twinkletoes.recipebook.UI.JFX;

import java.io.IOException;
import java.util.List;

import com.twinkletoes.recipebook.Base.Recipe;
import com.twinkletoes.recipebook.Base.Session;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


public class RecipeMenuController {
    private Session currentSession;
    private List<Recipe> recipes;
    private Parent mainMenu;

    @FXML private Label recipeText;
    @FXML private ListView recipeListView;

    void init(Session session, int type, Parent mainMenu) { 
        currentSession = session;
        this.mainMenu = mainMenu;

        switch (type) {
            case 0:
                recipeText.setText("Available beverages:");
                recipes = currentSession.getGlobalBeverages();
                break;
            case 1: 
                recipeText.setText("Available pastries:");
                recipes = currentSession.getGlobalPastries();
                break;
        }

        populateRecipes(type);
    }

    private void populateRecipes(int type) {
        ObservableList<String> recipeTitles = FXCollections.observableArrayList();
        for (Recipe recipe : recipes) {
            recipeTitles.add(recipe.getName());
        }

        recipeListView.getItems().addAll(recipeTitles);
    }

    @FXML
    public void openRecipeAction() throws IOException {
        ObservableList selectedIndices = recipeListView.getSelectionModel().getSelectedIndices();

        Recipe selectedRecipe = recipes.get((int)selectedIndices.get(0));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("genericRecipe.fxml"));
        Parent menu = loader.load();
        
        RecipeController controller = loader.getController();
        controller.init(selectedRecipe);

        Stage stage = new Stage();
        Scene scene = new Scene(menu, 640, 360);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void backToRecipeMenuAction() throws IOException {
        JFXApp.setRoot(mainMenu);
    }
}
