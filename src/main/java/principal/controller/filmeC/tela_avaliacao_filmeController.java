package principal.controller.filmeC;

import CustomExceptions.AnoInvalidoException;
import CustomExceptions.PontuacaoInvalidaException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import principal.controller.Validador;

import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import static principal.DiarioCultural.changeScene;
import static principal.DiarioCultural.filmeController;
import static principal.controller.tela_principalController.id;

/**
 * Controlador responsável pela interface de avaliação de filmes.
 * Permite ao usuário inserir uma pontuação, escrever uma resenha
 * e indicar a data em que assistiu ao filme.
 * Os dados são validados antes de serem enviados ao sistema.
 */
public class tela_avaliacao_filmeController implements Initializable {

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

    private String idFilme; // Identificador do filme (não usado diretamente aqui)

    /**
     * Ação executada ao clicar no botão "Confirmar".
     * Valida os dados preenchidos e envia a avaliação para o controller principal.
     *
     * @param event Evento de clique no botão
     */
    @FXML
    void clicarConfirmar(ActionEvent event) {
        try {
            String review = tf_review.getText();
            int pontuacao = Integer.parseInt(tf_pontuacao.getText());

            // Valida pontuação entre 1 e 5
            if (pontuacao < 1 || pontuacao > 5) {
                throw new PontuacaoInvalidaException("Pontuação deve estar entre 1 e 5.");
            }

            // Valida a data selecionada
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

            // Envia os dados ao controller para persistência
            boolean sucesso = filmeController.avaliarFilme(id, review, pontuacao, dataVisto);

            if (sucesso) {
                filmeController.salvarFilmes();
                mostrarAlerta(Alert.AlertType.INFORMATION, "Avaliação registrada", "Filme avaliado com sucesso!");
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
     * Retorna para a tela principal sem salvar a avaliação.
     *
     * @param event Evento de clique no botão
     */
    @FXML
    void clicarRetornar(ActionEvent event) {
        changeScene("/telas/tela_principal.fxml");
    }

    /**
     * Inicializa os componentes da interface e aplica restrições aos campos.
     *
     * @param url            não utilizado
     * @param resourceBundle não utilizado
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Validador.entradaSomenteNumerica(tf_pontuacao); // Impede letras no campo de pontuação
    }

    /**
     * Exibe um alerta ao usuário com o tipo, título e mensagem especificados.
     *
     * @param tipo     Tipo do alerta (INFORMATION, WARNING, ERROR)
     * @param titulo   Título da janela de alerta
     * @param mensagem Mensagem a ser exibida no conteúdo do alerta
     */
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
