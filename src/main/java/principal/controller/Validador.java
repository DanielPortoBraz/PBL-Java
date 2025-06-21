package principal.controller;

import javafx.scene.control.TextField;

public abstract class Validador {

    public static void entradaSomenteNumerica(TextField textField) {
        textField.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.matches("\\d*")) {
                textField.setText(newText.replaceAll("[^\\d]", ""));
            }
        });
    }
}