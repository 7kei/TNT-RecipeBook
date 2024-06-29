package com.twinkletoes.recipebook.UI.JFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.twinkletoes.recipebook.Base.Session;

/**
 * JavaFX App
 */
public class JFXApp extends Application {

    private static Scene scene;
    private Session currentSession;

    @Override
    public void start(Stage stage) throws IOException {
        currentSession = new Session();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent menu = loader.load();
        
        LoginController controller = loader.getController();
        controller.init(currentSession); 

        scene = new Scene(menu, 640, 480);
        
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop(){
        currentSession.saveDatabases();
    }

    public static void setRoot(Parent in) throws IOException {
        scene.setRoot(in);
    }

    public void main() {
        launch();
    }

}