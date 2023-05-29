package domain;
import java.awt.Color;
/**
 * Elemento más importante dentro de una red AManufacturing.
 * @author ESCUELA 2022-02
 */
public class Cell extends Artefact implements Thing{
    protected char nextState;
    protected Color color;
    private AManufacturing aManufactuing;
    private int row, column;
    /**
     * Ubica una nueva célula dentro de una red bidimensional.
     * @param am AManufacturing donde se va ubicar la nueva célula.
     * @param row Fila donde estará ubicada la célula.
     * @param column Columna donde estará ubicada la célula. 
     * @param active Estado de la nueva célula.
     */
    public Cell(AManufacturing am,int row, int column, boolean active){
        aManufactuing=am;
        this.row=row;
        this.column=column;
        state=(active ? Artefact.ACTIVE: Artefact.INACTIVE);
        nextState=(active ? Artefact.ACTIVE: Artefact.INACTIVE);
        aManufactuing.setThing(row,column,(Thing)this);    
        color = Color.black;
    }
    
    /**
     * Da la fila donde se encuentra ubicada la célula.
     * @return Número de la fila dond está la célula.
     */
    public final int getRow(){
        return row;
    }

    /**
     * Da la columna donde se encuentra ubicada la célula.
     * @return Número de la columna dond está la célula.
     */
    public final int getColumn(){
        return column;
    }

    /**
     * Da el color que tiene la célula.
     * @return Color de la célula.
     */
    public final Color getColor(){
        return color;
    }
    
    /**
     * Da la red bidimensional de celdas donde está la célula.
     * @return AManufacturing donde está ubicada la célula,
     */
    public final AManufacturing getAManufacturing(){
        return aManufactuing;
    }
    
    /**
     * Decide el proximo estado de la célula.
     */
    public void decide(){
        nextState=(getSteps() % 3 == 0 ? Artefact.ACTIVE:Artefact.INACTIVE);
    }

    /**
     * Cambia el actual estado de la célua por el siguiente.
     */
    public final void change(){
        step();
        state = nextState;
    }
    
    /**
     * Develve el número de los vecinos que están activos de la célula.
     * @return Número de vecinos activos.
     */
    public int neighborsActive(){
        return aManufactuing.neighborsActive(row,column);
    }
    
    /**
     * Dice si la celda vecina está vacia.
     * @param dr Fila de la celda vecina con respecto a la posición de la célula.
     * @param dc Columna de la celda vecina con respecto a la posición de la célula.
     * @return Valor booleano que indica si la celda vecina está vacia.
     */    
    public boolean neighborIsEmpty(int dr,int dc){
        return aManufactuing.isEmpty(row+dr,column+dc);
    }
}
