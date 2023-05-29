package domain;

/**
 * Tipo de comodín que varía su compórtamiento dependiendo de la ficha.
 * @author Jefer Gonzalez
 * @version 1.0 (12/12/2022)
 */
public class Infinity extends Comodin{

	/**
	 * Constructor para objetos de clase Infinity.
	 * @param casilla Casilla en la que va estar el comodín.
	 */
	public Infinity(Casilla casilla) {
		super(casilla);
	}

	@Override
	public void activar() throws DAPOOSException {
		casilla.ficha().infinity();
		casilla.tablero().dapoos().cambiarTurno();
	}

	@Override
	public String toString() {
		return "Infinity";
	}
}
