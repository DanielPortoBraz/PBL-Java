package principal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import Controller.LivroController;
import javafx.scene.text.Text;

public class menu_principalController implements Initializable {

    @FXML
    private Button bt_avaliar;

    @FXML
    private Button bt_buscar;

    @FXML
    private Button bt_cadastrar;

    @FXML
    private Button bt_listar;

    @FXML
    private Button bt_remover;

    @FXML
    private Button bt_sair;

    @FXML
    private Text id_titulo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
