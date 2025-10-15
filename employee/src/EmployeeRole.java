import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EmployeeRole {
    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    //do we need getters and setters?

    public EmployeeRole() {
        productsDatabase = new ProductDatabase("Products.txt");
        customerProductDatabase = new CustomerProductDatabase("customerProducts.txt");
        productsDatabase.readFromFile();
        customerProductDatabase.readFromFile();
    }

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price) {
        //ask why the price is not included in the function on the lab4 pdf

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("Products.txt", true))){
            bw.write(productID + "," + productName + "," + manufacturerName + "," + supplierName + "," + quantity + "," + price);
            bw.newLine();
            System.out.println("item added successfully");
        }catch(IOException e){
            System.out.println("Error in adding product to file");
        }
    }
}
