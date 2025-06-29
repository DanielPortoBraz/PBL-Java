package principal.controller.livroC;

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

import static principal.DiarioCultural.changeScene;
import static principal.DiarioCultural.livroController;
import Model.Livro;

/**
 * Controlador da interface de listagem de livros.
 * Responsável por configurar a tabela que exibe os livros cadastrados,
 * carregar os dados da lista de livros e tratar a ação de retorno para a tela principal.
 */
public class tela_lista_livroController implements Initializable {

    /**
     * Formato de data para exibição da data de leitura do livro.
     */
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

    /**
     * Evento acionado ao clicar no botão "Retornar".
     * Muda a cena para a tela principal da aplicação.
     *
     * @param event Evento de clique do botão
     */
    @FXML
    void clicarRetornar(ActionEvent event) {
        changeScene("/telas/tela_principal.fxml");
    }

    /**
     * Inicializa o controlador, configurando as colunas da tabela
     * e carregando os livros para exibição.
     *
     * @param url            URL da localização do arquivo FXML (não utilizado)
     * @param resourceBundle Recursos adicionais (não utilizado)
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColunas();
        carregarLivros();
    }

    /**
     * Configura os vinculadores (cell value factories) das colunas da tabela de livros,
     * definindo qual propriedade do objeto Livro será exibida em cada coluna.
     * Também formata a data da leitura para exibição amigável.
     */
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

    /**
     * Carrega os livros cadastrados no controlador principal e popula a tabela.
     */
    public void carregarLivros() {
        ObservableList<Livro> livros = FXCollections.observableArrayList(livroController.getLivrosR().getLivros());
        tb_livros.setItems(livros);
    }
}
