package View_GUI.controller.serieC;

import CustomExceptions.AnoInvalidoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import static View_GUI.DiarioCultural.*;
import static View_GUI.DiarioCultural.serieController;
import static View_GUI.controller.tela_principalController.id;

/**
 * Controlador responsável pela interface de avaliação de séries.
 * Permite ao usuário inserir uma resenha textual e a data em que assistiu à série.
 * Os dados são validados antes de serem enviados ao controller View_GUI.
 */
public class tela_avaliacao_serieController implements Initializable {

    @FXML
    private Button bt_confirmar;

    @FXML
    private DatePicker dp_dataLeitura;

    @FXML
    private Button bt_retornar;

    @FXML
    private TextField tf_review;

    /**
     * Ação executada ao clicar no botão "Confirmar".
     * Realiza a validação dos campos e envia os dados para salvar a avaliação da série.
     *
     * @param event Evento de clique no botão
     */
    @FXML
    void clicarConfirmar(ActionEvent event) {
        try {
            String review = tf_review.getText();

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

            // Envia os dados ao controller da série
            boolean sucesso = serieController.avaliarSerie(id, review, dataVisto);

            if (sucesso) {
                serieController.salvarSeries();
                mostrarAlerta(Alert.AlertType.INFORMATION, "Avaliação registrada", "Série avaliada com sucesso!");
                changeScene("/telas/tela_principal.fxml");
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro ao avaliar", "Não foi possível salvar a avaliação.");
            }

        } catch (AnoInvalidoException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "Dado inválido", e.getMessage());
        }
    }

    /**
     * Ação executada ao clicar no botão "Retornar".
     * Retorna à tela View_GUI sem salvar alterações.
     *
     * @param event Evento de clique no botão
     */
    @FXML
    void clicarRetornar(ActionEvent event) {
        changeScene("/telas/tela_principal.fxml");
    }

    /**
     * Inicializa o controlador da interface.
     * (Sem inicializações adicionais nesta tela.)
     *
     * @param url            não utilizado
     * @param resourceBundle não utilizado
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Nenhuma inicialização necessária
    }

    /**
     * Exibe um alerta ao usuário com o tipo, título e mensagem fornecidos.
     *
     * @param tipo     Tipo de alerta (INFORMATION, WARNING, ERROR)
     * @param titulo   Título da caixa de diálogo
     * @param mensagem Mensagem View_GUI do alerta
     */
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
