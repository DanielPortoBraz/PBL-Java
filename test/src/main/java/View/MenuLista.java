package View;

import Controller.FilmeController;
import Controller.LivroController;
import Controller.SerieController;

import java.util.Scanner;

public class MenuLista implements Menu {
    private Scanner scanner;
    protected LivroController livroController;
    protected FilmeController filmeController;
    protected SerieController serieController;

    public MenuLista(Scanner scanner, LivroController livroController,
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
