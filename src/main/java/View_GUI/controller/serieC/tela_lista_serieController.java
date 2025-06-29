package View_GUI.controller.serieC;

import Model.Serie;
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

import static View_GUI.DiarioCultural.*;

/**
 * Controlador da interface para listagem de séries.
 * Configura a tabela que exibe informações detalhadas das séries,
 * como título, elenco, número de temporadas, pontuação, data de avaliação,
 * ano de lançamento e identificador.
 *
 * Fornece funcionalidade para retorno à tela View_GUI.
 */
public class tela_lista_serieController implements Initializable {

    /**
     * Formato para exibição das datas no padrão "dd/MM/yyyy".
     */
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

    /**
     * Trata o evento de clique no botão "Retornar",
     * que muda a cena para a tela View_GUI.
     *
     * @param event evento de clique
     */
    @FXML
    void clicarRetornar(ActionEvent event) {
        changeScene("/telas/tela_principal.fxml");
    }

    /**
     * Inicializa o controlador configurando as colunas da tabela
     * e carregando as séries para exibição.
     *
     * @param url URL do arquivo FXML (não utilizado)
     * @param resourceBundle recursos adicionais (não utilizado)
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColunas();
        carregarSeries();
    }

    /**
     * Configura o vínculo das colunas da tabela com as propriedades da classe Serie,
     * além de definir comportamentos visuais como tooltip e truncamento para textos longos.
     */
    private void configurarColunas() {
        pontuacao.setCellValueFactory(new PropertyValueFactory<>("pontuacao"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        anoLancamento.setCellValueFactory(new PropertyValueFactory<>("anoLancamento"));

        nTemporadas.setCellValueFactory(cellData -> {
            Serie serie = cellData.getValue();
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

        // Configuração da coluna título com tooltip e truncamento
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

        // Conversão e exibição do elenco com tooltip e truncamento
        elenco.setCellValueFactory(cellData -> {
            Set<String> elencoSet = cellData.getValue().getElenco();
            String elencoStr = (elencoSet != null) ? String.join(", ", elencoSet) : "";
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

    /**
     * Carrega a lista de séries do controlador View_GUI e popula a tabela da interface.
     */
    public void carregarSeries() {
        ObservableList<Serie> series = FXCollections.observableArrayList(serieController.getSeriesR().getSeries());
        tb_series.setItems(series);
    }
}
