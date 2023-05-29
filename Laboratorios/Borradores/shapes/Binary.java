import java.awt.*;
import java.awt.geom.*;
import java.util.*;

/**
 * Un número representado en notación binaria y decimal.
 * 
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (27 de agosto de 2022)
 */
public class Binary
{
    private int numDecimal;
    private boolean[] numBinary;
    private String color;
    private ArrayList<Rectangle> rectangulos = new ArrayList<Rectangle>();
    private ArrayList<Circle> circulos = new ArrayList<Circle>();  
    private int xPosition;
    private int yPosition;
    private boolean isVisible;

    /**
     * Constructor para objetos de la clase Binary.
     * @param decimal Número en base 10.
     */
    public Binary(int decimal){
        color = "red";
        xPosition = 455;
        yPosition = 15;
        isVisible = false;
        String binario = "";
        numDecimal = decimal;
        for (int i = decimal; i != 0; i = i / 2){
            binario = i % 2 + binario;
         }
        numBinary = new boolean[binario.length()];
        for (int i = 0; i < binario.length(); i += 1){
            if (binario.substring(i, i+1).equals("1")){
                numBinary[i] = true;
            } else { numBinary[i] = false;
            }
        }
    }

    /**
     * Constructor para objetos de la clase Binary.
     * @param binary Arreglo de booleanos que reseprentan un número en base 2.
     */
    public Binary(boolean[] binary){
        color = "red";
        xPosition = 455;
        yPosition = 15;
        isVisible = false;
        numBinary = binary;
        numDecimal = 0;
        for (int i = 0; i < binary.length; i += 1){
            if (binary[i] == true){
                numDecimal += Math.pow(2, binary.length-i-1);
            }
        }
    }

    /**
     * Método que da el número en base 10.
     * @return El número en decimal.
     */
    public int getDecimal(){
        return numDecimal;
    }

    /**
     * Método que da el número en base 2.
     * @return El número en binario representado por un arreglo de booleanos.
     */
    public boolean[] getBinary(){
        return numBinary;
    }

    /**
     * Método que da el el color del número.
     * @return El color del número.
     */
    public String getColor(){
        return color;
    }
    
    /**
     * Método que cambia el numero actual por el siguiente a el.
     */
    public void next(){
        erase();
        numDecimal += 1;
        String binario = "";
        for (int i = numDecimal; i != 0; i = i / 2){
            binario = i % 2 + binario;
         }
        numBinary = new boolean[binario.length()];
        for (int i = 0; i < binario.length(); i += 1){
            if (binario.substring(i, i+1).equals("1")){
                numBinary[i] = true;
            } else { numBinary[i] = false;
            }
        }
        draw();
    }    
    
    /**
     * Método que cambia el número actual por uno nuevo.
     * @param newDecimal Recibe un nuevo número en base 10.
     */
    public void change(int newDecimal){
        erase();
        String binario = "";
        numDecimal = newDecimal;
        for (int i = newDecimal; i != 0; i = i / 2){
            binario = i % 2 + binario;
         }
        numBinary = new boolean[binario.length()];
        for (int i = 0; i < binario.length(); i += 1){
            if (binario.substring(i, i+1).equals("1")){
                numBinary[i] = true;
            } else { numBinary[i] = false;
            }
        }
        draw();
    }
    
    /**
     * Método que cambia el número actual por uno aleatorio.
     */
    public void change(){
        erase();
        numDecimal = (int)(Math.random()*100+1);
        String binario = "";
        for (int i = numDecimal; i != 0; i = i / 2){
            binario = i % 2 + binario;
         }
        numBinary = new boolean[binario.length()];
        for (int i = 0; i < binario.length(); i += 1){
            if (binario.substring(i, i+1).equals("1")){
                numBinary[i] = true;
            } else { numBinary[i] = false;
            }
        }
        draw();
    }    
    
    /**
     * Hace el número visible representandolo como binario en círculos y rectángulos.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Hace el número invisible.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * Mueve el número a una posición dada.
     * @param newX Nueva posición en el eje x.
     * @param newY Nueva posición en el eje y.
     */
    public void moveTo(int newX, int newY){
        int oldX = xPosition;
        int oldY = yPosition;
        xPosition = newX;
        yPosition = newY;
        for (int i = 0; i < rectangulos.size(); i += 1){
                rectangulos.get(i).moveHorizontal(xPosition - oldX);
                rectangulos.get(i).moveVertical(yPosition - oldY);
        }
        for (int i = 0; i < circulos.size(); i += 1){
                circulos.get(i).moveHorizontal(xPosition - oldX);
                circulos.get(i).moveVertical(yPosition - oldY);
        }
    }
    
    /**
     * Cambia el color del número
     * @param newColor El nuevo color. Los colores validos son "red", "yellow", "blue", "green", "magenta" and "black".
     */
    public void changeColor(String newColor){
        erase();
        color = newColor;
        draw();
    }
    
    /*
     * Dibuja el número en binario representadolo con círculos y rectángulos en la pantalla.
     */
    private void draw() {
        if(isVisible) {
            int len = numBinary.length;
            int numrec = 0;
            int numcir = 0;
            int antX = xPosition;
            for (int i = 0;i != len;i += 1){
                if (numBinary[len-i-1]){
                    if (numrec < rectangulos.size()){
                        rectangulos.remove(numrec);
                    } 
                    rectangulos.add(numrec, new Rectangle()); 
                    rectangulos.get(numrec).moveHorizontal(xPosition-54);
                    rectangulos.get(numrec).moveVertical(yPosition-15);
                    rectangulos.get(numrec).changeSize(40,8);
                    rectangulos.get(numrec).changeColor(color);
                    rectangulos.get(numrec).makeVisible();
                    numrec += 1;
                } else {
                    if (numcir < circulos.size()){
                        circulos.remove(numcir);
                    }                   
                    circulos.add(numcir, new Circle());
                    circulos.get(numcir).moveHorizontal(xPosition-20);
                    circulos.get(numcir).moveVertical(yPosition-15);
                    circulos.get(numcir).changeSize(40);  
                    circulos.get(numcir).changeColor(color);
                    circulos.get(numcir).makeVisible();
                    numcir += 1;                 
                }
                xPosition -= 45;
            }
            xPosition = antX;
        }
    }

    /*
     * Borra el número de la pantalla.
     */
    private void erase(){
        if(isVisible) {
            for (int i = 0; i < rectangulos.size(); i += 1){
                rectangulos.get(i).makeInvisible();
            }
            for (int i = 0; i < circulos.size(); i += 1){
                circulos.get(i).makeInvisible();
            }
        }
    }
}
