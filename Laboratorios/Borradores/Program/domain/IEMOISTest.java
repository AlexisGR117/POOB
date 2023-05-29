package domain;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
* Test class of IEMOIS.
*
* @author Angel Cuervo y Jefer Gonzalez.
* @version 1.0 (25/10/2022)
*/
public class IEMOISTest{
    @Test
    public void shouldCanAddCourses(){   
        IEMOIS proyecto = new IEMOIS();
        try {
            proyecto.addCourse("INTRO TO HTML AND CSS", "1000");
            assertNotNull(proyecto.consult("INTRO TO HTML AND CSS"));
            assertEquals(1000, proyecto.consult("INTRO TO HTML AND CSS").price());
        } catch (IEMOISException e){
            fail("Threw a exception");
        }    
    } 
    
    @Test
    public void shouldCanAddSpecializations(){   
        IEMOIS proyecto = new IEMOIS();
        try {
            proyecto.addCourse("INTRO TO HTML AND CSS", "1000");
            proyecto.addCourse("INTRO TO JAVASCRIPT", "2000");
            proyecto.addCourse("JAVASCRIPT AND THE DOM", "3000");
            proyecto.addSpecialization("FRONT END DEVELOPER", "30", "INTRO TO HTML AND CSS\nINTRO TO JAVASCRIPT\nJAVASCRIPT AND THE DOM");
            assertNotNull(proyecto.consult("FRONT END DEVELOPER"));
            assertEquals(4200, proyecto.consult("FRONT END DEVELOPER").price());
        } catch (IEMOISException e){
            fail("Threw a exception");
        }
    } 
    
    @Test
    public void shouldCanListPrograms(){   
        IEMOIS proyecto = new IEMOIS();
        try {
            proyecto.addCourse("INTRO TO HTML AND CSS", "1000");
            proyecto.addCourse("INTRO TO JAVASCRIPT", "2000");
            proyecto.addCourse("JAVASCRIPT AND THE DOM", "3000");
            proyecto.addSpecialization("FRONT END DEVELOPER", "30", "INTRO TO HTML AND CSS\nINTRO TO JAVASCRIPT\nJAVASCRIPT AND THE DOM");
        } catch (IEMOISException e){
            fail("Threw a exception");
        }
        String l = "10. INTRO TO HTML AND CSS. Precio:1000\n11. INTRO TO JAVASCRIPT. Precio:2000\n12. JAVASCRIPT AND THE DOM. Precio:3000\n13. FRONT END DEVELOPER. Descuento: 30\n\t1. INTRO TO HTML AND CSS : 1000\n\t2. INTRO TO JAVASCRIPT : 2000\n\t3. JAVASCRIPT AND THE DOM : 3000";
        assertTrue(proyecto.toString().contains(l));
    }  
    
    @Test
    public void acceptanceTest(){   
        //Se crea un nuevo proyecto
        IEMOIS proyecto = new IEMOIS();
        try {
            //Se agrega un curso llamado INTRO TO HTML AND CSS con precio 1000.
            proyecto.addCourse("INTRO TO HTML AND CSS", "1000");
        } catch (IEMOISException e){
            fail("Threw a exception");
        }
        //El proyecto debería contener el nuevo curso.
        assertNotNull(proyecto.consult("INTRO TO HTML AND CSS"));
        try {
            //La información del curso debería incluir el nombre y el precio.
            assertEquals("INTRO TO HTML AND CSS. Precio:1000", proyecto.consult("INTRO TO HTML AND CSS").data());
            //El precio de esta curso debería ser de 1000.
            assertEquals(1000, proyecto.consult("INTRO TO HTML AND CSS").price());
        } catch (IEMOISException e){
            fail("Threw a exception");
        }
        //Se agrega una especialización llamada FRONT END DEVELOPER con descuento de 30% y con el curso INTRO TO HTML AND CSS.
        try {
            proyecto.addSpecialization("FRONT END DEVELOPER", "30", "INTRO TO HTML AND CSS");
        } catch (IEMOISException e){
            fail("Threw a exception");
        }
        //El proyecto debería contener la nueva especialización.
        assertNotNull(proyecto.consult("FRONT END DEVELOPER"));
        try {
            //La información de la especialización debería incluir el nombre, descuento y los cursos que tiene.
            assertEquals("FRONT END DEVELOPER. Descuento: 30\n\t1. INTRO TO HTML AND CSS : 1000\n", proyecto.consult("FRONT END DEVELOPER").data());
            //El precio de esta especialización debería ser de 700.
            assertEquals(700, proyecto.consult("FRONT END DEVELOPER").price());
        } catch (IEMOISException e){
            fail("Threw a exception");
        }
        // En total deberían haber 11 programas contando a los que vienen por defecto.
        assertEquals(11, proyecto.numberPrograms());
    }  
    
    @Test
    public void shouldThrowExceptionIfAddACourseWithNameThatAlreadyExists(){   
        IEMOIS proyecto = new IEMOIS();
        try { 
            proyecto.addCourse("INTRO TO HTML AND CSS", "1000");
            proyecto.addCourse("INTRO TO HTML AND CSS", "2000");
            fail("Did not throw exception");
        } catch (IEMOISException e) {
            assertEquals(IEMOISException.COURSE_ALREADY_EXISTS, e.getMessage());
        }    
    }  
    
    @Test
    public void shouldThrowExceptionIfAddACourseWithPriceNull(){   
        IEMOIS proyecto = new IEMOIS();
        try { 
            proyecto.addCourse("INTRO TO HTML AND CSS", null);
            fail("Did not throw exception");
        } catch (IEMOISException e) {
            assertEquals(IEMOISException.INVALID_PRICE, e.getMessage());
        }    
    } 
    
