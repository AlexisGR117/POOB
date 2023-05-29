package presentation;

import domain.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

/**
 * Interfaz gráfica de una partida del juego DAPOOS.
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (19/11/2022)
 */
public class Juego extends JFrame {
	
    private JMenuItem nuevo, abrir, guardar, salir;
    private JPanel tableroJuego, informacion, jugadorUno, jugadorDos, casillas2, panelS;
    private JLabel nombreUno, colorUno, movimientosUno, fichasUno, capturasUno, especialesUno, comodinesUno, turno;  
    private JLabel nombreDos, colorDos, movimientosDos, fichasDos, capturasDos, especialesDos, comodinesDos, labelTimer;    
    private PanelCasilla[][] tablero;
    private Fondo fondo;
	private DAPOOS dapoos;
	private DAPOOSGUI gui;
	private String nombreNorte, nombreSur, colorNorte, colorSur, visualizacion, dificultad, tipoMaquina;
	private int tiempo, porcentaje;
	private Timer timer, timerRelámpago;
	private boolean fichasActivas, comodinesActivos;
	
	/**
	 * Constructor para objetos de clase juego.
	 * @param nombreUno Nombre del jugador Norte.
	 * @param nombreDos Nombre del jugador Sur.
	 * @param colorNorte Color del jugador Norte.
	 * @param colorSur Color del jugador Sur.
	 * @param tipoMaquina Tipo de máquina que va a jugar contra el usuario.
	 * @param porcentaje Porcentaje de casillas especiales en el tablero.
	 * @param visualizacion Tipo de visualización de las casillas especiales puede ser: "Relámpago" o "Permanente".
	 * @param tiempo Si la dificultad del juego es "QuickTime", el tiempo es lo que dura cada turno.
	 * @param dificultad Dificultad del juego puede ser: "QuickTime" o "Nomral"
	 * @param gui Pantalla inial del juego
	 * @throws DAPOOSException	LONG_NAME, si alguno de los nombres tiene longitud mayor a 14.
	 * 						   	SHORT_NAME, si alguno de los nombres tiene longitud menor a 3.
	 * 							SAME_NAME, si los nombres de los juagdores son iguales.
	 * 							SAME_COLORS, si los colres de los jugadores son iguales.
	 * 							INVALID_PERCENTAGE, si el porcentage no es mayor a cero y menor que 100.
	 * 							INVALID_DIFFICULTY, si la dificultad dad no esta entre las establecidas que son "Normal" y "QuickTime".
	 * 							INVALID_TIME_TURN, si el modo es "QuickTime" y el tiempo de cada turno no está entre 0 y 100. 
	 */
	public Juego(String nombreUno,String nombreDos,String colorNorte,String colorSur,String tipoMaquina,int porcentaje,boolean comodinesActivos, 
				 boolean fichasActivas,String visualizacion,int tiempo,String dificultad, DAPOOSGUI gui) throws DAPOOSException {
		if (tipoMaquina == null && dificultad.equals("Normal")) {
			dapoos = new DAPOOS(nombreUno, nombreDos, colorNorte, colorSur, comodinesActivos, fichasActivas, porcentaje, visualizacion);
		} else if (tipoMaquina != null && dificultad.equals("Normal")) {
			dapoos = new DAPOOS(nombreUno, nombreDos, colorNorte, colorSur, tipoMaquina, comodinesActivos, fichasActivas, porcentaje, visualizacion);
		} else if (tipoMaquina == null && !dificultad.equals("Normal")) {
			dapoos = new DAPOOS(nombreUno, nombreDos, colorNorte, colorSur, comodinesActivos, fichasActivas, porcentaje, visualizacion, dificultad, tiempo);
		} else 	dapoos = new DAPOOS(nombreUno, nombreDos, colorNorte, colorSur, tipoMaquina, comodinesActivos, fichasActivas, porcentaje, visualizacion, dificultad, tiempo);
        this.gui = gui;
        this.nombreNorte = nombreUno;
        this.nombreSur = nombreDos;
		this.dificultad = dificultad;
		this.colorNorte = colorNorte;
		this.colorSur = colorSur;
		this.porcentaje = porcentaje;
		this.visualizacion = visualizacion;
		this.tiempo = tiempo;
		this.tipoMaquina = tipoMaquina;
		this.comodinesActivos = comodinesActivos;
		this.fichasActivas = fichasActivas;
		prepararElementos();
        prepararAcciones();
        setVisible(true);
        
	}
	
