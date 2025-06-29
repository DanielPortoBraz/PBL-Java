package principal.controller.serieC;

import CustomExceptions.PontuacaoInvalidaException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import principal.controller.Validador;

import static principal.DiarioCultural.changeScene;
import static principal.DiarioCultural.serieController;
import static principal.controller.tela_principalController.id;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador responsável pela interface de avaliação de temporadas de séries.
 * Permite ao usuário informar o número da temporada, a pontuação e um comentário (review).
 * Realiza validações e envia os dados para o controller principal da série.
 */
public class tela_avaliacao_temporadaController implements Initializable {

    @FXML
    private Button bt_confirmar;

    @FXML
    private Button bt_retornar;

    @FXML
    private TextField tf_numero;

    @FXML
    private TextField tf_pontuacao;

    @FXML
    private TextField tf_review;

    /**
     * Ação executada ao clicar no botão "Confirmar".
     * Valida os campos de número da temporada e pontuação, lança exceções se inválidos,
     * e tenta salvar a avaliação da temporada via controller.
     *
     * @param event Evento de clique no botão
     */
    @FXML
    void clicarConfirmar(ActionEvent event) {
        try {
            String review = tf_review.getText();
            int numeroTemporada = Integer.parseInt(tf_numero.getText());
            int pontuacao = Integer.parseInt(tf_pontuacao.getText());

            // Validação da pontuação (deve estar entre 1 e 5)
            if (pontuacao < 1 || pontuacao > 5) {
                throw new PontuacaoInvalidaException("Pontuação deve estar entre 1 e 5.");
            }

            boolean sucesso = serieController.avaliarTemporada(id, numeroTemporada, review, pontuacao);

            if (sucesso) {
                serieController.salvarSeries();
                mostrarAlerta(Alert.AlertType.INFORMATION, "Avaliação registrada", "Temporada avaliada com sucesso!");
                changeScene("/telas/tela_principal.fxml");
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro ao avaliar", "Não foi possível salvar a avaliação.");
            }

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de entrada", "Digite apenas números válidos para número da temporada e pontuação.");
        } catch (PontuacaoInvalidaException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "Pontuação inválida", e.getMessage());
        }
    }

    /**
     * Ação executada ao clicar no botão "Retornar".
     * Retorna à tela principal sem salvar alterações.
     *
     * @param event Evento de clique no botão
     */
    @FXML
    void clicarRetornar(ActionEvent event) {
        changeScene("/telas/tela_principal.fxml");
    }

    /**
     * Inicializa o controlador da interface, aplicando restrições para aceitar somente números nos campos apropriados.
     *
     * @param url            não utilizado
     * @param resourceBundle não utilizado
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Validador.entradaSomenteNumerica(tf_numero);
        Validador.entradaSomenteNumerica(tf_pontuacao);
    }

    /**
     * Exibe um alerta para o usuário com o tipo, título e mensagem especificados.
     *
     * @param tipo     Tipo do alerta (INFORMATION, WARNING, ERROR)
     * @param titulo   Título da janela de alerta
     * @param mensagem Conteúdo da mensagem exibida
     */
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
