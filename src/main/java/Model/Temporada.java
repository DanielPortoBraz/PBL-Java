package Model;

/**
 * Representa uma temporada de uma série ou programa.
 * <p>
 * Esta classe contém informações sobre uma temporada, como o ano de exibição, a quantidade de episódios,
 * o número da temporada, a pontuação atribuída e a review (comentário) associada.
 * </p>
 */
public class Temporada {
    private int ano;
    private int quantEpisodios;
    private int numero;

    /** Pontuação atribuída à temporada (valor público). */
    public int pontuacao;

    /** Review (comentário) referente à temporada. */
    public String review = " ";

    /**
     * Constrói uma nova instância de Temporada com os parâmetros especificados.
     *
     * @param ano             O ano em que a temporada foi lançada.
     * @param quantEpisodios  A quantidade de episódios da temporada.
     * @param numero          O número que identifica a temporada.
     */
    public Temporada(int ano, int quantEpisodios, int numero) {
        this.ano = ano;
        this.quantEpisodios = quantEpisodios;
        this.numero = numero;
    }

    public Temporada(){

    }

    /**
     * Retorna uma representação em string da temporada.
     * <p>
     * A string inclui o número da temporada, a quantidade de episódios, o ano de lançamento,
     * a pontuação (caso diferente de zero) e a review.
     * </p>
     *
     * @return uma String representando os detalhes da temporada.
     */
    @Override
    public String toString(){
        return "\nTemporada: " + getNumero() + '\n' +
                "Episódios: " + getQuantEpisodios() + '\n' +
                "Ano: " + getAno() + '\n' +
                "Pontuação: " + (getPontuacao() != 0 ? getPontuacao() : " ") + '\n' +
                "Review: " + getReview() + '\n';
    }

    /**
     * Retorna o ano de lançamento da temporada.
     *
     * @return o ano da temporada.
     */
    public int getAno() {
        return ano;
    }

    /**
     * Define o ano de lançamento da temporada.
     *
     * @param ano o ano a ser definido.
     */
    public void setAno(int ano) {
        this.ano = ano;
    }

    /**
     * Retorna a quantidade de episódios da temporada.
     *
     * @return o número de episódios.
     */
    public int getQuantEpisodios() {
        return quantEpisodios;
    }

    /**
     * Define a quantidade de episódios da temporada.
     *
     * @param quantEpisodios o número de episódios a ser definido.
     */
    public void setQuantEpisodios(int quantEpisodios) {
        this.quantEpisodios = quantEpisodios;
    }

    /**
     * Retorna o número da temporada.
     *
     * @return o número da temporada.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Define o número da temporada.
     *
     * @param numero o número que identificará a temporada.
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Retorna a pontuação atribuída à temporada.
     *
     * @return a pontuação.
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * Define a pontuação para a temporada.
     *
     * @param pontuacao a pontuação a ser atribuída.
     */
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    /**
     * Retorna a review (comentário) da temporada.
     *
     * @return a review.
     */
    public String getReview() {
        return review;
    }

    /**
     * Define a review (comentário) para a temporada.
     *
     * @param review a review a ser definida.
     */
    public void setReview(String review) {
        this.review = review;
    }
}
