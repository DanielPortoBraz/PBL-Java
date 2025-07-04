package Model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Classe responsável por gerenciar um repositório de filmes. Os filmes são armazenados de maneira ordenada
 * usando um {@link TreeSet}, garantindo que os filmes sejam mantidos em ordem natural.
 * Oferece métodos para adicionar, remover e buscar filmes com base em diferentes critérios.
 */
public class FilmeRepositorio {
    private TreeSet<Filme> filmes;

    /**
     * Construtor da classe, inicializa o repositório de filmes.
     */
    public FilmeRepositorio() {
        this.filmes = new TreeSet<Filme>();
    }

    /**
     * Adiciona um filme ao repositório de forma ordenada.
     *
     * @param filme O filme a ser adicionado.
     * @return {@code true} se o filme foi adicionado com sucesso,
     *         {@code false} se o filme já estiver presente no repositório.
     */
    public boolean addFilme(Filme filme){
        return this.filmes.add(filme);
    }

    /**
     * Remove um filme do repositório.
     *
     * @param filme O filme a ser removido.
     * @return {@code true} se o filme foi removido com sucesso,
     *         {@code false} se o filme não estava presente no repositório.
     */
    public boolean removeFilme(Filme filme){
        return this.filmes.remove(filme);
    }

    /**
     * Salva a lista de filmes em um arquivo JSON chamado "filmes.json".
     *
     * Este método utiliza a biblioteca Jackson para serializar a lista de filmes no formato JSON,
     * com formatação legível (indentação). Caso ocorra algum erro durante a gravação, a exceção
     * é capturada e uma mensagem de erro é exibida no console.
     *
     * @return {@code true} se os filmes foram salvos com sucesso; {@code false} em caso de erro.
     */
    public boolean salvarFilmes() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            mapper.writeValue(new File("filmes.json"), this.getFilmes());
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao salvar filmes: " + e.getMessage());
            return false;
        } catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    /**
     * Carrega os filmes do arquivo "filmes.json" e os armazena na estrutura interna.
     *
     * Este método utiliza a biblioteca Jackson para desserializar os dados contidos no
     * arquivo JSON e convertê-los em objetos do tipo {@code Filme}. Se o arquivo estiver
     * ausente ou ocorrer um erro de leitura, o método trata a exceção e retorna {@code false}.
     *
     * @return {@code true} se os filmes foram carregados com sucesso; {@code false} caso contrário.
     */
    public boolean carregarFilmes() {
        ObjectMapper mapper = new ObjectMapper();
        File filmesSalvos = new File("filmes.json");

        try {
            this.filmes = mapper.readValue(filmesSalvos, new TypeReference<TreeSet<Filme>>(){});
            return true;
        } catch(IOException e){
            System.out.println("Erro ao salvar filmes: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retorna todos os filmes armazenados no repositório.
     *
     * @return Um {@link TreeSet} contendo todos os filmes no repositório.
     */
    public TreeSet<Filme> getFilmes() {
        return filmes;
    }

    /**
     * Busca filmes pelo título. A busca é feita de maneira insensível a maiúsculas e minúsculas.
     *
     * @param titulo O título a ser buscado.
     * @return Um {@link TreeSet} contendo os filmes cujo título contém a string fornecida.
     */
    public TreeSet<Filme> buscarTitulo(String titulo){
        return this.filmes.stream()
                .filter(filme -> filme.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Busca filmes pelo gênero.
     *
     * @param genero O gênero a ser buscado.
     * @return Um {@link TreeSet} contendo os filmes que possuem o gênero fornecido.
     */
    public TreeSet<Filme> buscarGenero(Genero genero){
        return this.filmes.stream()
                .filter(filme -> filme.getGenero().contains(genero))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Busca filmes pelo ano de lançamento.
     *
     * @param ano O ano de lançamento a ser buscado.
     * @return Um {@link TreeSet} contendo os filmes lançados no ano fornecido.
     */
    public TreeSet<Filme> buscarAno(int ano){
        return this.filmes.stream()
                .filter(filme -> filme.getAnoLancamento() == ano)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Busca filmes pelo nome do diretor. A busca é feita de maneira insensível a maiúsculas e minúsculas.
     *
     * @param nomeDiretor O nome do diretor a ser buscado.
     * @return Um {@link TreeSet} contendo os filmes dirigidos por diretores cujo nome contém a string fornecida.
     */
    public TreeSet<Filme> buscarDiretor(String nomeDiretor) {
        String nomeLower = nomeDiretor.toLowerCase();

        return this.filmes.stream()
                .filter(filme -> filme.getDirecao().stream()
                        .anyMatch(diretor -> diretor.toLowerCase().contains(nomeLower))
                )
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Busca filmes pelo nome do ator. A busca é feita de maneira insensível a maiúsculas e minúsculas.
     *
     * @param nomeAtor O nome do ator a ser buscado.
     * @return Um {@link TreeSet} contendo os filmes que possuem o ator cujo nome contém a string fornecida.
     */
    public TreeSet<Filme> buscarAtor(String nomeAtor) {
        String nomeLower = nomeAtor.toLowerCase();

        return this.filmes.stream()
                .filter(filme -> filme.getElenco().stream()
                        .anyMatch(ator -> ator.toLowerCase().contains(nomeLower))
                )
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Busca um filme pelo ID.
     *
     * @param id O ID do filme a ser buscado.
     * @return O filme correspondente ao ID fornecido, ou {@code null} se não encontrado.
     */
    public Filme buscarId(int id){
        for (Filme i : filmes){
            if (i.getId() == id) return i;
        }
        return null;
    }
}
