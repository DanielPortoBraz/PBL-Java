package Model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;


public class FilmeTest {

    @Test
    public void deveCriarFilmeCompleto() {
        Filme filme = new Filme(
                "Interestelar",
                new HashSet<>(Arrays.asList(Genero.FICCAO_CIENTIFICA)),
                2014,
                true,
                169,
                new HashSet<>(Arrays.asList("Christopher Nolan")),
                new HashSet<>(Arrays.asList("Jonathan Nolan")),
                new HashSet<>(Arrays.asList("Matthew McConaughey", "Anne Hathaway")),
                "Interstellar",
                new HashSet<>(Arrays.asList("Netflix", "HBO Max"))
        );

        assertEquals("Interestelar", filme.getTitulo());
        assertEquals("Interstellar", filme.getTituloOriginal());
        assertEquals(0, filme.getPontuacao()); // valor padrão
        assertEquals(" ", filme.getReview());
        assertTrue(filme.isVisto());
        assertEquals(169, filme.getTempoDuracao());
    }

    @Test
    public void deveCriarFilmeIncompleto() {
        Filme filme = new Filme(
                "", new HashSet<>(), 0, false,
                0, new HashSet<>(), new HashSet<>(),
                new HashSet<>(), "", new HashSet<>()
        );

        assertEquals("", filme.getTitulo());
        assertEquals("", filme.getTituloOriginal());
        assertEquals(0, filme.getPontuacao());
        assertEquals(" ", filme.getReview());
        assertEquals(0, filme.getTempoDuracao());
    }

    @Test
    public void deveRetornarFilmeAoPegarIdValido() {
        Filme filme = new Filme(
                "Filme Qualquer", new HashSet<>(), 2000,
                false, 100, new HashSet<>(),
                new HashSet<>(), new HashSet<>(), "Original Title",
                new HashSet<>()
        );

        assertTrue(filme.getId() >= 0 && filme.getId() <= 99999);
    }

    @Test
    public void deveRetornarQueFilmesIguaisPorIdSaoIguais() {
        Filme filme1 = new Filme(
                "Filme A", new HashSet<>(), 2010, true,
                90, new HashSet<>(), new HashSet<>(), new HashSet<>(),
                "Filme Original A", new HashSet<>());
        filme1.setId(100);

        Filme filme2 = new Filme(
                "Filme B", new HashSet<>(), 2000, false,
                120, new HashSet<>(), new HashSet<>(), new HashSet<>(),
                "Filme Original B", new HashSet<>());
        filme2.setId(100);

        assertEquals(filme1, filme2); // Iguais porque o id é igual
    }

    @Test
    public void naoDeveRetornarQueFilmesDiferentesPorIdSaoIguais() {
        Filme filme1 = new Filme(
                "Filme X", new HashSet<>(), 2000,
                false, 100, new HashSet<>(), new HashSet<>(),
                new HashSet<>(), "Original X", new HashSet<>()
        );
        filme1.setId(200);

        Filme filme2 = new Filme(
                "Filme X", new HashSet<>(), 2000,
                false, 100, new HashSet<>(), new HashSet<>(),
                new HashSet<>(), "Original X", new HashSet<>()
        );
        filme2.setId(300);
        assertNotEquals(filme1, filme2);
    }
}
