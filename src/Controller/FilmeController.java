package Controller;

import Model.Genero;
import Model.FilmeRepositorio;
import Model.Filme;

import java.util.HashSet;

public class FilmeController {
    private FilmeRepositorio filmesR;

    public FilmeController() {
        this.filmesR = new FilmeRepositorio();
    }

    public void cadastrarFilme(String titulo, HashSet<Genero> generos, int anoLancamento,
                              boolean visto,int tempoDuracao, HashSet<String> direcao,
                              String roteiro, HashSet<String> elenco, String tituloOriginal,
                              HashSet<String> ondeAssistir){
            filmesR.addFilme(new Filme(titulo, generos, anoLancamento, visto, tempoDuracao,
                    direcao, roteiro, elenco, tituloOriginal, ondeAssistir));
    }

    public void listarFilmes(){
        for (Filme i : filmesR.getFilmes()){
            System.out.println(i.toString());
        }
    }
}
