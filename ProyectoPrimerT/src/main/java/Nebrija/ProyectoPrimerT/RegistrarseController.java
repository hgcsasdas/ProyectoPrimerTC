package Nebrija.ProyectoPrimerT;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import Nebrija.ProyectoPrimerT.ConexionBD.ConexionMysql;
import Nebrija.ProyectoPrimerT.Usuarios.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrarseController {

	@FXML
    private TextField apellidos;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private Button btnVolverInicio;

    @FXML
    private PasswordField contrasenia;

    @FXML
    private TextField correo;

    @FXML
    private TextField nickname;

    @FXML
    private TextField nombre;
    private boolean creado = false;
    @FXML
    void registrarUsuario(/*ActionEvent event*/) throws SQLException, NoSuchAlgorithmException {
    	ConexionMysql conexion = new ConexionMysql();
    	Usuario user = validar();
    	if(creado){
        	creado = conexion.crearUsuario(user, creado);
    	}else {
    		System.out.println("sse ha validao");
    	}    	
    	if(creado) {
    		String u = "El usuario se ha creado con éxito";
    		mostrarAlertInfo(null);
    	}else {
    		String u = "El usuario no se ha podido crear";
    		mostrarAlertInfo(null);
    	}
    	
    }

    @FXML
    void volverInicio(/*ActionEvent event*/) throws IOException {
    	App.setRoot("inicio");
    }
    
    @FXML
    private void mostrarAlertInfo(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Usuario");
        alert.setContentText("Usuario creado");
        alert.showAndWait();
    	try {
			App.setRoot("inicio");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    private void mostrarAlert(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Usuario");
        alert.setContentText("No se puede crear porque se ha superado el número de caracteres posibles");
        alert.showAndWait();
    }
    
    public Usuario validar() {
    	Usuario user = new Usuario();
    	
			if(!nombre.getText().isEmpty()) {
				user.setNombre(nombre.getText());
			}else {
				mostrarAlert(null);
				creado = false;
	        	nombre.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
			}
			if(!apellidos.getText().isEmpty()) {
				user.setApellidos(apellidos.getText());
			}else {
				mostrarAlert(null);
				creado = false;
	        	apellidos.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
			}
			if(!nickname.getText().isEmpty()) {
				user.setNick(nickname.getText());
			}else {
				mostrarAlert(null);
				creado = false;
	        	nickname.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
			}
			if(!contrasenia.getText().isEmpty()) {
				user.setContrasenia(contrasenia.getText());
			}else {
				mostrarAlert(null);
				creado = false;
				contrasenia.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
			}
			if(!correo.getText().isEmpty()) {
				user.setCorreo(correo.getText());
			}else {
				mostrarAlert(null);
				creado = false;
	        	correo.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
			}
		
			
			return user;
    }

}