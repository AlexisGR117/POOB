package icpc;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;
import java.util.*;
/**
 * The test class ICPCC2Test.
 *
 * @author Angel Cuervo y Jefer Gonzalez.
 * @version 1.0 (18/09/2022)
 */
public class ICPCC2Test{
    @Test
    public void deberiaPoderCrearRedes(){
        ICPC red = new ICPC(600, 800);
        assertTrue(red.ok());
        assertEquals(600, red.getLength());
        assertEquals(800, red.getWidth());
    } 

    @Test
    public void deberiaPoderCrearRedesConCosto(){
        ICPC red = new ICPC(900, 300, 12);
        assertTrue(red.ok());
        assertEquals(900, red.getLength());
        assertEquals(300, red.getWidth());
        assertEquals(12, red.getSignalCost());
    }
    
    @Test
    public void deberiaCrearRedesConInformacion(){
        int[][] routesSpeedLimits = {{1, 2, 10}, {1, 3, 5}, {1, 4, 7}, {2, 5, 9}};
        // 1-red, 2-orange, 3-yellow, 4-green, 5-cyan, 6-magenta, 7-pink
        // 8-white, 9-blue, 10-lightGray, 11-gray, 12-darkGray, 13-black.
        // Las llaves de los HashMap que guardan las rutas y las señales son la concatenación de 
        // las interesecciones ordenadas alfabéticamente y separadas por un "-". Ejemplo: "orange-red".
        ICPC red = new ICPC(10, routesSpeedLimits);
        assertTrue(red.ok());
        assertEquals(950, red.getLength());
        assertEquals(950, red.getWidth());
        assertEquals(10, red.getSignalCost());
        assertEquals(5, red.intersections().length);
        assertEquals(4, red.roads().length);
        assertEquals(10, red.getRoads().get("orange-red").getSpeedLimit());
        assertEquals(5, red.getRoads().get("red-yellow").getSpeedLimit());
        assertEquals(7, red.getRoads().get("green-red").getSpeedLimit());
        assertEquals(9, red.getRoads().get("cyan-orange").getSpeedLimit()); 
    }  
    
    @Test
    public void noDeberiaCrearRedesConUnAnchoMenorA200(){
        // Los rangos que establecimos son 200 <= width <= 950 y 200 <= length <= 950
        ICPC red = new ICPC(500, -100);
        assertFalse(red.ok());
        assertEquals(500, red.getLength());
        assertEquals(950, red.getWidth());
    }
    
    @Test
    public void noDeberiaCrearRedesConUnAnchoMayorA950(){
        ICPC red = new ICPC(500, 1000, 10);
        assertFalse(red.ok());
        assertEquals(500, red.getLength());
        assertEquals(950, red.getWidth());
        assertEquals(10, red.getSignalCost());
    }
    
    @Test
    public void noDeberiaCrearRedesConUnLargoMenorA200(){
        ICPC red = new ICPC(0, 500, 10);
        assertFalse(red.ok());
        assertEquals(950, red.getLength());
        assertEquals(500, red.getWidth());
        assertEquals(10, red.getSignalCost());
    }
    
    @Test
    public void noDeberiaCrearRedesConUnLargoMayorA950(){
        ICPC red = new ICPC(2000, 500);
        assertFalse(red.ok());
        assertEquals(950, red.getLength());
        assertEquals(500, red.getWidth());        
    }
    
    @Test
    public void noDeberiaCrearRedesConCostosDeLasSeñalesMenoresA1(){
        // Rango para el costo de la señales dado por el problema B: 1 <= length <= 100000
        ICPC red1 = new ICPC(500, 500, -10);
        assertFalse(red1.ok());
        assertEquals(10, red1.getSignalCost());
        int[][] routesSpeedLimits = {};
        ICPC red2 = new ICPC(-1, routesSpeedLimits);
        assertFalse(red2.ok());
        assertEquals(10, red2.getSignalCost());        
    }
    
