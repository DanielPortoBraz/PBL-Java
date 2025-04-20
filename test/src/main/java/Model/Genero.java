package Model;

public enum Genero {
    ACAO("Ação", 1),
    AVENTURA("Aventura", 2),
    ANIMACAO("Animação", 3),
    BIOGRAFIA("Biografia", 4),
    COMEDIA("Comédia", 5),
    DOCUMENTARIO("Documentário", 6),
    DRAMA("Drama", 7),
    FANTASIA("Fantasia", 8),
    FICCAO_CIENTIFICA("Ficção Científica", 9),
    GUERRA("Guerra", 10),
    HISTORICO("Histórico", 11),
    MUSICAL("Musical", 12),
    MISTERIO("Mistério", 13),
    POLICIAL("Policial", 14),
    ROMANCE("Romance", 15),
    SUSPENSE("Suspense", 16),
    TERROR("Terror", 17),
    THRILLER("Thriller", 18),
    FAROESTE("Faroeste", 19);

    private final String nomeFormatado;
    private final int numero;

    Genero(String nomeFormatado, int numero) {
        this.nomeFormatado = nomeFormatado;
        this.numero = numero;
    }

    public String getNomeFormatado() {
        return nomeFormatado;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return nomeFormatado;
    }
}
