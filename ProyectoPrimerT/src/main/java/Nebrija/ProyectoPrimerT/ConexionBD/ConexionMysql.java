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
	
	public String createSHAHash(String input) {

		String hashtext = "";
		try {
			
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] messageDigest =
		md.digest(input.getBytes(StandardCharsets.UTF_8));

		hashtext = convertToHex(messageDigest);
	
		}catch (Exception e) {
			// TODO: handle exception
		}
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
	   
	
	public boolean crearUsuario(Usuario user, boolean creado) {
		 PreparedStatement stmt;
		try {
			stmt = conexion.prepareStatement("insert into usuarios(nombre,apellidos,nick,contrasenia,correo) values(?,?,?,?,?)");
			stmt.setString(1, user.getNombre());
			stmt.setString(2, user.getApellidos());
			stmt.setString(3, user.getNick());
			String pass = createSHAHash(user.getContrasenia());
			if(pass != null) {

			}else {
			}
			stmt.setString(4, pass);

			stmt.setString(5, user.getCorreo());

			stmt.executeUpdate();
			stmt.close();
			creado = true;
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		 return creado; 
	}
	
	public boolean crearUsuarioAdmin(Usuario user, boolean creado) throws NoSuchAlgorithmException {
		 PreparedStatement stmt;
		try {
			stmt = conexion.prepareStatement("insert into usuarios(nombre,apellidos,nick,contrasenia,correo, permisos) values(?,?,?,?,?,?)");
			stmt.setString(1, user.getNombre());
			stmt.setString(2, user.getApellidos());
			stmt.setString(3, user.getNick());
		 	stmt.setString(4, createSHAHash(user.getContrasenia()));
		 	stmt.setString(5, user.getCorreo());
		 	stmt.setString(6, user.getPermisos());

		 	System.out.println(stmt.toString());
		 	stmt.executeUpdate();
		 	stmt.close();
		 	System.out.println("Creaste al user");
		 	creado = true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 return creado;

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
        ResultSet srs = st.executeQuery("SELECT * FROM usuarios where habilitado = 1"); 
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
	
	public boolean actualizarUser(Usuario user, boolean actualizado) throws NoSuchAlgorithmException {

		String con = NombreSesion.cogerContraseniaGuardada();
		String stmt = "";
		if(con.equals(user.getContrasenia())) {
			stmt = "UPDATE `usuarios` SET `nombre`='" + user.getNombre() +"',`apellidos`='"  + user.getApellidos() + "',`nick`='" +user.getNick() + "',`contrasenia`='" + user.getContrasenia() +"',`correo` ='" + user.getCorreo() +"',`permisos`='"+ user.getPermisos() +"' WHERE idUsuarios = " + user.getId();
		}else {
			stmt = "UPDATE `usuarios` SET `nombre`='" + user.getNombre() +"',`apellidos`='"  + user.getApellidos() + "',`nick`='" +user.getNick() + "',`contrasenia`='" + createSHAHash(user.getContrasenia()) +"',`correo` ='" + user.getCorreo() +"',`permisos`='"+ user.getPermisos() +"' WHERE idUsuarios = " + user.getId();
		}
		System.out.println(stmt);
		PreparedStatement ps;
		try {
			ps = conexion.prepareStatement(stmt);
			ps.executeUpdate();
			ps.close();
			actualizado = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		
		
		System.out.println("Actualizaste al user");
		
		return actualizado;

	}
	public boolean eliminarUsuario(Usuario user, boolean eliminado) {
		String stmt = "UPDATE `usuarios` SET habilitado = 0 where idUsuarios = " + user.getId() ;
		PreparedStatement ps;
		try {
			ps = conexion.prepareStatement(stmt);
			ps.executeUpdate();
			ps.close();
			eliminado = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		
		
		System.out.println("Se elimin?? al User");
		return eliminado;
	}
	
}
