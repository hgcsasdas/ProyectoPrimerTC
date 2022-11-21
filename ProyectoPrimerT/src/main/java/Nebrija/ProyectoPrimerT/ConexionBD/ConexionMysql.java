package Nebrija.ProyectoPrimerT.ConexionBD;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Nebrija.ProyectoPrimerT.Usuarios.Usuario;

public class ConexionMysql {

	private Connection conexion;
	private String usuario = "root";
	private String password = "";
	private String servidor = "localhost";
	private String puerto = "3306";
	private String nombreBD = "proyectocarceles";
	
	private String url = "jdbc:mysql://" + servidor + ":" + puerto + "/" + nombreBD + "?serverTimezone=UTC";

	private String driver = "com.mysql.cj.jdbc.Driver";
	
	public ConexionMysql() {
		
		try {
			Class.forName(driver);
			
			conexion = DriverManager.getConnection(url,usuario,password);
			
			if(conexion != null) {
				System.out.println("Conexion realizada correctamente!!!");
			}
			
		} catch (Exception e) {
			System.err.println("Error de conexion");	
			System.err.println("El error es : " + e.getMessage());
			System.err.println("Deta11e del error: ");
			e.printStackTrace() ;
		}
		
	}
	
	public void crearUsuario(Usuario user) throws SQLException {
		 PreparedStatement stmt = conexion.prepareStatement("insert into usuarios(nombre,apellidos,nick,contrasenia,correo) values(?,?,?,?,?)");// parametización
		 
		 stmt.setString(1, user.getNombre());
		 stmt.setString(2, user.getApellidos());
		 stmt.setString(3, user.getNick());
		 stmt.setString(4, user.getContrasenia());
		 stmt.setString(5, user.getCorreo());
		 
		 stmt.executeUpdate();
		 System.out.println("Creaste al user");

	}
	
	public void loginUser() {
		
	}
	
	
}
