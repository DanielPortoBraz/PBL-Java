package Model;

import java.util.HashSet;

public class TemporadaRepositorio {
    private HashSet<Temporada> temporadas;

    public TemporadaRepositorio() {
        this.temporadas = new HashSet<Temporada>();
    }

    public boolean addTemporada(Temporada temporada){
        return this.temporadas.add(temporada);
    }

    public boolean removeTemporada(Temporada temporada){
        return this.temporadas.remove(temporada);
    }

    public HashSet<Temporada> getTemporadas() {
        return temporadas;
    }
}