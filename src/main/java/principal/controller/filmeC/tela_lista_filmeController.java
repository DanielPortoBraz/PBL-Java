package principal.controller.filmeC;

import Model.Filme;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import static principal.DiarioCultural.*;

public class tela_lista_filmeController implements Initializable {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @FXML
    private TableColumn<Filme, Integer> ano;

    @FXML
    private Button bt_retornar;

    @FXML
    private TableColumn<Filme, String> dataVisto;

    @FXML
    private TableColumn<Filme, String> direcao;

    @FXML
    private TableColumn<Filme, Integer> duracao;

    @FXML
    private TableColumn<Filme, String> elenco;

    @FXML
    private TableColumn<Filme, Integer> id;

    @FXML
    private TableColumn<Filme, Integer> pontuacao;

    @FXML
    private TableView<Filme> tb_filmes;

    @FXML
    private TableColumn<Filme, String> titulo;

    @FXML
    void clicarRetornar(ActionEvent event) {
        changeScene("/telas/tela_principal.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColunas();
        carregarFilmes();
    }

    private void configurarColunas() {
        pontuacao.setCellValueFactory(new PropertyValueFactory<>("pontuacao"));
        titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        direcao.setCellValueFactory(new PropertyValueFactory<>("direcao"));
        elenco.setCellValueFactory(new PropertyValueFactory<>("elenco"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        ano.setCellValueFactory(new PropertyValueFactory<>("anoLancamento"));
        duracao.setCellValueFactory(new PropertyValueFactory<>("tempoDuracao"));
        dataVisto.setCellValueFactory(cellData -> {
            Calendar data = cellData.getValue().getDataVisto();
            String formatado = (data != null) ? sdf.format(data.getTime()) : "N/A";
            return new SimpleStringProperty(formatado);
        });
    }

    public void carregarFilmes() {
        ObservableList<Filme> filmes = FXCollections.observableArrayList(filmeController.getFilmesR().getFilmes());
        tb_filmes.setItems(filmes);
    }
}