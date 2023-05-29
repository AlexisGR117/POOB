package icpc;
/**
 * Tipo de señal que va ser la unica en la ruta y es valida para ambos extremos.
 * 
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (17/10/2022)
 */
public class Unique extends Sign{
    /**
     * Constructor para señales de tipo Unique.
     * @param speedLimit Límite de velocidad que va llevar la señal.
     * @param x1 Posición en x de la primera intersección.
     * @param y1 Posición en y de la primera intersección.
     * @param x2 Posición en x de la segunda intersección.
     * @param y2 Posición en y de la segunda intersección. 
     */
    public Unique(ICPC icpc, String sign, String route, int speedLimit) throws ICPCException{
        super(icpc, sign, route, speedLimit);
        changeColor("orange");
    }
    
    /**
     * Da la posición de la señal que va tener la señal
     * @param x1 Posición en x de la primera intersección.
     * @param y1 Posición en y de la primera intersección.
     * @param x2 Posición en x de la segunda intersección.
     * @param y2 Posición en y de la segunda intersección. 
     * @return Arreglo con las ubicaciones en el eje x y en el eje y de la señal.
     */
    @Override
    public double[] distances(int x1,int y1,int x2,int y2){
        int distanceX = (x1+x2)/2+9;
        int distanceY = (y1+y2)/2+9;
        double[] distances = {distanceX, distanceY};
        return distances;
    }
}