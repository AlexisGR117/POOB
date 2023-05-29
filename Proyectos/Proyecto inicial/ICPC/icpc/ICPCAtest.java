package icpc;

import static org.junit.Assert.*;
import org.junit.Test;
/**
 * The test class ICPCAtest.
 *
 * @author Angel Cuervo y Jefer Gonzalez.
 * @version 1.0 (22/10/2022)
 */
public class ICPCAtest{
    @Test
    public void simulaYSolucionalElProblema()throws ICPCException{
        ICPCContest contest = new ICPCContest();
        //Informacion de la red.
        int[][] routesSpeedLimit2 = {{1, 2, 60}, {1, 3, 80}, {1, 4, 80}, {2, 5, 50}, {2, 6, 45}, {3, 7, 120}, {4, 8, 70}};
        //Simula la solución del caso dado.
        //Deben crearse 8 intersecciones con diferentes colores de la siguiente manera:
        //1-rojo, 2-naranja, 3-amarillo, 4-verde, 5-cyan, 6-magenta, 7-rosado, 8-blanco.
        //Las 7 carreteras que deben aparecer con su respectivo límite de velocidad final en el centro de color rosado son:
        //Rojo-Naranja (60), Rojo-Verde (80), Rojo-Amarillo (80), Naranja-Cyan (50+10=60)
        //Naranja-Magenta (45+15=60), Verde-Blanco (70+10=80) y Amarillo-Rosado (120)
        //Las 3 carreteras que deben aumentar la velocidad y por ende cambiar de color blanco a verde son:
        //Naranja-Cyan, Naranja-Magenta y Verde-Blanco.
        //Las 5 señales que deben aparecer con el límite de velocidad de la carretera en la que se ubican son:
        //Rojo-Naranja (60), Rojo-Verde (80), Rojo-Amarillo (80), Amarillo-Rojo (80) y Amarillo-Rosado (120).
        contest.simulate(15, routesSpeedLimit2);
        //Al solucionar el caso propuesto debe dar 110 que se obtiene de la siguiente manera:
        //Señales: 15 * 5 = 75 
        //Aumento de velocidad: (60 - 50) + (60 - 45) + (80 - 70) = 10 + 15 + 10 = 35
        //Total = 75 + 35 = 110
        assertEquals(110, contest.solve(15, routesSpeedLimit2));
    } 
    
    @Test
    public void distingueLosDiferentesElementos()throws ICPCException{
        //Crear una nueva red ICPC con ancho y longitud de 950.
        ICPC mapa = new ICPC(950, 950);
        //Agregar una intersección normal (contorno negro) con color magenta ubicada en (600, 100):
        mapa.addIntersection("magenta", 600, 100);
        //Agregar una intersección normal (contorno negro) con color cyan ubicada en (100, 100):
        mapa.addIntersection("Normal", "cyan", 100, 100);
        //Nueva carretera normal (contorno negro) que va desde cyan hasta magenta (es horizontal).
        mapa.addRoute("magenta", "cyan");
        //Asignar límite de velocidad de 10 a la carretera magenta-cyan se ubica en el centro de esta y es de color rosado.
        mapa.routeSpeedLimit("magenta", "cyan", 10);
        //Poner nueva intersección Hermit (contorno azul) con color rojo ubicada en (100, 600).
        mapa.addIntersection("Hermit", "red", 100, 600);
        //Carretera normal (contorno negro) que va desde cyan hasta rojo (es vertical).
        mapa.addRoute("Normal", "cyan", "red");
        //Asignar límite de velocidad de 20 a la carretera rojo-cyan se ubica en el centro de esta y es de color rosado.
        mapa.routeSpeedLimit("cyan", "red", 20);
        //Poner nueva intersección Needy (contorno rojo) con color amarillo ubicada en (300, 300).
        mapa.addIntersection("Needy", "yellow", 300, 300);
        //Carretera Fixed (contorno rojo) que va desde cyan hasta amarillo (la distancia vertical y horizontal son iguales).
        mapa.addRoute("Fixed", "yellow", "cyan");
        //Asignar límite de velocidad de 4 a la carretera amarillo-cyan se ubica en el centro de esta y es de color rosado.
        mapa.routeSpeedLimit("cyan", "yellow", 4);
        //Agregar nueva intersección Needy (contorno rojo) con color naranja ubicada en (600, 800).
        mapa.addIntersection("Needy", "orange", 600, 900);
        //Carretera normal (contorno negro) que va desde amarillo hasta naranja (La distancia vertical es mayor a la horizontal).
        mapa.addRoute("yellow", "orange");
        //Asignar límite de velocidad de 4 a la carretera amarillo-naranja se ubica en el centro de esta y es de color rosado.
        mapa.routeSpeedLimit("orange", "yellow", 4);
        //Poner nueva intersección Hermit (contorno azul) con color verde ubicada en (900, 50).
        mapa.addIntersection("Hermit", "green", 900, 50);
        //Carretera Fixed (contorno rojo) que va desde amarillo hasta ver (La distancia vertical es menor a la horizontal).
        mapa.addRoute("Fixed", "yellow", "green");
        //Asignar límite de velocidad de 4 a la carretera amarillo-verde se ubica en el centro de esta y es de color rosado.
        mapa.routeSpeedLimit("green", "yellow", 4);
        //Agregar una intersección normal (contorno negro) con color gris claro ubicada en (150, 900):
        mapa.addIntersection("Normal", "lightGray", 150, 900);
        //Carretera Rebel (contorno naranja) que va desde amarillo hasta gris claro.
        mapa.addRoute("Rebel", "yellow", "lightGray");
        //Poner una señal normal (color blanco) magenta-cyan con límite de velocidad 5,
        //va a tener contorno verde ya que es inecesaria.
        mapa.putSign("magenta", "cyan", 5);
        //Poner una señal Cautious (color gris claro) cyan-magenta con límite de velocidad 5,
        //va a tener límite de velocidad 10 ya que es Cautious.
        mapa.putSign("Cautious", "cyan", "magenta", 5);
        //Poner una señal Twin (color amarillo) cyan-red con límite de velocidad 5,
        //se va a poner otra señal twin al otro lado y esta es inecesaria por tanto tendrá contorno verde.
        mapa.putSign("Twin", "cyan", "red", 5);
        //Poner una señal Unique (color naranja) cyan-yellow con límite de velocidad 5,
        //se va a poner la señal en el centro y el número de esta va ser rojo ya que es erronea.
        mapa.putSign("Unique", "cyan", "yellow", 5);
        //Poner una señal Cautious (color gris claro) orange-yellow con límite de velocidad 5,
        //va a tener límite de velocidad 4 ya que es Cautious y tendrá contorno verde porque es inecesaria.
        mapa.putSign("Cautious", "orange", "yellow", 5);
        //Poner una señal normal (color blanco) yellow-orange con límite de velocidad 5,
        //el número de esta va ser rojo ya que es erronea.
        mapa.putSign("Normal","yellow", "orange", 5);
        //Poner una señal Twin (color amarillo) green-yellow con límite de velocidad 5,
        //se va a poner otra señal twin al otro lado y esta es inecesaria por tanto tendrá contorno verde,
        //además ambas serán erroneas y por lo tanto tendrán el número de color rojo.
        mapa.putSign("Twin", "green", "yellow", 5);
        //Se hace visible el simulador.
        mapa.makeVisible(); 
    } 
}
