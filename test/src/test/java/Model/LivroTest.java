package Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class LivroTest {

    @Test
    public void criarLivroCompleto() {
        Livro livro = new Livro(
                "1984", new HashSet<>(Arrays.asList(Genero.FICCAO_CIENTIFICA)),
                1949, false, "George Orwell",
                "Companhia Editora Nacional", "1234567890123",
                true
        );

        assertEquals("1984", livro.getTitulo());
        assertEquals(0, livro.getPontuacao()); // Valor padrão
        assertFalse(livro.isVisto());
        assertEquals("George Orwell", livro.getAutor());
        assertEquals("1234567890123", livro.getIsbn());
        assertEquals(" ",livro.getReview());
    }

    @Test
    public void criarLivroIncompleto() {
        Livro livro = new Livro(
                "", new HashSet<>(), 0,
                false, "", "", "",
                false
        );

        assertEquals("", livro.getTitulo());
        assertEquals(0, livro.getAnoLancamento());
        assertEquals(0, livro.getPontuacao()); // Deve continuar como padrão
        assertEquals("", livro.getAutor());
        assertEquals("", livro.getIsbn());
    }

    @Test
    public void verificarLivrosIguaisPorIsbn() {
        Livro livro1 = new Livro("Livro A", new HashSet<>(), 2000,
                false, "Autor A", "Editora A", "1111111111111",
                true);
        Livro livro2 = new Livro("Livro B", new HashSet<>(), 2020,
                true, "Autor B", "Editora B", "1111111111111",
                false);

        assertTrue(livro1.compareTo(livro2) == 0); // Devem ser iguais pelo ISBN
        assertEquals(livro1, livro2); // Devem ser iguais pelo ISBN
    }

    @Test
    public void verificarLivrosDiferentesPorIsbn() {
        Livro livro1 = new Livro("Livro X", new HashSet<>(), 2000,
                false, "Autor A", "Editora A", "0000000000001",
                true);
        Livro livro2 = new Livro("Livro X", new HashSet<>(), 2000,
                false, "Autor A", "Editora A", "0000000000002",
                true);

        assertTrue(livro1.compareTo(livro2) != 0); // Devem ser diferentes pelo ISBN
        assertNotEquals(livro1, livro2);
    }
}
