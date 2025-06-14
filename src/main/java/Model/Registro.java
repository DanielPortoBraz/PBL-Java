package Model;

import java.util.Calendar;
import java.util.HashSet;

/**
 * Classe base que representa um registro genérico de mídia, como livros ou filmes.
 * Implementa {@code Comparable<Registro>} para permitir ordenação em estruturas como {@code TreeSet}.
 */
public class Registro implements Comparable<Registro> {
    private String titulo;
    private HashSet<Genero> generos;
    private int anoLancamento;
    private boolean visto; // Indica se o registro foi visto

    private Calendar dataVisto;
    public String review = " ";
    public int pontuacao; // Inicia com valor 0

    /**
     * Construtor da classe Registro.
     *
     * @param titulo        Título do registro.
     * @param generos       Conjunto de gêneros associados ao registro.
     * @param anoLancamento Ano de lançamento do registro.
     * @param visto         Indica se o registro foi visto.
     */
    public Registro(String titulo, HashSet<Genero> generos, int anoLancamento, boolean visto) {
        this.titulo = titulo;
        this.generos = new HashSet<>(generos);
        this.anoLancamento = anoLancamento;
        this.visto = visto;
    }

    // Construtor padrão com nenhum argumento necessário para o Jackson
    public Registro(){

    }

    /**
     * Compara dois registros para ordenação.
     * A comparação é feita com base na pontuação (ordem decrescente) e, em caso de empate, pelo título (ordem alfabética).
     *
     * @param inserido Registro a ser comparado.
     * @return Valor negativo, zero ou positivo conforme este objeto seja menor, igual ou maior.
     */
    @Override
    public int compareTo(Registro inserido) {
        int comparacao = Integer.compare(inserido.getPontuacao(), this.getPontuacao());

        if (comparacao != 0) return comparacao;

        return this.getTitulo().compareToIgnoreCase(inserido.getTitulo());
    }

    /**
     * Retorna a review do registro.
     *
     * @return Review em formato de texto.
     */
    public String getReview() {
        return review;
    }

    /**
     * Define a review do registro.
     *
     * @param review Texto da review.
     */
    public void setReview(String review) {
        this.review = review;
    }

    /**
     * Retorna o título do registro.
     *
     * @return Título como string.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Define o título do registro.
     *
     * @param titulo Título a ser definido.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Retorna o conjunto de gêneros do registro.
     *
     * @return Conjunto de objetos do tipo {@code Genero}.
     */
    public HashSet<Genero> getGenero() {
        return generos;
    }

    /**
     * Define o conjunto de gêneros do registro.
     *
     * @param generos Conjunto de gêneros.
     */
    public void setGenero(HashSet<Genero> generos) {
        this.generos = generos;
    }

    /**
     * Retorna o ano de lançamento.
     *
     * @return Ano como inteiro.
     */
    public int getAnoLancamento() {
        return anoLancamento;
    }

    /**
     * Define o ano de lançamento.
     *
     * @param anoLancamento Ano a ser definido.
     */
    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    /**
     * Verifica se o registro foi visto.
     *
     * @return true se foi visto, false caso contrário.
     */
    public boolean isVisto() {
        return visto;
    }

    /**
     * Define o status de visualização do registro.
     *
     * @param visto true se foi visto, false caso contrário.
     */
    public void setVisto(boolean visto) {
        this.visto = visto;
    }

    /**
     * Retorna a pontuação do registro.
     *
     * @return Valor inteiro da pontuação.
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * Define a pontuação do registro.
     *
     * @param pontuacao Valor da pontuação.
     */
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    /**
     * Retorna a data em que o registro foi visto.
     *
     * @return Objeto {@code Calendar} representando a data.
     */
    public Calendar getDataVisto() {
        return dataVisto;
    }

    /**
     * Define a data em que o registro foi visto.
     *
     * @param dataVisto Objeto {@code Calendar} com a data.
     */
    public void setDataVisto(Calendar dataVisto) {
        this.dataVisto = dataVisto;
    }
}
