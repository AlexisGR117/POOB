package icpc;
/**
 * Tipo de señal que siempre va con una gemela
 * que se ubica en el otro extremo de la ruta.
 * 
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (17/10/2022)
 */
public class Twin extends Sign{
    /**
     * Constructor para señales de tipo Twin.
     * @param speedLimit Límite de velocidad que va llevar la señal.
     * @param x1 Posición en x de la primera intersección.
     * @param y1 Posición en y de la primera intersección.
     * @param x2 Posición en x de la segunda intersección.
     * @param y2 Posición en y de la segunda intersección. 
     */
    public Twin(ICPC icpc, String sign, String route, int speedLimit) throws ICPCException{
        super(icpc, sign, route, speedLimit);
        changeColor("yellow");
    }
}
