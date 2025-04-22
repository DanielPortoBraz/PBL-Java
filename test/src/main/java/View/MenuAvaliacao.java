package View;

import Controller.*;
import CustomExceptions.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class MenuAvaliacao implements Menu {
    private Scanner scanner;
    protected LivroController livroController;
    protected FilmeController filmeController;
    protected SerieController serieController;

    public MenuAvaliacao(Scanner scanner, LivroController livroController,
                        FilmeController filmeController,
                        SerieController serieController){
        this.scanner = scanner;
        this.livroController = livroController;
        this.filmeController = filmeController;
        this.serieController = serieController;
    }

    @Override
    public void exibir(){
        String opcao;
        boolean avaliado;

        do {
            System.out.println("\n-- MENU DE AVALIAÇÃO --");
            System.out.println("1- Livro");
            System.out.println("2- Filme");
            System.out.println("3- Série");
            System.out.println("4- Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextLine();

            switch (opcao) {
                case "1": // Livro

                    try {
                        System.out.println("\n<<< AVALIAÇÃO DE LIVRO >>>");

                        System.out.print("Digite o ISBN do livro: ");
                        String isbnLivro = scanner.nextLine();

                        System.out.print("Escreva sua review: ");
                        String reviewLivro = scanner.nextLine();
                        lerDadoVazio(reviewLivro, "Review");

                        System.out.print("Dê uma pontuação de 1 a 5: ");
                        int pontuacaoLivro = scanner.nextInt();
                        scanner.nextLine();
                        validarPontuacao(pontuacaoLivro);

                        Calendar dataVistoLivro = lerData(scanner);

                        avaliado = livroController.avaliarLivro(isbnLivro, reviewLivro,
                                pontuacaoLivro, dataVistoLivro);

                        if (avaliado)
                            System.out.println("Livro avaliado com sucesso!");
                        else
                            System.out.println("Não foi possível avaliar o livro.");
                    } catch (PontuacaoInvalidaException | DadoVazioException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Erro inesperado: " + e.getMessage());
                        scanner.nextLine();
                    }

                    break;

                case "2": // Série
                    System.out.println("\n<<< AVALIAÇÃO DE FILME >>>");

                    try {
                        System.out.print("Digite o ID do filme: ");
                        int idFilme = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Escreva sua review: ");
                        String reviewFilme = scanner.nextLine();
                        lerDadoVazio(reviewFilme, "Review");

                        System.out.print("Dê uma pontuação de 1 a 5: ");
                        int pontuacaoFilme = scanner.nextInt();
                        scanner.nextLine();
                        validarPontuacao(pontuacaoFilme);

                        Calendar dataVistoFilme = lerData(scanner);

                        avaliado = filmeController.avaliarFilme(idFilme, reviewFilme, pontuacaoFilme, dataVistoFilme);

                        if (avaliado)
                            System.out.println("Filme avaliado com sucesso!");
                        else
                            System.out.println("Não foi possível avaliar o filme.");

                    } catch (PontuacaoInvalidaException | DadoVazioException e) {
                        System.out.println("Erro na avaliação: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Erro inesperado: " + e.getMessage());
                        scanner.nextLine();
                    }

                    break;

                case "3": // Série - Temporada
                    System.out.println("\n<<< AVALIAÇÃO DE SÉRIE >>>\n" +
                            "1- Série\n" +
                            "2- Temporada\n" +
                            "3- Retornar");
                    opcao = scanner.nextLine();

                    switch (opcao) {
                        case "1":
                            System.out.println("\n<<< AVALIAÇÃO DE SÉRIE >>>");

                            try {
                                System.out.print("Digite o ID da série: ");
                                int idSerie = scanner.nextInt();
                                scanner.nextLine();

                                System.out.print("Escreva sua review da série: ");
                                String reviewSerie = scanner.nextLine();
                                lerDadoVazio(reviewSerie, "Review da Série");

                                Calendar dataVistoSerie = lerData(scanner);

                                avaliado = serieController.avaliarSerie(idSerie, reviewSerie, dataVistoSerie);

                                if (avaliado)
                                    System.out.println("Série avaliada com sucesso!");
                                else
                                    System.out.println("Não foi possível avaliar a série.");

                            } catch (DadoVazioException e) {
                                System.out.println("Erro na avaliação: " + e.getMessage());
                            } catch (Exception e) {
                                System.out.println("Erro inesperado: " + e.getMessage());
                                scanner.nextLine();
                            }

                            break;

                        case "2":
                            System.out.println("\n<<< AVALIAÇÃO DE TEMPORADA >>>");

                            try {
                                System.out.print("Digite o ID da série: ");
                                int serieId = scanner.nextInt();
                                scanner.nextLine();

                                System.out.print("Digite o número da temporada que deseja avaliar: ");
                                int numeroTemporada = scanner.nextInt();
                                scanner.nextLine();

                                System.out.print("Escreva sua review da temporada: ");
                                String reviewTemporada = scanner.nextLine();
                                lerDadoVazio(reviewTemporada, "Review da Temporada");

                                System.out.print("Dê uma pontuação de 1 a 5: ");
                                int pontuacaoTemporada = scanner.nextInt();
                                scanner.nextLine();

                                validarPontuacao(pontuacaoTemporada);

                                avaliado = serieController.avaliarTemporada(serieId, numeroTemporada, reviewTemporada, pontuacaoTemporada);

                                if (avaliado)
                                    System.out.println("Temporada avaliada com sucesso!");
                                else
                                    System.out.println("Não foi possível avaliar a temporada.");

                            } catch (PontuacaoInvalidaException | DadoVazioException e) {
                                System.out.println("Erro na avaliação: " + e.getMessage());
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

    public Calendar lerData(Scanner scanner) {

        while(true) {
            System.out.print("Digite a data (dd/MM/yyyy): ");
            String dataStr = scanner.nextLine();

            Calendar data = Calendar.getInstance();
            try {
                DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                Date dataConvertida = formato.parse(dataStr);
                data.setTime(dataConvertida);
            } catch (ParseException e) {
                System.out.println("Data inválida.");
            }
            return data;
        }
    }

    public void validarPontuacao(int pontuacao) throws PontuacaoInvalidaException{

        if (pontuacao < 1 || pontuacao > 5)
            throw new PontuacaoInvalidaException("Pontuação inválida. A pontuação dever ser de 1 a 5.");
    }

    public void lerDadoVazio(String dado, String campo) throws DadoVazioException {
        if (dado == null || dado.trim().isEmpty()) {
            throw new DadoVazioException("O campo \"" + campo + "\" não pode estar vazio.");
        }
    }
}
