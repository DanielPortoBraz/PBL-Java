package principal.controller.filmeC;

import Model.Filme;
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
import java.util.ResourceBundle;
import java.util.TreeSet;

import static principal.DiarioCultural.changeScene;
import static principal.DiarioCultural.filmeController;

public class tela_busca_filmeController implements Initializable {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @FXML
    private TableColumn<Filme, String> ano;

    @FXML
    private Button bt_buscar;

    @FXML
    private Button bt_retornar;

    @FXML
    private ToggleGroup categoria;

    @FXML
    private TableColumn<Filme, String> dataVisto;

    @FXML
    private TableColumn<Filme, String> direcao;

    @FXML
    private TableColumn<Filme, String> duracao;

    @FXML
    private TableColumn<Filme, String> elenco;

    @FXML
    private TableColumn<Filme, String> id;

    @FXML
    private TableColumn<Filme, String> ondeAssistir;

    @FXML
    private TableColumn<Filme, String> pontuacao;

    @FXML
    private RadioButton rb_anoLancamento;

    @FXML
    private RadioButton rb_ator;

    @FXML
    private RadioButton rb_diretor;

    @FXML
    private RadioButton rb_genero;

    @FXML
    private RadioButton rb_id;

    @FXML
    private RadioButton rb_titulo;

    @FXML
    private TableColumn<Filme, String> roteiro;

    @FXML
    private TableView<Filme> tb_filmes;

    @FXML
    private TextField tf_filtro;

    @FXML
    private TableColumn<Filme, String> titulo;

    @FXML
    private TableColumn<Filme, String> tituloOriginal;

    @FXML
    void clicarBuscar(ActionEvent event) {
        String filtro = tf_filtro.getText().trim();
        int categoriaSelecionada = 0;

        if (rb_titulo.isSelected()) {
            categoriaSelecionada = 1;
        } else if (rb_ator.isSelected()) {
            categoriaSelecionada = 2;
        } else if (rb_genero.isSelected()) {
            categoriaSelecionada = 3;
        } else if (rb_anoLancamento.isSelected()) {
            categoriaSelecionada = 4;
        } else if (rb_diretor.isSelected()) {
            categoriaSelecionada = 5;
        } else if (rb_id.isSelected()) {
            categoriaSelecionada = 6;
        }

        if (filtro.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Digite um valor para buscar.");
            alerta.show();
            return;
        }

        try {
            TreeSet<Filme> filmes = filmeController.buscarFilmes(categoriaSelecionada, filtro);
            ObservableList<Filme> listaObservable = FXCollections.observableArrayList(filmes);
            tb_filmes.setItems(listaObservable);
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR, "Erro ao buscar filmes: " + e.getMessage());
            alerta.show();
        }
    }


    @FXML
    void clicarRetornar(ActionEvent event) {
        changeScene("/telas/tela_principal.fxml");
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
        configurarColunas();
    }

    private void configurarColunas() {
        titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tituloOriginal.setCellValueFactory(new PropertyValueFactory<>("tituloOriginal"));
        ano.setCellValueFactory(new PropertyValueFactory<>("anoLancamento"));
        duracao.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTempoDuracao() + " min"));
        direcao.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.join(", ", cellData.getValue().getDirecao())));
        roteiro.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.join(", ", cellData.getValue().getRoteiro())));
        elenco.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.join(", ", cellData.getValue().getElenco())));
        ondeAssistir.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.join(", ", cellData.getValue().getOndeAssistir())));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        pontuacao.setCellValueFactory(new PropertyValueFactory<>("pontuacao"));
        dataVisto.setCellValueFactory(cellData -> {
            Calendar data = cellData.getValue().getDataVisto();
            String formatado = (data != null) ? sdf.format(data.getTime()) : "N/A";
            return new SimpleStringProperty(formatado);
        });
    }
}
