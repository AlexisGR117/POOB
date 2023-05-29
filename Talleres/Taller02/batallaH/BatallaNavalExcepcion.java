
public class BatallaNavalExcepcion extends Exception{
    public static final String UBICACION_ERROR = "La nueva ubicacion no está dentro de los rangos del tablero";
    public BatallaNavalExcepcion(String message){
        super(message);
    }
}
