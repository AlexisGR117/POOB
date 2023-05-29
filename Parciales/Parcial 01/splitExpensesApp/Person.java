import java.util.*;
import java.time.*;

public class Person {
    private String name;
    private String email;
    private String password;
    private boolean active;
    private LocalDate signUpDate;
    private HashMap<String,Group> groups;
    /**
     * Calcula el total de los gastos por categoria y nombre especificados.
     * @param mEmail Correo de la persona.
     * @param cName Nombre de la categoria.
     * @return Total de los gastos.
     */
    public float getExpensesByCategory(String mEmail, String cName){
         Set<String>  keys = groups.keySet();   
         String[] keysArray = keys.toArray(new String[keys.size()]);
         float t = 0;
         for (int i = 0; i < keysArray.length; i++){
             t += groups.get(keysArray[i]).getExpensesByCategory(mEmail, cName);
         }
         return t;
    }
}
