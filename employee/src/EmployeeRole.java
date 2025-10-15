public class EmployeeRole {
    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public EmployeeRole() {
        productsDatabase = new ProductDatabase();
        customerProductDatabase = new CustomerProductDatabase();
    }

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity){
        //add to the array list
        productsDatabase.insertRecord(productsDatabase.createRecordFrom(productsDatabase.lineRepresentation()));
        //add to the file
        productsDatabase.saveToFile();
    }
}
