package api;

public enum Figura {
	CORAZON("Corazón"),TREBOL("Trébol"),DIAMANTE("Diamante"),ESPADAS("Espadas");

	private final String nombreFigura;
	
	private Figura(String nombre) {
		nombreFigura=nombre;
	}
	
	public String toString () {
		return nombreFigura;
	}
}
