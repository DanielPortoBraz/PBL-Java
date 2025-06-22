package principal.controller;

import static principal.DiarioCultural.changeScene;
import static principal.DiarioCultural.livroController;
import static principal.controller.tela_principalController.idRegistro;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import CustomExceptions.*;
import principal.controller.Validador;

import principal.controller.tela_principalController;

import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

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

    private String isbnLivro; // Recebido da tela anterior

    @FXML
    void clicarConfirmar(ActionEvent event) {
        try {
            String review = tf_review.getText();
            int pontuacao = Integer.parseInt(tf_pontuacao.getText());

            // Valida pontuação (1 a 5)
            if (pontuacao < 1 || pontuacao > 5) {
                throw new PontuacaoInvalidaException("Pontuação deve estar entre 1 e 5.");
            }

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


            boolean sucesso = livroController.avaliarLivro(idRegistro, review, pontuacao, dataVisto);

            if (sucesso) {
                livroController.salvarLivros();
                mostrarAlerta(Alert.AlertType.INFORMATION, "Avaliação registrada", "Livro avaliado com sucesso!");
                changeScene("principal");
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro ao avaliar", "Não foi possível salvar a avaliação.");
            }

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de entrada", "Pontuação inválida. Digite apenas números.");
        } catch (PontuacaoInvalidaException | AnoInvalidoException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "Dados inválidos", e.getMessage());
        }
    }

    @FXML
    void clicarRetornar(ActionEvent event) {
        changeScene("principal");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Validador.entradaSomenteNumerica(tf_pontuacao); // Impede letras no campo de pontuação
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
