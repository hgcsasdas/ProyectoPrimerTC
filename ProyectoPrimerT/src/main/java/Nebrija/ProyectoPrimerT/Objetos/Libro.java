package Nebrija.ProyectoPrimerT.Objetos;

public class Libro {

	private String nombre;
	private String ruta;
		
	public Libro() {
		super();
	}

	public Libro(String nombre, String ruta) {
		super();
		this.nombre = nombre;
		this.ruta = ruta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
}
