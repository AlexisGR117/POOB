import java.util.*;
public class SplitAccountApp {
    private HashMap<String, Person> people = new HashMap<String, Person>();
    private HashMap<String, Group> groups = new HashMap<String, Group>();
    /**
     * Calcula el total de los gastos por categoria y nombre especificados.
     * INV: Debe estar en la aplicación la persona.
     * @param mEmail Correo de la persona.
     * @param cName Nombre de la categoria.
     * @return Total de los gastos.
     */
    public float getExpensesByCategory(String mEmail, String cName){
         Person p = findMember(mEmail);
         float t = 0;
         if (p!=null){
             t = people.get(mEmail).getExpensesByCategory(mEmail, cName);
         }
         return t;
    }
    /**
     * Encuentra una persona.
     * @param mEmail Correo de la persona.
     * @return La persona.
     */
    public Person findMember(String mEmail){
        return people.get(mEmail);
    }
    /**
     * Calcula el procentaje de gasto por categoria.
     * INV: Debe estar en la aplicación el grupo.
     * @param nGroup Nombre del gurpo.
     * @param cName Nombre de la categoria.
     * @return Porcentaje de gasto.
     */
    public float getPercentageByCategory(String nGroup, String cName){
        return 0;
    }    
}
