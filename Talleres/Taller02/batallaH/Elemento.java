public abstract class Elemento{
    private boolean destruido;
    public abstract void autoDestruirse();
    public void destruir(){
        destruido = true;
    }
    public boolean estaDestruido(){
        return destruido;
    }
}
