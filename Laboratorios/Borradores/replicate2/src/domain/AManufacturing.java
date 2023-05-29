package domain;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.*;
import java.util.*;

import javax.swing.JOptionPane;

/**
 * Red bidimensional de celdas que contienen elementos.
 * 
 * @author ESCUELA 2022-02 - Angel Cuervo y Jefer Gonzalez
 */
public class AManufacturing implements Serializable {
    static private int SIZE=50;
    private Thing[][] lattice;
    /**
     * Constructor para objetos de clase AManufacturing.
     */
    public AManufacturing() {
        lattice=new Thing[SIZE][SIZE];
        for (int r=0;r<SIZE;r++){
            for (int c=0;c<SIZE;c++){
                lattice[r][c]=null;
            }
        }
        someThings();
    }
    
    /**
     * Devuelve el tamaño de la red bidimensional.
     * @return Entero que representa el tamaño de la red bidimensional.
     */
    public int getSize(){
        return SIZE;
    }

    /**
     * Da el elemento que está en una posición especificada.
     * @param r Número de la fila donde está el elemento.
     * @param c Número de la columna donde está el elemento.
     * @return El elemento ubicado en la posición dada.
     */
    public Thing getThing(int r,int c){
        return lattice[r][c];
    }
    
    /**
     * Coloca un elemento en la red bidimensional.
     * @param r Número de la fila donde va a estar el elemento.
     * @param c Número de la columna donde va a estar el elemento.
     * @param e Thing El elemento que se desea ubicar.
     */
    public void setThing(int r, int c, Thing e){
        lattice[r][c]=e;
    }

    /**
     * Algunos elemento que van a estar dentro de la red bidimensional.
     */
    public void someThings(){
        // Célula normal
        //Cell simba = new Cell(this, 1, 1, true);
        //Cell dala = new Cell(this, 2, 2, true);
        // Célula mimo
        //Mimo mufasa = new Mimo(this, 10, 5, true);
        //Mimo scar = new Mimo(this, 10, 6, true);
        //Mimo rafiki = new Mimo(this, 10, 7, true);
        // Célula kriptonita
        //Kriptonita one = new Kriptonita(this, 0, 0, true);
        //Kriptonita two = new Kriptonita(this, 0, SIZE-1, true);
        //Kriptonita three = new Kriptonita(this, SIZE-1, 0, true);
        //Kriptonita four = new Kriptonita(this, SIZE-1, SIZE-1, true);
        // Célula tímida
        //Timida angel = new Timida(this, 25, 25, true);
        //Timida alexis = new Timida(this, 24, 25, true);
        Kriptonita clark = new Kriptonita(this, 24, 24, true);
        //Cell simba = new Cell(this, 23, 25, true);
        //Cell dala = new Cell(this, 24, 24, true);
        //Cell mufasa = new Cell(this, 23, 24, true);
        //Cell scar = new Cell(this, 25, 26, true);
        //Cell rafiki = new Cell(this, 26, 25, true);
        //Mimo timon  = new Mimo(this, 24, 24);
        //Mimo puba  = new Mimo(this, 23, 25);
        //Mimo sarabi  = new Mimo(this, 23, 24);
        //Mimo simba = new Mimo(this, 25, 24);
        //Mimo dala = new Mimo(this, 26, 24);
        //Mimo mufasa = new Mimo(this, 26, 25);
        //Mimo scar = new Mimo(this, 26, 26);
        //Mimo rafiki = new Mimo(this, 23, 26);
        //Mimo zazu = new Mimo(this, 25, 26);
        //Mimo shenzi = new Mimo(this, 24, 26);
        //Pared two = new Pared(this, 11, 10);
        //Pared three = new Pared(this, 9, 10);
        //Pared four2 = new Pared(this, 10, 11);
        //Pared five = new Pared(this, 10, 9);
        //Pared one = new Pared(this, 10, 10);
        //Pared six = new Pared(this,9,9);
        Rfplicbte one = new Rfplicbte(this, 25, 25, true);
        Rfplicbte two = new Rfplicbte(this, 25, 24, true);
        Rfplicbte three = new Rfplicbte(this, 24, 25, true);
        // Virus
        //Virus virusOne = new Virus(this, 22, 25);
        //Virus virusTwo = new Virus(this, 25, 26);
        //Kriptonita two = new Kriptonita(this, 26, 26, false);
        //Kriptonita four = new Kriptonita(this, 27, 26, false);
        //Timida three = new Timida(this, 27, 25, true);
    }
    
