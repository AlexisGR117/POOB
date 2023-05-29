package domain; 

/**
 * Type of program.
 * @author ESCUELA 2022-02
 */
public class Course extends Program{

    private Integer price;
    
    /**
     * Constructs a new course.
     * @param name Name of the course.
     * @param price Price of the course.
     */
    public Course(String name, Integer price){
        this.name=name;
        this.price=price;
    }    
    
    /**
     * Return the price.
     * @return The cost of the course.
     * @throws IEMOISException, if the price is not available.
     */
    @Override
    public int price() throws IEMOISException{
        if (price == null) throw new IEMOISException(IEMOISException.COURSE_NO_PRICE);
        if (price < 1) throw new IEMOISException(IEMOISException.COURSE_ERROR_PRICE);
        return price;
    }
    
    /**
     * Represents the course information in a String.
     * @return String with course information.
     */
    public String data(){
        return name+". Precio:" +price;
    }
}
