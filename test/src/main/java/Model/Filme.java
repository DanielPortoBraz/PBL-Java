package Model;

import java.text.SimpleDateFormat;
import java.util.HashSet;


public class Filme extends Registro {
    private int tempoDuracao; // Em minutos
    private HashSet<String> direcao;
    private HashSet<String> roteiro;
    private HashSet<String> elenco;
    private String tituloOriginal;
    private HashSet<String> ondeAssistir;
    private static HashSet<Integer> listaId = new HashSet<Integer>();
    private int id;


    public Filme(String titulo, HashSet<Genero> generos, int anoLancamento,
                 boolean visto, int tempoDuracao, HashSet<String> direcao,
                 HashSet<String> roteiro, HashSet<String> elenco, String tituloOriginal,
                 HashSet<String> ondeAssistir) {
        super(titulo, generos, anoLancamento, visto);
        this.tempoDuracao = tempoDuracao;
        this.direcao = new HashSet<String>(direcao);
        this.roteiro = new HashSet<String>(roteiro);
        this.elenco = new HashSet<String>(elenco);
        this.tituloOriginal = tituloOriginal;
        this.ondeAssistir = new HashSet<String>(ondeAssistir);
        this.id = GeradorID.gerarID(listaId);
    }

    @Override
    public boolean equals(Object obj){
        Filme filme = (Filme) obj;
        return this.getId() == filme.getId();
    }

    @Override
    public int compareTo(Registro inserido) { // Adiciona a ordenação por título original e/ou direção
        int comparacao = super.compareTo(inserido);

        if (comparacao == 0) {
            comparacao = this.getTituloOriginal().compareToIgnoreCase(((Filme) inserido).getTituloOriginal());
            if (comparacao != 0) return comparacao;

            HashSet<String> inseridoDirecao = ((Filme) inserido).getDirecao();
            HashSet<String> inclusoDirecao = this.getDirecao();

            for (String diretor1 : inseridoDirecao) {
                for (String diretor2 : inclusoDirecao) {
                    comparacao = diretor1.compareToIgnoreCase(diretor2);
                    if (comparacao != 0) return comparacao;
                }
            }
        }

        return comparacao;
    }

    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = (this.getDataVisto() != null) ? formato.format(getDataVisto().getTime()) : "--/--/----";

        return "Título: " + getTitulo() + '\n' +
                "Título Original: " + getTituloOriginal() + '\n' +
                "Gêneros: " + getGenero() + '\n' +
                "Ano de Lançamento: " + getAnoLancamento() + '\n' +
                "Duração: " + getTempoDuracao() + " minutos" + '\n' +
                "Visto: " + (isVisto() ? "Sim" : "Não") + '\n' +
                "Direção: " + getDirecao() + '\n' +
                "Roteiro: " + getRoteiro() + '\n' +
                "Elenco: " + getElenco() + '\n' +
                "Onde Assistir: " + getOndeAssistir() + '\n' +
                "Pontuação: " + (getPontuacao() != 0 ? getPontuacao() : " ") + '\n' +
                "Review: " + getReview() + '\n' +
                "Visto em: " + dataFormatada + '\n' +
                "ID: " + getId() + '\n';
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

    public HashSet<String> getRoteiro() {
        return roteiro;
    }

    public void setRoteiro(HashSet<String> roteiro) {
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

    public int getId() {
        return id;
    }

    // Usado somente para testes
    public void setId(int id){
        this.id = id;
    }
}