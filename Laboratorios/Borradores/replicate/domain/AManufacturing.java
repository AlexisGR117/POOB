package domain;
import java.util.*;

/**
 * Red bidimensional de celdas que contienen elementos.
 * 
 * @author ESCUELA 2022-02 - Angel Cuervo y Jefer Gonzalez
 */
public class AManufacturing{
    static private int SIZE=50;
    private Thing[][] lattice;
    /**
     * Constructor para objetos de clase AManufacturing.
     */
    public AManufacturing() {
        lattice=new Thing[SIZE][SIZE];
        for (int r=0;r<SIZE;r++){
            for (int c=0;c<SIZE;c++){
                lattice[r][c]=null;
            }
        }
        someThings();
    }
    
    /**
     * Devuelve el tamaño de la red bidimensional.
     * @return Entero que representa el tamaño de la red bidimensional.
     */
    public int getSize(){
        return SIZE;
    }

    /**
     * Da el elemento que está en una posición especificada.
     * @param r Número de la fila donde está el elemento.
     * @param c Número de la columna donde está el elemento.
     * @return El elemento ubicado en la posición dada.
     */
    public Thing getThing(int r,int c){
        return lattice[r][c];
    }
    
    /**
     * Coloca un elemento en la red bidimensional.
     * @param r Número de la fila donde va a estar el elemento.
     * @param c Número de la columna donde va a estar el elemento.
     * @param e Thing El elemento que se desea ubicar.
     */
    public void setThing(int r, int c, Thing e){
        lattice[r][c]=e;
    }

    /**
     * Algunos elemento que van a estar dentro de la red bidimensional.
     */
    public void someThings(){
        // Célula normal
        //Cell simba = new Cell(this, 1, 1, true);
        //Cell dala = new Cell(this, 2, 2, true);
        // Célula mimo
        //Mimo mufasa = new Mimo(this, 10, 5, true);
        //Mimo scar = new Mimo(this, 10, 6, true);
        //Mimo rafiki = new Mimo(this, 10, 7, true);
        // Célula kriptonita
        //Kriptonita one = new Kriptonita(this, 0, 0, true);
        //Kriptonita two = new Kriptonita(this, 0, SIZE-1, true);
        //Kriptonita three = new Kriptonita(this, SIZE-1, 0, true);
        //Kriptonita four = new Kriptonita(this, SIZE-1, SIZE-1, true);
        // Célula tímida
        //Timida angel = new Timida(this, 25, 25, true);
        //Timida alexis = new Timida(this, 24, 25, true);
        //Kriptonita clark = new Kriptonita(this, 24, 23, true);
        //Cell simba = new Cell(this, 23, 25, true);
        //Cell dala = new Cell(this, 24, 24, true);
        //Cell mufasa = new Cell(this, 23, 24, true);
        //Cell scar = new Cell(this, 25, 26, true);
        //Cell rafiki = new Cell(this, 26, 25, true);
        //Mimo timon  = new Mimo(this, 24, 24);
        //Mimo puba  = new Mimo(this, 23, 25);
        //Mimo sarabi  = new Mimo(this, 23, 24);
        //Mimo simba = new Mimo(this, 25, 24);
        //Mimo dala = new Mimo(this, 26, 24);
        //Mimo mufasa = new Mimo(this, 26, 25);
        //Mimo scar = new Mimo(this, 26, 26);
        //Mimo rafiki = new Mimo(this, 23, 26);
        //Mimo zazu = new Mimo(this, 25, 26);
        //Mimo shenzi = new Mimo(this, 24, 26);
        //Pared two = new Pared(this, 11, 10);
        //Pared three = new Pared(this, 9, 10);
        //Pared four2 = new Pared(this, 10, 11);
        //Pared five = new Pared(this, 10, 9);
        //Pared one = new Pared(this, 10, 10);
        //Pared six = new Pared(this,9,9);
        //Rfplicbte one = new Rfplicbte(this, 25, 25, true);
        //Rfplicbte two = new Rfplicbte(this, 25, 24, true);
        //Rfplicbte three = new Rfplicbte(this, 24, 25, true);
        // Virus
        //Virus virusOne = new Virus(this, 25, 25);
        //Virus virusTwo = new Virus(this, 25, 26);
        //Kriptonita two = new Kriptonita(this, 26, 26, false);
        //Kriptonita four = new Kriptonita(this, 27, 26, false);
        //Timida three = new Timida(this, 27, 25, true);
    }
    
    /**
     * Devuelve el número de vecinos activos al rededor de una posición.
     * @param r Número de la fila.
     * @param c Número de la columna.
     * @return El número de veciones que tiene la posición dada.
     */
    public int neighborsActive(int r, int c){
        int num=0;
        for(int dr=-1; dr<2;dr++){
            for (int dc=-1; dc<2;dc++){
                if ((dr!=0 || dc!=0) && inLatice(r+dr,c+dc) && 
                    (lattice[r+dr][c+dc]!=null) &&  (lattice[r+dr][c+dc].isActive())) num++;
            }
        }
        return (inLatice(r,c) ? num : 0);
    }
    
    /**
     * Dice si la celda de la red bidimensional en la posición dada está vacía.
     * @param r Número de la fila.
     * @param c Número de la columna.
     * @return Valor booleano que indica si la celda está vacía.
     */
    public boolean isEmpty(int r, int c){
        return (inLatice(r,c) && lattice[r][c]==null);
    }    
        
    /**
     * Dice si la posicion dada está dentro de la red bidimensional.
     * @param r Número de la fila.
     * @param c Número de la columna.
     * @return Valor booleano que indica si la posición es valida.
     */
    private boolean inLatice(int r, int c){
        return ((0<=r) && (r<SIZE) && (0<=c) && (c<SIZE));
    }
    
    /**
     * Cambia el estado de los elementos y los replica, si es el caso, de manera correpondiente al comportmaiento de cada uno.
     */
    public void ticTac(){     
        Thing[][] latticeCopy =  new Thing[SIZE][SIZE];
        for(int x = 0; x < SIZE; x++) latticeCopy[x] = Arrays.copyOf(lattice[x], SIZE);
        for(int x = 0; x < SIZE; x++) for (int y = 0; y < SIZE; y++){
            if (latticeCopy[x][y] != null && latticeCopy[x][y] == lattice[x][y]) lattice[x][y].replicate();    
        }
        for(int x = 0; x < SIZE; x++) for (int y = 0; y < SIZE; y++){
            if (lattice[x][y] != null) lattice[x][y].decide();    
        }
        for(int x = 0; x < SIZE; x++) for (int y = 0; y < SIZE; y++){
            if (lattice[x][y] != null) lattice[x][y].change();
        }
    }
}
