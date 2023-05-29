import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.JOptionPane;
/**
 * BinaryTaken es un juego solitario que tiene como objetivo ordenar de menor a mayor fichas
 * mágicas marcadas con números binarios. El tablero inicia con un número determinadas de
 * fichas al azar y el jugador puede decidir fijar o liberar fichas o rotar todas las que no están
 * fijas. Desarrolle la clase BinaryTaken que permita simular el juego.
 * 
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (27 de agosto de 2022)
 */
public class BinaryTaken
{
    private ArrayList<Binary> chips = new ArrayList<Binary>(); 
    private int maxValue;
    private int size;
    private boolean isVisible;
    private int fixed;
    /**
     * Constructor para los objetos de clase BinaryTaken.
     */
    public BinaryTaken(int numChips,int max){
        int x = 455;
        maxValue = max;
        size = numChips;
        int y = 15;
        for (int i = 0; i <= numChips - 1; i += 1){
            chips.add(i, new Binary((int)(Math.random()*max+1)));
            chips.get(i).moveTo(x, y);
            chips.get(i).changeColor("green");
            y += 50;
        }
        isVisible = false;
        makeVisible();
    }
    
    /**
     * Modifica al azar el valor de todas las fichas no fijas.
     */
    public void rotate(){
        for (int i = 0; i < chips.size(); i += 1){
            if (chips.get(i).getColor() == "green"){
                chips.get(i).change((int)(Math.random()*maxValue+1));
            }
        }
        if (fixed == size){
            blink();
        }
    }
    
    /**
     * Cambia el modo de una ficha a fija.
     * @param position Posición de la ficha.
     */
    public void fix(int position){
        if (chips.get(position-1).getColor().equals("green")){
            fixed += 1;
            chips.get(position-1).changeColor("red");
        } else {
            blink();
        }
        if (fixed == size){
            boolean gana = true;
            for (int i = 0; i < chips.size() - 1; i += 1){
                if (chips.get(i).getDecimal() > chips.get(i+1).getDecimal()){
                gana = false;
                }   
            }
             if (gana){
                JOptionPane.showMessageDialog(null, "¡Ganaste!");
            }   
        }
    }
    
    /**
     * Cambia el modo de una ficha a no fija.
     * @param position Posición de la ficha.
     */
    public void unfix(int position){
        if (chips.get(position-1).getColor().equals("red")){
            fixed -= 1;
            chips.get(position-1).changeColor("green");
        } else {
            blink();
            }
    }
    
    /**
     * Hace parpadear las fichas del juego.
     */
    public void blink(){
        for (int i = 0; i <= 1; i += 1){
            makeInvisible();
            makeVisible();
         }
    }
    
    /**
     * Hace el juego visible. Si ya es visible no hace nada.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Hace el juego invisible. Si ya es invisible no hace nada.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /*
     * Dibuja el juego en la pantalla.
     */
    private void draw() {
        if(isVisible) {
            for (int i = 0; i <= size - 1; i += 1){
                chips.get(i).makeVisible();
            }
        }
    }

    /*
     * Borra el juegon de la pantalla.
     */
    private void erase(){
        if(isVisible) {
            for (int i = 0; i <= size - 1; i += 1){
                chips.get(i).makeInvisible();
            }
        }
    }
}