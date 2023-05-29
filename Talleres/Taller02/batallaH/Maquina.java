 public abstract class Maquina extends Elemento{
    private Ubicacion ubicacion;
    public void avance(int dLon, int dLat) throws BatallaNavalExcepcion{
        ubicacion.setLongitud(dLon+ubicacion.getLongitud());
        ubicacion.setLatitud(dLat+ubicacion.getLatitud());
    }
    public Ubicacion getUbicacion(){
        return ubicacion;
    }
    public abstract boolean seraDestruida(int longitud, int latitud);
    public abstract boolean maquinaDebil();
}
