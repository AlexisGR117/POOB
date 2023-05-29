package presentation;

import domain.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
/**
 *  Graphical interface with which the user interacts.
 *
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (11/04/2022)
 */
public class TantFantGUI extends JFrame {
    private JMenuItem newGame, open, save, restart, exit, changeColors, boardSize;
    private JPanel gameBoard, play, information;
    private JButton move;
    private JSpinner initialColumn, initialRow, finalRow, finalColumn;
    private JLabel moves, turn;    
    private Color backgroundColor, borderColor;
    private Color[] colorsPieces;
    private Box[][] board;
    private TantFant tantFant;
    /*
     * Constructor  for objects of class tantFantGUI.
     */
    private TantFantGUI() {
        super("Tant Fant");
        try {
            backgroundColor = Color.white;
            borderColor = Color.black;
            colorsPieces = new Color[4];
            colorsPieces[0] = Color.black;
            colorsPieces[1] = Color.cyan; 
            colorsPieces[2] = Color.white; 
            colorsPieces[3] = Color.orange; 
            tantFant = new TantFant(3);
            prepareElements();
            prepareActions();
        } catch (TantFantException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /*
     * Prepare the elements with which the user interacts.
     */
    private void prepareElements() {   
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dimension.width/2, dimension.height/2);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(524, 280));
        prepareElementsMenu();
        prepareElementsBoard();
        prepareElementsPlay();     
    } 
    
    /*
     * Prepare the menu elements.
     */
    private void prepareElementsMenu() {   
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Menú"), setting = new JMenu("Configuración");
        newGame = new JMenuItem("Nuevo");
        open = new JMenuItem("Abrir");
        save = new JMenuItem("Salvar");
        exit = new JMenuItem("Salir");
        restart = new JMenuItem("Reiniciar");
        open = new JMenuItem("Abrir");
        boardSize = new JMenuItem("Tamaño");
        changeColors = new JMenuItem("Colores");
        bar.add(menu);
        bar.add(setting);
        menu.add(newGame);
        menu.add(open);
        menu.add(save);
        menu.add(restart);
        menu.add(exit);
        setting.add(changeColors);
        setting.add(boardSize);
        setJMenuBar(bar);
    } 
    
    /*
     * Prepare the board elements.
     */
    private void prepareElementsBoard() {  
        int size = tantFant.getSize();
        gameBoard = new JPanel();
        gameBoard.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Tablero de juego")));
        board = new Box[size][size];
        gameBoard.setLayout(new BorderLayout());
        JPanel boxes = new JPanel();
        boxes.setLayout(new GridLayout(size, size));
        gameBoard.add(boxes, BorderLayout.CENTER);
        JPanel rows = new JPanel(), rows2 = new JPanel(), columns = new JPanel(), columns2 = new JPanel();
        rows.setLayout(new GridLayout(size, 1));
        rows2.setLayout(new GridLayout(size, 1));
        columns.setLayout(new GridLayout(1, size));
        columns2.setLayout(new GridLayout(1, size));
        gameBoard.add(rows, BorderLayout.WEST);
        gameBoard.add(rows2, BorderLayout.EAST);
        gameBoard.add(columns, BorderLayout.NORTH);
        gameBoard.add(columns2, BorderLayout.SOUTH);
        for (int i = 0; i < size; i++) {
            rows.add(new JLabel((size-i)+""));
            rows2.add(new JLabel((size-i)+""));
            columns.add(new JLabel((i+1)+"",SwingConstants.CENTER));
            columns2.add(new JLabel((i+1)+"",SwingConstants.CENTER));
            for (int j = 0; j < size; j++) {
                Box box = new Box(i, j, this);
                JButton boxButton = box.getButton();
                box.setLayout(new GridLayout(1, 1));
                box.add(boxButton);              
                box.setBackground(backgroundColor);
                boxButton.setBackground(backgroundColor);
                box.setBorder(new LineBorder(borderColor));                    
                boxes.add(box);
                board[i][j] = box;
            }
        }
        add(gameBoard, BorderLayout.CENTER);
    }    
    
