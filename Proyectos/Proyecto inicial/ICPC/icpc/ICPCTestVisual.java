package icpc;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * The test class ICPCTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ICPCTestVisual{
    @Test
    public void pruebaSeñal(){
        ICPC mapa = new ICPC(950, 950);
        mapa.addIntersection("magenta", 300, 100);
        mapa.addIntersection("cyan", 100, 100);
        mapa.addIntersection("red", 100, 300);
        mapa.addIntersection("yellow", 300, 300);
        mapa.addIntersection("orange", 600, 800);
        mapa.addIntersection("green", 900, 50);
        mapa.addRoute("cyan", "red");
        mapa.addRoute("magenta", "cyan");
        mapa.addRoute("yellow", "cyan");
        mapa.addRoute("yellow", "orange");
        mapa.addRoute("yellow", "green");
        mapa.routeSpeedLimit("magenta", "cyan", 10);
        mapa.routeSpeedLimit("cyan", "red", 20);
        mapa.routeSpeedLimit("cyan", "yellow", 4);
        mapa.routeSpeedLimit("orange", "yellow", 4);
        mapa.routeSpeedLimit("green", "yellow", 4);
        mapa.putSign("cyan", "magenta", 5);
        mapa.putSign("magenta", "cyan", 5);
        mapa.putSign("cyan", "red", 5);
        mapa.putSign("red", "cyan", 5);
        mapa.putSign("cyan", "yellow", 5);
        mapa.putSign("yellow", "cyan", 5);
        mapa.putSign("orange", "yellow", 5);
        mapa.putSign("yellow", "orange", 5);
        mapa.putSign("green", "yellow", 5);
        mapa.putSign("yellow", "green", 5);
        mapa.makeVisible();
        //mapa.delIntersection("orange");
        //mapa.delRoad("cyan", "yellow");
        //mapa.removeSign("red", "yellow");
        //mapa.makeInvisible();
        //{"red", "orange", "yellow", "green", "cyan", "magenta", "white", "lightGray", "gray", "darkGray", "blue", "black"};
        // || higher == value.getSpeedLimit()
        //{{1, 2, 10}, {1, 3, 5}, {1, 4, 7}, {2, 5, 9}}
        //{{1, 8, 101}, {1, 9, 30}, {1, 2, 100}, {1, 3, 100}, {2, 4, 75}, {2, 5, 70}, {2, 6, 82}, {2, 7, 77}, {3, 10, 73}, {3, 11, 69}, {3, 12, 83}, {3, 13, 79}}
    }
    
    @Test
    public void pruebaSeñal2(){
        int[][] routesSpeedLimits = {{5, 6, 10}, {1, 5, 20}, {3, 5, 4}, {2, 3, 4}, {3, 4, 4}};
        ICPC mapa = new ICPC(10, routesSpeedLimits);
        mapa.putSign("cyan", "magenta", 5);
        mapa.putSign("magenta", "cyan", 5);
        mapa.putSign("cyan", "red", 5);
        mapa.putSign("red", "cyan", 5);
        mapa.putSign("cyan", "yellow", 5);
        mapa.putSign("yellow", "cyan", 5);
        mapa.putSign("orange", "yellow", 5);
        mapa.putSign("yellow", "orange", 5);
        mapa.putSign("green", "yellow", 5);
        mapa.putSign("yellow", "green", 5);
        mapa.makeVisible();
    }
    
    @Test
    public void prueba2(){
        ICPC mapa = new ICPC(950, 950, 950);
        mapa.addIntersection("orange", 100, 800);
        mapa.addIntersection("green", 700, 0);
        mapa.addIntersection("yellow", 300, 600);
        mapa.addIntersection("red", 0, 50);
        mapa.addIntersection("cyan", 300, 700);
        mapa.addRoute("orange", "green");
        mapa.addRoute("orange", "yellow"); 
        mapa.addRoute("yellow", "cyan");
        mapa.addRoute("red", "orange");
        mapa.routeSpeedLimit("red", "orange", 20);
        mapa.putSign("orange", "red", 25);
        mapa.routeSpeedLimit("green", "orange", 20);
        mapa.putSign("orange", "green", 15);
        mapa.routeSpeedLimit("cyan", "yellow", 10);
        mapa.putSign("yellow", "cyan", 10);
        mapa.routeSpeedLimit("orange", "yellow", 20);
        mapa.putSign("yellow", "orange", 20);
        mapa.makeVisible();
    }
    
    @Test
    public void prueba3(){
        int[][] routesSpeedLimits = {{1, 2, 10}, {1, 3, 5}, {1, 4, 7}, {2, 5, 9}};
        ICPC mapa = new ICPC(10, routesSpeedLimits);
        mapa.putSign("cyan", "magenta", 5);
        mapa.putSign("magenta", "cyan", 5);
        mapa.putSign("cyan", "red", 5);
        mapa.putSign("red", "cyan", 5);
        mapa.putSign("cyan", "yellow", 5);
        mapa.putSign("yellow", "cyan", 5);
        mapa.putSign("orange", "yellow", 5);
        mapa.putSign("yellow", "orange", 5);
        mapa.putSign("green", "yellow", 5);
        mapa.putSign("yellow", "green", 5);
        mapa.makeVisible();
    }
    
    @Test
    public void pruebaHermit(){
        ICPC red = new ICPC(950, 950);
        red.makeVisible();
        red.addIntersection("Hermit", "orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addIntersection("red", 300, 500);
        red.addRoute("orange", "green");
        red.addRoute("orange", "red");
    }
    
    @Test
    public void pruebaNeedy(){
        ICPC red = new ICPC(950, 950);
        red.makeVisible();
        red.addIntersection("Needy", "orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addIntersection("red", 300, 500);
        red.addRoute("orange", "green");
        red.addRoute("red", "orange");
        red.delRoute("orange", "green");
        red.delRoute("red", "orange");
    }
    
    @Test
    public void pruebaNeedy2(){
        ICPC red = new ICPC(950, 950);
        red.makeVisible();
        red.addIntersection("Needy", "orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("orange", "green");
        red.delIntersection("green");
    }
    
    @Test
    public void pruebaNeedy3(){
        ICPC red = new ICPC(950, 950);
        red.makeVisible();
        red.addIntersection("Needy", "orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("orange", "green");
    }
            
    @Test
    public void pruebaRebel(){
        ICPC red = new ICPC(950, 950);
        red.makeVisible();
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("Rebel", "orange", "green");
        red.routeSpeedLimit("orange", "green", 15);
        red.putSign("orange", "green", 15);
    }
    
    @Test
    public void pruebaFixed(){
        ICPC red = new ICPC(950, 950);
        red.makeVisible();
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("Fixed", "orange", "green");
        red.delRoute("orange", "green");
    }   
    
    @Test
    public void pruebaFixed2(){
        ICPC red = new ICPC(950, 950);
        red.makeVisible();
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("Fixed", "orange", "green");
        red.delIntersection("orange");
    } 
    
    @Test
    public void pruebaTwin(){
        ICPC red = new ICPC(950, 950);
        red.makeVisible();
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("orange", "green");
        red.routeSpeedLimit("orange", "green", 15);
        red.putSign("Twin", "orange", "green", 20);
    }   
    
    @Test
    public void pruebaTwin2(){
        ICPC red = new ICPC(950, 950);
        red.makeVisible();
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("orange", "green");
        red.routeSpeedLimit("orange", "green", 10);
        red.putSign("orange", "green", 10);
        red.putSign("Twin", "green", "orange", 15);
    }  
    
    @Test
    public void pruebaTwin3(){
        ICPC red = new ICPC(950, 950);
        red.makeVisible();
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("orange", "green");
        red.routeSpeedLimit("orange", "green", 10);
        red.putSign("Twin", "green", "orange", 15);
        red.removeSign("green", "orange");
        assertFalse(red.getSigns().containsKey("green-orange"));
        assertFalse(red.getSigns().containsKey("orange-green"));
        assertEquals(0, red.signs().length);
    }   
    
    @Test
    public void pruebaCautious(){
        ICPC red = new ICPC(950, 950);
        red.makeVisible();
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("orange", "green");
        red.routeSpeedLimit("orange", "green", 10);
        red.putSign("Cautious", "green", "orange", 15);
        assertEquals(10, red.getSigns().get("green-orange").getNumber().getNumber());
    }   
    
    @Test
    public void pruebaUnique(){
        ICPC red = new ICPC(950, 950);
        red.makeVisible();
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 700, 0);
        red.addRoute("orange", "green");
        red.routeSpeedLimit("orange", "green", 10);
        red.putSign("Unique", "green", "orange", 15);
    }  
    
    @Test
    public void pruebaVHermit(){
        ICPC red = new ICPC(950, 950);
        red.makeVisible();
        red.addIntersection("Hermit", "orange", 200, 800);
    }
    
    @Test
    public void pruebaVNeedy(){
        ICPC red = new ICPC(950, 950);
        red.makeVisible();
        red.addIntersection("Needy", "orange", 600, 800);
    }
    
    @Test
    public void pruebaVRebel(){
        ICPC red = new ICPC(950, 950);
        red.makeVisible();
        red.addIntersection("orange", 300, 400);
        red.addIntersection("green", 200, 900);
        red.addRoute("Rebel", "green", "orange");
    }
    
    @Test
    public void pruebaVFixed(){
        ICPC red = new ICPC(950, 950);
        red.makeVisible();
        red.addIntersection("orange", 200, 300);
        red.addIntersection("green", 400, 200);
        red.addRoute("Fixed", "green", "orange");
    }
    
    @Test
    public void pruebaVTwin(){
        ICPC red = new ICPC(950, 950);
        red.makeVisible();
        red.addIntersection("orange", 600, 700);
        red.addIntersection("green", 700, 500);
        red.addRoute("green", "orange");
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("Twin", "green", "orange", 13);
    }
    
    @Test
    public void pruebaVCautious(){
        ICPC red = new ICPC(950, 950);
        red.makeVisible();
        red.addIntersection("orange", 500, 800);
        red.addIntersection("green", 300, 600);
        red.addRoute("green", "orange");
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("Cautious", "green", "orange", 13);  
    }

    @Test
    public void pruebaVUnique(){
        ICPC red = new ICPC(950, 950);
        red.makeVisible();
        red.addIntersection("orange", 100, 800);
        red.addIntersection("green", 200, 600);
        red.addRoute("green", "orange");
        red.routeSpeedLimit("green", "orange", 10);
        red.putSign("Unique", "green", "orange", 13);  
    }
}