	/**
	 * Constructor para objetos de clase juego.poos
	 * @param dapood Juego de DAPOOS.
	 * @param gui Pantalla inial del juego
	 */
	public Juego(DAPOOS dapoos, DAPOOSGUI gui) {
		this.dapoos = dapoos;
        this.gui = gui;
        this.nombreNorte = dapoos.jugadorNorte().nombre();
        this.nombreSur = dapoos.jugadorSur().nombre();
		this.dificultad = dapoos.dificultad();
		this.colorNorte = dapoos.jugadorNorte().color();
		this.colorSur = dapoos.jugadorSur().color();
		this.porcentaje = dapoos.porcentaje();
		this.visualizacion = dapoos.visualizacion();
		this.tiempo = dapoos.tiempo();
		this.tipoMaquina = dapoos.tipoMaquina();
		this.comodinesActivos = dapoos.comodinesActivos();
		this.fichasActivas = dapoos.fichasActivas();
		prepararElementos();
		prepararAcciones();
		setVisible(true);

}
	
	/*
     * Prepara los elementos del juego.
     */
    private void prepararElementos() {   
    	if (dificultad.equals("QuickTime")) setSize(1125, 690);
    	else  setSize(1125, 675);
        setLayout(new GridLayout(1, 1));
        setLocationRelativeTo(null);
        setResizable(false);
        fondo = new Fondo("FondoBlanco");
        add(fondo);
        fondo.setVisible(true);
        fondo.setLayout(new BorderLayout());  
        prepararElementosMenu();
        prepararElementosTablero();
        prepararElementosjugadorUno();
        prepararElementosJugadorDos();
        prepararElementosInformacion();        
    } 
    
    /*
     * Prepara los elementos del menú.
     */
    private void prepararElementosMenu() {   
        JMenuBar barra = new JMenuBar();
        JMenu menu = new JMenu("Menú"), setting = new JMenu("Configuración");
        nuevo = new JMenuItem("Nuevo");
        abrir = new JMenuItem("Abrir");
        guardar = new JMenuItem("Salvar");
        salir = new JMenuItem("Salir");
        barra.add(menu);
        barra.add(setting);
        menu.add(nuevo);
        menu.add(new JSeparator());
        menu.add(abrir);
        menu.add(guardar);
        menu.add(new JSeparator());
        menu.add(salir);
        setJMenuBar(barra);
    } 
    
    /*
     * Prepara los elementos del tablero de juego.
     */
    private void prepararElementosTablero() {  
        int tamano = Tablero.TAMANOLADO;
        JPanel panelC = new JPanel();
        panelC.setLayout(null);
        panelC.setOpaque(false);
        tableroJuego = new JPanel();
        tableroJuego.setBackground(Color.white);
        tableroJuego.setBorder(new LineBorder(Color.black, 3)); 
        tablero = new PanelCasilla[tamano][tamano];
        tableroJuego.setLayout(null);
        tableroJuego.setBounds(10, 10, 550, 550);
        JPanel casillas = new JPanel();
        casillas2 = new JPanel();
        casillas.setOpaque(false);
        casillas2.setOpaque(false);
        casillas.setLayout(null);
        casillas.setBounds(20, 20, 510, 510);
        casillas.setBorder(new LineBorder(Color.black, 3));   
        casillas2.setLayout(new GridLayout(tamano, tamano));
        casillas2.setBounds(5, 5, 500, 500);
        casillas2.setBorder(new LineBorder(Color.black, 2));   
        tableroJuego.add(casillas);
        casillas.add(casillas2);
        prepararElementosCasillas();
    	if (visualizacion.equals("Relámpago")) timerRelámpago = new Timer();
    	panelC.add(tableroJuego);
        fondo.add(panelC, BorderLayout.CENTER);
    }  
    
