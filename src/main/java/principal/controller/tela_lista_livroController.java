package principal.controller;

import Model.LivroRepositorio;
import com.fasterxml.jackson.databind.ser.impl.PropertyBasedObjectIdGenerator;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;

import static principal.DiarioCultural.changeScene;
import static principal.DiarioCultural.livroController;
import Model.Livro;

public class tela_lista_livroController implements Initializable {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @FXML
    private Button bt_retornar;

    @FXML
    private TableColumn<Livro, String> ano;

    @FXML
    private TableColumn<Livro, String> autor;

    @FXML
    private TableColumn<Livro, String> dataVisto;

    @FXML
    private TableColumn<Livro, String> editora;

    @FXML
    private TableColumn<Livro, String> isbn;

    @FXML
    private TableColumn<Livro, Integer> pontuacao;

    @FXML
    private TableColumn<Livro, String> titulo;

    @FXML
    private TableView<Livro> tb_livros;

    @FXML
    void clicarRetornar(ActionEvent event) {
        changeScene("principal");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColunas();
        carregarLivros();
    }

    private void configurarColunas() {
        titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        autor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        editora.setCellValueFactory(new PropertyValueFactory<>("editora"));
        isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        ano.setCellValueFactory(new PropertyValueFactory<>("anoLancamento"));
        pontuacao.setCellValueFactory(new PropertyValueFactory<>("pontuacao"));
        dataVisto.setCellValueFactory(cellData -> {
            Calendar data = cellData.getValue().getDataVisto();
            String formatado = (data != null) ? sdf.format(data.getTime()) : "N/A";
            return new SimpleStringProperty(formatado);
        });

    }

    private void carregarLivros() {
        ObservableList<Livro> livros = FXCollections.observableArrayList(livroController.getLivrosR().getLivros());
        tb_livros.setItems(livros);
    }
}
