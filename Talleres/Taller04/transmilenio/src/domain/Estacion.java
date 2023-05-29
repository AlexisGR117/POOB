package domain;

/**
 * Estación de un sistema de transmilenio.
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (27/11/2022)
 */
public class Estacion {
	
	private String nombre;
	private String nivelOcupacion;
	private int tiempoEspera;
	private Tramo tramo;
	
	/**
	 * Constructor para objetos de clase Estacion.
	 * @param nombre Nombre de la estación.
	 */
	public Estacion(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Da el tiempo de espera de la estación.
	 * @return Un número entero que representa el tiempo de espera en minutos.
	 */
	public int tiempoEspera() {
		return tiempoEspera;
	}
	
	/**
	 * Asigna un timepo de espera a la estación.
	 * @param tiempo Tiempo de espera.
	 */
	public void establecerTiempoEspera(int tiempo) {
		tiempoEspera = tiempo;
	}	
	
	/**
	 * Establece el tramo en la que se encuentra la estación.
	 * @param tramo
	 */
	public void establecerTramo(Tramo tramo) {
		this.tramo = tramo;
	}	
	
	/**
	 * Devuelve el nombre de la estación.
	 * @return Nombre de la estación.
	 */
	public String nombre() {
		return nombre;
	}
	
	/**
	 * Da el tramo en eñ que está la estación.
	 * @return Tramo de la estación.
	 */
	public Tramo tramo() {
		return tramo;
	}	
}
