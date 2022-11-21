package Nebrija.ProyectoPrimerT.Usuarios;

public class Usuario {

	private String nombre;
	private String apellidos;
	private String nick;
	private String correo;
	private String contrasenia;
	
	public Usuario(String nombre, String apellidos, String nick, String correo, String contrasenia) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nick = nick;
		this.correo = correo;
		this.contrasenia = contrasenia;
	}
	
	public Usuario() {
		super();
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	
}
