package View;

import Controller.FilmeController;
import Controller.LivroController;
import Controller.SerieController;
import java.util.Scanner;

/**
 * Classe MenuLista que implementa a interface {@code Menu} para exibir opções de listagem
 * de mídias no terminal.
 * <p>
 * Essa classe apresenta um menu interativo, onde o usuário pode escolher listar Livros, Filmes ou Séries,
 * invocando os controllers responsáveis por cada operação.
 * </p>
 *
 * @see Menu
 */
public class MenuLista implements Menu {
    private Scanner scanner;

    protected LivroController livroController;
    protected FilmeController filmeController;
    protected SerieController serieController;

    /**
     * Constrói uma instância de MenuLista com os componentes necessários para a listagem de mídias.
     *
     * @param scanner          Instância de {@code Scanner} utilizada para ler a entrada do usuário.
     * @param livroController  Controller responsável pelas operações relacionadas a livros.
     * @param filmeController  Controller responsável pelas operações relacionadas a filmes.
     * @param serieController  Controller responsável pelas operações relacionadas a séries.
     */
    public MenuLista(Scanner scanner, LivroController livroController,
                     FilmeController filmeController,
                     SerieController serieController) {
        this.scanner = scanner;
        this.livroController = livroController;
        this.filmeController = filmeController;
        this.serieController = serieController;
    }

    /**
     * Exibe o menu de listagem no terminal e processa as opções selecionadas pelo usuário.
     *
     * O menu apresenta as seguintes opções:
     * <ul>
     *   <li>"1" - Listar Livros</li>
     *   <li>"2" - Listar Filmes</li>
     *   <li>"3" - Listar Séries</li>
     *   <li>"4" - Voltar</li>
     * </ul>
     * Para cada opção, o método invoca o controller correspondente para realizar a listagem.
     */
    @Override
    public void exibir() {
        String opcao;
        do {
            System.out.println("\n-- MENU DE LISTAGEM --");
            System.out.println("1- Livros");
            System.out.println("2- Filmes");
            System.out.println("3- Séries");
            System.out.println("4- Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.println("\n<<< LISTA DE LIVROS >>>");
                    livroController.listarLivros();
                    break;
                case "2":
                    System.out.println("\n<<< LISTA DE FILMES >>>");
                    filmeController.listarFilmes();
                    break;
                case "3":
                    System.out.println("\n<<< LISTA DE SÉRIES >>>");
                    serieController.listarSeries();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (!opcao.equals("4"));
    }
}
