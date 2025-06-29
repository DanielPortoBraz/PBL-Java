package View_GUI.controller;

import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.HashSet;

public abstract class Validador {

    public static void entradaSomenteNumerica(TextField textField) {
        textField.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.matches("\\d*")) {
                textField.setText(newText.replaceAll("[^\\d]", ""));
            }
        });
    }

    public static HashSet<String> entradaComMultiplasStrings(String texto) {
        return new HashSet<>(Arrays.asList(texto.split("\\s*,\\s*")));
    }
}