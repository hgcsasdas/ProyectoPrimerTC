package Nebrija.ProyectoPrimerT;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import Nebrija.ProyectoPrimerT.ConexionBD.ConexionMysql;
import Nebrija.ProyectoPrimerT.Usuarios.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class IniciarSesionController {
    @FXML
    private PasswordField passwordInput;

    @FXML
    private TextField userInput;

    @FXML
    private Button volverInicio;

    @FXML
    void login(/*ActionEvent event*/) throws SQLException, IOException, NoSuchAlgorithmException {
    	ConexionMysql conexion = new ConexionMysql();
    	Usuario user = validar();
    	boolean condicion = conexion.loginUser(user);
    	if(condicion) {
    		
        	System.out.println("Existe el user");
        	condicion = conexion.VerPermisos(user);
        	
        	if(condicion) {
            	App.setRoot("adminOpc");
        	}else {
            	App.setRoot("normalUser");
        	}
        	
    	}else {
        	System.out.println("No existe el user");

    	}
    }

    @FXML
    void volverInicio(/*ActionEvent event*/) throws IOException {
    	App.setRoot("inicio");
    }
    public Usuario validar() {
    	Usuario user = new Usuario();
    	boolean validar = true;

    	do {
    		if(!userInput.getText().isEmpty()) {
    			user.setDatoInsertado(userInput.getText());
				validar = false;
    		}else {
    			userInput.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
    		}
    		
    		if(!passwordInput.getText().isEmpty()) {
    			user.setContrasenia(passwordInput.getText());
				validar = false;
    		}else {
    			passwordInput.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
    		}
    		return user;
    	}while(validar);
    }
}
