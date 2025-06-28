package principal.controller.serieC;

import Model.Serie;
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
import static principal.DiarioCultural.serieController;

public class tela_busca_serieController implements Initializable {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @FXML
    private TableColumn<Serie, String> anoEncerramento;

    @FXML
    private TableColumn<Serie, String> anoLancamento;

    @FXML
    private Button bt_buscar;

    @FXML
    private Button bt_retornar;

    @FXML
    private ToggleGroup categoria;

    @FXML
    private TableColumn<Serie, String> dataVisto;

    @FXML
    private TableColumn<Serie, String> elenco;

    @FXML
    private TableColumn<Serie, String> id;

    @FXML
    private TableColumn<Serie, String> ondeAssistir;

    @FXML
    private TableColumn<Serie, String> pontuacao;

    @FXML
    private RadioButton rb_anoLancamento;

    @FXML
    private RadioButton rb_ator;

    @FXML
    private RadioButton rb_genero;

    @FXML
    private RadioButton rb_id;

    @FXML
    private RadioButton rb_ondeAssistir;

    @FXML
    private RadioButton rb_titulo;

    @FXML
    private TableView<Serie> tb_series;

    @FXML
    private TableColumn<Serie, String> temporadas;

    @FXML
    private TextField tf_filtro;

    @FXML
    private TableColumn<Serie, String> titulo;

    @FXML
    private TableColumn<Serie, String> tituloOriginal;

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
        } else if (rb_ondeAssistir.isSelected()) {
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
            TreeSet<Serie> series = serieController.buscarSeries(categoriaSelecionada, filtro);
            ObservableList<Serie> listaObservable = FXCollections.observableArrayList(series);
            tb_series.setItems(listaObservable);
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR, "Erro ao buscar series: " + e.getMessage());
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
    void selecionarOndeAssistir(ActionEvent event) {

    }

    @FXML
    void selecionarTitulo(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColunas();
    }

    private void configurarColunas() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        anoLancamento.setCellValueFactory(new PropertyValueFactory<>("anoLancamento"));
        anoEncerramento.setCellValueFactory(cellData -> {
            int encerramento = cellData.getValue().getAnoEncerramento();
            return new SimpleStringProperty((encerramento != 0) ? String.valueOf(encerramento) : " - ");
        });
        pontuacao.setCellValueFactory(new PropertyValueFactory<>("pontuacao"));

        dataVisto.setCellValueFactory(cellData -> {
            Calendar data = cellData.getValue().getDataVisto();
            String formatado = (data != null) ? sdf.format(data.getTime()) : "N/A";
            return new SimpleStringProperty(formatado);
        });

        // Título com Tooltip
        titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        titulo.setCellFactory(col -> new TableCell<Serie, String>() {
            private final Tooltip tooltip = new Tooltip();

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setTooltip(null);
                } else {
                    setText(item.length() > 30 ? item.substring(0, 27) + "..." : item);
                    tooltip.setText(item);
                    setTooltip(tooltip);
                }
            }
        });

        // Título Original com Tooltip
        tituloOriginal.setCellValueFactory(new PropertyValueFactory<>("tituloOriginal"));
        tituloOriginal.setCellFactory(col -> new TableCell<Serie, String>() {
            private final Tooltip tooltip = new Tooltip();

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setTooltip(null);
                } else {
                    setText(item.length() > 30 ? item.substring(0, 27) + "..." : item);
                    tooltip.setText(item);
                    setTooltip(tooltip);
                }
            }
        });

        // Temporadas (com Tooltip sobre toString)
        temporadas.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTemporadas().toString()));
        temporadas.setCellFactory(col -> new TableCell<Serie, String>() {
            private final Tooltip tooltip = new Tooltip();

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setTooltip(null);
                } else {
                    setText(item.length() > 30 ? item.substring(0, 27) + "..." : item);
                    tooltip.setText(item);
                    setTooltip(tooltip);
                }
            }
        });

        // Elenco (Set<String>) com tooltip
        elenco.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.join(", ", cellData.getValue().getElenco())));
        elenco.setCellFactory(column -> new TableCell<Serie, String>() {
            private final Tooltip tooltip = new Tooltip();

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setTooltip(null);
                } else {
                    setText(item.length() > 30 ? item.substring(0, 27) + "..." : item);
                    tooltip.setText(item);
                    setTooltip(tooltip);
                }
            }
        });

        // Onde Assistir (Set<String>) com tooltip
        ondeAssistir.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.join(", ", cellData.getValue().getOndeAssistir())));
        ondeAssistir.setCellFactory(column -> new TableCell<Serie, String>() {
            private final Tooltip tooltip = new Tooltip();

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setTooltip(null);
                } else {
                    setText(item.length() > 30 ? item.substring(0, 27) + "..." : item);
                    tooltip.setText(item);
                    setTooltip(tooltip);
                }
            }
        });
    }
}

