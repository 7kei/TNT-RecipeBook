module com.twinkletoes.recipebook {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.twinkletoes.recipebook.UI.JFX to javafx.fxml;
    exports com.twinkletoes.recipebook.UI.JFX;
}
