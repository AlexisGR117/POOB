package domain;  

import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;
/**
 * Class that is responsible for registering.
 */
public class Log{
    public static String nombre="IEMOIS";
    
    /**
     * Take the exceptions obtained and log them in a file called IEMOIS.log
     * @param e Exception to be logged.
     */
    public static void record(Exception e){
        try{
            Logger logger=Logger.getLogger(nombre);
            logger.setUseParentHandlers(false);
            FileHandler file=new FileHandler(nombre+".log",true);
            file.setFormatter(new SimpleFormatter());
            logger.addHandler(file);
            logger.log(Level.SEVERE,e.toString(),e);
            file.close();
        }catch (Exception oe){
            oe.printStackTrace();
            System.exit(0);
        }
    }
}
    
