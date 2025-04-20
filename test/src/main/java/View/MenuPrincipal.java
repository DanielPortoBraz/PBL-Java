package View;

import Controller.*;
import java.util.Scanner;

public class MenuPrincipal implements Menu{
    private Scanner scanner;
    protected LivroController livroController;
    protected FilmeController filmeController;
    protected SerieController serieController;

    public MenuPrincipal(Scanner scanner, LivroController livroController,
                         FilmeController filmeController,
                         SerieController serieController){
        this.scanner = scanner;
        this.livroController = livroController;
        this.filmeController = filmeController;
        this.serieController = serieController;
    }

    @Override
    public void exibir(){
        String opcao;
        do {
            System.out.println("\n-- MENU PRINCIPAL --");
            System.out.println("1- Cadastrar");
            System.out.println("2- Avaliar");
            System.out.println("3- Buscar");
            System.out.println("4- Listar");
            System.out.println("5- Sair");
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
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (!opcao.equals("5"));
    }
}

