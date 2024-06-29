package com.twinkletoes.recipebook.UI.JFX;

import java.io.IOException;

import com.twinkletoes.recipebook.Base.Session;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignupMenuController {
    private Session currentSession;

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField fullNameField;
    @FXML private ComboBox accessLevelComboBox;

    void init(Session session) {
        currentSession = session;

        // Populate combobox
        String[] userLevels = {"Employee", "Manager", "Admin"};
        accessLevelComboBox.getItems().addAll(FXCollections.observableArrayList(userLevels));
    }

    @FXML
    private void signupAction() throws IOException {
        int result = currentSession.signUp(usernameField.getText(), passwordField.getText(), fullNameField.getText(), accessLevelComboBox.getSelectionModel().getSelectedIndex());
        if (result == 0) { // Successful signup
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
            Parent menu = loader.load();
            
            MainMenuController controller = loader.getController();
            controller.init(currentSession, menu); 

            JFXApp.setRoot(menu);
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            
            // Default error
            String contentText = "An error occured.";

            // User with same username
            if (result == 1) contentText = "User with same username already exists. Please try again.";

            alert.setContentText(contentText);
            alert.showAndWait();
        }
    }
}
