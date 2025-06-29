package View_GUI.controller.serieC;

import Model.Temporada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import View_GUI.controller.Validador;

import java.net.URL;
import java.util.ResourceBundle;

import static View_GUI.DiarioCultural.serieController;
import static View_GUI.controller.tela_principalController.id;
import static View_GUI.DiarioCultural.changeScene;

/**
 * Controlador responsável pela interface de cadastro de uma nova temporada
 * vinculada a uma série previamente registrada.
 *
 * Fornece validação, criação e envio da temporada ao controller View_GUI.
 */
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

    /**
     * Ação executada ao clicar no botão "Confirmar".
     * Lê os dados da temporada, realiza a validação e tenta cadastrar a temporada
     * na série correspondente.
     *
     * @param event Evento de clique do botão
     */
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
                changeScene("/telas/tela_principal.fxml");
            } else {
                exibirAlerta("Erro no cadastro", "Não foi possível cadastrar a temporada.");
            }
        } catch (NumberFormatException e) {
            exibirAlerta("Entrada inválida", "Preencha todos os campos com números válidos.");
        } catch (Exception e) {
            exibirAlerta("Erro inesperado", "Ocorreu um erro: " + e.getMessage());
        }
    }

    /**
     * Ação executada ao clicar no botão "Retornar".
     * Volta para a tela View_GUI sem realizar alterações.
     *
     * @param event Evento de clique do botão
     */
    @FXML
    void clicarRetornar(ActionEvent event) {
        changeScene("/telas/tela_principal.fxml");
    }

    /**
     * Inicializa os campos da interface, aplicando validadores de entrada numérica.
     *
     * @param url            não utilizado
     * @param resourceBundle não utilizado
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Validador.entradaSomenteNumerica(tf_ano);
        Validador.entradaSomenteNumerica(tf_numero);
        Validador.entradaSomenteNumerica(tf_quantidadeEps);
    }

    /**
     * Exibe uma caixa de alerta com a mensagem fornecida.
     *
     * @param titulo   Título da janela de alerta
     * @param mensagem Mensagem View_GUI a ser exibida
     */
    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
