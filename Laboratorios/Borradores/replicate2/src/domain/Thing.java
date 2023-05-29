package domain;
import java.awt.Color;

/**
 * Plantilla que generaliza el comportamiento de un elemento dentro de un AManufacturing.
 * 
 * @author ESCUELA 2022-02
 */
public interface Thing{
    int ROUND = 1;
    int SQUARE = 2;
    /**
     * Decide el siguiente estado del elemento.
     */
    void decide();
   
    /**
     * Cambia el estado del elemento.
     */
    default void change(){
    }
  
    /**
     * Replica un elemento.
     */
    default void replicate(){
    }
  
    /**
     * Da la forma que tiene el elemento.
     * @return Entero que representa la forma del elemento.
     */
    default int shape(){
        return SQUARE;
    }
  
    /**
     * Da el color que tiene el elemento.
     * @return El color del elemento.
     */
    default Color getColor(){
        return Color.red;
    }
  
    /**
     * Dice si el elemento está activo.
     * @return Valor booleano que dice si el estado del elemento es activo.
     */
    default boolean isActive(){
        return false;
    }
    
    String toString();
}