    /**
     * Devuelve el número de vecinos activos al rededor de una posición.
     * @param r Número de la fila.
     * @param c Número de la columna.
     * @return El número de veciones que tiene la posición dada.
     */
    public int neighborsActive(int r, int c){
        int num=0;
        for(int dr=-1; dr<2;dr++){
            for (int dc=-1; dc<2;dc++){
                if ((dr!=0 || dc!=0) && inLatice(r+dr,c+dc) && 
                    (lattice[r+dr][c+dc]!=null) &&  (lattice[r+dr][c+dc].isActive())) num++;
            }
        }
        return (inLatice(r,c) ? num : 0);
    }
    
    /**
     * Dice si la celda de la red bidimensional en la posición dada está vacía.
     * @param r Número de la fila.
     * @param c Número de la columna.
     * @return Valor booleano que indica si la celda está vacía.
     */
    public boolean isEmpty(int r, int c){
        return (inLatice(r,c) && lattice[r][c]==null);
    }    
        
    /**
     * Dice si la posicion dada está dentro de la red bidimensional.
     * @param r Número de la fila.
     * @param c Número de la columna.
     * @return Valor booleano que indica si la posición es valida.
     */
    private boolean inLatice(int r, int c){
        return ((0<=r) && (r<SIZE) && (0<=c) && (c<SIZE));
    }
    
    /**
     * Cambia el estado de los elementos y los replica, si es el caso, de manera correpondiente al comportmaiento de cada uno.
     */
    public void ticTac(){     
        Thing[][] latticeCopy =  new Thing[SIZE][SIZE];
        for(int x = 0; x < SIZE; x++) latticeCopy[x] = Arrays.copyOf(lattice[x], SIZE);
        for(int x = 0; x < SIZE; x++) for (int y = 0; y < SIZE; y++){
            if (latticeCopy[x][y] != null && latticeCopy[x][y] == lattice[x][y]) lattice[x][y].replicate();    
        }
        for(int x = 0; x < SIZE; x++) for (int y = 0; y < SIZE; y++){
            if (lattice[x][y] != null) lattice[x][y].decide();    
        }
        for(int x = 0; x < SIZE; x++) for (int y = 0; y < SIZE; y++){
            if (lattice[x][y] != null) lattice[x][y].change();
        }
    }
   
   /**
    * Abre un archivo que contiene la información de una red AManufacturing.
    * @param file Archivo que se desea abrir.
    * @return Una red bidimensional AManufacturing.
    * @throws ReplicateException FILE_NOT_FOUND, si no se ha encontrado el archivo.
    * 							 STREAM_CORRUPTED, si el archivo está corrupto.
    * 							 INVALID_OBJECT, si uno o más objetos deserializados fallaron las pruebas de validación.
    * 							 OPTIONAL_DATA, si durante la lectura de un objeto hubo datos primitivos no leídos o al final de los datos pertenecientes a un objeto serializado en el flujo.
    * 							 NOT_ACTIVE, si la deserialización no está activa.
    * 							 EOF, si se ha alcanzado inesperadamente un final de archivo o final de secuencia durante la entrada.
    * 							 INPUT_OUTPUT_ERROR, si ha ocurrido un error en la entrada.
    * 							 OPEN_ERROR, si hubo un error al abrir el archivo.
    */
   public static AManufacturing abra(File file) throws ReplicateException {
		try {
			FileInputStream fileInput = new FileInputStream(file);
			ObjectInputStream objectInput = new ObjectInputStream(fileInput);
			AManufacturing aManufacturing = (AManufacturing)objectInput.readObject();
			objectInput.close();
			return aManufacturing;
		} catch (FileNotFoundException e) {
            throw new ReplicateException(ReplicateException.FILE_NOT_FOUND);
        } catch (StreamCorruptedException e) {
            throw new ReplicateException(ReplicateException.STREAM_CORRUPTED);
        } catch (InvalidObjectException e) {
            throw new ReplicateException(ReplicateException.INVALID_OBJECT);
        } catch (OptionalDataException e) {
            throw new ReplicateException(ReplicateException.OPTIONAL_DATA);
        } catch (NotActiveException e) {
            throw new ReplicateException(ReplicateException.NOT_ACTIVE);
        } catch (EOFException e) {
            throw new ReplicateException(ReplicateException.EOF);
        } catch (IOException e) {
                throw new ReplicateException(ReplicateException.INPUT_OUTPUT_ERROR);
        } catch (Exception e) {
			throw new ReplicateException(ReplicateException.OPEN_ERROR);
        } 
   }
   
