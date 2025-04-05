package Model;

import java.util.TreeSet;

public class SerieRepositorio {
    private TreeSet<Serie> series;

    public SerieRepositorio() {
        this.series = new TreeSet<Serie>();
    }

    public boolean addSerie(Serie serie){
        return this.series.add(serie);
    }

    public boolean removeSerie(Serie serie){
        return this.series.remove(serie);
    }

    public TreeSet<Serie> getSeries() {
        return series;
    }
}