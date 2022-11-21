package Nebrija.ProyectoPrimerT;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class IniciarSesionController {
    @FXML
    private TextField passwordInput;

    @FXML
    private TextField userInput;

    @FXML
    private Button volverInicio;

    @FXML
    void login(/*ActionEvent event*/) {

    }

    @FXML
    void volverInicio(/*ActionEvent event*/) throws IOException {
    	App.setRoot("inicio");
    }
}
