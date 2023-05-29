
/**
  *Fraction
  * This class implements the Fraction data type; that is, a rational number that can be written in the form p/q, where p and q are integers, with q <> 0
  * The implementation is done by immutable objects
  * INV: The fractional is represented irreducibly.
  * @author ESCUELA
  */
public class Fraction {
    private int n;
    private int d;
    /**Calculate the greatest common divisor of two integers
     * We will implement it using the recursive algorithm
     * @param a first integer
     * @param b second integer
     * @return the Greatest Common Divisor of a and b
     */
    public static int gcd(int a,int b){
        int saveA;
        if (a < 0){
            a = (-1) * a;
        }
        if (b < 0){
            b = (-1) * b;
        }
        while (b != 0){
            saveA = a;
            a = b;
            b = saveA % b;
        }
        return a;
    }    
    
    /**Create a new fraction, given the numerator and denominator
     * @param numerator
     * @param denominator. denominator <> 0
     */
    public Fraction(int numerator, int denominator) {
        int[] s = simplify(numerator, denominator);
        n = s[0];
        d = s[1];
    }
    
    /**Create a fraction corresponding to an integer
     * @param integer the integer to create
     */
    public Fraction(int integer) {
        n = integer;
        d = 1;
    }

    /**Create a fraction, from its mixed representation.
     * The number created is mixedInteger + mixednumerator/mixeddenominator
     * @param integer the integer part of the number
     * @param numerator the numerator of the fractional part
     * @param denominator the denominator of the fractional part. denominator!=0
     */
    public Fraction(int integer, int numerator, int denominator) {
        int[] s = simplify(integer * denominator + numerator, denominator);
        n = s[0];
        d = s[1];
    }

    /**
     * Return the numerator of the simplified fraction
     * A fractional p/q is written in simplified form if q is a positive integer and
     * The greatest common divisor of p and q is 1.
     * @return The numerator of the simplified fraction
     */
    public int numerator(){
        return n;
    }
    
     /**
     * Return the denominator of the simplified fraction
     * A fractional p/q is written in simplified form if q is a positive integer and
     * The greatest common divisor of p and q is 1.
     * @return The denominator of the simplified fraction
     */   
    public int denominator() {
        return d;
    }
    
    /**
     * Add this fraction with another fraction
     * @param other is another fractional
     * @return this+other
     */
    public Fraction sume(Fraction other) {
        int denominator = d * other.denominator();
        int numerator = n * other.denominator() + other.numerator() * d;
        Fraction f = new Fraction(numerator, denominator);
        return f;
    }
    
    /**
     * Substract this fraction with another fraction
     * @param other is another fractional
     * @return this-other
     */
    public Fraction substract(Fraction other) {
        int denominator = d * other.denominator();
        int numerator = n * other.denominator() - other.numerator() * d;
        Fraction f = new Fraction(numerator, denominator);
        return f;
    }   
    
    /**
     * Multiply this fraction with another fraction
     * @param other is another fractional
     * @return this*other
     */
    public Fraction multiply(Fraction other) {
        int denominator = d * other.denominator();
        int numerator = n * other.numerator();
        Fraction f = new Fraction(numerator, denominator);
        return f;
    }
    
    /**Divide this fraction with another fraction
     * @param other is another fractional
     * @return this/other
     */
    public Fraction divide(Fraction other) {
        int denominator = d * other.numerator();
        int numerator = n * other.denominator();
        Fraction f = new Fraction(numerator, denominator);
        return f;
    }
    
    @Override
    public boolean equals(Object obj) {
        return equals((Fraction)obj);
    }    
    
      /**Compare this fraction to another fraction
      * @param other eL other fractional
      * @return true if this fraction is mathematically equal to the other fraction, False d.l.c.
      */
    public boolean equals(Fraction other) {
        boolean equal = false;
        if (other.numerator() == n && other.denominator() == d){
            equal = true;
        }
        return equal;
    }


    /** Calculate the string representation of a fraction in mixed simplified format
     * @see java.lang.Object#toString(java.lang.Object)
     */
    @Override
    public String toString(){
        return n+"/"+d;
    }
    
    /**
     * Simplify a fraction.
     * @param a Fraction numerator.
     * @param b Fraction denominator.
     * @return An array with the new numerator and denominator values.
     */
    private int[] simplify(int a, int b){
        if (b < 0){
            a = a * -1;
            b = b * -1;
        }
        int gcd = gcd(a, b);
        n = a / gcd;
        d = b / gcd;
        int[] s = {n, d};
        return s;
    }
}
