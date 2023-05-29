package domain;

/**
 * Clase de excepciones de Sistema.
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (27/11/2022)
 */
public class SistemaException extends Exception {
	
	public static final String NON_EXISTENT_STATION = "La estación no existe.";
	public static final String NON_EXISTENT_ROUTE = "La ruta no existe.";
	
	/**
	 * Constructor para objetos de clase SistemaException.
	 * @param message Mensaje de la excepción.
	 */
    public SistemaException(String message) {
        super(message);
    }
}