    /*
     * Prepare elements with which the user plays.
     */
    private void prepareElementsPlay() {  
        play = new JPanel();
        play.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Jugar")));
        play.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane();
        JPanel playC = new JPanel();
        playC.setLayout(new BorderLayout());
        play.add(playC, BorderLayout.CENTER);
        JPanel playCC = new JPanel();
        playCC.setLayout(null);
        playCC.setPreferredSize(new Dimension(210, 325));
        scrollPane.setViewportView(playCC);
        playC.add(scrollPane, BorderLayout.CENTER);    
        JPanel playCS = new JPanel();
        playCS.setLayout(new FlowLayout());
        playC.add(playCS, BorderLayout.SOUTH);        
        initialRow = new JSpinner();
        initialRow.setFont(new java.awt.Font("Dialog", 0, 18));
        initialRow.setBounds(50, 60, 200, 25);
        initialColumn = new JSpinner();
        initialColumn.setFont(new java.awt.Font("Dialog", 0, 18));
        initialColumn.setBounds(50, 140, 200, 25);
        finalRow = new JSpinner(); 
        finalRow.setFont(new java.awt.Font("Dialog", 0, 18));
        finalRow.setBounds(50, 220, 200, 25);
        finalColumn = new JSpinner();
        finalColumn.setFont(new java.awt.Font("Dialog", 0, 18));
        finalColumn.setBounds(50, 300, 200, 25);        
        JLabel fi = new JLabel("Fila inicial");
        fi.setBounds(126, 20, 100, 20);
        playCC.add(fi);      
        playCC.add(initialRow);
        JLabel ci = new JLabel("Columna inicial");
        ci.setBounds(106, 100, 100, 20);
        playCC.add(ci);
        playCC.add(initialColumn);
        JLabel ff = new JLabel("Fila final");
        ff.setBounds(128, 180, 100, 20);
        playCC.add(ff);
        playCC.add(finalRow);
        JLabel cf = new JLabel("Columna final");
        cf.setBounds(108, 260, 100, 20);
        playCC.add(cf);
        playCC.add(finalColumn);        
        move = new JButton("Mover");
        move.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        playCS.add(move);
        prepareElementsInformation();
        play.add(information, BorderLayout.SOUTH);
        add(play, BorderLayout.EAST);
    }   
    
    /*
     * Prepare the board elements.
     */
    private void prepareElementsInformation() {  
        information = new JPanel();
        information.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Información")));
        information.setLayout(new GridLayout(2, 2));
        information.add(new JLabel("Turno:"));
        turn = new JLabel("Jugador Uno", SwingConstants.CENTER);
        information.add(turn);
        information.add(new JLabel("Número de movimientos:"));
        moves = new JLabel("0", SwingConstants.CENTER);
        information.add(moves);
    }   
    
