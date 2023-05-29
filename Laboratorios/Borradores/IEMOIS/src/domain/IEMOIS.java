package domain;

import java.util.*;
/**
 * IEMOIS
 * @author POOB  
 * @version ECI 2022
 */

public class IEMOIS{
    
    private LinkedList<Program> programs;
    private HashMap<String,Course> courses;

    /**
     * Create a IEMOIS.
     */
    public IEMOIS(){
        programs = new LinkedList<Program>();
        courses = new HashMap<String,Course>();
        addSome();
    }

    /**
     * Add some programs to the IEMOIS project.
     */
    private void addSome(){
        String [][] courses = {{"Aprendiendo a Aprender. MacMaster-California. Coursera","41"},
                               {"Introduction to Computer Science and Programming Using Python","75"},
                               {"Databases: Modeling and Theory","50"},{"Databases: Relational Databases and SQL","50"}, 
                               {"Databases: Advances Topics in SQL","50"},{"Databases: Semistructured Data", "50"},
                               {"Machine Learning","95"}};
        for (String [] c: courses){
            try {
                addCourse(c[0],c[1]);
            } catch (IEMOISException e) {}
        }
        String [][] specializations = {{"Developing Databases. Stanford Online. Edx.", "50", "Databases: Modeling and Theory\nDatabases: Relational Databases and SQL"},
                                       {"Advanced Topics of Databases. Standford Online. Edx.", "10", "Databases: Advances Topics in SQL\nDatabases: Semistructured Data"}};
        for (String [] s: specializations){
            try {
                addSpecialization(s[0],s[1],s[2]);
            } catch (IEMOISException e) {}            
        }
    }

    /**
     * Consult a program.
     * @param name Name of the program.
     * @return The program.
     */
    public Program consult(String name){
        Program p=null;
        for(int i=0;i<programs.size() && p == null;i++){
            if (programs.get(i).name().compareToIgnoreCase(name)==0) 
               p=programs.get(i);
        }
        return p;
    }

    
    /**
     * Add a new course to the IEMOIS project.
     * @param name Name of the course.
     * @param price Price of the course.
     * @throws IEMOISException COURSE_ALREADY_EXISTS, if the a course with that name already exists.
     * @throws IEMOISException INVALID_PRICE, if the price is not a positive number.
     * @throws IEMOISException COURSE_WITHOUT_NAME, if does not give the name of the course.
     */
    public void addCourse(String name, String price) throws IEMOISException{ 
        if (courses.containsKey(name.toUpperCase())) throw new IEMOISException(IEMOISException.COURSE_ALREADY_EXISTS);
        if (name.trim().equals("")) throw new IEMOISException(IEMOISException.COURSE_WITHOUT_NAME);
        int p;
        try {
            p = Integer.parseInt(price);
        } catch(NumberFormatException e){
            throw new IEMOISException(IEMOISException.INVALID_PRICE);       
        }
        if (p < 0) throw new IEMOISException(IEMOISException.INVALID_PRICE);
        Course nc = new Course(name, p);
        programs.add(nc);
        courses.put(name.toUpperCase(), nc); 
    }
    
    /**
     * Add a new specialization to the IEMOIS project.
     * @param name Name of the specialization.
     * @param discount Discount of the specialization.
     * @param courses Courses that make up the specialization.
     * @throws IEMOISException SPECIALIZATION_ALREADY_EXISTS, if the a specialization with that name already exists.
     * @throws IEMOISException INVALID_DISCOUNT, if the discount is not a positive number.
     * @throws IEMOISException DISCOUNT_OUT_OF_RANGE, if the discount is greater than 100%.
     * @throws IEMOISException SOME_COURSE_NO_EXIST, if some specialization course does not exist.
     */
    public void addSpecialization(String name, String discount, String courses) throws IEMOISException{ 
        for (Program p: programs){
            if (p instanceof Specialization && p.name().equals(name)) throw new IEMOISException(IEMOISException.SPECIALIZATION_ALREADY_EXISTS);
        }
        int d;
        try {
            d = Integer.parseInt(discount);
        } catch(NumberFormatException e){
            throw new IEMOISException(IEMOISException.INVALID_DISCOUNT);       
        }
        if (d < 0) throw new IEMOISException(IEMOISException.INVALID_DISCOUNT);
        if (d > 100) throw new IEMOISException(IEMOISException.DISCOUNT_OUT_OF_RANGE);
        Specialization s = new Specialization(name,Integer.parseInt(discount));
        String [] aCourses= courses.split("\n");
        for (String p : aCourses){
            if (!this.courses.containsKey(p.toUpperCase())) throw new IEMOISException(IEMOISException.SOME_COURSE_NO_EXIST);
            s.addCourse(this.courses.get(p.toUpperCase()));
        }
        programs.add(s);
    }

    /**
     * Consults the programs that start with a prefix
     * @param prefix Prefix with which you want to do the select.
     * @return A linked list of the programs that start with the prefix.
     */
    public LinkedList<Program> select(String prefix){
        LinkedList <Program> answers = new LinkedList<Program>();
        prefix=prefix.toUpperCase();
        for(int i=0;i<programs.size();i++){
            if(programs.get(i).name().toUpperCase().startsWith(prefix)){
                answers.add(programs.get(i));
            }   
        }
        return answers;
    }
    
    /**
     * Consult selected programs.
     * @param selected A linked list of the selected programs.
     * @return A string with the information of the selected programs.
     */
    public String data(LinkedList<Program> selected){
        StringBuffer answer=new StringBuffer();
        answer.append(selected.size()+ " programas\n");
        int count = 1;
        for(Program p : selected) {
            try{
                answer.append(count+". "+p.data());
                answer.append("\n");
                count++;
            }catch(IEMOISException e){
                answer.append("**** "+e.getMessage());
            }
        }    
        return answer.toString();
    }
    
     /**
     * Return the data of programs with a prefix.
     * @param prefix Prefix with which you want to do the search.
     * @return A string with the information of the selected programs.
     */ 
    public String search(String prefix){
        return data(select(prefix));
    }
    
    
    /**
     * Return the data of all programs.
     * @return Data of the programs in the IEMOIS project.
     */    
    public String toString(){
        return data(programs);
    }
    
    /**
     * Consult the number of Programs.
     * @return Number of programs in the IEMOIS project.
     */
    public int numberPrograms(){
        return programs.size();
    }
}
