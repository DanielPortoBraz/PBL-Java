package Model;

import java.util.HashSet;

public class SerieRepositorio {
    private HashSet<Serie> series;

    public SerieRepositorio() {
        this.series = new HashSet<Serie>();
    }

    public boolean addSerie(Serie serie){
        return this.series.add(serie);
    }

    public boolean removeSerie(Serie serie){
        return this.series.remove(serie);
    }

    public HashSet<Serie> getSeries() {
        return series;
    }
}