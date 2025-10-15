public class EmployeeRole {
    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public EmployeeRole() {
        productsDatabase = new ProductDatabase();
        customerProductDatabase = new CustomerProductDatabase();
    }

}
