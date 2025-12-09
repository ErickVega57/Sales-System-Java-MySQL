package org.borghisales.salessysten.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.borghisales.salessysten.model.Seller;
import org.borghisales.salessysten.model.SellerDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpController extends MenuController implements Initializable {

    @FXML
    private TextArea helpText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String texto = """
                Fucionalidades
                Inicio de sesión
                Para iniciar sesión, se requiere el DNI y la contraseña del vendedor. En la base de datos, estos corresponden a los atributos del vendedor(seller), donde el DNI se asocia con 'dni' y la contraseña con 'user'.
                """;
        helpText.setText(texto);
    }
    public void onClose() {
        System.out.println("Cerrando ventana de ayuda");
        Stage stage = (Stage) helpText.getScene().getWindow();
        stage.close();
        openNewStage(MANAGEMENT_VIEW_FXML,"Menú principal");
    }
}



