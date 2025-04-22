package Model;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;

public class Serie extends Registro{
    private int anoEncerramento;
    private HashSet<String> elenco;
    private String tituloOriginal;
    private HashSet<String> ondeAssistir;
    private HashSet<Temporada> temporadas;
    private static HashSet<Integer> listaId = new HashSet<Integer>();
    private int id; // Atributo usado para diferenciar e buscar uma série específica


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

    // Sobrescreve equals para afirmar que séries com o mesmo ID são iguais. Usado em testes
    @Override
    public boolean equals(Object obj){
        Serie serie = (Serie) obj;
        return this.getId() == serie.getId();
    }

    @Override
    public int compareTo(Registro inserido){ // Compara por título original e/ou elenco, respectivamente
        int comparacao = super.compareTo(inserido); // Faz a comparação pela pontuação e título

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

    @Override
    public String toString() {
        // Formata a data no formato dd/MM/YY. Caso a data seja null, atualiza a data para a atual do sistema
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
