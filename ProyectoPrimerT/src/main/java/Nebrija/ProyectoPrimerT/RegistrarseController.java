package Nebrija.ProyectoPrimerT;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegistrarseController {

    @FXML
    private TextField apellidos;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private Button btnVolverInicio;

    @FXML
    private TextField contrasenia;

    @FXML
    private TextField correo;

    @FXML
    private TextField nombre;

    @FXML
    void registrarUsuario(/*ActionEvent event*/) {

    }

    @FXML
    void volverInicio(/*ActionEvent event*/) throws IOException {
    	App.setRoot("inicio");
    }
}
