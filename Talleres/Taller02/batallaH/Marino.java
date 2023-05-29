import javax.swing.JOptionPane;
public class Marino extends Elemento{
    private String nombre;
    private int rango;
    @Override
    public void autoDestruirse(){
        destruir();
        JOptionPane.showMessageDialog(null, "Marino autodestruido por instruccion recibida");
    }
}
