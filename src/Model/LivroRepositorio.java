package Model;

import java.util.TreeSet;

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
}
