package principal;

import Controller.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DiarioCultural extends Application {

    public static Stage janela;
    private static Scene cenaPrincipal;

    public static LivroController livroController = new LivroController();
    public static FilmeController filmeController = new FilmeController();
    public static SerieController serieController = new SerieController();

    @Override
    public void start(Stage stage) throws Exception {
        // Inicializa os dados
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

    public static void changeScene(String caminhoFXML) {
        try {
            Parent novoRoot = FXMLLoader.load(DiarioCultural.class.getResource(caminhoFXML));
            cenaPrincipal.setRoot(novoRoot);
        } catch (Exception e) {
            e.printStackTrace(); // imprime o stack trace no console
        }
    }
}
