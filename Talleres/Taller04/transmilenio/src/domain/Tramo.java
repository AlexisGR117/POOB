package domain;

import java.util.*;

/**
 * Tramo de un sistema de transmilenio.
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (27/11/2022)
 */
public class Tramo {
	
	private Troncal troncal;
	private LinkedHashMap<Estacion, Integer> estaciones = new LinkedHashMap<Estacion, Integer>();
	
	/**
	 * Constructor para objetos de clase Tramo.
	 * @param troncal Troncal en la que se encuentra el tramo.
	 */
	public Tramo(Troncal troncal) {
		this.troncal = troncal;
	}
	
	/**
	 * Da las estaciones del tramo.
	 * @return Estaciones del tramo.
	 */
	public LinkedHashMap<Estacion, Integer> estaciones(){
		return estaciones;
	}
	
	/**
	 * Da la troncal del tramo.
	 * @return Troncal del tramo.
	 */
	public Troncal troncal(){
		return troncal;
	}
}
