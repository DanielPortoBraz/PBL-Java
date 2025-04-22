package Model;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;

// Herda de Registro
public class Filme extends Registro {
    private int tempoDuracao; // Em minutos
    private HashSet<String> direcao;
    private HashSet<String> roteiro;
    private HashSet<String> elenco;
    private String tituloOriginal;
    private HashSet<String> ondeAssistir;
    private static HashSet<Integer> listaId = new HashSet<Integer>();
    private int id; // Atributo usado para diferenciar e buscar um filme específico


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

    // Sobrescreve equals para afirmar que filmes com o mesmo ID são iguais. Usado em testes
    @Override
    public boolean equals(Object obj){
        Filme filme = (Filme) obj;
        return this.getId() == filme.getId();
    }

    @Override
    public int compareTo(Registro inserido) { // Compara pelo título original e/ou direção, respectivamente
        int comparacao = super.compareTo(inserido); // Faz a comparação pela pontuação e título

        if (comparacao == 0) {
            comparacao = this.getTituloOriginal().compareToIgnoreCase(((Filme) inserido).getTituloOriginal());
            if (comparacao != 0) return comparacao;

            List<String> direcaoInserido = ((Filme) inserido).getDirecao() != null ?
                    ((Filme) inserido).getDirecao().stream().sorted().toList() : List.of();
            List<String> direcaoIncluso = this.getDirecao() != null ?
                    this.getDirecao().stream().sorted().toList() : List.of();

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