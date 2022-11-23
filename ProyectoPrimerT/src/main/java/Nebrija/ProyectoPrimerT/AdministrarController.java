package Nebrija.ProyectoPrimerT;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Nebrija.ProyectoPrimerT.ConexionBD.ConexionMysql;
import Nebrija.ProyectoPrimerT.Usuarios.Usuario;

public class AdministrarController implements Initializable{
	
	private ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	private ArrayList<Usuario> listaUsuariosBuscador = new ArrayList<Usuario>();
	
	@FXML
    private TextField apellidos;

    @FXML
    private TextField buscador;

    @FXML
    private TextField contrasenia;

    @FXML
    private TextField correo;

    @FXML
    private CheckBox habilitado;

    @FXML
    private TextField nick;

    @FXML
    private Label nickUsuario;

    @FXML
    private TextField nombre;

    @FXML
    private CheckBox permisos;
    
    @FXML
    private TableView<Usuario> tablaUsuarios;

    @FXML
    void actualizarUsuario(/*ActionEvent event*/) {

    }

    @FXML
    void aniadirUsuario(/*ActionEvent event*/) {

    }

    @FXML
    void buscarEnTabla(KeyEvent event) {
    	String x = buscador.getText().toLowerCase();
    	listaUsuariosBuscador.clear();
    	for(int i=0; i<listaUsuarios.size(); i++) {
    		Usuario o = listaUsuarios.get(i);
			if(o.getNombre().toLowerCase().contains(x)) {
    			if(!listaUsuariosBuscador.equals(o)) {
    				listaUsuariosBuscador.add(o);
    			}
    		}else {
				System.out.println("Ya está añadido");	    			
    		}
    	}
    	
    	if(buscador.getText().isEmpty()) {
        	ObservableList<Usuario> listaUsuariosNueva = FXCollections.observableArrayList(listaUsuarios);
        	tablaUsuarios.setItems((ObservableList<Usuario>) listaUsuariosNueva);    		
    	}else {
        	ObservableList<Usuario> listaUsuariosNueva = FXCollections.observableArrayList(listaUsuariosBuscador);
        	tablaUsuarios.setItems((ObservableList<Usuario>) listaUsuariosNueva);
    	}
    }

    @FXML
    void eliminarUsuario(/*ActionEvent event*/) {

    }

    @FXML
    void irWeb(/*ActionEvent event*/) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
    	ConexionMysql conexion = new ConexionMysql();
    	try {
			conexion.llenarArrayUsuario(listaUsuarios);
			conexion.llenarArrayUsuario(listaUsuariosBuscador);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
		TableColumn idCol = new TableColumn("idUsuarios");
    	TableColumn nombreCol = new TableColumn("nombre");
    	TableColumn apellidosCol = new TableColumn("apellidos");
    	TableColumn nickCol = new TableColumn("nick");
    	TableColumn contraseniaCol = new TableColumn("contraseña");
    	TableColumn correoCol = new TableColumn("correo");
    	TableColumn permisosCol = new TableColumn("permisos");
    	TableColumn habilitarCol = new TableColumn("habilitado");
    	
    	tablaUsuarios.getColumns().addAll(idCol, nombreCol, apellidosCol, nickCol, contraseniaCol, correoCol, permisosCol, habilitarCol);
    	
    	idCol.setCellValueFactory(new PropertyValueFactory<Usuario, String>("id"));
    	nombreCol.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
    	apellidosCol.setCellValueFactory(new PropertyValueFactory<Usuario, String>("apellidos"));
    	nickCol.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nick"));
    	contraseniaCol.setCellValueFactory(new PropertyValueFactory<Usuario, String>("contrasenia"));
    	correoCol.setCellValueFactory(new PropertyValueFactory<Usuario, String>("correo"));
    	permisosCol.setCellValueFactory(new PropertyValueFactory<Usuario, String>("permisos"));
    	habilitarCol.setCellValueFactory(new PropertyValueFactory<Usuario, String>("habilitar"));

    	ObservableList<Usuario> listaUsuariosNueva = FXCollections.observableArrayList(listaUsuarios);
    	tablaUsuarios.setItems((ObservableList<Usuario>) listaUsuariosNueva);
	}
}