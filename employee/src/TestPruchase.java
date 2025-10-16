import java.time.LocalDate;
public class TestPruchase {
    public static void main(String[] args){
        /*
        EmployeeRole e = new EmployeeRole();
        CustomerProduct[] c = e.getListOfPurchasingOperations();
        for(int i = 0; i < c.length; i++){
            System.out.println(c[i].toString());
        }
         */




        EmployeeRole emp = new EmployeeRole();
        boolean result = emp.purchaseProduct("123456789", "P2394", LocalDate.now());
        System.out.println(result ? "✅ Purchase successful" : "❌ Product sold out or error");

    }
}
