package View;

import Controller.*;
import Model.Genero;
import Model.Temporada;
import CustomExceptions.*; // Exceções personalizadas

import java.util.Calendar; // Para ler e formatar Datas
import java.util.HashSet;
import java.util.Scanner; // Entrada de dados

/**
 * Classe que implementa o menu de cadastro de mídias.
 * <p>
 * Esta classe provê a interface para que o usuário cadastre livros, filmes e séries,
 * solicitando e realizando a validação dos dados inseridos. Para cada tipo de mídia,
 * os dados são lidos via {@code Scanner} e enviados aos respectivos controllers para o cadastro.
 * São utilizados métodos auxiliares (como {@code lerDadoVazio}, {@code cadastrarGeneros} e {@code validarAno})
 * para garantir que os dados sejam inseridos corretamente, disparando exceções personalizadas quando necessário.
 * </p>
 *
 * @see Menu
 * @see LivroController
 * @see FilmeController
 * @see SerieController
 * @see Genero
 * @see Temporada
 * @see CustomExceptions.DadoVazioException
 * @see CustomExceptions.AnoInvalidoException
 */
class MenuCadastro implements Menu {
    private Scanner scanner;

    protected LivroController livroController;
    protected FilmeController filmeController;
    protected SerieController serieController;

    /**
     * Constrói uma instância de MenuCadastro com o {@code Scanner} e os controllers inicializados.
     *
     * @param scanner         Objeto {@code Scanner} para leitura da entrada do usuário.
     * @param livroController Controller responsável pelas operações relacionadas a livros.
     * @param filmeController Controller responsável pelas operações relacionadas a filmes.
     * @param serieController Controller responsável pelas operações relacionadas a séries.
     */
    public MenuCadastro(Scanner scanner, LivroController livroController,
                        FilmeController filmeController,
                        SerieController serieController) {
        this.scanner = scanner;
        this.livroController = livroController;
        this.filmeController = filmeController;
        this.serieController = serieController;
    }

