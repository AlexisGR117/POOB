import java.util.*;
/**
 * Esta clase implemeta el tipo de datos Polynomial; que es, un polinomio que puede ser escrito en la forma a+bx+cx2+... donde a, b y c son objetos de la clase Fraction.
 *
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (06/09/2022)
 */
public class Polynomial{
    private ArrayList<Fraction> fractions = new ArrayList<Fraction>();
    /**
     * Contructor para objetos de clase Polynomial.
     */
    public Polynomial(){
    }
    
    /**
     * Agrega un termino al polinomio.
     * @param f Fracción que representa el coeficiente del termino.
     */
    public void addTerm(Fraction f){
            fractions.add(f);
    }

    /**
     * Agrega un termino al polinomio.
     * @param f Arreglo con el numerador y denominador de la fracción que representa el coeficiente del termino.
     */
    public void addTerm(int[] f){
            fractions.add(new Fraction(f[0], f[1]));
    }
    
    /**
     * Agrega varios terminos al polinomio.
     * @param f Arreglo de arreglos con los numeradores y denominadores de la fracciones que representan los coeficientes del terminos.
     */
    public void addTerm(int[][] f){
        for (int i = 0; i < f.length; i ++){
            addTerm(f[i]);
        }
    }
    
    /**
     * Obtiene las fracciones que tienen los terminos del polinomio.
     */
    public ArrayList getFractions(){
        return fractions;
    }
    
    /**
     * Retorna el polinomio.
     * @return El polinomio como cadena.
     */
    public String toString(){
        String p = "";
        for (int i = 0; i < fractions.size(); i++){
            if (i == 0){
                p += fractions.get(i);
            } else if (i == 1){
                p += fractions.get(i) + "x";
            } else {
                p += fractions.get(i) + "x" + i;
            }
            if (i < fractions.size() - 1 && fractions.get(i+1).numerator() >= 0){
                p += "+";
            }
        }
        return p;
    }
    
    /**
     * Calcula la suma de dos polinomios.
     * @param other Segundo sumando.
     * @return El polinomio que resulta de la suma.
     */
    public Polynomial add(Polynomial other){
        ArrayList<Fraction> fractionsA = other.getFractions();
        Polynomial polynomial = new Polynomial();
        int sizeOne = fractions.size();
        int sizeTwo = fractionsA.size();
        if (sizeOne < sizeTwo){
            sizeOne = sizeTwo;
            sizeTwo = fractions.size();
        }
        int c = 0;
        for (int i = 0; i < sizeOne; i++){
            if (c < sizeTwo){
                polynomial.addTerm(fractions.get(i).sume(fractionsA.get(i)));
            } else {
                if (fractions.size() < fractionsA.size()){
                    polynomial.addTerm(fractionsA.get(i));
                }
                else{
                    polynomial.addTerm(fractions.get(i));
                }
            }
            c ++;
        }
        return polynomial;
    }

    /**
     * Calcula la resta de dos polinomios.
     * @param other Sustraendo.
     * @return El polinomio que resulta de la resta.
     */
    public Polynomial substract(Polynomial other){
        ArrayList<Fraction> fractionsA = other.getFractions();
        Polynomial polynomial = new Polynomial();
        int sizeOne = fractions.size();
        int sizeTwo = fractionsA.size();
        if (sizeOne < sizeTwo){
            sizeOne = sizeTwo;
            sizeTwo = fractions.size();
        }
        int c = 0;
        for (int i = 0; i < sizeOne; i++){
            if (c < sizeTwo){
                polynomial.addTerm(fractions.get(i).substract(fractionsA.get(i)));
            } else {
                if (fractions.size() < fractionsA.size()){
                    Fraction m = new Fraction(-1);
                    polynomial.addTerm(fractionsA.get(i).multiply(m));
                }
                else{
                    polynomial.addTerm(fractions.get(i));
                }
            }
            c ++;
        }
        return polynomial;
    }

    /**
     * Calcula la multiplicación de dos polinomios.
     * @param other Segundo factor.
     * @return El polinomio que resulta de la multiplicación.
     */
    public Polynomial multiply(Polynomial other){
        ArrayList<Fraction> fractionsA = other.getFractions();
        ArrayList<Polynomial> polynomials = new ArrayList<Polynomial>();
        Polynomial polynomial = new Polynomial();
        for (int i = 0; i < fractions.size(); i++){
            polynomials.add(new Polynomial());
            for (int k = 0; k < i; k++){
                polynomials.get(i).addTerm(new Fraction(0));
            }
            for (int j = 0; j < fractionsA.size(); j++){
                polynomials.get(i).addTerm(fractions.get(i).multiply(fractionsA.get(j)));
            }
        }
        for (int i = 0; i < fractions.size() + fractionsA.size()-1; i++){
            Fraction fraction = new Fraction(0);
            for (int j = 0; j < fractions.size(); j++){
                ArrayList<Fraction> f = polynomials.get(j).getFractions();
                if (f.size() > i){
                    fraction = fraction.sume(f.get(i)); 
                }
            }
            polynomial.addTerm(fraction);
        }
        return polynomial;
    }

    /**
     * Calcula la división de dos polinomios.
     * @param other Divisor.
     * @return El polinomio que resulta de la división.
     */
    public Polynomial divide(Polynomial other){
        ArrayList<Fraction> fractionsA = other.getFractions();
        ArrayList<Fraction> fractionsB = fractions;
        int size1 = fractionsB.size();
        int size2 = fractionsA.size();
        int index;
        int index2 = size1 - size2;
        Polynomial result = new Polynomial();
        Polynomial polynomial = new Polynomial();
        for (int i = 0; i <= index2; i++){
                result.addTerm(new Fraction(0));
        }
        Fraction f;
        Fraction f2;
        while (size1 >= size2){
            f = fractionsB.get(size1-1).divide(fractionsA.get(size2-1));
            polynomial.addTerm(f);
            result.getFractions().set(index2, f);
            index2 -= 1;
            Polynomial m = polynomial.multiply(other);
            Polynomial polynomial2 = new Polynomial();
            index = size1-1;
            for (int i = 0; i < m.getFractions().size(); i++){
                f2 = fractionsB.get(index-m.getFractions().size()+i+1);
                polynomial2.addTerm(f2);
            }
            Polynomial r = polynomial2.substract(m);
            fractionsB.remove(size1-1);
            size1 -= 1;
            ArrayList<Fraction> fractionsC = r.getFractions();
            index = fractionsC.size()-2;
            for (int i = 0; i < size2 - 1; i++){
                Fraction f3 = fractionsC.get(i);
                fractionsB.set(size1-1-index+i, f3);
            }
            polynomial = new Polynomial();
        }
        return result;
    }

    /**
     * Calcula la derivación de un polinomio.
     * @return El polinomio que resulta de la derivación.
     */
    public Polynomial derivate(){
        Polynomial result = new Polynomial();
        if (fractions.size() == 1){
            result.addTerm(new Fraction(0));
        }
        Fraction coefficient;
        for (int i = 1; i < fractions.size(); i++){
            coefficient = fractions.get(i).multiply(new Fraction(i));
            result.addTerm(coefficient);
        }
        return result;
    }
    
    /**
     * Calcula la integración de un polinomio.
     * @return El polinomio que resulta de la integración.
     */
    public Polynomial integrate(){
        Polynomial result = new Polynomial();
        Fraction coefficient;
        for (int i = 0; i < fractions.size(); i++){
            if (i == 0){
                result.addTerm(new Fraction(0));
            }
            coefficient = fractions.get(i).divide(new Fraction(i+1));
            result.addTerm(coefficient);
        }
        return result;
    }
}
