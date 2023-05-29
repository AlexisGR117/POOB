import java.util.ArrayList;
import javax.swing.JOptionPane;
public class Barco extends Maquina{
    private int numero;
    private ArrayList<Marino> marinos;
    public ArrayList<Marino> getMarinos(){
        return marinos;
    }
    @Override
    public boolean seraDestruida(int longitud, int latitud){
        boolean seraDestruida = false;
        if (longitud == getUbicacion().getLongitud() && latitud == getUbicacion().getLatitud()) seraDestruida = true;
        return seraDestruida;
    }
    @Override
    public boolean maquinaDebil(){
        return marinos.size() < 5;
    }
    @Override
    public void autoDestruirse(){
        destruir();
        JOptionPane.showMessageDialog(null, "Barco autodestruido por instruccion recibida");
    }
}
