package principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DiarioCultural extends Application {
    private static Stage janela;
    private static Scene TelaPrincipal;
    private static Scene TelaCadastro;

    @Override
    public void start(Stage stage) throws Exception {
        janela = stage;

        Parent tela1 = FXMLLoader.load(getClass().getResource("/telas/tela_principal.fxml"));
        TelaPrincipal = new Scene(tela1);

        Parent tela2 = FXMLLoader.load(getClass().getResource("/telas/tela_cadastro.fxml"));
        TelaCadastro = new Scene(tela2);

        janela.setScene(TelaPrincipal);
        janela.show();
    }

    public static void changeScene(String str){
        switch(str){
            case "principal":
                janela.setScene(TelaPrincipal);
                break;
            case "cadastro":
                janela.setScene(TelaCadastro);
                break;
        }
    }
}