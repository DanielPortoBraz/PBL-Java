package Controller;

import Model.LivroRepositorio;
import Model.Genero;
import Model.Livro;

import java.util.Calendar;
import java.util.HashSet;
import java.util.TreeSet;


public class LivroController extends RegistroController {

    private LivroRepositorio livrosR;

    public LivroController() {
        this.livrosR = new LivroRepositorio();
    }

    // Retorna true caso consiga cadastrar o Livro, e false para o contrário
    public boolean cadastrarLivro(String titulo, HashSet<Genero> generos, int anoLancamento,
                               boolean visto, String autor, String editora, String isbn, boolean exemplar) {
        return livrosR.addLivro(new Livro(titulo, generos, anoLancamento, visto, autor,
                editora, isbn, exemplar));
    }

    // Busca livros a partir da categoria e o filtro. Categoria indica o atributo e filtro indica o valor do atributo
    // Retorna true caso consiga encontrar algum Livro, e false caso contrário
    public boolean buscarLivros(String categoria, String filtro) {
        TreeSet<Livro> livrosEncontrados;
        Livro livroEncontrado;

        switch (categoria) {
            case "1": // Titulo
                livrosEncontrados = livrosR.buscarTitulo(filtro);

                if (!livrosEncontrados.isEmpty()) {
                    livrosEncontrados.forEach(System.out::println);
                    return true;
                }
                break;

            case "2": // Autor
                livrosEncontrados = livrosR.buscarAutor(filtro);

                if (!livrosEncontrados.isEmpty()) {
                    livrosEncontrados.forEach(System.out::println);
                    return true;
                }
                break;

            case "3": // Gênero

                for (Genero i : Genero.values()){

                    if (filtro.equalsIgnoreCase(i.getNomeFormatado())){
                        livrosEncontrados = livrosR.buscarGenero(i);
                        livrosEncontrados.forEach(System.out::println);
                        return true;
                    }
                }
                break;

            case "4": // Ano
                int filtroNum = Integer.parseInt(filtro);
                livrosEncontrados = livrosR.buscarAno(filtroNum);

                if (!livrosEncontrados.isEmpty()) {
                    livrosEncontrados.forEach(System.out::println);
                    return true;
                }
                break;

            case "5": // Isbn
                livroEncontrado = livrosR.buscarIsbn(filtro);

                if (livroEncontrado != null){
                    System.out.println(livroEncontrado);
                    return true;
                }
                break;

            default:
                System.out.println("Categoria inexistente.");

        }

        return false;

    }

    // Lista todos os Livros. A lista já vem ordenada devido ao TreeSet
    public void listarLivros(){
        for (Livro i : livrosR.getLivros()){
            System.out.println(i.toString());
        }
    }

    // Retorna true caso consigo encontrar o Livro e avaliar pelo ISBN, retorna false caso contrário
    public boolean avaliarLivro(String isbn, String review, int pontuacao, Calendar dataVisto){
        Livro livroAvaliado = livrosR.buscarIsbn(isbn);

        if (livroAvaliado != null) {
            livrosR.removeLivro(livroAvaliado);
            livroAvaliado.setVisto(true); // Marca o livro como visto
            livroAvaliado.setDataVisto(dataVisto); // Atualiza a data que foi lido
            livroAvaliado.setReview(review); // Atualiza a review do livro
            livroAvaliado.setPontuacao(pontuacao); // Atualiza a pontuação do livro
            livrosR.addLivro(livroAvaliado);

            return true;
        }

        else
            return false;
    }

    // Retorna o repositório atual. Usado somente para testes
    public LivroRepositorio getLivrosR() {
        return livrosR;
    }
}
