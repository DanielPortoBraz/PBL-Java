package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class SerieRepositorioTest {

    private SerieRepositorio series;

    @BeforeEach
    public void setup() {
        series = new SerieRepositorio();
    }

    @Test
    public void adicionarSerie() {
        Serie serie = new Serie("Série A", new HashSet<>(), 2000,
                false, 2005, new HashSet<>(), "Original A",
                new HashSet<>(), new HashSet<>());
        series.addSerie(serie);
        assertEquals(1, series.getSeries().size());
    }

    @Test
    public void adicionarSeriesIguais() {
        Serie serie1 = new Serie("Série A", new HashSet<>(), 2000,
                false, 2005, new HashSet<>(), "Original", new HashSet<>(), new HashSet<>());
        serie1.setId(100);

        Serie serie2 = new Serie("Série A", new HashSet<>(), 2000,
                false, 2005, new HashSet<>(), "Original", new HashSet<>(), new HashSet<>());
        serie2.setId(100);

        series.addSerie(serie1);
        series.addSerie(serie2);

        assertEquals(1, series.getSeries().size());
    }

    @Test
    public void adicionarSeriesDiferentesPorTituloOriginal() {
        Serie serie1 = new Serie("Série", new HashSet<>(), 2000,
                false, 2005, new HashSet<>(), "Original A",
                new HashSet<>(), new HashSet<>());
        serie1.setId(101);

        Serie serie2 = new Serie("Série", new HashSet<>(), 2000,
                true, 2005, new HashSet<>(), "Original B", new HashSet<>(),
                new HashSet<>());
        serie2.setId(102);

        series.addSerie(serie1);
        series.addSerie(serie2);

        assertEquals(2, series.getSeries().size());
    }

    @Test
    public void removerSerieInexistente() {
        Serie serie = new Serie("Inexistente", new HashSet<>(), 1990,
                false, 1995, new HashSet<>(), "Nonexistent",
                new HashSet<>(), new HashSet<>());
        serie.setId(9999);

        assertFalse(series.removeSerie(serie));
    }

    @Test
    public void buscarPorTitulo() {
        Serie serie = new Serie("Breaking Bad", new HashSet<>(), 2008,
                true, 2013, new HashSet<>(), "Breaking Bad",
                new HashSet<>(), new HashSet<>());
        series.addSerie(serie);

        assertEquals(1, series.buscarTitulo("Breaking").size());
    }

    @Test
    public void buscarPorAno() {
        Serie serie = new Serie("Ano Teste", new HashSet<>(), 2011,
                true, 2015, new HashSet<>(), "Original", new HashSet<>(), new HashSet<>());
        series.addSerie(serie);

        assertEquals(1, series.buscarAno(2011).size());
    }

    @Test
    public void buscarPorAtor() {
        HashSet<String> elenco = new HashSet<>(Arrays.asList("Bryan Cranston", "Aaron Paul"));
        Serie serie = new Serie("Breaking Bad", new HashSet<>(), 2008,
                true, 2013, elenco, "Breaking Bad", new HashSet<>(), new HashSet<>());
        series.addSerie(serie);

        assertEquals(1, series.buscarAtor("Aaron").size());
    }

    @Test
    public void buscarPorOndeAssistir() {
        HashSet<String> plataformas = new HashSet<>(Arrays.asList("Netflix", "Amazon Prime"));
        Serie serie = new Serie("Série Stream", new HashSet<>(), 2010,
                true, 2012, new HashSet<>(), "Original Stream",
                plataformas, new HashSet<>());
        series.addSerie(serie);

        assertEquals(1, series.buscarOndeAssistir("Netflix").size());
    }

    @Test
    public void buscarPorGenero() {
        HashSet<Genero> romance = new HashSet<>(Arrays.asList(Genero.ROMANCE));
        HashSet<Genero> drama = new HashSet<>(Arrays.asList(Genero.DRAMA));

        series.addSerie(new Serie("Romance A", romance, 1999,
                true, 2001, new HashSet<>(), "R1",
                new HashSet<>(), new HashSet<>()));
        series.addSerie(new Serie("Romance B", romance, 2000,
                true, 2002, new HashSet<>(), "R2",
                new HashSet<>(), new HashSet<>()));
        series.addSerie(new Serie("Romance C", romance, 2001,
                true, 2003, new HashSet<>(), "R3",
                new HashSet<>(), new HashSet<>()));
        series.addSerie(new Serie("Drama D", drama, 2002,
                true, 2004, new HashSet<>(), "R4",
                new HashSet<>(), new HashSet<>()));

        assertEquals(3, series.buscarGenero(Genero.ROMANCE).size());
    }

    @Test
    public void buscarPorID() {
        Serie serie1 = new Serie("Série X", new HashSet<>(), 2000,
                false, 2005, new HashSet<>(), "Original X",
                new HashSet<>(), new HashSet<>());
        serie1.setId(200);

        Serie serie2 = new Serie("Série Y", new HashSet<>(), 2005,
                true, 2010, new HashSet<>(), "Original Y",
                new HashSet<>(), new HashSet<>());
        serie2.setId(300);

        series.addSerie(serie1);
        series.addSerie(serie2);

        assertEquals("Série Y", series.buscarId(300).getTitulo());
    }

    @Test
    public void verificarOrdenacaoPorPontuacao() {
        Serie serieA = new Serie("Série A", new HashSet<>(), 2010,
                true, 2015, new HashSet<>(), "Original A",
                new HashSet<>(), new HashSet<>());
        serieA.setPontuacao(3);

        Serie serieB = new Serie("Série B", new HashSet<>(), 2011,
                true, 2016, new HashSet<>(), "Original B",
                new HashSet<>(), new HashSet<>());
        serieB.setPontuacao(1);

        Serie serieC = new Serie("Série C", new HashSet<>(), 2012,
                true, 2017, new HashSet<>(), "Original C",
                new HashSet<>(), new HashSet<>());
        serieC.setPontuacao(5);

        series.addSerie(serieA);
        series.addSerie(serieB);
        series.addSerie(serieC);

        Serie[] ordenados = series.getSeries().toArray(new Serie[0]);

        assertEquals("Série C", ordenados[0].getTitulo());
        assertEquals("Série A", ordenados[1].getTitulo());
        assertEquals("Série B", ordenados[2].getTitulo());
    }
}
