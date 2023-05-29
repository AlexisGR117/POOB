package domain;
import java.awt.Color;
import java.io.Serializable;

/**
 * Clase abstracta que generalizar el comportamiento y características de un artefacto dentro de un AManufacturing.
 * @author ESCUELA 2022-02
 */
public abstract class Artefact implements Serializable {
    public final static char UNKNOWN='u', ACTIVE='a', INACTIVE='d';
    protected char state;
    private int steps;
    /**
     * Crea un nuevo artefacto.
     */
    public Artefact(){
        state=UNKNOWN;
        steps=0;
    }

    /**
     * Hace en el artefacto de un paso.
     */
    protected void step(){
        steps++;
    }    
    
    /**
     * Devuelve los pasos que ha dado el artefacto.
     * @return Número entero que representa los pasos dados por el artefacto.
     */   
    public final int getSteps(){
        return steps;
    }    

    /**
     * Dice si el artefacto está activo.
     * @return Valor booleano que dice si el estado del artefacto es activo.
     */
    public final boolean isActive(){
        return (state == Artefact.ACTIVE) ;
    }
    
}
