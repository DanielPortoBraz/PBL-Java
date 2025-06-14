package Controller;

import Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LivroControllerTest {
    @InjectMocks
    LivroController controller;

    @Mock
    LivroRepositorio repositorio;

    Livro livro;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        livro = new Livro("Livro A", new HashSet<>(Arrays.asList(Genero.AVENTURA,
                Genero.DRAMA)), 2000, true, "Autor A",
                "Editora A", "12345", false);
    }

    @Test
    public void deveRemoverLivro(){
        when(repositorio.buscarIsbn(livro.getIsbn())).thenReturn(livro);
        when(repositorio.removeLivro(livro)).thenReturn(true);

        boolean removido = controller.removerLivro(livro.getIsbn());

        assertTrue(removido);
        verify(repositorio).removeLivro(livro);
    }

    @Test
    public void naoDeveRemoverLivroInexistente(){
        Livro livroInexistente = new Livro("Livro B", new HashSet<>(), 2000,
                false, "Autor B", "Editora B", "54321", true);
        when(repositorio.removeLivro(livroInexistente)).thenReturn(false);

        boolean removido = controller.removerLivro(livroInexistente.getIsbn());

        assertFalse(removido);
        verify(repositorio, never()).removeLivro(livro);
    }

    @Test
    public void deveRetornarLivrosAoBuscarPorGeneroAventura() {
        when(repositorio.buscarGenero(any(Genero.class))).thenReturn(new TreeSet<Livro>());

        boolean encontrados = controller.buscarLivros("3", "Aventura");

        assertTrue(encontrados);
        verify(repositorio).buscarGenero(Genero.AVENTURA);
        verifyNoMoreInteractions(repositorio);
    }


    @Test
    public void naoDeveRetornarLivroAoBuscarPorGeneroInvalido() {
        when(repositorio.buscarGenero(Genero.AVENTURA)).thenReturn(new TreeSet<Livro>());

        boolean encontrados = controller.buscarLivros("3", "Fábula");

        assertFalse(encontrados);
        verify(repositorio, never()).buscarGenero(Genero.AVENTURA);
        verifyNoMoreInteractions(repositorio);
    }

    @Test
    public void deveAvaliarLivroComIsbnValido() {
        when(repositorio.buscarIsbn("12345")).thenReturn(livro);

        controller.avaliarLivro("12345", "Bom", 5, null);

        assertEquals(5, livro.getPontuacao());
        assertEquals("Bom", livro.getReview());
        assertTrue(livro.isVisto());
    }

    @Test
    public void naoDeveAvaliarLivroComIsbnInvalido() {
        when(repositorio.buscarIsbn("99999")).thenReturn(null);

        controller.avaliarLivro("99999", "Ruim", 1, null);

        verify(repositorio, never()).addLivro(any());
    }
}