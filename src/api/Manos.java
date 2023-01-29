package api;

public enum Manos {
	FLOR_IMPERIAL("Royal flush"),ESCALERA_COLOR("Straignt flush"),POKER("Four of a kind"),FULL("Full house"),COLOR("Flush"),ESCALERA("Straight"),TERCIA("Treee of a kind"),DOS_PAR("Two pair"),PAR("Pair"),ALTA("High card");

	private final String nombreMano;
	
	private Manos(String nombre) {
		nombreMano=nombre;
	}
	
	public String toString () {
		return nombreMano;
	}
}
