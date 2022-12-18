package Nebrija.ProyectoPrimerT;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Nebrija.ProyectoPrimerT.ConexionBD.ConexionMysql;
import Nebrija.ProyectoPrimerT.ConexionBD.NombreSesion;
import Nebrija.ProyectoPrimerT.Usuarios.Usuario;

public class AdministrarController implements Initializable{
	
	private ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	private ArrayList<Usuario> listaUsuariosBuscador = new ArrayList<Usuario>();
	
	public Usuario c = new Usuario();
	
	@FXML
    private Button actualizarbtn;

    @FXML
    private Button aniadirbtn;

    @FXML
    private Button nuevobtn;
    
    @FXML
    private Button eliminarbtn;
    
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
    public void nuevo() {
    	apellidos.setText("");
    	contrasenia.setText("");
    	correo.setText("");
    	nick.setText("");
    	nombre.setText("");
    	
		habilitado.setSelected(false);
		permisos.setSelected(false);

    	actualizarbtn.setDisable(true);
    	eliminarbtn.setDisable(true);
    }
    
    @FXML
    private void mostrarAlertCreacion(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Usuario");
        alert.setContentText("Usuario correctamente creado");
        alert.showAndWait();
    }
    @FXML
    private void mostrarAlertCreacionF(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Usuario");
        alert.setContentText("El usuario no se ha podido crear");
        alert.showAndWait();
    }
    @FXML
    private void mostrarAlertEliminacion(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Usuario");
        alert.setContentText("Usuario eliminado corrrectamente");
        alert.showAndWait();
    }
    @FXML
    private void mostrarAlertEliminacionF(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Usuario");
        alert.setContentText("El usuario no se ha podido eliminar");
        alert.showAndWait();
    }
    
    @FXML
    private void mostrarAlertActualizacion(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Usuario");
        alert.setContentText("Usuario actualizado correctamente");
        alert.showAndWait();
    }
    @FXML
    private void mostrarAlertActualizacionF(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Usuario");
        alert.setContentText("El usuario no se ha podido actualizar");
        alert.showAndWait();
    }
    
