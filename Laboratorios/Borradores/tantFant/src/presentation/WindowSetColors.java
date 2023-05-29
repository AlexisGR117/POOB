package presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import domain.TantFant;
import domain.TantFantException;
/**
 * Dialog with which the user change colors of the board game.
 *
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (09/11/2022)
 */
public class WindowSetColors extends JDialog {
    private JButton selectColor1, selectColor2, selectColor3, selectColor4;
    private JButton selectColor5, selectColor6;
    private JButton ok, cancel, restore;
    private TantFantGUI tantFantGUI;
    private Color[] colorsBoard, colorsPieces;
    private JPanel setting;
    /**
     * Constructor for objects of class WindoSetColors.
     */
    public WindowSetColors(TantFantGUI tantFantGUI) {
        super(tantFantGUI, Dialog. ModalityType.DOCUMENT_MODAL);
        colorsBoard = tantFantGUI.boardColors();
        colorsPieces = tantFantGUI.piecesColors();
        this.tantFantGUI = tantFantGUI;
        prepareElements();
        prepareActions();
        setVisible(true);
    } 
    
    /*
     * Prepare the elements with which the user interacts.
     */
    private void prepareElements() {   
        setTitle("Configurar Colores");
        setSize(430, 380);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setting = new JPanel();
        setting.setLayout(new GridLayout(3, 1));
        TitledBorder title = new TitledBorder("Configurar Colores");
        Font font = new Font("Lato", Font.BOLD, 18);
        title.setTitleFont(font);
        setting.setBorder(new CompoundBorder(new EmptyBorder(0,0,0,0), title));
        prepareElementsPlayerOne();
        prepareElementsPlayerTwo();
        prepareElementsBoard();
        prepareElementsOptions();
        add(setting, BorderLayout.CENTER);
    } 
    
    /*
     * Prepare plater one elements.
     */
    private void prepareElementsPlayerOne() {   
        JPanel playerOne = new JPanel();
        playerOne.setLayout(null);
        TitledBorder title = new TitledBorder("Jugador Uno");
        Font font = new Font("Lato", Font.PLAIN, 16);
        title.setTitleFont(font);
        playerOne.setBorder(new CompoundBorder(new EmptyBorder(0,0,0,0), title));
        font = new Font("Lato", Font.PLAIN, 16);
        JLabel piece = new JLabel("Ficha:");
        piece.setBounds(30, 20, 200, 30);
        piece.setFont(font);
        JLabel possibleMoves = new JLabel("Posibles movimientos:");
        possibleMoves.setBounds(30, 55, 200, 30);
        possibleMoves.setFont(font);
        selectColor1 = new JButton();
        selectColor1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selectColor1.setBackground(colorsPieces[0]);
        selectColor1.setBounds(240, 20, 150, 30);   
        selectColor2 = new JButton();
        selectColor2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selectColor2.setBackground(colorsPieces[1]);
        selectColor2.setBounds(240, 55, 150, 30);    
        playerOne.add(piece);
        playerOne.add(selectColor1);
        playerOne.add(possibleMoves);
        playerOne.add(selectColor2);
        setting.add(playerOne);
    }
    
    /*
     * Prepare player two elements.
     */
    private void prepareElementsPlayerTwo() {   
        JPanel playerTwo = new JPanel();
        playerTwo.setLayout(null);
        TitledBorder title = new TitledBorder("Jugador Dos");
        Font font = new Font("Lato", Font.PLAIN, 16);
        title.setTitleFont(font);
        playerTwo.setBorder(new CompoundBorder(new EmptyBorder(0,0,0,0), title));
        font = new Font("Lato", Font.PLAIN, 16);
        JLabel piece = new JLabel("Ficha:");
        piece.setBounds(30, 20, 200, 30);
        piece.setFont(font);
        JLabel possibleMoves = new JLabel("Posibles movimientos:");
        possibleMoves.setBounds(30, 55, 200, 30);
        possibleMoves.setFont(font);
        selectColor3 = new JButton();
        selectColor3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selectColor3.setBackground(colorsPieces[2]);
        selectColor3.setBounds(240, 20, 150, 30);   
        selectColor4 = new JButton();
        selectColor4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selectColor4.setBackground(colorsPieces[3]);
        selectColor4.setBounds(240, 55, 150, 30);    
        playerTwo.add(piece);
        playerTwo.add(selectColor3);
        playerTwo.add(possibleMoves);
        playerTwo.add(selectColor4);
        setting.add(playerTwo);
    }
    
    /*
     * Prepare the board elements.
     */
    private void prepareElementsBoard() {   
        JPanel board = new JPanel();
        board.setLayout(null);
        TitledBorder title = new TitledBorder("Tablero de juego");
        Font font = new Font("Lato", Font.PLAIN, 16);
        title.setTitleFont(font);
        board.setBorder(new CompoundBorder(new EmptyBorder(0,0,0,0), title));
        font = new Font("Lato", Font.PLAIN, 16);
        JLabel background = new JLabel("Fondo:");
        background.setBounds(30, 20, 200, 30);
        background.setFont(font);
        JLabel border = new JLabel("Bordes:");
        border.setBounds(30, 55, 200, 30);
        border.setFont(font);
        selectColor5 = new JButton();
        selectColor5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selectColor5.setBackground(colorsBoard[0]);
        selectColor5.setBounds(240, 20, 150, 30);   
        selectColor6 = new JButton();
        selectColor6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selectColor6.setBackground(colorsBoard[1]);
        selectColor6.setBounds(240, 55, 150, 30);    
        board.add(background);
        board.add(selectColor5);
        board.add(border);
        board.add(selectColor6);
        setting.add(board);
    }
    
