import java.util.*;
import java.time.*;

public class Group {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int capacity;
    private boolean active;
    private HashMap<String,Person> members;
    private Person organizer;
    private ArrayList<Expense> expenses;
    /**
     * Calcula el total de los gastos por categoria y nombre especificados.
     * @param mEmail Correo de la persona.
     * @param cName Nombre de la categoria.
     * @return Total de los gastos.
     */
    public float getExpensesByCategory(String mEmail, String cName){
         float t = 0;
         for (int i = 0; i < expenses.size(); i++){
             t += expenses.get(i).getExpensesByCategory(mEmail, cName);
         }
         return t;
    }
}