   /**
    * Guarda un archivo con la información de la red AManufacturing.
    * @param file Archivo en el que se desea guardar.
    * @throws ReplicateException FILE_NOT_FOUND, si no se ha encontrado el archivo.
    * 							 INVALID_CLASS, si el tiempo de ejecución de serialización a detectaso un problemas con una clase.
    * 							 NOT_SERIALIZABLE, si una de las clases no es serializable.
    * 							 NOT_ACTIVE, si la serialización no está activa.
    * 							 INPUT_OUTPUT_ERROR, si ha ocurrido un error en la salida.
    * 							 SAVE_ERROR, si hubo un error al guardar la red AManufacturing.
    */
   public void guarde(File file) throws ReplicateException {
		try {
			FileOutputStream fileOutput = new FileOutputStream(file);
			ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
			objectOutput.writeObject(this);
			objectOutput.close();
        } catch (FileNotFoundException e) {
            throw new ReplicateException(ReplicateException.FILE_NOT_FOUND);
        } catch (InvalidClassException e) {
            throw new ReplicateException(ReplicateException.INVALID_CLASS);
        } catch (NotSerializableException e) {
            throw new ReplicateException(ReplicateException.NOT_SERIALIZABLE);
        } catch (NotActiveException e) {
            throw new ReplicateException(ReplicateException.NOT_ACTIVE);
        } catch (IOException e) {        	
            throw new ReplicateException(ReplicateException.INPUT_OUTPUT_ERROR);
		} catch (Exception e) {
			throw new ReplicateException(ReplicateException.SAVE_ERROR);
		}
   }
   
   /**
    * Importa un archivo que contiene la información de una red AManufacturing.
    * @param file Archivo que se desea importar.
    * @return Una red bidimensional AManufacturing.
    * @throws ReplicateException CLASS_NOT_FOUND, si una de clases no se ha encontrado.
    * 							 INSUFFICIENT_INFORMATION, si la información no está completa.
    * 							 OUT_OF_RANGE, si alguna posicion dada en la informacion se sale del rango.
    * 							 NOT_A_NUMBER, si en una fila o columna no dan un número entero.
    * 							 INSTANCIATION_ERROR, si No se ha podido instanciar un objeto.
    * 							 INVOCATION_ERROR, si ha ocurrido un error en la invocación de un objeto.
    * 							 ILLEGAL_ACCES, si no se tiene acceso al constructor del objeto para poder invocarlo.
    * 							 INPUT_OUTPUT_ERROR, si ha ocurrido un error en la salida.
    * 							 IMPORT_ERROR, si hubo un error al importar el archivo.
    */
   public static AManufacturing importe(File file) throws ReplicateException {
		try {       
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			ArrayList<String[]> information = new ArrayList<String[]>();
			String[] thing;
			String line = reader.readLine();
			while (line != null) {
				thing = line.trim().split(" ");
				information.add(thing);
				line = reader.readLine();
			}
			reader.close();	
			return crearAManufacturing(information);
		} catch (ReplicateException e) {
			escribirError(e.getMessage());
			throw e;
		} catch (IOException e) {
			escribirError(e.getMessage());
			throw new ReplicateException(ReplicateException.INPUT_OUTPUT_ERROR);
		} catch (Exception e) {
			escribirError(e.getMessage());
			throw new ReplicateException(ReplicateException.IMPORT_ERROR);
		}
   }
   
