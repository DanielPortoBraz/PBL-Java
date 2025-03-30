package Model;

import java.util.HashSet;

public class FilmeRepositorio {
    private HashSet<Filme> filmes;

    public FilmeRepositorio() {
        this.filmes = new HashSet<Filme>();
    }

    public boolean addFilme(Filme filme){
        return this.filmes.add(filme);
    }

    public boolean removeFilme(Filme filme){
        return this.filmes.remove(filme);
    }

    public HashSet<Filme> getFilmes() {
        return filmes;
    }
}