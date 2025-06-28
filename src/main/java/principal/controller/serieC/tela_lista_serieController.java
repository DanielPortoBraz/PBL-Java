package principal.controller.serieC;

import Model.Serie;
import Model.Temporada;
import javafx.beans.property.SimpleIntegerProperty;
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
import java.util.Set;

import static principal.DiarioCultural.*;

public class tela_lista_serieController implements Initializable {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @FXML
    private TableColumn<Serie, Integer> anoLancamento;

    @FXML
    private Button bt_retornar;

    @FXML
    private TableColumn<Serie, String> dataVisto;

    @FXML
    private TableColumn<Serie, String> elenco;

    @FXML
    private TableColumn<Serie, Number> nTemporadas;

    @FXML
    private TableColumn<Serie, Integer> id;

    @FXML
    private TableColumn<Serie, Integer> pontuacao;

    @FXML
    private TableView<Serie> tb_series;

    @FXML
    private TableColumn<Serie, String> titulo;

    @FXML
    void clicarRetornar(ActionEvent event) {
        changeScene("/telas/tela_principal.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColunas();
        carregarSeries();
    }

    private void configurarColunas() {
        pontuacao.setCellValueFactory(new PropertyValueFactory<>("pontuacao"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        anoLancamento.setCellValueFactory(new PropertyValueFactory<>("anoLancamento"));
        nTemporadas.setCellValueFactory(cellData -> {
            Serie serie = cellData.getValue(); // Utiliza a série da mesma linha na Tabela
            int quantidade = (serie != null && serie.getTemporadas() != null)
                    ? serie.getTemporadas().size()
                    : 0;
            return new SimpleIntegerProperty(quantidade);
        });

        dataVisto.setCellValueFactory(cellData -> {
            Calendar data = cellData.getValue().getDataVisto();
            String formatado = (data != null) ? sdf.format(data.getTime()) : "N/A";
            return new SimpleStringProperty(formatado);
        });

        // Conversão do HashSet<String> para String nos atributos título e elenco

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

        elenco.setCellValueFactory(cellData -> {
            Set<String> elencoSet = cellData.getValue().getElenco();
            String elencoStr = (elencoSet != null) ? String.join(", ", elencoSet) : ""; // Concatena Strings separadas por vírgula
            return new SimpleStringProperty(elencoStr);
        });
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
    }

    public void carregarSeries() {
        ObservableList<Serie> series = FXCollections.observableArrayList(serieController.getSeriesR().getSeries());
        tb_series.setItems(series);
    }
}
