package domain;

/**
 * Una unidad de enseñanza ofrecida por la decanatura durante el periodo intermedio a sus estudiantes en el proyecto IEMOIS.
 * @author ESCUELA 2022-02
 */
public abstract class Program{
    
    protected String name;

    /**
     * Return the name.
     * @return The name of the program.
     */
    public String name(){
        return name;
    }

    /**
     * Return the price.
     * @return The cost of the program.
     * @throws IEMOISException, if the price is not available.
     */
    public abstract int price() throws IEMOISException;
    
    /**
     * Return the representation as string
     * @return String with program information.
     * @throws IEMOISException, if the data is not complete
     */    
    public abstract String data() throws IEMOISException;
}
