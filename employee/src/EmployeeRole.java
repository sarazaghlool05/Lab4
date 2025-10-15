import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EmployeeRole {
    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    //do we need getters and setters?

    public EmployeeRole() {
        productsDatabase = new ProductDatabase("Products.txt");
        customerProductDatabase = new CustomerProductDatabase("customersProducts.txt");
        productsDatabase.readFromFile();
        customerProductDatabase.readFromFile();
    }

    public CustomerProduct[] getListOfPurchasingOperations(){
        ArrayList<CustomerProduct> purchasedProducts = new ArrayList<CustomerProduct>();
        try(BufferedReader br = new BufferedReader(new FileReader("CustomersProducts.txt"))){
            String line;
            while((line = br.readLine()) != null){
                String[] words = line.trim().split("\\s*,\\s*");
                LocalDate date = LocalDate.parse(words[2], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                CustomerProduct product = new CustomerProduct(words[0], words[1], date);
                product.setPaid(Boolean.parseBoolean(words[3]));
                purchasedProducts.add(product);
                System.out.println("Purchased product added successfully");
            }
            CustomerProduct[] products = new CustomerProduct[purchasedProducts.size()];
            for(int i = 0; i < purchasedProducts.size(); i++){
                products[i] = purchasedProducts.get(i);
            }
            return products;
        }catch(IOException e){
            System.out.println("error in reading from file(from employee role 3rd method)");
        }
        return new CustomerProduct[0];
    }
}