    /**
     * Exibe o menu de cadastro no terminal e processa as opções selecionadas pelo usuário.
     * <p>
     * O menu apresenta as seguintes opções:
     * <ul>
     *   <li>"1" - Cadastro de Livro</li>
     *   <li>"2" - Cadastro de Filme</li>
     *   <li>"3" - Cadastro de Série</li>
     *   <li>"4" - Voltar</li>
     * </ul>
     * Para o cadastro de livro, por exemplo, são solicitados dados como título, gêneros, ano de lançamento,
     * se já foi visto, autor, editora, ISBN e se possui exemplar físico. Cada dado é validado
     * (por exemplo, campos vazios ou ano inválido) e, em caso de erro, uma mensagem é exibida.
     * </p>
     */
    @Override
    public void exibir() {
        String opcao;
        boolean cadastrado;

        do {
            System.out.println("\n-- MENU DE CADASTRO --");
            System.out.println("1- Livro");
            System.out.println("2- Filme");
            System.out.println("3- Série");
            System.out.println("4- Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.println("\n<<< CADASTRO DE LIVRO >>>");

                    try {
                        String tituloLivro;
                        while (true) {
                            System.out.print("Digite o título do livro: ");
                            tituloLivro = scanner.nextLine();
                            try {
                                // Valida se o título não é vazio
                                lerDadoVazio(tituloLivro, "Título");
                                break;
                            } catch (DadoVazioException e) {
                                System.out.println("Erro: " + e.getMessage());
                            }
                        }

                        HashSet<Genero> generosLivro = cadastrarGeneros(scanner);

                        int anoLancamentoLivro;
                        while (true) {
                            try {
                                System.out.print("Digite o ano de lançamento: ");
                                anoLancamentoLivro = scanner.nextInt();
                                scanner.nextLine();
                                // Valida o ano informado
                                validarAno(anoLancamentoLivro);
                                break;
                            } catch (AnoInvalidoException e) {
                                System.out.println("Erro: " + e.getMessage());
                            } catch (Exception e) {
                                System.out.println("Erro inesperado. Digite novamente.");
                                scanner.nextLine(); // Limpa o buffer
                            }
                        }

                        boolean vistoLivro;
                        while (true) {
                            System.out.print("Já foi visto? (true/false): ");
                            String vistoStr = scanner.nextLine().trim();
                            if (vistoStr.equalsIgnoreCase("true") || vistoStr.equalsIgnoreCase("false")) {
                                vistoLivro = Boolean.parseBoolean(vistoStr);
                                break;
                            } else {
                                System.out.println("Erro: Digite apenas true ou false.");
                            }
                        }

                        String autorLivro;
                        while (true) {
                            System.out.print("Digite o autor: ");
                            autorLivro = scanner.nextLine();
                            try {
                                lerDadoVazio(autorLivro, "Autor");
                                break;
                            } catch (DadoVazioException e) {
                                System.out.println("Erro: " + e.getMessage());
                            }
                        }

                        String editoraLivro;
                        while (true) {
                            System.out.print("Digite a editora: ");
                            editoraLivro = scanner.nextLine();
                            try {
                                lerDadoVazio(editoraLivro, "Editora");
                                break;
                            } catch (DadoVazioException e) {
                                System.out.println("Erro: " + e.getMessage());
                            }
                        }

                        String isbnLivro;
                        while (true) {
                            System.out.print("Digite o ISBN: ");
                            isbnLivro = scanner.nextLine();
                            try {
                                lerDadoVazio(isbnLivro, "ISBN");
                                break;
                            } catch (DadoVazioException e) {
                                System.out.println("Erro: " + e.getMessage());
                            }
                        }

                        boolean exemplarLivro;
                        while (true) {
                            System.out.print("Possui um exemplar físico? (true/false): ");
                            String exemplarStr = scanner.nextLine().trim();
                            if (exemplarStr.equalsIgnoreCase("true") || exemplarStr.equalsIgnoreCase("false")) {
                                exemplarLivro = Boolean.parseBoolean(exemplarStr);
                                break;
                            } else {
                                System.out.println("Erro: Digite apenas true ou false.");
                            }
                        }

                        cadastrado = livroController.cadastrarLivro(
                                tituloLivro, generosLivro, anoLancamentoLivro, vistoLivro,
                                autorLivro, editoraLivro, isbnLivro, exemplarLivro
                        );

                        if (cadastrado)
                            System.out.println("Livro cadastrado com sucesso!");
                        else
                            System.out.println("Não foi possível cadastrar o livro. Certifique-se de que não há outro livro idêntico ou com o mesmo ISBN.");

                    } catch (Exception e) {
                        System.out.println("Erro inesperado: " + e.getMessage());
                        scanner.nextLine();
                    }
                    break;

                case "2":
                    System.out.println("\n<<< CADASTRO DE FILME >>>");

                    try {
                        String tituloFilme;
                        while (true) {
                            System.out.print("Digite o título do filme: ");
                            tituloFilme = scanner.nextLine();
                            try {
                                lerDadoVazio(tituloFilme, "Título");
                                break;
                            } catch (DadoVazioException e) {
                                System.out.println("Erro: " + e.getMessage());
                            }
                        }

                        HashSet<Genero> generosFilme = cadastrarGeneros(scanner);

                        int anoLancamentoFilme;
                        while (true) {
                            try {
                                System.out.print("Digite o ano de lançamento: ");
                                anoLancamentoFilme = scanner.nextInt();
                                scanner.nextLine();
                                validarAno(anoLancamentoFilme);
                                break;
                            } catch (AnoInvalidoException e) {
                                System.out.println("Erro: " + e.getMessage());
                            } catch (Exception e) {
                                System.out.println("Erro inesperado. Digite novamente.");
                                scanner.nextLine();
                            }
                        }

                        boolean vistoFilme;
                        while (true) {
                            System.out.print("Já foi visto? (true/false): ");
                            String vistoStr = scanner.nextLine();
                            if (vistoStr.equalsIgnoreCase("true") || vistoStr.equalsIgnoreCase("false")) {
                                vistoFilme = Boolean.parseBoolean(vistoStr);
                                break;
                            }
                            System.out.println("Erro: Digite apenas true ou false.");
                        }

                        int tempoDuracaoFilme;
                        while (true) {
                            try {
                                System.out.print("Digite o tempo de duração (minutos): ");
                                tempoDuracaoFilme = scanner.nextInt();
                                scanner.nextLine();
                                if (tempoDuracaoFilme <= 0)
                                    throw new IllegalArgumentException("Duração deve ser positiva.");
                                break;
                            } catch (Exception e) {
                                System.out.println("Erro: Duração inválida. Tente novamente.");
                                scanner.nextLine();
                            }
                        }

                        HashSet<String> direcaoFilme = cadastrarListaDeNomes(scanner, "Direção");
                        HashSet<String> roteiroFilme = cadastrarListaDeNomes(scanner, "Roteiro");
                        HashSet<String> elencoFilme = cadastrarListaDeNomes(scanner, "Elenco");

                        String tituloOriginalFilme;
                        while (true) {
                            System.out.print("Digite o título original: ");
                            tituloOriginalFilme = scanner.nextLine();
                            try {
                                lerDadoVazio(tituloOriginalFilme, "Título Original");
                                break;
                            } catch (DadoVazioException e) {
                                System.out.println("Erro: " + e.getMessage());
                            }
                        }

                        HashSet<String> ondeAssistirFilme = cadastrarListaDeNomes(scanner, "Onde assistir");

                        cadastrado = filmeController.cadastrarFilme(tituloFilme, generosFilme, anoLancamentoFilme,
                                vistoFilme, tempoDuracaoFilme, direcaoFilme, roteiroFilme,
                                elencoFilme, tituloOriginalFilme, ondeAssistirFilme);

                        if (cadastrado)
                            System.out.println("Filme cadastrado com sucesso!");
                        else
                            System.out.println("Não foi possível cadastrar o filme. Verifique duplicidade.");
                    } catch (Exception e) {
                        System.out.println("Erro inesperado: " + e.getMessage());
                        scanner.nextLine();
                    }

                    break;

                case "3":
                    System.out.println("\n<<< CADASTRO DE SÉRIE >>>\n" +
                            "1- Série\n" +
                            "2- Temporada\n" +
                            "3- Retornar\n");
                    opcao = scanner.nextLine();

                    switch (opcao) {
                        case "1":
                            System.out.println("\n<<< CADASTRO DE SÉRIE >>>");

                            try {
                                String tituloSerie;
                                while (true) {
                                    System.out.print("Digite o título da série: ");
                                    tituloSerie = scanner.nextLine();
                                    try {
                                        lerDadoVazio(tituloSerie, "Título");
                                        break;
                                    } catch (DadoVazioException e) {
                                        System.out.println("Erro: " + e.getMessage());
                                    }
                                }

                                HashSet<Genero> generosSerie = cadastrarGeneros(scanner);

                                int anoLancamentoSerie;
                                while (true) {
                                    try {
                                        System.out.print("Digite o ano de lançamento: ");
                                        anoLancamentoSerie = scanner.nextInt();
                                        scanner.nextLine();
                                        validarAno(anoLancamentoSerie);
                                        break;
                                    } catch (AnoInvalidoException e) {
                                        System.out.println("Erro: " + e.getMessage());
                                    } catch (Exception e) {
                                        System.out.println("Erro inesperado. Digite novamente.");
                                        scanner.nextLine();
                                    }
                                }

                                boolean vistoSerie;
                                while (true) {
                                    System.out.print("Já foi vista? (true/false): ");
                                    String vistoStr = scanner.nextLine();
                                    if (vistoStr.equalsIgnoreCase("true") || vistoStr.equalsIgnoreCase("false")) {
                                        vistoSerie = Boolean.parseBoolean(vistoStr);
                                        break;
                                    }
                                    System.out.println("Erro: Digite apenas true ou false.");
                                }

                                int anoEncerramentoSerie;
                                while (true) {
                                    try {
                                        System.out.print("Digite o ano de encerramento (ou 0 se ainda está em exibição): ");
                                        anoEncerramentoSerie = scanner.nextInt();
                                        scanner.nextLine();
                                        if (anoEncerramentoSerie != 0) {
                                            validarAno(anoEncerramentoSerie);
                                            if (anoEncerramentoSerie < anoLancamentoSerie) {
                                                throw new AnoInvalidoException("Ano de encerramento não pode ser anterior ao de lançamento.");
                                            }
                                        }
                                        break;
                                    } catch (AnoInvalidoException e) {
                                        System.out.println("Erro: " + e.getMessage());
                                    } catch (Exception e) {
                                        System.out.println("Erro inesperado. Digite novamente.");
                                        scanner.nextLine();
                                    }
                                }

                                HashSet<String> elencoSerie = cadastrarListaDeNomes(scanner, "Elenco");

                                String tituloOriginalSerie;
                                while (true) {
                                    System.out.print("Digite o título original: ");
                                    tituloOriginalSerie = scanner.nextLine();
                                    try {
                                        lerDadoVazio(tituloOriginalSerie, "Título Original");
                                        break;
                                    } catch (DadoVazioException e) {
                                        System.out.println("Erro: " + e.getMessage());
                                    }
                                }

                                HashSet<String> ondeAssistirSerie = cadastrarListaDeNomes(scanner, "Onde assistir");
                                System.out.println("AVISO: Cadastre ao menos uma temporada.");
                                HashSet<Temporada> temporadasSerie = cadastrarTemporadas(scanner);

                                cadastrado = serieController.cadastrarSerie(tituloSerie, generosSerie, anoLancamentoSerie,
                                        vistoSerie, anoEncerramentoSerie, elencoSerie, tituloOriginalSerie,
                                        ondeAssistirSerie, temporadasSerie);

                                if (cadastrado)
                                    System.out.println("Série cadastrada com sucesso!");
                                else
                                    System.out.println("Não foi possível cadastrar a série. Certifique-se que ao menos uma temporada foi cadastrada, e não há a mesma série cadastrada.");

                            } catch (DadoVazioException e) {
                                System.out.println("Erro no cadastro: " + e.getMessage());
                            } catch (Exception e) {
                                System.out.println("Erro inesperado: " + e.getMessage());
                                scanner.nextLine();
                            }
                            break;


                        case "2":
                            System.out.println("\n<<< CADASTRO DE TEMPORADA >>>");

                            try {
                                System.out.print("Digite o ID da Série: ");
                                int serieId = scanner.nextInt();
                                scanner.nextLine();

                                HashSet<Temporada> temporadas = cadastrarTemporadas(scanner);

                                if (temporadas.isEmpty()) {
                                    System.out.println("Nenhuma temporada foi cadastrada.");
                                } else {
                                    for (Temporada t : temporadas) {
                                        cadastrado = serieController.cadastrarTemporada(serieId, t);

                                        if (cadastrado)
                                            System.out.printf("Temporada %d cadastrada com sucesso!\n", t.getNumero());
                                        else
                                            System.out.printf("Não foi possível cadastrar a temporada %d.\n", t.getNumero());
                                    }
                                    System.out.println("Temporada(s) cadastrada(s) com sucesso!");
                                }

                            } catch (IllegalArgumentException e) {
                                System.out.println("Erro: " + e.getMessage());
                            } catch (Exception e) {
                                System.out.println("Erro inesperado: " + e.getMessage());
                                scanner.nextLine();
                            }

                            break;
                        case "3":
                            return;

                        default:
                            System.out.println("Opção Inválida. Tente de novo");
                    }
                    break;

                case "4":
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (!opcao.equals("4"));
    }

    public void cadastrarAno() {

    }

    /**
     * Solicita e cadastra os gêneros de uma mídia.
     * <p>
     * Este método apresenta uma lista dos gêneros disponíveis (obtidos por meio de {@link Genero#values()})
     * e permite ao usuário selecionar um ou mais gêneros digitando o número correspondente a cada um.
     * A seleção é encerrada quando o usuário digita "0".
     * </p>
     *
     * @param scanner Objeto {@code Scanner} para leitura da entrada do usuário.
     * @return Um {@code HashSet} contendo os gêneros selecionados.
     */
    public static HashSet<Genero> cadastrarGeneros(Scanner scanner) {
        HashSet<Genero> generos = new HashSet<>();
        int genero = 0;

        do {
            System.out.println("\n--- GÊNEROS ---");
            for (Genero i : Genero.values())
                System.out.printf("%d - %s\n", i.getNumero(), i.getNomeFormatado());
            System.out.println("Selecione o gênero (0 para sair)");
            genero = scanner.nextInt();
            scanner.nextLine();

            switch (genero) {
                case 0:
                    break;
                case 1:
                    generos.add(Genero.ACAO);
                    break;
                case 2:
                    generos.add(Genero.AVENTURA);
                    break;
                case 3:
                    generos.add(Genero.ANIMACAO);
                    break;
                case 4:
                    generos.add(Genero.BIOGRAFIA);
                    break;
                case 5:
                    generos.add(Genero.COMEDIA);
                    break;
                case 6:
                    generos.add(Genero.DOCUMENTARIO);
                    break;
                case 7:
                    generos.add(Genero.DRAMA);
                    break;
                case 8:
                    generos.add(Genero.FANTASIA);
                    break;
                case 9:
                    generos.add(Genero.FICCAO_CIENTIFICA);
                    break;
                case 10:
                    generos.add(Genero.GUERRA);
                    break;
                case 11:
                    generos.add(Genero.HISTORICO);
                    break;
                case 12:
                    generos.add(Genero.MUSICAL);
                    break;
                case 13:
                    generos.add(Genero.MISTERIO);
                    break;
                case 14:
                    generos.add(Genero.POLICIAL);
                    break;
                case 15:
                    generos.add(Genero.ROMANCE);
                    break;
                case 16:
                    generos.add(Genero.SUSPENSE);
                    break;
                case 17:
                    generos.add(Genero.TERROR);
                    break;
                case 18:
                    generos.add(Genero.THRILLER);
                    break;
                case 19:
                    generos.add(Genero.FAROESTE);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

        } while (genero != 0);

        return generos;
    }

    /**
     * Solicita e cadastra uma lista de nomes para um determinado campo.
     * <p>
     * Este método lê repetidamente os nomes para o tipo especificado (por exemplo, "Direção",
     * "Roteiro", "Elenco" ou "Onde assistir") até que o usuário pressione ENTER sem digitar nada.
     * Caso o dado esteja em branco, uma {@code DadoVazioException} é lançada e tratada, e o nome não é adicionado.
     * </p>
     *
     * @param scanner Objeto {@code Scanner} para leitura da entrada do usuário.
     * @param tipo    Uma {@code String} que indica o tipo de nomes que estão sendo cadastrados.
     * @return Um {@code HashSet} contendo os nomes cadastrados.
     */
    public static HashSet<String> cadastrarListaDeNomes(Scanner scanner, String tipo) {
        HashSet<String> lista = new HashSet<>();
        String entrada;

        System.out.printf("Digite os nomes de %s (pressione ENTER para encerrar):\n", tipo);
        while (true) {
            System.out.printf("%s: ", tipo.substring(0, 1).toUpperCase() + tipo.substring(1));
            entrada = scanner.nextLine().trim();

            if (entrada.isEmpty()) break;

            try {
                if (entrada.isBlank()) {
                    throw new DadoVazioException("O campo \"" + tipo + "\" não pode estar vazio.");
                }
                lista.add(entrada);
            } catch (DadoVazioException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        return lista;
    }

    /**
     * Solicita e cadastra os dados das temporadas de uma série.
     * <p>
     * Este método permite ao usuário inserir, de forma interativa, os dados de cada temporada,
     * solicitando o ano de lançamento e a quantidade de episódios. A entrada é encerrada quando
     * o usuário digita uma linha vazia para o ano de lançamento. Caso o ano esteja fora do intervalo
     * permitido (menor que 0 ou maior que o ano atual), uma {@code AnoInvalidoException} é lançada.
     * </p>
     *
     * @param scanner Objeto {@code Scanner} para leitura da entrada do usuário.
     * @return Um {@code HashSet} contendo as temporadas cadastradas.
     */
    public static HashSet<Temporada> cadastrarTemporadas(Scanner scanner) {
        HashSet<Temporada> temporadas = new HashSet<>();
        int numeroTemporada = 1;
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);

        System.out.println("\nDigite os dados das temporadas (pressione ENTER vazio no ano para encerrar):");

        while (true) {
            try {
                System.out.printf("Temporada %d - Ano de lançamento: ", numeroTemporada);
                String entrada = scanner.nextLine().trim();
                if (entrada.isEmpty()) break;

                int ano = Integer.parseInt(entrada);
                if (ano < 0 || ano > anoAtual) {
                    throw new AnoInvalidoException("Ano inválido. O ano deve estar entre 0 e " + anoAtual + ".");
                }

                System.out.print("Quantidade de episódios: ");
                int quantEpisodios = scanner.nextInt();
                scanner.nextLine();

                Temporada temporada = new Temporada(ano, quantEpisodios, numeroTemporada);
                temporadas.add(temporada);
                numeroTemporada++;

            } catch (AnoInvalidoException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Valor inválido para ano. Digite apenas números.");
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                scanner.nextLine();
            }
        }

        return temporadas;
    }

    /**
     * Valida se o ano fornecido é válido.
     * <p>
     * Um ano é considerado válido se estiver entre 0 e o ano atual (inclusive).
     * Caso contrário, uma {@code AnoInvalidoException} é lançada.
     * </p>
     *
     * @param ano O ano a ser validado.
     * @throws AnoInvalidoException Se o ano estiver fora do intervalo permitido.
     */
    public void validarAno(int ano) throws AnoInvalidoException {
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);

        if (ano < 0 || ano > anoAtual)
            throw new AnoInvalidoException("O ano fornecido é inválido.");
    }

    /**
     * Verifica se o dado fornecido é nulo ou vazio.
     * <p>
     * Este método valida o conteúdo de uma {@code String} associada a um campo específico.
     * Se a {@code String} for nula ou contiver apenas espaços em branco, uma
     * {@code DadoVazioException} é lançada.
     * </p>
     *
     * @param dado  O dado que será verificado.
     * @param campo O nome do campo associado, utilizado para compor a mensagem de erro.
     * @throws DadoVazioException Se o dado for nulo ou vazio.
     */
    public void lerDadoVazio(String dado, String campo) throws DadoVazioException {
        if (dado == null || dado.trim().isEmpty()) {
            throw new DadoVazioException("O campo \"" + campo + "\" não pode estar vazio.");
        }
    }
}