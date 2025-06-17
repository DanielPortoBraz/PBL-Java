package principal.controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import principal.DiarioCultural;

public class tela_cadastro_livroController implements Initializable {

    @FXML
    private Button bt_retornar;

    @FXML
    void clicarRetornar(ActionEvent event) {
        DiarioCultural.changeScene("principal");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
