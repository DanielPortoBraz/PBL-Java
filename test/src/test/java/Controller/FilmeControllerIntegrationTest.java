package Controller;

import Model.Filme;
import Model.Genero;
import Model.Livro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class FilmeControllerIntegrationTest {

    FilmeController controller;

    private HashSet<Genero> generos1, generos2, generos3;
    private HashSet<String> direcao1, roteiro1, elenco1, ondeAssistir1;
    private HashSet<String> direcao2, roteiro2, elenco2, ondeAssistir2;
    private HashSet<String> direcao3, roteiro3, elenco3, ondeAssistir3;

    private int id1, id2;

    @BeforeEach
    void setUp() {
        controller = new FilmeController();

        generos1 = new HashSet<>(List.of(Genero.FICCAO_CIENTIFICA));
        generos2 = new HashSet<>(List.of(Genero.COMEDIA));
        generos3 = new HashSet<>(List.of(Genero.TERROR));

        direcao1 = new HashSet<>(List.of("Christopher Nolan"));
        roteiro1 = new HashSet<>(List.of("Jonathan Nolan"));
        elenco1 = new HashSet<>(List.of("Matthew McConaughey", "Anne Hathaway"));
        ondeAssistir1 = new HashSet<>(List.of("Netflix", "HBO Max"));

        direcao2 = new HashSet<>(List.of("Todd Phillips"));
        roteiro2 = new HashSet<>(List.of("Jon Lucas"));
        elenco2 = new HashSet<>(List.of("Bradley Cooper", "Zach Galifianakis"));
        ondeAssistir2 = new HashSet<>(List.of("Amazon Prime"));

        direcao3 = new HashSet<>(List.of("Stanley Kubrick"));
        roteiro3 = new HashSet<>(List.of("Stephen King"));
        elenco3 = new HashSet<>(List.of("Jack Nicholson", "Shelley Duvall"));
        ondeAssistir3 = new HashSet<>(List.of("HBO Max"));

        controller.cadastrarFilme("Interestelar", generos1, 2014, true, 169,
                direcao1, roteiro1, elenco1, "Interstellar", ondeAssistir1);

        controller.cadastrarFilme("Se Beber, Não Case!", generos2, 2009, false, 100,
                direcao2, roteiro2, elenco2, "The Hangover", ondeAssistir2);

        controller.cadastrarFilme("O Iluminado", generos3, 1980, true, 146,
                direcao3, roteiro3, elenco3, "The Shining", ondeAssistir3);

        // capturar IDs dos filmes cadastrados
        id1 = controller.getFilmesR().buscarTitulo("Interestelar").first().getId();
        id2 = controller.getFilmesR().buscarTitulo("O Iluminado").first().getId();
    }

    @Test
    public void naoDeveCadastrarFilmesIguais(){
        boolean resultado = controller.cadastrarFilme("Interestelar", generos1, 2014, true, 169,
                direcao1, roteiro1, elenco1, "Interstellar", ondeAssistir1);

        assertFalse(resultado);

    }

    @Test
    public void deveCadastrarFilmeComSomenteTituloOriginalIgual() {
        boolean adicionado = controller.cadastrarFilme("Filme Teste", generos1,
                2014, true, 169, direcao1, roteiro1,
                elenco1, "Interestelar", ondeAssistir1);

        assertTrue(adicionado);
    }

    @Test
    public void deveOrdenarFilmesPorPontuacao() {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(id1); // Indice 0: Livro com maior pontuação
        ids.add(id2); // Indice 1: Livro com segunda maior pontuação

        controller.avaliarFilme(id2, "Bom filme", 4, Calendar.getInstance());
        controller.avaliarFilme(id1, "Obra-prima", 5, Calendar.getInstance());
        int cont = 0;

        for (Filme i : controller.getFilmesR().getFilmes()){
            assertEquals(ids.get(cont), i.getId());
            if (cont == 1) break;
            cont++;
        }
    }

    @Test
    public void deveBuscarFilmePorTituloIncompleto() {
        boolean encontrados = controller.buscarFilmes("1", "Interest");
        assertTrue(encontrados);
    }

    @Test
    public void deveBuscarFilmePorGenero() {
        boolean encontrados = controller.buscarFilmes("3", "Comédia");
        assertTrue(encontrados);
    }

    @Test
    public void deveBuscarFilmePorAno() {
        boolean encontrados = controller.buscarFilmes("4", "2014");
        assertTrue(encontrados);
    }

    @Test
    public void deveBuscarFilmePorAtor() {
        boolean encontrados = controller.buscarFilmes("2", "Anne Hathaway");
        assertTrue(encontrados);
    }

    @Test
    public void deveBuscarFilmePorDiretor() {
        boolean encontrados = controller.buscarFilmes("5", "Nolan");
        assertTrue(encontrados);
    }

    @Test
    public void deveBuscarFilmePorId() {
        boolean encontrado = controller.buscarFilmes("6", String.valueOf(id1));
        assertTrue(encontrado);
    }

    @Test
    public void naoDeveAvaliarFilmeComIdInvalido() {
        boolean resultado = controller.avaliarFilme(999999, "Inexistente", 3, Calendar.getInstance());
        assertFalse(resultado);
    }
}
