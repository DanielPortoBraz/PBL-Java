package principal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import principal.DiarioCultural;

public class tela_principalController implements Initializable {

    @FXML
    private Button bt_avaliar;

    @FXML
    private Button bt_buscar;

    @FXML
    private Button bt_cadastrar;

    @FXML
    private Button bt_filme;

    @FXML
    private Button bt_listar;

    @FXML
    private Button bt_livro;

    @FXML
    private Button bt_remover;

    @FXML
    private Button bt_sair;

    @FXML
    private Button bt_serie;

    @FXML
    private Text id_titulo;

    @FXML
    private AnchorPane mn_SelecaoRegistro;

    @FXML
    void clicarAvaliar(ActionEvent event) {

    }

    @FXML
    void clicarBuscar(ActionEvent event) {

    }

    @FXML
    void clicarCadastrar(ActionEvent event) {
        ativarSelecaoRegistro();
    }

    @FXML
    void clicarListar(ActionEvent event) {

    }

    @FXML
    void clicarRemover(ActionEvent event) {
        desativarSelecaoRegistro();
    }

    @FXML
    void clicarSair(ActionEvent event) {

    }

    @FXML
    void ativarSelecaoRegistro(){
        mn_SelecaoRegistro.setVisible(true);
    }

    @FXML
    void desativarSelecaoRegistro(){
        mn_SelecaoRegistro.setVisible(false);
    }

    @FXML
    void selecionarFilme(ActionEvent event) {

    }

    @FXML
    void selecionarLivro(ActionEvent event) {
        DiarioCultural.changeScene("cadastro");
    }

    @FXML
    void selecionarSerie(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
