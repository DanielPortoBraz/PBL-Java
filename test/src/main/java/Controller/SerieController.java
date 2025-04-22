package Controller;

import Model.Serie;
import Model.SerieRepositorio;
import Model.Genero;
import Model.Temporada;

import java.util.Calendar;
import java.util.HashSet;
import java.util.TreeSet;

public class SerieController {
    private SerieRepositorio seriesR;

    public SerieController(){
        this.seriesR = new SerieRepositorio();
    }

    // Retorna true caso consiga cadastrar a Série, e false para o contrário
    public boolean cadastrarSerie(String titulo, HashSet<Genero> generos, int anoLancamento,
                               boolean visto, int anoEncerramento,
                               HashSet<String> elenco, String tituloOriginal,
                               HashSet<String> ondeAssistir, HashSet<Temporada> temporadas){
        return seriesR.addSerie(new Serie(titulo, generos, anoLancamento, visto, anoEncerramento,
                elenco, tituloOriginal, ondeAssistir, temporadas));
    }

    // Retorna true caso consiga cadastrar a Temporada da Série pelo i, e false para o contrário
    public boolean cadastrarTemporada(int id, Temporada temporada){
        return seriesR.buscarId(id).addTemporada(temporada);
    }

    // Busca séries a partir da categoria e o filtro. Categoria indica o atributo e filtro indica o valor do atributo
    // Retorna true caso consiga encontrar alguma Série, e false caso contrário
    public boolean buscarSeries(String categoria, String filtro){
        TreeSet<Serie> seriesEncontradas;
        Serie serieEncontrada;
        int filtroNum;

        switch(categoria){
            case "1": // Titulo
                seriesEncontradas = seriesR.buscarTitulo(filtro);

                if (!seriesEncontradas.isEmpty()){
                    seriesEncontradas.forEach(System.out::println);
                    return true;
                }
                break;

            case "2": // Ator
                seriesEncontradas = seriesR.buscarAtor(filtro);

                if (!seriesEncontradas.isEmpty()){
                    seriesEncontradas.forEach(System.out::println);
                    return true;
                }
                break;

            case "3": // Gênero
                for (Genero i : Genero.values()){

                    if (filtro.equalsIgnoreCase(i.getNomeFormatado())){
                        seriesEncontradas = seriesR.buscarGenero(i);
                        seriesEncontradas.forEach(System.out::println);
                        return true;
                    }
                }
                break;

            case "4": // Ano
                filtroNum = Integer.parseInt(filtro);
                seriesEncontradas = seriesR.buscarAno(filtroNum);

                if (!seriesEncontradas.isEmpty()){
                    seriesEncontradas.forEach(System.out::println);
                    return true;
                }
                break;

            case "5": // Onde assistir
                seriesEncontradas = seriesR.buscarOndeAssistir(filtro);

                if (!seriesEncontradas.isEmpty()){
                    seriesEncontradas.forEach(System.out::println);
                    return true;
                }
                break;

            case "6": // ID
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

    // Lista todas as séries. A lista já vem ordenada devido ao TreeSet
    public void listarSeries(){
        for (Serie i : seriesR.getSeries()){
            System.out.println(i.toString());
        }
    }

    // Retorna true caso consigo encontrar a Série e avaliar pelo Id, retorna false caso contrário
    public boolean avaliarSerie(int id, String reviewSerie, Calendar dataVisto){
        Serie serieAvaliada = seriesR.buscarId(id);

        if (serieAvaliada != null) {
            serieAvaliada.setVisto(true); // Marca a série como vista
            serieAvaliada.setDataVisto(dataVisto); // Atualiza a data que a série foi vista
            serieAvaliada.setReview(reviewSerie);// Atualiza a review da série
            return true;
        }

        else
            return false;
    }


    // Retorna true caso consigo encontrar a Temporada da Série e avaliar pelo ID, retorna false caso contrário
    public boolean avaliarTemporada(int id, int numero, String reviewTemporada, int pontuacao){
        Serie serieAvaliada = seriesR.buscarId(id);
        int pontuacaoTotal = 0;

        if (serieAvaliada != null) {
            HashSet<Temporada> temporadas = serieAvaliada.getTemporadas();
            int quantTemporadas = temporadas.size();

            if (!temporadas.isEmpty()) {

                for (Temporada i : temporadas) {

                    if (i.getNumero() == numero) {
                        i.setReview(reviewTemporada); // Atualiza a review da temporada
                        i.setPontuacao(pontuacao); // Atualiza a pontuação da temporada
                    }

                    if (i.getPontuacao() != 0) // Se a temporada foi avaliada
                        pontuacaoTotal += i.getPontuacao();
                }

                if (quantTemporadas != 0) {
                    seriesR.removeSerie(serieAvaliada);
                    serieAvaliada.setPontuacao(pontuacaoTotal / quantTemporadas); // Atualiza a pontuação da Série pela média das pontuações das temporadas
                    seriesR.addSerie(serieAvaliada);
                    return true;
                }
            }
        }
        return false;
    }

    // Retorna o repositorio atual. Usado para testes
    public SerieRepositorio getSeriesR(){
        return seriesR;
    }
}
