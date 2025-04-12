package Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;

public class Livro extends Registro{
    private String autor;
    private String editora;
    private String isbn;
    private boolean exemplar;

    public Livro(String titulo, HashSet<Genero> generos, int anoLancamento, boolean visto,
                 String autor, String editora, String isbn, boolean exemplar) {
        super(titulo, generos, anoLancamento, visto);
        this.autor = autor;
        this.editora = editora;
        this.isbn = isbn;
        this.exemplar = exemplar;
    }

    @Override
    public int compareTo(Registro inserido){ // Adiciona a ordenação por autor e/ou isbn, respectivamente
        int comparacao = super.compareTo(inserido);

        if (comparacao == 0){
            comparacao = this.getAutor().compareToIgnoreCase(((Livro) inserido).getAutor());
            if (comparacao != 0) return comparacao;

            return this.getIsbn().compareToIgnoreCase(((Livro) inserido).getIsbn());
        }

        return comparacao;
    }

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
                "Pontuação: " + getPontuacao() + '\n' +
                "Review: " + getReview() + '\n' +
                "Lido em: " + dataFormatada + '\n';
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isExemplar() {
        return exemplar;
    }

    public void setExemplar(boolean exemplar) {
        this.exemplar = exemplar;
    }
}
