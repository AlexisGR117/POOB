package icpc;
import javax.swing.JOptionPane;

import java.util.*;
/**
 * Soluciona y simula el Problem B de la maratón de programación internacional 2020 The Cost of Speed Limits.
 * 
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (01/10/2022)
 */
public class ICPCContest{
    static boolean last;
    /**
     * Soluciona el problema de maratón.
     * @param cost Costo de las señales de la red ICPC.
     * @param routesSpeedLimits Información definida en el Problem B.
     * @return El costo mínimo de actualizar e instalar señales de límite de velocidad.
     */
    public static int solve(int cost, int[][] routesSpeedLimits){
        boolean[] valid = validInformation(routesSpeedLimits);
        boolean validLimit = valid[0], validInformation = valid[1], validRoutes = valid[2], validIntersections = valid[3];
        int minimum = 0;
        if (1 <= cost && cost <= 100000 && validLimit && validInformation && validRoutes && validIntersections){
            int higher = higherRSL(routesSpeedLimits);
            int costChange = costChange(routesSpeedLimits, higher);
            ArrayList<Integer> intersections = intersections(routesSpeedLimits);
            HashMap<Integer, Integer[]> costs = costs(routesSpeedLimits, cost, intersections);
            minimum = minimum(costs, costChange);
            last = true;
        } else{
            last = false;
            JOptionPane.showMessageDialog(null, "No se pudo realizar la acción.");
            if (!validLimit) JOptionPane.showMessageDialog(null, "El límite de velocidad de cada ruta debe estar entre 1 y 100000");  
            if (!validInformation) JOptionPane.showMessageDialog(null, "Por cada carretera deben ir dos intersecciones y su límite de velocidad"); 
            if (!validRoutes) JOptionPane.showMessageDialog(null, "Las rutas deben ser diferentes.");
            if (!validIntersections) JOptionPane.showMessageDialog(null, "Las intersecciones de las rutas deben ser diferentes."); 
        }   
        return minimum; 
    }
    
    /**
     * Simula la solución del problema de maratón.
     * @param cost Costo de las señales de la red ICPC.
     * @param routesSpeedLimits Información definida en el Problem B.
     */
    public static void simulate(int cost, int[][] routesSpeedLimits){
        boolean[] valid = validInformation(routesSpeedLimits);
        boolean validLimit = valid[0], validInformation = valid[1], validRoutes = valid[2], validIntersections = valid[3];
        if (1 <= cost && cost <= 100000 && validLimit && validInformation && validRoutes && validIntersections){
            ICPC red = new ICPC(cost, routesSpeedLimits);
            int higher = higherRSL(routesSpeedLimits);
            int costChange = costChange(routesSpeedLimits, higher);
            ArrayList<Integer> intersections = intersections(routesSpeedLimits);
            HashMap<Integer, Integer[]> costs = costs(routesSpeedLimits, cost, intersections);
            int minimum = minimum(costs, costChange);
            if(minimum != costChange){
                costs.forEach((k, v) -> {
                    String color = color(k);
                    if (v[0] < v[1] && v[0] >= 0) changeRSL(red, routesSpeedLimits, k, color);
                    else putSigns(red, color);
                });
            } else changeRSL(red, higher);
            red.makeVisible(); 
            JOptionPane.showMessageDialog(null, "El costo mínimo es: " + minimum);
        } else{
            last = false;
            JOptionPane.showMessageDialog(null, "No se pudo realizar la acción.");
            if (!validLimit) JOptionPane.showMessageDialog(null, "El límite de velocidad de cada ruta debe estar entre 1 y 100000");  
            if (!validInformation) JOptionPane.showMessageDialog(null, "Por cada carretera deben ir dos intersecciones y su límite de velocidad"); 
            if (!validRoutes) JOptionPane.showMessageDialog(null, "Las rutas deben ser diferentes.");
            if (!validIntersections) JOptionPane.showMessageDialog(null, "Las intersecciones de las rutas deben ser diferentes."); 
        }   
    }
    
    /**
     * Dice si la última acción se hizo o no.
     * @return Retorna si la última acción se pudo realizar o no.
     */
    public static boolean ok(){
        return last;
    }
    
