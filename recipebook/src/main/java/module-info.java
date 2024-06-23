module com.twinkletoes.recipebook {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.twinkletoes.recipebook.UI.JFX to javafx.fxml;

    exports com.twinkletoes.recipebook.UI.JFX;
}
