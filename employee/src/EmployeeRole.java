//written by sara zaghlool on wednesday 15/10

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate){
        List<String> lines = new ArrayList<String>();
        try(BufferedReader br = new BufferedReader(new FileReader("Products.txt"))){
            String line;
            while((line = br.readLine()) != null){
                String[] words = line.trim().split("\\s*,\\s*");
                if(productID.equals(words[0]) && Integer.parseInt(words[4]) == 0){
                    lines.add(line);
                    br.close();         //may cause an issue be carefull
                    return false;
                }
                else if(productID.equals(words[0])){
                    int quantity = Integer.parseInt(words[4]);
                    --quantity;
                    words[4] = Integer.toString(quantity);
                    line = String.join(",", words);
                    lines.add(line);
                }
                else{
                    lines.add(line);
                }
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("Products.txt"))) {
                for (int i = 0; i < lines.size(); i++) {
                    bw.write(lines.get(i));
                    bw.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error in opening Products file(from employee role fourth method)");
            }
        }catch(IOException e){
            System.out.println("Error in opening Products file(from employee role fourth method)");
        }

        String formattedDate = String.format("%02d-%02d-%04d", purchaseDate.getDayOfMonth(), purchaseDate.getMonthValue(), purchaseDate.getYear());
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("CustomersProducts.txt",true))){
            bw.write(customerSSN + "," + productID + "," + formattedDate + ",false");
            bw.newLine();
        }catch(IOException e){
            System.out.println("Error in opening CustomersProducts file(from employee role fourth method)");
        }
        return true;
    }
}
