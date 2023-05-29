package icpc;
import shapes.*;

import java.util.*;
/**
 * Señales de límite de velocidad de la red de carreteras de ICPC.
 * 
 * @author Angel Cuervo y Jefer Gonzalez 
 * @version 1.0 (31/08/2022)
 */
public class Sign{
    private ArrayList<Circle> circles = new ArrayList<Circle>();
    private shapes.Number number;
    private boolean isVisible;
    /**
     * Constructor para objetos de clase Sign.
     * @param speedLimit Límite de velocidad que va llevar la señal.
     * @param x1 Posición en x de la primera intersección.
     * @param y1 Posición en y de la primera intersección.
     * @param x2 Posición en x de la segunda intersección.
     * @param y2 Posición en y de la segunda intersección. 
     */
    public Sign(ICPC icpc, String sign, String route, int speedLimit) throws ICPCException{
        String intersectionA = sign.split("-")[0], intersectionB = sign.split("-")[1];
        String sign2 = intersectionB + "-" + intersectionA; 
        if (!icpc.existR(route)) throw new ICPCException(ICPCException.ROUTE_NOT_EXIST);
        if (icpc.existS(sign)) throw new ICPCException(ICPCException.SIGN_ALREADY_EXISTS);
        if (!icpc.existRSL(route)) throw new ICPCException(ICPCException.NO_ASSIGNED_SPEED_LIMIT); 
        if (icpc.getRoads().get(route) instanceof Rebel) throw new ICPCException(ICPCException.ROUTE_REBEL);
        if (speedLimit > 100000 || speedLimit < 1) throw new ICPCException(ICPCException.INVALID_SPEED_LIMIT);
        if (icpc.getSigns().containsKey(sign2) && icpc.getSigns().get(sign2) instanceof Unique) throw new ICPCException(ICPCException.UNIQUE_SIGN);
        int x1 = icpc.xPosition(intersectionA), y1 = icpc.yPosition(intersectionA), x2 = icpc.xPosition(intersectionB), y2 = icpc.yPosition(intersectionB);
        isVisible = false;
        number = new shapes.Number(speedLimit);
        circles.add(new Circle());
        circles.add(new Circle());
        double[] distances = distances(x1, y1, x2, y2);
        double distanceX = distances[0], distanceY = distances[1];
        move(distanceX, distanceY);
    }

    /**
     * Cambia el color de fondo de la señal de límite de velocidad.
     * @param newColor El nuevo color.
     */
    public void changeBackColor(String newColor){
        circles.get(0).changeColor(newColor);
        draw();
    }
    
    /**
     * Cambia el color de la señal de límite de velocidad.
     * @param newColor El nuevo color.
     */
    public void changeColor(String newColor){
        circles.get(1).changeColor(newColor);
        draw();
    }    

    /**
     * Cambia el color del número de la señal de límite de velocidad.
     * @param newColor El nuevo color.
     */
    public void changeColorNumber(String newColor){
        number.changeColor(newColor);
        draw();
    }
    
    /**
     * @return El color de la señal.
     */
    public String getColor(){
        return circles.get(1).getColor();
    }
    
    /**
     * @return El color trasero de la señal.
     */
    public String getBackColor(){
        return circles.get(0).getColor();
    }
    
    /**
     * @return El color trasero de la señal.
     */
    public String getColorNumber(){
        return number.getColor();
    }
    
    /**
     * @return El número de la señal.
     */
    public shapes.Number getNumber(){
        return number;
    }
    
    /**
     * Hace visible la señal de límite de velocidad.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Hace invisible la señal de límite de velocidad.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * Da la posición de la señal que va tener la señal
     * @param x1 Posición en x de la primera intersección.
     * @param y1 Posición en y de la primera intersección.
     * @param x2 Posición en x de la segunda intersección.
     * @param y2 Posición en y de la segunda intersección. 
     * @return Arreglo con las ubicaciones en el eje x y en el eje y de la señal.
     */
    public double[] distances(int x1,int y1,int x2,int y2){
        double m = 1;
        y1 += 9;
        y2 += 9;
        x1 += 9;
        x2 += 9;        
        if (x1-x2 != 0){
            m = ((double)(y1)-(double)(y2))/((double)(x1)-(double)(x2));
        }
        double b = y1 - m * x1, distanceX, distanceY;
        if(x1 == x2){
            distanceX = x1;
            distanceY = y1;
            if (y1 > y2) distanceY -= 41;
            else distanceY += 41;
        }else if (Math.abs(x1-x2) > Math.abs(y1-y2)) {
            int x = 41;
            if (x1 > x2) x = -41;
            distanceX = x1+x;
            distanceY = m*(distanceX)+b;            
        } else {
            int y = 41;
            if (y1 > y2) y = -41;
            distanceY = y1+y;
            distanceX = (distanceY-b)/m;       
        }
        double[] distances = {distanceX, distanceY};
        return distances;
    }
    
    /*
     * Mueve la señal
     */
    private void move(double distanceX, double distanceY){
        circles.get(0).changeSize(32);
        circles.get(0).changeColor("red");
        circles.get(1).changeSize(26);  
        circles.get(1).changeColor("white");
        if (number.getNumber() > 99){
            circles.get(0).changeSize(42);
            circles.get(1).changeSize(36);
            distanceX -= 6;
            distanceY -= 6;
            number.moveHorizontal((int)(distanceX)+4);
            number.moveVertical((int)(distanceY)+14);
        } else if (number.getNumber() > 9){
            number.moveHorizontal((int)(distanceX)+6);
            number.moveVertical((int)(distanceY)+8);
        } else {
            number.moveHorizontal((int)(distanceX)+12);
            number.moveVertical((int)(distanceY)+8);
        }
        circles.get(0).moveVertical((int)(distanceY));
        circles.get(0).moveHorizontal((int)(distanceX));
        circles.get(1).moveVertical((int)(distanceY+3));
        circles.get(1).moveHorizontal((int)(distanceX+3));
    }
    
    /*
     * Dibuja en la pantalla la señal de límite de velocidad.
     */
    private void draw(){
        if(isVisible) {
            circles.get(0).makeVisible();
            circles.get(1).makeVisible();
            number.makeVisible();
        } 
    }
    
    /*
     * Borra de la pantalla la señal de límite de velocidad. 
     */
    private void erase(){
        if(isVisible) {
            circles.get(0).makeInvisible();
            circles.get(1).makeInvisible();
            number.makeInvisible();
        } 
    }
}