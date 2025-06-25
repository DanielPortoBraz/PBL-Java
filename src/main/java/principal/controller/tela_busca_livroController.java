package principal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import static principal.DiarioCultural.changeScene;

public class tela_busca_livroController implements Initializable {

    @FXML
    private TableColumn<?, ?> ano;

    @FXML
    private TableColumn<?, ?> autor;

    @FXML
    private Button bt_buscar;

    @FXML
    private Button bt_retornar;

    @FXML
    private ToggleGroup categoria;

    @FXML
    private TableColumn<?, ?> dataVisto;

    @FXML
    private TableColumn<?, ?> editora;

    @FXML
    private TableColumn<?, ?> exemplar;

    @FXML
    private TableColumn<?, ?> genero;

    @FXML
    private TableColumn<?, ?> isbn;

    @FXML
    private TableColumn<?, ?> pontuacao;

    @FXML
    private RadioButton rb_anoPublicacao;

    @FXML
    private RadioButton rb_autor;

    @FXML
    private RadioButton rb_genero;

    @FXML
    private RadioButton rb_isbn;

    @FXML
    private RadioButton rb_titulo;

    @FXML
    private TableColumn<?, ?> review;

    @FXML
    private TableView<?> tb_livros;

    @FXML
    private TextField tf_filtro;

    @FXML
    private TableColumn<?, ?> titulo;

    @FXML
    private TableColumn<?, ?> visto;

    @FXML
    void clicarBuscar(ActionEvent event) {

    }

    @FXML
    void clicarRetornar(ActionEvent event) {
        changeScene("principal");
    }

    @FXML
    void selecionarAnoPublicacao(ActionEvent event) {

    }

    @FXML
    void selecionarAutor(ActionEvent event) {

    }

    @FXML
    void selecionarGenero(ActionEvent event) {

    }

    @FXML
    void selecionarIsbn(ActionEvent event) {

    }

    @FXML
    void selecionarTitulo(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
