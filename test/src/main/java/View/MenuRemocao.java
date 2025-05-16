package View;
import Controller.*;
import java.util.Scanner;


public class MenuRemocao implements Menu{
    private Scanner scanner;

    protected LivroController livroController;
    protected FilmeController filmeController;
    protected SerieController serieController;

    public MenuRemocao(Scanner scanner, LivroController livroController,
                       FilmeController filmeController,
                       SerieController serieController){
        this.scanner = scanner;
        this.livroController = livroController;
        this.filmeController = filmeController;
        this.serieController = serieController;
    }

    @Override
    public void exibir() {
        String opcao;
        String isbn;
        int id;
        boolean removeu, salvo;

        do {
            System.out.println("\n-- MENU DE REMOÇÃO --");
            System.out.println("1- Livro");
            System.out.println("2- Filme");
            System.out.println("3- Série");
            System.out.println("4- Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.println("\n<<< REMOÇÃO DE LIVRO >>>\n");

                    try {
                        while(true) {
                            try {
                                System.out.println("Digite o ISBN do livro que você deseja remover: ");
                                isbn = scanner.nextLine();
                                break;

                            } catch (Exception e) {
                                System.out.println("Erro: " + e.getMessage());
                            }
                        }
                        removeu = livroController.removerLivro(isbn);

                        if(removeu){
                            System.out.println("Livro removido com sucesso!");
                            salvo = livroController.salvarLivros();

                            if (salvo)
                                System.out.println("Alterações salvas com sucesso!");
                            else
                                System.out.println("Não foi possível salvar a alteração!");
                        }

                        else
                            System.out.println("Não foi possível remover o livro. Verifique se o ISBN fornecido é válido.");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case "2":
                    System.out.println("\n<<< REMOÇÃO DE FILME >>>\n");

                    try {
                        while(true) {
                            try {
                                System.out.println("Digite o ID do Filme que você deseja remover: ");
                                id = scanner.nextInt();
                                scanner.nextLine();
                                break;

                            } catch (Exception e) {
                                System.out.println("Erro: " + e.getMessage());
                            }
                        }
                        removeu = filmeController.removerFilme(id);

                        if(removeu){
                            System.out.println("Filme removido com sucesso!");
                            salvo = filmeController.salvarFilmes();

                            if (salvo)
                                System.out.println("Alterações salvas com sucesso!");
                            else
                                System.out.println("Não foi possível salvar a alteração!");
                        }
                        else
                            System.out.println("Não foi possível remover o filme. Verifique se o ID fornecido é válido.");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case "3":
                    System.out.println("\n<<< REMOÇÃO DE SÉRIE >>>\n");

                    try {
                        while(true) {
                            try {
                                System.out.println("Digite o ID da Série que você deseja remover: ");
                                id = scanner.nextInt();
                                scanner.nextLine();
                                break;

                            } catch (Exception e) {
                                System.out.println("Erro: " + e.getMessage());
                            }
                        }
                        removeu = serieController.removerSerie(id);

                        if(removeu){
                            System.out.println("Série removida com sucesso!");
                            salvo = serieController.salvarSeries();

                            if (salvo)
                                System.out.println("Alterações salvas com sucesso!");
                            else
                                System.out.println("Não foi possível salvar a alteração!");
                        }
                        else
                            System.out.println("Não foi possível remover a série. Verifique se o ID fornecido é válido.");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
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
