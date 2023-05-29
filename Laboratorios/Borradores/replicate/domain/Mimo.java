package domain;
import java.awt.Color;

/**
 * Tipo de célula que va imitando el estado de sus células vecinas, iniciando en dirección norte y
 * siguiendo las manecillas del reloj.
 * @author Angel Cuervo y Jefer Gonzalez
 */
public class Mimo extends Cell{
    private int rowClock;
    private int columnClock;
    /**
     * Constructor para células de tipo mimo.
     * @param am AManufacturing donde se va ubicar la nueva célula mimo.
     * @param row Fila donde estará ubicada la célula mimo.
     * @param column Columna donde estará ubicada la célula mimo. 
     * @param active Estado de la nueva célula mimo.
     */
    public Mimo(AManufacturing am,int row, int column, boolean active){
        super(am, row, column, active);
        color = Color.orange;
        rowClock = -1;
        columnClock = 0;
    }
  
    /**
     * Decide el proximo estado de la célula mimo.
     */
    @Override
    public void decide(){
        int r = getRow();
        int c = getColumn();
        AManufacturing am = getAManufacturing();
        if(!neighborIsEmpty(rowClock, columnClock)){
            nextState = (am.getThing(r+rowClock, c+columnClock).isActive() ? Artefact.ACTIVE:Artefact.INACTIVE); 
        }
        if (rowClock == -1 && (columnClock == 0 || columnClock == -1)) columnClock += 1;
        else if ((rowClock == -1 || rowClock == 0) && columnClock == 1) rowClock += 1;
        else if (rowClock == 1 && (columnClock == 1 ||columnClock == 0)) columnClock -= 1;
        else if ((rowClock == 1 ||rowClock == 0) && columnClock == -1) rowClock -= 1;
    }
}