   /**
    * Crea una red AManufacturing.
    * @param information ArrayList que contiene la informacion de las Thing del AManufacturing.
    * @return La red AManufacturing con la información dada.
    * @throws ReplicateException CLASS_NOT_FOUND, si una de clases no se ha encontrado.
    * 							 INSUFFICIENT_INFORMATION, si la información no está completa.
    * 							 OUT_OF_RANGE, si alguna posicion dada en la informacion se sale del rango.
    * 							 NOT_A_NUMBER, si en una fila o columna no dan un número entero.
    * 							 INSTANCIATION_ERROR, si No se ha podido instanciar un objeto.
    * 							 INVOCATION_ERROR, si ha ocurrido un error en la invocación de un objeto.
    * 							 ILLEGAL_ACCES, si no se tiene acceso al constructor del objeto para poder invocarlo.
    * 							 IMPORT_ERROR, si hubo un error al importar el archivo.
    */
   public static AManufacturing crearAManufacturing(ArrayList<String[]> information) throws ReplicateException {
		AManufacturing aManufacturing = new AManufacturing();
	   for (int l = 1; l <= information.size(); l++) {
			String[] thing = information.get(l - 1);
			try {
				Class<?> c = Class.forName("domain." + thing[0]);
				Constructor<?>[] cons = c.getConstructors();
				validarPosicion(thing[1], l);
				validarPosicion(thing[2], l);
				Object[] parameters = {aManufacturing, Integer.parseInt(thing[1]), Integer.parseInt(thing[2]), false};
				cons[0].newInstance(parameters);
			} catch (ReplicateException e) {
				throw e;
	        } catch (ClassNotFoundException e) {
	            throw new ReplicateException(ReplicateException.CLASS_NOT_FOUND + " en la linea " + l + ": '" + thing[0] + "'");
		    } catch(ArrayIndexOutOfBoundsException e) {
		    	throw new ReplicateException(ReplicateException.INSUFFICIENT_INFORMATION + " en la linea " + l);
	        } catch (IllegalArgumentException e) {
	            throw new ReplicateException(ReplicateException.ILLEGAL_ARGUMENT + " en la linea " + l);
	        } catch (InstantiationException e) {
	            throw new ReplicateException(ReplicateException.INSTANCIATION_ERROR + " en la linea " + l);
	        } catch (InvocationTargetException e) {
	            throw new ReplicateException(ReplicateException.INVOCATION_ERROR + " en la linea " + l);
	        } catch (IllegalAccessException  e) {
	            throw new ReplicateException(ReplicateException.ILLEGAL_ACCES + " en la linea " + l);
	        } catch (Exception e) {
	    	    throw new ReplicateException(ReplicateException.IMPORT_ERROR + " en la linea " + l);
	        }
	   }
	   return aManufacturing;
   }
   
   /**
    * Valida la posición de un Thing.
    * @param position La fila o columna del Thing.
    * @param line El número de línea donde está escrito el Thing.
    * @throws ReplicateException OUT_OF_RANGE, si alguna posicion dada en la informacion se sale del rango.
    * 							 NOT_A_NUMBER, si en una fila o columna no dan un número entero.
    */
   public static void validarPosicion(String position, int line) throws ReplicateException {
		try {
			if (Integer.parseInt(position) < 0 || Integer.parseInt(position) >= 50) {
				throw new ReplicateException(ReplicateException.OUT_OF_RANGE + " en la linea " + line + ": '" + position + "'");
			};
		} catch(NumberFormatException e) {
			throw new ReplicateException(ReplicateException.NOT_A_NUMBER + " en la linea " + line + ": '" + position + "'");
		}
   }
   
