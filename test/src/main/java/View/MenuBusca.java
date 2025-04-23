package View;

import Controller.*;
import java.util.Scanner;

/**
 * Classe MenuBusca que implementa a interface Menu para fornecer funcionalidades de
 * busca interativas para livros, filmes e séries.
 * <p>
 * Esta classe apresenta um menu no terminal permitindo ao usuário escolher a mídia a ser buscada,
 * bem como a categoria e o filtro de busca. Dependendo da opção escolhida, o menu solicita as
 * informações necessárias e invoca o respectivo controller para realizar a busca.
 * </p>
 *
 * @see Menu
 */
public class MenuBusca implements Menu {
    private Scanner scanner;

    protected LivroController livroController;
    protected FilmeController filmeController;
    protected SerieController serieController;

    /**
     * Cria uma instância de MenuBusca com o scanner e os controllers responsáveis pelas operações
     * de busca de livros, filmes e séries.
     *
     * @param scanner          Scanner para leitura de entrada do usuário.
     * @param livroController  Controller responsável pelas operações de busca de livros.
     * @param filmeController  Controller responsável pelas operações de busca de filmes.
     * @param serieController  Controller responsável pelas operações de busca de séries.
     */
    public MenuBusca(Scanner scanner, LivroController livroController,
                     FilmeController filmeController,
                     SerieController serieController) {
        this.scanner = scanner;
        this.livroController = livroController;
        this.filmeController = filmeController;
        this.serieController = serieController;
    }

    /**
     * Exibe o menu de busca no terminal e processa as ações do usuário.
     * <p>
     * O menu apresenta as seguintes opções:
     * <ul>
     *   <li>"1" para busca de livros, permitindo filtrar por Título, Autor, Gênero, Ano ou ISBN.</li>
     *   <li>"2" para busca de filmes, permitindo filtrar por Título, Ator, Gênero, Ano, Diretor ou ID.</li>
     *   <li>"3" para busca de séries, permitindo filtrar por Título, Ator, Gênero, Ano, Onde assistir ou ID.</li>
     *   <li>"4" para voltar (encerrar o menu).</li>
     * </ul>
     * Para filtros que esperam valores numéricos (como Ano ou ID), se o usuário fornecer um valor não inteiro,
     * uma mensagem de erro será exibida.
     * </p>
     */
    @Override
    public void exibir() {
        String opcao;
        String filtro = "", categoria;
        int filtroNum;
        boolean encontrou;

        do {
            System.out.println("\n-- MENU DE BUSCA --");
            System.out.println("1- Livro");
            System.out.println("2- Filme");
            System.out.println("3- Série");
            System.out.println("4- Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.println("\n<<< BUSCA DE LIVRO >>>");
                    System.out.println("--- CATEGORIAS ---\n" +
                            "1- Título\n" +
                            "2- Autor\n" +
                            "3- Gênero\n" +
                            "4- Ano\n" +
                            "5- ISBN");
                    System.out.println("Digite a categoria: ");
                    categoria = scanner.nextLine();

                    try {
                        System.out.println("Digite a sua busca: ");
                        filtro = scanner.nextLine();

                        if (categoria.equalsIgnoreCase("4"))
                            filtroNum = Integer.parseInt(filtro);
                        encontrou = livroController.buscarLivros(categoria, filtro);

                        if (!encontrou)
                            System.out.println("Livro não encontrado.");

                    } catch (NumberFormatException e) {
                        System.out.println("Erro: Valor não inteiro fornecido.");
                    }

                    break;

                case "2":
                    System.out.println("\n<<< BUSCA DE FILME >>>");
                    System.out.println("--- CATEGORIAS ---\n" +
                            "1- Título\n" +
                            "2- Ator\n" +
                            "3- Gênero\n" +
                            "4- Ano\n" +
                            "5- Diretor\n" +
                            "6- ID\n");
                    System.out.println("Digite a categoria: ");
                    categoria = scanner.nextLine();

                    try {
                        System.out.println("Digite a sua busca: ");
                        filtro = scanner.nextLine();

                        if (categoria.equalsIgnoreCase("4") || categoria.equalsIgnoreCase("6"))
                            filtroNum = Integer.parseInt(filtro);
                        encontrou = filmeController.buscarFilmes(categoria, filtro);

                        if (!encontrou)
                            System.out.println("Filme não encontrado.");

                    } catch (NumberFormatException e) {
                        System.out.println("Erro: Valor não inteiro fornecido.");
                    }
                    break;

                case "3":
                    System.out.println("\n<<< BUSCA DE SÉRIE >>>");
                    System.out.println("--- CATEGORIAS ---\n" +
                            "1- Título\n" +
                            "2- Ator\n" +
                            "3- Gênero\n" +
                            "4- Ano\n" +
                            "5- Onde assistir\n" +
                            "6- ID\n");
                    System.out.println("Digite a categoria: ");
                    categoria = scanner.nextLine();

                    try {
                        System.out.println("Digite a sua busca: ");
                        filtro = scanner.nextLine();

                        if (categoria.equalsIgnoreCase("4") || categoria.equalsIgnoreCase("6"))
                            filtroNum = Integer.parseInt(filtro);
                        encontrou = serieController.buscarSeries(categoria, filtro);

                        if (!encontrou)
                            System.out.println("Série não encontrada.");

                    } catch (NumberFormatException e) {
                        System.out.println("Erro: Valor não inteiro fornecido.");
                    }
                    break;

                case "4":
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (!opcao.equals("4"));
    }
}
