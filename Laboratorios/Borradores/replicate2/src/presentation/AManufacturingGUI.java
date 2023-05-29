package presentation;
import domain.*;

import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * Interfaz gráfica de AManufacturing.
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (26/11/2022)
 */
public class AManufacturingGUI extends JFrame{  
	
    public static final int SIDE=11;
    public final int SIZE;
    private JButton ticTacButton;
    private JPanel  controlPanel;
    private JMenuItem newR, open, saveAs, importR, export, exit;
    private PhotoAManufacturing photo;
    private AManufacturing aManufacturing;
   
    /*
     * Constructor para objetos de clase AManufacturingGUI.
     */
    private AManufacturingGUI() {
        aManufacturing=new AManufacturing();
        SIZE=aManufacturing.getSize();
        prepareElementos();
        prepareAcciones();
    }
    
    /*
     * PRepara los elementos del AManufacturing.
     */
    private void prepareElementos() {
        setTitle("aManufacturing celular");
        photo=new PhotoAManufacturing(this);
        ticTacButton=new JButton("Tic-tac");
        setLayout(new BorderLayout());
        add(photo,BorderLayout.NORTH);
        add(ticTacButton,BorderLayout.SOUTH);
        setSize(new Dimension(SIDE*SIZE+15,SIDE*SIZE+72)); 
        setResizable(false);
        photo.repaint();
        prepareElementosMenu();
    }

    /*
     * Prepara los elementos del menú.
     */
    private void prepareElementosMenu() {   
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Menú");
        newR = new JMenuItem("Nuevo");
        open = new JMenuItem("Abrir");
        saveAs = new JMenuItem("Guardar Como");
        importR = new JMenuItem("Importar");
        export = new JMenuItem("Exportar como");
        exit = new JMenuItem("Salir");
        bar.add(menu);
        menu.add(newR);
        menu.add(new JSeparator());
        menu.add(open);
        menu.add(saveAs);
        menu.add(new JSeparator());
        menu.add(importR);
        menu.add(export);
        menu.add(new JSeparator());
        menu.add(exit);
        setJMenuBar(bar);
    } 
    
    /*
     * Prepara las acciones del AManufacturing.
     */
    private void prepareAcciones(){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);       
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                opcionSalir();
            }
        });
        ticTacButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    ticTacButtonAction();
                }
            });
        prepareAccionesMenu();
    }

    /*
     * Prepara las acciones del menú.
     */
    private void prepareAccionesMenu() {   
    	newR.addActionListener(e -> opcionNuevo());
        open.addActionListener(e -> opcionAbrir());
        saveAs.addActionListener(e -> opcionGuardar());
        importR.addActionListener(e -> opcionImportar());
        export.addActionListener(e -> opcionExportar());
        exit.addActionListener(e -> opcionSalir());
    }
    
    /*
     * Crea un nuevo AManufacturing.
     */
    private void opcionNuevo() {   
    	aManufacturing = new AManufacturing();
    	photo.repaint();
    }
    
    /*
     * Abre un AManufacturing.
     */
    private void opcionAbrir() {   
    	try {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".dat", "dat");
            chooser.setDialogTitle("Abrir AManufacturing");
            chooser.setFileFilter(filter);
            int open = chooser.showOpenDialog(this);
            if (open == JFileChooser.APPROVE_OPTION) {
            	AManufacturing aManufacturing = AManufacturing.abra(chooser.getSelectedFile());
            	this.aManufacturing = aManufacturing;
            	photo.repaint();
            }    		
		} catch (ReplicateException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
    }
    
    /*
     * Guarda un AManufacturing.
     */
    private void opcionGuardar() {   
    	try {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".dat", "dat");
            chooser.setDialogTitle("Guardar AManufacturing");
            chooser.setFileFilter(filter);
            int save = chooser.showSaveDialog(this);
            if (save == JFileChooser.APPROVE_OPTION) {
                aManufacturing.guarde(chooser.getSelectedFile());
            }
		} catch (ReplicateException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
    }
    
    /*
     * Importa un AManufacturing.
     */
    private void opcionImportar() {  
    	try {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
            chooser.setDialogTitle("Importar AManufacturing");
            chooser.setFileFilter(filter);
            int open = chooser.showOpenDialog(this);
            if (open == JFileChooser.APPROVE_OPTION) {
            	AManufacturing aManufacturing = AManufacturing.importe(chooser.getSelectedFile());
            	this.aManufacturing = aManufacturing;
            	photo.repaint();
            }    		
		} catch (ReplicateException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
    }
    
    /*
     * Exporta un AManufacturing.
     */
    private void opcionExportar() {   
    	try {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
            chooser.setDialogTitle("Exportar AManufacturing");
            chooser.setFileFilter(filter);
            int save = chooser.showSaveDialog(this);
            if (save == JFileChooser.APPROVE_OPTION) {
            	aManufacturing.exporte(chooser.getSelectedFile());
            }
		} catch (ReplicateException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
    }
    
    /*
     * Sale del AManufacturing.
     */
    private void opcionSalir() {   
        int answer = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres salir?", "Salir del AManufacturing", JOptionPane.OK_CANCEL_OPTION);
        if (answer == JOptionPane.OK_OPTION){
            setVisible(false);
            System.exit(0);
        }
    }    
    
    private void ticTacButtonAction() {
        aManufacturing.ticTac();
        photo.repaint();
    }

    public AManufacturing getaManufacturing(){
        return aManufacturing;
    }
    
    public static void main(String[] args) {
        AManufacturingGUI ca=new AManufacturingGUI();
        ca.setVisible(true);
    }  
}

class PhotoAManufacturing extends JPanel{
    private AManufacturingGUI gui;

    public PhotoAManufacturing(AManufacturingGUI gui) {
        this.gui=gui;
        setBackground(Color.white);
        setPreferredSize(new Dimension(gui.SIDE*gui.SIZE+10, gui.SIDE*gui.SIZE+10));         
    }

    public void paintComponent(Graphics g){
        AManufacturing aManufacturing=gui.getaManufacturing();
        super.paintComponent(g);
         
        for (int c=0;c<=aManufacturing.getSize();c++){
            g.drawLine(c*gui.SIDE,0,c*gui.SIDE,aManufacturing.getSize()*gui.SIDE);
        }
        for (int f=0;f<=aManufacturing.getSize();f++){
            g.drawLine(0,f*gui.SIDE,aManufacturing.getSize()*gui.SIDE,f*gui.SIDE);
        }       
        for (int f=0;f<aManufacturing.getSize();f++){
            for(int c=0;c<aManufacturing.getSize();c++){
                if (aManufacturing.getThing(f,c)!=null){
                    g.setColor(aManufacturing.getThing(f,c).getColor());
                    if (aManufacturing.getThing(f,c).shape()==Thing.SQUARE){                  
                        if (aManufacturing.getThing(f,c).isActive()){
                            g.fillRoundRect(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2,2,2);
                        }else{
                            g.drawRoundRect(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2,2,2);    
                        }
                    }else {
                        if (aManufacturing.getThing(f,c).isActive()){
                            g.fillOval(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2);
                        } else {
                            g.drawOval(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2);
                        }
                    }
                }
            }
        }
    }
}