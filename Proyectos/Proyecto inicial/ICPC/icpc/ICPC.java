package icpc; 
import shapes.*;

import java.util.*;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.lang.Math;
import java.lang.reflect.Constructor;
/**
 * Simulación inspirada en el Problem B de la maratón de programación internacional 2020 The Cost of Speed Limits.
 * 
 * @author Angel Cuervo y Jefer Gonzalez 
 * @version 1.0 (31/08/2022)
 */
public class ICPC{
    private HashMap<String, Intersection> intersections = new HashMap<String, Intersection>();
    private HashMap<String, Road> roads = new HashMap<String, Road>();
    private HashMap<String, Sign> signs = new HashMap<String, Sign>();
    private ArrayList<String> wrongSigns = new ArrayList<String>();
    private ArrayList<String> unNecessarySigns = new ArrayList<String>();
    private boolean isVisible, last;
    private int length, width, signalCost;
    /**
     * Constructor para objetos de clase ICPC.
     * @param length Largo de la red ICPC.
     * @param width Ancho de la red ICPC.
     */
    public ICPC(int length, int width){
        last = true;
        try{
            validLength(length);
        } catch (ICPCException e){
            if(e.getMessage().equals(ICPCException.INVALID_LENGTH)) length = 950;
            last = false;
        }
        try{
            validWidth(width);
        } catch (ICPCException e){
            if(e.getMessage().equals(ICPCException.INVALID_WIDTH)) width = 950;
            last = false;
        }
        this.length = length;
        this.width = width;
        isVisible = false;
    }

    /**
     * Constructor para objetos de clase ICPC
     * @param length Largo de la red ICPC.
     * @param width Ancho de la red ICPC.
     * @param cost Costo de las señales de la red ICPC.
     */
    public ICPC(int length, int width, int cost){
        this(length, width);
        try{
            validCost(cost);
        } catch (ICPCException e){
            if(e.getMessage().equals(ICPCException.INVALID_COST)) cost = 10;
            last = false;
        }
        this.signalCost = cost; 
    }

    /**
     * Constructor para objetos de clase ICPC
     * @param cost Costo de las señales de la red ICPC.
     * @param routesSpeedLimits Información definida en el Problem B.
     */
    public ICPC(int cost, int[][] routesSpeedLimits){
        this(950, 950, cost); 
        boolean l = last;
        try{
            validInformation(routesSpeedLimits);
        } catch (ICPCException e){
            if(e.getMessage().equals(ICPCException.INVALID_INFORMATION) || e.getMessage().equals(ICPCException.INVALID_SPEED_LIMIT)) {
               routesSpeedLimits = new int[0][0]; 
            }
            l = false;
        }
        for (int[] rsl: routesSpeedLimits){
            String colorOne = Canvas.colors[rsl[0]-1], colorTwo = Canvas.colors[rsl[1]-1];
            if (!existI(colorOne)) addIntersection(colorOne, (int)(Math.random()*951), (int)(Math.random()*951)); 
            if (!existI(colorTwo)) addIntersection(colorTwo, (int)(Math.random()*951), (int)(Math.random()*951)); 
            addRoute(colorOne, colorTwo);
            routeSpeedLimit(colorOne, colorTwo, rsl[2]);
        }
        last = l;
    }    
    
