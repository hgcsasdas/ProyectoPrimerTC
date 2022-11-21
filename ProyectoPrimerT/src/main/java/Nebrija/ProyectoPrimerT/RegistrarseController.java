package Nebrija.ProyectoPrimerT;

import java.io.IOException;
import java.sql.SQLException;

import Nebrija.ProyectoPrimerT.ConexionBD.ConexionMysql;
import Nebrija.ProyectoPrimerT.Usuarios.Usuario;
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
    private TextField nickname;

    @FXML
    private TextField nombre;
    @FXML
    void registrarUsuario(/*ActionEvent event*/) throws SQLException {
    	ConexionMysql conexion = new ConexionMysql();
    	Usuario user = validar();
    	conexion.crearUsuario(user);
    }

    @FXML
    void volverInicio(/*ActionEvent event*/) throws IOException {
    	App.setRoot("inicio");
    }
    
    public Usuario validar() {
    	Usuario user = new Usuario();
    	boolean validar = true;
    	do {
			if(!nombre.getText().isEmpty()) {
				user.setNombre(nombre.getText());
				validar = false;
			}else {
	        	nombre.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
			}
			if(!apellidos.getText().isEmpty()) {
				user.setApellidos(apellidos.getText());
				validar = false;
			}else {
	        	apellidos.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
			}
			if(!nickname.getText().isEmpty()) {
				user.setNick(nickname.getText());
				validar = false;
			}else {
	        	nickname.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
			}
			if(!contrasenia.getText().isEmpty()) {
				user.setContrasenia(contrasenia.getText());
				validar = false;
			}else {
	        	contrasenia.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
			}
			if(!correo.getText().isEmpty()) {
				user.setCorreo(correo.getText());
				validar = false;
			}else {
	        	correo.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
			}
		} while (validar);
		return user;
    }

}
