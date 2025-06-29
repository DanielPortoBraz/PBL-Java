package principal.controller.serieC;

import CustomExceptions.AnoInvalidoException;
import CustomExceptions.PontuacaoInvalidaException;
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

import static principal.DiarioCultural.*;
import static principal.DiarioCultural.serieController;
import static principal.controller.tela_principalController.id;

public class tela_avaliacao_serieController implements Initializable {

    @FXML
    private Button bt_confirmar;

    @FXML
    private DatePicker dp_dataLeitura;

    @FXML
    private Button bt_retornar;

    @FXML
    private TextField tf_review;

    @FXML
    void clicarConfirmar(ActionEvent event) {
        try {
            String review = tf_review.getText();

            // Converte a data e valida o ano
            LocalDate dataSelecionada = dp_dataLeitura.getValue();
            if (dataSelecionada == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Data inválida", "Por favor, selecione uma data.");
                return;
            }

            if (dataSelecionada.getYear() > 2025) {
                throw new AnoInvalidoException("Ano não pode ser superior a 2025.");
            }

            Calendar dataVisto = GregorianCalendar.from(dataSelecionada.atStartOfDay(Calendar.getInstance().getTimeZone().toZoneId()));


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

    @FXML
    void clicarRetornar(ActionEvent event) {
        changeScene("/telas/tela_principal.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
