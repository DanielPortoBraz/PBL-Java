package principal.controller.serieC;

import Model.Temporada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import principal.controller.Validador;

import java.net.URL;
import java.util.ResourceBundle;

import static principal.DiarioCultural.serieController;
import static principal.controller.tela_principalController.id;
import static principal.DiarioCultural.changeScene;

public class tela_cadastro_temporadaController implements Initializable {

    @FXML
    private Button bt_confirmar;

    @FXML
    private Button bt_retornar;

    @FXML
    private TextField tf_ano;

    @FXML
    private TextField tf_numero;

    @FXML
    private TextField tf_quantidadeEps;

    @FXML
    void clicarConfirmar(ActionEvent event) {
        try {
            int numero = Integer.parseInt(tf_numero.getText());
            int ano = Integer.parseInt(tf_ano.getText());
            int quantidadeEpisodios = Integer.parseInt(tf_quantidadeEps.getText());

            Temporada temporada = new Temporada(ano, quantidadeEpisodios, numero);

            boolean sucesso = serieController.cadastrarTemporada(id, temporada);
            if (sucesso) {
                exibirAlerta("Cadastro realizado", "Temporada cadastrada com sucesso!");
                changeScene("/telas/tela_principal.fxml"); // Se quiser voltar após cadastrar
            } else {
                exibirAlerta("Erro no cadastro", "Não foi possível cadastrar a temporada.");
            }
        } catch (NumberFormatException e) {
            exibirAlerta("Entrada inválida", "Preencha todos os campos com números válidos.");
        } catch (Exception e) {
            exibirAlerta("Erro inesperado", "Ocorreu um erro: " + e.getMessage());
        }
    }


    @FXML
    void clicarRetornar(ActionEvent event) {
        changeScene("/telas/tela_principal.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Validador.entradaSomenteNumerica(tf_ano);
        Validador.entradaSomenteNumerica(tf_numero);
        Validador.entradaSomenteNumerica(tf_quantidadeEps);
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
