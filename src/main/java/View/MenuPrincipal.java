package View;

import Controller.*;
import java.util.Scanner;

/**
 * Classe MenuPrincipal que implementa a interface {@code Menu}.
 * <p>
 * Esta classe representa o menu principal do sistema, permitindo o acesso às
 * funcionalidades de Cadastro, Avaliação, Busca e Listagem de mídias.
 * O menu principal orienta o usuário a selecionar uma opção e, conforme a escolha,
 * redireciona para o menu correspondente.
 * </p>
 *
 * @see MenuCadastro
 * @see MenuAvaliacao
 * @see MenuBusca
 * @see MenuLista
 */
public class MenuPrincipal implements Menu {
    private Scanner scanner;

    protected LivroController livroController;
    protected FilmeController filmeController;
    protected SerieController serieController;

    /**
     * Constrói uma instância de {@code MenuPrincipal} com os controllers necessários para as operações do sistema.
     *
     * @param scanner          Instância de {@code Scanner} para leitura da entrada do usuário.
     * @param livroController  Controller responsável pelas operações com livros.
     * @param filmeController  Controller responsável pelas operações com filmes.
     * @param serieController  Controller responsável pelas operações com séries.
     */
    public MenuPrincipal(Scanner scanner, LivroController livroController,
                         FilmeController filmeController,
                         SerieController serieController) {
        this.scanner = scanner;
        this.livroController = livroController;
        this.filmeController = filmeController;
        this.serieController = serieController;
    }

    /**
     * Exibe o menu principal no terminal e processa as opções selecionadas pelo usuário.
     *
     * As opções disponíveis são:
     * <ul>
     *   <li>"1" - Acessar o menu de Cadastro.</li>
     *   <li>"2" - Acessar o menu de Avaliação.</li>
     *   <li>"3" - Acessar o menu de Busca.</li>
     *   <li>"4" - Acessar o menu de Listagem.</li>
     *   <li>"5" - Acessar o menu de Remoção.</li>
     *   <li>"6" - Sair do sistema.</li>
     * </ul>
     * Dependendo da opção escolhida, o método instancia e exibe o menu correspondente.
     *
     */
    @Override
    public void exibir() {
        // Tenta importar os arquivos de cada registro
        if (!livroController.importarLivros()) // Se não conseguir importar, cria um novo arquivo "livros.json"
            livroController.salvarLivros();
        if (!filmeController.importarFilmes()) // Se não conseguir importar, cria um novo arquivo "filmes.json"
            filmeController.salvarFilmes();
        if (!serieController.importarSeries()) // Se não conseguir importar, cria um novo arquivo "series.json
            serieController.salvarSeries();

        String opcao;
        do {
            System.out.println("\n-- MENU PRINCIPAL --");
            System.out.println("1- Cadastrar");
            System.out.println("2- Avaliar");
            System.out.println("3- Buscar");
            System.out.println("4- Listar");
            System.out.println("5- Remover");
            System.out.println("6- Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    new MenuCadastro(scanner, livroController, filmeController, serieController).exibir();
                    break;
                case "2":
                    new MenuAvaliacao(scanner, livroController, filmeController, serieController).exibir();
                    break;
                case "3":
                    new MenuBusca(scanner, livroController, filmeController, serieController).exibir();
                    break;
                case "4":
                    new MenuLista(scanner, livroController, filmeController, serieController).exibir();
                    break;
                case "5":
                    new MenuRemocao(scanner, livroController, filmeController, serieController).exibir();
                    break;
                case "6":
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (!opcao.equals("6"));
    }
}
