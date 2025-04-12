package Controller;

import Model.Genero;
import Model.FilmeRepositorio;
import Model.Filme;
import Model.Filme;

import java.util.HashSet;

public class FilmeController {
    private FilmeRepositorio filmesR;

    public FilmeController() {
        this.filmesR = new FilmeRepositorio();
    }

    public void cadastrarFilme(String titulo, HashSet<Genero> generos, int anoLancamento,
                              boolean visto,int tempoDuracao, HashSet<String> direcao,
                              HashSet<String> roteiro, HashSet<String> elenco, String tituloOriginal,
                              HashSet<String> ondeAssistir){
            filmesR.addFilme(new Filme(titulo, generos, anoLancamento, visto, tempoDuracao,
                    direcao, roteiro, elenco, tituloOriginal, ondeAssistir));
    }

    public void buscarFilmes(int categoria, String filtro){

        switch(categoria){
            case 1: // Titulo
                filmesR.buscarTitulo(filtro).forEach(System.out::println);
                break;

            case 2: // Ator
                filmesR.buscarAtor(filtro).forEach(System.out::println);
                break;

            case 3: // Gênero
                //filmesR.buscarGenero(filtro).forEach(System.out::println);
                break;

            case 4: // Ano
                int filtroNum = Integer.parseInt(filtro);
                filmesR.buscarAno(filtroNum).forEach(System.out::println);
                break;

            case 5: // Diretor
                filmesR.buscarDiretor(filtro).forEach(System.out::println);
                break;

            default:
                System.out.println("Não encontrado.");

        }
    }

    public void listarFilmes(){
        for (Filme i : filmesR.getFilmes()){
            System.out.println(i.toString());
        }
    }

    public void avaliarFilme(String titulo, String review, int pontuacao){
        Filme filmeAvaliado = filmesR.buscarTitulo(titulo).getFirst();

        if (filmeAvaliado != null) {
            filmesR.removeFilme(filmeAvaliado);
            filmeAvaliado.setVisto(true); // Marca o filme como visto
            filmeAvaliado.setReview(review); // Atualiza a review do filme
            filmeAvaliado.setPontuacao(pontuacao); // Atualiza a pontuação do filme
            filmesR.addFilme(filmeAvaliado);
        }

        else
            System.out.println("Filme não encontrado. Não foi possível realizar a avaliação.");
    }
}
