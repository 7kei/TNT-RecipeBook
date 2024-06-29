package com.twinkletoes.recipebook.UI.JFX;

import java.io.IOException;

import com.twinkletoes.recipebook.Base.Session;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Parent;

public class LoginMenuController {

    private Session currentSession;

    @FXML private TextField userNameField;
    @FXML private PasswordField passwordField;

    void init (Session session) { this.currentSession = session; }

    @FXML
    private void loginAction() throws IOException {
        
        int result = currentSession.login(userNameField.getText(), passwordField.getText());
        if (result == 0) { // Successful login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
            Parent menu = loader.load();
            
            MainMenuController controller = loader.getController();
            controller.init(currentSession, menu); 

            JFXApp.setRoot(menu);
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            
            // Default error
            String contentText = "An error occured.";

            // Incorrect password (Result 1)
            if (result == 1) contentText = "Incorrect password.";
            // User not found
            else if (result == 2) contentText = "User not found in database.";

            alert.setContentText(contentText);
            alert.showAndWait();
        }
        
    }
}
