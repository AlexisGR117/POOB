package icpc;
import shapes.*;

import java.util.*;
/**
 * Carreteras de la red de carreteras de ICPC.
 * 
 * @author Angel Cuervo y Jefer Gonzalez 
 * @version 1.0 (31/08/2022)
 */
public class Road{
    private ArrayList<Rhomboid> rhomboids = new ArrayList<Rhomboid>();
    private boolean isVisible;
    private int x1, x2, y1, y2, speedLimit;
    private shapes.Number speed;
    private ICPC icpc;
    private String route;
    /**
     * Constructor para objetos de clase Road.
     * @param x1 Posición en x de la primera intersección.
     * @param y1 Posición en y de la primera intersección.
     * @param x2 Posición en x de la segunda intersección.
     * @param y2 Posición en y de la segunda intersección. 
     */
    public Road(ICPC icpc, String route) throws ICPCException{
        this.icpc = icpc;
        String intersectionA = route.split("-")[0], intersectionB = route.split("-")[1];
        if (intersectionA.equals(intersectionB)) throw new ICPCException(ICPCException.EQUAL_INTERSECTIONS);  
        if (!icpc.existI(intersectionA)) throw new ICPCException(ICPCException.FIRST_INTERSECTION_NOT_EXIST);  
        if (!icpc.existI(intersectionB)) throw new ICPCException(ICPCException.SECOND_INTERSECTION_NOT_EXIST);  
        if (icpc.existR(route)) throw new ICPCException(ICPCException.ROUTE_ALREADY_EXISTS);
        if (!icpc.validRoute(intersectionA, intersectionB)) throw new ICPCException(ICPCException.UNNECESSARY_ROUTE);  
        if (!icpc.validRIntersection(intersectionA)) throw new ICPCException(ICPCException.FIRST_INTERSECTION_IS_HERMIT);  
        if (!icpc.validRIntersection(intersectionB)) throw new ICPCException(ICPCException.SECOND_INTERSECTION_IS_HERMIT); 
        this.route = route;
        int x1 = icpc.xPosition(intersectionA), y1 = icpc.yPosition(intersectionA), x2 = icpc.xPosition(intersectionB), y2 = icpc.yPosition(intersectionB);
        rhomboids.add(new Rhomboid(x1+25, y1+25, x2+25, y2+25, 25, "black"));
        rhomboids.add(new Rhomboid(x1+25, y1+25, x2+25, y2+25, 15, "white"));
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        isVisible = false; 
    }
    
    /**
     * Asigna un límite de velocidad a la carretera.
     * @param s Límite de velocidad.
     */
    public void setSpeedLimit(int s) throws ICPCException{
        if (!icpc.higherRSL(route, s)) throw new ICPCException(ICPCException.ONLY_INCREASE_SPEED_LIMIT);  
        speedLimit = s;
        speed = new shapes.Number(s);
        speed.changeSide(2);
        speed.changeColor("pink");
        speed.moveVertical((y1+y2)/2-10);
        if (speedLimit > 99) speed.moveHorizontal((x1+x2)/2+12);
        else if (speedLimit > 9) speed.moveHorizontal((x1+x2)/2+18);
        else speed.moveHorizontal((x1+x2)/2+23);
        draw();
    }
    
    /**
     * Devuelve el límite de velocidad que tiene la carretera.
     * @return El límite de velocidad.
     */
    public int getSpeedLimit(){
        return speedLimit;
    }
    
    /**
     * Cambia el color trasero de la carretera.
     * @param newColor El nuevo color.
     */
    public void changeBackColor(String newColor){
        rhomboids.get(0).changeColor(newColor);
        draw();
    }
    
    /**
     * Cambia el color de la carretera.
     * @param newColor El nuevo color.
     */
    public void changeColor(String newColor){
        rhomboids.get(1).changeColor(newColor);
        draw();
    }
    
    /**
     * @return El color de la carretera.
     */
    public String getColor(){
        return rhomboids.get(1).getColor();
    }
    
    /**
     * @return El color trasero de la carretera.
     */
    public String getBackColor(){
        return rhomboids.get(0).getColor();
    }

    /**
     * Hace visible la carretera.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Hace invisible la carretera.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /*
     * Dibuja en la pantalla la carretera.
     */
    private void draw() {
        if(isVisible) {
            rhomboids.get(0).makeVisible();
            rhomboids.get(1).makeVisible();
            if (getSpeedLimit() != 0) speed.makeVisible();
        } 
    }
    /*
     * Borra de la pantalla la carretera.
     */
    private void erase(){
        if(isVisible) {
            rhomboids.get(0).makeInvisible();
            rhomboids.get(1).makeInvisible();
            if (getSpeedLimit() != 0) speed.makeInvisible();
        }
    } 

}