   /**
    * Escribe un error dado en un archivo .txt
    * @param message String con el mensaje del error que se desea escribir en el archivo de texto.
    * @throws ReplicateException INPUT_OUTPUT_ERROR, si ha ocurrido un error en la salida.
    */
   public static void escribirError (String message) throws ReplicateException {
		try {
			File errores = new File("replicateErrG.txt");
			FileWriter writer = new FileWriter(errores, true);	 
			writer.write(message + "\n");
			writer.close();
		} catch (IOException e1) {
			throw new ReplicateException(ReplicateException.INPUT_OUTPUT_ERROR);
		}
   }
   
   /**
    * Exporta un archivo con la información de la red AManufacturing.
    * @param file Archivo en el que se desea exportar.
    * @throws ReplicateException INPUT_OUTPUT_ERROR, si ha ocurrido un error en la salida.
    * 							 EXPORT_ERROR, si hubo un error al exportar la red AManufacturing.
    */
   public void exporte(File file) throws ReplicateException {
		try {
			FileOutputStream fileOutput = new FileOutputStream(file);
			PrintWriter writer = new PrintWriter(fileOutput);
			writer.println(this.toString());
			writer.close();
	     } catch (IOException e) {        	
	    	 throw new ReplicateException(ReplicateException.INPUT_OUTPUT_ERROR);
	     } catch (Exception e) {
	    	 throw new ReplicateException(ReplicateException.EXPORT_ERROR);
	     }
   }
   
   @Override
   public String toString() {
	   String s = "";
		for (int i = 0; i < lattice.length; i++) for (int j = 0; j < lattice[i].length; j++) {
			if (lattice[i][j] != null ) {
				if (!s.equals("")) s += "\n";
				 s += lattice[i][j].toString() + " " + i + " " + j;
			}
		}
	   return s;
   }
   
   /**
    * Abre un archivo con la información de la red AManufacturing.
    * @param file Archivo que se desea abrir.
    * @throws ReplicateException IN_CONSTRUCTION, si el método está construcción.
    */
   public static AManufacturing abra00(File file) throws ReplicateException {
	   throw new ReplicateException(ReplicateException.IN_CONSTRUCTION);
   }
   
   /**
    * Guarda un archivo con la información de la red AManufacturing.
    * @param file Archivo en el que se desea guardar.
    * @throws ReplicateException IN_CONSTRUCTION, si el método está construcción.
    */
   public void guarde00(File file) throws ReplicateException {
	   throw new ReplicateException(ReplicateException.IN_CONSTRUCTION);
   }
   
   /**
    * Importa un archivo que contiene la información de una red AManufacturing.
    * @param file Archivo que se desea importar.
    * @return Una red bidimensional AManufacturing.
    * @throws ReplicateException IN_CONSTRUCTION, si el método está construcción.
    */
   public static AManufacturing importe00(File file) throws ReplicateException {
	   throw new ReplicateException(ReplicateException.IN_CONSTRUCTION);
   }
   
   /**
    * Exporta un archivo con la información de la red AManufacturing.
    * @param file Archivo en el que se desea exportar.
    * @throws ReplicateException IN_CONSTRUCTION, si el método está construcción.
    */
   public void exporte00(File file) throws ReplicateException {
	   throw new ReplicateException(ReplicateException.IN_CONSTRUCTION);
   }
   
   /**
    * Abre un archivo que contiene la información de una red AManufacturing.
    * @param file Archivo que se desea abrir.
    * @return Una red bidimensional AManufacturing.
    * @throws ReplicateException GENERAL_ERROR, si ha ocurrido un error al ejecutar el método.
    */
   public static AManufacturing abra01(File file) throws ReplicateException {
		try {
			FileInputStream fileInput = new FileInputStream(file);
			ObjectInputStream objectInput = new ObjectInputStream(fileInput);
			AManufacturing aManufacturing = (AManufacturing)objectInput.readObject();
			objectInput.close();
			return aManufacturing;
		} catch (Exception e) {
			throw new ReplicateException(ReplicateException.GENERAL_ERROR);
		}
   }
   
