package com.twinkletoes.recipebook.UI.JFX;

import java.io.IOException;

import com.twinkletoes.recipebook.Base.Session;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class LoginController {
    private Session currentSession;

    void init(Session session) { this.currentSession = session; }

    @FXML
    private void loginAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginMenu.fxml"));
        Parent menu = loader.load();
        
        LoginMenuController controller = loader.getController();
        controller.init(currentSession); 

        JFXApp.setRoot(menu);
    }

    @FXML
    private void signUpAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signupMenu.fxml"));
        Parent menu = loader.load();
        
        SignupMenuController controller = loader.getController();
        controller.init(currentSession); 

        JFXApp.setRoot(menu);
    }

    @FXML
    private void exitProgram() throws IOException {
        Platform.exit();
    }
}
