package Model;

import java.text.SimpleDateFormat;
import java.util.HashSet;

public class Serie extends Registro{
    private int anoEncerramento;
    private HashSet<String> elenco;
    private String tituloOriginal;
    private HashSet<String> ondeAssistir;
    private HashSet<Temporada> temporadas;
    private static HashSet<Integer> listaId = new HashSet<Integer>();
    private int id;


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

    @Override
    public boolean equals(Object obj){
        Serie serie = (Serie) obj;
        return this.getId() == serie.getId();
    }

    @Override
    public int compareTo(Registro inserido){ // Adiciona a ordenação por título original
        int comparacao = super.compareTo(inserido);

        if (comparacao == 0)
            return this.getTituloOriginal().compareToIgnoreCase(((Serie) inserido).getTituloOriginal());

        return comparacao;
    }

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

    public void addTemporada(Temporada temporada) {
        this.temporadas.add(temporada);
    }

    public void removeTemporada(Temporada temporada) {
        this.temporadas.remove(temporada);
    }

    public int getId() {
        return id;
    }

    // Usado somente para testes
    public void setId(int id){
        this.id = id;
    }
}