    /*
     * Encuentra el límite de velocidad mas alto presente en la red ICPC.
     */
    private static int higherRSL(int[][] routesSpeedLimits){
        int higher = 0;
        for(int[] rsl: routesSpeedLimits) higher = Math.max(higher, rsl[2]);
        return higher;
    }
    
    /*
     * Encuentra el límite de velocidad más alto presente en las rutas que comparten una intersección dada.
     */
    private static int higherRSL(int[][] routesSpeedLimits, int intersection){
        int higher = 0;
        for(int[] rsl: routesSpeedLimits){
            if(rsl[0] == intersection || rsl[1] == intersection) higher = Math.max(higher, rsl[2]);
        }
        return higher;
    }
    
    /*
     * Da el número de rutas que salen de cada intersección de la red ICPC.
     */
    private static HashMap<Integer, Integer> numberRoutes(int[][] routesSpeedLimits){
        HashMap<Integer, Integer> nRoutes = new HashMap<Integer, Integer>();
        for(int[] rsl: routesSpeedLimits){
            if(!nRoutes.containsKey(rsl[0])) nRoutes.put(rsl[0], 0);
            if(!nRoutes.containsKey(rsl[1])) nRoutes.put(rsl[1], 0);
            nRoutes.put(rsl[0], nRoutes.get(rsl[0]) + 1);
            nRoutes.put(rsl[1], nRoutes.get(rsl[1]) + 1);
        }
        return nRoutes;
    }
    
    /*
     * Da las intersecciones presentes en la red ICPC.
     */
    private static ArrayList<Integer> intersections(int[][] routesSpeedLimits){
        ArrayList<Integer> intersections = new ArrayList<Integer>();
        for(int[] rsl: routesSpeedLimits){
            if(!intersections.contains(rsl[0])) intersections.add(rsl[0]);  
            if(!intersections.contains(rsl[1])) intersections.add(rsl[1]); 
        }
        return intersections;
    }
    
    /*
     * Devuelve el costo de cambiar todos los límites de velocidad de la red ICPC por el límite de velocidad mayor.
     */
    private static int costChange(int[][] routesSpeedLimits, int higher){
        int costChange = 0;
        for(int[] rsl: routesSpeedLimits) costChange += higher - rsl[2];
        return costChange;
    }
    
    /*
     * Devuelve el costo de cambiar todos los límites de velocidad de las rutas que comparten una intersección dada por el límite de velocidad mayor.
     */
    private static int costChange(int[][] routesSpeedLimits, int higher, int intersection, HashMap<Integer, Integer> nRoutes){
        int costChange = 0;
        boolean notHigher = false;
        for(int[] rsl: routesSpeedLimits){
            if(rsl[0] == intersection || rsl[1] == intersection){
                costChange += higher - rsl[2];
                if(rsl[2] != higher){
                    if(rsl[0] != intersection && nRoutes.get(rsl[0]) > 1) notHigher = true;
                    else if (rsl[1] != intersection && nRoutes.get(rsl[1]) > 1) notHigher = true;
                }
            }
        }   
        if(notHigher) costChange = -1;
        return costChange;
    }
    
    /*
     * Calcula los costos de poner solo señales y el costo de solo aumentar los límites de velocidad de cada intersección.
     */
    private static HashMap<Integer, Integer[]> costs(int[][] routesSpeedLimits, int cost, ArrayList<Integer> intersections){
        HashMap<Integer, Integer> nRoutes = numberRoutes(routesSpeedLimits);
        HashMap<Integer, Integer[]> costs = new HashMap<Integer, Integer[]>();
        for(int i: intersections){
            if(nRoutes.get(i) > 1){
                costs.put(i, new Integer[2]);
                int higher2 = higherRSL(routesSpeedLimits, i);
                int cost1 = costChange(routesSpeedLimits, higher2, i, nRoutes);
                int cost2 = nRoutes.get(i) * cost;
                costs.get(i)[0] = cost1;
                costs.get(i)[1] = cost2;
            }
        }
        return costs;
    }
    
