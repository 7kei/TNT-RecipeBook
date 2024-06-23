package com.twinkletoes.recipebook.UI.JFX;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        JFXApp.setRoot("secondary");
    }
}
