package presentation;

import java.awt.*;
import javax.swing.*;

/**
 * JPanel con una imágen de fondo.
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (19/11/2022)
 */
class Fondo extends JPanel {
	
	String nombreImagen;
	
	/**
	 * Constructor para objetos de clase Fondo.
	 * @param nombreImagen Nombre de la igagen que está en la carpeta imagenes.
	 */
	public Fondo(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}
	
	@Override
	public void paint(Graphics g) {
		Image f = new ImageIcon(getClass().getResource("Imagenes/"+nombreImagen+".png")).getImage();
		Image fondo = new ImageIcon(f.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)).getImage();
		g.drawImage(fondo, 0, 0, this);
		setOpaque(false);
		super.paint(g);
	}
}