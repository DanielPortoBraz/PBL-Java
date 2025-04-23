package Model;

import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Classe responsável por gerenciar um repositório de séries. As séries são armazenadas de maneira ordenada
 * usando um {@link TreeSet}, garantindo que as séries sejam mantidas em ordem natural.
 * Oferece métodos para adicionar, remover e buscar séries com base em diferentes critérios.
 */
public class SerieRepositorio {
    private TreeSet<Serie> series;

    /**
     * Construtor da classe, inicializa o repositório de séries.
     */
    public SerieRepositorio() {
        this.series = new TreeSet<Serie>();
    }

    /**
     * Adiciona uma série ao repositório de forma ordenada.
     *
     * @param serie A série a ser adicionada.
     * @return {@code true} se a série foi adicionada com sucesso,
     *         {@code false} se a série já estiver presente no repositório.
     */
    public boolean addSerie(Serie serie){
        return this.series.add(serie);
    }

    /**
     * Remove uma série do repositório.
     *
     * @param serie A série a ser removida.
     * @return {@code true} se a série foi removida com sucesso,
     *         {@code false} se a série não estava presente no repositório.
     */
    public boolean removeSerie(Serie serie){
        return this.series.remove(serie);
    }

    /**
     * Retorna todas as séries armazenadas no repositório.
     *
     * @return Um {@link TreeSet} contendo todas as séries no repositório.
     */
    public TreeSet<Serie> getSeries() {
        return series;
    }

    /**
     * Busca séries pelo título. A busca é feita de maneira insensível a maiúsculas e minúsculas.
     *
     * @param titulo O título a ser buscado.
     * @return Um {@link TreeSet} contendo as séries cujo título contém a string fornecida.
     */
    public TreeSet<Serie> buscarTitulo(String titulo){
        return this.series.stream()
                .filter(serie -> serie.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Busca séries pelo gênero.
     *
     * @param genero O gênero a ser buscado.
     * @return Um {@link TreeSet} contendo as séries que possuem o gênero fornecido.
     */
    public TreeSet<Serie> buscarGenero(Genero genero){
        return this.series.stream()
                .filter(serie -> serie.getGenero().contains(genero))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Busca séries pelo ano de lançamento.
     *
     * @param ano O ano de lançamento a ser buscado.
     * @return Um {@link TreeSet} contendo as séries lançadas no ano fornecido.
     */
    public TreeSet<Serie> buscarAno(int ano){
        return this.series.stream()
                .filter(serie -> serie.getAnoLancamento() == ano)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Busca séries pelo nome de um ator. A busca é feita de maneira insensível a maiúsculas e minúsculas.
     *
     * @param nomeAtor O nome do ator a ser buscado.
     * @return Um {@link TreeSet} contendo as séries que possuem o ator cujo nome contém a string fornecida.
     */
    public TreeSet<Serie> buscarAtor(String nomeAtor) {
        String nomeLower = nomeAtor.toLowerCase();

        return this.series.stream()
                .filter(serie -> serie.getElenco().stream()
                        .anyMatch(ator -> ator.toLowerCase().contains(nomeLower)))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Busca séries pelo nome da plataforma ou serviço onde podem ser assistidas.
     *
     * @param ondeAssistir O nome do serviço ou plataforma de exibição.
     * @return Um {@link TreeSet} contendo as séries disponíveis no serviço ou plataforma fornecido.
     */
    public TreeSet<Serie> buscarOndeAssistir(String ondeAssistir){
        return this.series.stream()
                .filter(serie -> serie.getOndeAssistir().contains(ondeAssistir))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Busca uma série pelo ID.
     *
     * @param id O ID da série a ser buscada.
     * @return A série correspondente ao ID fornecido, ou {@code null} se não encontrado.
     */
    public Serie buscarId(int id){
        for (Serie i : series){
            if (i.getId() == id) return i;
        }
        return null;
    }
}
