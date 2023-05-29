import java.util.ArrayList;
public class PortaAviones extends Barco{
    private int capacidad;
    private ArrayList<Avion> aviones;
    public ArrayList<Avion> getAviones(){
        return aviones;
    }
    @Override
    public boolean maquinaDebil(){
        boolean maquinaDebil = super.maquinaDebil();
        for(int i = 0; i < aviones.size() && !maquinaDebil; i++){
            if (aviones.get(i).maquinaDebil() && aviones.get(i).getEnAire()) maquinaDebil = true;
        }
        return maquinaDebil;
    }
}