    @Test
    public void noDeberiaCrearRedesConCostosDeLasSeñalesMayoresA100000(){
        ICPC red1 = new ICPC(500, 900, 100001);
        assertFalse(red1.ok());
        assertEquals(10, red1.getSignalCost());
        int[][] routesSpeedLimits = {};
        ICPC red2 = new ICPC(1000001, routesSpeedLimits);
        assertFalse(red2.ok());
        assertEquals(10, red2.getSignalCost()); 
    }
    
    @Test
    public void noDeberiaCrearRedesConInformacionIncompleta(){
        int[][] routesSpeedLimits1 = {{2, 3}, {3, 1, 6}};
        ICPC red1 = new ICPC(10, routesSpeedLimits1);
        assertFalse(red1.ok());
        assertEquals(0, red1.intersections().length);
        assertEquals(0, red1.roads().length);
        int[][] routesSpeedLimits2 = {{2, 3, 10}, {3}, {4, 5, 2}};
        ICPC red2 = new ICPC(10, routesSpeedLimits2);
        assertFalse(red2.ok());
        assertEquals(0, red2.intersections().length);
        assertEquals(0, red2.roads().length);
        int[][] routesSpeedLimits3 = {{2, 3, 10}, {3, 1, 6}, {}};
        ICPC red3 = new ICPC(10, routesSpeedLimits3);
        assertFalse(red3.ok());
        assertEquals(0, red3.intersections().length);
        assertEquals(0, red3.roads().length);
    }
    
    @Test
    public void noDeberiaCrearRedesLimitesDeVelocidadMenoresA1(){
        // Rango para los límites de velocidad de la rutas dado por el problema B: 1 <= speedLimit <= 100000
        int[][] routesSpeedLimits = {{2, 3, 10}, {4, 5, 0}};
        ICPC red = new ICPC(10, routesSpeedLimits);
        assertFalse(red.ok());
        assertEquals(0, red.intersections().length);
        assertEquals(0, red.roads().length);        
    }
    
    @Test
    public void noDeberiaCrearRedesConLimitesDeVelocidadMayoresA100000(){
        int[][] routesSpeedLimits = {{2, 3, 100001}, {3, 1, 6}};
        ICPC red = new ICPC(10, routesSpeedLimits);
        assertFalse(red.ok());
        assertEquals(0, red.intersections().length);
        assertEquals(0, red.roads().length);  
    }
    
