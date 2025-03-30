package Model;

import java.util.HashSet;

public class Registro {
    private String titulo;
    private HashSet<Genero> generos;
    private int anoLancamento;
    private boolean visto; // Inicia qualquer registro como não visto

    private int anoVisto;
    public String review;
    public int pontuacao;

    public Registro(String titulo, HashSet<Genero> generos, int anoLancamento, boolean visto,
                    int anoVisto) {
        this.titulo = titulo;
        this.generos = new HashSet<Genero>(generos);
        this.anoLancamento = anoLancamento;
        this.visto = visto;
        this.anoVisto = anoVisto;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public HashSet<Genero> getGenero() {
        return generos;
    }

    public void setGenero(HashSet<Genero> generos) {
        this.generos = generos;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public boolean getVisto() {
        return visto;
    }

    public void setVisto(boolean visto) {
        this.visto = visto;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getAnoVisto() {
        return anoVisto;
    }

    public void setAnoVisto(int anoVisto) {
        this.anoVisto = anoVisto;
    }
}
