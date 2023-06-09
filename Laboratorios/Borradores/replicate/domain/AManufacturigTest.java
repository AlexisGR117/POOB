package domain;
import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.Color;
/**
 * The test class AManufacturigTest.
 *
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (05/10/2022)
 */
public class AManufacturigTest{
    @Test
    public void deberiaPoderCrearCelulas(){
        AManufacturing am = new AManufacturing();
        Cell simba = new Cell(am, 1, 1, true);
        assertEquals(1, simba.getRow());
        assertEquals(1, simba.getColumn());
        assertTrue(simba.isActive());
        assertEquals(simba, am.getThing(1, 1));
    }
    @Test
    public void ticTacCelulas(){
        AManufacturing am = new AManufacturing();
        Cell simba = new Cell(am, 1, 1, true);
        Cell dala = new Cell(am, 2, 2, true);
        am.ticTac();
        assertTrue(simba.isActive());
        assertTrue(dala.isActive());
        am.ticTac();
        assertFalse(simba.isActive());
        assertFalse(dala.isActive());
        am.ticTac();
        assertFalse(simba.isActive());
        assertFalse(dala.isActive());
    }
    @Test
    public void deberiaPoderCrearCelulasMimo(){
        AManufacturing am = new AManufacturing();
        Cell mufasa = new Mimo(am, 10, 5, true);
        assertEquals(10, mufasa.getRow());
        assertEquals(5, mufasa.getColumn());
        assertEquals(true, mufasa.isActive());
        assertEquals(Color.orange, mufasa.getColor());
        assertEquals(mufasa, am.getThing(10, 5));
    }
    @Test
    public void ticTacCelulasMimo(){
        AManufacturing am = new AManufacturing();
        Mimo mufasa = new Mimo(am, 10, 5, true);
        Mimo scar = new Mimo(am, 10, 6, true);
        Mimo rafiki = new Mimo(am, 10, 7, true);
        am.ticTac();
        assertTrue(mufasa.isActive());
        assertTrue(scar.isActive());
        assertTrue(rafiki.isActive());
        am.ticTac();
        assertTrue(mufasa.isActive());
        assertTrue(scar.isActive());
        assertTrue(rafiki.isActive());
        am.ticTac();
        assertTrue(mufasa.isActive());
        assertTrue(scar.isActive());
        assertTrue(rafiki.isActive());
    }
    @Test
    public void deberiaPoderCrearCelulasKriptonita(){
        AManufacturing am = new AManufacturing();
        Kriptonita one = new Kriptonita(am, 0, 0, true);
        assertEquals(0, one.getRow());
        assertEquals(0, one.getColumn());
        assertTrue(one.isActive());
        assertEquals(Color.green, one.getColor());
        assertEquals(1, one.shape());
        assertEquals(one, am.getThing(0, 0));
    }
    @Test
    public void ticTacCelulasKriptonita(){
        AManufacturing am = new AManufacturing();
        int size = am.getSize();
        Kriptonita one = new Kriptonita(am, 0, 0, true);
        Kriptonita two = new Kriptonita(am, 0, size-1, true);
        Kriptonita three = new Kriptonita(am, size-1, 0, true);
        Kriptonita four = new Kriptonita(am, size-1, size-1, true);
        am.ticTac();
        int count = 0;
        for(int r = 0; r < size; r++) for (int c = 0; c < size; c++){
            if (!am.isEmpty(r, c) && am.getThing(r, c).isActive()) count ++;    
        }
        assertEquals(16, count);
        am.ticTac();
        count = 0;
        for(int r = 0; r < size; r++) for (int c = 0; c < size; c++){
            if (!am.isEmpty(r, c) && am.getThing(r, c).isActive()) count ++;    
        }
        assertEquals(36, count);
        am.ticTac();
        count = 0;
        for(int r = 0; r < size; r++) for (int c = 0; c < size; c++){
            if (!am.isEmpty(r, c) && am.getThing(r, c).isActive()) count ++;    
        }
        assertEquals(64, count);
    }
    @Test
    public void deberiaPoderCrearCelulasTimidas(){
        AManufacturing am = new AManufacturing();
        Timida one = new Timida(am, 25, 25, true);
        assertEquals(25, one.getRow());
        assertEquals(25, one.getColumn());
        assertTrue(one.isActive());
        assertEquals(Color.gray, one.getColor());
        assertEquals(1, one.shape());
        assertEquals(one, am.getThing(25, 25));
    }
    @Test
    public void deberianDesactivarseLasTimidasCuandoTienenMasDe3VecinosActivos(){
        AManufacturing am = new AManufacturing();
        Timida angel = new Timida(am, 25, 25, true);
        Timida alexis = new Timida(am, 24, 25, true);
        Kriptonita clark = new Kriptonita(am, 24, 23, true);
        am.ticTac();
        assertTrue(angel.isActive());
        assertTrue(alexis.isActive());
        am.ticTac();
        assertTrue(angel.isActive());
        assertFalse(alexis.isActive());
        am.ticTac();
        assertFalse(angel.isActive());
        assertFalse(alexis.isActive());
    }
    @Test
    public void deberianActivarseLasTimidasCuandoTienenMenosDe4VecinosActivos(){
        AManufacturing am = new AManufacturing();
        Timida angel = new Timida(am, 25, 25, true);
        Timida alexis = new Timida(am, 24, 25, true);
        Cell simba = new Cell(am, 23, 25, true);
        Cell dala = new Cell(am, 24, 24, true);
        Cell mufasa = new Cell(am, 23, 24, true);
        Cell scar = new Cell(am, 25, 26, true);
        Cell rafiki = new Cell(am, 26, 25, true);
        am.ticTac();
        assertFalse(angel.isActive());
        assertFalse(alexis.isActive());
        am.ticTac();
        assertTrue(angel.isActive());
        assertFalse(alexis.isActive());
        am.ticTac();
        assertTrue(angel.isActive());
        assertTrue(alexis.isActive());
    }
    @Test
    public void deberiaPoderCrearCelulasVirus(){
        AManufacturing am = new AManufacturing();
        Virus virus = new Virus(am, 25, 25);
        assertEquals(25, virus.getRow());
        assertEquals(25, virus.getColumn());
        assertTrue(virus.isActive());
        assertEquals(Color.red, virus.getColor());
        assertEquals(1, virus.shape());
        assertEquals(virus, am.getThing(25, 25));
    }
    @Test
    public void deberianDesactivarseLosVirusCuandoTienenTodasLasCeldasVecinasVirus(){
        AManufacturing am = new AManufacturing();
        Virus virusOne = new Virus(am, 25, 25);
        Virus virusTwo = new Virus(am, 25, 26);
        Kriptonita clark = new Kriptonita(am, 27, 27, true);
        assertNull(am.getThing(26, 26));
        assertNull(am.getThing(26, 27));
        am.ticTac();
        assertTrue(am.getThing(26, 26) instanceof Kriptonita);
        assertTrue(am.getThing(26, 26).isActive());
        assertTrue(am.getThing(26, 27) instanceof Kriptonita);
        assertTrue(am.getThing(26, 27).isActive());
        am.ticTac();
        assertTrue(am.getThing(26, 26) instanceof Virus);
        assertTrue(am.getThing(26, 26).isActive());
        assertTrue(am.getThing(26, 27) instanceof Virus);
        assertTrue(am.getThing(26, 27).isActive());
        am.ticTac();
        assertFalse(am.getThing(26, 26).isActive());
        assertFalse(am.getThing(26, 27).isActive());
    }
    @Test
    public void noDeberianInfectarUnVirusALosVecinosInactivos(){
        AManufacturing am = new AManufacturing();
        Virus virusOne = new Virus(am, 25, 25);
        Virus virusTwo = new Virus(am, 25, 26);
        Kriptonita clark = new Kriptonita(am, 26, 26, false);
        Kriptonita kent = new Kriptonita(am, 27, 26, false);
        Timida timida = new Timida(am, 27, 25, true);
        assertFalse(clark.isActive());
        assertTrue(timida.isActive());
        am.ticTac();
        assertTrue(clark.isActive());
        assertTrue(timida.isActive());
        am.ticTac();
        assertTrue(am.getThing(26, 26) instanceof Virus);
        assertFalse(timida.isActive());
        am.ticTac();
        assertTrue(am.getThing(27, 25) instanceof Timida);
        assertFalse(timida.isActive());
    }
    @Test
    public void deberiaPoderCrearCelulasRfplicbte (){
        AManufacturing am = new AManufacturing();
        Rfplicbte rfplicbte = new Rfplicbte(am, 25, 25, true);
        assertEquals(25, rfplicbte.getRow());
        assertEquals(25, rfplicbte.getColumn());
        assertTrue(rfplicbte.isActive());
        assertEquals(Color.blue, rfplicbte.getColor());
        assertEquals(2, rfplicbte.shape());
        assertEquals(rfplicbte, am.getThing(25, 25));
    }
    @Test
    public void ticTacCelulasRfplicbte(){
        AManufacturing am = new AManufacturing();
        int size = am.getSize();
        Rfplicbte one = new Rfplicbte(am, 25, 25, true);
        Rfplicbte two = new Rfplicbte(am, 25, 24, true);
        Rfplicbte three = new Rfplicbte(am, 24, 25, true);
        am.ticTac();
        int count = 0;
        int count2 = 0;
        for(int r = 0; r < size; r++) for (int c = 0; c < size; c++){
            if (!am.isEmpty(r, c)){
              if (am.getThing(r, c).isActive()) count++;  
              else count2 ++;
            }   
        }
        assertEquals(11, count);
        assertEquals(4, count2);
        am.ticTac();
        count = 0;
        count2 = 0;
        for(int r = 0; r < size; r++) for (int c = 0; c < size; c++){
            if (!am.isEmpty(r, c)){
              if (am.getThing(r, c).isActive()) count++;  
              else count2 ++;
            }   
        }
        assertEquals(8, count2);
        assertEquals(27, count);
        am.ticTac();
        count = 0;
        count2 = 0;
        for(int r = 0; r < size; r++) for (int c = 0; c < size; c++){
            if (!am.isEmpty(r, c)){
              if (am.getThing(r, c).isActive()) count++;  
              else count2 ++;
            }   
        }
        assertEquals(20, count2);
        assertEquals(43, count);
        am.ticTac();
        count = 0;
        count2 = 0;
        for(int r = 0; r < size; r++) for (int c = 0; c < size; c++){
            if (!am.isEmpty(r, c)){
              if (am.getThing(r, c).isActive()) count++;  
              else count2 ++;
            }   
        }
        assertEquals(72, count2);
        assertEquals(27, count);
    }
}