    /*
     * Prepara las casillas del tablero de juego.
     */
    private void prepararElementosCasillas() {
    	int tamano = Tablero.TAMANOLADO;
        for (int i = 0; i < tamano; i++) {
        	char l = (char)(i+65);
        	JLabel fila = new JLabel((tamano-i)+"", SwingConstants.CENTER), columna = new JLabel(l+"");
            fila.setBounds(5, 25+(500/(tamano*2))+(500/tamano)*i-i, 15, 15);
            columna.setBounds(25+(500/(tamano*2))+(500/tamano)*i-i, 8, 10, 10);
            tableroJuego.add(fila);
            tableroJuego.add(columna);
        	JLabel fila2 = new JLabel((tamano-i)+"", SwingConstants.CENTER), columna2 = new JLabel(l+"");
            fila2.setBounds(530, 25+(500/(tamano*2))+(500/tamano)*i-i, 15, 15);
            columna2.setBounds(25+(500/(tamano*2))+(500/tamano)*i-i, 535, 10, 10);
            tableroJuego.add(fila2);
            tableroJuego.add(columna2);
            for (int j = 0; j < tamano; j++) {
            	PanelCasilla casilla;
                if((i%2 == 0 && j%2 != 0) || (i%2 != 0 && j%2 == 0)) {
                	JButton boton = new JButton();
                    boton.setOpaque(false);
                    boton.setBorderPainted(false);
                    boton.setBackground(Tablero.COLORDOS);
                    boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    casilla = new PanelCasilla(i, j, this, boton);
                    casilla.add(boton);
                    casilla.setLayout(new GridLayout(1, 1));
                	casilla.setBackground(Tablero.COLORDOS);
                } else {
                	casilla = new PanelCasilla(i, j, this, null);
                	casilla.setBackground(Tablero.COLORUNO);
                }                  
                casillas2.add(casilla);
                tablero[i][j] = casilla;
            }
        }
    }  
    
