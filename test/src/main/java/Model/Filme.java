package Model;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;

/**
 * Representa um filme com informações adicionais como direção, elenco e onde assistir.
 * Herda os atributos e comportamentos da classe {@link Registro}.
 */
public class Filme extends Registro {
    private int tempoDuracao; // Em minutos
    private HashSet<String> direcao;
    private HashSet<String> roteiro;
    private HashSet<String> elenco;
    private String tituloOriginal;
    private HashSet<String> ondeAssistir;

    private static HashSet<Integer> listaId = new HashSet<>();
    private int id;

    /**
     * Construtor da classe Filme.
     *
     * @param titulo          Título do filme.
     * @param generos         Conjunto de gêneros do filme.
     * @param anoLancamento   Ano de lançamento.
     * @param visto           Indica se o filme já foi visto.
     * @param tempoDuracao    Duração do filme em minutos.
     * @param direcao         Conjunto de diretores.
     * @param roteiro         Conjunto de roteiristas.
     * @param elenco          Conjunto de atores/atrizes.
     * @param tituloOriginal  Título original do filme.
     * @param ondeAssistir    Plataformas onde o filme pode ser assistido.
     */
    public Filme(String titulo, HashSet<Genero> generos, int anoLancamento,
                 boolean visto, int tempoDuracao, HashSet<String> direcao,
                 HashSet<String> roteiro, HashSet<String> elenco, String tituloOriginal,
                 HashSet<String> ondeAssistir) {
        super(titulo, generos, anoLancamento, visto);
        this.tempoDuracao = tempoDuracao;
        this.direcao = new HashSet<>(direcao);
        this.roteiro = new HashSet<>(roteiro);
        this.elenco = new HashSet<>(elenco);
        this.tituloOriginal = tituloOriginal;
        this.ondeAssistir = new HashSet<>(ondeAssistir);
        this.id = GeradorID.gerarID(listaId);
    }

    /**
     * Verifica se dois filmes são iguais com base no ID.
     *
     * @param obj Objeto a ser comparado.
     * @return true se os filmes possuem o mesmo ID, false caso contrário.
     */
    @Override
    public boolean equals(Object obj) {
        Filme filme = (Filme) obj;
        return this.getId() == filme.getId();
    }

    /**
     * Compara este filme com outro registro com base na pontuação, título original e direção.
     *
     * @param inserido Registro a ser comparado.
     * @return Valor da comparação.
     */
    @Override
    public int compareTo(Registro inserido) {
        int comparacao = super.compareTo(inserido);

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

    /**
     * Retorna uma representação textual do objeto Filme.
     *
     * @return String com todos os atributos formatados.
     */
    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = (this.getDataVisto() != null) ?
                formato.format(getDataVisto().getTime()) : "--/--/----";

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

    // === Getters e Setters ===

    /**
     * @return Duração do filme em minutos.
     */
    public int getTempoDuracao() {
        return tempoDuracao;
    }

    public void setTempoDuracao(int tempoDuracao) {
        this.tempoDuracao = tempoDuracao;
    }

    /**
     * @return Conjunto com os nomes dos diretores.
     */
    public HashSet<String> getDirecao() {
        return direcao;
    }

    public void setDirecao(HashSet<String> direcao) {
        this.direcao = direcao;
    }

    /**
     * @return Conjunto com os nomes dos roteiristas.
     */
    public HashSet<String> getRoteiro() {
        return roteiro;
    }

    public void setRoteiro(HashSet<String> roteiro) {
        this.roteiro = roteiro;
    }

    /**
     * @return Conjunto com os nomes dos atores/atrizes.
     */
    public HashSet<String> getElenco() {
        return elenco;
    }

    public void setElenco(HashSet<String> elenco) {
        this.elenco = elenco;
    }

    /**
     * @return Título original do filme.
     */
    public String getTituloOriginal() {
        return tituloOriginal;
    }

    public void setTituloOriginal(String tituloOriginal) {
        this.tituloOriginal = tituloOriginal;
    }

    /**
     * @return Plataformas onde o filme pode ser assistido.
     */
    public HashSet<String> getOndeAssistir() {
        return ondeAssistir;
    }

    public void setOndeAssistir(HashSet<String> ondeAssistir) {
        this.ondeAssistir = ondeAssistir;
    }

    /**
     * @return ID único do filme.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID manualmente (usado apenas para testes).
     *
     * @param id Novo ID do filme.
     */
    public void setId(int id) {
        this.id = id;
    }
}
