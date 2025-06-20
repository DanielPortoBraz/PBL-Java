package principal.controller;

import static principal.DiarioCultural.changeScene;
import static principal.DiarioCultural.livroController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class tela_avaliacao_livroController implements Initializable {

    @FXML
    private Button bt_confirmar;

    @FXML
    private Button bt_retornar;

    @FXML
    private TextField tf_pontuacao;

    @FXML
    private TextField tf_review;

    @FXML
    void clicarConfirmar(ActionEvent event) {

    }

    @FXML
    void clicarRetornar(ActionEvent event) {
        changeScene("principal");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
