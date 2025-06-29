module View_GUI {
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires org.controlsfx.controls;
    requires jdk.compiler;

    opens View_GUI to javafx.fxml;
    opens View_GUI.controller to javafx.fxml;
    opens Model to javafx.base, com.fasterxml.jackson.databind;
    exports Model;
    exports View_GUI to javafx.graphics;
    opens View_GUI.controller.livroC to javafx.fxml;
    opens View_GUI.controller.filmeC to javafx.fxml;
    opens View_GUI.controller.serieC to javafx.fxml;
}