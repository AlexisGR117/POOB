import java.util.ArrayList;
import java.time.*;

public class Expense {
    private String description;
    private float amount;
    private LocalDate creationDate;
    private boolean splitWithAll;
    private String notes;
    private LocalDate closeDate;
    private Person owner;
    private Ticket ticket;
    private Category category;
    private ArrayList<Payment> payments;
    private ArrayList<Notification> notifications;
    public float getExpensesByCategory(String mEmail, String cName){
         float t = 0;
         boolean ok = category.isCategory(cName);
         if (ok && closeDate.isBefore(LocalDate.now())){
             t += payments.getAmountByMember(mEmail, cName);
         }
         return t;
    }
}
