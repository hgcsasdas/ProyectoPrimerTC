package Nebrija.ProyectoPrimerT.Usuarios;

public class Usuario {

	private String nombre;
	private String apellidos;
	private String nick;
	private String correo;
	private String contrasenia;
	
	private String datoInsertado;
	private String id;
	private String permisos;
	private String habilitar;

	

	public Usuario(String nombre, String apellidos, String nick, String correo, String contrasenia, String id, String permisos, String habilitar) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nick = nick;
		this.correo = correo;
		this.contrasenia = contrasenia;
		this.id = id;
		this.permisos = permisos;
		this.habilitar = habilitar;
	}

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
	
	public Usuario(String datoInsertado, String contrasenia) {
		super();
		this.datoInsertado = datoInsertado;
		this.contrasenia = contrasenia;
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
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPermisos() {
		return permisos;
	}
	
	public void setPermisos(String permisos) {
		this.permisos = permisos;
	}
	
	public String getHabilitar() {
		return habilitar;
	}
	
	public void setHabilitar(String habilitar) {
		this.habilitar = habilitar;
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

	public String getDatoInsertado() {
		return datoInsertado;
	}

	public void setDatoInsertado(String datoInsertado) {
		this.datoInsertado = datoInsertado;
	}
	
	
}
