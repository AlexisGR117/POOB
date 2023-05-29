import java.util.*;
import javax.swing.JOptionPane;
public class Flota {
    private Tablero tablero;
    private String nombre;
    private ArrayList<Marino> marinos;
    private ArrayList<Maquina> maquinas;
    private ArrayList<Elemento> elementosAutoDestruidos;
    public void avance(int dLon, int dLat) throws BatallaNavalExcepcion{
        for(Maquina m:maquinas) m.avance(dLon, dLat);
    }
    public ArrayList<Maquina> seranDestruidas(int longitud, int latitud){
        ArrayList<Maquina> seranDestruidas = new ArrayList<Maquina>();
        for (Maquina m:maquinas){
            if (m.seraDestruida(longitud, latitud)) seranDestruidas.add(m);
        }
        return seranDestruidas;
    }
    public ArrayList<Maquina> maquinasDebiles(){
        ArrayList<Maquina> maquinasDebiles = new ArrayList<Maquina>();
        for (Maquina m:maquinas){
            if (m.maquinaDebil()) maquinasDebiles.add(m);
        }
        return maquinasDebiles;
    }
    public void ataquen(int lon, int lat) throws BatallaNavalExcepcion{
        ArrayList<Maquina> debiles = maquinasDebiles();
        for (Maquina m:maquinas){
            if (!debiles.contains(m)){
                m.avance(lon, lat);
            }
        }
    }   
    public void autoDestruir(Barco barco){
        if (maquinas.contains(barco)){
            barco.autoDestruirse();
            elementosAutoDestruidos.add(barco);     
        }
    } 
    public void autoDestruir(Marino marino){
        if (maquinas.contains(marino)){
            marino.autoDestruirse();
            elementosAutoDestruidos.add(marino);
        }
    }
    public void alNorte(){
        boolean mover = true;
        try{
            for(Maquina m:maquinas) m.avance(0, 1);
        } catch (BatallaNavalExcepcion e){
            System.out.println(e.getMessage());
        }
    }
}
