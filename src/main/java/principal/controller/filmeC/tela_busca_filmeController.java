package principal.controller.filmeC;

import Model.Filme;
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
    private TableColumn<Filme, String> genero;

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
    private TableColumn<Filme, String> review;

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
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        pontuacao.setCellValueFactory(new PropertyValueFactory<>("pontuacao"));
        dataVisto.setCellValueFactory(cellData -> {
            Calendar data = cellData.getValue().getDataVisto();
            String formatado = (data != null) ? sdf.format(data.getTime()) : "N/A";
            return new SimpleStringProperty(formatado);
        });

        // Título com Tooltip
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

        tituloOriginal.setCellValueFactory(new PropertyValueFactory<>("tituloOriginal"));
        tituloOriginal.setCellFactory(col -> new TableCell<Filme, String>() {
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

        ano.setCellValueFactory(new PropertyValueFactory<>("anoLancamento"));
        duracao.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTempoDuracao() + " min"));

        direcao.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.join(", ", cellData.getValue().getDirecao())));
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

        roteiro.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.join(", ", cellData.getValue().getRoteiro())));
        roteiro.setCellFactory(column -> new TableCell<Filme, String>() {
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

        elenco.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.join(", ", cellData.getValue().getElenco())));
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

        ondeAssistir.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.join(", ", cellData.getValue().getOndeAssistir())));
        ondeAssistir.setCellFactory(column -> new TableCell<Filme, String>() {
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

        review.setCellValueFactory(new PropertyValueFactory<>("review"));
        review.setCellFactory(column -> new TableCell<Filme, String>() {
            private final Tooltip tooltip = new Tooltip();

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.isBlank()) {
                    setText(null);
                    setTooltip(null);
                } else {
                    setText(item.length() > 30 ? item.substring(0, 27) + "..." : item);
                    tooltip.setText(item);
                    setTooltip(tooltip);
                }
            }
        });

        genero.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.join(", ", cellData.getValue().getGenero().toString())));
        genero.setCellFactory(column -> new TableCell<Filme, String>() {
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
