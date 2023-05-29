package domain;

import java.util.ArrayList;

/**
 * Tipo de ficha que se puede mover de a una casilla hacia adelante y hacia atras.
 * @author Jefer Gonzalez
 * @version 1.0 (08/12/2022)
 */
public abstract class Nueva extends Ficha {
	
	protected int captura;
	
	/**
	 * Constructor para objetos de clase Nueva.
	 * @param jugador Jugador al cual pertenece la ficha.
	 */
	public Nueva(Jugador jugador) {
		super(jugador);
		captura = 0;
	}
	
	@Override
	public ArrayList<int[]> movimientosPosibles() {
		ArrayList<int[]> m = new ArrayList<int[]>();
		if (casilla.salir()) {
			int fila = casilla.fila(), columna = casilla.columna();
			Tablero tablero = casilla.tablero();
			for (int df=-1; df<2;df++) for (int dc=-1; dc<2;dc++) {
	            if (df != 0 && dc != 0 && tablero.dentro(fila+df, columna+dc) && tablero.ficha(fila+df, columna+dc) == null) {
	        		int[] p = {fila+df, columna+dc};
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
			int fila = casilla.fila(), columna = casilla.columna();
			Tablero tablero = casilla.tablero();
			for (int df=-1; df<2;df++) for (int dc=-1; dc<2;dc++) {
	            if (df != 0 && dc != 0 && tablero.dentro(fila+df, columna+dc) && tablero.ficha(fila+df, columna+dc) != null && tablero.ficha(fila+df, columna+dc).lado() != jugador.lado() &&
					tablero.dentro(fila+df*2, columna+dc*2) && tablero.ficha(fila+df*2, columna+dc*2) == null) {
	            	int[] p = {fila+df*2, columna+dc*2};
	            	m.add(p);
	            }
	        }
		}
		return m;
	}	
	
	@Override
	public abstract String toString();
}
