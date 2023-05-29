package test;
import domain.*;
import static org.junit.Assert.*;
import org.junit.Test;
/**
* Test class of specialization.
*
* @author Angel Cuervo y Jefer Gonzalez.
* @version 1.0 (25/10/2022)
*/
public class SpecializationTest{
    @Test
    public void shouldCalculateTheCostOfASpecialization(){
        Specialization s = new Specialization("FRONT END DEVELOPER", 10);
        s.addCourse(new Course("INTRO TO HTML AND CSS", 1000));
        s.addCourse(new Course("INTRO TO JAVASCRIPT",8000));
        s.addCourse(new Course("JAVASCRIPT AND THE DOM", 1000));
        try {
           assertEquals(9000,s.price());
        } catch (IEMOISException e){
            fail("Threw a exception");
        }    
    }    
    
    @Test
    public void shouldThrowExceptionIfSpecializationHasNoCourses(){
        Specialization s = new Specialization("FRONT END DEVELOPER", 10);
        try { 
           int price=s.price();
           fail("Did not throw exception");
        } catch (IEMOISException e) {
            assertEquals(IEMOISException.SPECIALIZATION_EMPTY, e.getMessage());
        }    
    }    
    
    @Test
    public void shouldThrowExceptionIfThereIsErrorInPrice(){
        Specialization s = new Specialization("FRONT END DEVELOPER", 10);
        s.addCourse(new Course("INTRO TO HTML AND CSS", 1000));
        s.addCourse(new Course("INTRO TO JAVASCRIPT",8000));
        s.addCourse(new Course("JAVASCRIPT AND THE DOM", -1000));
        try { 
           int price=s.price();
           fail("Did not throw exception");
        } catch (IEMOISException e) {
            assertEquals(IEMOISException.COURSE_ERROR_PRICE, e.getMessage());
        }    
    }     
    
    @Test
    public void shouldThrowExceptionIfPriceIsNotKnown(){
        Specialization s = new Specialization("FRONT END DEVELOPER", 10);
        s.addCourse(new Course("INTRO TO HTML AND CSS", 1000));
        s.addCourse(new Course("INTRO TO JAVASCRIPT",null));
        s.addCourse(new Course("JAVASCRIPT AND THE DOM", -1000));
        try { 
           int price=s.price();
           fail("Did not throw exception");
        } catch (IEMOISException e) {
            assertEquals(IEMOISException.COURSE_NO_PRICE, e.getMessage());
        }    
    }  
    
    @Test
    public void shouldCalculateTheDefaultPriceOfASpecialization(){
        Specialization s = new Specialization("FRONT END DEVELOPER", 30);
        s.addCourse(new Course("INTRO TO HTML AND CSS", 1000));
        s.addCourse(new Course("INTRO TO JAVASCRIPT", null));
        s.addCourse(new Course("JAVASCRIPT AND THE DOM", 0));
        try {
           assertEquals(14700,s.defaultPrice());
        } catch (IEMOISException e){
            fail("Threw a exception");
        }    
    }    
    
    @Test
    public void shouldThrowExceptionInCalculateTheDefaultPriceIfSpecializationHasNoCourses(){
        Specialization s = new Specialization("FRONT END DEVELOPER", 30);
        try { 
           int price=s.defaultPrice();
           fail("Did not throw exception");
        } catch (IEMOISException e) {
            assertEquals(IEMOISException.SPECIALIZATION_EMPTY, e.getMessage());
        }    
    }   
    
    @Test
    public void shouldCalculateThePriceOfACourseOfASpecialization(){
        Specialization s = new Specialization("FRONT END DEVELOPER", 30);
        s.addCourse(new Course("INTRO TO HTML AND CSS", 1000));
        try {
           assertEquals(700,s.price("INTRO TO HTML AND CSS"));
        } catch (IEMOISException e){
            fail("Threw a exception");
        }    
    }    
    
    @Test
    public void shouldThrowExceptionIfExistTwoCoursesWithTheSameName(){
        Specialization s = new Specialization("FRONT END DEVELOPER", 30);
        s.addCourse(new Course("INTRO TO HTML AND CSS", 1000));
        s.addCourse(new Course("INTRO TO HTML AND CSS", 3000));
        try { 
           int price=s.price("INTRO TO HTML AND CSS");
           fail("Did not throw exception");
        } catch (IEMOISException e) {
            assertEquals(IEMOISException.TWO_COURSES, e.getMessage());
        }    
    }    
    
    @Test
    public void shouldThrowExceptionIfTheCourseNoExist(){
        Specialization s = new Specialization("FRONT END DEVELOPER", 30);
        try { 
           int price=s.price("INTRO TO HTML AND CSS");
           fail("Did not throw exception");
        } catch (IEMOISException e) {
            assertEquals(IEMOISException.COURSE_NO_EXIST, e.getMessage());
        }    
    }    
}
