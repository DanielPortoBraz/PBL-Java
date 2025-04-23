package CustomExceptions;

/**
 * Exceção que indica que um dado informado está vazio, isto é,
 * preenchido apenas com espaços em branco.
 */
public class DadoVazioException extends RuntimeException {

    /**
     * Cria uma nova instância de DadoVazioException com a mensagem de detalhe especificada.
     *
     * @param message a mensagem detalhada descrevendo o motivo da exceção.
     */
    public DadoVazioException(String message) {
        super(message);
    }
}
