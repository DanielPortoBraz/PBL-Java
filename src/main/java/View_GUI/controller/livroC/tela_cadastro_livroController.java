package View_GUI.controller.livroC;

import Model.Genero;
import javafx.scene.layout.HBox;
import org.controlsfx.control.CheckComboBox;
import View_GUI.controller.Validador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import View_GUI.DiarioCultural;

import static View_GUI.DiarioCultural.livroController;

/**
 * Controlador da tela de cadastro de livros.
 * Gerencia a entrada de dados e interação do usuário para cadastrar um novo livro no sistema.
 */
public class tela_cadastro_livroController implements Initializable {

    private CheckComboBox<Genero> checkComboBox;

    @FXML
    private Button bt_confirmar;

    @FXML
    private Button bt_retornar;

    @FXML
    private HBox cb_generos;

    @FXML
    private RadioButton rb_naoExemplar;

    @FXML
    private RadioButton rb_naoVisto;

    @FXML
    private RadioButton rb_simExemplar;

    @FXML
    private RadioButton rb_simVisto;

    @FXML
    private TextField tf_anoPublicacao;

    @FXML
    private TextField tf_autor;

    @FXML
    private TextField tf_editora;

    @FXML
    private TextField tf_isbn;

    @FXML
    private TextField tf_titulo;

    /**
     * Inicializa os componentes da interface, como o grupo de botões e a lista de gêneros.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Genero> generos = FXCollections.observableArrayList(Genero.values());
        checkComboBox = new CheckComboBox<>(generos);
        cb_generos.getChildren().add(checkComboBox);

        ToggleGroup grupoVisto = new ToggleGroup();
        rb_simVisto.setToggleGroup(grupoVisto);
        rb_naoVisto.setToggleGroup(grupoVisto);

        ToggleGroup grupoExemplar = new ToggleGroup();
        rb_simExemplar.setToggleGroup(grupoExemplar);
        rb_naoExemplar.setToggleGroup(grupoExemplar);

        Validador.entradaSomenteNumerica(tf_anoPublicacao);
    }

    /**
     * Ação do botão "Confirmar". Realiza as validações e tenta cadastrar o livro com os dados informados.
     *
     * @param event Evento de clique no botão.
     */
    @FXML
    void clicarConfirmar(ActionEvent event) {
        String titulo = tf_titulo.getText().trim();
        String autor = tf_autor.getText().trim();
        String editora = tf_editora.getText().trim();
        String isbn = tf_isbn.getText().trim();

        if (titulo.isEmpty() || autor.isEmpty() || editora.isEmpty() || isbn.isEmpty() || tf_anoPublicacao.getText().isEmpty()) {
            exibirAlerta("Erro", "Preencha todos os campos.");
            return;
        }

        int ano;
        try {
            ano = Integer.parseInt(tf_anoPublicacao.getText());
        } catch (NumberFormatException e) {
            exibirAlerta("Erro", "Ano de publicação inválido.");
            return;
        }

        boolean visto = rb_simVisto.isSelected();
        boolean exemplar = rb_simExemplar.isSelected();

        ObservableList<Genero> generosSelecionados = checkComboBox.getCheckModel().getCheckedItems();
        HashSet<Genero> generoHashSet = new HashSet<>(generosSelecionados);

        if (generoHashSet.isEmpty()) {
            exibirAlerta("Erro", "Selecione pelo menos um gênero.");
            return;
        }

        boolean sucesso = livroController.cadastrarLivro(titulo, generoHashSet, ano, visto, autor, editora, isbn, exemplar);

        if (sucesso) {
            livroController.salvarLivros();
            exibirAlerta("Sucesso", "Livro cadastrado com sucesso!");
            limparCampos();
        } else {
            exibirAlerta("Erro", "Não foi possível cadastrar o livro.");
        }

        limparCampos();
    }

    /**
     * Exibe uma caixa de diálogo com uma mensagem informativa.
     *
     * @param titulo   Título da janela de alerta.
     * @param mensagem Mensagem a ser exibida.
     */
    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    /**
     * Limpa todos os campos da tela de cadastro, incluindo textos e seleções.
     */
    private void limparCampos() {
        tf_titulo.clear();
        tf_autor.clear();
        tf_editora.clear();
        tf_isbn.clear();
        tf_anoPublicacao.clear();
        rb_simVisto.setSelected(false);
        rb_naoVisto.setSelected(false);
        rb_simExemplar.setSelected(false);
        rb_naoExemplar.setSelected(false);
        checkComboBox.getCheckModel().clearChecks();
    }

    /**
     * Ação do botão "Retornar". Redireciona o usuário de volta à tela View_GUI.
     *
     * @param event Evento de clique no botão.
     */
    @FXML
    void clicarRetornar(ActionEvent event) {
        DiarioCultural.changeScene("/telas/tela_principal.fxml");
    }

}
