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

    public boolean buscarSeries(int categoria, String filtro){
        TreeSet<Serie> seriesEncontradas;
        Serie serieEncontrada;

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
                int filtroNum = Integer.parseInt(filtro);
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

            for (Temporada i : temporadas){

                if (i.getNumero() == numero){
                    i.setReview(reviewTemporada);
                    i.setPontuacao(pontuacao);
                }
                pontuacaoTotal += i.getPontuacao();
            }

            if (quantTemporadas != 0)
                serieAvaliada.setPontuacao(pontuacaoTotal / quantTemporadas);
            return true;
        }

        else
            System.out.println("Série não encontrada. Não foi possível realizar a avaliação.");
        return false;
    }
}
