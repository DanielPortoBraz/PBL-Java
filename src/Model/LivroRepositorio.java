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

    public TreeSet<Livro> buscarTitulo(String titulo){
        TreeSet<Livro> livrosFiltrados = new TreeSet<>();

        for (Livro i : this.livros){

            if (i.getTitulo().equalsIgnoreCase(titulo)){
                livrosFiltrados.add(i);
            }
        }
        return livrosFiltrados;
    }

    public TreeSet<Livro> getLivros() {
        return livros;
    }
}
