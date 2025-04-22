package Model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;


public class SerieTest {

    @Test
    public void deveCriarSerieCompleta() {
        Serie serie = new Serie(
                "Breaking Bad", new HashSet<>(Arrays.asList(Genero.DRAMA)),
                2008, true, 2013,
                new HashSet<>(Arrays.asList("Bryan Cranston", "Aaron Paul")),
                "Breaking Bad", new HashSet<>(Arrays.asList("Netflix")),
                new HashSet<>(Arrays.asList(
                        new Temporada(2008, 7, 1),
                        new Temporada(2009, 13, 2)
                ))
        );

        assertEquals("Breaking Bad", serie.getTitulo());
        assertEquals("Breaking Bad", serie.getTituloOriginal());
        assertEquals(2008, serie.getAnoLancamento());
        assertEquals(2013, serie.getAnoEncerramento());
        assertTrue(serie.isVisto());
        assertEquals(0, serie.getPontuacao()); // valor padrão
        assertEquals(" ", serie.getReview());
        assertEquals(2, serie.getTemporadas().size());
    }

    @Test
    public void deveCriarSerieIncompleta() {
        Serie serie = new Serie(
                "", new HashSet<>(), 0,
                false, 0, new HashSet<>(),
                "", new HashSet<>(), new HashSet<>()
        );

        assertEquals("", serie.getTitulo());
        assertEquals("", serie.getTituloOriginal());
        assertEquals(0, serie.getAnoLancamento());
        assertEquals(0, serie.getAnoEncerramento());
        assertFalse(serie.isVisto());
        assertEquals(0, serie.getPontuacao());
        assertEquals(" ", serie.getReview());
        assertEquals(0, serie.getTemporadas().size());
    }

    @Test
    public void deveRetornarSerieAoPegarIdValido() {
        Serie serie = new Serie(
                "Friends", new HashSet<>(Arrays.asList(Genero.COMEDIA)),
                1994, true, 2004,
                new HashSet<>(Arrays.asList("Jennifer Aniston", "Lisa Kudrow")),
                "Friends", new HashSet<>(Arrays.asList("HBO Max")),
                new HashSet<>(Arrays.asList(new Temporada(1994, 24, 1)))
        );

        assertTrue(serie.getId() >= 0 && serie.getId() <= 99999);
    }

    @Test
    public void deveRetornarQueSeriesIguaisPorIdSaoIguais() {
        Serie serie1 = new Serie(
                "Série A", new HashSet<>(),
                2010, true, 2015,
                new HashSet<>(), "Original A", new HashSet<>(),
                new HashSet<>()
        );
        serie1.setId(777);

        Serie serie2 = new Serie(
                "Série B", new HashSet<>(), 2012,
                false, 2016, new HashSet<>(),
                "Original B", new HashSet<>(),
                new HashSet<>()
        );
        serie2.setId(777);

        assertEquals(serie1, serie2);
    }

    @Test
    public void naoDeveRetornarQueSeriesIguaisPorIdSaoIguais() {
        Serie serie1 = new Serie(
                "Série X", new HashSet<>(), 2000,
                false, 2005, new HashSet<>(),
                "Original X", new HashSet<>(),
                new HashSet<>()
        );
        serie1.setId(1010);

        Serie serie2 = new Serie(
                "Série X", new HashSet<>(), 2000,
                false, 2005, new HashSet<>(),
                "Original X", new HashSet<>(),
                new HashSet<>()
        );
        serie2.setId(2020);

        assertNotEquals(serie1, serie2);
    }


}
