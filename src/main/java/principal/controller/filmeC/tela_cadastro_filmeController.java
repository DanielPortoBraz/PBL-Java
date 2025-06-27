package principal.controller.filmeC;

import Model.Genero;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import org.controlsfx.control.CheckComboBox;
import principal.DiarioCultural;

import static principal.controller.Validador.*;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

import static principal.DiarioCultural.filmeController;

public class tela_cadastro_filmeController implements Initializable {

    private CheckComboBox<Genero> checkComboBox;

    @FXML
    private Button bt_confirmar;

    @FXML
    private Button bt_retornar;

    @FXML
    private HBox cb_generos;

    @FXML
    private RadioButton rb_naoVisto;

    @FXML
    private RadioButton rb_simVisto;

    @FXML
    private TextField tf_anoLancamento;

    @FXML
    private TextField tf_direcao;

    @FXML
    private TextField tf_duracao;

    @FXML
    private TextField tf_elenco;

    @FXML
    private TextField tf_ondeAssistir;

    @FXML
    private TextField tf_roteiro;

    @FXML
    private TextField tf_titulo;

    @FXML
    private TextField tf_tituloOriginal;

    @FXML
    private ToggleGroup visto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializa CheckComboBox de gêneros
        ObservableList<Genero> generos = FXCollections.observableArrayList(Genero.values());
        checkComboBox = new CheckComboBox<>(generos);
        cb_generos.getChildren().add(checkComboBox);

        // Agrupa os RadioButtons
        ToggleGroup grupoVisto = new ToggleGroup();
        rb_simVisto.setToggleGroup(grupoVisto);
        rb_naoVisto.setToggleGroup(grupoVisto);

        // Restringe entradas numéricas
        entradaSomenteNumerica(tf_anoLancamento);
        entradaSomenteNumerica(tf_duracao);
    }

    @FXML
    void clicarConfirmar(ActionEvent event) {
        String titulo = tf_titulo.getText().trim();
        String tituloOriginal = tf_tituloOriginal.getText().trim();
        String anoStr = tf_anoLancamento.getText().trim();
        String duracaoStr = tf_duracao.getText().trim();
        String direcao = tf_direcao.getText().trim();
        String roteiro = tf_roteiro.getText().trim();
        String elenco = tf_elenco.getText().trim();
        String ondeAssistir = tf_ondeAssistir.getText().trim();

        // Validações de campos vazios
        if (titulo.isEmpty() || tituloOriginal.isEmpty() || anoStr.isEmpty()
                || duracaoStr.isEmpty() || direcao.isEmpty() || roteiro.isEmpty()
                || elenco.isEmpty() || ondeAssistir.isEmpty()) {
            exibirAlerta("Erro", "Preencha todos os campos.");
            return;
        }

        int anoLancamento, tempoDuracao;
        try {
            anoLancamento = Integer.parseInt(anoStr);
            tempoDuracao = Integer.parseInt(duracaoStr);
        } catch (NumberFormatException e) {
            exibirAlerta("Erro", "Ano ou duração inválida.");
            return;
        }

        boolean visto = rb_simVisto.isSelected();

        // Gêneros
        ObservableList<Genero> generosSelecionados = checkComboBox.getCheckModel().getCheckedItems();
        HashSet<Genero> generoHashSet = new HashSet<>(generosSelecionados);
        if (generoHashSet.isEmpty()) {
            exibirAlerta("Erro", "Selecione pelo menos um gênero.");
            return;
        }

        // Conversão dos campos de múltiplos valores
        HashSet<String> direcaoSet = entradaComMultiplasStrings(direcao);
        HashSet<String> roteiroSet = entradaComMultiplasStrings(roteiro);
        HashSet<String> elencoSet = entradaComMultiplasStrings(elenco);
        HashSet<String> ondeAssistirSet = entradaComMultiplasStrings(ondeAssistir);

        boolean sucesso = filmeController.cadastrarFilme(
                titulo, generoHashSet, anoLancamento, visto, tempoDuracao,
                direcaoSet, roteiroSet, elencoSet, tituloOriginal, ondeAssistirSet
        );

        if (sucesso) {
            filmeController.salvarFilmes();
            exibirAlerta("Sucesso", "Filme cadastrado com sucesso!");
            limparCampos();
        } else {
            exibirAlerta("Erro", "Não foi possível cadastrar o filme.");
        }
    }

    private void limparCampos() {
        tf_titulo.clear();
        tf_tituloOriginal.clear();
        tf_anoLancamento.clear();
        tf_duracao.clear();
        tf_direcao.clear();
        tf_roteiro.clear();
        tf_elenco.clear();
        tf_ondeAssistir.clear();
        rb_simVisto.setSelected(false);
        rb_naoVisto.setSelected(false);
        checkComboBox.getCheckModel().clearChecks();
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    void clicarRetornar(ActionEvent event) {
        DiarioCultural.changeScene("/telas/tela_principal.fxml");
    }
}
