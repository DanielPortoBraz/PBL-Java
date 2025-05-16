package Controller;

import Model.LivroRepositorio;
import Model.Genero;
import Model.Livro;

import java.util.Calendar;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Controlador responsável pela lógica de manipulação dos objetos do tipo {@link Livro}.
 * Ele interage com o repositório de livros para realizar operações como cadastro, busca,
 * listagem e avaliação.
 *
 */
public class LivroController {

    /** Repositório responsável por armazenar os livros. */
    private LivroRepositorio livrosR;

    /**
     * Construtor padrão que inicializa o repositório de livros.
     */
    public LivroController() {
        this.livrosR = new LivroRepositorio();
    }

    /**
     * Cadastra um novo livro no repositório.
     *
     * @param titulo         Título do livro.
     * @param generos        Conjunto de gêneros associados ao livro.
     * @param anoLancamento  Ano de lançamento do livro.
     * @param visto          Indica se o livro já foi lido.
     * @param autor          Nome do autor.
     * @param editora        Nome da editora.
     * @param isbn           Código ISBN do livro.
     * @param exemplar       Indica se possui exemplar físico do livro.
     * @return {@code true} se o livro foi cadastrado com sucesso; {@code false} caso contrário.
     */
    public boolean cadastrarLivro(String titulo, HashSet<Genero> generos, int anoLancamento,
                                  boolean visto, String autor, String editora, String isbn, boolean exemplar) {
        return livrosR.addLivro(new Livro(titulo, generos, anoLancamento, visto, autor,
                editora, isbn, exemplar));
    }


    /**
     * Remove um Livro cadastrado baseado pelo seu ISBN
     * @param isbn ISBN do Livro que se deseja remover
     * @return {@code true} se foi possível remover o Livro pelo ISBN; {@code false} caso o livro não tenha sido encontrado
     */
    public boolean removerLivro(String isbn){
        Livro livroRemovido = livrosR.buscarIsbn(isbn);

        if (livroRemovido != null)
            return livrosR.removeLivro(livroRemovido);

        return false;
    }

    public boolean salvarLivros(){
        return livrosR.salvarLivros();
    }

    public boolean importarLivros(){
        return livrosR.carregarLivros();
    }


    /**
     * Busca livros com base em uma categoria e um filtro específico.
     *
     * <p>As categorias possíveis são:
     * <ul>
     *     <li>"1" - Título</li>
     *     <li>"2" - Autor</li>
     *     <li>"3" - Gênero</li>
     *     <li>"4" - Ano de lançamento</li>
     *     <li>"5" - ISBN</li>
     * </ul>
     *
     * @param categoria Categoria da busca (representada por um número em formato {@code String}).
     * @param filtro    Valor do filtro correspondente à categoria.
     * @return {@code true} se algum livro foi encontrado; {@code false} caso contrário.
     */
    public boolean buscarLivros(String categoria, String filtro) {
        TreeSet<Livro> livrosEncontrados;
        Livro livroEncontrado;

        switch (categoria) {
            case "1": // Título
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
                for (Genero i : Genero.values()) {
                    if (filtro.equalsIgnoreCase(i.getNomeFormatado())) {
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

            case "5": // ISBN
                livroEncontrado = livrosR.buscarIsbn(filtro);
                if (livroEncontrado != null) {
                    System.out.println(livroEncontrado);
                    return true;
                }
                break;

            default:
                System.out.println("Categoria inexistente.");
        }

        return false;
    }

    /**
     * Lista todos os livros cadastrados no repositório.
     * Os livros são listados em ordem, conforme a ordenação natural definida na classe {@link Livro}.
     */
    public void listarLivros() {
        for (Livro i : livrosR.getLivros()) {
            System.out.println(i.toString());
        }
    }

    /**
     * Avalia um livro a partir do seu ISBN.
     * Atualiza os dados de visualização, pontuação, review e data de leitura.
     *
     * @param isbn       Código ISBN do livro a ser avaliado.
     * @param review     Texto com a review do livro.
     * @param pontuacao  Nota atribuída ao livro (em geral, de 0 a 5).
     * @param dataVisto  Data em que o livro foi lido.
     * @return {@code true} se o livro foi encontrado e avaliado; {@code false} caso contrário.
     */
    public boolean avaliarLivro(String isbn, String review, int pontuacao, Calendar dataVisto) {
        Livro livroAvaliado = livrosR.buscarIsbn(isbn);

        if (livroAvaliado != null) {
            livrosR.removeLivro(livroAvaliado);
            livroAvaliado.setVisto(true);
            livroAvaliado.setDataVisto(dataVisto);
            livroAvaliado.setReview(review);
            livroAvaliado.setPontuacao(pontuacao);
            livrosR.addLivro(livroAvaliado);

            return true;
        } else {
            return false;
        }
    }

    /**
     * Retorna o repositório de livros utilizado internamente.
     * Método utilizado apenas para fins de teste.
     *
     * @return O repositório de livros atual.
     */
    public LivroRepositorio getLivrosR() {
        return livrosR;
    }
}
