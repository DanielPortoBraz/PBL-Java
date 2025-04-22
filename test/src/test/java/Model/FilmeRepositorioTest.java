package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class FilmeRepositorioTest {

    private FilmeRepositorio filmes;

    @BeforeEach
    public void setup() {
        filmes = new FilmeRepositorio();
    }

    @Test
    public void deveAdicionarFilme() {
        Filme filme = new Filme("Filme A", new HashSet<>(), 2000,
                false, 120, new HashSet<>(), new HashSet<>(), new HashSet<>(),
                "Original A", new HashSet<>());
        filmes.addFilme(filme);
        assertEquals(1, filmes.getFilmes().size());
    }

    @Test
    public void deveAdicionarFilmesIguais(){
        Filme filme1 = new Filme("Filme A", new HashSet<>(), 2000,
                false, 120, new HashSet<>(), new HashSet<>(), new HashSet<>(),
                "Original", new HashSet<>());

        Filme filme2 = new Filme("Filme A", new HashSet<>(), 2000,
                false, 120, new HashSet<>(), new HashSet<>(), new HashSet<>(),
                "Original", new HashSet<>());

        filmes.addFilme(filme1);
        filmes.addFilme(filme2);

        assertEquals(1, filmes.getFilmes().size());
    }

    @Test
    public void deveAdicionarDoisFilmesDiferentesPorTituloOriginal(){
        Filme filme1 = new Filme("Filme", new HashSet<>(), 2000,
                false, 120, new HashSet<>(), new HashSet<>(), new HashSet<>(),
                "Original A", new HashSet<>());

        Filme filme2 = new Filme("Filme", new HashSet<>(), 2000,
                true, 120, new HashSet<>(), new HashSet<>(), new HashSet<>(),
                "Original B", new HashSet<>());

        filmes.addFilme(filme1);
        filmes.addFilme(filme2);

        assertEquals(2, filmes.getFilmes().size());
    }

    @Test
    public void deveRetornarFalsoAoRemoverFilmeInexistente() {
        Filme filme = new Filme("Inexistente", new HashSet<>(), 1990,
                false, 100, new HashSet<>(), new HashSet<>(), new HashSet<>(),
                "Nonexistent", new HashSet<>());

        assertFalse(filmes.removeFilme(filme));
    }

    @Test
    public void deveBuscarPorTitulo() {
        Filme filme = new Filme("A Origem", new HashSet<>(), 2010,
                true, 148, new HashSet<>(), new HashSet<>(), new HashSet<>(),
                "Inception", new HashSet<>());
        filmes.addFilme(filme);

        assertEquals(1, filmes.buscarTitulo("Origem").size());
    }

    @Test
    public void deveBuscarPorAno() {
        Filme filme = new Filme("Ano Teste", new HashSet<>(), 2020,
                true, 90, new HashSet<>(), new HashSet<>(), new HashSet<>(),
                "Teste", new HashSet<>());
        filmes.addFilme(filme);

        assertEquals(1, filmes.buscarAno(2020).size());
    }

    @Test
    public void deveBuscarPorDiretor() {
        HashSet<String> direcao = new HashSet<>(Arrays.asList("Steven Spielberg"));
        Filme filme = new Filme("Jurassic Park", new HashSet<>(), 1993,
                true, 127, direcao, new HashSet<>(), new HashSet<>(),
                "Jurassic Park", new HashSet<>());
        filmes.addFilme(filme);

        assertEquals(1, filmes.buscarDiretor("Spielberg").size());
    }

    @Test
    public void deveBuscarPorAtor() {
        HashSet<String> elenco = new HashSet<>(Arrays.asList("Will Smith"));
        Filme filme = new Filme("Eu, Robô", new HashSet<>(), 2004,
                true, 115, new HashSet<>(), new HashSet<>(), elenco,
                "I, Robot", new HashSet<>());
        filmes.addFilme(filme);

        assertEquals(1, filmes.buscarAtor("Will Smith").size());
    }

    @Test
    public void deveBuscarPorGenero() {
        HashSet<Genero> romance = new HashSet<>(Arrays.asList(Genero.ROMANCE));
        HashSet<Genero> drama = new HashSet<>(Arrays.asList(Genero.DRAMA));

        filmes.addFilme(new Filme("Romance A", romance, 1999,
                true, 90, new HashSet<>(), new HashSet<>(), new HashSet<>(),
                "Original A", new HashSet<>()));
        filmes.addFilme(new Filme("Romance B", romance, 2000,
                true, 100, new HashSet<>(), new HashSet<>(), new HashSet<>(),
                "Original B", new HashSet<>()));
        filmes.addFilme(new Filme("Romance C", romance, 2001,
                true, 95, new HashSet<>(), new HashSet<>(), new HashSet<>(),
                "Original C", new HashSet<>()));
        filmes.addFilme(new Filme("Drama D", drama, 2002,
                true, 95, new HashSet<>(), new HashSet<>(), new HashSet<>(),
                "Original D", new HashSet<>()));

        assertEquals(3, filmes.buscarGenero(Genero.ROMANCE).size());
    }

    @Test
    public void deveBuscarPorID() {
        Filme filme1 = new Filme("Filme A", new HashSet<>(), 2000,
                false, 120, new HashSet<>(), new HashSet<>(), new HashSet<>(),
                "Original A", new HashSet<>());
        filme1.setId(100);

        Filme filme2 = new Filme("Filme B", new HashSet<>(), 2005,
                true, 90, new HashSet<>(), new HashSet<>(), new HashSet<>(),
                "Original B", new HashSet<>());
        filme2.setId(200);

        filmes.addFilme(filme1);
        filmes.addFilme(filme2);

        assertEquals(filme2, filmes.buscarId(200));
    }

    @Test
    public void verificarOrdenacaoPorPontuacao() {
        Filme filmeA = new Filme("Filme A", new HashSet<>(), 2010,
                true, 100, new HashSet<>(), new HashSet<>(), new HashSet<>(),
                "Original A", new HashSet<>());
        filmeA.setPontuacao(3);

        Filme filmeB = new Filme("Filme B", new HashSet<>(), 2011,
                true, 100, new HashSet<>(), new HashSet<>(), new HashSet<>(),
                "Original B", new HashSet<>());
        filmeB.setPontuacao(1);

        Filme filmeC = new Filme("Filme C", new HashSet<>(), 2012,
                true, 100, new HashSet<>(), new HashSet<>(), new HashSet<>(),
                "Original C", new HashSet<>());
        filmeC.setPontuacao(5);

        filmes.addFilme(filmeA);
        filmes.addFilme(filmeB);
        filmes.addFilme(filmeC);

        Filme[] ordenados = filmes.getFilmes().toArray(new Filme[0]);

        assertEquals("Filme C", ordenados[0].getTitulo());
        assertEquals("Filme A", ordenados[1].getTitulo());
        assertEquals("Filme B", ordenados[2].getTitulo());
    }
}
