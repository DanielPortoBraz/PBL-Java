package View;

import Controller.*;
import Model.Genero;
import Model.Temporada;
import CustomExceptions.*;

import java.time.Year;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Scanner; // Entrada de dados

class MenuCadastro implements Menu{
    private Scanner scanner;
    protected LivroController livroController;
    protected FilmeController filmeController;
    protected SerieController serieController;

    public MenuCadastro(Scanner scanner, LivroController livroController,
                        FilmeController filmeController,
                        SerieController serieController){
        this.scanner = scanner;
        this.livroController = livroController;
        this.filmeController = filmeController;
        this.serieController = serieController;
    }

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
                        System.out.print("Digite o título do livro: ");
                        String tituloLivro = scanner.nextLine();
                        lerDadoVazio(tituloLivro, "Título");

                        HashSet<Genero> generosLivro = cadastrarGeneros(scanner);

                        System.out.print("Digite o ano de lançamento: ");
                        int anoLancamentoLivro = scanner.nextInt();
                        validarAno(anoLancamentoLivro);

                        System.out.print("Já foi visto? (true/false): ");
                        boolean vistoLivro = scanner.nextBoolean();
                        scanner.nextLine();

                        System.out.print("Digite o autor: ");
                        String autorLivro = scanner.nextLine();
                        lerDadoVazio(autorLivro, "Autor");

                        System.out.print("Digite a editora: ");
                        String editoraLivro = scanner.nextLine();
                        lerDadoVazio(editoraLivro, "Editora");

                        System.out.print("Digite o ISBN: ");
                        String isbnLivro = scanner.nextLine();
                        lerDadoVazio(isbnLivro, "ISBN");

                        System.out.print("Possui um exemplar físico? (true/false): ");
                        boolean exemplarLivro = scanner.nextBoolean();
                        scanner.nextLine();

                        cadastrado = livroController.cadastrarLivro(tituloLivro, generosLivro, anoLancamentoLivro,
                                vistoLivro, autorLivro, editoraLivro, isbnLivro, exemplarLivro);
                        if (cadastrado)
                            System.out.println("Livro cadastrado com sucesso!");
                        else
                            System.out.println("Não foi possível cadastrar o livro.");
                    } catch (AnoInvalidoException | DadoVazioException e) {
                        System.out.println("Erro no cadastro: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Erro inesperado: " + e.getMessage());
                        scanner.nextLine();
                    }

                    break;

                case "2":
                    System.out.println("\n<<< CADASTRO DE FILME >>>");

                    try {
                        System.out.print("Digite o título do filme: ");
                        String tituloFilme = scanner.nextLine();
                        lerDadoVazio(tituloFilme, "Título");

                        HashSet<Genero> generosFilme = cadastrarGeneros(scanner);

                        System.out.print("Digite o ano de lançamento: ");
                        int anoLancamentoFilme = scanner.nextInt();
                        scanner.nextLine();
                        validarAno(anoLancamentoFilme);

                        System.out.print("Já foi visto? (true/false): ");
                        boolean vistoFilme = scanner.nextBoolean();
                        scanner.nextLine();

                        System.out.print("Digite o tempo de duração (minutos): ");
                        int tempoDuracaoFilme = scanner.nextInt();
                        scanner.nextLine();

                        HashSet<String> direcaoFilme = cadastrarListaDeNomes(scanner, "Direção");
                        HashSet<String> roteiroFilme = cadastrarListaDeNomes(scanner, "Roteiro");
                        HashSet<String> elencoFilme = cadastrarListaDeNomes(scanner, "Elenco");

                        System.out.print("Digite o título original: ");
                        String tituloOriginalFilme = scanner.nextLine();
                        lerDadoVazio(tituloOriginalFilme, "Título Original");

                        HashSet<String> ondeAssistirFilme = cadastrarListaDeNomes(scanner, "Onde assistir");

                        cadastrado = filmeController.cadastrarFilme(tituloFilme, generosFilme, anoLancamentoFilme,
                                vistoFilme, tempoDuracaoFilme, direcaoFilme, roteiroFilme,
                                elencoFilme, tituloOriginalFilme, ondeAssistirFilme);
                        if (cadastrado)
                            System.out.println("Filme cadastrado com sucesso!");
                        else
                            System.out.println("Não foi possível cadastrado o filme.");

                    } catch (AnoInvalidoException | DadoVazioException e) {
                        System.out.println("Erro no cadastro: " + e.getMessage());
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

                    switch(opcao) {
                        case "1":
                            System.out.println("\n<<< CADASTRO DE SÉRIE >>>");

                            try {
                                System.out.print("Digite o título da série: ");
                                String tituloSerie = scanner.nextLine();
                                lerDadoVazio(tituloSerie, "Título");

                                HashSet<Genero> generosSerie = cadastrarGeneros(scanner);

                                System.out.print("Digite o ano de lançamento: ");
                                int anoLancamentoSerie = scanner.nextInt();
                                scanner.nextLine();
                                validarAno(anoLancamentoSerie);

                                System.out.print("Já foi vista? (true/false): ");
                                boolean vistoSerie = scanner.nextBoolean();
                                scanner.nextLine();

                                System.out.print("Digite o ano de encerramento (ou 0 se ainda estiver em exibição): ");
                                int anoEncerramentoSerie = scanner.nextInt();
                                scanner.nextLine();
                                if (anoEncerramentoSerie != 0) {
                                    validarAno(anoEncerramentoSerie);
                                    if (anoEncerramentoSerie < anoLancamentoSerie) {
                                        throw new AnoInvalidoException("Ano de encerramento não pode ser anterior ao de lançamento.");
                                    }
                                }

                                HashSet<String> elencoSerie = cadastrarListaDeNomes(scanner, "Elenco");

                                System.out.print("Digite o título original: ");
                                String tituloOriginalSerie = scanner.nextLine();
                                lerDadoVazio(tituloOriginalSerie, "Título Original");

                                HashSet<String> ondeAssistirSerie = cadastrarListaDeNomes(scanner, "Onde assistir");
                                HashSet<Temporada> temporadasSerie = cadastrarTemporadas(scanner);

                                cadastrado = serieController.cadastrarSerie(tituloSerie, generosSerie, anoLancamentoSerie,
                                        vistoSerie, anoEncerramentoSerie, elencoSerie, tituloOriginalSerie,
                                        ondeAssistirSerie, temporadasSerie);
                                if (cadastrado)
                                    System.out.println("Série cadastrada com sucesso!");
                                else
                                    System.out.println("Não foi possível cadastrar a série.");

                            } catch (AnoInvalidoException | DadoVazioException e) {
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

                                if (serieId < 0) {
                                    throw new IllegalArgumentException("O ID da série não pode ser negativo.");
                                }

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

    public void cadastrarAno(){

    }

    public static HashSet<Genero> cadastrarGeneros(Scanner scanner){
        HashSet<Genero> generos = new HashSet<>();
        int genero = 0;

        do{
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

        } while(genero != 0);

        return generos;
    }

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
                if (ano < 1900 || ano > anoAtual) {
                    throw new AnoInvalidoException("Ano inválido. O ano deve estar entre 1900 e " + anoAtual + ".");
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

    public void validarAno(int ano) throws AnoInvalidoException{
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);

        if (ano < 0 || ano > anoAtual)
            throw new AnoInvalidoException("O ano fornecido é inválido.");
    }

    public void lerDadoVazio(String dado, String campo) throws DadoVazioException {
        if (dado == null || dado.trim().isEmpty()) {
            throw new DadoVazioException("O campo \"" + campo + "\" não pode estar vazio.");
        }
    }
}

