package principal.controller.livroC;

import static principal.DiarioCultural.changeScene;
import static principal.DiarioCultural.livroController;
import static principal.controller.tela_principalController.isbn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import CustomExceptions.*;
import principal.controller.Validador;

import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

/**
 * Controlador responsável pela tela de avaliação de um livro já cadastrado.
 * Permite inserir uma pontuação, escrever uma resenha e definir a data de leitura.
 * A validação dos dados é feita antes de enviar a avaliação ao controller principal.
 */
public class tela_avaliacao_livroController implements Initializable {

    @FXML
    private Button bt_confirmar;

    @FXML
    private DatePicker dp_dataLeitura;

    @FXML
    private Button bt_retornar;

    @FXML
    private TextField tf_pontuacao;

    @FXML
    private TextField tf_review;

    private String isbnLivro; // Recebido da tela anterior (não utilizado diretamente neste código)

    /**
     * Ação executada ao clicar no botão "Confirmar".
     * Realiza a leitura dos campos, valida os dados e envia a avaliação para o controller.
     *
     * @param event Evento de clique no botão
     */
    @FXML
    void clicarConfirmar(ActionEvent event) {
        try {
            String review = tf_review.getText();
            int pontuacao = Integer.parseInt(tf_pontuacao.getText());

            // Validação da pontuação (entre 1 e 5)
            if (pontuacao < 1 || pontuacao > 5) {
                throw new PontuacaoInvalidaException("Pontuação deve estar entre 1 e 5.");
            }

            // Validação da data de leitura
            LocalDate dataSelecionada = dp_dataLeitura.getValue();
            if (dataSelecionada == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Data inválida", "Por favor, selecione uma data.");
                return;
            }

            if (dataSelecionada.getYear() > 2025) {
                throw new AnoInvalidoException("Ano não pode ser superior a 2025.");
            }

            Calendar dataVisto = GregorianCalendar.from(
                    dataSelecionada.atStartOfDay(Calendar.getInstance().getTimeZone().toZoneId())
            );

            // Chama o controller principal para registrar a avaliação
            boolean sucesso = livroController.avaliarLivro(isbn, review, pontuacao, dataVisto);

            if (sucesso) {
                livroController.salvarLivros();
                mostrarAlerta(Alert.AlertType.INFORMATION, "Avaliação registrada", "Livro avaliado com sucesso!");
                changeScene("/telas/tela_principal.fxml");
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro ao avaliar", "Não foi possível salvar a avaliação.");
            }

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de entrada", "Pontuação inválida. Digite apenas números.");
        } catch (PontuacaoInvalidaException | AnoInvalidoException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "Dados inválidos", e.getMessage());
        }
    }

    /**
     * Ação executada ao clicar no botão "Retornar".
     * Volta para a tela principal sem salvar a avaliação.
     *
     * @param event Evento de clique no botão
     */
    @FXML
    void clicarRetornar(ActionEvent event) {
        changeScene("/telas/tela_principal.fxml");
    }

    /**
     * Inicializa os componentes da interface, aplicando validadores de entrada.
     *
     * @param url            não utilizado
     * @param resourceBundle não utilizado
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Validador.entradaSomenteNumerica(tf_pontuacao); // Restringe a entrada a números
    }

    /**
     * Exibe um alerta com base no tipo, título e mensagem fornecidos.
     *
     * @param tipo     Tipo de alerta (INFORMATION, WARNING, ERROR)
     * @param titulo   Título da janela do alerta
     * @param mensagem Mensagem principal exibida ao usuário
     */
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
