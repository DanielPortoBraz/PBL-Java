package Controller;

import Model.*;

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

    public void avaliarSerie(String titulo, String reviewSerie, int numero, String reviewTemporada, int pontuacao){
        Serie serieAvaliada = seriesR.buscarTitulo(titulo).getFirst();
        HashSet<Temporada> temporadas = serieAvaliada.getTemporadas();
        int pontuacaoTotal = 0;
        int quantTemporadas = temporadas.size();

        if (serieAvaliada != null) {
            serieAvaliada.setVisto(true); // Marca a série como vista
            serieAvaliada.setReview(reviewSerie);// Atualiza a review da série

            for (Temporada i : temporadas){

                if (i.getNumero() == numero){
                    i.setReview(reviewTemporada);
                    i.setPontuacao(pontuacao);
                }
                pontuacaoTotal += i.getPontuacao();
            }

            if (quantTemporadas != 0)
                serieAvaliada.setPontuacao(pontuacaoTotal / quantTemporadas);
        }

        else
            System.out.println("Série não encontrada. Não foi possível realizar a avaliação.");
    }
}
