package icpc;
/**
 * Tipo de señal que ajusta su límite al mínimo de las rutas de la
 * ciudad en la que se ubica.
 * 
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (17/10/2022)
 */
public class Cautious extends Sign{
    /**
     * Constructor para señales de tipo Cautious.
     * @param speedLimit Límite de velocidad que va llevar la señal.
     * @param x1 Posición en x de la primera intersección.
     * @param y1 Posición en y de la primera intersección.
     * @param x2 Posición en x de la segunda intersección.
     * @param y2 Posición en y de la segunda intersección. 
     */
    public Cautious(ICPC icpc, String sign, String route, int speedLimit) throws ICPCException{
        super(icpc, sign, route, speedLimit);
        changeColor("lightGray");
    }
}
