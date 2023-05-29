package shapes;

import java.awt.*;
import java.awt.geom.*;
/**
 * A shape that can be manipulated and that draws itself on a canvas.
 * 
 * @author Angel Cuervo y Jefer Gonzalez 
 * @version 1.0 (15/10/2022)
 */
public abstract class Shape{
    protected int xPosition, yPosition;
    protected String color;
    protected boolean isVisible;
    /**
     * Change the color of the shape. 
     * @param color the new color.
     */
    public final void changeColor(String newColor){
        color = newColor;
        draw();
    }
    
    /**
     * @return Color of the shape
     */
    public final String getColor(){
        return color;
    }
    
    /**
     * Make this shape visible. If it was already visible, do nothing.
     */   
    public final void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this shape invisible. If it was already invisible, do nothing.
     */
    public final void makeInvisible(){
        erase();
        isVisible = false;
    }

    /**
     * Draw the shape with current specifications on screen.
     */
    public abstract void draw();
    
    /**
     * Erase the shape on scree
     */
    public abstract void erase();

    /**
     * Move the shape horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the shape vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }
}
