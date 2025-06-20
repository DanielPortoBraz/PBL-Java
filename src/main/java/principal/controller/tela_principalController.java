package principal.controller;

import principal.DiarioCultural;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import static principal.DiarioCultural.changeScene;
import static principal.DiarioCultural.livroController;

public class tela_principalController implements Initializable {

    private enum AcaoAtual {
        NENHUMA, CADASTRAR, AVALIAR
    }

    private AcaoAtual acaoAtual = AcaoAtual.NENHUMA;

    @FXML
    private Button bt_avaliar;

    @FXML
    private Button bt_buscar;

    @FXML
    private Button bt_cadastrar;

    @FXML
    private Button bt_confirmar;

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
    private AnchorPane ap_entradaIsbn;

    @FXML
    private TextField tf_isbnLivro;

    @FXML
    void clicarAvaliar(ActionEvent event) {
        acaoAtual = AcaoAtual.AVALIAR;
        ativarSelecaoRegistro();
    }

    @FXML
    void clicarBuscar(ActionEvent event) {

    }

    @FXML
    void clicarCadastrar(ActionEvent event) {
        acaoAtual = AcaoAtual.CADASTRAR;
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
    void ativarEntradaIsbn(){
        ap_entradaIsbn.setVisible(true);
    }

    @FXML
    void desativarEntradaIsbn(){
        ap_entradaIsbn.setVisible(false);
    }

    @FXML
    void selecionarFilme(ActionEvent event) {

    }

    @FXML
    void selecionarLivro(ActionEvent event) {
        switch (acaoAtual) {
            case CADASTRAR:
                DiarioCultural.changeScene("cadastro_livro");
                break;

            case AVALIAR:
                ativarEntradaIsbn();
                break;

            default:
                break;
        }
    }

    @FXML
    void selecionarSerie(ActionEvent event) {

    }

    @FXML
    void clicarConfirmarIsbn(ActionEvent event){
        desativarEntradaIsbn();

        if (livroController.buscarLivros("5", tf_isbnLivro.getText()))
            changeScene("avaliacao_livro");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
