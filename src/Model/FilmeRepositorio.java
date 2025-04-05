package Model;

import java.util.TreeSet;
import java.util.stream.Collectors;

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

    public TreeSet<Filme> buscarTitulo(String titulo){
        return this.filmes.stream()
                .filter(filme -> filme.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public TreeSet<Filme> buscarGenero(Genero genero){
        return this.filmes.stream()
                .filter(filme -> filme.getGenero().contains(genero))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public TreeSet<Filme> buscarAno(int ano){
        return this.filmes.stream()
                .filter(filme -> filme.getAnoLancamento() == ano)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public TreeSet<Filme> buscarDiretor(String nomeDiretor) {
        String nomeLower = nomeDiretor.toLowerCase();

        return this.filmes.stream()
                .filter(filme ->
                        filme.getDirecao().stream()
                                .anyMatch(diretor -> diretor.toLowerCase().contains(nomeLower))
                )
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public TreeSet<Filme> buscarAtor(String nomeAtor) {
        String nomeLower = nomeAtor.toLowerCase();

        return this.filmes.stream()
                .filter(filme -> filme.getElenco().stream()
                        .anyMatch(ator -> ator.toLowerCase().contains(nomeLower))
                )
                .collect(Collectors.toCollection(TreeSet::new));
    }
}