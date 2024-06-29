package com.twinkletoes.recipebook.UI.JFX;

import java.io.IOException;

import com.twinkletoes.recipebook.Base.Session;
import com.twinkletoes.recipebook.Base.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;

public class AdminMenuController {
    private Session currentSession;
    private Parent mainMenu;

    @FXML private ListView usersListView;
    
    void init(Session session, Parent mainMenu) {
        currentSession = session;
        this.mainMenu = mainMenu;
        populateListView();
    }

    private void populateListView() {
        ObservableList<String> usernames = FXCollections.observableArrayList();
        for (User user : currentSession.getAllUsers()) {
            usernames.add(user.getUsername());
        }

        usersListView.getItems().setAll(usernames);
    }

    @FXML
    public void removeUserAction() throws IOException {
        ObservableList selectedIndices = usersListView.getSelectionModel().getSelectedIndices();

        int result = currentSession.removeUser((int)selectedIndices.get(0));
        if (result == 0) { // Successful user removal
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("User removed.");
            alert.showAndWait();
            populateListView();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            
            // Default error
            String contentText = "An error occured.";

            // Same user error
            if (result == 1) contentText = "User being removed cannot be the same as the logged in user.";

            alert.setContentText(contentText);
            alert.showAndWait();
        }
    }

    @FXML
    public void backToMenuAction() throws IOException {
        JFXApp.setRoot(mainMenu);
    }

}
