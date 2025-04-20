package Model;

import java.util.TreeSet;
import java.util.stream.Collectors;

public class LivroRepositorio {
    private TreeSet<Livro> livros;

    public LivroRepositorio() {
        this.livros = new TreeSet<Livro>();
    }

    public boolean addLivro(Livro livro){
        return this.livros.add(livro);
    }

    public boolean removeLivro(Livro livro){
        return this.livros.remove(livro);
    }

    public TreeSet<Livro> getLivros() {
        return livros;
    }

    public TreeSet<Livro> buscarTitulo(String titulo){
        return this.livros.stream()
                .filter(livro -> livro.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public TreeSet<Livro> buscarAutor(String autor){
        return this.livros.stream()
                .filter(livro -> livro.getAutor().toLowerCase().contains(autor.toLowerCase()))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public TreeSet<Livro> buscarGenero(Genero genero){
        return this.livros.stream()
                .filter(livro -> livro.getGenero().contains(genero))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public TreeSet<Livro> buscarAno(int ano){
        return this.livros.stream()
                .filter(livro -> livro.getAnoLancamento() == ano)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public Livro buscarIsbn(String isbn){
        for (Livro i : livros){
            if (i.getIsbn().equalsIgnoreCase(isbn)) return i;
        }
        return null;
    }
}
