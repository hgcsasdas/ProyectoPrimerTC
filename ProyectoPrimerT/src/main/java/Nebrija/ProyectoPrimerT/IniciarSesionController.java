package Nebrija.ProyectoPrimerT;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import Nebrija.ProyectoPrimerT.ConexionBD.ConexionMysql;
import Nebrija.ProyectoPrimerT.ConexionBD.NombreSesion;
import Nebrija.ProyectoPrimerT.Usuarios.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private void mostrarAlert(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Usuario");
        alert.setContentText("Usuario o contraseñas incorrectos");
        alert.showAndWait();
    }
    @FXML
    void login(/*ActionEvent event*/) throws NoSuchAlgorithmException, IOException {
    	ConexionMysql conexion = new ConexionMysql();
    	Usuario user = validar();
    	boolean condicion = false;
		try {
			condicion = conexion.loginUser(user);
		} catch (NoSuchAlgorithmException | SQLException e) {
			// TODO Auto-generated catch block
			mostrarAlert(null);
			e.printStackTrace();
		}
		
    	if(condicion) {
    		
        	System.out.println("Existe el user");
        	condicion = conexion.VerHabilitacion(user);
        	
        	if(condicion) {
        		System.out.println("Tiene habilitado entrar");
        		condicion = conexion.VerPermisos(user);
            	
            	if(condicion) {
            		NombreSesion.aniadirNickSesion(user);
            		System.out.println("asdasd");
                	try {
						App.setRoot("adminOpc");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}else {
        			mostrarAlert(null);
                	App.setRoot("normalUser");
            	}
            	
        	}else {
        		System.out.println("No existe el user");
    			mostrarAlert(null);
        	}
    	}else {
			mostrarAlert(null);
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