    @Test
    public void deberiaPoderCrearIntersecciones(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 200);
        assertTrue(red.ok());
        assertTrue(red.getIntersections().containsKey("orange"));
        assertEquals(1, red.intersections().length);
        assertEquals("orange", red.getIntersections().get("orange").getColor());
        assertEquals(100, red.getIntersections().get("orange").getXPosition());
        assertEquals(200, red.getIntersections().get("orange").getYPosition());
    }
    
    @Test
    public void noDeberiaPermitirCrearInterseccionesConPosicionHorizontalMayorAlAnchoDeLaRed(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 951, 200);
        assertFalse(red.ok());
        assertEquals(0, red.intersections().length);
        assertNull(red.getIntersections().get("orange"));
    }
    
    @Test
    public void noDeberiaPermitirCrearInterseccionesConPosicionHorizontalMenorA0(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", -1, 200);
        assertFalse(red.ok());
        assertEquals(0, red.intersections().length);
        assertNull(red.getIntersections().get("orange"));
    }
    
    @Test
    public void noDeberiaPermitirCrearInterseccionesConPosicionVerticalMayorAlLargoDeLaRed(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 951);
        assertFalse(red.ok());
        assertEquals(0, red.intersections().length);
        assertNull(red.getIntersections().get("orange"));
    }
    
    @Test
    public void noDeberiaPermitirCrearInterseccionesConVerticalHorizontalMenorA0(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, -1);
        assertFalse(red.ok());
        assertEquals(0, red.intersections().length);
        assertNull(red.getIntersections().get("orange"));
    }
    
    @Test
    public void noDeberiaPermitirCrearInterseccionesConColoresQueNoExistenEnCanvas(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("purple", 100, 200);
        assertFalse(red.ok());
        assertEquals(0, red.intersections().length);
        assertNull(red.getIntersections().get("purple"));
    }
    
    @Test
    public void noDeberiaPermitirCrearInterseccionesConColoresQueYaEstenUsados(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 200);
        red.addIntersection("orange", 300, 500);
        assertFalse(red.ok());
        assertEquals(1, red.intersections().length);
        assertEquals(100, red.getIntersections().get("orange").getXPosition());
        assertEquals(200, red.getIntersections().get("orange").getYPosition());
    }
    
    @Test
    public void noDeberiaPermitirCrearInterseccionesConPosicionIgualAOtraInterseccion(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 200);
        red.addIntersection("red", 100, 200);
        assertFalse(red.ok());
        assertEquals(1, red.intersections().length);
        assertNull(red.getIntersections().get("red"));
    }
    
    @Test
    public void deberiaPoderCrearRutas(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("green", "orange");
        assertTrue(red.ok());
        assertTrue(red.getRoads().containsKey("green-orange"));
        assertEquals(1, red.roads().length);
    }
    
    @Test
    public void noDeberiaPermitirCrearRutasConInterseccionesDelMismoColor(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addRoute("orange", "orange");
        assertFalse(red.ok());
        assertEquals(0, red.roads().length);
        assertFalse(red.getRoads().containsKey("orange-orange"));
    }

    @Test
    public void noDeberiaPermitirCrearRutasConLaPrimeraInterseccionInexistente(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("purple", "orange");        
        assertFalse(red.ok());
        assertEquals(0, red.roads().length);
        assertFalse(red.getRoads().containsKey("purple-orange"));   
    }
    
    @Test
    public void noDeberiaPermitirCrearRutasConLaSegundaInterseccionInexistente(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("green", "purple");        
        assertFalse(red.ok());
        assertEquals(0, red.roads().length);
        assertFalse(red.getRoads().containsKey("green-purple")); 
    }
    
    @Test
    public void noDeberiaPermitirCrearRutasQueYaExistan(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("green", "orange");
        red.addRoute("green", "orange");        
        assertFalse(red.ok());
        assertEquals(1, red.roads().length);      
    }
    
    @Test
    public void noDeberiaPermitirCrearRutasQueSeanInnecesarias(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addIntersection("red", 200, 400);
        red.addRoute("green", "orange");
        red.addRoute("green", "red");      
        red.addRoute("orange", "red"); 
        assertFalse(red.ok());
        assertEquals(2, red.roads().length); 
        assertFalse(red.getRoads().containsKey("orange-red")); 
    }
    
    @Test
    public void deberiaPoderAsignarLimitesDeVelocidad(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("orange", "green");
        red.routeSpeedLimit("green", "orange", 10);
        assertTrue(red.ok());
        assertEquals(10, red.getRoads().get("green-orange").getSpeedLimit());
    }
    
    @Test
    public void noDeberiaPermitirAsignarLimitesDeVelocidadACarreterasInexistentes(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("green", "orange");
        red.routeSpeedLimit("green", "blue", 10);
        assertFalse(red.ok());
        assertNull(red.getRoads().get("blue-green"));
    }

    @Test
    public void noDeberiaPermitirAsignarLimitesDeVelocidadMenoresA1(){
        // Rango para los límites de velocidad de la rutas dado por el problema B: 1 <= speedLimit <= 100000
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("green", "orange");
        red.routeSpeedLimit("green", "orange", -100);
        assertFalse(red.ok());
        assertEquals(0, red.getRoads().get("green-orange").getSpeedLimit());
    }
    
    @Test
    public void noDeberiaPermitirAsignarLimitesDeVelocidadMayoresA100000(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("green", "orange");
        red.routeSpeedLimit("green", "orange", 100001);
        assertFalse(red.ok());
        assertEquals(0, red.getRoads().get("green-orange").getSpeedLimit());
    }
        
    @Test
    public void noDeberiaPermitirAsignarUnLimiteDeVelocidadMenorAlQueTeniaAntesLaRuta(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("green", "orange");
        red.routeSpeedLimit("green", "orange", 500);
        red.routeSpeedLimit("green", "orange", 400);
        assertFalse(red.ok());
        assertEquals(500, red.getRoads().get("green-orange").getSpeedLimit());
    }
    
    @Test
    public void deberiaPoderPonerSeñales(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("green", "orange");
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("green", "orange", 13);
        assertTrue(red.ok());
        assertTrue(red.getSigns().containsKey("green-orange"));
        assertEquals(13, red.getSigns().get("green-orange").getNumber().getNumber());
        assertEquals(1, red.signs().length);   
    }
    
    @Test
    public void noDeberiaPermitirPonerSeñalesARutasQueNoTienenLimiteDeVelocidad(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("green", "orange");
        red.putSign("green", "orange", 13);
        assertFalse(red.ok());
        assertFalse(red.getSigns().containsKey("green-orange"));
        assertEquals(0, red.signs().length);
    }
    
    @Test
    public void noDeberiaPermitirPonerSeñalesARutasQueNoExisten(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("green", "orange");
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("green", "blue", 13);
        assertFalse(red.ok());
        assertFalse(red.getSigns().containsKey("blue-green"));
        assertEquals(0, red.signs().length);
    }

    @Test
    public void noDeberiaPermitirPonerSeñalesConLimiteDeVelocidadMenoresA1(){
        // Rango para los límites de velocidad de la rutas dado por el problema B: 1 <= speedLimit <= 100000
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("green", "orange");
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("green", "orange", -100);
        assertFalse(red.ok());
        assertFalse(red.getSigns().containsKey("green-orange"));
        assertEquals(0, red.signs().length);
    }
    
    @Test
    public void noDeberiaPermitirPonerSeñalesConLimiteDeVelocidadMayoresA100000(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("green", "orange");
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("green", "orange", 100001);
        assertFalse(red.ok());
        assertFalse(red.getSigns().containsKey("green-orange"));
        assertEquals(0, red.signs().length);
    }
    
    @Test
    public void noDeberiaPermitirPonerSeñalesARutasQueYatienen(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("green", "orange");
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("green", "orange", 11);
        red.putSign("green", "orange", 13);
        assertFalse(red.ok());
        assertEquals(11, red.getSigns().get("green-orange").getNumber().getNumber());
        assertEquals(1, red.signs().length);  
    }
    
    @Test
    public void deberiaPoderEliminarIntersecciones(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addIntersection("yellow", 300, 600);
        red.addIntersection("red", 400, 500);
        red.delIntersection("green");
        assertTrue(red.ok());
        assertFalse(red.getIntersections().containsKey("green"));
        assertEquals(3, red.intersections().length);  
        red.delIntersection("orange");
        assertTrue(red.ok());
        assertFalse(red.getIntersections().containsKey("orange"));
        assertEquals(2, red.intersections().length);  
        red.delIntersection("yellow");
        assertTrue(red.ok());
        assertFalse(red.getIntersections().containsKey("yellow"));
        assertEquals(1, red.intersections().length);  
    }
    
    @Test
    public void deberianEliminarseLasRutasRelacionadasALaInterseccionQueSeElimina(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addIntersection("yellow", 300, 600);
        red.addIntersection("red", 400, 500);
        red.addRoute("green", "orange");
        red.addRoute("green", "yellow");
        red.addRoute("red", "yellow");
        red.delIntersection("red");
        assertTrue(red.ok());
        assertFalse(red.getRoads().containsKey("red-yellow")); 
        assertEquals(2, red.roads().length); 
        red.delIntersection("green");
        assertTrue(red.ok());
        assertFalse(red.getRoads().containsKey("green-orange")); 
        assertFalse(red.getRoads().containsKey("green-yellow")); 
        assertEquals(0, red.roads().length);    
    }
    
    @Test
    public void deberianEliminarseLasSeñalesRelacionadasALaInterseccionQueSeElimina(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addIntersection("yellow", 300, 600);
        red.addIntersection("red", 400, 500);
        red.addRoute("green", "orange");
        red.addRoute("green", "yellow");
        red.addRoute("red", "yellow");
        red.routeSpeedLimit("green", "orange", 10);
        red.routeSpeedLimit("yellow", "red", 20);
        red.putSign("green", "orange", 13);
        red.putSign("yellow", "red", 17);
        red.delIntersection("green");
        assertTrue(red.ok());
        assertFalse(red.getSigns().containsKey("green-orange"));
        assertEquals(1, red.signs().length); 
        red.delIntersection("yellow");
        assertTrue(red.ok());
        assertFalse(red.getSigns().containsKey("red-yellow"));
        assertEquals(0, red.signs().length); 
    }
    
    @Test
    public void noDeberiaPermitirEliminarInterseccionesQueNoExisten(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("green", "orange");
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("green", "orange", 13);
        red.delIntersection("purple");
        assertFalse(red.ok());
        assertEquals(2, red.intersections().length);  
        assertEquals(1, red.roads().length); 
        assertEquals(1, red.signs().length);
    }
    
    @Test
    public void deberiaPoderEliminarRutas(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addIntersection("yellow", 300, 600);
        red.addRoute("green", "orange");
        red.addRoute("green", "yellow");
        red.delRoute("orange", "green");
        assertTrue(red.ok());
        assertFalse(red.getRoads().containsKey("green-orange")); 
        assertEquals(1, red.roads().length); 
        red.delRoute("green", "yellow");
        assertTrue(red.ok());
        assertFalse(red.getRoads().containsKey("green-yellow")); 
    }
    
    @Test
    public void deberianEliminarseLaSeñalRelacionadaALaRuta(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addIntersection("yellow", 300, 600);
        red.addRoute("green", "orange");
        red.addRoute("green", "yellow");
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("green", "orange", 13);
        red.delRoute("orange", "green");
        assertTrue(red.ok());
        assertFalse(red.getSigns().containsKey("green-orange"));
        assertEquals(0, red.signs().length); 
    }
    
    @Test
    public void noDeberiaPermitirEliminarRutasQueNoExisten(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addIntersection("yellow", 300, 600);
        red.addRoute("green", "orange");
        red.addRoute("green", "yellow"); 
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("green", "orange", 13);
        red.delRoute("orange", "yellow");
        assertFalse(red.ok());
        assertEquals(3, red.intersections().length);  
        assertEquals(2, red.roads().length); 
        assertEquals(1, red.signs().length);
    }
    
    @Test
    public void deberiaPoderQuitarSeñales(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addIntersection("yellow", 300, 600);
        red.addRoute("green", "orange");
        red.addRoute("green", "yellow"); 
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("green", "orange", 13);
        red.routeSpeedLimit("green", "yellow", 8);
        red.putSign("green", "yellow", 5);
        red.removeSign("green", "orange");
        assertTrue(red.ok());
        assertFalse(red.getSigns().containsKey("green-orange"));
        assertEquals(1, red.signs().length);
        red.removeSign("green", "yellow");
        assertTrue(red.ok());
        assertFalse(red.getSigns().containsKey("green-yellow"));
        assertEquals(0, red.signs().length);
    }
    
    @Test
    public void noDeberiaPermitirQuitarSeñalesQueNoExisten(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addIntersection("yellow", 300, 600);
        red.addRoute("green", "orange");
        red.addRoute("green", "yellow"); 
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("green", "orange", 13);
        red.routeSpeedLimit("green", "yellow", 8);
        red.putSign("green", "yellow", 5);
        red.removeSign("orange", "yellow");
        assertFalse(red.ok());
        assertEquals(3, red.intersections().length);  
        assertEquals(2, red.roads().length); 
        assertEquals(2, red.signs().length);
    }
    
    @Test
    public void deberiaPoderConsultarIntersecciones(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addIntersection("yellow", 300, 600);
        String[] i = {"orange", "green", "yellow"};
        assertArrayEquals(i, red.intersections());
        assertTrue(red.ok());
    }
    
    @Test
    public void deberiaPoderConsultarRutas(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addIntersection("yellow", 300, 600);
        red.addIntersection("red", 100, 600);
        red.addRoute("orange", "green");
        red.addRoute("green", "yellow"); 
        red.addRoute("yellow", "red");
        String[][] r = {{"green", "orange"}, {"green", "yellow"}, {"red","yellow"}};
        assertArrayEquals(r, red.roads());
        assertTrue(red.ok());
    }
    
    @Test
    public void deberiaPoderConsultarSeñales(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addIntersection("yellow", 300, 600);
        red.addIntersection("red", 500, 600);
        red.addRoute("orange", "green");
        red.addRoute("green", "yellow"); 
        red.addRoute("yellow", "red");
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("green", "orange", 15);
        red.routeSpeedLimit("green", "yellow", 20);
        red.putSign("green", "yellow", 15);
        red.routeSpeedLimit("red", "yellow", 5);
        red.putSign("red", "yellow", 5);
        String[][] s = {{"green", "orange"}, {"green", "yellow"}, {"red","yellow"}};
        assertArrayEquals(s, red.signs());
        assertTrue(red.ok());
    }
    
    @Test
    public void deberiaPoderConsultarSeñalesErroneas(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addIntersection("yellow", 300, 600);
        red.addIntersection("red", 0, 50);
        red.addIntersection("cyan", 300, 700);
        red.addRoute("red", "orange");
        red.addRoute("orange", "green");
        red.addRoute("yellow", "cyan");
        red.addRoute("orange", "yellow"); 
        red.routeSpeedLimit("red", "orange", 20);
        red.routeSpeedLimit("green", "orange", 10);
        red.routeSpeedLimit("cyan", "yellow", 5);
        red.routeSpeedLimit("orange", "yellow", 15);
        red.putSign("red", "orange", 25);
        String[][] s1 = red.signs();
        assertArrayEquals(s1, red.wrongSigns());
        red.putSign("green", "orange", 15);
        String[][] s2 = red.signs();
        assertArrayEquals(s2, red.wrongSigns());
        red.putSign("orange", "yellow", 15);
        assertArrayEquals(s2, red.wrongSigns());
        red.putSign("cyan", "yellow", 15);
        String[][] s3 = {{"red", "orange"}, {"green", "orange"}, {"cyan", "yellow"}};
        assertArrayEquals(s3, red.wrongSigns());
        assertTrue(red.ok());
    }
    
    @Test
    public void deberiaEliminarseSeñalesErroneasAlEliminarSeñales(){
        ICPC red = new ICPC(950, 950);
        String[][] s = red.wrongSigns();
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("orange", "green");
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("green", "orange", 15);
        red.removeSign("green", "orange");
        assertArrayEquals(s, red.wrongSigns());
        assertTrue(red.ok());
    }
    
    @Test
    public void deberiaEliminarseSeñalesErroneasAlEliminarRutas(){
        ICPC red = new ICPC(950, 950);
        String[][] s = red.wrongSigns();
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("orange", "green");
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("green", "orange", 15);
        red.delRoute("green", "orange");
        assertArrayEquals(s, red.wrongSigns());
        assertTrue(red.ok());
    }
    
    @Test
    public void deberiaEliminarseSeñalesErroneasAlEliminarIntersecciones(){
        ICPC red = new ICPC(950, 950);
        String[][] s = red.wrongSigns();
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("orange", "green");
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("green", "orange", 15);
        red.delIntersection("green");
        assertArrayEquals(s, red.wrongSigns());
        assertTrue(red.ok());
    }
    
    @Test
    public void deberiaPoderConsultarSeñalesInnecesarias(){
        ICPC red = new ICPC(950, 950);
        String[][] s = red.unNecessarySigns();
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addIntersection("yellow", 300, 600);
        red.addIntersection("red", 0, 50);
        red.addIntersection("cyan", 300, 700);
        red.addRoute("orange", "green");
        red.addRoute("red", "orange");
        red.addRoute("orange", "yellow"); 
        red.addRoute("yellow", "cyan");
        red.routeSpeedLimit("red", "orange", 20);
        red.putSign("orange", "red", 25);
        assertArrayEquals(s, red.unNecessarySigns());
        red.routeSpeedLimit("green", "orange", 20);
        red.putSign("orange", "green", 15);
        String[][] s2 = red.signs();
        assertArrayEquals(s, red.unNecessarySigns());
        red.routeSpeedLimit("cyan", "yellow", 10);
        red.putSign("yellow", "cyan", 10);
        assertArrayEquals(s, red.unNecessarySigns());
        red.routeSpeedLimit("orange", "yellow", 20);
        red.putSign("yellow", "orange", 20);
        assertArrayEquals(s2, red.unNecessarySigns());
        assertTrue(red.ok());
    }
    
    @Test
    public void deberiaEliminarseSeñalesInnecesariasAlEliminarSeñales(){
        ICPC red = new ICPC(950, 950);
        String[][] s = red.unNecessarySigns();
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("orange", "green");
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("green", "orange", 15);
        red.removeSign("green", "orange");
        assertArrayEquals(s, red.unNecessarySigns());
        assertTrue(red.ok());
    }   
    
    @Test
    public void deberiaEliminarseSeñalesInnecesariasAlEliminarRutas(){
        ICPC red = new ICPC(950, 950);
        String[][] s = red.unNecessarySigns();
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("orange", "green");
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("green", "orange", 15);
        red.delRoute("green", "orange");
        assertArrayEquals(s, red.unNecessarySigns());
        assertTrue(red.ok());
    }  
    
    @Test
    public void deberiaEliminarseSeñalesInnecesariasAlEliminarIntersecciones(){
        ICPC red = new ICPC(950, 950);
        String[][] s = red.unNecessarySigns();
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("orange", "green");
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("green", "orange", 15);
        red.delIntersection("green");
        assertArrayEquals(s, red.unNecessarySigns());
        assertTrue(red.ok());
    }  
    
    @Test
    public void deberiaPoderConsultarCostoTotal(){
        ICPC red = new ICPC(950, 950, 10);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addIntersection("yellow", 300, 600);
        red.addIntersection("red", 0, 50);
        red.addIntersection("cyan", 400, 600);
        red.addRoute("orange", "green");
        red.addRoute("green", "yellow"); 
        red.addRoute("yellow", "red");
        red.addRoute("red", "cyan");
        red.routeSpeedLimit("green", "orange", 5);
        red.putSign("green", "orange", 5);
        assertEquals(10, red.totalSignsCost());
        red.routeSpeedLimit("green", "yellow", 10);
        red.putSign("green", "yellow", 10);
        assertEquals(20, red.totalSignsCost());
        red.routeSpeedLimit("red", "yellow", 10);
        red.putSign("red", "yellow", 10);
        assertEquals(30, red.totalSignsCost());
        red.routeSpeedLimit("red", "cyan", 10);
        red.putSign("red", "cyan", 10);
        assertEquals(40, red.totalSignsCost());
        assertTrue(red.ok());
    }
    
    @Test
    public void deberiaReducirElCostoTotalAlEliminarSeñales(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("orange", "green");
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("green", "orange", 15);
        red.removeSign("green", "orange");
        assertEquals(0, red.totalSignsCost());
        assertTrue(red.ok());
    }   
    
    @Test
    public void deberiaReducirElCostoTotalAlEliminarRutas(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("orange", "green");
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("green", "orange", 15);
        red.delRoute("green", "orange");
        assertEquals(0, red.totalSignsCost());
        assertTrue(red.ok());
    }  
    
    @Test
    public void deberiaReducirElCostoTotalAlEliminarIntersecciones(){
        ICPC red = new ICPC(950, 950);
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("orange", "green");
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("green", "orange", 15);
        red.delIntersection("green");
        assertEquals(0, red.totalSignsCost());
        assertTrue(red.ok());
    } 
}