package icpc;

/**
* Clase excepción de ICPC.
*
* @author Angel Cuervo y Jefer Gonzalez.
* @version 1.0 (30/10/2022)
*/
public class ICPCException extends Exception{
    public static final String INVALID_LENGTH = "El largo de la red ICPC debe estar entre 200 y 950.";
    public static final String INVALID_WIDTH = "El ancho de la red ICPC debe estar entre 200 y 950.";
    public static final String INVALID_COST = "El costo de las señales debe estar entre 1 y 100000.";
    public static final String INVALID_SPEED_LIMIT = "El límite de velocidad de cada ruta debe estar entre 1 y 100000.";
    public static final String INVALID_INFORMATION = "Por cada carretera deben ir dos intersecciones y su límite de velocidad.";
    public static final String INVALID_X_POSITION = "La posición en x de la intersección se sale de la red ICPC.";
    public static final String INVALID_Y_POSITION = "La posición en y de la intersección se sale de la red ICPC.";
    public static final String INVALID_COLOR = "El color dado no está dentro de la opciones de Canvas que son: red, orange, yellow, green, cyan, magenta, pink, white, lightGray, gray, darkGray, blue, black.";
    public static final String INTERSECTION_ALREADY_EXISTS = "El color dado ya está siendo usado por una intersección existente.";
    public static final String INVALID_POSITION = "Otra intersección ya está en la posición dada.";
    public static final String INVALID_INTERSECTION_TYPE = "El tipo de interseccion dado no está dentro de la opciones que son: Normal, Hermit o Needy.";
    public static final String EQUAL_INTERSECTIONS = "Las intersecciones de las rutas deben ser diferentes.";
    public static final String FIRST_INTERSECTION_NOT_EXIST = "La primera intersección no existe.";
    public static final String SECOND_INTERSECTION_NOT_EXIST = "La segunda intersección no existe.";
    public static final String ROUTE_ALREADY_EXISTS = "Las intersecciones dadas ya tienen ruta.";
    public static final String UNNECESSARY_ROUTE = "La ruta es innecesaria. Solo debe haber una ruta desde cualquier intersección a cualquier otra intersección.";
    public static final String FIRST_INTERSECTION_IS_HERMIT = "La primera intersección es de tipo Hermit y no permite mas de una ruta.";
    public static final String SECOND_INTERSECTION_IS_HERMIT = "La segunda intersección es de tipo Hermit y no permite mas de una ruta.";
    public static final String INVALID_ROUTE_TYPE = "El tipo de ruta dado no está dentro de la opciones que son: Normal, Rebel o Fixed.";
    public static final String ROUTE_NOT_EXIST = "La carretera con las intersecciones dadas no existe.";
    public static final String ONLY_INCREASE_SPEED_LIMIT = "El límite de velocidad que se le asigna a la carretera debe ser mayor al que tenía asignado antes.";
    public static final String NO_ASSIGNED_SPEED_LIMIT = "La carretera no tiene asignada un límite de velocidad.";
    public static final String SIGN_ALREADY_EXISTS = "La ruta dada ya tiene una señal de límite de velocidad.";
    public static final String ROUTE_REBEL = "La ruta dada es Rebel por lo que no se le puden poner señales.";
    public static final String UNIQUE_SIGN = "Hay una señal Unique en la ruta dada.";
    public static final String INVALID_SIGN_TYPE = "El tipo de ruta dado no está dentro de la opciones que son: Normal, Twin o Cautious.";
    public static final String ROUTE_WITH_SIGN = "Al poner una señal Twin o Unique no debe haber ninguna señal en la ruta.";
    public static final String INTERSECTION_NOT_EXIST = "El color dado no está siendo usado por una intersección.";
    public static final String INTERSECTION_WITH_FIXED_ROUTE = "La intersección contiene una ruta Fixed.";
    public static final String FIXED_ROUTE = "Las rutas de tipo Fixed no se pueden eliminar.";
    public static final String SIGN_NOT_EXIST = "No existe la señal en la carretera de las intersecciones dadas.";
    public static final String EQUAL_ROUTES = "Las rutas deben ser diferentes.";
    /**
     * Constructor para objetos de clase ICPCException.
     */
    public ICPCException (String message){
        super(message);
    }
}