package principal;

import Controller.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DiarioCultural extends Application {
    private static Stage janela;
    private static Scene telaPrincipal;
    private static Scene telaCadastroLivro;
    public static LivroController livroController = new LivroController();
    public static FilmeController filmeController = new FilmeController();
    public static SerieController serieController = new SerieController();

    @Override
    public void start(Stage stage) throws Exception {
        janela = stage;

        Parent tela1 = FXMLLoader.load(getClass().getResource("/telas/tela_principal.fxml"));
        telaPrincipal = new Scene(tela1);

        Parent tela2 = FXMLLoader.load(getClass().getResource("/telas/tela_cadastro_livro.fxml"));
        telaCadastroLivro = new Scene(tela2);

        janela.setScene(telaPrincipal);
        janela.show();
    }

    public static void changeScene(String str){
        switch(str){
            case "principal":
                janela.setScene(telaPrincipal);
                break;
            case "cadastro_livro":
                janela.setScene(telaCadastroLivro);
                break;
        }
    }
}