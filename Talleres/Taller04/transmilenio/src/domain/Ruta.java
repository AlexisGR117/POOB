package domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Ruta de un sistema de transmilenio.
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (27/11/2022)
 */
public class Ruta {

	private String nombre;
	private List<Estacion> estaciones = new LinkedList<Estacion>();
	
	/**
	 * Constructor para objetos de clase Ruta.
	 * @param nombre Nombre de la ruta.
	 */
	public Ruta(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Agrega una estación al final de la lista de estaciones-.
	 * @param estacion Estación que se quiere agregar.
	 */
	public void agregarEsacion(Estacion estacion) {
		estaciones.add(estacion);
	}
	
	/**
	 * Da la lista de estaciones que conforman la ruta.
	 * @return Lista con las estaciones.
	 */
	public List<Estacion> estaciones(){
		return estaciones;
	}
	
	/**
	 * Da el nombre de la ruta.
	 * @return Nombre de la ruta.
	 */
	public String nombre() {
		return nombre;
	}
	
	/**
	 * Importa una ruta con su nombre y sus estaciones.
	 * @param file Archivo en el que se va a importar la ruta.
	 * @return La ruta resultante al importar.
	 * @throws IOException,si hay un error con la entrada.
	 */
	public static Ruta importar(File file) throws IOException {
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		Ruta ruta = null;
		String line = reader.readLine();
		while (line != null) {
			String l = line.trim();
			if (ruta == null) ruta = new Ruta(l);
			else ruta.agregarEsacion(new Estacion(l));
			line = reader.readLine();
		}
		reader.close();
		return ruta;
	}
}