    /**
     * Añade una nueva intersección a la red de carreteras de ICPC.
     * @param color Color que identifica a la intersección.
     * @param x Poscición en el eje x de la intersección.
     * @param y Poscición en el eje y de la intersección.
     */
    public void addIntersection(String color, int x, int y){
        erase();
        try{
            intersections.put(color, new Intersection(this, color, x, y)); 
            last = true;
        }catch (ICPCException e){
            last = false;
            if (isVisible){
                JOptionPane.showMessageDialog(null, "No se pudo realizar la acción.");
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        draw();
    }
    
    /**
     * Añade una nueva intersección a la red de carreteras de ICPC.
     * @param type Tipo de intersección puede ser Normal, Hermit o Neddy. 
     * @param color Color que identifica a la intersección.
     * @param x Poscición en el eje x de la intersección.
     * @param y Poscición en el eje y de la intersección.
     */
    public void addIntersection(String type, String color, int x, int y){
        erase();
        try{
            validIntersectionType(type);
            if (type == "Normal") intersections.put(color, new Intersection(this, color, x, y)); 
            else if (type == "Hermit") intersections.put(color, new Hermit(this, color, x, y));
            else intersections.put(color, new Needy(this, color, x, y));
            last = true;
        }catch (ICPCException e){
            last = false;
            if (isVisible){
                JOptionPane.showMessageDialog(null, "No se pudo realizar la acción.");
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        draw();
    }
    
    /**
     * Agrega una nueva ruta a la red de carreteras de ICPC dada dos intersecciones existentes.
     * @param intersectionA Color de la primera intersección a la cuál llega la ruta.
     * @param intersectionB Color de la segunda intersección a la cuál llega la ruta.
     */
    public void addRoute(String intersectionA, String intersectionB){
        erase();
        try{
            String route = order(intersectionA, intersectionB);
            roads.put(route, new Road(this, route));
            last = true;
        }catch (ICPCException e){
            last = false;
            if (isVisible){
                JOptionPane.showMessageDialog(null, "No se pudo realizar la acción.");
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } 
        draw();
    }
    
    /**
     * Agrega una nueva ruta a la red de carreteras de ICPC dada dos intersecciones existentes.
     * @param type Tipo de ruta puede ser Normal, Rebel o Fixed. 
     * @param intersectionA Color de la primera intersección a la cuál llega la ruta.
     * @param intersectionB Color de la segunda intersección a la cuál llega la ruta.
     */
    public void addRoute(String type, String intersectionA, String intersectionB){
        erase();
        try{
            validRouteType(type);
            String route = order(intersectionA, intersectionB);
            if (type == "Normal") roads.put(route, new Road(this, route));
            else if (type == "Rebel") roads.put(route, new Rebel(this, route));
            else roads.put(route, new Fixed(this, route));
            last = true;
        }catch (ICPCException e){
            last = false;
            if (isVisible){
                JOptionPane.showMessageDialog(null, "No se pudo realizar la acción.");
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        draw();
    }
    
    /**
     * Asigna un límite de velocidad a una ruta.
     * @param intersectionA Color de la primera intersección a la cuál llega la ruta.
     * @param intersectionB Color de la segunda intersección a la cuál llega la ruta.
     * @param speedLimit Límite de velocidad de la ruta.
     */
    public void routeSpeedLimit(String intersectionA, String intersectionB, int speedLimit){
        erase();
        try{
            validSpeedLimit(speedLimit);
            String route = order(intersectionA, intersectionB);
            roads.get(route).setSpeedLimit(speedLimit);
            signs.forEach((key, value) -> {
                if (key.contains(intersectionA) || key.contains(intersectionB)) necessary(key);
            });           
            last = true;
        }catch (Exception e){
            last = false;
            if (isVisible){
                JOptionPane.showMessageDialog(null, "No se pudo realizar la acción.");
                if (e instanceof ICPCException) JOptionPane.showMessageDialog(null, e.getMessage());
                if (e instanceof NullPointerException) JOptionPane.showMessageDialog(null, ICPCException.ROUTE_NOT_EXIST);
            }
        }    
        draw();
    }
    
    /**
     * Pone una nueva señal de límite de velocidad a la red de carreteras de ICPC.
     * @param intersectionA Color de la primera intersección que conforma la ruta.
     * @param intersectionB Color de la segunda intersección que conforma la ruta.
     * @param speddLimit Velocidad máxima que va a tener la carretera.
     */
    public void putSign(String intersectionA, String intersectionB, int speedLimit){
        erase();
        try{
            String sign = intersectionA + "-" + intersectionB, route = order(intersectionA, intersectionB);
            signs.put(sign, new Sign(this, sign, route, speedLimit));
            wrong(sign, route);
            necessary(sign);
            last = true;
        }catch (ICPCException e){
            last = false;
            if (isVisible){
                JOptionPane.showMessageDialog(null, "No se pudo realizar la acción.");
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        draw();
    }

    /**
     * Pone una nueva señal de límite de velocidad a la red de carreteras de ICPC.
     * @param type Tipo de señal puede ser Normal, Twin o Cautious. 
     * @param intersectionA Color de la primera intersección que conforma la ruta.
     * @param intersectionB Color de la segunda intersección que conforma la ruta.
     * @param speddLimit Velocidad máxima que va a tener la carretera.
     */
    public void putSign(String type, String intersectionA, String intersectionB, int speedLimit){
        erase();
        try{
            String sign = intersectionA + "-" + intersectionB, route = order(intersectionA, intersectionB), sign2 = intersectionB + "-" + intersectionA; ;
            validSignType(type);
            if (type == "Twin" || type == "Unique") validWhitoutSigns(route);
            if (type == "Normal") signs.put(sign, new Sign(this, sign, route, speedLimit));
            else if (type == "Twin"){
                signs.put(sign, new Twin(this, sign, route, speedLimit)); 
                signs.put(sign2, new Twin(this, sign2, route, speedLimit));  
                wrong(sign2, route);
                necessary(sign2);
            }
            else if (type == "Cautious") signs.put(sign, new Cautious(this, sign, route, roads.get(route).getSpeedLimit()));
            else signs.put(sign, new Unique(this, sign, route, speedLimit));
            wrong(sign, route);
            necessary(sign);
            last = true;
        }catch (ICPCException e){
            last = false;
            if (isVisible){
                JOptionPane.showMessageDialog(null, "No se pudo realizar la acción.");
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        draw();
    }
    
    /**
     * Elimina una intersección de la red de carreteras de ICPC.
     * @param color Color con el que se identifica la intersección que se va a eliminar.
     */
    public void delIntersection(String color){
        boolean used = existI(color), containsFixed = false;
        String[] keysR = inArray(roads);
        for (String k:keysR){
            if (k.contains(color) && roads.get(k) instanceof Fixed) containsFixed = true;
        }  
        if (used && !containsFixed){
            erase();
            intersections.remove(color);
            delRoutes(color);
            delSigns(color);
            signs.forEach((key, value) -> {necessary(key);});
            draw();
            last = true;
        } else {
            last = false;
            if (isVisible){
                JOptionPane.showMessageDialog(null, "No se pudo realizar la acción.");
                if (!used)JOptionPane.showMessageDialog(null, "El color dado no está siendo usado por una intersección.");  
                if (containsFixed)JOptionPane.showMessageDialog(null, "La intersección contiene una ruta Fixed"); 
            }
        }   
    }
    
    /**
     * Elimina una carretera de la red de carreteras de ICPC.
     * @param intersectionA Color de la primera intersección que conforma la ruta.
     * @param intersectionB Color de la segunda intersección que conforma la ruta.
     */
    public void delRoute(String intersectionA, String intersectionB){
        String colors = order(intersectionA, intersectionB);
        boolean used = existR(colors), isFixed = false;
        if (used) isFixed = roads.get(colors) instanceof Fixed;
        if (used && !isFixed){
            erase();
            roads.remove(colors);
            delSigns(colors);
            signs.forEach((key, value) -> {
                if (key.contains(intersectionA) || key.contains(intersectionB)) necessary(key);
            });
            if (numberRoutes(intersectionA) == 0 && intersections.get(intersectionA) instanceof Needy){
                delIntersection(intersectionA);    
            }
            if (numberRoutes(intersectionB) == 0 && intersections.get(intersectionB) instanceof Needy){
                delIntersection(intersectionB);    
            }
            draw();
            last = true;
        } else {
            last = false;
            if (isVisible){
                JOptionPane.showMessageDialog(null, "No se pudo realizar la acción");
                if (!used) JOptionPane.showMessageDialog(null, "No existe la carretera con las intersecciones dadas.");  
                if (isFixed) JOptionPane.showMessageDialog(null, "Las rutas de tipo Fixed no se pueden eliminar."); 
            }
        }
    }
    
    /**
     * Quita una señal de límite de velocidad a la red de carreteras de ICPC.
     * @param intersectionA Color de la primera intersección que conforma al ruta donde está ubicada la señal.
     * @param intersectionB Color de la segunda intersección que conforma al ruta donde está ubicada la señal.
     */
    public void removeSign(String intersectionA, String intersectionB){
        String colors = intersectionA + "-" + intersectionB;
        boolean used = existS(colors);
        if (used){
            erase();
            if (signs.get(colors) instanceof Twin){
                signs.remove(intersectionB + "-" + intersectionA);
            }
            signs.remove(colors);
            unNecessarySigns.remove(colors);
            wrongSigns.remove(colors);
            draw();
            last = true;
        } else {
            last = false;
            if (isVisible){
                JOptionPane.showMessageDialog(null, "No se pudo realizar la acción");
                if (!used) JOptionPane.showMessageDialog(null, "No existe la señal en la carretera de las intersecciones dadas.");
            }
        }
    }
    
    /**
     * Da todas las intersecciones que se encuentran el la red de carreteras de ICPC.
     * @return Retorna un arreglo con los colores que se identifican las intersecciones.
     */
    public String[] intersections(){
        last = true;
        return inArray(intersections);
    }
    
    /**
     * Da todas las carreteras que se encuentran el la red de carreteras de ICPC.
     * @return Retorna un arreglo con los colores de las intersecciones que conforman la rutas.
     */
    public String[][] roads(){
        last = true;
        return inArrays(roads);
    }
    
    /**
     * Da todas las señales de límite de velocidad que se encuentran el la red de carreteras de ICPC.
     * @return Retorna un arreglo con los colores de las intersecciones que conforman la rutas donde se encuentran las señales de límite de velocidad.
     */
    public String[][] signs(){
        last = true;
        return inArrays(signs);
    }

    /**
     * Da todas las señales de límite de velociad erroneas que se encuentran el la red de carreteras de ICPC.
     * @return Retorna un arreglo con los colores de las intersecciones que conforman la rutas donde se encuentran las señales de límite de velocidad erroneas.
     */
    public String[][] wrongSigns(){
        last = true;
        return inArrays(wrongSigns);
    }    
    
    /**
     * Da todas las señales de límite de velociad innecesarias que se encuentran el la red de carreteras de ICPC.
     * @return Retorna un arreglo con los colores de las intersecciones que conforman la rutas donde se encuentran las señales de límite de velocidad innecesarias.
     */
    public String[][] unNecessarySigns(){
        last = true;
        return inArrays(unNecessarySigns);
    }   
    
    /**
     * Da el costo total de las señales de la red de carreteras ICPC.
     * @return Costo total.
     */
    public int totalSignsCost(){
        last = true;
        return signalCost * signs.size();
    }   
    
    /**
     * Hace visible la red de carreteras de ICPC.
     */
    public void makeVisible(){
        isVisible = true;
        last = true;
        draw();
    }
    
    /**
     * Hace Invisible la red de carreteras de ICPC.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
        last = true;
    }
    
    /**
     * Termina el simulador de la red de carreteras de ICPC.
     */
    public void finish(){
        System.exit(0);
    }
    
    /**
     * Dice si la última acción se hizo o no.
     * @return Retorna si la última acción se pudo realizar o no.
     */
    public boolean ok(){
        return last;
    }
    
    /**
     * @return El largo de la red ICPC.
     */
    public int getLength(){
        return length;
    }
    
    /**
     * @return El ancho de la red ICPC.
     */    
    public int getWidth(){
        return width;
    }
    
    /**
     * @return El costo de poner una señal en la red ICPC.
     */
    public int getSignalCost(){
        return signalCost;
    }
   
    /**
     * @return Las intersecciones presentes en la red ICPC.
     */
    public HashMap<String, Intersection> getIntersections(){
        return intersections;
    }
    
    /**
     * @return Las rutas presentes en la red ICPC.
     */
    public HashMap<String, Road> getRoads(){
        return roads;
    }
    
    /**
     * @return Las señales presentes en la red ICPC.
     */
    public HashMap<String, Sign> getSigns(){
        return signs;
    }
    
    /**
     * @return Las señales innecesarias presentes en la red ICPC.
     */
    public ArrayList<String> getUnNecessarySigns(){
        return unNecessarySigns;
    }
    
    /*
     * Borra de la pantalla la red de carreteras de ICPC.
     */
    private void erase(){
        if(isVisible) {
            intersections.forEach((key, value) -> {
                value.makeInvisible();
            });
            roads.forEach((key, value) -> {
                value.makeInvisible();
            });
            signs.forEach((key, value) -> {
                value.makeInvisible();
            });
        }
    } 
    
    /*
     * Dibuja en la pantalla la red de carreteras de ICPC.
     */
    private void draw() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas(width + 50, length + 50);
            roads.forEach((key, value) -> {
                value.makeVisible();
            });
            intersections.forEach((key, value) -> {
                value.makeVisible();
            });
            signs.forEach((key, value) -> {
                value.makeVisible();
            });
        }
    }   
    
    /*
     * Ordena dos cadenas alfabeticamente y las une con un guión.
     */
    private String order(String A, String B){
        String colors = B + "-" + A;
        if (A.compareTo(B) < 0){
            colors =  A + "-" + B;
        }
        return colors;
    }
    
    /**
     * Dice sí existe una intersección con ese color.
     * @param intersection Color de la intersección.
     * @return Valor booleano que dice si ya existe una intersección con ese color en al red.
     */
    public boolean existI(String intersection){
        return intersections.containsKey(intersection);
    }
    
    /**
     * Dice sí existe una ruta que une dos intersecciones con esos colores.
     * @param route Intersecciones que conforman la ruta concatenados con un guion.
     * @return Valor booleano que dice si ya existe una ruta con ese color en al red.
     */
    public boolean existR(String route){
        return roads.containsKey(route);
    }
    
    /*
     * Dice sí existe una señal en una ruta que une dos intersecciones con esos colores.
     */
    public boolean existS(String sign){
        return signs.containsKey(sign);
    }
    
    /*
     * Dice sí existe un límite de velocidad en la carretera dada.
     */
    public boolean existRSL(String route){
        return roads.get(route).getSpeedLimit() > 0;
    }
    
    /*
     * Dice sí el nuevo límite de velocidad es mayor al que había antes.
     */
    public boolean higherRSL(String route, int speedLimit){
        boolean existR = existR(route), existRSL = existRSL(route), higherRSL = false;
        if (existR && existRSL) higherRSL = speedLimit > roads.get(route).getSpeedLimit();
        else if (existR && !existRSL) higherRSL = true;
        return higherRSL;
    }
    
    /*
     * Elimina todas las carreteras asociadas a ala intersección dada.
     */
    private void delRoutes(String intersection){
        String[] keysR = inArray(roads);
        for (String k:keysR){
            String[] intersections = k.split("-");
            if (k.contains(intersection)) delRoute(intersections[0], intersections[1]);
        }     
    }
    
    /*
     * Elimina todas las señales de las carreteras que tienen las intersecciones con el o los colores dados.
     */
    private void delSigns(String color){
        String[] colors = color.split("-");
        if (color.contains("-")){
            if (existS(color)) removeSign(colors[0], colors[1]);
            if (existS(colors[1] + "-" + colors[0])) removeSign(colors[1], colors[0]);
        } else {
            String[] keysS = inArray(signs);
            for (String k:keysS){
                if (k.contains(color)){
                    String[] colors2 = k.split("-");
                    removeSign(colors2[0], colors2[1]);
                }
            }    
        }
    }
    
    /*
     * Obtiene las llaves de las intersecciones en un arreglo de cadenas.
     */
    private String[] inArray(HashMap map){
        Set<String> keys = map.keySet();
        return keys.toArray(new String[0]);
    }
    
    /*
     * Obtiene las llaves de las carreteras y señales en un arreglo de arreglos.
     */
    private String[][] inArrays(HashMap map){
        Iterator<String> keysSet = map.keySet().iterator();
        String[][] keys = new String[map.size()][2];
        int i = 0;
        while (keysSet.hasNext()){
            String[] colors = keysSet.next().split("-");
            keys[i][0] = colors[0];
            keys[i][1] = colors[1];
            i ++;
        }
        return keys;
    }

    /*
     * Obtiene las intersecciones de las carreteras y señales en un arreglo de arreglos.
     */
    private String[][] inArrays(ArrayList<String> array){
        String[][] a = new String[array.size()][2];
        for (int i = 0; i < array.size(); i++){
            String[] colors = array.get(i).split("-");
            a[i][0] = colors[0];
            a[i][1] = colors[1];
        }
        return a;
    }
    
    /*
     * Dice si una señal de límite de velocidad es innecesaria, si lo es, entonces cambia su color por verde y la añade a las señales innecesarias.
     */
    private void necessary(String sign){
        String[] intersections = sign.split("-"), keysR = inArray(roads);
        String route = order(intersections[0], intersections[1]);
        boolean unNecessary = true;
        for (int i = 0; i < keysR.length && unNecessary; i++){
            if ((keysR[i].contains(intersections[0]) || (keysR[i].contains(intersections[1]) && signs.get(sign) instanceof Unique)) && 
                 roads.get(route).getSpeedLimit() != roads.get(keysR[i]).getSpeedLimit()){
                unNecessary = false;
            }
        }    
        if (unNecessary && !unNecessarySigns.contains(sign)){
            unNecessarySigns.add(sign);
            signs.get(sign).changeBackColor("green");
        } else if (!unNecessary && unNecessarySigns.contains(sign)){
            unNecessarySigns.remove(sign);
        } 
    }
    
    /*
     * Dice si una señal de límite de velocidad es erronea, si lo es, entonces cambia su color de número por rojo y la añade a las señales erroneas.
     */
    private void wrong(String sign, String route){
        if (signs.get(sign).getNumber().getNumber() > roads.get(route).getSpeedLimit()){
            wrongSigns.add(sign);
            signs.get(sign).changeColorNumber("red");
        }
    }
    
    /*
     * Valida la información de las carreteras junto con sus límites de velocidad.
     */
    private void validInformation(int[][] routesSpeedLimits) throws ICPCException{
        for (int i = 0; i < routesSpeedLimits.length; i++){
            if (routesSpeedLimits[i].length != 3) throw new ICPCException(ICPCException.INVALID_INFORMATION);    
            if (routesSpeedLimits[i].length == 3 && (routesSpeedLimits[i][2] > 100000 || routesSpeedLimits[i][2] < 1)) throw new ICPCException(ICPCException.INVALID_SPEED_LIMIT);
        }
    }
    
    /**
     * Valida si la intersección tiene una posición diferente a las presentes en la red ICPC.
     * @param x Posición horizontal de la intersección.
     * @param y Posición vertical de la intersección.
     * @return Valor booleano que dice si es valida la intersección.
     */
    public boolean validIntersection(int x, int y){
        ArrayList<String> positions = new ArrayList<String>();
        intersections.forEach((k, v) ->{
            positions.add(v.getXPosition() + "-" + v.getYPosition());
        });
        return !positions.contains(x + "-" + y);
    }
    
    /**
     * Valida si la ruta es innecesaria .
     * @param intersectionA Primera intersección de la ruta.
     * @param y Posición Segunda intersección de la ruta.
     * @return Valor booleano que dice si es valida la ruta.
     */
    public boolean validRoute(String intersectionA, String intersectionB){
        ArrayList<String> i = new ArrayList<String>();
        for(String[] r: roads()){
            if (!i.contains(r[0])) i.add(r[0]);
            if (!i.contains(r[1])) i.add(r[1]);
        }
        return !(i.contains(intersectionA) && i.contains(intersectionB));
    }
    
    /**
     * Da la posición en x de una intersección
     * @param intersection Color de la intersección.
     * @return Posición horizontal en la que se encuentra la intersección.
     */
    public int xPosition(String intersection){
        return intersections.get(intersection).getXPosition();
    }
    
    /**
     * Da la posición en y de una intersección
     * @param intersection Color de la intersección.
     * @return Posición vetical en la que se encuentra la intersección.
     */
    public int yPosition(String intersection){
        return intersections.get(intersection).getYPosition();
    }
    
    /*
     * Devuelve el número de rutas que tiene asociada una intersección.
     */
    private int numberRoutes(String intersection){
        int n = 0;
        String[] keysR = inArray(roads);
        for (String k:keysR){
            if (k.contains(intersection)) n++;
        }   
        return n;
    }
    
    /**
     * Dice si la señal es Hermit y ya tiene una ruta asociada.
     * @param intersection Intersección que se quiere validar.
     * @return Valor booleano que dice si la intersección es Hermit y ya tiene una ruta asociada.
     */
    public boolean validRIntersection(String intersection){
        boolean exist = existI(intersection), valid = true, isHermit;
        if (exist){
            isHermit = intersections.get(intersection) instanceof Hermit;   
            valid = !isHermit || numberRoutes(intersection) < 1;
        }
        return valid;
    }
    
    private void validLength(int length) throws ICPCException{
        if (200 > length || length > 950) throw new ICPCException(ICPCException.INVALID_LENGTH);
    } 
    
    private void validWidth(int width) throws ICPCException{
        if (200 > width || width > 950) throw new ICPCException(ICPCException.INVALID_WIDTH);
    }   
    
    private void validCost(int cost) throws ICPCException{
        if (1 > cost || cost > 100000) throw new ICPCException(ICPCException.INVALID_COST);
    }  
    
    public void validSpeedLimit(int speedLimit) throws ICPCException{
        if (speedLimit > 100000 || speedLimit < 1) throw new ICPCException(ICPCException.INVALID_SPEED_LIMIT);
    }  
    
    private void validIntersectionType(String type) throws ICPCException{
        if (!(type == "Normal" || type == "Hermit" || type == "Needy")) throw new ICPCException(ICPCException.INVALID_INTERSECTION_TYPE);
    }  
    
    private void validRouteType(String type) throws ICPCException{
        if (!(type == "Normal" || type == "Rebel" || type == "Fixed")) throw new ICPCException(ICPCException.INVALID_ROUTE_TYPE);
    } 
    
    private void validSignType(String type) throws ICPCException{
        if (!(type == "Normal" || type == "Twin" || type == "Cautious" || type == "Unique")) throw new ICPCException(ICPCException.INVALID_SIGN_TYPE);
    } 
    
    private void validWhitoutSigns(String route) throws ICPCException{
        String intersectionA = route.split("-")[0], intersectionB = route.split("-")[1];
        if (signs.containsKey(intersectionA+"-"+intersectionB) || signs.containsKey(intersectionB+"-"+intersectionA)){
            throw new ICPCException(ICPCException.ROUTE_WITH_SIGN);
        }
    }  
}