package Controller;

import Model.*;

import java.util.HashSet;
import java.util.TreeSet;

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

    public void cadastrarTemporada(int id, int ano, int quantEpisodios, int numero){
        seriesR.buscarId(id).addTemporada(new Temporada(ano, quantEpisodios, numero));
    }

    public boolean buscarSeries(int categoria, String filtro){
        TreeSet<Serie> seriesEncontradas;
        Serie serieEncontrada;
        int filtroNum;

        switch(categoria){
            case 1: // Titulo
                seriesEncontradas = seriesR.buscarTitulo(filtro);

                if (!seriesEncontradas.isEmpty()){
                    seriesEncontradas.forEach(System.out::println);
                    return true;
                }
                break;

            case 2: // Ator
                seriesEncontradas = seriesR.buscarAtor(filtro);

                if (!seriesEncontradas.isEmpty()){
                    seriesEncontradas.forEach(System.out::println);
                    return true;
                }
                break;

            case 3: // Gênero
                for (Genero i : Genero.values()){

                    if (filtro.equalsIgnoreCase(i.getNomeFormatado())){
                        seriesEncontradas = seriesR.buscarGenero(i);
                        seriesEncontradas.forEach(System.out::println);
                        return true;
                    }
                }
                break;

            case 4: // Ano
                filtroNum = Integer.parseInt(filtro);
                seriesEncontradas = seriesR.buscarAno(filtroNum);

                if (!seriesEncontradas.isEmpty()){
                    seriesEncontradas.forEach(System.out::println);
                    return true;
                }
                break;

            case 5: // Onde assistir
                seriesEncontradas = seriesR.buscarOndeAssistir(filtro);

                if (!seriesEncontradas.isEmpty()){
                    seriesEncontradas.forEach(System.out::println);
                    return true;
                }
                break;

            case 6: // ID
                filtroNum = Integer.parseInt(filtro);
                serieEncontrada = seriesR.buscarId(filtroNum);

                if (serieEncontrada != null){
                    System.out.println(serieEncontrada);
                    return true;
                }
                break;

            default:
                System.out.println("Categoria inexistente.");
        }

        return false;
    }

    public void listarSeries(){
        for (Serie i : seriesR.getSeries()){
            System.out.println(i.toString());
        }
    }

    public boolean avaliarSerie(int id, String reviewSerie, int numero, String reviewTemporada, int pontuacao){
        Serie serieAvaliada = seriesR.buscarId(id);
        HashSet<Temporada> temporadas = serieAvaliada.getTemporadas();
        int pontuacaoTotal = 0;
        int quantTemporadas = temporadas.size();

        if (serieAvaliada != null) {
            serieAvaliada.setVisto(true); // Marca a série como vista
            serieAvaliada.setReview(reviewSerie);// Atualiza a review da série
            return true;
        }

        else
            System.out.println("Série não encontrada. Não foi possível realizar a avaliação.");
        return false;
    }

    public boolean avaliarTemporada(int id, int numero, String reviewTemporada, int pontuacao){
        Serie serieAvaliada = seriesR.buscarId(id);
        HashSet<Temporada> temporadas = serieAvaliada.getTemporadas();
        int pontuacaoTotal = 0;
        int quantTemporadas = temporadas.size();

        if (temporadas != null) {

            for (Temporada i : temporadas) {

                if (i.getNumero() == numero) {
                    i.setReview(reviewTemporada); // Atualiza a review da temporada
                    i.setPontuacao(pontuacao); // Atualiza a pontuação da temporada
                }

                if (i.getPontuacao() != 0) // Se a temporada foi avaliada
                    pontuacaoTotal += i.getPontuacao();
            }

            if (quantTemporadas != 0)
                // Atualiza a pontuação da Série pela média das pontuações das temporadas
                serieAvaliada.setPontuacao(pontuacaoTotal / quantTemporadas);
            return true;
        }

        else
            System.out.println("Temporada não encontrada. Não foi possível realizar a avaliação.");
        return false;
    }
}
