package Nebrija.ProyectoPrimerT;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InicioController {

    @FXML
    private Button iniciarSesion;

    @FXML
    private Button registrarse;
    
    @FXML
    void cambiarIniciarSesion(/*ActionEvent event*/) throws IOException {
    	App.setRoot("iniciarSesion");

    }

    @FXML
    void cambiarRegistrarse(/*ActionEvent event*/) throws IOException {
    	App.setRoot("registrarse");
    }
}
