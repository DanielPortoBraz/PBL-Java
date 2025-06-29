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
    private HBox ap_entradaIdentificacao;

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
    private Button bt_fechaSelecaoSerie;

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
    private Button bt_serieSerie;

    @FXML
    private Button bt_serieTemporada;

    @FXML
    private Text id_titulo;

    @FXML
    private HBox mn_SelecaoRegistro;

    @FXML
    private HBox mn_SelecaoSerie;

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

    void ativarSelecaoRegistro() {
        mn_SelecaoRegistro.setVisible(true);
    }

    void ativarSelecaoSerie() {
        mn_SelecaoSerie.setVisible(true);
    }

    void desativarSelecaoSerie() {
        mn_SelecaoSerie.setVisible(false);
    }

    void desativarSelecaoRegistro() {
        mn_SelecaoRegistro.setVisible(false);
    }

    @FXML
    void clicarFecharSelecaoRegistro(ActionEvent event) {
        desativarSelecaoRegistro();
    }

    @FXML
    void clicarFecharSelecaoSerie(ActionEvent event) {
        desativarSelecaoSerie();
    }

    void ativarEntradaIdentificacao() {
        ap_entradaIdentificacao.setVisible(true);
    }

    void desativarEntradaIdentifcacao() {
        ap_entradaIdentificacao.setVisible(false);
    }

    @FXML
    void clicarSelecionarFilme(ActionEvent event) {
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
    void clicarSelecionarLivro(ActionEvent event) {
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
    void clicarSelecionarSerie(ActionEvent event) {
        RegistroAtual = 3;

        switch (acaoAtual) {
            case CADASTRAR:
                ativarSelecaoSerie();
                break;

            case AVALIAR:
                ativarSelecaoSerie();
                break;

            case BUSCAR:
                DiarioCultural.changeScene("/telas/serie/tela_busca_serie.fxml");
                break;

            case LISTAR:
                DiarioCultural.changeScene("/telas/serie/tela_lista_serie.fxml");
                break;

            case REMOVER:
                ativarEntradaIdentificacao();
                break;

            default:
                break;
        }
    }

    @FXML
    void clicarSelecionarSerieSerie(ActionEvent event) {
        RegistroAtual = 3;

        switch (acaoAtual) {
            case CADASTRAR:
                DiarioCultural.changeScene("/telas/serie/tela_cadastro_serie.fxml");
                break;

            case AVALIAR:
                ativarEntradaIdentificacao();
                break;

            default:
                break;
        }
    }

    @FXML
    void clicarSelecionarSerieTemporada(ActionEvent event) {
        RegistroAtual = 4;

        switch (acaoAtual) {
            case CADASTRAR:
                DiarioCultural.changeScene("/telas/serie/tela_cadastro_temporada.fxml");
                break;

            case AVALIAR:
                ativarEntradaIdentificacao();
                break;

            default:
                break;
        }
    }

    @FXML
    void clicarConfirmarId(ActionEvent event) {
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
            } else if (acaoAtual == AcaoAtual.REMOVER) {
                if (livroController.removerLivro(isbn)) {
                    livroController.salvarLivros();
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Sucesso");
                    alerta.setContentText("Livro removido com sucesso!");
                    alerta.showAndWait();
                } else {
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Livro não encontrado");
                    alerta.setContentText("Nenhum livro foi encontrado com o ISBN informado.");
                    alerta.showAndWait();
                }
            }
        } else if (RegistroAtual == 2) { // 2 para filme

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

        } else if (RegistroAtual == 3 || RegistroAtual == 4) { // 3 para série e 4 para temporada
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

                if (serieController.buscarSeries("6", String.valueOf(id))) {

                    if (RegistroAtual == 3) {
                        changeScene("/telas/serie/tela_avaliacao_serie.fxml");
                    }
                    else {
                        changeScene("/telas/serie/tela_avaliacao_temporada.fxml");
                    }

                } else {
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Série não encontrada");
                    alerta.setContentText("Nenhuma série foi encontrada com o ID informado.");
                    alerta.showAndWait();
                }

            } else if (acaoAtual == AcaoAtual.REMOVER){
                if (serieController.removerSerie(id)) {
                    serieController.salvarSeries();
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Sucesso");
                    alerta.setContentText("Série removida com sucesso!");
                    alerta.showAndWait();
                } else {
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Série não encontrada");
                    alerta.setContentText("Nenhuma série foi encontrada com o ID informado.");
                    alerta.showAndWait();
                }
            }
        }
        tf_idRegistro.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
