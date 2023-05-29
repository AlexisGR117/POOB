 

public class Ubicacion {
    private int longitud;
    private int latitud;
    public void setLongitud(int longitud) throws BatallaNavalExcepcion{
        if (0 <= longitud && longitud <= 180){
            this.longitud = longitud;
        } else {
            throw new BatallaNavalExcepcion(BatallaNavalExcepcion.UBICACION_ERROR);
        }
    }
    public void setLatitud(int latitud) throws BatallaNavalExcepcion{
        if (-90 <= latitud && latitud <= 90){
            this.latitud = latitud;
        } else {
            throw new BatallaNavalExcepcion(BatallaNavalExcepcion.UBICACION_ERROR);
        }
    }
    public int getLongitud(){
        return longitud;
    }
    public int getLatitud(){
        return longitud;
    }
}
