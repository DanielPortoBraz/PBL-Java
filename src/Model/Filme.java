package Model;

import java.util.HashSet;

public class Filme extends Registro {
    private int tempoDuracao; // Em minutos
    private HashSet<String> direcao;
    private String roteiro;
    private HashSet<String> elenco;
    private String tituloOriginal;
    private HashSet<String> ondeAssistir;


    public Filme(String titulo, HashSet<Genero> generos, int anoLancamento,
                 boolean visto, int tempoDuracao, HashSet<String> direcao,
                 String roteiro, HashSet<String> elenco, String tituloOriginal,
                 HashSet<String> ondeAssistir) {
        super(titulo, generos, anoLancamento, visto);
        this.tempoDuracao = tempoDuracao;
        this.direcao = new HashSet<String>(direcao);
        this.roteiro = roteiro;
        this.elenco = new HashSet<String>(elenco);
        this.tituloOriginal = tituloOriginal;
        this.ondeAssistir = new HashSet<String>(ondeAssistir);
    }

    public int getTempoDuracao() {
        return tempoDuracao;
    }

    public void setTempoDuracao(int tempoDuracao) {
        this.tempoDuracao = tempoDuracao;
    }

    public HashSet<String> getDirecao() {
        return direcao;
    }

    public void setDirecao(HashSet<String> direcao) {
        this.direcao = direcao;
    }

    public String getRoteiro() {
        return roteiro;
    }

    public void setRoteiro(String roteiro) {
        this.roteiro = roteiro;
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
}