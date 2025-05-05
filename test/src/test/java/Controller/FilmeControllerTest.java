package Controller;

import Model.Filme;
import Model.FilmeRepositorio;
import Model.Genero;
import Model.Filme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FilmeControllerTest {
    @InjectMocks
    FilmeController controller;

    @Mock
    FilmeRepositorio repositorio;

    Filme filme;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        filme = new Filme("Filme A", new HashSet<>(Arrays.asList(Genero.AVENTURA, Genero.DRAMA)),
                2010, false, 120, new HashSet<>(Collections.singletonList("Diretor A")),
                new HashSet<>(Collections.singletonList("Roteirista A")),
                new HashSet<>(Collections.singletonList("Ator A")), "Título Original A",
                new HashSet<>(Collections.singletonList("Plataforma X")));

        filme.setId(1);
    }

    @Test
    public void deveRemoverFilme(){
        when(repositorio.buscarId(filme.getId())).thenReturn(filme);
        when(repositorio.removeFilme(filme)).thenReturn(true);

        boolean removido = controller.removerFilme(filme.getId());

        assertTrue(removido);
        verify(repositorio).removeFilme(filme);
    }

    @Test
    public void naoDeveRemoverFilmeInexistente(){
        Filme filmeInexistente = new Filme("Filme I", new HashSet<>(Arrays.asList(Genero.AVENTURA, Genero.DRAMA)),
                2010, false, 120, new HashSet<>(Collections.singletonList("Diretor A")),
                new HashSet<>(Collections.singletonList("Roteirista I")),
                new HashSet<>(Collections.singletonList("Ator I")), "Original I",
                new HashSet<>(Collections.singletonList("Plataforma I")));
        when(repositorio.removeFilme(filmeInexistente)).thenReturn(false);

        boolean removido = controller.removerFilme(filmeInexistente.getId());

        assertFalse(removido);
        verify(repositorio, never()).removeFilme(filme);
    }

    @Test
    public void deveAvaliarFilmePorIdValido() {
        Calendar dataVisto = Calendar.getInstance();

        when(repositorio.buscarId(1)).thenReturn(filme);
        when(repositorio.addFilme(any(Filme.class))).thenReturn(true);

        boolean resultado = controller.avaliarFilme(1, "Ótimo filme!", 5, dataVisto);

        assertTrue(resultado);
        assertTrue(filme.isVisto());
        assertEquals("Ótimo filme!", filme.getReview());
        assertEquals(5, filme.getPontuacao());

        verify(repositorio).buscarId(1);
        verify(repositorio).removeFilme(filme);
        verify(repositorio).addFilme(filme);
    }

    @Test
    public void naoDeveAvaliarFilmePorIdInvalido() {
        when(repositorio.buscarId(99)).thenReturn(null);

        boolean resultado = controller.avaliarFilme(99, "Ótimo filme!", 5, Calendar.getInstance());

        assertFalse(resultado);
        verify(repositorio).buscarId(99);
        verifyNoMoreInteractions(repositorio);
    }

    @Test
    public void deveBuscarFilmePorGeneroAventura() {
        TreeSet<Filme> filmesMock = new TreeSet<>();
        filmesMock.add(filme);

        when(repositorio.buscarGenero(Genero.AVENTURA)).thenReturn(filmesMock);

        boolean encontrados = controller.buscarFilmes("3", "Aventura");

        assertTrue(encontrados);
        verify(repositorio).buscarGenero(Genero.AVENTURA);
    }

    @Test
    public void naoDeveRetornarFilmePorGeneroInvalido() {
        when(repositorio.buscarGenero(any(Genero.class))).thenReturn(new TreeSet<>());

        boolean encontrados = controller.buscarFilmes("3", "Fábula");

        assertFalse(encontrados);
        verify(repositorio, never()).buscarGenero(Genero.AVENTURA);
    }
}