    /*
     * Prepara los elementos del jugador Norte.
     */
    private void prepararElementosjugadorUno() {  
    	JPanel panelW = new JPanel();
        panelW.setOpaque(false);
    	panelW.setLayout(new GridLayout(3, 1));
    	JPanel uno = new JPanel();
    	uno.setOpaque(false);
    	panelW.add(uno);
        jugadorUno = new JPanel();
        jugadorUno.setBackground(Color.white);
        //jugadorUno.setOpaque(false);
        jugadorUno.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder(new LineBorder(Color.black), "Jugador Norte")));
        jugadorUno.setLayout(new GridLayout(7, 2));
        jugadorUno.add(new JLabel("Nombre:"));
        nombreUno = new JLabel(dapoos.jugadorNorte().nombre(), SwingConstants.CENTER);
        jugadorUno.add(nombreUno);
        jugadorUno.add(new JLabel("Color:"));
        colorUno = new JLabel(dapoos.jugadorNorte().color(), SwingConstants.CENTER);
        jugadorUno.add(colorUno);
        jugadorUno.add(new JLabel("Movimientos:"));
        movimientosUno = new JLabel(dapoos.jugadorNorte().movimientos()+"", SwingConstants.CENTER);
        jugadorUno.add(movimientosUno);
        jugadorUno.add(new JLabel("Fichas:"));
        fichasUno = new JLabel(dapoos.jugadorNorte().numeroFichas()+"", SwingConstants.CENTER);
        jugadorUno.add(fichasUno);
        jugadorUno.add(new JLabel("Fichas capturadas:"));
        capturasUno = new JLabel(20-dapoos.jugadorSur().numeroFichas()+"", SwingConstants.CENTER);
        jugadorUno.add(capturasUno);
        jugadorUno.add(new JLabel("Fichas Especiales:"));
        especialesUno = new JLabel(dapoos.jugadorNorte().numeroFichasEspeciales()+"", SwingConstants.CENTER);
        jugadorUno.add(especialesUno);
	    jugadorUno.add(new JLabel("Comodines activados:"));
	    comodinesUno = new JLabel(dapoos.jugadorNorte().comodinesActivados()+"", SwingConstants.CENTER);
	    jugadorUno.add(comodinesUno);
        panelW.add(jugadorUno);
        fondo.add(panelW, BorderLayout.WEST);
    }   
    
    /*
     * Prepara los elementos del jugador sur.
     */
    private void prepararElementosJugadorDos() {  
    	JPanel panelE = new JPanel();
        panelE.setOpaque(false);
    	panelE.setLayout(new GridLayout(3, 1));
    	JPanel uno = new JPanel();
    	uno.setOpaque(false);
    	panelE.add(uno);
        jugadorDos = new JPanel();
        //jugadorDos.setOpaque(false);
        jugadorDos.setBackground(Color.white);
        jugadorDos.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder(new LineBorder(Color.black), "Jugador Sur")));
        jugadorDos.setLayout(new GridLayout(7, 2));
        jugadorDos.add(new JLabel("Nombre:"));
        nombreDos = new JLabel(dapoos.jugadorSur().nombre(), SwingConstants.CENTER);
        jugadorDos.add(nombreDos);
        jugadorDos.add(new JLabel("Color:"));
        colorDos = new JLabel(dapoos.jugadorSur().color(), SwingConstants.CENTER);
        jugadorDos.add(colorDos);
        jugadorDos.add(new JLabel("Movimientos:"));
        movimientosDos = new JLabel(dapoos.jugadorSur().movimientos()+"", SwingConstants.CENTER);
        jugadorDos.add(movimientosDos);
        jugadorDos.add(new JLabel("Fichas:"));
        fichasDos = new JLabel(dapoos.jugadorSur().numeroFichas()+"", SwingConstants.CENTER);
        jugadorDos.add(fichasDos);
        jugadorDos.add(new JLabel("Fichas capturadas:"));
        capturasDos = new JLabel(20-dapoos.jugadorNorte().numeroFichas()+"", SwingConstants.CENTER);
        jugadorDos.add(capturasDos);
        jugadorDos.add(new JLabel("Fichas Especiales:"));
        especialesDos = new JLabel(dapoos.jugadorSur().numeroFichasEspeciales()+"", SwingConstants.CENTER);
        jugadorDos.add(especialesDos);
        jugadorDos.add(new JLabel("Comodines activados:"));
        comodinesDos = new JLabel(dapoos.jugadorSur().comodinesActivados()+"", SwingConstants.CENTER);
        jugadorDos.add(comodinesDos);        	
        panelE.add(jugadorDos);
        fondo.add(panelE, BorderLayout.EAST);
    }   
    
    /*
     * Prepara la información del juego.
     */
    private void prepararElementosInformacion() {  
    	panelS = new JPanel();
    	panelS.setOpaque(false);
    	panelS.setLayout(new GridLayout(1, 3));
    	JPanel uno = new JPanel();
    	uno.setOpaque(false);
    	panelS.add(uno);
        informacion = new JPanel();
        informacion.setBackground(Color.white);
        //informacion.setOpaque(false);
        informacion.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder(new LineBorder(Color.black), "Información")));
        if (dificultad.equals("QuickTime")) informacion.setLayout(new GridLayout(2, 2));
        else informacion.setLayout(new GridLayout(1, 2));
        informacion.add(new JLabel("Turno:"));
        turno = new JLabel(dapoos.turno().nombre(), SwingConstants.CENTER);
        informacion.add(turno);
        panelS.add(informacion);
    	JPanel tres = new JPanel();
    	tres.setOpaque(false);
    	panelS.add(tres);
    	labelTimer = new JLabel(dapoos.segundos()+"", SwingConstants.CENTER);
    	timer = new Timer();
    	if (dificultad.equals("QuickTime")) {
        	informacion.add(new JLabel("Tiempo restante:"));
        	informacion.add(labelTimer);
    	}
    	fondo.add(panelS, BorderLayout.SOUTH);
    }   
    
    /*
     * Prepara las acciones del juego.
     */
    private void prepararAcciones() {   
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                accionSalir();
            }
        });
        prepararAccionesTablero();
        prepararAccionesMenu();
    } 
    
    /*
     * Prepara las acciones del menú.
     */
    private void prepararAccionesMenu() {   
        nuevo.addActionListener(e -> accionNuevo());
        abrir.addActionListener(e -> accionAbrir());
        guardar.addActionListener(e -> accionGuardar());
        salir.addActionListener(e -> accionSalir());
    }  
    
    /*
     * Sale del juego a la pantalla incial.
     */
    private void accionSalir() {   
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres salir del juego?", "Salir del juego", JOptionPane.OK_CANCEL_OPTION);
        if (respuesta == JOptionPane.OK_OPTION) {
            dispose();
            gui.setVisible(true);
        }
    }
    
    /*
     * Reinicia la partida, comenzando una nueva.
     */
    private void accionNuevo() {   
    	int respuesta = JOptionPane.showConfirmDialog(null, "¿Quieres guardar la partida actual?", "Nueva partida", JOptionPane.YES_NO_CANCEL_OPTION);
        if (respuesta == JOptionPane.YES_OPTION){
        	int respuesta2 = accionGuardar();
        	if (respuesta2 == JFileChooser.APPROVE_OPTION) {
        		reiniciar();
        	}
        } else if (respuesta == JOptionPane.NO_OPTION){
        	reiniciar();
        }
    }
    
    
    /*
     * Abre una partidad de una rchivo.
     */
    private void accionAbrir() {   
    	int respuesta = JOptionPane.showConfirmDialog(null, "¿Quieres guardar la partida actual?", "Abrir partida", JOptionPane.YES_NO_CANCEL_OPTION);
        int respuesta2 = JFileChooser.APPROVE_OPTION;
    	if (respuesta == JOptionPane.YES_OPTION) respuesta2 = accionGuardar();
        if (respuesta == JOptionPane.NO_OPTION || (respuesta == JOptionPane.YES_OPTION && respuesta2 == JFileChooser.APPROVE_OPTION)) {
        	try {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(".dat", "dat");
                chooser.setDialogTitle("Abrir AManufacturing");
                chooser.setFileFilter(filter);
                int open = chooser.showOpenDialog(this);
                if (open == JFileChooser.APPROVE_OPTION) {
                	dapoos = DAPOOS.abra(chooser.getSelectedFile());
                    nombreNorte = dapoos.jugadorNorte().nombre();
                    nombreSur = dapoos.jugadorSur().nombre();
            		dificultad = dapoos.dificultad();
            		colorNorte = dapoos.jugadorNorte().color();
            		colorSur = dapoos.jugadorSur().color();
            		porcentaje = dapoos.porcentaje();
            		visualizacion = dapoos.visualizacion();
            		tiempo = dapoos.tiempo();
            		tipoMaquina = dapoos.tipoMaquina();
            		comodinesActivos = dapoos.comodinesActivos();
            		fichasActivas = dapoos.fichasActivas();
                    nombreUno.setText(dapoos.jugadorNorte().nombre());
                    colorUno.setText(dapoos.jugadorNorte().color());
                    nombreDos.setText(dapoos.jugadorSur().nombre());
                    colorDos.setText(dapoos.jugadorSur().color());
                	if (dificultad.equals("QuickTime")) setSize(1125, 690);
                	else  setSize(1125, 675);
                    fondo.remove(panelS);
                	prepararElementosInformacion();
                	refrescar();
                }    		
    		} catch (DAPOOSException e) {
    			JOptionPane.showMessageDialog(null, e.getMessage());
    		} catch (Exception e) {
    			Log.record(e);
    			JOptionPane.showMessageDialog(null,"Ha ocurrido un error inesperado.");
    		}
        }
    }
    
    /*
     * Guarda la partida en un archivo.
     */
    private int accionGuardar() {   
    	int save = JFileChooser.CANCEL_OPTION;
    	try {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".dat", "dat");
            chooser.setDialogTitle("Guardar partida de DAPOOS");
            chooser.setFileFilter(filter);
            save = chooser.showSaveDialog(this);
            if (save == JFileChooser.APPROVE_OPTION) {
                dapoos.guarde(chooser.getSelectedFile());
            }
		} catch (DAPOOSException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}  catch (Exception e) {
			Log.record(e);
			JOptionPane.showMessageDialog(null,"Ha ocurrido un error inesperado.");
		}
        return save;
    }    
    
    /*
     * Prepara las acciones del tablero.
     */
    private void prepararAccionesTablero() {   
    	int tamano = Tablero.TAMANOLADO;
        for (int i = 0; i < tamano; i++) for (int j = 0; j < tamano; j++) {
        	if((i%2 == 0 && j%2 != 0) || (i%2 != 0 && j%2 == 0)) {
        		tablero[i][j].boton().addActionListener(e -> accionSeleccionar(e));
        	}
        }
	    timer.scheduleAtFixedRate(new TimerTask() {	
	        public void run() {
	        	if (dificultad.equals("QuickTime")) refrescarInformacion();
	        	if (dapoos.ganador() != null) {
	        		JOptionPane.showMessageDialog(null, "Ganó "+ dapoos.ganador().nombre()+".");
	        		reiniciar();
	        	}
	        }
	    }, 0, 1000);
        if (visualizacion.equals("Relámpago")) timerRelámpagoTask();
    } 
    
    private void timerRelámpagoTask() {
        timerRelámpago.scheduleAtFixedRate(new TimerTask() {	
        	int s = 5;
        	public void run() {
        		s--;
        		if (s < 0) {
        			for (Casilla[] f:dapoos.tablero().casillas()) for (Casilla c:f) {
        				if (c != null && !c.activo()) c.invisibilizar();
        			}
        			cancel();
        			refrescarCasillasEspeciales();
        		}
        	}
        }, 0, 1000);
    }
    
    /*
     * Selecciona una casilla del tablero de juego.
     */
    private void accionSeleccionar(ActionEvent e) {  
        PanelCasilla boton = (PanelCasilla )((JButton)e.getSource()).getParent();
        int r = boton.fila(), c = boton.columna();
        Tablero t = dapoos.tablero();
        if ((boton.estado() == 'p' || boton.estado() == 'e') && t.seleccion() != null) {
        	accionMover(r, c, t);
        } else if (t.ficha(r, c) != null) {
        	try {
        		if (t.comodinActivo() instanceof Stomp) {
     				t.seleccionar(t.ficha(r, c));
        			ArrayList<int[]> ep = dapoos.turno().posiblesEliminaciones();
    	            for (int[] p:ep) {
    	            	tablero[p[0]][p[1]].cambiarEstado('e');
    	                tablero[p[0]][p[1]].repaint();
    	            } 
        		} else {
        			t.seleccionar(t.ficha(r, c));
	        		refrescar();
	        		if (t.seleccion() != null) {
        			tablero[r][c].cambiarEstado('s');
        	            ArrayList<int[]> mp = t.seleccion().movimientosPosibles();
        	            for (int[] m:mp) {
        	            	tablero[m[0]][m[1]].cambiarEstado('p');
        	                tablero[m[0]][m[1]].repaint();
        	            }
            		}
        		}
	        } catch (DAPOOSException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
        	} catch (Exception ex) {
    			Log.record(ex);
    			JOptionPane.showMessageDialog(null,"Ha ocurrido un error inesperado.");
    		}	  
        }
    }
    
    /**
     * Mueve una ficha en el tablero de juego.
     */
    private void accionMover(int r, int c, Tablero t) {
    	try {
    		boolean m;
    		Ficha seleccion = t.seleccion();
    		if (((seleccion.lado() == 'n' && r == Tablero.TAMANOLADO - 1) ||
    			(seleccion.lado() == 's' && r == 0)) && seleccion instanceof Peon && dapoos.fichasActivas()) {
    			SeleccionFicha selector = new SeleccionFicha(this, seleccion.color());
        		m = t.moverSeleccion(r, c, selector.seleccion());  			
    		} else m = t.moverSeleccion(r, c);
    		if (m) JOptionPane.showMessageDialog(null, "Podía capturar, se ha capturado la ficha que no actuó.");
    		refrescar();
    		if (t.comodinActivo() instanceof Gun) {
    			ArrayList<int[]> ep = dapoos.turno().posiblesEliminaciones();
	            for (int[] p:ep) {
	            	tablero[p[0]][p[1]].cambiarEstado('e');
	                tablero[p[0]][p[1]].repaint();
	            }
    		}
    		if (t.comodinActivo() instanceof Infinity && t.ultimaSeleccion() instanceof Peon) {
    			SeleccionFicha selector = new SeleccionFicha(this, seleccion.color());
        		t.ultimaSeleccion().cambiarTipo(selector.seleccion());   
    		}
    		if (t.bloqueado()) {
    			ArrayList<int[]> mp = t.seleccion().movimientosPosibles();
                for (int[] mo:mp) {
                	tablero[mo[0]][mo[1]].cambiarEstado('p');
                	tablero[mo[0]][mo[1]].repaint();
                }
    		}
    	} catch (DAPOOSException ex) {
    		JOptionPane.showMessageDialog(null, ex.getMessage());
    	}  catch (Exception e) {
			Log.record(e);
			JOptionPane.showMessageDialog(null,"Ha ocurrido un error inesperado.");
		}
    }
    
    /*
     * Refresca la información del juego.
     */
    private void refrescarInformacion() {  
    	if (dapoos.segundos() == 0) refrescarTablero();
    	turno.setText(dapoos.turno()+"");
    	labelTimer.setText(dapoos.segundos()+"");  	
    } 
    
    /*
     * Refresca las casillas especiales.
     */
    private void refrescarCasillasEspeciales() {   
    	int tamano = Tablero.TAMANOLADO;
        for(int i = 0; i < tamano; i++) for(int j = 0; j < tamano; j++) {
            tablero[i][j].repaint();
        }
    }    
    
    /*
     * Refresca el tablero.
     */
    private void refrescarTablero() {   
    	int tamano = Tablero.TAMANOLADO;
        for(int i = 0; i < tamano; i++) for(int j = 0; j < tamano; j++) {
            tablero[i][j].cambiarEstado('v');   
            tablero[i][j].repaint();
        }
    } 
    
    /*
     * Refresca el juego.
     */
    private void refrescar() {   
    	movimientosUno.setText(dapoos.jugadorNorte().movimientos()+"");
    	movimientosDos.setText(dapoos.jugadorSur().movimientos()+"");
    	int numFichasNorte = dapoos.jugadorNorte().numeroFichas();
    	int numFichasSur = dapoos.jugadorSur().numeroFichas();
    	fichasUno.setText(numFichasNorte+"");
    	fichasDos.setText(numFichasSur+"");
    	capturasUno.setText(20-numFichasSur+"");
    	capturasDos.setText(20-numFichasNorte+"");
    	especialesUno.setText(dapoos.jugadorNorte().numeroFichasEspeciales()+"");
    	especialesDos.setText(dapoos.jugadorSur().numeroFichasEspeciales()+"");
    	comodinesUno.setText(dapoos.jugadorNorte().comodinesActivados()+"");
    	comodinesDos.setText(dapoos.jugadorSur().comodinesActivados()+"");
    	turno.setText(dapoos.turno().nombre());
        refrescarTablero();
    } 
    
    /*
     * Reinicia la partida.
     */
    private void reiniciar() {   
    	try {
    		if (tipoMaquina == null && dificultad.equals("Normal")) {
    			dapoos = new DAPOOS(nombreNorte, nombreSur, colorNorte, colorSur, comodinesActivos, fichasActivas, porcentaje, visualizacion);
    		} else if (tipoMaquina != null && dificultad.equals("Normal")) {
    			dapoos = new DAPOOS(nombreNorte, nombreSur, colorNorte, colorSur, tipoMaquina, comodinesActivos, fichasActivas, porcentaje, visualizacion);
    		} else if (tipoMaquina == null && !dificultad.equals("Normal")) {
    			dapoos = new DAPOOS(nombreNorte, nombreSur, colorNorte, colorSur, comodinesActivos, fichasActivas, porcentaje, visualizacion, dificultad, tiempo);
    		} else 	dapoos = new DAPOOS(nombreNorte, nombreSur, colorNorte, colorSur, tipoMaquina, comodinesActivos, fichasActivas, porcentaje, visualizacion, dificultad, tiempo);
    		if (visualizacion.equals("Relámpago")) {
    			timerRelámpago = new Timer();
    			timerRelámpagoTask();
    		}
			refrescar();   
		} catch (DAPOOSException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (Exception e) {
			Log.record(e);
			JOptionPane.showMessageDialog(null,"Ha ocurrido un error inesperado.");
		}
    }
    
    /**
     * Retorna el juego DAPOOS.
     * @return El juego DAPOOS.
     */
    public DAPOOS dapoos() {
    	return dapoos;
    }
    
}

