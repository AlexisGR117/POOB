package icpc;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;
import java.util.*;
/**
 * The test class ICPCC4test.
 *
 * @author Angel Cuervo y Jefer Gonzalez.
 * @version 1.0 (17/10/2022)
 */
public class ICPCC4test{
    @Test
    public void noDeberiaPoderPonerInterseccionesConTiposQueNoExisten(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("ermit", "orange", 100, 800);
        assertFalse(red.ok());
        assertFalse(red.getIntersections().containsKey("orange"));
        assertEquals(0, red.intersections().length);
    }
    
    @Test
    public void deberiaPoderPonerInterseccionesHermit(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("Hermit", "orange", 100, 800);
        assertTrue(red.ok());
        assertTrue(red.getIntersections().containsKey("orange"));
        assertTrue(red.getIntersections().get("orange") instanceof Hermit);
        assertEquals(1, red.intersections().length);
        assertEquals("orange", red.getIntersections().get("orange").getColor());
        assertEquals("blue", red.getIntersections().get("orange").getBackColor());
        assertEquals(100, red.getIntersections().get("orange").getXPosition());
        assertEquals(800, red.getIntersections().get("orange").getYPosition());
    }
        
    @Test
    public void noDeberiaPoderPonerUnaRutaSiLaPrimeraInterseccionEsHermitYTieneUnaRuta(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("Hermit", "orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addIntersection("red", 300, 500);
        red.addRoute("orange", "green");
        red.addRoute("orange", "red");
        assertFalse(red.ok());
        assertEquals(1, red.roads().length);
    }
    
    @Test
    public void noDeberiaPoderPonerUnaRutaSiLaSegundaInterseccionEsHermitYTieneUnaRuta(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("Hermit", "orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addIntersection("red", 300, 500);
        red.addRoute("orange", "green");
        red.addRoute("red", "orange");
        assertFalse(red.ok());
        assertEquals(1, red.roads().length);
    }  
    
    @Test
    public void noDeberiaPoderPonerUnaRutaConTipoSiLaPrimeraInterseccionEsHermitYTieneUnaRuta(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("Hermit", "orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addIntersection("red", 300, 500);
        red.addRoute("orange", "green");
        red.addRoute("Rebel", "orange", "red");
        assertFalse(red.ok());
        assertEquals(1, red.roads().length);
    }
    
    @Test
    public void noDeberiaPoderPonerUnaRutaConTipoSiLaSegundaInterseccionEsHermitYTieneUnaRuta(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("Hermit", "orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addIntersection("red", 300, 500);
        red.addRoute("orange", "green");
        red.addRoute("Fixed", "red", "orange");
        assertFalse(red.ok());
        assertEquals(1, red.roads().length);
    }   
    
    @Test
    public void deberiaPoderPonerInterseccionesNeedy(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("Needy", "orange", 500, 800);
        assertTrue(red.ok());
        assertTrue(red.getIntersections().containsKey("orange"));
        assertTrue(red.getIntersections().get("orange") instanceof Needy);
        assertEquals(1, red.intersections().length);
        assertEquals("orange", red.getIntersections().get("orange").getColor());
        assertEquals("red", red.getIntersections().get("orange").getBackColor());
        assertEquals(500, red.getIntersections().get("orange").getXPosition());
        assertEquals(800, red.getIntersections().get("orange").getYPosition());
    }
    
    @Test
    public void deberiaEliminarseLaInterseccionSiEsNeedyYSeQuedaSinRutasAlEliminarRutas(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("Needy", "orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addIntersection("red", 300, 500);
        red.addRoute("orange", "green");
        red.addRoute("red", "orange");
        red.delRoute("orange", "green");
        red.delRoute("red", "orange");
        assertTrue(red.ok());
        assertFalse(red.getIntersections().containsKey("orange"));
        assertEquals(2, red.intersections().length);
    }   
    
    @Test
    public void deberiaEliminarseLaInterseccionSiEsNeedyYSeQuedaSinRutasAlEliminarIntersecciones(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("Needy", "orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("orange", "green");
        red.delIntersection("green");
        assertTrue(red.ok());
        assertFalse(red.getIntersections().containsKey("orange"));
        assertEquals(0, red.intersections().length);
    }   
    
    @Test
    public void noDeberiaPoderCrearRutasConTiposQueNoExisten(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("Rebelde", "green", "orange");
        assertFalse(red.ok());
        assertFalse(red.getRoads().containsKey("green-orange"));
        assertEquals(0, red.roads().length);
    }
    
    @Test
    public void deberiaPoderCrearRutasRebel(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("Rebel", "green", "orange");
        assertTrue(red.ok());
        assertTrue(red.getRoads().containsKey("green-orange"));
        assertTrue(red.getRoads().get("green-orange") instanceof Rebel);
        assertEquals("orange", red.getRoads().get("green-orange").getBackColor());
        assertEquals(1, red.roads().length);
    }
    
    @Test
    public void noDeberiaDejarPonerSeñalesARutasRebel(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("Rebel", "orange", "green");
        red.routeSpeedLimit("orange", "green", 15);
        red.putSign("orange", "green", 15);
        assertFalse(red.ok());
        assertFalse(red.getSigns().containsKey("orange-green"));
        assertEquals(0, red.signs().length);
    }   
    
    @Test
    public void deberiaPoderCrearRutasFixed(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 200, 300);
        red.addIntersection("green", 300, 200);
        red.addRoute("Fixed", "green", "orange");
        assertTrue(red.ok());
        assertTrue(red.getRoads().containsKey("green-orange"));
        assertTrue(red.getRoads().get("green-orange") instanceof Fixed);
        assertEquals("red", red.getRoads().get("green-orange").getBackColor());
        assertEquals(1, red.roads().length);
    }
    
    @Test
    public void noDeberiaDejarQuitarRutasFixed(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("Fixed", "orange", "green");
        red.delRoute("orange", "green");
        assertFalse(red.ok());
        assertTrue(red.getRoads().containsKey("green-orange"));
        assertEquals(1, red.roads().length);
    }   
    
    @Test
    public void noDeberiaDejarQuitarRutasFixedAlEliminarIntersecciones(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("Fixed", "orange", "green");
        red.delIntersection("orange");
        assertFalse(red.ok());
        assertTrue(red.getRoads().containsKey("green-orange"));
        assertEquals(1, red.roads().length);
    } 
    
    @Test
    public void noDeberiaPoderPonerSeñalesConTiposQueNoExisten(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("green", "orange");
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("Unica", "green", "orange", 13);
        assertFalse(red.ok());
        assertFalse(red.getSigns().containsKey("green-orange"));
        assertEquals(0, red.signs().length);   
    }
    
    @Test
    public void deberiaPoderPonerSeñalesTwin(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("green", "orange");
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("Twin", "green", "orange", 13);
        assertTrue(red.ok());
        assertTrue(red.getSigns().containsKey("green-orange"));
        assertTrue(red.getSigns().get("green-orange") instanceof Twin);
        assertEquals(13, red.getSigns().get("green-orange").getNumber().getNumber());
        assertEquals("yellow", red.getSigns().get("green-orange").getColor());
        assertEquals(2, red.signs().length);   
    }
    
    @Test
    public void deberiaPonerseOtrasSeñalTwinAlPonerUnaTwin(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("orange", "green");
        red.routeSpeedLimit("orange", "green", 15);
        red.putSign("Twin", "orange", "green", 15);
        assertTrue(red.ok());
        assertTrue(red.getSigns().containsKey("green-orange"));
        assertEquals(2, red.signs().length);
    }   
    
    @Test
    public void noDeberiaPonerseUnaSeñalTwinSiAlOtroExtremoYaExisteUna(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("orange", "green");
        red.routeSpeedLimit("orange", "green", 10);
        red.putSign("orange", "green", 10);
        red.putSign("Twin", "green", "orange", 15);
        assertFalse(red.ok());
        assertFalse(red.getSigns().containsKey("green-orange"));
        assertEquals(10, red.getSigns().get("orange-green").getNumber().getNumber());
        assertEquals(1, red.signs().length);
    }  
    
    @Test
    public void deberiaEliminarseLasDosSeñalesTwinsAlEliminarseAlgunaDeLasDos(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("orange", "green");
        red.routeSpeedLimit("orange", "green", 10);
        red.putSign("Twin", "green", "orange", 15);
        red.removeSign("green", "orange");
        assertTrue(red.ok());
        assertFalse(red.getSigns().containsKey("green-orange"));
        assertFalse(red.getSigns().containsKey("orange-green"));
        assertEquals(0, red.signs().length);
    }   
    
    @Test
    public void deberiaPoderPonerSeñalesCautious(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("green", "orange");
        red.routeSpeedLimit("green", "orange", 10);;
        red.putSign("Cautious", "green", "orange", 13);
        assertTrue(red.ok());
        assertTrue(red.getSigns().containsKey("green-orange"));
        assertTrue(red.getSigns().get("green-orange") instanceof Cautious);
        assertEquals(10, red.getSigns().get("green-orange").getNumber().getNumber());
        assertEquals("lightGray", red.getSigns().get("green-orange").getColor());
        assertEquals(1, red.signs().length);   
    }
    
    @Test
    public void deberiaAjustarseElLimiteDeVelocidadDeUnaSeñalCautiousAlLimiteMinimoDeLaRuta(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("orange", "green");
        red.routeSpeedLimit("orange", "green", 10);
        red.putSign("Cautious", "green", "orange", 15);
        assertTrue(red.ok());
        assertEquals(10, red.getSigns().get("green-orange").getNumber().getNumber());
    }   
    
    @Test
    public void deberiaPoderPonerSeñalesUnique(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("green", "orange");
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("Unique", "green", "orange", 13);
        assertTrue(red.ok());
        assertTrue(red.getSigns().containsKey("green-orange"));
        assertTrue(red.getSigns().get("green-orange") instanceof Unique);
        assertEquals(13, red.getSigns().get("green-orange").getNumber().getNumber());
        assertEquals("orange", red.getSigns().get("green-orange").getColor());
        assertEquals(1, red.signs().length);   
    }
    
    @Test
    public void noDeberiaPonerseUnaSeñalUniqueSiHayUnaSeñalEnLaRuta(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("orange", "green");
        red.routeSpeedLimit("orange", "green", 10);
        red.putSign("orange", "green", 10);
        red.putSign("Unique", "green", "orange", 15);
        assertFalse(red.ok());
        assertFalse(red.getSigns().containsKey("green-orange"));
        assertEquals(10, red.getSigns().get("orange-green").getNumber().getNumber());
        assertEquals(1, red.signs().length);
    }  
    
    @Test
    public void noDeberiaDejarPonerNingunaSeñalEnUnaRutaQueTengaUnaSeñalUnique(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("orange", "green");
        red.routeSpeedLimit("orange", "green", 10);
        red.putSign("Unique", "green", "orange", 15);
        red.putSign("orange", "green", 12);
        assertFalse(red.ok());
        assertFalse(red.getSigns().containsKey("orange-green"));
        assertEquals(1, red.signs().length);
    }  
    
    @Test
    public void deberiaSerNecesariaLaSeñalUniqueSiEnCualquieraDeLasDosInterseccionesHayUnCambioDeLimite(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addIntersection("red", 0, 300);
        red.addRoute("orange", "green");
        red.routeSpeedLimit("orange", "green", 20);
        red.addRoute("orange", "red");
        red.routeSpeedLimit("orange", "red", 10);
        red.putSign("Unique", "red", "orange", 15);
        assertTrue(red.ok());
        assertFalse(red.getUnNecessarySigns().contains("red-orange"));
    }   
} 