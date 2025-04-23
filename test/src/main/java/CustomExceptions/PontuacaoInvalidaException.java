package CustomExceptions;

/**
 * Exceção lançada quando uma pontuação inválida é informada.
 * <p>
 * A pontuação válida é de 1 a 5. Essa exceção deve ser utilizada para indicar
 * que o valor fornecido está fora do intervalo permitido.
 * </p>
 */
public class PontuacaoInvalidaException extends Exception {

    /**
     * Cria uma nova instância de PontuacaoInvalidaException com a mensagem de detalhe especificada.
     *
     * @param mensagem a mensagem detalhando o motivo pelo qual a exceção foi lançada.
     */
    public PontuacaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}
