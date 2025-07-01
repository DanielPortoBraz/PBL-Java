package View_GUI;

import Controller.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe View_GUI da aplicação Diário Cultural.
 *
 * <p>Responsável por inicializar o JavaFX, importar os dados armazenados
 * (ou criá-los, caso inexistentes), e carregar a interface gráfica inicial.
 * Também fornece um método utilitário para trocar a cena exibida na janela View_GUI.
 *
 * <p>A aplicação permite ao usuário cadastrar, avaliar, buscar, listar e remover
 * livros, filmes e séries.
 *
 * @author [Seu Nome]
 */
public class DiarioCultural extends Application {

    /**
     * Janela View_GUI da aplicação.
     */
    public static Stage janela;

    /**
     * Cena View_GUI da aplicação, que será trocada dinamicamente.
     */
    private static Scene cenaPrincipal;

    /**
     * Controlador responsável pelas operações com livros.
     */
    public static LivroController livroController = new LivroController();

    /**
     * Controlador responsável pelas operações com filmes.
     */
    public static FilmeController filmeController = new FilmeController();

    /**
     * Controlador responsável pelas operações com séries.
     */
    public static SerieController serieController = new SerieController();

    /**
     * Método de entrada da aplicação JavaFX.
     *
     * <p>Importa os dados salvos de livros, filmes e séries, ou os inicializa caso não existam,
     * e exibe a tela View_GUI.
     *
     * @param stage O palco View_GUI da aplicação.
     * @throws Exception se ocorrer erro ao carregar a interface gráfica.
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Carrega os dados de arquivos JSON
        if (!livroController.importarLivros())
            livroController.salvarLivros();

        if (!filmeController.importarFilmes())
            filmeController.salvarFilmes();

        if (!serieController.importarSeries())
            serieController.salvarSeries();

        janela = stage;

        // Carrega a tela inicial
        Parent raizInicial = FXMLLoader.load(getClass().getResource("/telas/tela_principal.fxml"));
        cenaPrincipal = new Scene(raizInicial);

        janela.setScene(cenaPrincipal);
        janela.setTitle("Diário Cultural");
        janela.show();
    }

    /**
     * Altera a cena atualmente exibida na janela View_GUI.
     *
     * @param caminhoFXML Caminho relativo do arquivo FXML que representa a nova interface.
     */
    public static void changeScene(String caminhoFXML) {
        try {
            Parent novoRoot = FXMLLoader.load(DiarioCultural.class.getResource(caminhoFXML));
            cenaPrincipal.setRoot(novoRoot);
        } catch (Exception e) {
            e.printStackTrace(); // imprime o stack trace no console
        }
    }
}
