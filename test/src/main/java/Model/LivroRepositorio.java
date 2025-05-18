package Model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Classe responsável por gerenciar um repositório de livros. Os livros são armazenados de maneira ordenada
 * usando um {@link TreeSet}, que garante que os livros sejam mantidos em ordem natural.
 * Oferece métodos para adicionar, remover e buscar livros com base em diferentes critérios.
 */
public class LivroRepositorio {
    private TreeSet<Livro> livros;

    /**
     * Construtor da classe, inicializa o repositório de livros.
     */
    public LivroRepositorio() {
        this.livros = new TreeSet<Livro>();
    }

    /**
     * Adiciona um livro ao repositório de forma ordenada.
     *
     * @param livro O livro a ser adicionado.
     * @return {@code true} se o livro foi adicionado com sucesso,
     *         {@code false} se o livro já estiver presente no repositório.
     */
    public boolean addLivro(Livro livro){
        return this.livros.add(livro);
    }

    /**
     * Remove um livro do repositório.
     *
     * @param livro O livro a ser removido.
     * @return {@code true} se o livro foi removido com sucesso,
     *         {@code false} se o livro não estava presente no repositório.
     */
    public boolean removeLivro(Livro livro){
        return this.livros.remove(livro);
    }

    /**
     * Salva a lista de livros em um arquivo JSON chamado "livros.json".
     *
     * @return {@code true} se os livros foram salvos com sucesso; {@code false} em caso de erro.
     */
    public boolean salvarLivros() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            mapper.writeValue(new File("livros.json"), this.getLivros());
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao salvar livros: " + e.getMessage());
            return false;
        } catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    /**
     * Carrega os livros do arquivo "livros.json".
     *
     * @return {@code true} se os livros foram carregados com sucesso; {@code false} caso contrário.
     */
    public boolean carregarLivros() {
        ObjectMapper mapper = new ObjectMapper();
        File livrosSalvos = new File("livros.json");

        try {
            this.livros = mapper.readValue(livrosSalvos, new TypeReference<TreeSet<Livro>>(){});
            return true;
        } catch(IOException e){
            System.out.println("Erro ao carregar livros: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retorna todos os livros armazenados no repositório.
     *
     * @return Um {@link TreeSet} contendo todos os livros no repositório.
     */
    public TreeSet<Livro> getLivros() {
        return livros;
    }

    /**
     * Busca livros pelo título. A busca é feita de maneira insensível a maiúsculas e minúsculas.
     *
     * @param titulo O título a ser buscado.
     * @return Um {@link TreeSet} contendo os livros cujo título contém a string fornecida.
     */
    public TreeSet<Livro> buscarTitulo(String titulo){
        return this.livros.stream()
                .filter(livro -> livro.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Busca livros pelo autor. A busca é feita de maneira insensível a maiúsculas e minúsculas.
     *
     * @param autor O autor a ser buscado.
     * @return Um {@link TreeSet} contendo os livros cujo autor contém a string fornecida.
     */
    public TreeSet<Livro> buscarAutor(String autor){
        return this.livros.stream()
                .filter(livro -> livro.getAutor().toLowerCase().contains(autor.toLowerCase()))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Busca livros por gênero.
     *
     * @param genero O gênero a ser buscado.
     * @return Um {@link TreeSet} contendo os livros que possuem o gênero fornecido.
     */
    public TreeSet<Livro> buscarGenero(Genero genero){
        return this.livros.stream()
                .filter(livro -> livro.getGenero().contains(genero))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Busca livros pelo ano de lançamento.
     *
     * @param ano O ano de lançamento a ser buscado.
     * @return Um {@link TreeSet} contendo os livros lançados no ano fornecido.
     */
    public TreeSet<Livro> buscarAno(int ano){
        return this.livros.stream()
                .filter(livro -> livro.getAnoLancamento() == ano)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Busca um livro pelo ISBN.
     *
     * @param isbn O ISBN do livro a ser buscado.
     * @return O livro correspondente ao ISBN fornecido, ou {@code null} se não encontrado.
     */
    public Livro buscarIsbn(String isbn){
        for (Livro i : livros){
            if (i.getIsbn().equalsIgnoreCase(isbn)) return i;
        }
        return null;
    }
}
