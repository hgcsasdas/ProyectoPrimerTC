package Nebrija.ProyectoPrimerT;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminEleccionController {

    @FXML
    private Button btnPagAdmin;

    @FXML
    private Button btnPagWeb;

    @FXML
    void AdminGestionar(/*ActionEvent event*/) throws IOException {
    	App.setScene("PaginaAdministracionUsuarios",757,540);
    }

    @FXML
    void cambiarPagWeb(/*ActionEvent event*/) {

    }
}