    @FXML
    private void actualizarUsuario(/*ActionEvent event*/) {
    	ConexionMysql conexion = new ConexionMysql();
    	boolean actualizado = false;
    	crearUsuario();
    	try {
			actualizado = conexion.actualizarUser(c, actualizado);
			if(actualizado) {
				mostrarAlertActualizacion(null);
			}else {
				mostrarAlertActualizacionF(null);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	actualizarTabla();
    }

    @FXML
    void aniadirUsuario(/*ActionEvent event*/) throws NoSuchAlgorithmException {
    	ConexionMysql conexion = new ConexionMysql();
    	boolean creado = false;
    	crearUsuario();
    	creado = conexion.crearUsuarioAdmin(c, creado);
    	if(creado) {
    		mostrarAlertCreacion(null);
    	}else {
    		mostrarAlertCreacionF(null);
    	}
    	actualizarTabla();
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
    	mostrarAlertEliminacion(null);
    	boolean eliminado = false;
    	ConexionMysql conexion = new ConexionMysql();
    	crearUsuario();
    	eliminado = conexion.eliminarUsuario(c, eliminado);
    	if(eliminado) {
    		mostrarAlertEliminacion(null);
    	}else {
    		mostrarAlertEliminacionF(null);
    	}
    	actualizarTabla();
    }

    @FXML
    void irWeb(/*ActionEvent event*/) {

    }
    
    public void crearUsuario() {
    	System.out.println(apellidos.getText());
		c.setApellidos(apellidos.getText());
		c.setContrasenia(contrasenia.getText());
		c.setCorreo(correo.getText());
		c.setNick(nick.getText());
		if(permisos.isSelected() == true) {			
			c.setPermisos("1");
		}else {
			c.setPermisos("0");
		}
		c.setNombre(nombre.getText());
    }
    
    public void actualizarTabla() {
    	listaUsuarios.clear();
    	ConexionMysql conexion = new ConexionMysql();
    	try {
			conexion.llenarArrayUsuario(listaUsuarios);
			conexion.llenarArrayUsuario(listaUsuariosBuscador);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	ObservableList<Usuario> listaUsuariosNueva = FXCollections.observableArrayList(listaUsuarios);
    	tablaUsuarios.setItems((ObservableList<Usuario>) listaUsuariosNueva);
    }
    
    public void gestionarEventos() {
    	tablaUsuarios.getSelectionModel().selectedItemProperty().addListener(
    			new ChangeListener<Usuario>() {
    				@Override
    				public void changed(ObservableValue<? extends Usuario> arg0, 
    						Usuario valorAnterior, Usuario valorSeleccionado) {
    					nuevo();
    					// TODO Auto-generated method stub
    					if(valorSeleccionado != null ) {
    						String idVS = String.valueOf(valorSeleccionado.getId());
    						String apellidosVS = String.valueOf(valorSeleccionado.getApellidos());
    						String contraseniaVS = String.valueOf(valorSeleccionado.getContrasenia());
    						String correoVS = String.valueOf(valorSeleccionado.getCorreo());
    						String habilitarVS = String.valueOf(valorSeleccionado.getHabilitar());
    						String nickVS = String.valueOf(valorSeleccionado.getNick());
    						String permisosVS = String.valueOf(valorSeleccionado.getPermisos());
    						String nombreVS = String.valueOf(valorSeleccionado.getNombre());
    						
    						c.setId(idVS);
    						c.setHabilitar(habilitarVS);
	
    						NombreSesion.contraseniaGuardar(contraseniaVS);;
    						
    						nombre.setText(nombreVS);
    						apellidos.setText(apellidosVS);
    						contrasenia.setText(contraseniaVS);
    						correo.setText(correoVS);
    						nick.setText(nickVS);
    						
    						if(permisosVS.equals("1")) {
    							permisos.setSelected(true);
    						}
    						
    						if(habilitarVS.equals("1")) {
    							habilitado.setSelected(true);
    						}
    						
    				    	actualizarbtn.setDisable(false);
    				    	eliminarbtn.setDisable(false);
    				    	    				    	
    					}
					}
				});
    	
    	nickUsuario.setText(NombreSesion.cogerNombreUsuario());
    	
    	actualizarbtn.setDisable(true);
    	eliminarbtn.setDisable(true);
    	habilitado.setDisable(true);
    	
    }
    
    public void inicializarTablaUsuarios() {
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
//    	TableColumn contraseniaCol = new TableColumn("contraseña");
    	TableColumn correoCol = new TableColumn("correo");
    	TableColumn permisosCol = new TableColumn("permisos");
    	TableColumn habilitarCol = new TableColumn("habilitado");
    	
    	tablaUsuarios.getColumns().addAll(idCol, nombreCol, apellidosCol, nickCol, /*contraseniaCol,*/ correoCol, permisosCol, habilitarCol);
    	
    	idCol.setCellValueFactory(new PropertyValueFactory<Usuario, String>("id"));
    	nombreCol.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
    	apellidosCol.setCellValueFactory(new PropertyValueFactory<Usuario, String>("apellidos"));
    	nickCol.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nick"));
    	//contraseniaCol.setCellValueFactory(new PropertyValueFactory<Usuario, String>("contrasenia"));
    	correoCol.setCellValueFactory(new PropertyValueFactory<Usuario, String>("correo"));
    	permisosCol.setCellValueFactory(new PropertyValueFactory<Usuario, String>("permisos"));
    	habilitarCol.setCellValueFactory(new PropertyValueFactory<Usuario, String>("habilitar"));

    	ObservableList<Usuario> listaUsuariosNueva = FXCollections.observableArrayList(listaUsuarios);
    	tablaUsuarios.setItems((ObservableList<Usuario>) listaUsuariosNueva);
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		inicializarTablaUsuarios();
		gestionarEventos();

	}

}
