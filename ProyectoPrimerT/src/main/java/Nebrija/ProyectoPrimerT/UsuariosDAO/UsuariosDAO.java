package Nebrija.ProyectoPrimerT.UsuariosDAO;

public interface UsuariosDAO {
	public Usuario grabar(Usuario usuario);
	public Usuario leer(Usuario usuario);
	public Usuario Actualizar(Usuario usuario);
	public Usuario Delete(Usuario usuario);
}
