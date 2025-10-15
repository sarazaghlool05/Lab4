//written by sara zaghlool on wednesday 15/10
import java.io.*;
import java.util.ArrayList;

public class EmployeeRole {
    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    //do we need getters and setters?

    public EmployeeRole() {
        productsDatabase = new ProductDatabase("Products.txt");
        customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
        productsDatabase.readFromFile();
        customerProductDatabase.readFromFile();
    }

    //adding a new product to the file
    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price) {
        //ask why the price is not included in the function on the lab4 pdf

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("Products.txt", true))){
            bw.write(productID + "," + productName + "," + manufacturerName + "," + supplierName + "," + quantity + "," + price);
            bw.newLine();
            System.out.println("item added successfully");
        }catch(IOException e){
            System.out.println("Error in adding product to file(from class EmployeeRole)");
        }
    }

    //making a new array filled with all products in the files
    public Product[] getListOfProducts(){
        ArrayList<Product> products = new ArrayList<Product>();
        try(BufferedReader br = new BufferedReader(new FileReader("Products.txt"))){
            String line;
            while((line = br.readLine()) != null){
                String[] words = line.trim().split("\\s*,\\s*");
                Product p = new Product(words[0], words[1], words[2], words[3], Integer.parseInt(words[4]), Float.parseFloat(words[5]));
                products.add(p);
                System.out.println("new item added successfully");
            }
        }catch(IOException e){
            System.out.println("Error in reading from file(from class EmployeeRole)");
        }
        Product[] productArray = new Product[products.size()];
        for(int i = 0; i < products.size(); i++){
            productArray[i] = products.get(i);
        }
        return productArray;
    }
}
