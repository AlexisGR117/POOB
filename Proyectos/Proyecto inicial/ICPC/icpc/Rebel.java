package icpc;
/**
 * Tipo de ruta que no deja que le pongan señales.
 * 
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (17/10/2022)
 */
public class Rebel extends Road{
    /**
     * Constructor para rutas de tipo Rebel.
     * @param x1 Posición en x de la primera intersección.
     * @param y1 Posición en y de la primera intersección.
     * @param x2 Posición en x de la segunda intersección.
     * @param y2 Posición en y de la segunda intersección. 
     */
    public Rebel(ICPC icpc, String route) throws ICPCException{
        super(icpc, route);
        changeBackColor("orange");
    }
}