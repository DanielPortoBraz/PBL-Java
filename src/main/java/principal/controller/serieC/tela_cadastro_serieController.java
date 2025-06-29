package principal.controller.serieC;

import Model.Genero;
import Model.Temporada;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import org.controlsfx.control.CheckComboBox;
import principal.controller.Validador;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

import static principal.DiarioCultural.serieController;
import static principal.DiarioCultural.changeScene;

public class tela_cadastro_serieController implements Initializable {

    private CheckComboBox<Genero> checkComboBox;

    private HashSet<Temporada> temporadasAdicionadas = new HashSet<>();

    @FXML
    private Button bt_adicionar;

    @FXML
    private Button bt_adicionarTemporada;

    @FXML
    private Button bt_confirmar;

    @FXML
    private Button bt_fechaAdicaoTemporada;

    @FXML
    private Button bt_retornar;

    @FXML
    private HBox cb_generos;

    @FXML
    private HBox mn_AdicaoTemporada;

    @FXML
    private RadioButton rb_naoVisto;

    @FXML
    private RadioButton rb_simVisto;

    @FXML
    private TextField tf_ano;

    @FXML
    private TextField tf_anoEncerramento;

    @FXML
    private TextField tf_anoLancamento;

    @FXML
    private TextField tf_elenco;

    @FXML
    private TextField tf_numero;

    @FXML
    private TextField tf_ondeAssistir;

    @FXML
    private TextField tf_quantidadeEps;

    @FXML
    private TextField tf_titulo;

    @FXML
    private TextField tf_tituloOriginal;

    @FXML
    private ToggleGroup visto;

    @FXML
    void clicarAdicionar(ActionEvent event) {
        try {
            int numero = Integer.parseInt(tf_numero.getText());
            int ano = Integer.parseInt(tf_ano.getText());
            int quantidadeEpisodios = Integer.parseInt(tf_quantidadeEps.getText());

            Temporada novaTemporada = new Temporada(ano, quantidadeEpisodios, numero);
            if (temporadasAdicionadas.add(novaTemporada)) {
                exibirAlerta("Temporada adicionada", "A temporada foi adicionada com sucesso!");
                // Limpa os campos após adição
                tf_numero.clear();
                tf_ano.clear();
                tf_quantidadeEps.clear();
            } else {
                exibirAlerta("Temporada já existente", "Essa temporada já foi adicionada.");
            }
        } catch (NumberFormatException e) {
            exibirAlerta("Entrada inválida", "Preencha corretamente os campos numéricos da temporada.");
        }
    }

    @FXML
    void clicarAdicionarTemporada(ActionEvent event) {
        mn_AdicaoTemporada.setVisible(true);
    }

    @FXML
    void clicarConfirmar(ActionEvent event) {
        try {
            String titulo = tf_titulo.getText().trim();
            String tituloOriginal = tf_tituloOriginal.getText().trim();
            int anoLancamento = Integer.parseInt(tf_anoLancamento.getText());
            int anoEncerramento = Integer.parseInt(tf_anoEncerramento.getText());
            boolean foiVisto = rb_simVisto.isSelected();

            HashSet<String> elenco = new HashSet<>(List.of(tf_elenco.getText().split(",")));
            HashSet<String> ondeAssistir = new HashSet<>(List.of(tf_ondeAssistir.getText().split(",")));

            HashSet<Genero> generosSelecionados = new HashSet<>(checkComboBox.getCheckModel().getCheckedItems());

            if (titulo.isEmpty() || tituloOriginal.isEmpty()) {
                exibirAlerta("Campos obrigatórios", "Preencha os campos de título e título original.");
                return;
            }

            if (temporadasAdicionadas.isEmpty()) {
                exibirAlerta("Nenhuma temporada", "Adicione ao menos uma temporada antes de cadastrar a série.");
                return;
            }

            if (generosSelecionados.isEmpty()) {
                exibirAlerta("Gênero obrigatório", "Selecione ao menos um gênero.");
                return;
            }

            boolean sucesso = serieController.cadastrarSerie(
                    titulo, generosSelecionados, anoLancamento, foiVisto,
                    anoEncerramento, elenco, tituloOriginal,
                    ondeAssistir, temporadasAdicionadas
            );

            if (sucesso) {
                serieController.salvarSeries();
                exibirAlerta("Cadastro realizado", "Série cadastrada com sucesso!");
                changeScene("/telas/tela_principal.fxml");
            } else {
                exibirAlerta("Erro", "Erro ao cadastrar a série.");
            }

        } catch (NumberFormatException e) {
            exibirAlerta("Erro de entrada", "Ano de lançamento e encerramento devem ser números válidos.");
        } catch (Exception e) {
            exibirAlerta("Erro inesperado", "Ocorreu um erro: " + e.getMessage());
        }
    }



    @FXML
    void clicarFecharAdicaoTemporada(ActionEvent event) {
        mn_AdicaoTemporada.setVisible(false);
    }

    @FXML
    void clicarRetornar(ActionEvent event) {
        changeScene("/telas/tela_principal.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Validador.entradaSomenteNumerica(tf_ano);
        Validador.entradaSomenteNumerica(tf_anoLancamento);
        Validador.entradaSomenteNumerica(tf_anoEncerramento);
        Validador.entradaSomenteNumerica(tf_numero);
        Validador.entradaSomenteNumerica(tf_quantidadeEps);
        Validador.entradaComMultiplasStrings(tf_elenco.getText());
        Validador.entradaComMultiplasStrings(tf_ondeAssistir.getText());

        // Popula o ListView com os gêneros e ativa seleção múltipla
        ObservableList<Genero> generos = FXCollections.observableArrayList(Genero.values());
        checkComboBox = new CheckComboBox<>(generos);
        cb_generos.getChildren().add(checkComboBox);
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
