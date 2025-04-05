package Model;

import java.util.HashSet;

public class Serie extends Registro{
    private int anoEncerramento;
    private HashSet<String> elenco;
    private String tituloOriginal;
    private HashSet<String> ondeAssistir;
    private HashSet<Temporada> temporadas;


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
    }

    @Override
    public int compareTo(Registro inserido){ // Adiciona a ordenação por título original
        inserido = (Serie) inserido;
        int comparacao = super.compareTo(inserido);

        if (comparacao == 0)
            return this.getTituloOriginal().compareToIgnoreCase(((Serie) inserido).getTituloOriginal());

        return comparacao;
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

    public void setTemporadas(HashSet<Temporada> temporadas) {
        this.temporadas = temporadas;
    }
}
