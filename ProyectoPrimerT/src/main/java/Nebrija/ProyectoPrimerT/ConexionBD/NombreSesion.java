package Nebrija.ProyectoPrimerT.ConexionBD;

import Nebrija.ProyectoPrimerT.Usuarios.Usuario;

public class NombreSesion {

	static Usuario user = new Usuario();
	
	static Usuario usuarioSusDatosContrasenia = new Usuario();
	
	public static void aniadirNickSesion(Usuario usuario) {
		user.setDatoInsertado(usuario.getDatoInsertado());
	}
	
	public static String cogerNombreUsuario() {
		return user.getDatoInsertado();
	}
	public static void contraseniaGuardar(String contraseniaVS) {
		usuarioSusDatosContrasenia.setContrasenia(contraseniaVS);
	}
	public static String cogerContraseniaGuardada() {
		return usuarioSusDatosContrasenia.getContrasenia();
	}
}
