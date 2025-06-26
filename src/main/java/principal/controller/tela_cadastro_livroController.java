package principal.controller;

import Controller.LivroController;
import Model.Genero;
import principal.controller.Validador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import principal.DiarioCultural;

import static principal.DiarioCultural.livroController;

public class tela_cadastro_livroController implements Initializable {

    @FXML
    private Button bt_confirmar;

    @FXML
    private Button bt_retornar;

    @FXML
    private ListView<Genero> lv_generos;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Popula o ListView com os gêneros e ativa seleção múltipla
        ObservableList<Genero> generos = FXCollections.observableArrayList(Genero.values());
        lv_generos.setItems(generos);
        lv_generos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Agrupa os RadioButtons
        ToggleGroup grupoVisto = new ToggleGroup();
        rb_simVisto.setToggleGroup(grupoVisto);
        rb_naoVisto.setToggleGroup(grupoVisto);

        ToggleGroup grupoExemplar = new ToggleGroup();
        rb_simExemplar.setToggleGroup(grupoExemplar);
        rb_naoExemplar.setToggleGroup(grupoExemplar);

        // Restringe o campo de ano para somente caracteres numéricos
        Validador.entradaSomenteNumerica(tf_anoPublicacao);
    }

    @FXML
    void clicarConfirmar(ActionEvent event) {
        String titulo = tf_titulo.getText().trim();
        String autor = tf_autor.getText().trim();
        String editora = tf_editora.getText().trim();
        String isbn = tf_isbn.getText().trim();

        // Validações de espaços vazios
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

        HashSet<Genero> generosSelecionados = new HashSet<Genero>(lv_generos.getSelectionModel().getSelectedItems());

        if (generosSelecionados.isEmpty()) {
            exibirAlerta("Erro", "Selecione pelo menos um gênero.");
            return;
        }

        boolean sucesso = livroController.cadastrarLivro(titulo, generosSelecionados, ano, visto, autor, editora, isbn, exemplar);

        if (sucesso) {
            livroController.salvarLivros();
            exibirAlerta("Sucesso", "Livro cadastrado com sucesso!");
            limparCampos();
        } else {
            exibirAlerta("Erro", "Não foi possível cadastrar o livro.");
        }

        limparCampos();
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

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
        lv_generos.getSelectionModel().clearSelection();
    }

@FXML
    void clicarRetornar(ActionEvent event) {
        DiarioCultural.changeScene("/telas/tela_principal.fxml");
    }

}
