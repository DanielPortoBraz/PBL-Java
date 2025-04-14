package Model;

public enum Genero {
    ACAO("Ação"),
    AVENTURA("Aventura"),
    ANIMACAO("Animação"),
    BIOGRAFIA("Biografia"),
    COMEDIA("Comédia"),
    DOCUMENTARIO("Documentário"),
    DRAMA("Drama"),
    FANTASIA("Fantasia"),
    FICCAO_CIENTIFICA("Ficção Científica"),
    GUERRA("Guerra"),
    HISTORICO("Histórico"),
    MUSICAL("Musical"),
    MISTERIO("Mistério"),
    POLICIAL("Policial"),
    ROMANCE("Romance"),
    SUSPENSE("Suspense"),
    TERROR("Terror"),
    THRILLER("Thriller"),
    FAROESTE("Faroeste");

    private final String nomeFormatado;

    Genero(String nomeFormatado) {
        this.nomeFormatado = nomeFormatado;
    }

    public String getNomeFormatado() {
        return nomeFormatado;
    }

    @Override
    public String toString() {
        return nomeFormatado;
    }
}
