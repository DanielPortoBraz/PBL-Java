package Controller;

import Model.Genero;
import Model.FilmeRepositorio;
import Model.Filme;
import Model.Filme;

import java.util.Calendar;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Controlador responsável pela lógica de manipulação dos objetos do tipo {@link Filme}.
 * Ele interage com o repositório de filmes para realizar operações como cadastro, busca,
 * listagem e avaliação.
 */
public class FilmeController {

    /** Repositório responsável por armazenar os filmes. */
    private FilmeRepositorio filmesR;

    /**
     * Construtor padrão que inicializa o repositório de filmes.
     */
    public FilmeController() {
        this.filmesR = new FilmeRepositorio();
    }

    /**
     * Cadastra um novo filme no repositório.
     *
     * @param titulo           Título do filme.
     * @param generos          Conjunto de gêneros associados ao filme.
     * @param anoLancamento    Ano de lançamento do filme.
     * @param visto            Indica se o filme já foi assistido.
     * @param tempoDuracao     Duração do filme em minutos.
     * @param direcao          Conjunto de nomes dos diretores.
     * @param roteiro          Conjunto de nomes dos roteiristas.
     * @param elenco           Conjunto de nomes dos atores.
     * @param tituloOriginal   Título original do filme (caso diferente do traduzido).
     * @param ondeAssistir     Conjunto de plataformas onde o filme está disponível.
     * @return {@code true} se o filme foi cadastrado com sucesso; {@code false} caso contrário.
     */
    public boolean cadastrarFilme(String titulo, HashSet<Genero> generos, int anoLancamento,
                                  boolean visto, int tempoDuracao, HashSet<String> direcao,
                                  HashSet<String> roteiro, HashSet<String> elenco,
                                  String tituloOriginal, HashSet<String> ondeAssistir) {
        return filmesR.addFilme(new Filme(titulo, generos, anoLancamento, visto, tempoDuracao,
                direcao, roteiro, elenco, tituloOriginal, ondeAssistir));
    }

    /**
     * Remove um Filme cadastrado baseado no seu ID
     * @param id ID do Filme que se deseja remover
     * @return {@code true} se foi possível remover o Filme pelo ID; {@code false} caso o filme não tenha sido encontrado
     */
    public boolean removerFilme(int id){
        Filme filmeRemovido = filmesR.buscarId(id);

        if (filmeRemovido != null)
            return filmesR.removeFilme(filmeRemovido);

        return false;
    }

    public boolean salvarFilmes(){
        return filmesR.salvarFilmes();
    }

    public boolean importarFilmes(){
        return filmesR.carregarFilmes();
    }

    /**
     * Busca filmes com base em uma categoria e um filtro específico.
     *
     * <p>As categorias possíveis são:
     * <ul>
     *     <li>"1" - Título</li>
     *     <li>"2" - Ator</li>
     *     <li>"3" - Gênero</li>
     *     <li>"4" - Ano de lançamento</li>
     *     <li>"5" - Diretor</li>
     *     <li>"6" - ID</li>
     * </ul>
     *
     * @param categoria Categoria da busca (representada por um número em formato {@code String}).
     * @param filtro    Valor do filtro correspondente à categoria.
     * @return {@code true} se algum filme foi encontrado; {@code false} caso contrário.
     */
    public boolean buscarFilmes(String categoria, String filtro) {
        TreeSet<Filme> filmesEncontrados;
        Filme filmeEncontrado;
        int filtroNum;

        switch (categoria) {
            case "1": // Título
                filmesEncontrados = filmesR.buscarTitulo(filtro);
                if (!filmesEncontrados.isEmpty()) {
                    filmesEncontrados.forEach(System.out::println);
                    return true;
                }
                break;

            case "2": // Ator
                filmesEncontrados = filmesR.buscarAtor(filtro);
                if (!filmesEncontrados.isEmpty()) {
                    filmesEncontrados.forEach(System.out::println);
                    return true;
                }
                break;

            case "3": // Gênero
                for (Genero i : Genero.values()) {
                    if (filtro.equalsIgnoreCase(i.getNomeFormatado())) {
                        filmesEncontrados = filmesR.buscarGenero(i);
                        filmesEncontrados.forEach(System.out::println);
                        return true;
                    }
                }
                break;

            case "4": // Ano
                filtroNum = Integer.parseInt(filtro);
                filmesEncontrados = filmesR.buscarAno(filtroNum);
                if (!filmesEncontrados.isEmpty()) {
                    filmesEncontrados.forEach(System.out::println);
                    return true;
                }
                break;

            case "5": // Diretor
                filmesEncontrados = filmesR.buscarDiretor(filtro);
                if (!filmesEncontrados.isEmpty()) {
                    filmesEncontrados.forEach(System.out::println);
                    return true;
                }
                break;

            case "6": // ID
                filtroNum = Integer.parseInt(filtro);
                filmeEncontrado = filmesR.buscarId(filtroNum);
                if (filmeEncontrado != null) {
                    System.out.println(filmeEncontrado);
                    return true;
                }
                break;

            default:
                System.out.println("Categoria Inexistente.");
        }

        return false;
    }

    /**
     * Lista todos os filmes cadastrados no repositório.
     * Os filmes são listados em ordem, conforme a ordenação natural definida na classe {@link Filme}.
     */
    public void listarFilmes() {
        for (Filme i : filmesR.getFilmes()) {
            System.out.println(i.toString());
        }
    }

    /**
     * Avalia um filme a partir do seu ID.
     * Atualiza os dados de visualização, pontuação, review e data de visualização.
     *
     * @param id          ID do filme a ser avaliado.
     * @param review      Texto com a review do filme.
     * @param pontuacao   Nota atribuída ao filme.
     * @param dataVisto   Data em que o filme foi assistido.
     * @return {@code true} se o filme foi encontrado e avaliado; {@code false} caso contrário.
     */
    public boolean avaliarFilme(int id, String review, int pontuacao, Calendar dataVisto) {
        Filme filmeAvaliado = filmesR.buscarId(id);

        if (filmeAvaliado != null) {
            filmesR.removeFilme(filmeAvaliado);
            filmeAvaliado.setVisto(true);
            filmeAvaliado.setDataVisto(dataVisto);
            filmeAvaliado.setReview(review);
            filmeAvaliado.setPontuacao(pontuacao);
            filmesR.addFilme(filmeAvaliado);

            return true;
        } else {
            return false;
        }
    }

    /**
     * Retorna o repositório de filmes utilizado internamente.
     * Método utilizado apenas para fins de teste.
     *
     * @return O repositório de filmes atual.
     */
    public FilmeRepositorio getFilmesR() {
        return filmesR;
    }
}
