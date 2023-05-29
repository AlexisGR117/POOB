public class Avion extends Maquina{
    private String placa;
    private boolean enAire;
    private Marino piloto;
    private Marino copiloto;
    public boolean getEnAire(){
        return enAire;
    }
    public Marino getPiloto(){
        return piloto;
    }
    @Override
    public boolean seraDestruida(int longitud, int latitud){
        boolean seraDestruida = false;
        if (!enAire && longitud == getUbicacion().getLongitud() && latitud == getUbicacion().getLatitud()) seraDestruida = true;
        return seraDestruida;
    }
    @Override
    public boolean maquinaDebil(){
        return piloto == null;
    }
    @Override
    public void autoDestruirse(){
        destruir();
    }
}