    /*
     * Prepare actions of TantFant.
     */
    private void prepareActions() {   
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                actionExit();
            }
        });
        prepareActionsMenu();
        prepareActionsSetting();
        prepareActionsPlay();
        prepareActionsBoard();
    } 
        
    /*
     * Prepare menu actions.
     */
    private void prepareActionsMenu() {   
    	newGame.addActionListener(e -> actionNew());
        exit.addActionListener(e -> actionExit());
        open.addActionListener(e -> actionOpen());
        save.addActionListener(e -> actionSave());
        restart.addActionListener(e -> actionRestart());
    }       
    
    /*
     * Restart game.
     */
    private void restart() {   
        try {
            tantFant = new TantFant(tantFant.getSize());
	        refresh();
        } catch (TantFantException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }     
    }
    
    /*
     * Exit game.
     */
    private void actionNew() {   
    	int answer = JOptionPane.showConfirmDialog(null, "¿Quieres guardar la partida actual?", "Nueva partida", JOptionPane.YES_NO_CANCEL_OPTION);
        if (answer == JOptionPane.YES_OPTION){
        	int answer2 = actionSave();
        	if (answer2 == JFileChooser.APPROVE_OPTION) {
        		restart();
        	}
        } else if (answer == JOptionPane.NO_OPTION){
        	restart();
        }
    }
    
    /*
     * Exit game.
     */
    private void actionExit() {   
        int answer = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres salir del juego?", "Salir del juego", JOptionPane.OK_CANCEL_OPTION);
        if (answer == JOptionPane.OK_OPTION) {
            setVisible(false);
            System.exit(0);
        }
    }
    
    /*
     * Open game.
     */
    private void actionOpen() {   
        JFileChooser chooser = new JFileChooser();
        int open = chooser.showOpenDialog(null);
        if (open == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            JOptionPane.showMessageDialog(null, "La funcionalidad para abir el archivo " + file.getName() + " está en construcción.");
        }
    }
    
    /*
     * Save game.
     */
    private int actionSave() {   
        JFileChooser chooser = new JFileChooser();
        int open = chooser.showSaveDialog(null);
        if (open == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            JOptionPane.showMessageDialog(null, "La funcionalidad para salvar el archivo " + file.getName() + " está en construcción.");
        }
        return open;
    }
    
    /*
     * Restart game.
     */
    private void actionRestart() {   
    	int answer = JOptionPane.showConfirmDialog(null, "¿Seguro que quiere reiniciar el tablero? se perderá la partida actual.", "Reiniciar tablero.", JOptionPane.OK_CANCEL_OPTION);
        if (answer == JOptionPane.OK_OPTION){
        	restart();
        } 
    }
    
    /*
     * Refresh the board.
     */
    private void refresh() {   
        int size = tantFant.getSize();
        moves.setText(tantFant.plays()+"");
        turn.setText("Jugador "+tantFant.player());
        for(int i = 0; i < size; i++) for(int j = 0; j < size; j++) {
            board[i][j].setBackground(backgroundColor);
            board[i][j].setBorder(new LineBorder(borderColor));    
        }
    }  
    
    /*
     * Prepare setting actions.
     */
    private void prepareActionsSetting() {   
        changeColors.addActionListener(e -> actionChangeColors());
        boardSize.addActionListener(e -> actionChangeSize());
    } 
        
    /*
     * Change game board colors.
     */
    private void actionChangeColors() {   
        new WindowSetColors(this);
    }
    
    /*
     * Change game board size.
     */
    private void actionChangeSize() {   
        String answer = JOptionPane.showInputDialog(null, "Ingrese el nuevo tamaño del tablero:");
        if (answer != null) {
            try {
                int answer2 = JOptionPane.showConfirmDialog(null, "Si guarda los cambios se perderá la partida actual.", "Guardar Cambios", JOptionPane.OK_CANCEL_OPTION);
                if (answer2 == JOptionPane.OK_OPTION) {
                    tantFant = new TantFant(Integer.parseInt(answer));
                    remove(gameBoard);
                    prepareElementsBoard();
                    prepareActionsBoard();
                    add(gameBoard, BorderLayout.CENTER);
                    setVisible(true);
                    moves.setText(tantFant.plays()+"");
                    turn.setText("Jugador "+tantFant.player());
                }
            } catch (TantFantException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Se debe dar un número");
            }    
        }
    }
 
    /*
     * Prepare play actions.
     */
    private void prepareActionsPlay() {   
        move.addActionListener(e -> actionMove());
        initialRow.addChangeListener(e -> actionPossible());
        initialColumn.addChangeListener(e -> actionPossible());
    } 
    
    /*
     * Move a piece.
     */
    private void actionMove() {  
        int ic = (int)initialColumn.getValue();
        int ir = (int)initialRow.getValue();
        int fc = (int)finalColumn.getValue();
        int fr = (int)finalRow.getValue();
        byte player = tantFant.player();
        int plays = tantFant.plays() + 1;
        boolean w = false;
        try {
            w = tantFant.play(ir, ic, fr, fc);
        } catch (TantFantException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        if (w) {
        	refresh();
            JOptionPane.showMessageDialog(null, "Ganó el jugador "+player+" con "+plays+" movimientos.");
            try {
                tantFant = new TantFant(tantFant.getSize());
            } catch (TantFantException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        refresh();
    }
    
    /*
     * Prepare board actions.
     */
    private void prepareActionsBoard() {   
        int size = tantFant.getSize();
        for (int i = 0; i < size; i++) for (int j = 0; j < size; j++) {
            board[i][j].getButton().addActionListener(e -> actionSelect(e));
        }
    } 
  
    
    /*
     * Select a box of the game board.
     */
    private void actionSelect(ActionEvent e) {  
        Box button = (Box)((JButton)e.getSource()).getParent();
        int r = button.row(), c = button.column();
        int size = tantFant.getSize();
        if (tantFant.board()[r][c] == 'n' || tantFant.board()[r][c] == 'b' ||
        	tantFant.board()[r][c] == 'o' || tantFant.board()[r][c] == 'c') {
        	if (((tantFant.board()[r][c] == 'n' || tantFant.board()[r][c] == 'o') && tantFant.player() != 1) || 
        		(tantFant.board()[r][c] == 'b' || tantFant.board()[r][c] == 'c') && tantFant.player() != 2) {
        		JOptionPane.showMessageDialog(null, "No es turno de este jugador");
        	} else {
        		 initialColumn.setValue(c+1);
                 initialRow.setValue(size-r);
                 actionPossible();
        	}
        } else if (tantFant.board()[r][c] == 'B' || tantFant.board()[r][c] == 'N') {
            finalColumn.setValue(c+1);
            finalRow.setValue(size-r);
            actionMove();
        }
    }
    
    /*
     * Shows the possible moves of a piece.
     */
    private void actionPossible() {  
        int ic = (int)initialColumn.getValue();
        int ir = (int)initialRow.getValue();      
        int size = tantFant.getSize();
        tantFant.possibles(size - ir, ic-1);
        refresh();
    }
    
    /**
     * Give the pieces colors.
     * @return An array with the colors of the pieces.
     */
    public Color[] piecesColors() {
        return colorsPieces;
    }
    
    /**
     * Give the board colors.
     * @return An array with the colors of the board.
     */
    public Color[] boardColors() {
        Color[] c = {backgroundColor, borderColor};
        return c;
    }
    
    /**
     * Assign th colors.
     * @param t Board colors
     * @param f Pieces colors.
     */
    public void setColors(Color[] t, Color[] f) {
        backgroundColor = t[0];
        borderColor = t[1];
        colorsPieces[0] = f[0];
        colorsPieces[1] = f[1];
        colorsPieces[2] = f[2];
        refresh();
    }
    
    /**
     * Give the Tant Fant Game.
     * @reutrn TanFant.
     */
    public TantFant getTantFant() {
        return tantFant;
    }
    
        /**
     * Main method of TantFant.
     */
    public static void main(String[] args) {
        TantFantGUI gui = new TantFantGUI();
        gui.setVisible(true);
    } 
}

class Box extends JPanel {
    private JButton button;
    private int c, r;
    private TantFantGUI tantFantGUI;
    /**
     * Constructor  for objects of class Box.
     */
    public Box(int r, int c, TantFantGUI tantFantGUI) {
        this.c = c;
        this.r = r;
        this.tantFantGUI = tantFantGUI;
        button = new JButton();
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setBackground(tantFantGUI.getBackground());
    }
    
    /**
     * Paint the piece in the box.
     * @param g Graphics.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        Color[] colors = tantFantGUI.piecesColors();
        char state = tantFantGUI.getTantFant().board()[r][c];
        if (state == 'n' || state == 'o') {
        	if (state == 'o') {
        		g2d.setColor(colors[1]);
        		g2d.fillOval((int)(getWidth()/5.6-5),(int)(getHeight()/5.6-5),(int)(getWidth()/1.5+10),(int)(getHeight()/1.5+10));
        	}
            g2d.setColor(colors[0]);
            g2d.fillOval((int)(getWidth()/5.6),(int)(getHeight()/5.6),(int)(getWidth()/1.5),(int)(getHeight()/1.5));
        }
        else if (state == 'b' || state == 'c') {
        	if (state == 'c') {
        		g2d.setColor(colors[3]);
        		g2d.fillOval((int)(getWidth()/5.6-5),(int)(getHeight()/5.6-5),(int)(getWidth()/1.5+10),(int)(getHeight()/1.5+10));
        	}
            g2d.setColor(colors[2]);    
            g2d.fillOval((int)(getWidth()/5.6),(int)(getHeight()/5.6),(int)(getWidth()/1.5),(int)(getHeight()/1.5));
        }
        if (state != 'v') {
            BasicStroke grosor = new BasicStroke(5);
            g2d.setStroke(grosor);
            g2d.setColor(Color.black);
            if (state == 'N') g2d.setColor(colors[1]);
            if (state == 'B') g2d.setColor(colors[3]);
            g2d.drawOval((int)(getWidth()/5.6),(int)(getHeight()/5.6),(int)(getWidth()/1.5),(int)(getHeight()/1.5));
        }
    }
    
    /**
     * Give the button of the box.
     * @return Button.
     */
    public JButton getButton() {
        return button;
    }
    
    /**
     * Gives the row where the box is located.
     * @return Row number.
     */
    public int row() {
        return r;
    }
    
    /**
     * Gives the column where the box is located.
     * @return Column number.
     */
    public int column() {
        return c;
    }
}