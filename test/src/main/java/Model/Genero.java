package Model;

/**
 * Enumeração que representa os diferentes gêneros de livro, séries ou filmes.
 * Cada gênero possui um nome formatado para exibição e um número associado.
 */
public enum Genero {
    ACAO("Ação", 1),
    AVENTURA("Aventura", 2),
    ANIMACAO("Animação", 3),
    BIOGRAFIA("Biografia", 4),
    COMEDIA("Comédia", 5),
    DOCUMENTARIO("Documentário", 6),
    DRAMA("Drama", 7),
    FANTASIA("Fantasia", 8),
    FICCAO_CIENTIFICA("Ficção Científica", 9),
    GUERRA("Guerra", 10),
    HISTORICO("Histórico", 11),
    MUSICAL("Musical", 12),
    MISTERIO("Mistério", 13),
    POLICIAL("Policial", 14),
    ROMANCE("Romance", 15),
    SUSPENSE("Suspense", 16),
    TERROR("Terror", 17),
    THRILLER("Thriller", 18),
    FAROESTE("Faroeste", 19);

    private final String nomeFormatado;
    private final int numero;

    /**
     * Construtor da enumeração que inicializa os valores do nome formatado
     * e número do gênero.
     *
     * @param nomeFormatado Nome formatado do gênero
     * @param numero Número associado ao gênero
     */
    Genero(String nomeFormatado, int numero) {
        this.nomeFormatado = nomeFormatado;
        this.numero = numero;
    }

    /**
     * Retorna o nome formatado do gênero.
     *
     * @return Nome formatado do gênero
     */
    public String getNomeFormatado() {
        return nomeFormatado;
    }

    /**
     * Retorna o número associado ao gênero.
     *
     * @return Número associado ao gênero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Retorna uma representação em String do gênero, que é o seu nome formatado.
     *
     * @return Nome formatado do gênero
     */
    @Override
    public String toString() {
        return nomeFormatado;
    }
}
