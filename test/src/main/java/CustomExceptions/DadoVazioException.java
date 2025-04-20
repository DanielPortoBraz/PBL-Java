package CustomExceptions;

// Para dados preenchidos com espaços em branco na View
public class DadoVazioException extends RuntimeException {
    public DadoVazioException(String message) {
        super(message);
    }
}
