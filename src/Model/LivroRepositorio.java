package Model;

import java.util.HashSet;

public class LivroRepositorio {
    private HashSet<Livro> livros;

    public LivroRepositorio() {
        this.livros = new HashSet<Livro>();
    }

    public boolean addLivro(Livro livro){
        return this.livros.add(livro);
    }

    public boolean removeLivro(Livro livro){
        return this.livros.remove(livro);
    }

    public HashSet<Livro> getLivros() {
        return livros;
    }
}
