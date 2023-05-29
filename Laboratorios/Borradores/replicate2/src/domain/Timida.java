package domain;
import java.awt.Color;
/**
 * Tipo de célula que solo se activa cuando tiene menos de 4 vecinos activos,
 * al tener mas de 3 se desactivan.
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (07/10/2022)
 */
public class Timida extends Cell{
    /**
     * Constructor para células de tipo Tímida.
     * @param am AManufacturing donde se va ubicar la nueva célula Tímida.
     * @param row Fila donde estará ubicada la célula Tímida.
     * @param column Columna donde estará ubicada la célula Tímida. 
     * @param active Estado de la nueva célula Tímida.
     */
    public Timida(AManufacturing am,int row, int column, boolean active){
        super(am, row, column, active);
        color = Color.gray;
    }
    
    /**
     * Da la forma que tiene la célula Tímida.
     * @return Entero que representa la forma de la célula Tímida.
     */
    public int shape(){
      return ROUND;
    }
    
    /**
     * Decide el proximo estado de la célula Tímida.
     */
    @Override
    public void decide(){
        nextState = (neighborsActive() <= 3 ? Artefact.ACTIVE:Artefact.INACTIVE);
    }
    
    public String toString() {
    	return "Timida";
    }
}