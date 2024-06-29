package com.twinkletoes.recipebook.UI.JFX;

import java.io.IOException;

import com.twinkletoes.recipebook.Base.Session;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;

public class ChangePasswordMenuController {
    private Session currentSession;
    private Parent mainMenu;

    @FXML private PasswordField passwordField;

    void init(Session session, Parent mainMenu) {
        currentSession = session;
        this.mainMenu = mainMenu;
    }

    @FXML
    public void okAction() throws IOException {
        int result = currentSession.changeUserPassword(passwordField.getText());
        if (result == 0) { // Successful password change
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Password successfully changed. Returning to main menu.");
            alert.showAndWait();
            JFXApp.setRoot(mainMenu);
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            
            // Default error
            String contentText = "An error occured.";

            // Password with invalid characters
            if (result == 1) contentText = "Password has invalid characters.";

            alert.setContentText(contentText);
            alert.showAndWait();
        }
    }

    @FXML
    public void backToMenuAction() throws IOException {
        JFXApp.setRoot(mainMenu);
    }

}
