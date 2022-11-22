package Nebrija.ProyectoPrimerT.ConexionBD;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	public String createSHAHash(String input)throws NoSuchAlgorithmException {

		String hashtext = null;
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] messageDigest =
		md.digest(input.getBytes(StandardCharsets.UTF_8));

		hashtext = convertToHex(messageDigest);
		return hashtext;
	}
	private String convertToHex(final byte[] messageDigest) {
		BigInteger bigint = new BigInteger(1, messageDigest);
		String hexText = bigint.toString(16);
		while (hexText.length() < 32) {
			hexText = "0".concat(hexText);
		}
		return hexText;
	}
	   
	
	public void crearUsuario(Usuario user) throws SQLException, NoSuchAlgorithmException {
		 PreparedStatement stmt = conexion.prepareStatement("insert into usuarios(nombre,apellidos,nick,contrasenia,correo) values(?,?,?,?,?)");// parametización
		 
		 stmt.setString(1, user.getNombre());
		 stmt.setString(2, user.getApellidos());
		 stmt.setString(3, user.getNick());
		 stmt.setString(4, createSHAHash(user.getContrasenia()));
		 stmt.setString(5, user.getCorreo());
		 
		 stmt.executeUpdate();
		 stmt.close();
		 System.out.println("Creaste al user");

	}
	
	public boolean loginUser(Usuario user) throws SQLException, NoSuchAlgorithmException {
		boolean condicion = false;
		System.out.println(user.getDatoInsertado());
		String stmt = "SELECT * FROM usuarios where contrasenia = "+ "'" + createSHAHash(user.getContrasenia()) + "'" +" and (nick = "+ "'" + user.getDatoInsertado() + "'" +" or correo = "+ "'" + user.getDatoInsertado() + "'" +") ";
		 try{
			 PreparedStatement ps = conexion.prepareStatement(stmt);   
			 ResultSet rs = ps.executeQuery();  
	            if(rs.next()){
	   			 	condicion = true;
	            }
		 } catch(Exception e){
			 System.err.print("Ha ocurrido un error: "+ e.getMessage());
	     } 
        
		return condicion;
	}
	
	public boolean VerPermisos(Usuario user) {
		boolean condicion = false;
		String stmt = "Select permisos from usuarios where nick = "+ "'" + user.getDatoInsertado() + "'";
		 try{
			 PreparedStatement ps = conexion.prepareStatement(stmt);   
			 ResultSet rs = ps.executeQuery();  
	            if(rs.next()){
	            	int permisos = rs.getInt("permisos");
	            	if(permisos == 1) {
		            	condicion = true;
	            	}
	            }else {
	            	System.out.println("sad");

	            }
		 } catch(Exception e){
			 System.err.print("Ha ocurrido un error: "+ e.getMessage());
	     } 
		return condicion;
	}
	
	public boolean VerHabilitacion(Usuario user) {
		boolean condicion = false;
		String stmt = "Select habilitado from usuarios where nick = "+ "'" + user.getDatoInsertado() + "'";
		 try{
			 PreparedStatement ps = conexion.prepareStatement(stmt);   
			 ResultSet rs = ps.executeQuery();  
	            if(rs.next()){
	            	int permisos = rs.getInt("habilitado");
	            	if(permisos == 1) {
		            	condicion = true;
	            	}
	            }else {
	            	System.out.println("sad");

	            }
		 } catch(Exception e){
			 System.err.print("Ha ocurrido un error: "+ e.getMessage());
	     } 
		return condicion;
	}
	
	public ArrayList<Usuario> llenarArrayUsuario(ArrayList<Usuario> listaUsuarios) throws SQLException{
        Statement st = conexion.createStatement();
        ResultSet srs = st.executeQuery("SELECT * FROM usuarios"); 
        while (srs.next()) {
        	Usuario user = new Usuario();
        	user.setNombre(srs.getString("nombre"));
        	user.setApellidos(srs.getString("apellidos"));
        	user.setNick(srs.getString("nick"));
        	user.setCorreo(srs.getString("correo"));
        	user.setContrasenia(srs.getString("contrasenia"));
        	user.setId(srs.getString("idUsuarios"));
        	user.setPermisos(srs.getString("permisos"));
        	user.setHabilitar(srs.getString("habilitado"));
        	listaUsuarios.add(user);

        }
		return listaUsuarios;
	}
}