   /**
    * Guarda un archivo con la información de la red AManufacturing.
    * @param file Archivo en el que se desea guardar.
    * @throws ReplicateException GENERAL_ERROR, si ha ocurrido un error al ejecutar el método.
    */
   public void guarde01(File file) throws ReplicateException {
		try {
			FileOutputStream fileOutput = new FileOutputStream(file);
			ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
			objectOutput.writeObject(this);
			objectOutput.close();
		} catch (IOException e) {
			throw new ReplicateException(ReplicateException.GENERAL_ERROR);
		}
   }
   
   /**
    * Importa un archivo que contiene la información de una red AManufacturing.
    * @param file Archivo que se desea importar.
    * @return Una red bidimensional AManufacturing.
    * @throws ReplicateException GENERAL_ERROR, si ha ocurrido un error al ejecutar el método.
    */
   public static AManufacturing importe01(File file) throws ReplicateException {
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			AManufacturing aManufacturing = new AManufacturing();
			String line = reader.readLine();
			while (line != null) {
				String[] thing = line.trim().split(" ");
				Class<?> c = Class.forName("domain." + thing[0]);
				Constructor<?>[] cons = c.getConstructors();
				Object[] parameters = {aManufacturing, Integer.parseInt(thing[1]), Integer.parseInt(thing[2]), false};
				cons[0].newInstance(parameters);
				line = reader.readLine();
			}
			reader.close();
			return aManufacturing;
		} catch (Exception e) {
			throw new ReplicateException(ReplicateException.GENERAL_ERROR);
		}
   }
   
   /**
    * Exporta un archivo con la información de la red AManufacturing.
    * @param file Archivo en el que se desea exportar.
    * @throws ReplicateException GENERAL_ERROR, si ha ocurrido un error al ejecutar el método.
    */
   public void exporte01(File file) throws ReplicateException {
		try {
			FileOutputStream fileOutput = new FileOutputStream(file);
			PrintWriter writer = new PrintWriter(fileOutput);
			writer.println(this.toString());
			writer.close();
		} catch (IOException e) {
			throw new ReplicateException(ReplicateException.GENERAL_ERROR);
		}
   }
   
   /**
    * Importa un archivo que contiene la información de una red AManufacturing.
    * @param file Archivo que se desea importar.
    * @return Una red bidimensional AManufacturing.
    * @throws ReplicateException CLASS_NOT_FOUND, si una de clases no se ha encontrado.
    * 							 ILLEGAL_ARGUMENT, si se ha pasado un argumento ilegal o inapropiado.
    * 							 INSTANCIATION_ERROR, si No se ha podido instanciar un objeto.
    * 							 INVOCATION_ERROR, si ha ocurrido un error en la invocación de un objeto.
    * 							 ILLEGAL_ACCES, si no se tiene acceso al constructor del objeto para poder invocarlo.
    * 							 INPUT_OUTPUT_ERROR, si ha ocurrido un error en la salida.
    * 							 IMPORT_ERROR, si hubo un error al importar el archivo.
    */
   public static AManufacturing importe02(File file) throws ReplicateException {
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			AManufacturing aManufacturing = new AManufacturing();
			String line = reader.readLine();
			while (line != null) {
				String[] thing = line.trim().split(" ");
				Class<?> c = Class.forName("domain." + thing[0]);
				Constructor<?>[] cons = c.getConstructors();
				Object[] parameters = {aManufacturing, Integer.parseInt(thing[1]), Integer.parseInt(thing[2]), false};
				cons[0].newInstance(parameters);
				line = reader.readLine();
			}
			reader.close();
			return aManufacturing;
        } catch (ClassNotFoundException e) {
            throw new ReplicateException(ReplicateException.CLASS_NOT_FOUND);
        } catch (IllegalArgumentException e) {
            throw new ReplicateException(ReplicateException.ILLEGAL_ARGUMENT);
        } catch (InstantiationException e) {
            throw new ReplicateException(ReplicateException.INSTANCIATION_ERROR);
        } catch (InvocationTargetException e) {
            throw new ReplicateException(ReplicateException.INVOCATION_ERROR);
        } catch (IllegalAccessException  e) {
            throw new ReplicateException(ReplicateException.ILLEGAL_ACCES);
        } catch (IOException  e) {
            throw new ReplicateException(ReplicateException.INPUT_OUTPUT_ERROR);
		} catch (Exception e) {
			throw new ReplicateException(ReplicateException.IMPORT_ERROR);
		}
   }
   
   /**
    * Exporta un archivo con la información de la red AManufacturing.
    * @param file Archivo en el que se desea exportar.
    * @throws ReplicateException INPUT_OUTPUT_ERROR, si ha ocurrido un error en la salida.
    * 							EXPORT_ERROR, si hubo un error al exportar la red AManufacturing.
    */
   public void exporte02(File file) throws ReplicateException {
		try {
			FileOutputStream fileOutput = new FileOutputStream(file);
			PrintWriter writer = new PrintWriter(fileOutput);
			writer.println(this.toString());
			writer.close();
	     } catch (IOException e) {        	
	    	 throw new ReplicateException(ReplicateException.INPUT_OUTPUT_ERROR);
	     } catch (Exception e) {
	    	 throw new ReplicateException(ReplicateException.EXPORT_ERROR);
	     }
   }
   
   /**
    * Importa un archivo que contiene la información de una red AManufacturing.
    * @param file Archivo que se desea importar.
    * @return Una red bidimensional AManufacturing.
    * @throws ReplicateException CLASS_NOT_FOUND, si una de clases no se ha encontrado.
    * 							 INSUFFICIENT_INFORMATION, si la información no está completa.
    * 							 OUT_OF_RANGE, si alguna posicion dada en la informacion se sale del rango.
    * 							 NOT_A_NUMBER, si en una fila o columna no dan un número entero.
    * 							 INSTANCIATION_ERROR, si No se ha podido instanciar un objeto.
    * 							 INVOCATION_ERROR, si ha ocurrido un error en la invocación de un objeto.
    * 							 ILLEGAL_ACCES, si no se tiene acceso al constructor del objeto para poder invocarlo.
    * 							 INPUT_OUTPUT_ERROR, si ha ocurrido un error en la salida.
    * 							 IMPORT_ERROR, si hubo un error al importar el archivo.
    */
   public static AManufacturing importe03(File file) throws ReplicateException {
		try {       
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			ArrayList<String[]> information = new ArrayList<String[]>();
			String[] thing;
			String line = reader.readLine();
			while (line != null) {
				thing = line.trim().split(" ");
				information.add(thing);
				line = reader.readLine();
			}
			reader.close();	
			return crearAManufacturing(information);
		} catch (ReplicateException e) {
			escribirError(e.getMessage());
			throw e;
		} catch (IOException e) {
			escribirError(e.getMessage());
			throw new ReplicateException(ReplicateException.INPUT_OUTPUT_ERROR);
		} catch (Exception e) {
			escribirError(e.getMessage());
			throw new ReplicateException(ReplicateException.IMPORT_ERROR);
		}
   }
   
   /**
    * Exporta un archivo con la información de la red AManufacturing.
    * @param file Archivo en el que se desea exportar.
    * @throws ReplicateException INPUT_OUTPUT_ERROR, si ha ocurrido un error en la salida.
    * 							 EXPORT_ERROR, si hubo un error al exportar la red AManufacturing.
    */
   public void exporte03(File file) throws ReplicateException {
		try {
			FileOutputStream fileOutput = new FileOutputStream(file);
			PrintWriter writer = new PrintWriter(fileOutput);
			writer.println(this.toString());
			writer.close();
	     } catch (IOException e) {        	
	    	 throw new ReplicateException(ReplicateException.INPUT_OUTPUT_ERROR);
	     } catch (Exception e) {
	    	 throw new ReplicateException(ReplicateException.EXPORT_ERROR);
	     }
   }
}
