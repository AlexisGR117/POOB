package shapes;

import java.awt.*;
/**
 * Un romboide que puede ser manipulado y representado en un canvas.
 * 
 * @author Angel Cuervo y Jefer Gonzalez 
 * @version 1.0 (31/08/2022)
 */
public class Rhomboid extends Shape{
    private int side, xPosition2, yPosition2;
    /**
     * Crea un nueve romboide con unos tamaños, posición y color predefinidos.
     * @param x1 Posición en x del centro del primer lado recto.
     * @param y1 Posición en y del centro del primer lado recto.
     * @param x2 Posición en x del centro del segundo lado recto.
     * @param y2 Posición en y del centro del segundo lado recto.
     * @param side Longitud de los lados rectos.
     * @param color Color del romboide.
     */
    public Rhomboid(int x1,int y1,int x2,int y2, int side, String color){
        this.side = side;
        xPosition = x1;
        yPosition = y1;
        xPosition2 = x2;
        yPosition2 = y2;
        this.color = color;
        isVisible = false;
    }
    
    /**
     * Dibuja el romboide en la pantalla.
     */
    public void draw() {
         if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            if (Math.abs(xPosition-xPosition2) > Math.abs(yPosition-yPosition2)){
                int[] xpoints = {xPosition, xPosition2, xPosition2, xPosition};
                int[] ypoints = {yPosition + side/2, yPosition2 + side/2, yPosition2 - side/2, yPosition - side/2};
                canvas.draw(this, color, new Polygon(xpoints, ypoints, 4));
            } else {
                int[] xpoints = {xPosition + side/2, xPosition2 + side/2, xPosition2 - side/2, xPosition - side/2};
                int[] ypoints = {yPosition, yPosition2, yPosition2, yPosition};
                canvas.draw(this, color, new Polygon(xpoints, ypoints, 4)); 
            }
        }
    }

    /**
     * Borra el romboide de la pantalla.
     */
    public void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}
