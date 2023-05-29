package domain; 
 
import java.util.ArrayList;

/**
 * Type of program that covers several courses.
 * @author ESCUELA 2022-02
 */
public class Specialization extends Program{
    
    public final static int DEFAULT_PRICE=10000;
    private int discount;
    private ArrayList<Course> courses;
    
    /**
     * Constructs a new specializacion.
     * @param name Name of the specializatin.
     * @param discount Discount of the specialization.
     */
    public Specialization(String name, int discount){
        this.name=name;
        this.discount=discount;
        courses= new ArrayList<Course>();
    }

     /**
     * Add a new course.
     * @param c Course to add to the specialization.
     */   
    public void addCourse(Course c){
        courses.add(c);
    }
    
    /**
     * Represents the specialization information in a String.
     * @return String with specialization information.
     * @throws IEMOISException, if the price of some course is not available.
     */
    @Override
    public String data() throws IEMOISException{
        StringBuffer answer=new StringBuffer();
        answer.append(name+". Descuento: "+ discount);
        answer.append('\n');
        int count = 1;
        for(Course c: courses) {
            answer.append("\t"+count+". "+c.name()+ " : " + c.price());
            answer.append('\n');
            count ++;
        }
        return answer.toString();
    }    
  
    /**
     * Return the price.
     * @return The cost of the specialization.
     * @throws IEMOISException, if the price is not available or the specialization is empty.
     */
    @Override
    public int price() throws IEMOISException{
        if (courses.size() == 0) throw new IEMOISException(IEMOISException.SPECIALIZATION_EMPTY);
        double price = 0;
        for (Course c:courses) price += c.price();            
        return (int)(price-price*(double)(discount)*0.01);
    }
    
    /**
     * Calculates the default price of a Specialization.
     * For courses where the price cannot be known, the DEFAULT_PRICE is assumed. 
     * @return The defaul price of the specialization.
     * @throws IEMOISException SPECIALIZATION_EMPTY, if it don't have courses.
     */
    public int defaultPrice() throws IEMOISException{
        if (courses.size() == 0) throw new IEMOISException(IEMOISException.SPECIALIZATION_EMPTY);
        double price = 0;
        for (Course c:courses){
            try{
                price += c.price();    
            } catch (IEMOISException e){
                price += DEFAULT_PRICE;  
            }
        }
        return (int)(price-price*(double)(discount)*0.01);
    }   

    /**
     * Calculates the price of a course.
     * @param name Name of the course that is in the specialization.
     * @return The price of the course.
     * @throws IEMOISException, if the price is not available.
     * @throws IEMOISException COURSE_NO_EXIST, if the course no exist.
     * @throws IEMOISException TWO_COURSES, if exist two courses with the same name.
     */
    public int price(String name) throws IEMOISException{
        int count = 0, price = 0, cPrice;
        for (Course c:courses) {
            if (c.name.equals(name)){
                count ++;
                cPrice = c.price();
                price = (int)(cPrice-cPrice*(double)(discount)*0.01);
            }
        }
        if (count == 0) throw new IEMOISException(IEMOISException.COURSE_NO_EXIST);
        if (count == 2) throw new IEMOISException(IEMOISException.TWO_COURSES);
        return price;
    }   
}
