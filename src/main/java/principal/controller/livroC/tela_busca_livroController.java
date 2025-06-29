package principal.controller.livroC;

import Model.Livro;

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
import static principal.DiarioCultural.livroController;

/**
 * Controlador da tela de busca de livros.
 * Permite ao usuário realizar buscas por diferentes categorias (título, autor, gênero, ano de publicação e ISBN),
 * exibindo os resultados em uma tabela com informações detalhadas.
 *
 * Inclui funcionalidades para configurar as colunas da tabela, formatar a exibição de dados,
 * e gerenciar eventos de busca e navegação para a tela principal.
 */
public class tela_busca_livroController implements Initializable {

    /**
     * Formato para exibição de datas no padrão "dd/MM/yyyy".
     */
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @FXML
    private TableColumn<Livro, Integer> ano;

    @FXML
    private TableColumn<Livro, String> autor;

    @FXML
    private Button bt_buscar;

    @FXML
    private Button bt_retornar;

    @FXML
    private ToggleGroup categoria;

    @FXML
    private TableColumn<Livro, String> dataVisto;

    @FXML
    private TableColumn<Livro, String> editora;

    @FXML
    private TableColumn<Livro, Integer> exemplar;

    @FXML
    private TableColumn<Livro, String> genero;

    @FXML
    private TableColumn<Livro, String> isbn;

    @FXML
    private TableColumn<Livro, Integer> pontuacao;

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
    private TableColumn<Livro, String> review;

    @FXML
    private TableView<Livro> tb_livros;

    @FXML
    private TextField tf_filtro;

    @FXML
    private TableColumn<Livro, String> titulo;

    @FXML
    private TableColumn<Livro, Boolean> visto;

    /**
     * Evento acionado ao clicar no botão de buscar.
     * Realiza a busca de livros conforme a categoria selecionada e o filtro informado,
     * atualizando a tabela com os resultados encontrados.
     *
     * Exibe alertas em caso de filtro vazio ou erro durante a busca.
     *
     * @param event evento do clique no botão buscar
     */
    @FXML
    void clicarBuscar(ActionEvent event) {
        String filtro = tf_filtro.getText().trim();
        int categoriaSelecionada = 0;

        if (rb_titulo.isSelected()) {
            categoriaSelecionada = 1;
        } else if (rb_autor.isSelected()) {
            categoriaSelecionada = 2;
        } else if (rb_genero.isSelected()) {
            categoriaSelecionada = 3;
        } else if (rb_anoPublicacao.isSelected()) {
            categoriaSelecionada = 4;
        } else if (rb_isbn.isSelected()) {
            categoriaSelecionada = 5;
        }

        if (filtro.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Digite um valor para buscar.");
            alerta.show();
            return;
        }

        try {
            TreeSet<Livro> livros = livroController.buscarLivros(categoriaSelecionada, filtro);
            ObservableList<Livro> listaObservable = FXCollections.observableArrayList(livros);
            tb_livros.setItems(listaObservable);
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR, "Erro ao buscar livros: " + e.getMessage());
            alerta.show();
        }
    }

    /**
     * Evento acionado ao clicar no botão retornar.
     * Retorna para a tela principal da aplicação.
     *
     * @param event evento do clique no botão retornar
     */
    @FXML
    void clicarRetornar(ActionEvent event) {
        changeScene("/telas/tela_principal.fxml");
    }

    @FXML
    void selecionarAnoPublicacao(ActionEvent event) {
        // Métodos vazios para serem usados se necessário para eventos dos radio buttons
    }

    @FXML
    void selecionarAutor(ActionEvent event) {}

    @FXML
    void selecionarGenero(ActionEvent event) {}

    @FXML
    void selecionarIsbn(ActionEvent event) {}

    @FXML
    void selecionarTitulo(ActionEvent event) {}

    /**
     * Inicializa o controlador configurando as colunas da tabela.
     *
     * @param url URL do arquivo FXML (não utilizado)
     * @param resourceBundle recursos adicionais (não utilizado)
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColunas();
    }

    /**
     * Configura as colunas da tabela, vinculando as propriedades dos livros
     * aos seus respectivos componentes visuais e configurando tooltips para textos longos.
     */
    private void configurarColunas() {
        pontuacao.setCellValueFactory(new PropertyValueFactory<>("pontuacao"));
        ano.setCellValueFactory(new PropertyValueFactory<>("anoLancamento"));
        exemplar.setCellValueFactory(new PropertyValueFactory<>("exemplar"));
        visto.setCellValueFactory(new PropertyValueFactory<>("visto"));

        dataVisto.setCellValueFactory(cellData -> {
            Calendar data = cellData.getValue().getDataVisto();
            String formatado = (data != null) ? sdf.format(data.getTime()) : "N/A";
            return new SimpleStringProperty(formatado);
        });

        isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        isbn.setCellFactory(column -> new TableCell<Livro, String>() {
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

        titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        titulo.setCellFactory(column -> new TableCell<Livro, String>() {
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

        autor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        autor.setCellFactory(column -> new TableCell<Livro, String>() {
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

        editora.setCellValueFactory(new PropertyValueFactory<>("editora"));
        editora.setCellFactory(column -> new TableCell<Livro, String>() {
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
        genero.setCellFactory(column -> new TableCell<Livro, String>() {
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
        review.setCellFactory(column -> new TableCell<Livro, String>() {
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
    }
}
