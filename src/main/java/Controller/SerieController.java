package Controller;

import Model.*;

import java.util.Calendar;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Classe responsável por controlar as operações relacionadas a Séries,
 * como cadastro, busca, avaliação e listagem.
 */
public class SerieController {
    private SerieRepositorio seriesR;

    /**
     * Construtor padrão que inicializa o repositório de séries.
     */
    public SerieController(){
        this.seriesR = new SerieRepositorio();
    }

    /**
     * Cadastra uma nova série no repositório.
     *
     * @param titulo Título da série
     * @param generos Conjunto de gêneros da série
     * @param anoLancamento Ano de lançamento
     * @param visto Indica se a série já foi vista
     * @param anoEncerramento Ano de encerramento da série
     * @param elenco Elenco da série
     * @param tituloOriginal Título original da série
     * @param ondeAssistir Plataformas onde a série está disponível
     * @param temporadas Conjunto de temporadas da série
     * @return true se a série foi cadastrada com sucesso, false caso contrário
     */
    public boolean cadastrarSerie(String titulo, HashSet<Genero> generos, int anoLancamento,
                                  boolean visto, int anoEncerramento,
                                  HashSet<String> elenco, String tituloOriginal,
                                  HashSet<String> ondeAssistir, HashSet<Temporada> temporadas){
         return seriesR.addSerie(new Serie(titulo, generos, anoLancamento, visto, anoEncerramento,
                elenco, tituloOriginal, ondeAssistir, temporadas)) && !temporadas.isEmpty();
    }

    /**
     * Remove uma Serie cadastrada baseada no seu ID
     * @param id ID da Serie que se deseja remover
     * @return {@code true} se foi possível remover a Serie pelo ID; {@code false} caso a série não tenha sido encontrada
     */
    public boolean removerSerie(int id){
        Serie serieRemovido = seriesR.buscarId(id);

        if (serieRemovido != null)
            return seriesR.removeSerie(serieRemovido);

        return false;
    }

    /**
     * Salva a lista de séries utilizando o repositório de séries.
     *
     * @return {@code true} se as séries foram salvas com sucesso, {@code false} caso contrário.
     */
    public boolean salvarSeries() {
        return seriesR.salvarSeries();
    }

    /**
     * Importa a lista de séries a partir do repositório.
     *
     * @return {@code true} se as séries foram carregadas com sucesso, {@code false} caso contrário.
     */
    public boolean importarSeries() {
        return seriesR.carregarSeries();
    }


    /**
     * Cadastra uma nova temporada a uma série existente, localizada pelo ID.
     *
     * @param id ID da série
     * @param temporada Objeto Temporada a ser adicionada
     * @return true se a temporada foi adicionada com sucesso, false caso contrário
     */
    public boolean cadastrarTemporada(int id, Temporada temporada){
        return seriesR.buscarId(id).addTemporada(temporada);
    }

    /**
     * Realiza uma busca por séries com base na categoria e no filtro fornecido.
     *
     * @param categoria Categoria da busca (ex: "1" para título)
     * @param filtro Valor a ser utilizado como filtro
     * @return true se séries forem encontradas, false caso contrário
     */
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

    /**
     * Lista todas as séries cadastradas.
     * A ordenação é feita automaticamente via TreeSet.
     */
    public void listarSeries(){
        for (Serie i : seriesR.getSeries()){
            System.out.println(i.toString());
        }
    }

    public TreeSet<Serie> buscarSeries(int categoria, String filtro){
        TreeSet<Serie> seriesEncontradas = new TreeSet<>();
        int filtroNum;

        switch(categoria){
            case 1: // Titulo
                seriesEncontradas = seriesR.buscarTitulo(filtro);
                break;

            case 2: // Ator
                seriesEncontradas = seriesR.buscarAtor(filtro);
                break;

            case 3: // Gênero
                for (Genero i : Genero.values()){

                    if (filtro.equalsIgnoreCase(i.getNomeFormatado())){
                        seriesEncontradas = seriesR.buscarGenero(i);
                    }
                }
                break;

            case 4: // Ano
                filtroNum = Integer.parseInt(filtro);
                seriesEncontradas = seriesR.buscarAno(filtroNum);
                break;

            case 5: // Onde assistir
                seriesEncontradas = seriesR.buscarOndeAssistir(filtro);
                break;

            case 6: // ID
                filtroNum = Integer.parseInt(filtro);
                seriesEncontradas.add(seriesR.buscarId(filtroNum));
                break;

            default:
                System.out.println("Categoria inexistente.");
        }

        return seriesEncontradas;
    }

    /**
     * Avalia uma série a partir do ID fornecido.
     *
     * @param id ID da série
     * @param reviewSerie Texto da avaliação
     * @param dataVisto Data em que a série foi assistida
     * @return true se a avaliação foi registrada com sucesso, false caso contrário
     */
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

    /**
     * Avalia uma temporada de uma série e atualiza a pontuação média da série.
     *
     * @param id ID da série
     * @param numero Número da temporada
     * @param reviewTemporada Texto da avaliação da temporada
     * @param pontuacao Pontuação atribuída à temporada
     * @return true se a avaliação foi registrada com sucesso, false caso contrário
     */
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
                    } else {
                        return false; // Retorna falso, pois não há temporada cadastrada com o número passado
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

    /**
     * Retorna o repositório de séries atual.
     *
     * @return Objeto SerieRepositorio associado ao controller
     */
    public SerieRepositorio getSeriesR(){
        return seriesR;
    }
}

