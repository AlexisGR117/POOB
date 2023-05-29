package domain;
/**
 * Exception class of IEMOIS.
 *
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (11/04/2022)
 */
public class TantFantException extends Exception {
    public static final String INVALID_SIZE = "El tamaño del tablero debe ser mayor a 2.";
    public static final String INVALID_MOVEMENT = "No se puede realizar el movimiento.";
    public static final String OFF_THE_BOARD = "La posición dada no está dentro del tablero.";
    public static final String INVALID_PLAYER = "No es el turno de este jugador.";
    /**
     * Constructor for objects of class TantFantException.
     */
    public TantFantException(String message) {
        super(message);
    }
}