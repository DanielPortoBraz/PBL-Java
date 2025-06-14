package Controller;

import Model.Livro;
import Model.Genero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class LivroControllerIntegrationTest {

    LivroController controller;
    private HashSet<Genero> generos1;
    private HashSet<Genero> generos2;
    private HashSet<Genero> generos3;
    private HashSet<Genero> generos4;
    private HashSet<Genero> generos5;

    @BeforeEach
    void setUp() {
        controller = new LivroController();

        generos1 = new HashSet<>(Arrays.asList(Genero.FICCAO_CIENTIFICA));
        generos2 = new HashSet<>(Arrays.asList(Genero.BIOGRAFIA));
        generos3 = new HashSet<>(Arrays.asList(Genero.ROMANCE));
        generos4 = new HashSet<>(Arrays.asList(Genero.FANTASIA));
        generos5 = new HashSet<>(Arrays.asList(Genero.TERROR));

        // Cadastrando os livros reais
        controller.cadastrarLivro("O Código Da Vinci", generos1, 2003, true,
                "Dan Brown", "Sextante", "9788575421139", true);

        controller.cadastrarLivro("O Iluminado", generos5, 1977, false,
                "Stephen King", "Suma", "9788556510509", true);

        controller.cadastrarLivro("Steve Jobs", generos2, 2011, false,
                "Walter Isaacson", "Companhia das Letras", "9788535919554", true);

        controller.cadastrarLivro("Moby Dick", generos3, 1851, false,
                "Hermam Merville", "Editora 34", "9781111111111", true);

        controller.cadastrarLivro("Orgulho e Preconceito", generos3, 1813, true,
                "Jane Austen", "Martin Claret", "9788572329797", false);

        controller.cadastrarLivro("Orgulho e Preconceito", generos3, 1813, true,
                "Autor Desconhecido", "Editora Alternativa", "9780000000000", false);

        controller.cadastrarLivro("Harry Potter e a Pedra Filosofal", generos4, 1997, true,
                "J.K. Rowling", "Rocco", "9788532511010", true);
    }


    @Test
    public void naoDeveCadastrarLivrosIguais() {
        boolean resultado = controller.cadastrarLivro("O Código Da Vinci", generos1, 2003, true,
                "Dan Brown", "Sextante", "9788575421139", true);

        assertFalse(resultado);
    }

    @Test
    public void deveRemoverLivro(){
        assertTrue(controller.removerLivro("9788575421139")); // Deve remover o livro "O Código da Vinci"
        assertFalse(controller.buscarLivros("5", "9788575421139")); // Busca o livro após ele ser removido
    }

    @Test
    public void naoDeveRemoverLivro(){
        assertFalse(controller.removerLivro("ISBN_INEXISTENTE")); // Fornece um ISBN que não pertence a qualquer livro cadastrado
        assertFalse(controller.buscarLivros("5", "ISBN_INEXISTENTE")); // Busca um livro inexistente
    }

    @Test
    public void devePermitirCadastroDeLivrosComSomenteIsbnDiferente() {
        boolean resultado = controller.cadastrarLivro("O Código Da Vinci", generos1, 2003, true,
                "Dan Brown", "Sextante", "9780000000001", true); // ISBN diferente

        assertTrue(resultado);
    }

    @Test
    public void deveEvitarCadastroDeLivrosComMesmoISBN() {
        boolean resultado = controller.cadastrarLivro("Novo Livro", generos1, 2003, true,
                "Dan Brown", "Sextante", "9788575421139", true); // Mesmo ISBN

        assertFalse(resultado);
    }

    @Test
    public void deveOrdenarLivrosPorAvaliacao() {

        ArrayList<String> isbns = new ArrayList<>();
        isbns.add("9788556510509"); // Indíce 0: Livro com maior pontuação
        isbns.add("9788575421139"); // Indíce 1: Livro com segunda maior pontuação

        controller.avaliarLivro(isbns.get(1), "Legal", 3, Calendar.getInstance());
        controller.avaliarLivro(isbns.get(0), "Incrível", 5, Calendar.getInstance());
        int cont = 0;

        for (Livro i : controller.getLivrosR().getLivros()){
            assertEquals(isbns.get(cont), i.getIsbn());
            if (cont == 1) break;
            cont++;
        }
    }

    @Test
    public void deveBuscarLivroPorTituloIncompleto() {
        boolean encontrados = controller.buscarLivros("1", "Código");
        assertTrue(encontrados);
    }

    @Test
    public void deveBuscarLivroPorGenero() {
        TreeSet<Livro> encontrados = controller.getLivrosR().buscarGenero(Genero.ROMANCE);
        assertEquals(3, encontrados.size()); // Devem ser os dois Orgulho e Preconceito, e Moby Dick
    }

    @Test
    public void naoDeveAvaliarLivroPorIsbnInvalido() {
        boolean resultado = controller.avaliarLivro("9780000009999", "Inexistente", 4, Calendar.getInstance());
        assertFalse(resultado);
    }
}
