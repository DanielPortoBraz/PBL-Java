package Controller;

import Model.Genero;
import Model.FilmeRepositorio;
import Model.Filme;

import java.util.Calendar;
import java.util.HashSet;
import java.util.TreeSet;

public class FilmeController {
    private FilmeRepositorio filmesR;

    public FilmeController() {
        this.filmesR = new FilmeRepositorio();
    }

    public boolean cadastrarFilme(String titulo, HashSet<Genero> generos, int anoLancamento,
                              boolean visto,int tempoDuracao, HashSet<String> direcao,
                              HashSet<String> roteiro, HashSet<String> elenco, String tituloOriginal,
                              HashSet<String> ondeAssistir){
        return filmesR.addFilme(new Filme(titulo, generos, anoLancamento, visto, tempoDuracao,
                direcao, roteiro, elenco, tituloOriginal, ondeAssistir));
    }

    public boolean buscarFilmes(String categoria, String filtro){
        TreeSet<Filme> filmesEncontrados;
        Filme filmeEncontrado;
        int filtroNum;

        switch(categoria){
            case "1": // Titulo
                filmesEncontrados = filmesR.buscarTitulo(filtro);

                if (!filmesEncontrados.isEmpty()){
                    filmesEncontrados.forEach(System.out::println);
                    return true;
                }
                break;

            case "2": // Ator
                filmesEncontrados = filmesR.buscarAtor(filtro);

                if (!filmesEncontrados.isEmpty()){
                    filmesEncontrados.forEach(System.out::println);
                    return true;
                }
                break;

            case "3": // Gênero
                for (Genero i : Genero.values()){

                    if (filtro.equalsIgnoreCase(i.getNomeFormatado())){
                        filmesEncontrados = filmesR.buscarGenero(i);
                        filmesEncontrados.forEach(System.out::println);
                        return true;
                    }
                }
                break;

            case "4": // Ano
                filtroNum = Integer.parseInt(filtro);
                filmesEncontrados = filmesR.buscarAno(filtroNum);

                if (!filmesEncontrados.isEmpty()){
                    filmesEncontrados.forEach(System.out::println);
                    return true;
                }
                break;

            case "5": // Diretor
                filmesEncontrados = filmesR.buscarDiretor(filtro);

                if (!filmesEncontrados.isEmpty()){
                    filmesEncontrados.forEach(System.out::println);
                    return true;
                }
                break;

            case "6": // ID
                filtroNum = Integer.parseInt(filtro);
                filmeEncontrado = filmesR.buscarId(filtroNum);

                if (filmeEncontrado != null){
                    System.out.println(filmeEncontrado);
                    return true;
                }
                break;

            default:
                System.out.println("Categoria Inexistente.");
        }

        return false;
    }

    public void listarFilmes(){
        for (Filme i : filmesR.getFilmes()){
            System.out.println(i.toString());
        }
    }

    public boolean avaliarFilme(int id, String review, int pontuacao,  Calendar dataVisto){
        Filme filmeAvaliado = filmesR.buscarId(id);

        if (filmeAvaliado != null) {
            filmesR.removeFilme(filmeAvaliado);
            filmeAvaliado.setVisto(true); // Marca o filme como visto
            filmeAvaliado.setDataVisto(dataVisto);
            filmeAvaliado.setReview(review); // Atualiza a review do filme
            filmeAvaliado.setPontuacao(pontuacao); // Atualiza a pontuação do filme
            filmesR.addFilme(filmeAvaliado);

            return true;
        }

        else
            return false;
    }

    // Usado somente para testes
    public FilmeRepositorio getFilmesR(){
        return filmesR;
    }
}