/**
 * Panel de la casilla del tablero de juego de DAPOOS.
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (19/11/2022)
 */
class PanelCasilla extends JPanel {
	
    private JButton boton;
    private int c, f;
    private char estado;
    private Juego Juego;
    
    /**
     * Constructor para objetos de clase PanelCasilla.
     */
    public PanelCasilla(int f, int c, Juego Juego, JButton boton) {
        this.c = c;
        this.f= f;
        this.Juego = Juego;
        this.boton = boton;
    }
    
    @Override
    public void paint(Graphics g){
    	Casilla casilla = Juego.dapoos().tablero().casilla(f, c);
    	super.paint(g);
    	if (boton != null) {
    		Ficha ficha = casilla.ficha();
    		setBackground(Color.lightGray);
    		setBorder(null); 
    		Ficha seleccionada = Juego.dapoos().tablero().seleccion();
    		Image imagen;
    		if (casilla.comodin() != null) {
    			imagen = new ImageIcon(getClass().getResource("Imagenes/"+casilla.comodin().toString()+".png")).getImage();
        		g.drawImage(imagen, 0, 0, this);
    		}
    		if (ficha != null) {
    			imagen = new ImageIcon(getClass().getResource("Imagenes/"+ficha.toString()+".png")).getImage();
				g.drawImage(imagen, 0, 0, this);
    			if (estado == 's') setBackground(Color.gray);
    			if (estado == 'e') {
    				setBorder(new LineBorder(new Color(136, 0, 27), 2)); 
    				setBackground(new Color(205, 167, 174));
    			}
    		} else if (ficha == null && estado == 'p' && seleccionada != null) {
    			imagen = new ImageIcon(getClass().getResource("Imagenes/"+seleccionada.toString()+"Transparente.png")).getImage();
    			g.drawImage(imagen, 0, 0, this);
    		}
    		if (casilla.visible() && casilla.toString() != "Casilla") {
    			imagen = new ImageIcon(getClass().getResource("Imagenes/"+casilla.toString()+".png")).getImage();
        		g.drawImage(imagen, 0, 0, this);
    		}		
    	}
    }
    
    /**
     * Da el botón del panel.
     * @return JButton del PanelCasilla.
     */
    public JButton boton() {
        return boton;
    }
    
    /**
     * Da la fila donde el PanelCasilla está posicionado.
     * @return Número de la fila.
     */
    public int fila() {
        return f;
    }
    
    /**
     * Da la columna donde el PanelCasilla está posicionado.
     * @return Número de la columna.
     */
    public int columna() {
        return c;
    }
    
    /**
     * Cambia el estado del PanelCasilla.
     * @param m Estado que se le quiere ponel al PanelCasilla.
     */
    public void cambiarEstado(char m) {
        estado = m;
    }
    
    /**
     * Retorn el estado actual del PanelCasilla.
     * @return Char que representa el estado del PanelCasilla.
     */
    public char estado() {
        return estado;
    }
}