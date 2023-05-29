package domain;

public class ReplicateException extends Exception {
	public static final String IN_CONSTRUCTION = "Opción en construcción.";
	public static final String GENERAL_ERROR = "No se pudo realizar la acción.";
	public static final String FILE_NOT_FOUND = "No se ha encontrado el archivo.";
	public static final String CLASS_NOT_FOUND = "No se ha encontrado la clase";
	public static final String INVALID_CLASS = "El tiempo de ejecución de serialización a detectaso un problemas con una clase.";
	public static final String INVALID_OBJECT = "Uno o más objetos deserializados fallaron las pruebas de validación.";
	public static final String NOT_ACTIVE = "La serialización o deserialización no está activa.";
	public static final String STREAM_CORRUPTED = "El archivo está corrupto.";
	public static final String OPTIONAL_DATA = "Durante la lectura de un objeto hubo datos primitivos no leídos o al final de los datos pertenecientes a un objeto serializado en el flujo";
	public static final String INPUT_OUTPUT_ERROR = "Ha ocurrido un error en la entrada/salida.";
	public static final String NOT_SERIALIZABLE = "Una de las clases no es serializable.";
	public static final String EOF = "Se ha alcanzado inesperadamente un final de archivo o final de secuencia durante la entrada.";
	public static final String ILLEGAL_ARGUMENT = "Se ha pasado un argumento ilegal o inapropiado";
	public static final String INSTANCIATION_ERROR = "No se ha podido instanciar un objeto";
	public static final String INVOCATION_ERROR = "Ha ocurrido un error en la invocación de un objeto";
	public static final String ILLEGAL_ACCES = "No se tiene acceso al constructor del objeto para poder invocarlo";
	public static final String OPEN_ERROR = "Error al abrir el archivo.";
	public static final String SAVE_ERROR = "Error al guardar la red AManufacturing.";
	public static final String IMPORT_ERROR = "Error al importar el archivo";
	public static final String EXPORT_ERROR = "Error al exportar la red AManufacturing.";
	public static final String INSUFFICIENT_INFORMATION = "Información insuficiente";
	public static final String NOT_A_NUMBER = "No es un número";
	public static final String OUT_OF_RANGE = "Fuera del rango de 50x50";
    public ReplicateException(String message) {
        super(message);
    }
}
