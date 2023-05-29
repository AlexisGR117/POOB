package shapes;

import java.awt.*;

/**
 * A rectangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes (Modified)
 * @version 1.0  (15 July 2000)()
 */
 
public class Rectangle extends Shape{
    public static final byte EDGES = 4;
    private int height;
    private int width;
    /**
     * Create a new rectangle at default position with default color.
     */
    public Rectangle(){
        height = 40;
        width = 40;
        xPosition = 0;
        yPosition = 0;
        color = "magenta";
        isVisible = false;
    }

    /**
     * Change the size to the new size
     * @param newHeight the new height in pixels. newHeight must be >=0.
     * @param newWidht the new width in pixels. newWidth must be >=0.
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }

    /**
     * Draw the rectangle with current specifications on screen.
     */
    public void draw() {
         if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = {xPosition, xPosition + width, xPosition + width, xPosition};
            int[] ypoints = {yPosition, yPosition, yPosition - height, yPosition - height};
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 4));
        }
    }

    /**
     * Erase the rectangle on screen.
     */
    public void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}

