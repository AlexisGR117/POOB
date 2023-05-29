package shapes;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
/**
 * Representación de números decimales formados por rectángulos.
 * 
 * @author Angel Cuervo y Jefer Gonzalez 
 * @version 1.0 (31/08/2022)
 */
public class Number extends Shape{
    private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
    private int number, side;
    /**
     * Constructor para objetos de clase Number.
     * @param n Número que se va representar gráficamente.
     */
    public Number(int n){
       isVisible = false; 
       number = n;
       side = 3;
       xPosition = 0;
       yPosition = side;
       color = "black";
    }
        
    /**
     * Mueve la representación del número horizontalmente.
     * @param distance La distancia en pixeles.
     */
    @Override
    public void moveHorizontal(int distance){
        for (int i = 0; i < rectangles.size(); i ++){
            rectangles.get(i).moveHorizontal(distance);
        }
        xPosition += distance;
    }

    /**
     * Mueve la representación del número verticalmente.
     * @param distance La distancia en pixeles.
     */
    @Override
    public void moveVertical(int distance){
        for (int i = 0; i < rectangles.size(); i ++){
            rectangles.get(i).moveVertical(distance);
        }
        yPosition += distance;
    }
    
    /**
     * Cambia el tamaño del número.
     * @param newSide Nuevo tamaño.
     */
    public void changeSide(int newSide){
        side = newSide;
        draw();
    } 
    
    /**
     * @return El número en entero que se está representando gráficamente.
     */
    public int getNumber(){
        return number;
    } 
    
    /**
     * Dibuja la representación del número en la pantalla.
     */
    public void draw() {
        if(isVisible) {
            int numrec = 0;
            String n = number+"";
            for (int i = 0; i < n.length(); i ++){
                for (int j = 0; j < 5; j++){
                   for (int k = 0; k < 3; k++){
                       if(!((j == 1 || j == 3) && k == 1)){
                           rectangles.add(numrec, new Rectangle());
                           rectangles.get(numrec).changeColor(color);
                           rectangles.get(numrec).changeSize(side, side);
                           rectangles.get(numrec).moveHorizontal(xPosition+side*(k+4*i));
                           rectangles.get(numrec).moveVertical(yPosition+j*side);
                           rectangles.get(numrec).makeVisible();
                           numrec ++; 
                       }
                   }
                }
                if (n.substring(i, i+1).equals("1")){
                   rectangles.get(3+13*i).makeInvisible();
                   rectangles.get(5+13*i).makeInvisible();   
                   rectangles.get(6+13*i).makeInvisible();
                   rectangles.get(8+13*i).makeInvisible();   
                   rectangles.get(10+13*i).makeInvisible();
                   rectangles.get(11+13*i).makeInvisible();
                } else if (n.substring(i, i+1).equals("2")){
                   rectangles.get(3+13*i).makeInvisible();
                   rectangles.get(9+13*i).makeInvisible();            
                } else if (n.substring(i, i+1).equals("3")){
                   rectangles.get(3+13*i).makeInvisible();
                   rectangles.get(8+13*i).makeInvisible();            
                } else if (n.substring(i, i+1).equals("4")){
                  rectangles.get(1+13*i).makeInvisible();
                  rectangles.get(8+13*i).makeInvisible();
                  rectangles.get(10+13*i).makeInvisible();
                  rectangles.get(11+13*i).makeInvisible();     
                } else if (n.substring(i, i+1).equals("5")){
                  rectangles.get(4+13*i).makeInvisible();
                  rectangles.get(8+13*i).makeInvisible();   
                } else if (n.substring(i, i+1).equals("6")){
                  rectangles.get(4+13*i).makeInvisible();     
                } else if (n.substring(i, i+1).equals("7")){
                  rectangles.get(3+13*i).makeInvisible();
                  rectangles.get(5+13*i).makeInvisible();
                  rectangles.get(8+13*i).makeInvisible();
                  rectangles.get(10+13*i).makeInvisible();
                  rectangles.get(11+13*i).makeInvisible();     
                }  else if (n.substring(i, i+1).equals("9")){
                  rectangles.get(8+13*i).makeInvisible();
                  rectangles.get(10+13*i).makeInvisible();
                  rectangles.get(11+13*i).makeInvisible();     
                } else if (n.substring(i, i+1).equals("0")){
                  rectangles.get(6+13*i).makeInvisible();  
                } 
            }
        }
    }
    
    /**
     * Borra de la pantalla la representación del número.
     */
    public void erase(){
        if(isVisible) {
            for (int i = 0; i < rectangles.size(); i ++){
                rectangles.get(i).makeInvisible();
            }  
        } 
    }
}

