package Controller;

import Model.Genero;
import Model.SerieRepositorio;
import Model.Serie;
import Model.Temporada;

import java.util.HashSet;

public class SerieController {
    private SerieRepositorio seriesR;

    public SerieController(){
        this.seriesR = new SerieRepositorio();
    }

    public void cadastrarSerie(String titulo, HashSet<Genero> generos, int anoLancamento,
                               boolean visto, int anoEncerramento,
                               HashSet<String> elenco, String tituloOriginal,
                               HashSet<String> ondeAssistir, HashSet<Temporada> temporadas){
        seriesR.addSerie(new Serie(titulo, generos, anoLancamento, visto, anoEncerramento,
                elenco, tituloOriginal, ondeAssistir, temporadas));
    }
}
