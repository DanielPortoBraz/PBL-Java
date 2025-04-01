package Model;

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
