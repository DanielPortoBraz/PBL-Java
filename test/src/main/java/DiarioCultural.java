import Controller.*;
import View.*;
import java.util.Scanner;

public class DiarioCultural {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LivroController livroController = new LivroController();
        FilmeController filmeController = new FilmeController();
        SerieController serieController = new SerieController();

        MenuPrincipal menu = new MenuPrincipal(scanner, livroController, filmeController, serieController);
        menu.exibir();

        scanner.close();
    }
}