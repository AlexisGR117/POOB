package icpc;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;
import java.util.*;
/**
 * The test class ICPCContestTest.
 *
 * @author Angel Cuervo y Jefer Gonzalez.
 * @version 1.0 (01/10/2022)
 */
public class ICPCContestTest{
    @Test
    public void deberiaPoderSolucionarProblemasSoloAumentandoTodosLosLimitesAlMayorLimite(){
        ICPCContest contest = new ICPCContest();
        int[][] routesSpeedLimit = {{1, 2, 10}, {1, 3, 5}, {1, 4, 7}, {2, 5, 9}};
        assertEquals(9, contest.solve(100, routesSpeedLimit));
        assertTrue(contest.ok());
    }
    
    @Test
    public void deberiaPoderSolucionarProblemasSoloPoniendoSeñales(){
        ICPCContest contest = new ICPCContest();
        int[][] routesSpeedLimit = {{1, 2, 10}, {1, 3, 5}, {1, 4, 7}};
        assertEquals(6, contest.solve(2, routesSpeedLimit));
        assertTrue(contest.ok());
    } 
    
    @Test
    public void deberiaPoderSolucionarProblemasPoniendoSeñalesYAumentadoLimites(){
        ICPCContest contest = new ICPCContest();
        int[][] routesSpeedLimit = {{1, 2, 10}, {1, 3, 5}, {1, 4, 7}, {2, 5, 9}};
        assertEquals(7, contest.solve(2, routesSpeedLimit));
        assertTrue(contest.ok());
        int[][] routesSpeedLimit2 = {{1, 8, 101}, {1, 9, 30}, {1, 2, 100}, {1, 3, 100}, {2, 4, 75}, {2, 5, 70}, {2, 6, 82}, {2, 7, 77}, {3, 10, 73}, {3, 11, 69}, {3, 12, 83}, {3, 13, 79}};
        assertEquals(272, contest.solve(20, routesSpeedLimit2));
        assertTrue(contest.ok());
    } 
    
    @Test
    public void noDeberiaSolucionarRedesConInformacionIncompleta(){
        ICPCContest contest = new ICPCContest();
        int[][] routesSpeedLimits1 = {{2, 3}, {3, 1, 6}};
        int solution1 = contest.solve(10, routesSpeedLimits1);
        assertFalse(contest.ok());
        assertEquals(0, solution1);
        int[][] routesSpeedLimits2 = {{2, 3, 10}, {3}, {4, 5, 2}};
        int solution2 = contest.solve(10, routesSpeedLimits2);
        assertFalse(contest.ok());
        assertEquals(0, solution2);
        int[][] routesSpeedLimits3 = {{2, 3, 10}, {3, 1, 6}, {}};
        int solution3 = contest.solve(10, routesSpeedLimits3);
        assertFalse(contest.ok());
        assertEquals(0, solution3);
    }
    
    @Test
    public void noDeberiaSolucionarRedesConLimitesDeVelocidadMenoresA1(){
        // Rango para los límites de velocidad de la rutas dado por el problema B: 1 <= speedLimit <= 100000
        ICPCContest contest = new ICPCContest();
        int[][] routesSpeedLimits = {{2, 3, 10}, {4, 5, 0}};
        int solution = contest.solve(10, routesSpeedLimits);
        assertFalse(contest.ok());
        assertEquals(0, solution);
    }
    
    @Test
    public void noDeberiaSolucionarRedesConLimitesDeVelocidadMayoresA100000(){
        ICPCContest contest = new ICPCContest();
        int[][] routesSpeedLimits = {{2, 3, 100001}, {3, 1, 6}};
        int solution = contest.solve(10, routesSpeedLimits);
        assertFalse(contest.ok());
        assertEquals(0, solution);
    }
    
    @Test
    public void noDeberiaSolucionarRedesSiAlgunaRutaTieneLasDosInterseccionesIguales(){
        ICPCContest contest = new ICPCContest();
        int[][] routesSpeedLimits = {{1, 1, 10}, {3, 1, 6}};
        int solution = contest.solve(10, routesSpeedLimits);
        assertFalse(contest.ok());
        assertEquals(0, solution);
    }
    
    @Test
    public void noDeberiaSolucionarRedesSiHayRutasRepetidas(){
        ICPCContest contest = new ICPCContest();
        int[][] routesSpeedLimits = {{1, 2, 10}, {2, 1, 15}};
        int solution = contest.solve(10, routesSpeedLimits);
        assertFalse(contest.ok());
        assertEquals(0, solution);
    }
    
    @Test
    public void noDeberiaSimularRedesConInformacionIncompleta(){
        ICPCContest contest = new ICPCContest();
        int[][] routesSpeedLimits1 = {{2, 3}, {3, 1, 6}};
        contest.simulate(10, routesSpeedLimits1);
        assertFalse(contest.ok());
        int[][] routesSpeedLimits2 = {{2, 3, 10}, {3}, {4, 5, 2}};
        contest.simulate(10, routesSpeedLimits2);
        assertFalse(contest.ok());
        int[][] routesSpeedLimits3 = {{2, 3, 10}, {3, 1, 6}, {}};
        contest.simulate(10, routesSpeedLimits3);
        assertFalse(contest.ok());
    }
    
    @Test
    public void noDeberiaSimularRedesConLimitesDeVelocidadMenoresA1(){
        // Rango para los límites de velocidad de la rutas dado por el problema B: 1 <= speedLimit <= 100000
        ICPCContest contest = new ICPCContest();
        int[][] routesSpeedLimits = {{2, 3, 10}, {4, 5, 0}};
        contest.simulate(10, routesSpeedLimits);
        assertFalse(contest.ok());
    }
    
    @Test
    public void noDeberiaSimularRedesConLimitesLimitesDeVelocidadMayoresA100000(){
        ICPCContest contest = new ICPCContest();
        int[][] routesSpeedLimits = {{2, 3, 100001}, {3, 1, 6}};
        contest.simulate(10, routesSpeedLimits);
        assertFalse(contest.ok());
    }
    
    @Test
    public void noDeberiaSimularRedesSiAlgunaRutaTieneLasDosInterseccionesIguales(){
        ICPCContest contest = new ICPCContest();
        int[][] routesSpeedLimits = {{1, 1, 10}, {3, 1, 6}};
        contest.simulate(10, routesSpeedLimits);
        assertFalse(contest.ok());
    }
    
    @Test
    public void noDeberiaSimularRedesSiHayRutasRepetidas(){
        ICPCContest contest = new ICPCContest();
        int[][] routesSpeedLimits = {{1, 2, 10}, {2, 1, 15}};
        contest.simulate(10, routesSpeedLimits);
        assertFalse(contest.ok());  
    }
}