    @Test
    public void shouldThrowExceptionIfAddACourseWithPriceThatIsNotANumber(){   
        IEMOIS proyecto = new IEMOIS();
        try { 
            proyecto.addCourse("INTRO TO HTML AND CSS", "mil");
            fail("Did not throw exception");
        } catch (IEMOISException e) {
            assertEquals(IEMOISException.INVALID_PRICE, e.getMessage());
        }    
    }  
    
    @Test
    public void shouldThrowExceptionIfAddACourseWithNegativePrice(){   
        IEMOIS proyecto = new IEMOIS();
        try { 
            proyecto.addCourse("INTRO TO HTML AND CSS", "-1000");
            fail("Did not throw exception");
        } catch (IEMOISException e) {
            assertEquals(IEMOISException.INVALID_PRICE, e.getMessage());
        }    
    }      
    
    @Test
    public void shouldThrowExceptionIfAddACourseWithoutName(){   
        IEMOIS proyecto = new IEMOIS();
        try { 
            proyecto.addCourse("", "1000");
            fail("Did not throw exception");
        } catch (IEMOISException e) {
            assertEquals(IEMOISException.COURSE_WITHOUT_NAME, e.getMessage());
        }    
    }     
    
    @Test
    public void shouldThrowExceptionIfAddASpecializationWithNameThatAlreadyExists(){   
        IEMOIS proyecto = new IEMOIS();
        try {
            proyecto.addCourse("INTRO TO HTML AND CSS", "1000");
            proyecto.addCourse("INTRO TO JAVASCRIPT", "2000");
            proyecto.addCourse("JAVASCRIPT AND THE DOM", "3000");
            proyecto.addSpecialization("FRONT END DEVELOPER", "30", "INTRO TO HTML AND CSS\nINTRO TO JAVASCRIPT");
            proyecto.addSpecialization("FRONT END DEVELOPER", "40", "JAVASCRIPT AND THE DOM");
            fail("Did not throw exception");
        } catch (IEMOISException e) {
            assertEquals(IEMOISException.SPECIALIZATION_ALREADY_EXISTS, e.getMessage());
        }    
    }  
    
    @Test
    public void shouldThrowExceptionIfAddASpecializationWithDiscountNull(){   
        IEMOIS proyecto = new IEMOIS();
        try { 
            proyecto.addCourse("INTRO TO HTML AND CSS", "1000");
            proyecto.addSpecialization("FRONT END DEVELOPER", null, "INTRO TO HTML AND CSS");
            fail("Did not throw exception");
        } catch (IEMOISException e) {
            assertEquals(IEMOISException.INVALID_DISCOUNT, e.getMessage());
        }    
    } 
    
    @Test
    public void shouldThrowExceptionIfAddASpecializationWithDiscountThatIsNotANumber(){   
        IEMOIS proyecto = new IEMOIS();
        try { 
            proyecto.addCourse("INTRO TO HTML AND CSS", "1000");
            proyecto.addSpecialization("FRONT END DEVELOPER", "treinta", "INTRO TO HTML AND CSS");
            fail("Did not throw exception");
        } catch (IEMOISException e) {
            assertEquals(IEMOISException.INVALID_DISCOUNT, e.getMessage());
        }    
    }  
    
    @Test
    public void shouldThrowExceptionIfAddASpecializationWithNegativeDiscount(){   
        IEMOIS proyecto = new IEMOIS();
        try { 
            proyecto.addCourse("INTRO TO HTML AND CSS", "1000");
            proyecto.addSpecialization("FRONT END DEVELOPER", "-30", "INTRO TO HTML AND CSS");
            fail("Did not throw exception");
        } catch (IEMOISException e) {
            assertEquals(IEMOISException.INVALID_DISCOUNT, e.getMessage());
        }    
    }  
    
    @Test
    public void shouldThrowExceptionIfAddSpecializationWithNonexistentCourse(){   
        IEMOIS proyecto = new IEMOIS();
        try { 
            proyecto.addSpecialization("FRONT END DEVELOPER", "30", "INTRO TO HTML AND CSS");
            fail("Did not throw exception");
        } catch (IEMOISException e) {
            assertEquals(IEMOISException.SOME_COURSE_NO_EXIST, e.getMessage());
        }    
    }  
    
    @Test
    public void shouldThrowExceptionIfAddASpecializationWithDiscountOutOfRange(){   
        IEMOIS proyecto = new IEMOIS();
        try { 
            proyecto.addCourse("INTRO TO HTML AND CSS", "1000");
            proyecto.addSpecialization("FRONT END DEVELOPER", "101", "INTRO TO HTML AND CSS");
            fail("Did not throw exception");
        } catch (IEMOISException e) {
            assertEquals(IEMOISException.DISCOUNT_OUT_OF_RANGE, e.getMessage());
        }    
    }  
    
    @Test
    public void shouldCanSearch(){   
        IEMOIS proyecto = new IEMOIS();
        try {
            proyecto.addCourse("INTRO TO HTML AND CSS", "1000");
            proyecto.addCourse("INTRO TO JAVASCRIPT", "2000");
            assertEquals("2 programas\n1. INTRO TO HTML AND CSS. Precio:1000\n2. INTRO TO JAVASCRIPT. Precio:2000\n", proyecto.search("INTRO TO"));
        } catch (IEMOISException e){
            fail("Threw a exception");
        }
    } 
}
