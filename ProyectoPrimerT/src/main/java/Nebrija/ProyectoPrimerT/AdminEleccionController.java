package Nebrija.ProyectoPrimerT;

import java.io.IOException;

import Nebrija.ProyectoPrimerT.Usuarios.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminEleccionController {

	Usuario user = new Usuario();
    @FXML
    private Button btnPagAdmin;

    @FXML
    private Button btnPagWeb;

    @FXML
    void AdminGestionar(ActionEvent event) throws IOException {
    	App.setScene("PaginaAdministracionUsuarios",800,540);
    }

    @FXML
    void cambiarPagWeb(/*ActionEvent event*/) {

    }
  
}
