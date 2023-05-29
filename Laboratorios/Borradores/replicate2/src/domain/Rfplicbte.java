package domain;

import java.awt.Color;

/**
 * Tipo de célula que si encuentran un número impar de las nueve vecinas
 * llena con células activas, quedan activas, de lo contrario quedan inactivas. 
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (07/10/2022)
 */
public class Rfplicbte extends Cell{
    /**
     * Constructor para células de tipo Rfplicbte.
     * @param am AManufacturing donde se va ubicar la nueva célula Rfplicbte.
     * @param row Fila donde estará ubicada la célula Rfplicbte.
     * @param column Columna donde estará ubicada la célula Rfplicbte. 
     * @param active Estado de la nueva célula Rfplicbte.
     */
    public Rfplicbte(AManufacturing am,int row, int column, boolean active){
        super(am, row, column, active);
        color = color.blue;
    }
  
    /**
     * Replica la célula Rfplicbte en las celdas vecinas que están vacias.
     */
    public void replicate(){
        for(int dr=-1; dr<2;dr++) for (int dc=-1; dc<2;dc++){
            if((dr!=0 || dc!=0) && neighborIsEmpty(dr,dc)){
                new Rfplicbte(getAManufacturing(), getRow()+dr, getColumn()+dc, false);    
            }
        }   
    }
    
    /**
     * Decide el proximo estado de la célula Rfplicbte.
     */
    @Override
    public void decide(){
        int num = (isActive() ? neighborsActive() + 1:neighborsActive()); 
        nextState = (num % 2 != 0 ? Artefact.ACTIVE:Artefact.INACTIVE); 
    }
    
    public String toString() {
    	return "Rfplicbte";
    }
}
