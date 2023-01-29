package api;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Carta {

	private NumCarta numero;
	private Figura figura;
	
	public Carta(NumCarta numero, Figura figura) {
		setNumero(numero);
		setFigura(figura);
	}
	
	protected void finalize() {
		System.out.println("se destruye una carta");
	}
	
	public void setNumero(NumCarta numero) {
		this.numero = numero;
	}

	public void setFigura(Figura figura) {
		this.figura = figura;
	}

	public NumCarta getNumero()
	{
		return numero;
	}

	public int intNumBlack()
	{
		switch (numero) {
			case AS: return 1; 
			case DOS: return 2; 
			case TRES: return 3; 
			case CUATRO: return 4; 
			case CINCO: return 5; 
			case SEIS: return 6; 
			case SIETE: return 7; 
			case OCHO: return 8; 
			case NUEVE: return 9; 
			case DIEZ: return 10; 
			case JOTO: return 10; 
			case REINA: return 10; 
			case REY: return 10; 
		
			default: return 0;
		}
	}
	
	public int intNumPoker()
	{
		switch (numero) {
			case DOS: return 1; 
			case TRES: return 2; 
			case CUATRO: return 3; 
			case CINCO: return 4; 
			case SEIS: return 5; 
			case SIETE: return 6; 
			case OCHO: return 7; 
			case NUEVE: return 8; 
			case DIEZ: return 9; 
			case JOTO: return 10; 
			case REINA: return 11; 
			case REY: return 12;
			case AS: return 13; 
		
			default: return 0;
		}
	}

	public Figura getFig()
	{
		return figura;
	}
	
	public Image getImage() {
		switch(figura) {
		case CORAZON:
			switch(numero) {
			case AS: return new ImageIcon(this.getClass().getResource("/asCorazon.jpg")).getImage();
			case DOS: return new ImageIcon(this.getClass().getResource("/dosCorazon.jpg")).getImage();
			case TRES: return new ImageIcon(this.getClass().getResource("/tresCorazon.jpg")).getImage();
			case CUATRO: return new ImageIcon(this.getClass().getResource("/cuatroCorazon.jpg")).getImage();
			case CINCO: return new ImageIcon(this.getClass().getResource("/cincoCorazon.jpg")).getImage();
			case SEIS: return new ImageIcon(this.getClass().getResource("/seisCorazon.jpg")).getImage();
			case SIETE: return new ImageIcon(this.getClass().getResource("/sieteCorazon.jpg")).getImage();
			case OCHO: return new ImageIcon(this.getClass().getResource("/ochoCorazon.jpg")).getImage();
			case NUEVE: return new ImageIcon(this.getClass().getResource("/nueveCorazon.jpg")).getImage();
			case DIEZ: return new ImageIcon(this.getClass().getResource("/diezCorazon.jpg")).getImage();
			case JOTO: return new ImageIcon(this.getClass().getResource("/jotoCorazon.jpg")).getImage();
			case REINA: return new ImageIcon(this.getClass().getResource("/reinaCorazon.jpg")).getImage();
			case REY: return new ImageIcon(this.getClass().getResource("/reyCorazon.jpg")).getImage();
			default: return null;
			}
		case DIAMANTE:
			switch(numero) {
			case AS: return new ImageIcon(this.getClass().getResource("/asDiamante.jpg")).getImage();
			case DOS: return new ImageIcon(this.getClass().getResource("/dosDiamante.jpg")).getImage();
			case TRES: return new ImageIcon(this.getClass().getResource("/tresDiamante.jpg")).getImage();
			case CUATRO: return new ImageIcon(this.getClass().getResource("/cuatroDiamante.jpg")).getImage();
			case CINCO: return new ImageIcon(this.getClass().getResource("/cincoDiamante.jpg")).getImage();
			case SEIS: return new ImageIcon(this.getClass().getResource("/seisDiamante.jpg")).getImage();
			case SIETE: return new ImageIcon(this.getClass().getResource("/sieteDiamante.jpg")).getImage();
			case OCHO: return new ImageIcon(this.getClass().getResource("/ochoDiamante.jpg")).getImage();
			case NUEVE: return new ImageIcon(this.getClass().getResource("/nueveDiamante.jpg")).getImage();
			case DIEZ: return new ImageIcon(this.getClass().getResource("/diezDiamante.jpg")).getImage();
			case JOTO: return new ImageIcon(this.getClass().getResource("/jotoDiamante.jpg")).getImage();
			case REINA: return new ImageIcon(this.getClass().getResource("/reinaDiamante.jpg")).getImage();
			case REY: return new ImageIcon(this.getClass().getResource("/reyDiamante.jpg")).getImage();
			default: return null;
			}
		case ESPADAS:
			switch(numero) {
			case AS: return new ImageIcon(this.getClass().getResource("/asEspada.jpg")).getImage();
			case DOS: return new ImageIcon(this.getClass().getResource("/dosEspada.jpg")).getImage();
			case TRES: return new ImageIcon(this.getClass().getResource("/tresEspada.jpg")).getImage();
			case CUATRO: return new ImageIcon(this.getClass().getResource("/cuatroEspada.jpg")).getImage();
			case CINCO: return new ImageIcon(this.getClass().getResource("/cincoEspada.jpg")).getImage();
			case SEIS: return new ImageIcon(this.getClass().getResource("/seisEspada.jpg")).getImage();
			case SIETE: return new ImageIcon(this.getClass().getResource("/sieteEspada.jpg")).getImage();
			case OCHO: return new ImageIcon(this.getClass().getResource("/ochoEspada.jpg")).getImage();
			case NUEVE: return new ImageIcon(this.getClass().getResource("/nueveEspada.jpg")).getImage();
			case DIEZ: return new ImageIcon(this.getClass().getResource("/diezEspada.jpg")).getImage();
			case JOTO: return new ImageIcon(this.getClass().getResource("/jotoEspada.jpg")).getImage();
			case REINA: return new ImageIcon(this.getClass().getResource("/reinaEspada.jpg")).getImage();
			case REY: return new ImageIcon(this.getClass().getResource("/reyEspada.jpg")).getImage();
			default: return null;
			}
		case TREBOL:
			switch(numero) {
			case AS: return new ImageIcon(this.getClass().getResource("/asTrebol.jpg")).getImage();
			case DOS: return new ImageIcon(this.getClass().getResource("/dosTrebol.jpg")).getImage();
			case TRES: return new ImageIcon(this.getClass().getResource("/tresTrebol.jpg")).getImage();
			case CUATRO: return new ImageIcon(this.getClass().getResource("/cuatroTrebol.jpg")).getImage();
			case CINCO: return new ImageIcon(this.getClass().getResource("/cincoTrebol.jpg")).getImage();
			case SEIS: return new ImageIcon(this.getClass().getResource("/seisTrebol.jpg")).getImage();
			case SIETE: return new ImageIcon(this.getClass().getResource("/sieteTrebol.jpg")).getImage();
			case OCHO: return new ImageIcon(this.getClass().getResource("/ochoTrebol.jpg")).getImage();
			case NUEVE: return new ImageIcon(this.getClass().getResource("/nueveTrebol.jpg")).getImage();
			case DIEZ: return new ImageIcon(this.getClass().getResource("/diezTrebol.jpg")).getImage();
			case JOTO: return new ImageIcon(this.getClass().getResource("/jotoTrebol.jpg")).getImage();
			case REINA: return new ImageIcon(this.getClass().getResource("/reinaTrebol.jpg")).getImage();
			case REY: return new ImageIcon(this.getClass().getResource("/reyTrebol.jpg")).getImage();
			default: return null;
			}
		default: return null;
		}
	}
	
	public String toString() {
		return String.format("%s de %s",numero,figura);
	}
	
	public boolean equals(Object obj) {
		if (!(obj instanceof Carta)) return false;
		Carta c = (Carta) obj;
		return figura == c.figura && numero == c.numero;
	}
	
}
