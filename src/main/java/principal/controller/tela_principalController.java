package principal.controller;

import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import principal.DiarioCultural;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import static principal.DiarioCultural.*;

public class tela_principalController implements Initializable {

    private enum AcaoAtual {
        NENHUMA, CADASTRAR, REMOVER, BUSCAR, LISTAR, AVALIAR
    }

    private AcaoAtual acaoAtual = AcaoAtual.NENHUMA;
    private int RegistroAtual = 0;

    public static String isbn;
    public static int id;

    @FXML
    private Button bt_avaliar;

    @FXML
    private Button bt_buscar;

    @FXML
    private Button bt_cadastrar;

    @FXML
    private Button bt_confirmar;

    @FXML
    private Button bt_fechaSelecaoRegistro;

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
    private HBox mn_SelecaoRegistro;

    @FXML
    private HBox ap_entradaIdentificacao;

    @FXML
    private TextField tf_idRegistro;

    @FXML
    void clicarAvaliar(ActionEvent event) {
        acaoAtual = AcaoAtual.AVALIAR;
        ativarSelecaoRegistro();
    }

    @FXML
    void clicarBuscar(ActionEvent event) {
        acaoAtual = AcaoAtual.BUSCAR;
        ativarSelecaoRegistro();
    }

    @FXML
    void clicarCadastrar(ActionEvent event) {
        acaoAtual = AcaoAtual.CADASTRAR;
        ativarSelecaoRegistro();
    }

    @FXML
    void clicarListar(ActionEvent event) {
        acaoAtual = AcaoAtual.LISTAR;
        ativarSelecaoRegistro();
    }

    @FXML
    void clicarRemover(ActionEvent event) {
        acaoAtual = AcaoAtual.REMOVER;
        ativarSelecaoRegistro();
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
    void clicarFecharSelecaoRegistro(ActionEvent event) {
        desativarSelecaoRegistro();
    }

    @FXML
    void ativarEntradaIdentificacao(){
        ap_entradaIdentificacao.setVisible(true);
    }

    @FXML
    void desativarEntradaIdentifcacao(){
        ap_entradaIdentificacao.setVisible(false);
    }

    @FXML
    void selecionarFilme(ActionEvent event) {
        RegistroAtual = 2;

        switch (acaoAtual) {
            case CADASTRAR:
                DiarioCultural.changeScene("/telas/filme/tela_cadastro_filme.fxml");
                break;

            case AVALIAR:
                ativarEntradaIdentificacao();
                break;

            case BUSCAR:
                DiarioCultural.changeScene("/telas/filme/tela_busca_filme.fxml");
                break;

            case LISTAR:
                DiarioCultural.changeScene("/telas/filme/tela_lista_filme.fxml");
                break;

            case REMOVER:
                ativarEntradaIdentificacao();
                break;

            default:
                break;
        }
    }

    @FXML
    void selecionarLivro(ActionEvent event) {
        RegistroAtual = 1;

        switch (acaoAtual) {
            case CADASTRAR:
                DiarioCultural.changeScene("/telas/livro/tela_cadastro_livro.fxml");
                break;

            case AVALIAR:
                ativarEntradaIdentificacao();
                break;

            case BUSCAR:
                DiarioCultural.changeScene("/telas/livro/tela_busca_livro.fxml");
                break;

            case LISTAR:
                changeScene("/telas/livro/tela_lista_livro.fxml");
                break;

            case REMOVER:
                ativarEntradaIdentificacao();
                break;

            default:
                break;
        }
    }

    @FXML
    void selecionarSerie(ActionEvent event) {
        RegistroAtual = 3;
    }

    @FXML
    void clicarConfirmarId(ActionEvent event){
        desativarEntradaIdentifcacao();

        // Verificações para identificar se o espaço tf_id foi selecionado para livro, filme ou série
        if (RegistroAtual == 1) { // 1 para livro
            isbn = tf_idRegistro.getText();

            if (acaoAtual == AcaoAtual.AVALIAR) {

                if (livroController.buscarLivros("5", isbn)) {
                    changeScene("/telas/livro/tela_avaliacao_livro.fxml");
                } else {
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Livro não encontrado");
                    alerta.setContentText("Nenhum livro foi encontrado com o ISBN informado.");
                    alerta.showAndWait();
                }
            }
            else if (acaoAtual == AcaoAtual.REMOVER) {
                if (livroController.removerLivro(isbn)){
                    livroController.salvarLivros();
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Sucesso");
                    alerta.setContentText("Livro removido com sucesso!");
                    alerta.showAndWait();
                }
                else {
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Livro não encontrado");
                    alerta.setContentText("Nenhum livro foi encontrado com o ISBN informado.");
                    alerta.showAndWait();
                }
            }
        }
        else if (RegistroAtual == 2){ // 2 para filme

            try {
                id = Integer.parseInt(tf_idRegistro.getText());
            } catch (NumberFormatException e) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Erro de Formato");
                alerta.setContentText("Por favor, insira um ID válido (número inteiro).");
                alerta.showAndWait();
                return;
            }

            if (acaoAtual == AcaoAtual.AVALIAR) {

                if (filmeController.buscarFilmes("6", String.valueOf(id))) {
                    changeScene("/telas/filme/tela_avaliacao_filme.fxml");
                } else {
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Filme não encontrado");
                    alerta.setContentText("Nenhum filme foi encontrado com o ID informado.");
                    alerta.showAndWait();
                }

            } else if (acaoAtual == AcaoAtual.REMOVER) {

                if (filmeController.removerFilme(id)) {
                    filmeController.salvarFilmes();
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Sucesso");
                    alerta.setContentText("Filme removido com sucesso!");
                    alerta.showAndWait();
                } else {
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Filme não encontrado");
                    alerta.setContentText("Nenhum filme foi encontrado com o ID informado.");
                    alerta.showAndWait();
                }
            }

        }
        else if (RegistroAtual == 3){ // 3 para série

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
