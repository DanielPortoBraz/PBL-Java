package Controller;

import Model.LivroRepositorio;
import Model.Genero;
import Model.Livro;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Stream;


public class LivroController extends RegistroController {
    private LivroRepositorio livrosR;

    public LivroController() {
        this.livrosR = new LivroRepositorio();
    }

    public void cadastrarLivro(String titulo, HashSet<Genero> generos, int anoLancamento,
                               boolean visto, String autor, String editora, String isbn, boolean exemplar) {
        livrosR.addLivro(new Livro(titulo, generos, anoLancamento, visto, autor, editora, isbn, exemplar));
    }

    public boolean buscarLivros(int categoria, String filtro) {
        TreeSet<Livro> livrosEncontrados = new TreeSet<>();

        switch (categoria) {
            case 1: // Titulo
                livrosEncontrados = livrosR.buscarTitulo(filtro);
                livrosEncontrados.forEach(System.out::println);
                break;

            case 2: // Autor
                livrosEncontrados = livrosR.buscarAutor(filtro);
                livrosEncontrados.forEach(System.out::println);
                break;

            case 3: // Gênero
                //livrosR.buscarGenero(filtro).forEach(System.out::println);
                break;

            case 4: // Ano
                int filtroNum = Integer.parseInt(filtro);
                livrosEncontrados = livrosR.buscarAno(filtroNum);
                livrosEncontrados.forEach(System.out::println);
                break;

            case 5: // Isbn
                livrosEncontrados.add(livrosR.buscarIsbn(filtro));
                System.out.println(livrosEncontrados.getFirst().toString());
                break;

            default:
                System.out.println("Categoria inexistente.");

        }

        if (livrosEncontrados.isEmpty()) {
            return false;
        }
        return true;

    }

    public void listarLivros(){
        for (Livro i : livrosR.getLivros()){
            System.out.println(i.toString());
        }
    }

    public void avaliarLivro(String isbn, String review, int pontuacao, Calendar dataVisto){
        Livro livroAvaliado = livrosR.buscarIsbn(isbn);

        if (livroAvaliado != null) {
            livrosR.removeLivro(livroAvaliado);
            livroAvaliado.setVisto(true); // Marca o livro como visto
            livroAvaliado.setDataVisto(dataVisto);
            livroAvaliado.setReview(review); // Atualiza a review do livro
            livroAvaliado.setPontuacao(pontuacao); // Atualiza a pontuação do livro
            livrosR.addLivro(livroAvaliado);
        }

        else
            System.out.println("Livro não encontrado. Não foi possível realizar a avaliação.");
    }
}
