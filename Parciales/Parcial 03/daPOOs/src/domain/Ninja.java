package domain;

/**
 * Tipo de ficha que es invulnerable la primera vez que se trata de capturar. Se mueve de a una casilla, se puede devolver.
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (27/11/2022)
 */
public class Ninja extends Nueva {
	/**
	 * Constructor para objetos de clase Ninja.
	 * @param jugador Jugador al cual pertenece la ficha.
	 */
	public Ninja(Jugador jugador) {
		super(jugador);
	}
	
	@Override
	public void capturar() {
		if (captura == 1) super.capturar();
		else captura += 1;
	}
	
	@Override
	public String toString() {
		return "Ninja"+jugador.color();
	}

	@Override
	public void infinity() {
		captura -= 1;
	}
}