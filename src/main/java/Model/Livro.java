package Model;

import java.text.SimpleDateFormat;
import java.util.HashSet;

/**
 * Representa um livro, que herda os atributos e comportamentos da classe Registro.
 * Adiciona informações específicas como autor, editora, ISBN e disponibilidade de exemplar.
 */
public class Livro extends Registro {
    private String autor;
    private String editora;
    private String isbn;
    private boolean exemplar;

    /**
     * Construtor da classe Livro.
     *
     * @param titulo        Título do livro.
     * @param generos       Conjunto de gêneros associados ao livro.
     * @param anoLancamento Ano de lançamento do livro.
     * @param visto         Indica se o livro já foi lido.
     * @param autor         Nome do autor.
     * @param editora       Nome da editora.
     * @param isbn          Código ISBN do livro.
     * @param exemplar      Indica se há exemplar disponível.
     */
    public Livro(String titulo, HashSet<Genero> generos, int anoLancamento, boolean visto,
                 String autor, String editora, String isbn, boolean exemplar) {
        super(titulo, generos, anoLancamento, visto);
        this.autor = autor;
        this.editora = editora;
        this.isbn = isbn;
        this.exemplar = exemplar;
    }

    // Construtor padrão para o Jackson
    public Livro(){

    }

    /**
     * Compara se dois livros são iguais com base no ISBN.
     *
     * @param obj Objeto a ser comparado.
     * @return true se os ISBNs forem iguais, ignorando maiúsculas e minúsculas.
     */
    @Override
    public boolean equals(Object obj) {
        Livro livro = (Livro) obj;
        return this.getIsbn().equalsIgnoreCase(livro.getIsbn());
    }

    /**
     * Compara este livro com outro registro para fins de ordenação.
     * A comparação é feita por ISBN, e em caso de empate, por pontuação, título e autor.
     *
     * @param inserido Registro a ser comparado.
     * @return Valor negativo, zero ou positivo se este livro for menor, igual ou maior que o registro especificado.
     */
    @Override
    public int compareTo(Registro inserido) {
        int comparacao, comparacaoIsbn;

        comparacao = this.getIsbn().compareToIgnoreCase(((Livro)inserido).getIsbn());
        comparacaoIsbn = comparacao;

        if (comparacao == 0)
            return comparacao;
        comparacao = super.compareTo(inserido);

        if (comparacao == 0)
            comparacao = this.getAutor().compareToIgnoreCase(((Livro) inserido).getAutor());

        if (comparacao == 0)
            return comparacaoIsbn;

        return comparacao;
    }

    /**
     * Retorna uma representação textual do livro, com todos os seus atributos.
     *
     * @return String com os dados formatados do livro.
     */
    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = (this.getDataVisto() != null) ? formato.format(getDataVisto().getTime()) : "--/--/----";

        return  "Título: " + getTitulo() + '\n' +
                "Gêneros: " + getGenero() + '\n' +
                "Ano de Lançamento: " + getAnoLancamento() + '\n' +
                "Visto: " + (isVisto() ? "Sim" : "Não") + '\n' +
                "Autor: " + getAutor() + '\n' +
                "Editora: " + getEditora() + '\n' +
                "ISBN: " + getIsbn() + '\n' +
                "Exemplar disponível: " + (isExemplar() ? "Sim" : "Não") + '\n' +
                "Pontuação: " + (getPontuacao() != 0 ? getPontuacao() : " ") + '\n' +
                "Review: " + getReview() + '\n' +
                "Lido em: " + dataFormatada + '\n';
    }

    /**
     * Obtém o nome do autor.
     *
     * @return Nome do autor.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Define o nome do autor.
     *
     * @param autor Nome do autor.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Obtém o nome da editora.
     *
     * @return Nome da editora.
     */
    public String getEditora() {
        return editora;
    }

    /**
     * Define o nome da editora.
     *
     * @param editora Nome da editora.
     */
    public void setEditora(String editora) {
        this.editora = editora;
    }

    /**
     * Obtém o código ISBN do livro.
     *
     * @return Código ISBN.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Define o código ISBN do livro.
     *
     * @param isbn Código ISBN.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Verifica se o exemplar está disponível.
     *
     * @return true se o exemplar estiver disponível, false caso contrário.
     */
    public boolean isExemplar() {
        return exemplar;
    }

    /**
     * Define a disponibilidade do exemplar.
     *
     * @param exemplar true para indicar exemplar disponível, false caso contrário.
     */
    public void setExemplar(boolean exemplar) {
        this.exemplar = exemplar;
    }
}
