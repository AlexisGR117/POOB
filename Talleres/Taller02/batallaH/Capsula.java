import javax.swing.JOptionPane;
public class Capsula extends Maquina{
    private Barco maquinaNodrizaB;
    private Capsula maquinaNodrizaC;    
    @Override
    public boolean seraDestruida(int longitud, int latitud){
        return false;
    }
    @Override
    public boolean maquinaDebil(){
        return false;
    }
    @Override
    public void autoDestruirse(){
        if(maquinaNodrizaB.estaDestruido() || maquinaNodrizaC.estaDestruido()){
            destruir();
            JOptionPane.showMessageDialog(null, "Se autodestruyó la capsula, su maquina nodriza fue destruida ");
        }
    }
}
