package domain;
import java.awt.Color;
/**
 * Tipo de célula que solo se activa con un vecino. 
 * En cada etapa se reproduce en las celdas vecinas vacías.
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (07/10/2022)
 */
public class Kriptonita extends Cell{
    /**
     * Constructor para células de tipo Kriptonita.
     * @param am AManufacturing donde se va ubicar la nueva célula Kriptonita.
     * @param row Fila donde estará ubicada la célula Kriptonita.
     * @param column Columna donde estará ubicada la célula Kriptonita. 
     * @param active Estado de la nueva célula Kriptonita.
     */
    public Kriptonita(AManufacturing am,int row, int column, boolean active){
        super(am, row, column, active);
        color = Color.green;
    }
    
    /**
     * Da la forma que tiene la célula Kriptonita.
     * @return Entero que representa la forma de la célula Kriptonita.
     */
    public int shape(){
      return ROUND;
    }
    
    /**
     * Replica la célula Kriptonita en las celdas vecinas que están vacias.
     */
    public void replicate(){
        for(int dr=-1; dr<2;dr++) for (int dc=-1; dc<2;dc++){
            if((dr!=0 || dc!=0) && neighborIsEmpty(dr,dc)){
                new Kriptonita(getAManufacturing(), getRow()+dr, getColumn()+dc, false);    
            }
        }
    }
    
    /**
     * Decide el proximo estado de la célula kriptonita.
     */
    @Override
    public void decide(){
        int num = 0;
        int num2 = 0;
        int r = getRow();
        int c = getColumn();
        int size = getAManufacturing().getSize();
        for(int dr=-1; dr<2;dr++) for (int dc=-1; dc<2;dc++){
            if ((dr!=0 || dc!=0)){
                if (0<=r+dr && r+dr<size && 0<=c+dc && c+dc<size) num ++;
                if (neighborIsEmpty(dr,dc)) num2 ++;
            }
        }
        nextState = (num != num2 ? Artefact.ACTIVE:Artefact.INACTIVE);
    }
}
