package Nebrija.ProyectoPrimerT.UsuariosDAO;

public class Usuario {

	private String nombre;
	private String apellidos;
	private String correo;
	private String contrasenia;
	private boolean permisos;
	
	public Usuario() {
		super();
	}

	public Usuario(String nombre, String apellidos, String correo, String contrasenia, boolean permisos) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.contrasenia = contrasenia;
		this.permisos = permisos;
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

	public boolean isPermisos() {
		return permisos;
	}

	public void setPermisos(boolean permisos) {
		this.permisos = permisos;
	}
	
	
}
