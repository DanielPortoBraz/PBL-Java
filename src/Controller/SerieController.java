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

    public void buscarSeries(int categoria, String filtro){

        switch(categoria){
            case 1: // Titulo
                seriesR.buscarTitulo(filtro).forEach(System.out::println);
                break;

            case 2: // Ator
                seriesR.buscarAtor(filtro).forEach(System.out::println);
                break;

            case 3: // Gênero
                //seriesR.buscarGenero(filtro).forEach(System.out::println);
                break;

            case 4: // Ano
                int filtroNum = Integer.parseInt(filtro);
                seriesR.buscarAno(filtroNum).forEach(System.out::println);
                break;

            case 5: // Onde assistir
                seriesR.buscarOndeAssistir(filtro).forEach(System.out::println);
                break;

            default:
                System.out.println("Não encontrado.");

        }
    }

    public void listarSeries(){
        for (Serie i : seriesR.getSeries()){
            System.out.println(i.toString());
        }
    }
}
