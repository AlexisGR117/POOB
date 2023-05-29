package shapes;

import java.awt.*;
import java.awt.geom.*;
/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0.  (15 July 2000) 
 */

public class Circle extends Shape{
    public static final double PI=3.1416;
    private int diameter;
    /**
     * Create a new circle at default position with default color.
     */
    public Circle(){
        diameter = 40;
        xPosition = 0;
        yPosition = 0;
        color = "orange";
        isVisible = false;
    }

    /**
     * Draw the circle with current specifications on screen.
     */
    public void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, 
                new Ellipse2D.Double(xPosition, yPosition, 
                diameter, diameter));
        }
    }
    
    /**
     * Erase the circle on screen.
     */
    public void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }

    /**
     * Change the size.
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newDiameter){
        erase();
        diameter = newDiameter;
        draw();
    }
}
