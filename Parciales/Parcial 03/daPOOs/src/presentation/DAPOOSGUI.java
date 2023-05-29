package presentation;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import domain.*;

/**
 * Interfaz gráfica de DAPOOS.
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (19/11/2022)
 */
public class DAPOOSGUI extends JFrame{
	
	JButton nuevo, abrir, opciones, salir, ayuda;
	
	/*
	 * Constructor para objetos de calse DAPOOSGUI.
	 */
	private DAPOOSGUI() {
		super("Inicio daPOOs");
		prepararElementos();
		prepararAcciones();
	}
	
    /*
     * Prepara los elementos con los que el usuario interactua.
     */
    private void prepararElementos() {   
    	Fondo fondo = new Fondo("Inicio");
    	setSize(1050, 715);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);
        int x = (int)((double)getWidth()/(double)2)-140;
        int y = (int)(getHeight()*0.4);
        Font fuente = new Font("Perpetua", 0, 30);
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        LineBorder borde = new LineBorder(Color.black, 2);
        fondo.setLayout(null);
        nuevo = new JButton("Nuevo Juego");
        nuevo.setBounds(x, y, 260, 45);
        nuevo.setCursor(cursor);
        nuevo.setBackground(Color.white);
        nuevo.setBorder(borde);
        nuevo.setFont(fuente);
        abrir = new JButton("Abrir Juego");
        abrir.setBounds(x, y+55, 260, 45);
        abrir.setCursor(cursor);
        abrir.setBackground(Color.white);
        abrir.setBorder(borde);
        abrir.setFont(fuente);
        opciones = new JButton("Opciones");
        opciones.setBounds(x, y+110, 260, 45);
        opciones.setCursor(cursor);
        opciones.setBackground(Color.white);
        opciones.setBorder(borde);
        opciones.setFont(fuente);
        salir = new JButton("Salir");
        salir.setBounds(x, y+165, 260, 45);
        salir.setCursor(cursor);
        salir.setBackground(Color.white);
        salir.setBorder(borde);
        salir.setFont(fuente);
        ayuda = new JButton("Ayuda");
        ayuda.setBounds(x, y+220, 260, 45);
        ayuda.setCursor(cursor);
        ayuda.setBackground(Color.white);
        ayuda.setBorder(borde);
        ayuda.setFont(fuente);
        fondo.add(nuevo);
        fondo.add(abrir);
        fondo.add(salir);
        fondo.add(opciones);
        fondo.add(ayuda);  
        add(fondo);
    } 
    
    
    /*
     * Prepara los acciones de DAPOOSGUI.
     */
    private void prepararAcciones(){   
    	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                accionSalir();
            }
        });
        nuevo.addActionListener(e -> accionNuevo());
        salir.addActionListener(e -> accionSalir());
        abrir.addActionListener(e -> accionAbrir());
        opciones.addActionListener(e -> accionOpciones());
        ayuda.addActionListener(e -> accionAyuda());
    } 
    
    /*
     * Salir de DAPOOSGUI.
     */
    private void accionSalir() {   
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres salir del juego?", "Salir del juego", JOptionPane.OK_CANCEL_OPTION);
        if (respuesta == JOptionPane.OK_OPTION){
            setVisible(false);
            System.exit(0);
        }
    } 
    
    /*
     * Abre un nuevo juego de DAPOOS.
     */
    private void accionNuevo() {   
        new OpcionesJuego(this);
    } 
    
    /*
     * Abre un juego de dapoos desde un archivo.
     */
    private void accionAbrir() {   
    	try {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".dat", "dat");
            chooser.setDialogTitle("Abrir AManufacturing");
            chooser.setFileFilter(filter);
            int open = chooser.showOpenDialog(this);
            if (open == JFileChooser.APPROVE_OPTION) {
            	DAPOOS dapoos = DAPOOS.abra(chooser.getSelectedFile());
            	new Juego(dapoos, this);
            }    		
		} catch (DAPOOSException e) { 
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (Exception e) {
			Log.record(e);
			JOptionPane.showMessageDialog(null,"Ha ocurrido un error inesperado.");
		}
    }
    
    /*
     * Abre las opciones de DAPOOS.
     */
    private void accionOpciones() {   
    	JOptionPane.showMessageDialog(null, "La funcionalidad de opciones está en construcción.");
    } 
    
    /*
     * Abre la yuda del juego DAPOOS.
     */
    private void accionAyuda() {   
    	JOptionPane.showMessageDialog(null, "La funcionalidad de ayuda está en construcción.");
    } 
    
	 /**
	 * Método principal de DAPOOS.
	 */
	public static void main(String[] args) {
		DAPOOSGUI gui = new DAPOOSGUI();
	    gui.setVisible(true);
	} 
}

