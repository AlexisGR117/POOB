package domain;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

/**
 * Tipo de ficha después de ser capturada, puede volver al espacio en que fue capturada. 
 * @author Jefer Gonzalez
 * @version 1.0 (08/12/2022)
 */
public class Zombie extends Nueva {
	
	private boolean enterrada;
	private int turnos;
	
	/**
	 * Constructor para objetos de clase Zombie.
	 * @param jugador Jugador al cual pertenece la ficha.
	 */
	public Zombie(Jugador jugador) {
		super(jugador);
		enterrada = false;
	}
	
	@Override
	public ArrayList<int[]> movimientosPosibles() {
		ArrayList<int[]> m = new ArrayList<int[]>();
		if (!enterrada) m = super.movimientosPosibles();
		return m;
	}
	
	@Override
	public ArrayList<int[]> saltosPosibles() {
		ArrayList<int[]> m = new ArrayList<int[]>();
		if (!enterrada) m = super.saltosPosibles();
		return m;
	}	
	
	@Override
	public void capturar() {
		if (captura == 1) super.capturar();
		else {
			casilla.quitarFicha();
			casilla.tablero().enterrarZombie(this);
			enterrada = true;
			captura += 1;
		}
	}
	
	/**
	 * Desentierra la ficha Zombie, lo que significa que vuelve al tablero.
	 * @return True si se pudo desenterrar, de lo contrario, false.
	 * @throws DAPOOSException
	 */
	public boolean desenterrar() throws DAPOOSException {
		turnos += 1;
		if (turnos >= 4 && casilla.ficha() == null) {
			casilla.colocarFicha(this);
			enterrada = false;
			return true;
		} else return false;
	}
	
	@Override
	public String toString() {
		return "Zombie"+jugador.color();
	}

	@Override
	public void infinity() throws DAPOOSException{
		try {
		Class<?> cls = Class.forName("domain.Peon");
		Constructor<?>[] cons = cls.getConstructors();
		jugador.eliminarFicha(this);
		casilla.colocarFicha((Ficha)cons[0].newInstance(jugador));	
		} catch (ReflectiveOperationException e) {
			throw new DAPOOSException(DAPOOSException.INVALID_TOKEN_TYPE);
		}
	}
}
