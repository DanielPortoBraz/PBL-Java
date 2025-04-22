package Controller;

import Model.Serie;
import Model.SerieRepositorio;
import Model.Genero;
import Model.Temporada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SerieControllerTest {
    @InjectMocks
    SerieController controller;

    @Mock
    SerieRepositorio repositorio;

    Serie serie;
    Temporada temporada1, temporada2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        temporada1 = new Temporada(2003, 10, 1);
        temporada2 = new Temporada(2005, 9, 2);

        HashSet<Temporada> temporadas = new HashSet<>(Arrays.asList(temporada1, temporada2));

        serie = new Serie("Serie A", new HashSet<>(Arrays.asList(Genero.AVENTURA, Genero.DRAMA)),
                2015, false, 2020, new HashSet<>(Collections.singletonList("Ator A")),
                "Título Original A", new HashSet<>(Collections.singletonList("Plataforma X")), temporadas);
        serie.setId(1);
    }

    @Test
    public void deveAvaliarSeriePorIdValido() {
        Calendar dataVisto = Calendar.getInstance();

        when(repositorio.buscarId(1)).thenReturn(serie);

        boolean resultado = controller.avaliarSerie(1, "Ótima série!", dataVisto);

        assertTrue(resultado);
        assertTrue(serie.isVisto());
        assertEquals("Ótima série!", serie.getReview());

        verify(repositorio).buscarId(1);
    }

    @Test
    public void naoDeveAvaliarSeriePorIdInvalido() {
        when(repositorio.buscarId(99)).thenReturn(null);

        boolean resultado = controller.avaliarSerie(99, "Ótima série!", Calendar.getInstance());

        assertFalse(resultado);
        verify(repositorio).buscarId(99);
        verifyNoMoreInteractions(repositorio);
    }

    @Test
    public void deveAtribuirPontuacaoDeSeriePelaMediaDasPontuacoesDasTemporadas() {
        when(repositorio.buscarId(1)).thenReturn(serie);

        boolean resultado1 = controller.avaliarTemporada(1, 1, "Ótima temporada", 5);
        boolean resultado2 = controller.avaliarTemporada(1, 2, "Ótima temporada", 3);

        assertTrue(resultado1 && resultado2);
        assertEquals(4, serie.getPontuacao()); // Média aritmética (5 + 3) / 2 = 4

        verify(repositorio, times(2)).buscarId(1);
        verify(repositorio, times(2)).removeSerie(serie);
        verify(repositorio, times(2)).addSerie(serie);
    }

    @Test
    public void deveBuscarSeriePorGeneroAventura() {
        when(repositorio.buscarGenero(Genero.AVENTURA)).thenReturn(new TreeSet<>());

        boolean encontrados = controller.buscarSeries("3", "Aventura");

        assertTrue(encontrados);
        verify(repositorio).buscarGenero(Genero.AVENTURA);
    }

    @Test
    public void naoDeveRetornarSeriePorGeneroInvalido() {
        when(repositorio.buscarGenero(any(Genero.class))).thenReturn(new TreeSet<>());

        boolean encontrados = controller.buscarSeries("3", "Fábula");

        assertFalse(encontrados);
        verify(repositorio, never()).buscarGenero(Genero.AVENTURA);
    }
}
