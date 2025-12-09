package org.borghisales.salessysten.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpController implements Initializable {

    @FXML
    private TextArea textAreaHelp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Aquí se puede inicializar texto por defecto si se desea
        if (textAreaHelp != null && textAreaHelp.getText().isEmpty()) {
            textAreaHelp.setText(
                    "Bienvenido al sistema Borghi Sales System.\n\n" +
                            "- Registrar ventas.\n" +
                            "- Gestionar clientes.\n" +
                            "- Agregar productos al carrito.\n" +
                            "- Seleccionar opciones de envío y calcular el total.\n\n" +
                            "Para más información sobre el sistema, consulta el repositorio en GitHub."
            );
        }
    }

    // Cierra la ventana de ayuda
    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
