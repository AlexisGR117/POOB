package domain;
import java.util.*;

/**
 * Tipo de ficha que solo se puede mover de a una casilla y no se puede devolver.
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (19/11/2022)
 */
public class Peon extends Ficha {
	
	/**
	 * Constructor para objetos de clase Peon.
	 * @param jugador Jugador al cual pertenece la ficha.
	 */
	public Peon(Jugador jugador) {
		super(jugador);
	}
	
	@Override
	public ArrayList<int[]> movimientosPosibles() {
		ArrayList<int[]> m = new ArrayList<int[]>();
		if (casilla.salir()) {
			int fila = casilla.fila(), columna = casilla.columna(), a = 1;
			if (jugador.lado() == 's') a = -1;
			Tablero tablero = casilla.tablero();
			for (int dc=-1; dc<2;dc++) {
	            if (dc!=0 && tablero.dentro(fila+a, columna+dc) && tablero.ficha(fila+a, columna+dc) == null) {
	        		int[] p = {fila+a, columna+dc};
	        		m.add(p);
	            }
			}
			m.addAll(saltosPosibles());
		}
		return m;
	}
	
	@Override
	public ArrayList<int[]> saltosPosibles() {
		ArrayList<int[]> m = new ArrayList<int[]>();
		if (casilla.salir()) {
			int fila = casilla.fila(), columna = casilla.columna(), a = 1;
			if (jugador.lado() == 's') a = -1;
			int b = 2 * a;
			Tablero tablero = casilla.tablero();
			for (int dc=-1; dc<2; dc++) {
	            if (dc!=0 && tablero.dentro(fila+a, columna+dc) && tablero.ficha(fila+a, columna+dc) != null && tablero.ficha(fila+a, columna+dc).lado() != jugador.lado() &&
					tablero.dentro(fila+b, columna+dc*2) && tablero.ficha(fila+b, columna+dc*2) == null) {
	            	int[] p = {fila+b, columna+dc*2};
	            	m.add(p);
	            }
	        }
		}
		return m;
	}	
		
	@Override
	public String toString() {
		return "Peon"+jugador.color();
	}

	@Override
	public void infinity(String tipo) {
		
	}

	@Override
	public void infinity() throws DAPOOSException {
		// TODO Auto-generated method stub
		
	}
}
