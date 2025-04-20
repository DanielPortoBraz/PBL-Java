package Controller;

import Model.TemporadaRepositorio;
import Model.Temporada;

public class TemporadaController {
    private TemporadaRepositorio temporadasR;

    public TemporadaController(){
        this.temporadasR = new TemporadaRepositorio();
    }

    public void cadastrarTemporada(int ano, int quantEpisodios, int numero){
        temporadasR.addTemporada(new Temporada(ano, quantEpisodios, numero));
    }
}
