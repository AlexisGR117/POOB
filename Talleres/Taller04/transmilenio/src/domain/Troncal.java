package domain;

import java.io.*;
import java.util.*;

/**
 * Troncal de un sistema de transmilenio.
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (27/11/2022)
 */
public class Troncal {

	private char nombre;
	private int velocidadPromedio;
	private List<Tramo> tramos;
	
	/**
	 * Constructor para objetos de clase Troncal.
	 * @param nombre Nombre de la troncal.
	 */
	public Troncal(char nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Guarda una troncal en un archivo.
	 * @param file Archivo en el que se va a guardar la troncal.
	 * @throws IOException, si ocurre una excepción en la salida.
	 */
	public void salvar(File file) throws IOException{
		FileOutputStream fileOutput = new FileOutputStream(file);
		ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
		objectOutput.writeObject(this);
		objectOutput.close();
	}
	
	/**
	 * Da la velocidad promedio de la troncal.
	 * @return Número entero que representa la velocidad promedio de la troncal en metros/minuto.
	 */
	public int velocidadPromedio(){
		return velocidadPromedio;
	}
}
