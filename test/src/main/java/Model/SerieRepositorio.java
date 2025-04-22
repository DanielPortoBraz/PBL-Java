package Model;

import java.util.TreeSet;
import java.util.stream.Collectors;

public class SerieRepositorio {
    private TreeSet<Serie> series;

    public SerieRepositorio() {
        this.series = new TreeSet<Serie>();
    }

    // Adiciona de forma ordenada
    public boolean addSerie(Serie serie){
        return this.series.add(serie);
    }

    public boolean removeSerie(Serie serie){
        return this.series.remove(serie);
    }

    public TreeSet<Serie> getSeries() {
        return series;
    }

    public TreeSet<Serie> buscarTitulo(String titulo){
        return this.series.stream()
                .filter(serie -> serie.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public TreeSet<Serie> buscarGenero(Genero genero){
        return this.series.stream()
                .filter(serie -> serie.getGenero().contains(genero))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public TreeSet<Serie> buscarAno(int ano){
        return this.series.stream()
                .filter(serie -> serie.getAnoLancamento() == ano)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public TreeSet<Serie> buscarAtor(String nomeAtor) {
        String nomeLower = nomeAtor.toLowerCase();

        return this.series.stream()
                .filter(serie -> serie.getElenco().stream()
                        .anyMatch(ator -> ator.toLowerCase().contains(nomeLower)))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public TreeSet<Serie> buscarOndeAssistir(String ondeAssistir){
        return this.series.stream()
                .filter(serie -> serie.getOndeAssistir().contains(ondeAssistir))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public Serie buscarId(int id){
        for (Serie i : series){
            if (i.getId() == id) return i;
        }
        return null;
    }
}