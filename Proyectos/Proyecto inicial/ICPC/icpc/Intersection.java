package icpc;
import shapes.*;

import java.util.*;
/**
 * Intersecciones de la red de carreteras de ICPC.
 * 
 * @author Angel Cuervo y Jefer Gonzalez 
 * @version 1.0 (31/08/2022)
 */
public class Intersection{
    private String color;
    private ArrayList<Circle> circles = new ArrayList<Circle>();
    private boolean isVisible;
    private int xPosition, yPosition;
    private ICPC icpc;
    /**
     * Contructor para objetos de clase Intersection.
     * @param color Color con el que se identifica la intersección.
     * @param x Posición en x de la intersección.
     * @param y Posición en y de la intersección.
     */
    public Intersection(ICPC icpc, String color, int x, int y) throws ICPCException{
        this.icpc = icpc;
        boolean valid = icpc.validIntersection(x, y), used = icpc.existI(color);
        if (used) throw new ICPCException(ICPCException.INTERSECTION_ALREADY_EXISTS);       
        if (!valid) throw new ICPCException(ICPCException.INVALID_POSITION);
        if (0 > x || x > icpc.getWidth()) throw new ICPCException(ICPCException.INVALID_X_POSITION);
        if (0 > y || y > icpc.getLength()) throw new ICPCException(ICPCException.INVALID_Y_POSITION);
        if (!Canvas.existColor(color)) throw new ICPCException(ICPCException.INVALID_COLOR);
        xPosition = x;
        yPosition = y;
        this.color = color;
        isVisible = false;
        circles.add(new Circle());
        circles.add(new Circle());
        circles.get(0).changeSize(50);
        circles.get(0).changeColor("black");
        circles.get(1).changeSize(40);
        circles.get(1).changeColor(color);
        circles.get(0).moveHorizontal(x);
        circles.get(0).moveVertical(y);
        circles.get(1).moveHorizontal(x+5);
        circles.get(1).moveVertical(y+5);
    }
    
    /**
     * Cambia el color de la señal de límite de velocidad.
     * @param newColor El nuevo color.
     */
    public void changeBackColor(String newColor){
        circles.get(0).changeColor(newColor);
        draw();
    }
    
    /**
     * Da la posición horizontal en la que se encuentra a intersección.
     * @return Retorna la posición de la intersección en el eje x.
     */
    public int getXPosition(){
        return xPosition;
    }
    
    /**
     * Da la posición vertical en la que se encuentra a intersección.
     * @return Retorna la posición de la intersección en el eje y.
     */
    public int getYPosition(){
        return yPosition;
    }
    
    /**
     * @return El color de la intersección.
     */
    public String getColor(){
        return color;
    }
    
    /**
     * @return El color trasero de la intersección.
     */
    public String getBackColor(){
        return circles.get(0).getColor();
    }
    
    /**
     * Hace visible la intersección.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Hace invisible la intersección.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /*
     * Dibuja en la pantalla la interseccion.
     */
    private void draw() {
        if(isVisible) {
            circles.get(0).makeVisible();
            circles.get(1).makeVisible();
        } 
    }
    
    /*
     * Borra de la pantalla la interseccion.
     */
    private void erase(){
        if(isVisible) {
            circles.get(0).makeInvisible();
            circles.get(1).makeInvisible();
        } 
    }
}
