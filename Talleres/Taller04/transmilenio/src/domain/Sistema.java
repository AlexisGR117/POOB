package domain;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * Sistema de transmilenio.
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (27/11/2022)
 */
public class Sistema {
	
	private HashMap<String, Estacion> estaciones = new HashMap<String, Estacion>();
	private TreeMap<String, Ruta> rutas = new TreeMap<String, Ruta>();
	private ArrayList<Troncal> trocales = new ArrayList<Troncal>();
	
	/**
	 * Constructor para objetos de clase Sistema.
	 */
	public Sistema() {
		Estacion estacion1 = new Estacion("Portal Norte");
		estacion1.establecerTiempoEspera(5);
		estaciones.put(estacion1.nombre(), estacion1);
		Estacion estacion2 = new Estacion("C.C. Santafé");
		estacion2.establecerTiempoEspera(7);		
		estaciones.put(estacion2.nombre(), estacion2);
		Estacion estacion3 = new Estacion("Toberín");
		estacion3.establecerTiempoEspera(2);		
		estaciones.put(estacion3.nombre(), estacion3);
		Estacion estacion4 = new Estacion("Cardio infantil");
		estacion4.establecerTiempoEspera(7);		
		estaciones.put(estacion4.nombre(), estacion4);
		Ruta r1 = new Ruta("R1");
		r1.agregarEsacion(estacion1);
		r1.agregarEsacion(estacion2);
		rutas.put(r1.nombre(), r1);
		Ruta r2 = new Ruta("R2");
		r2.agregarEsacion(estacion2);
		r2.agregarEsacion(estacion4);
		rutas.put(r2.nombre(), r2);
		Ruta r3 = new Ruta("R3");
		r3.agregarEsacion(estacion1);
		r3.agregarEsacion(estacion4);
		rutas.put(r3.nombre(), r3);
		Ruta r4 = new Ruta("R4");
		r4.agregarEsacion(estacion1);
		r4.agregarEsacion(estacion2);
		r4.agregarEsacion(estacion3);
		r4.agregarEsacion(estacion4);
		rutas.put(r4.nombre(), r4);
		Ruta q1 = new Ruta("Q1");
		q1.agregarEsacion(estacion1);
		q1.agregarEsacion(estacion4);
		rutas.put(q1.nombre(), q1);
		
	}
	
	/**
	 * Da el tiempo de espera de una estación.
	 * @param estacion Nombre de la estación que se quiere saber el tiempo de espera.
	 * @return Un número enetro que representa el tiempo de espera en minutos.
	 * @throws SistemaException NON_EXIST_STATION, si no existe la estación dada.
	 */
	public int tiempoEspera(String estacion) throws SistemaException{
		if (estaciones.get(estacion) == null) throw new SistemaException(SistemaException.NON_EXISTENT_STATION);
		return estaciones.get(estacion).tiempoEspera();
	}
	
	/**
	 * Da las rutas que permiten ir de una estación a otra sin hacer transbordos
	 * @param estacionUno Nombre de la estación uno.
	 * @param estacionDos Nombre de la estación dos.
	 * @return Nombre de las rutas ordenadas de menor a mayor por número de paradas y alfabéticamente por nombre de la ruta
	 * @throws SistemaException NON_EXIST_STATION, si no existe alguna de las estaciones dadas.
	 */
	public String[] rutasSinTransbordos(String estacionUno, String estacionDos) throws SistemaException {
		if (estaciones.get(estacionUno) == null || estaciones.get(estacionDos) == null) throw new SistemaException(SistemaException.NON_EXISTENT_STATION);
		TreeMap<String, Integer> r = new TreeMap<String, Integer>(); 
		Estacion uno = estaciones.get(estacionUno);
		Estacion dos = estaciones.get(estacionDos);
		rutas.forEach((k, v) -> {
			List<Estacion> e = v.estaciones();
			if (e.contains(uno) && e.contains(dos) && e.indexOf(uno) < e.indexOf(dos)) {
				r.put(v.nombre(), e.indexOf(dos)-e.indexOf(uno));
			}
		});	
		Comparator<String> comparador = new Comparador<String, Integer>(r);
		Map<String, Integer> sortedMap = new TreeMap<>(comparador);
        sortedMap.putAll(r);
		return sortedMap.keySet().toArray(new String[sortedMap.size()]);
	}
	
	/**
	 * El tiempo de recorrido de un plan.
	 * @param plan El plan en una arreglo de arreglos con los nombres de las estaciones y rutas.
	 * @return Un número enetro que representa el tiempo de recorrido en minutos.
	 */
	public int timepoRecorrido(String[][] plan) throws SistemaException {
		String ruta = null;
		int tiempo = 0;
		Estacion estacion;
		for  (String[] p:plan) {
			if (estaciones.get(p[0]) == null) throw new SistemaException(SistemaException.NON_EXISTENT_STATION);
			if (rutas.get(p[1]) == null) throw new SistemaException(SistemaException.NON_EXISTENT_ROUTE);
			estacion =  estaciones.get(p[0]);
			if (!p[1].equals(ruta)) {
				ruta = p[1];
				tiempo += estaciones.get(p[0]).tiempoEspera();
			}
			tiempo += estacion.tramo().estaciones().get(estacion) / estacion.tramo().troncal().velocidadPromedio();
		}
		return tiempo;
	} 
	
	/**
	 * El mejor plan de recorrido para ir de una estación a otra.
	 * @param estacionUno Nombre de la estación uno.
	 * @param estacionDos Nombre de la estación dos.
	 * @return El plan de ruta en un arreglo de arreglos con los nombres de las estaciones y rutas.
	 */
	public String[][] mejorPlan(String estacionUno, String estacionDos) throws SistemaException {
		if (estaciones.get(estacionUno) == null || estaciones.get(estacionDos) == null) throw new SistemaException(SistemaException.NON_EXISTENT_STATION);
		return null;
	}
	
	/**
	 * Exporta a un archivo de texto las rutas que permiten ir de una estación a otra sin transbordos
	 * @param file Archivo en el que se va  exportar.
	 * @param estacionUno Nombre de la estación uno.
	 * @param estacionDos Nombre de la estación dos.
	 * @throws FileNotFoundException, Sii el archivo no se encuentra.
	 * @throws SistemaException NON_EXIST_STATION, si no existe alguna de las estaciones dadas.
	 */
	public void exportarRutasSinTransbordos(File file, String estacionUno, String estacionDos) throws FileNotFoundException, SistemaException {
		FileOutputStream fileOutput = new FileOutputStream(file);
		PrintWriter writer = new PrintWriter(fileOutput);
		String[] rutas = rutasSinTransbordos(estacionUno, estacionDos);
		for (String r:rutas) writer.println(r+"\n");
		writer.close();
	}
	
	/**
	 * 
	 * @param args
	 * @throws SistemaException
	 */
    public static void main(String[] args) throws SistemaException {
        Sistema sistema = new Sistema();
        System.out.println(Arrays.toString(sistema.rutasSinTransbordos("Portal Norte", "Cardio infantil")));
    } 
}

/**
 * 
 * @author USUARIO
 *
 * @param <K>
 * @param <V>
 */
class Comparador<K, V> implements Comparator<K> {
	
    private Map<K, V> map;
    
    /**
     * 
     * @param map
     */
    public Comparador(Map<K, V> map) {
        this.map = new HashMap<>(map);
    }
 
    @Override
    public int compare(K s1, K s2) {
    	int r = (map.get(s1)+"").compareTo(map.get(s2)+"");
    	if (r == 0) r = (s1+"").compareTo(s2+"");
        return r;
    }
}
