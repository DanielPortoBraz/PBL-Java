module principal {
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires org.controlsfx.controls;

    opens principal to javafx.fxml;
    opens principal.controller to javafx.fxml;
    opens Model to javafx.base, com.fasterxml.jackson.databind;
    exports Model;
    exports principal to javafx.graphics;
}