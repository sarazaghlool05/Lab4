public class TestPruchase {
    public static void main(String[] args){
        EmployeeRole e = new EmployeeRole();
        CustomerProduct[] c = e.getListOfPurchasingOperations();
        for(int i = 0; i < c.length; i++){
            System.out.println(c[i].toString());
        }
    }
}
