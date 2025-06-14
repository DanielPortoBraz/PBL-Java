package Controller;

import Model.Serie;
import Model.Temporada;
import Model.Genero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SerieControllerIntegrationTest {

    SerieController controller;

    private HashSet<Genero> generos1, generos2, generos3;
    private HashSet<String> elenco1, elenco2, elenco3;
    private HashSet<String> ondeAssistir1, ondeAssistir2, ondeAssistir3;
    private HashSet<Temporada> temporadas1, temporadas2, temporadas3;

    private int id1, id2, id3;

    @BeforeEach
    public void setUp() {
        controller = new SerieController();

        generos1 = new HashSet<>(Arrays.asList(Genero.DRAMA));
        generos2 = new HashSet<>(Arrays.asList(Genero.DOCUMENTARIO));
        generos3 = new HashSet<>(Arrays.asList(Genero.COMEDIA));

        elenco1 = new HashSet<>(Arrays.asList("Bryan Cranston", "Aaron Paul"));
        elenco2 = new HashSet<>(Arrays.asList("David Attenborough"));
        elenco3 = new HashSet<>(Arrays.asList("Jennifer Aniston", "Lisa Kudrow"));

        ondeAssistir1 = new HashSet<>(Arrays.asList("Netflix"));
        ondeAssistir2 = new HashSet<>(Arrays.asList("HBO Max"));
        ondeAssistir3 = new HashSet<>(Arrays.asList("Amazon Prime"));

        temporadas1 = new HashSet<>();
        temporadas1.add(new Temporada(2008, 7, 1));
        temporadas1.add(new Temporada(2009, 13, 2));

        temporadas2 = new HashSet<>();
        temporadas2.add(new Temporada(2019, 8, 1));

        temporadas3 = new HashSet<>();
        temporadas3.add(new Temporada(1994, 24, 1));
        temporadas3.add(new Temporada(1995, 24, 2));

        controller.cadastrarSerie("Breaking Bad", generos1, 2008, true, 2013,
                elenco1, "Breaking Bad", ondeAssistir1, temporadas1);

        controller.cadastrarSerie("Nosso Planeta", generos2, 2019, false, 2019,
                elenco2, "Our Planet", ondeAssistir2, temporadas2);

        controller.cadastrarSerie("Friends", generos3, 1994, true, 2004,
                elenco3, "Friends", ondeAssistir2, temporadas3);

        id1 = controller.getSeriesR().buscarTitulo("Breaking Bad").first().getId();
        id2 = controller.getSeriesR().buscarTitulo("Nosso Planeta").first().getId();
        id3 = controller.getSeriesR().buscarTitulo("Friends").first().getId();
    }

    @Test
    public void naoDeveCadastrarSeriesIguais() {
        boolean resultado = controller.cadastrarSerie("Breaking Bad", generos1, 2008, true, 2013,
                elenco1, "Breaking Bad", ondeAssistir1, temporadas1);

        assertFalse(resultado);
    }

    @Test
    public void deveRemoverSerie(){
        assertTrue(controller.removerSerie(id1)); // Deve remover a série "Breaking Bad"
        assertFalse(controller.buscarSeries("5", Integer.toString(id1))); // Busca a série após ela ser removida
    }

    @Test
    public void naoDeveRemoverSerie(){
        assertFalse(controller.removerSerie(-1)); // Fornece um ID que não pertence a qualquer filme cadastrado
        assertFalse(controller.buscarSeries("6", "-1")); // Busca um filme inexistente
    }

    @Test
    public void deveCadastrarSerieComSomenteTituloOriginalIgual() {
        boolean resultado = controller.cadastrarSerie("Serie Teste", generos3, 1994, true, 2004,
                elenco3, "Friends", ondeAssistir2, temporadas3);

        assertTrue(resultado);
    }

    @Test
    public void deveCadastrarTemporadaParaSerie() {
        Temporada nova = new Temporada(2010, 12, 3);
        boolean resultado = controller.cadastrarTemporada(id1, nova);

        assertTrue(resultado);
        assertEquals(3, controller.getSeriesR().buscarId(id1).getTemporadas().size());
    }

    @Test
    public void deveAvaliarSerie() {
        Calendar data = Calendar.getInstance();
        boolean resultado = controller.avaliarSerie(id1, "Excelente série", data);

        assertTrue(resultado);
        Serie s = controller.getSeriesR().buscarId(id1);
        assertEquals("Excelente série", s.getReview());
        assertEquals(data, s.getDataVisto());
        assertTrue(s.isVisto());
    }

    @Test
    public void deveOrdenarSeriePorAvaliacao() {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(id1); // Indice 0: maior pontuação
        ids.add(id3); // Indice 1: segunda maior

        controller.avaliarSerie(id3, "Legal", Calendar.getInstance());
        controller.avaliarTemporada(id3, 1, "Boa", 4);
        controller.avaliarTemporada(id3, 2, "Sensacional", 4);

        controller.avaliarSerie(id1, "Excelente", Calendar.getInstance());
        controller.avaliarTemporada(id1, 1, "Muito bom", 5);
        controller.avaliarTemporada(id1, 2, "Show", 5);
        int cont = 0;

        for (Serie i : controller.getSeriesR().getSeries()) {
            System.out.println(i.toString());
            assertEquals(ids.get(cont), i.getId());
            if (cont == 1) break;
            cont++;
        }
    }


    @Test
    public void deveAvaliarTemporadaEDefinirMediaNaSerie() {
        controller.avaliarTemporada(id1, 1, "Boa", 4);
        controller.avaliarTemporada(id1, 2, "Mais ou menos", 2);

        assertEquals(3, controller.getSeriesR().buscarId(id1).getPontuacao()); // Média: (4+2)/2 = 3
    }

    @Test
    public void deveBuscarSeriePorTitulo() {
        boolean resultado = controller.buscarSeries("1", "Nosso");
        assertTrue(resultado);
    }

    @Test
    public void deveBuscarSeriePorGenero() {
        boolean resultado = controller.buscarSeries("3", "Drama");
        assertTrue(resultado);
    }

    @Test
    public void deveBuscarSeriePorAno() {
        boolean resultado = controller.buscarSeries("4", "2008");
        assertTrue(resultado);
    }

    @Test
    public void deveBuscarSeriePorAtor() {
        boolean resultado = controller.buscarSeries("2", "Aaron Paul");
        assertTrue(resultado);
    }

    @Test
    public void deveBuscarSeriePorId() {
        boolean resultado = controller.buscarSeries("6", String.valueOf(id1));
        assertTrue(resultado);
    }

    @Test
    public void naoDeveAvaliarSerieInexistente() {
        boolean resultado = controller.avaliarSerie(9999, "Review", Calendar.getInstance());
        assertFalse(resultado);
    }

    @Test
    public void naoDeveAvaliarTemporadaDeSerieInexistente() {
        boolean resultado = controller.avaliarTemporada(9999, 1, "Invalido", 5);
        assertFalse(resultado);
    }
}
