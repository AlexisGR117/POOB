package icpc;
/**
 * Tipo de intersección que solo permite una ruta.
 * 
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (17/10/2022)
 */
public class Hermit extends Intersection{
    /**
     * Contructor para intersecciones de tipo Needy.
     * @param color Color con el que se identifica la intersección.
     * @param x Posición en x de la intersección.
     * @param y Posición en y de la intersección.
     */
    public Hermit(ICPC icpc, String color, int x, int y) throws ICPCException{
        super(icpc, color, x, y);
        changeBackColor("blue");
    }
}
