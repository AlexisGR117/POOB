import java.util.*;

/** 
 * Calculadora de polinomios.
 * 
 * @author ESCUELA 2022-02
 */
    
public class PolynomialCalculator{
    
    private HashMap<String,Polynomial> variables = new HashMap<String,Polynomial>();
    private boolean last;
    
    /**
     * Contructor para objetos de clase PolynomialCalcuator.
     */
    public PolynomialCalculator(){
        last = true;
    }

    /**
     * Crea una nueva variable.
     * @param nombre Nombre de la variable.
     */
    public void create(String nombre){
        if (!variables.containsKey(nombre)){
            variables.put(nombre, new Polynomial());
            last = true;
        } else {
            last = false;
        }
    }
     
    /**
     * Asigna un polinomio a una variable existente.
     * @param set Variable a la cual se le va asignar el polinomio.
     * @param fractions Fracciones que va tener el polinomio.
     */
    public void assign(String set, int[][] fractions){
        if (variables.containsKey(set)){
            for (int i = 0; i < fractions.length; i ++){
                variables.get(set).addTerm(fractions[i]);
            }
            last = true;
        } else {
            last = false;
        }
    }    
    
    /**
     * Asigna el valor de una operación a una variable
     * @param a Variable a la cual se le va asignar el resultado de la operación.
     * @param b Primera variable que va en la operación.
     * @param op Operación que se va a realizar entre las variables. Las caracteres de las operaciones son: '+', '-', '*' y '/'
     * @param c Segunda variable que va en la operación.
     */
    public void assign(String a, String b, char op, String c){
        if (variables.containsKey(a) && variables.containsKey(b) && variables.containsKey(c)){
            last = true;
            Polynomial p = variables.get(b);
            Polynomial p2 = variables.get(c);
            if (op == '+'){
                variables.put(a, p.add(p2));
            } else if (op == '-'){
                variables.put(a, p.substract(p2));
            } else if (op == '*'){
                variables.put(a, p.multiply(p2));
            } else if (op == '/'){
                variables.put(a, p.divide(p2));
            } else {
                last = false;
            }
        }
        else {
            last = false;
        }
    }
  
    /**
     * Asigna el valor de una operación a una variable
     * @param a Variable a la cual se le va asignar el resultado de la operación.
     * @param op Operación que se va a realizar entre las variables. Las caracteres de las operaciones son: 'd' y 'i'
     * @param b Variable que a la cual se le realizará la operacion.
     */
    public void assign(String a, char op, String b){
        if (variables.containsKey(a) && variables.containsKey(b)){
            last = true;
            Polynomial p = variables.get(b);
            if (op == 'd'){
                variables.put(a, p.derivate());
            } else if (op == 'i'){
                variables.put(a, p.integrate());
            } else {
                last = false;
            }
        }
        else {
            last = false;
        }
    }

    /**
     * Asigna el valor de una operación a una variable
     * @param a Variable a la cual se le va asignar el resultado de la operación.
     * @param op Operación que se va a realizar entre las variables. Las caracteres de las operaciones son: 'd' y 'i'
     * @param b Variable que a la cual se le realizará la operacion.
     * @param n Numero de veces que se realizará la operación.
     */
    public void assign(String a, char op, String b, int n){
        if (variables.containsKey(a) && variables.containsKey(b)){
            last = true;
            variables.put(a, variables.get(b));
            Polynomial p = variables.get(a);
            if (op == 'd'){
                for (int i = 0; i < n; i++){
                    variables.put(a, p.derivate());
                    p = variables.get(a);
                }
            } else if (op == 'i'){
                for (int i = 0; i < n; i++){
                    variables.put(a, p.integrate());
                    p = variables.get(a);
                }
            } else {
                last = false;
            }
        }
        else {
            last = false;
        }
    }
    
    /**
     * Evalua el polinomio
     * @param a Variable donde está el polinomio que se va evaluar
     * @param x Arreglo con los valores que se van a utilizar para evaluar.
     * @return Cadena con los resultados obtenidos al evaluar cada valor en el polinomio.
     */
    public String evaluate(String a, int[] x){
        String resultado = "";
        if (variables.containsKey(a)){
            Polynomial polynomial = variables.get(a);
            ArrayList<Fraction> fractions = polynomial.getFractions();
            Fraction fraction;
            for (int i  = 0; i < x.length; i++){
                fraction = new Fraction(0);
                for (int j = 0; j < fractions.size(); j++){
                    Fraction f = fractions.get(j);
                    if (j == 0){
                        fraction = fraction.sume(f);
                    } else {
                        int p = x[i];
                        for (int k = 1; k < j; k++){
                            p *= x[i];
                        }
                        fraction = fraction.sume(f.multiply(new Fraction(p)));
                    }
                }
                if (i < x.length - 1){
                    resultado += fraction + ", ";
                } else {
                    resultado += fraction;
                }
            }
            last = true;
            return resultado;
        } else {
            last = false;
        }
        return null;
    } 
    
    /**
     * Retorna el polinomio como cadena.
     * @param set Variable donde está el polinomio que se va a retornar.
     * @return El polinomio como cadena.
     */
    public String toString(String set){
        if (variables.containsKey(set)){
            last = true;
            return variables.get(set).toString();       
        } else {
            last = false;
            return null;
        }
    }
    
    /**
     * Dice si la última acción se hizo o no.
     * @return Retorna si la última acción se pudo realizar o no.
     */
    public boolean ok(){
        return last;
    }
    
    /**
     * Obtiene las variables que hay en la calculadora.
     * @return Las variables en un HashMap.
     */
    public HashMap getVariables(){
        last = true;
        return variables;
    }
}