package domain;
import java.awt.Color;
/**
 * Elemento que infecta a los elementos vecinos en un AMAnufacturing.
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (07/10/2022)
 */
public class Virus implements Thing{
    protected Color color;
    private int row, column;
    protected boolean state, nextState;
    private AManufacturing aManufactuing;
    /**
     * Ubica una nuevo Virus aleatoriamente dentro de una red bidimensional.
     * @param am AManufacturing donde se va aparecer el nuevo Virus
     */
    public Virus(AManufacturing am){
        aManufactuing = am;  
        color = Color.red;
        state = true;
        row = (int)(Math.random()*51);
        column = (int)(Math.random()*51);
        aManufactuing.setThing(row, column, (Thing)this);    
    }

    /**
     * Ubica una nuevo Virus dentro de una red bidimensional.
     * @param am AManufacturing donde se va ubicar el nuevo Virus
     * @param row Fila donde estará ubicada el virus.
     * @param column Columna donde estará ubicada el virus.
     */
    public Virus(AManufacturing am, int row, int column){
        aManufactuing = am;  
        color = Color.red;
        state = true;
        this.row = row;
        this.column = column;
        aManufactuing.setThing(row, column, (Thing)this);    
    }
    
    /**
     * Decide el proximo estado del virus.
     */
    public void decide(){
        int num = 0;
        int num2 = 0;
        int size = aManufactuing.getSize();
        for(int dr=-1; dr<2;dr++) for (int dc=-1; dc<2;dc++){
            if((dr!=0 || dc!=0) && 0<=row+dr && row+dr<size && 0<=column+dc && column+dc<size){
                if(neighborNotIsEmpty(dr, dc) && (aManufactuing.getThing(row+dr, column+dc) instanceof Virus)){
                    num++;
                }
                num2++;
            }
        }
        nextState = (num != num2);
    }

    /**
     * Cambia el actual estado del virus por el siguiente.
     */
    public final void change(){
        state = nextState;
    }
    
    /**
     * Replica el virus en las celdas vecinas que tienen elementos.
     */
    public void replicate(){
        int size = aManufactuing.getSize();
        for(int dr=-1; dr<2;dr++) for (int dc=-1; dc<2;dc++){
            if((dr!=0 || dc!=0) && neighborNotIsEmpty(dr, dc) && 
                !(aManufactuing.getThing(row+dr, column+dc) instanceof Virus) &&
                aManufactuing.getThing(row+dr, column+dc).isActive()){
                new Virus(aManufactuing, row+dr, column+dc);
            }
        }
    }
    
    /**
     * Da la forma que tiene un Virus.
     * @return Entero que representa la forma del Virus.
     */
    public int shape(){
      return ROUND;
    }

    /**
     * Da el color que tiene el virus.
     * @return El color del virus.
     */
    public Color getColor(){
        return color;
    }
    
    /**
     * Dice si el virus está activo.
     * @return Valor booleano que dice si el estado del virus es activo.
     */
    public final boolean isActive(){
        return state;
    }
    
    /**
     * Da la fila donde se encuentra ubicada el virus.
     * @return Número de la fila dond está el virus.
     */
    public final int getRow(){
        return row;
    }

    /**
     * Da la columna donde se encuentra ubicada el virus.
     * @return Número de la columna dond está el virus.
     */
    public final int getColumn(){
        return column;
    }
    
    /**
     * Dice si la celda vecina no está vacia.
     * @param dr Fila de la celda vecina con respecto a la posición del virus.
     * @param dc Columna de la celda vecina con respecto a la posición del virus.
     * @return Valor booleano que indica si la celda vecina no está vacia.
     */    
    public boolean neighborNotIsEmpty(int dr,int dc){
        int size = aManufactuing.getSize();
        return 0<=row+dr && row+dr<size && 0<=column+dc && column+dc<size && aManufactuing.getThing(row+dr, column+dc) != null;
    }
}