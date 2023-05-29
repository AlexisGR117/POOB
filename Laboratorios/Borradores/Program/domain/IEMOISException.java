package domain;
/**
* Exception class of IEMOIS.
*
* @author Angel Cuervo y Jefer Gonzalez.
* @version 1.0 (25/10/2022)
*/
public class IEMOISException extends Exception{
    public static final String COURSE_NO_PRICE = "El curso no tiene precio.";
    public static final String COURSE_ERROR_PRICE = "El precio del curso debe ser mayor a 0.";
    public static final String SPECIALIZATION_EMPTY = "La especialización no tiene cursos.";
    public static final String COURSE_NO_EXIST = "El curso con ese nombre no existe.";
    public static final String TWO_COURSES = "Existen dos cursos con ese nombre.";
    public static final String COURSE_ALREADY_EXISTS = "Ya existe un curso con ese nombre.";
    public static final String INVALID_PRICE = "El precio debe ser un número positivo.";
    public static final String COURSE_WITHOUT_NAME = "El curso debe tener nombre.";
    public static final String SPECIALIZATION_ALREADY_EXISTS = "Ya existe una especialización con ese nombre.";
    public static final String INVALID_DISCOUNT = "El descuento debe ser un número positivo.";
    public static final String SOME_COURSE_NO_EXIST = "Algún curso de la especialización no existe.";
    public static final String DISCOUNT_OUT_OF_RANGE = "El descuento debe ser menor o igual a 100%.";
    /**
     * Constructor for objects of class IEMOISException
     */
    public IEMOISException (String message){
        super(message);
    }
}