package com.twinkletoes.recipebook.UI.JFX;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        JFXApp.setRoot("primary");
    }
}