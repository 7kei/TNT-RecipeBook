package com.twinkletoes.recipebook.UI.JFX;

import java.io.IOException;

import com.twinkletoes.recipebook.Base.Session;
import com.twinkletoes.recipebook.Base.UserAccess;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainMenuController {

    private Session currentSession;
    private Parent mainMenu;
    @FXML private VBox mainMenuVbox;
    @FXML private Button removeUsersButton;

    void init(Session session, Parent mainMenu) { 
        currentSession = session; 
        this.mainMenu = mainMenu;

        if (currentSession.getCurrentUser().getUserAccessLevel() != UserAccess.ADMIN) {
            mainMenuVbox.getChildren().remove(removeUsersButton);
        }

    }

    @FXML
    public void displayBeverageRecipes() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("recipeMenu.fxml"));
        Parent menu = loader.load();
        
        RecipeMenuController controller = loader.getController();
        controller.init(currentSession, 0, mainMenu);

        JFXApp.setRoot(menu);
    }

    @FXML
    public void displayPastryRecipes() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("recipeMenu.fxml"));
        Parent menu = loader.load();
        
        RecipeMenuController controller = loader.getController();
        controller.init(currentSession, 1, mainMenu);

        JFXApp.setRoot(menu);
    }

    @FXML
    public void changePassword() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("changePasswordMenu.fxml"));
        Parent menu = loader.load();
        
        ChangePasswordMenuController controller = loader.getController();
        controller.init(currentSession, mainMenu);

        JFXApp.setRoot(menu);
    }

    @FXML
    public void removeUsers() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("adminMenu.fxml"));
        Parent menu = loader.load();
        
        AdminMenuController controller = loader.getController();
        controller.init(currentSession, mainMenu);

        JFXApp.setRoot(menu);
    }

    @FXML
    public void exitProgram() {
        Platform.exit();
    }
}