    /*
     * Da el costo mínimo de poner señales y aumentar los límites de velocidad.
     */
    private static int minimum(HashMap<Integer, Integer[]> costs, int costChange){
        int minimum = 0;
        Integer[] keysC = costs.keySet().toArray(new Integer[costs.size()]);
        for (Integer k:keysC){
            if (costs.get(k)[0] < costs.get(k)[1] && costs.get(k)[0] >= 0) minimum += costs.get(k)[0];
            else minimum += costs.get(k)[1];
        }
        if(costChange < minimum) minimum = costChange;
        return minimum;
    }
    
    /*
     * Retorna el color correpondiente al número de intersección dado.
     */
    private static String color(int intersection){
        String color;
        if (intersection == 1) color = "red";
        else if (intersection == 2) color = "orange";
        else if (intersection == 3) color = "yellow";
        else if (intersection == 4) color = "green";
        else if (intersection == 5) color = "cyan";
        else if (intersection == 6) color = "magenta";
        else if (intersection == 7) color = "pink";
        else if (intersection == 8) color = "white";
        else if (intersection == 9) color = "lightGray";
        else if (intersection == 10) color = "gray";
        else if (intersection == 11) color = "darkGray";
        else if (intersection == 12) color = "blue";
        else  color = "black";
        return color;
    }
    
    /*
     * Ordena dos cadenas y las concatena, separandolas por un guión.
     */
    private static String order(String A, String B){
        String colors = B + "-" + A;
        if (A.compareTo(B) < 0) colors =  A + "-" + B;
        return colors;
    }
    
    /*
     * Cambia los límites de velocidad por el mayor límite de velocidad de la red ICPC. Reperesenta este cambio cambiando el color de las carreteras por verde.
     */
    private static void changeRSL(ICPC red, int higher){
        red.getRoads().forEach((k, v)->{
                if (v.getSpeedLimit() != higher){
                    try{
                        v.setSpeedLimit(higher);
                    }
                    catch (icpc.ICPCException icpce){
                        icpce.printStackTrace();
                    }
                    v.changeColor("green"); 
                }
        });
    }
    
    /*
     * Cambia los límites de velocidad por el mayor límite de velocidad de las rutas que comparten una intersección dada. Reperesenta este cambio cambiando el color de las carreteras por verde. 
     */
    private static void changeRSL(ICPC red, int[][] routesSpeedLimits, int k, String color){
        red.getRoads().forEach((k2, v2)->{
            int higher2 = higherRSL(routesSpeedLimits, k);
            if(k2.contains(color) && v2.getSpeedLimit() != higher2){
                try{
                    v2.setSpeedLimit(higher2);
                }
                catch (icpc.ICPCException icpce){
                    icpce.printStackTrace();
                }
                v2.changeColor("green"); 
            }
        });
    }
    
    /*
     * Pone señales a todas las rutas que comparten una intersección dada.
     */
    private static void putSigns(ICPC red, String color){
        red.getRoads().forEach((k2, v2)->{
            String[] colors = k2.split("-");
            if(colors[0].equals(color)) red.putSign(colors[0], colors[1], v2.getSpeedLimit());
            else if (colors[1].equals(color)) red.putSign(colors[1], colors[0], v2.getSpeedLimit());
        });
    }
    
    /*
     * Valida la información dada.
     */
    private static boolean[] validInformation(int[][] routesSpeedLimits){
        boolean validLimit = true, validInformation = true, validRoutes = true, validIntersections = true;
        ArrayList<String> routes = new ArrayList<String>();
        for (int i = 0; i < routesSpeedLimits.length && (validLimit || validInformation || validRoutes); i++){
            if (routesSpeedLimits[i].length != 3) validInformation = false;
            else {
                if (routesSpeedLimits[i][0] == routesSpeedLimits[i][1]) validIntersections = false;
                String route = order(routesSpeedLimits[i][0]+"", routesSpeedLimits[i][1]+"");
                if (routes.contains(route)) validRoutes = false;
                else routes.add(route);
            }
            if (routesSpeedLimits[i].length == 3 && (routesSpeedLimits[i][2] > 100000 || routesSpeedLimits[i][2] < 1)) validLimit = false;
        }
        boolean[] result = {validLimit, validInformation, validRoutes, validIntersections};
        return result;
    }
}