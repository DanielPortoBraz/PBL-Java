package Model;

public class Temporada {
    private int ano;
    private int quantEpisodios;
    private int numero;

    public Temporada(int ano, int quantEpisodios, int numero) {
        this.ano = ano;
        this.quantEpisodios = quantEpisodios;
        this.numero = numero;
    }

    @Override
    public String toString(){
        return "\nTemporada: " + getNumero() + '\n' +
                "Episódios: " + getQuantEpisodios() + '\n' +
                "Ano: " + getAno() + '\n';
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getQuantEpisodios() {
        return quantEpisodios;
    }

    public void setQuantEpisodios(int quantEpisodios) {
        this.quantEpisodios = quantEpisodios;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