    /*
     * Prepare the options elements.
     */
    private void prepareElementsOptions() {   
        JPanel options = new JPanel();
        options.setLayout(new GridLayout(1, 3, 10, 10));;
        ok = new JButton("Guardar");
        ok.setBounds(0, 0, 150, 50);    
        ok.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancel = new JButton("Cancelar");
        cancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        restore = new JButton("Restaurar");
        restore.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        options.add(ok);
        options.add(restore);
        options.add(cancel);
        add(options, BorderLayout.SOUTH);
    }
    
    /*
     * Prepare actions of TantFant.
     */
    private void prepareActions(){   
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                actionExit();
            }
        });
        prepareActionsPlayerOne();
        prepareActionsPlayerTwo();
        prepareActionsBoard();
        prepareActionsOptions();
    } 
    
    /*
     * Exit colors configuration.
     */
    private void actionExit(){   
        int answer = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres salir de la configuración de color?", "Salir configuración de color", JOptionPane.OK_CANCEL_OPTION);
        if (answer == JOptionPane.OK_OPTION){
            setVisible(false);
            dispose();
        }
    }
    
    /*
     * Prepare actions of player one.
     */
    private void prepareActionsPlayerOne(){  
        selectColor1.addActionListener(e -> actionChangeColorPiece1());
        selectColor2.addActionListener(e -> actionChangeColorPossibleMoves1());
    }
    
    /*
     * Change piece color of player one.
     */
    private void actionChangeColorPiece1() {
        Color color = JColorChooser.showDialog(null,"Color de la ficha del jugador uno", colorsPieces[0]);
        if (color != null) {
            colorsPieces[0] = color;
            selectColor1.setBackground(colorsPieces[0]);
        }
    }
    
    /*
     * Change possible moves color of player one.
     */    
    private void actionChangeColorPossibleMoves1() {
        Color color = JColorChooser.showDialog(null,"Color de los posibles movimientos del jugador uno", colorsPieces[1]);
        if (color != null) {
            colorsPieces[1] = color;
            selectColor2.setBackground(colorsPieces[1]);
        }
    }
    
    /*
     * Prepare actions of player two.
     */
    private void prepareActionsPlayerTwo(){  
        selectColor3.addActionListener(e -> actionChangeColorPiece2());
        selectColor4.addActionListener(e -> actionChangeColorPossibleMoves2());
    }
    
    /*
     * Change piece color of player two.
     */
    private void actionChangeColorPiece2() {
        Color color = JColorChooser.showDialog(null,"Color de la ficha del jugador dos", colorsPieces[2]);
        if (color != null) {
            colorsPieces[2] = color;
            selectColor3.setBackground(colorsPieces[2]);
        }
    }
    
    /*
     * Change possible moves color of player two.
     */    
    private void actionChangeColorPossibleMoves2() {
        Color color = JColorChooser.showDialog(null,"Color de los posibles movimientos del jugador dos", colorsPieces[3]);
        if (color != null) {
            colorsPieces[3] = color;
            selectColor4.setBackground(colorsPieces[3]);
        }
    }
    
    /*
     * Prepare actions of board.
     */
    private void prepareActionsBoard(){  
        selectColor5.addActionListener(e -> actionChangeColorBackground());
        selectColor6.addActionListener(e -> actionChangeColorBorder());
    }
    
    /*
     * Change background color.
     */
    private void actionChangeColorBackground() {
        Color color = JColorChooser.showDialog(null,"Color del fondo del tablero", colorsBoard[0]);
        if (color != null) {
            colorsBoard[0] = color;
            selectColor5.setBackground(colorsBoard[0]);
        }
    }
    
    /*
     * Change border color.
     */    
    private void actionChangeColorBorder() {
        Color color = JColorChooser.showDialog(null,"Color de los bordes tablero", colorsBoard[1]);
        if (color != null) {
            colorsBoard[1] = color;
            selectColor6.setBackground(colorsBoard[1]);
        }
    }
    
    /*
     * Prepare actions of board.
     */
    private void prepareActionsOptions(){  
        ok.addActionListener(e -> actionOk());
        cancel.addActionListener(e -> actionCancel());
        restore.addActionListener(e -> actionRestore());
    }
    
    /*
     * Change the colors of the board to the selected ones.
     */
    private void actionOk() {
        tantFantGUI.setColors(colorsBoard, colorsPieces);
        setVisible(false);
        dispose();
    }
    
    /*
     * It does not make any changes to the colors
     */    
    private void actionCancel() {
        actionExit();
    }
    
    /*
     * Board colors return to default.
     */    
    private void actionRestore() {
        colorsBoard[0] = Color.white;
        selectColor5.setBackground(colorsBoard[0]);
        colorsBoard[1] = Color.black;
        selectColor6.setBackground(colorsBoard[1]);
        colorsPieces[0] = Color.black;
        selectColor1.setBackground(colorsPieces[0]);
        colorsPieces[1] = Color.cyan;
        selectColor2.setBackground(colorsPieces[1]);
        colorsPieces[2] = Color.white;
        selectColor3.setBackground(colorsPieces[2]);
        colorsPieces[3] = Color.orange;
        selectColor4.setBackground(colorsPieces[3]);   
    }
}
