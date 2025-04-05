package Model;

import java.util.TreeSet;

public class FilmeRepositorio {
    private TreeSet<Filme> filmes;

    public FilmeRepositorio() {
        this.filmes = new TreeSet<Filme>();
    }

    public boolean addFilme(Filme filme){
        return this.filmes.add(filme);
    }

    public boolean removeFilme(Filme filme){
        return this.filmes.remove(filme);
    }

    public TreeSet<Filme> getFilmes() {
        return filmes;
    }
}