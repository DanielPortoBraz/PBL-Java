package CustomExceptions;

/**
 * Exceção que indica que um ano inválido foi informado.
 * <p>
 * Esta exceção deve ser lançada quando um ano não atender aos critérios esperados,
 * permitindo que o programa trate de forma específica este tipo de erro.
 * </p>
 */
public class AnoInvalidoException extends Exception {

    /**
     * Cria uma nova instância de AnoInvalidoException com uma mensagem de detalhe.
     *
     * @param mensagem a descrição detalhada do erro que ocasionou a exceção.
     */
    public AnoInvalidoException(String mensagem){
        super(mensagem);
    }
}

