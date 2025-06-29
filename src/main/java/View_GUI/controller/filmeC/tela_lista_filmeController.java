package View_GUI.controller.filmeC;

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
import java.util.Set;

import static View_GUI.DiarioCultural.*;

/**
 * Controlador da interface de listagem de filmes.
 * Responsável por configurar e popular a tabela de filmes exibindo
 * informações detalhadas de cada filme, como título, direção, elenco, duração,
 * ano de lançamento, pontuação, data de avaliação e identificador.
 *
 * Também gerencia o evento de retorno à tela View_GUI.
 */
public class tela_lista_filmeController implements Initializable {

    /**
     * Formato para exibir datas no padrão dd/MM/yyyy.
     */
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
    private TableColumn<Filme, String> duracao;

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

    /**
     * Trata o evento de clique no botão "Retornar", que muda a cena para a tela View_GUI.
     *
     * @param event evento de clique do botão
     */
    @FXML
    void clicarRetornar(ActionEvent event) {
        changeScene("/telas/tela_principal.fxml");
    }

    /**
     * Inicializa o controlador configurando as colunas da tabela e carregando os filmes.
     *
     * @param url URL do arquivo FXML (não utilizado)
     * @param resourceBundle recursos adicionais (não utilizado)
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColunas();
        carregarFilmes();
    }

    /**
     * Configura os vinculadores das colunas da tabela para exibir as propriedades
     * dos filmes, incluindo tratamento especial para campos de texto longo
     * e conversão de conjuntos em strings separadas por vírgula.
     */
    private void configurarColunas() {
        pontuacao.setCellValueFactory(new PropertyValueFactory<>("pontuacao"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        ano.setCellValueFactory(new PropertyValueFactory<>("anoLancamento"));

        duracao.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTempoDuracao() + " min"));

        dataVisto.setCellValueFactory(cellData -> {
            Calendar data = cellData.getValue().getDataVisto();
            String formatado = (data != null) ? sdf.format(data.getTime()) : "N/A";
            return new SimpleStringProperty(formatado);
        });

        // Configuração para o título com tooltip e truncamento para exibição
        titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        titulo.setCellFactory(col -> new TableCell<Filme, String>() {
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

        // Conversão e exibição da direção com tooltip e truncamento
        direcao.setCellValueFactory(cellData -> {
            Set<String> direcaoSet = cellData.getValue().getDirecao();
            String direcaoStr = (direcaoSet != null) ? String.join(", ", direcaoSet) : "";
            return new SimpleStringProperty(direcaoStr);
        });
        direcao.setCellFactory(column -> new TableCell<Filme, String>() {
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
        elenco.setCellFactory(column -> new TableCell<Filme, String>() {
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
     * Carrega a lista de filmes do controlador View_GUI e popula a tabela da interface.
     */
    public void carregarFilmes() {
        ObservableList<Filme> filmes = FXCollections.observableArrayList(filmeController.getFilmesR().getFilmes());
        tb_filmes.setItems(filmes);
    }
}
