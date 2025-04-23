package Model;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;

/**
 * Representa uma série de TV, incluindo informações como título, elenco, gêneros,
 * onde assistir, temporadas e ano de encerramento.
 * Além disso, a classe implementa métodos para comparar e exibir informações sobre a série.
 */
public class Serie extends Registro{
    private int anoEncerramento;
    private HashSet<String> elenco;
    private String tituloOriginal;
    private HashSet<String> ondeAssistir;
    private HashSet<Temporada> temporadas;
    private static HashSet<Integer> listaId = new HashSet<Integer>();
    private int id; // Atributo usado para diferenciar e buscar uma série específica


    /**
     * Construtor que cria uma instância da série com todas as informações necessárias.
     *
     * @param titulo Título da série.
     * @param generos Gêneros da série.
     * @param anoLancamento Ano de lançamento da série.
     * @param visto Se a série foi vista ou não.
     * @param anoEncerramento Ano de encerramento da série (caso já tenha terminado).
     * @param elenco Elenco principal da série.
     * @param tituloOriginal Título original da série.
     * @param ondeAssistir Locais onde a série pode ser assistida.
     * @param temporadas Temporadas da série.
     */
    public Serie(String titulo, HashSet<Genero> generos, int anoLancamento,
                 boolean visto, int anoEncerramento,
                 HashSet<String> elenco, String tituloOriginal,
                 HashSet<String> ondeAssistir, HashSet<Temporada> temporadas) {

        super(titulo, generos, anoLancamento, visto);
        this.anoEncerramento = anoEncerramento;
        this.elenco = new HashSet<String>(elenco);
        this.tituloOriginal = tituloOriginal;
        this.ondeAssistir = new HashSet<String>(ondeAssistir);
        this.temporadas = new HashSet<Temporada>(temporadas);
        this.id = GeradorID.gerarID(listaId);
    }

    /**
     * Sobrescreve o método `equals` para garantir que séries com o mesmo ID sejam consideradas iguais.
     *
     * @param obj Objeto a ser comparado.
     * @return `true` se as séries forem iguais (mesmo ID), `false` caso contrário.
     */
    @Override
    public boolean equals(Object obj){
        Serie serie = (Serie) obj;
        return this.getId() == serie.getId();
    }

    /**
     * Compara duas séries, primeiro pelo título original, e em caso de empate, pelo elenco.
     *
     * @param inserido Objeto a ser comparado.
     * @return Valor negativo, zero ou positivo conforme a comparação.
     */
    @Override
    public int compareTo(Registro inserido){
        int comparacao = super.compareTo(inserido); // Compara pela pontuação e título

        if (comparacao == 0) {
            comparacao = this.getTituloOriginal().compareToIgnoreCase(((Serie) inserido).getTituloOriginal());
            if (comparacao != 0) return comparacao;

            List<String> direcaoInserido = ((Serie) inserido).getElenco() != null ?
                    ((Serie) inserido).getElenco().stream().sorted().toList() : List.of();
            List<String> direcaoIncluso = this.getElenco() != null ?
                    this.getElenco().stream().sorted().toList() : List.of();

            for (int i = 0; i < Math.min(direcaoInserido.size(), direcaoIncluso.size()); i++) {
                comparacao = direcaoInserido.get(i).compareToIgnoreCase(direcaoIncluso.get(i));
                if (comparacao != 0) return comparacao;
            }

            return Integer.compare(direcaoInserido.size(), direcaoIncluso.size());
        }

        return comparacao;
    }

    /**
     * Retorna uma representação em formato de string de todos os detalhes da série.
     *
     * @return A string formatada com as informações da série.
     */
    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = (this.getDataVisto() != null) ? formato.format(getDataVisto().getTime()) : "--/--/----";

        return  "Título: " + getTitulo() + '\n' +
                "Título Original: " + getTituloOriginal() + '\n' +
                "Gêneros: " + getGenero() + '\n' +
                "Ano de Lançamento: " + getAnoLancamento() + '\n' +
                "Ano de Encerramento: " + (getAnoEncerramento() > 0 ? getAnoEncerramento() : "Ainda em andamento") + '\n' +
                "Visto: " + (isVisto() ? "Sim" : "Não") + '\n' +
                "Elenco: " + getElenco() + '\n' +
                "Onde Assistir: " + getOndeAssistir() + '\n' +
                "Temporadas: " + getTemporadas().toString() + '\n' +
                "Pontuação: " + getPontuacao() + '\n' +
                "Review: " + getReview() + '\n' +
                "Vista em: " + dataFormatada + '\n' +
                "ID: " + getId() + '\n';
    }

    // Métodos Getter e Setter

    public int getAnoEncerramento() {
        return anoEncerramento;
    }

    public void setAnoEncerramento(int anoEncerramento) {
        this.anoEncerramento = anoEncerramento;
    }

    public HashSet<String> getElenco() {
        return elenco;
    }

    public void setElenco(HashSet<String> elenco) {
        this.elenco = elenco;
    }

    public String getTituloOriginal() {
        return tituloOriginal;
    }

    public void setTituloOriginal(String tituloOriginal) {
        this.tituloOriginal = tituloOriginal;
    }

    public HashSet<String> getOndeAssistir() {
        return ondeAssistir;
    }

    public void setOndeAssistir(HashSet<String> ondeAssistir) {
        this.ondeAssistir = ondeAssistir;
    }

    public HashSet<Temporada> getTemporadas() {
        return temporadas;
    }

    public boolean addTemporada(Temporada temporada) {
        return this.temporadas.add(temporada);
    }

    public boolean removeTemporada(Temporada temporada) {
        return this.temporadas.remove(temporada);
    }

    public int getId() {
        return id;
    }

    // Usado somente para testes
    public void setId(int id){
        this.id = id;
    }
}
