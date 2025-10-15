import java.sql.Array;
import java.util.ArrayList;

public class EmployeeRole {
    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public EmployeeRole() {
        productsDatabase = new ProductDatabase();
        customerProductDatabase = new CustomerProductDatabase();
    }

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity){
        //add to the file
        productsDatabase.saveToFile();
    }

    public Product[] getListOfProducts(){
        Arraylist<Product> ProductsInFile = new ArrayList<Product>();
        
    }
}
