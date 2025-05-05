package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.*;

public class LivroRepositorioTest {

    private LivroRepositorio livros;

    @BeforeEach
    public void setup() {
        livros = new LivroRepositorio();
    }

    @Test
    public void deveAdicionarLivro() {
        Livro livro = new Livro("Livro A", new HashSet<>(), 2000,
                false, "Autor A", "Editora A", "ISBN001", true);
        livros.addLivro(livro);
        assertEquals(1, livros.getLivros().size());
    }

    @Test
    public void deveRemoverLivro(){
        Livro livro1 = new Livro("Livro A", new HashSet<>(), 2000,
                false, "Autor A", "Editora A", "ISBN001", true);
        Livro livro2 = new Livro("Livro B", new HashSet<>(), 2000,
                false, "Autor B", "Editora A", "ISBN002", true);

        livros.addLivro(livro1);
        livros.addLivro(livro2);

        assertEquals(2, livros.getLivros().size());
        assertTrue(livros.removeLivro(livro1));
        assertEquals(1, livros.getLivros().size());
    }

    @Test
    public void naoDeveRemoverLivroInexistente(){
        Livro livro1 = new Livro("Livro A", new HashSet<>(), 2000,
                false, "Autor A", "Editora A", "ISBN001", true);
        Livro livro2 = new Livro("Livro B", new HashSet<>(), 2000,
                false, "Autor B", "Editora A", "ISBN002", true);

        livros.addLivro(livro1);

        assertEquals(1, livros.getLivros().size());
        assertFalse(livros.removeLivro(livro2));
        assertEquals(1, livros.getLivros().size());
    }

    @Test
    public void deveAdicionarSomenteUmLivroEntreDoisIguaisPorIsbn() {
        Livro livro1 = new Livro("Livro A", new HashSet<>(), 2000,
                false, "Autor A", "Editora A", "ISBN001", true);
        Livro livro2 = new Livro("Livro B", new HashSet<>(), 2005,
                true, "Autor B", "Editora B", "ISBN001", false);
        livros.addLivro(livro1);
        livros.addLivro(livro2);
        assertEquals(1, livros.getLivros().size());
    }

    @Test
    public void deveAdicionarDoisLivrosDiferentesPorIsbn() {
        Livro livro1 = new Livro("Livro A", new HashSet<>(), 2000,
                false, "Autor A", "Editora A", "1111111", true);
        Livro livro2 = new Livro("Livro A", new HashSet<>(), 2000,
                false, "Autor A", "Editora A", "2222222", true);
        livros.addLivro(livro1);
        livros.addLivro(livro2);

        assertEquals(2, livros.getLivros().size());
    }

    @Test
    public void deveRetornarFalsoAoRemoverLivroInexistente() {
        Livro livro = new Livro("Inexistente", new HashSet<>(), 1990,
                false, "Desconhecido", "Nenhuma", "ISBN999", false);
        assertFalse(livros.removeLivro(livro));
    }

    @Test
    public void deveBuscarPorTitulo() {
        Livro livro = new Livro("A Cabana", new HashSet<>(), 2007,
                true, "Autor X", "Editora X", "12345", true);
        livros.addLivro(livro);
        assertEquals(1, livros.buscarTitulo("A Cabana").size());
    }

    @Test
    public void deveBuscarPorAutor() {
        Livro livro = new Livro("Outro Livro", new HashSet<>(), 2001,
                false, "Machado de Assis", "Editora Clássica", "12345", true);
        livros.addLivro(livro);
        assertEquals(1, livros.buscarAutor("Machado").size());
    }

    @Test
    public void deveBuscarPorAno() {
        Livro livro = new Livro("Ano Teste", new HashSet<>(), 2010,
                false, "Autor", "Editora", "12345", true);
        livros.addLivro(livro);
        assertEquals(1, livros.buscarAno(2010).size());
    }

    @Test
    public void deveRetornarSomente3AobuscarPorGenero() {
        HashSet<Genero> romance = new HashSet<>(Arrays.asList(Genero.ROMANCE));
        HashSet<Genero> drama = new HashSet<>(Arrays.asList(Genero.DRAMA));

        livros.addLivro(new Livro("Romance A", romance, 1999,
                true, "Autor A", "Editora A", "111", true));
        livros.addLivro(new Livro("Romance B", romance, 2000,
                true, "Autor B", "Editora B", "222", true));
        livros.addLivro(new Livro("Romance C", romance, 2001,
                true, "Autor C", "Editora C", "333", true));
        livros.addLivro(new Livro("Drama D", drama, 2005,
                true, "Autor D", "Editora D", "444", true));

        assertEquals(3, livros.buscarGenero(Genero.ROMANCE).size());
    }

    @Test
    public void deveBuscarLivrosDiferentesPorIsbn() {
        Livro livro1 = new Livro("Livro 1", new HashSet<>(), 2011,
                true, "Autor 1", "Editora 1", "ISBN-A", true);
        Livro livro2 = new Livro("Livro 1", new HashSet<>(), 2011,
                true, "Autor 1", "Editora 1", "ISBN-B", true);

        livros.addLivro(livro1);
        livros.addLivro(livro2);

        assertEquals(livro2, livros.buscarIsbn("ISBN-B"));
    }

    @Test
    public void verificarOrdenacaoPorPontuacao() {
        Livro livroA = new Livro("Livro A", new HashSet<>(), 2010,
                true, "Autor", "Editora", "X1", true);
        livroA.setPontuacao(3);

        Livro livroB = new Livro("Livro B", new HashSet<>(), 2011,
                true, "Autor", "Editora", "X2", true);
        livroB.setPontuacao(1);

        Livro livroC = new Livro("Livro C", new HashSet<>(), 2012,
                true, "Autor", "Editora", "X3", true);
        livroC.setPontuacao(5);

        livros.addLivro(livroA);
        livros.addLivro(livroB);
        livros.addLivro(livroC);

        Livro[] ordenados = livros.getLivros().toArray(new Livro[0]);

        assertEquals("Livro C", ordenados[0].getTitulo()); // Maior pontuação
        assertEquals("Livro A", ordenados[1].getTitulo()); // Média
        assertEquals("Livro B", ordenados[2].getTitulo()); // Menor
    }
}
