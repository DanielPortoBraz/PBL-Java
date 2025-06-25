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
    private static Scene telaAvaliacaoLivro;
    private static Scene telaListaLivro;
    private static Scene telaBuscaLivro;

    public static LivroController livroController = new LivroController();
    public static FilmeController filmeController = new FilmeController();
    public static SerieController serieController = new SerieController();

    @Override
    public void start(Stage stage) throws Exception {

        // Tenta importar os arquivos de cada registro
        if (!livroController.importarLivros()) // Se não conseguir importar, cria um novo arquivo "livros.json"
            livroController.salvarLivros();
        if (!filmeController.importarFilmes()) // Se não conseguir importar, cria um novo arquivo "filmes.json"
            filmeController.salvarFilmes();
        if (!serieController.importarSeries()) // Se não conseguir importar, cria um novo arquivo "series.json
            serieController.salvarSeries();

        janela = stage;

        Parent tela1 = FXMLLoader.load(getClass().getResource("/telas/tela_principal.fxml"));
        telaPrincipal = new Scene(tela1);

        Parent tela2 = FXMLLoader.load(getClass().getResource("/telas/livro/tela_cadastro_livro.fxml"));
        telaCadastroLivro = new Scene(tela2);

        Parent tela3 = FXMLLoader.load(getClass().getResource("/telas/livro/tela_avaliacao_livro.fxml"));
        telaAvaliacaoLivro = new Scene(tela3);

        Parent tela4 = FXMLLoader.load(getClass().getResource("/telas/livro/tela_lista_livro.fxml"));
        telaListaLivro = new Scene(tela4);

        Parent tela5 = FXMLLoader.load(getClass().getResource("/telas/livro/tela_busca_livro.fxml"));
        telaBuscaLivro = new Scene(tela5);

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

            case "avaliacao_livro":
                janela.setScene(telaAvaliacaoLivro);
                break;

            case "lista_livro":
                janela.setScene(telaListaLivro);
                break;

            case "busca_livro":
                janela.setScene(telaBuscaLivro);
                break;

            default:
                break;
        }
    }
}