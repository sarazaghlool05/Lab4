import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

//written by sara zaghlool on wednesday 15/10
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

    //written by sara zaghlool on wednesday 15/10
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

    //written by sara zaghlool on wednesday 15/10
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

    //written by sara zaghlool on wednesday 15/10
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

    //written by sara zaghlool on friday 16/10
    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate){
        List<String> lines = new ArrayList<String>();
        int flag = 0;
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
                    flag = 1;
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
        if(flag == 1){
            String formattedDate = String.format("%02d-%02d-%04d", purchaseDate.getDayOfMonth(), purchaseDate.getMonthValue(), purchaseDate.getYear());
            try(BufferedWriter bw = new BufferedWriter(new FileWriter("CustomersProducts.txt",true))){
                bw.write(customerSSN + "," + productID + "," + formattedDate + ",false");
                bw.newLine();
            }catch(IOException e){
                System.out.println("Error in opening CustomersProducts file(from employee role fourth method)");
            }
            return true;
        }
        System.out.println("item not found");
        return false;
    }


    //written by sara zaghlool on friday 16/10
    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate , LocalDate returnDate){
        if(returnDate.isBefore(purchaseDate)){
            return -1;
        }
        else if(ChronoUnit.DAYS.between(purchaseDate, returnDate) > 14){
            return -1;
        }

        int flag = 0;
        List<String> linesC = new ArrayList<String>();
        String lineC;
        try(BufferedReader br = new BufferedReader(new FileReader("CustomersProducts.txt"))){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String target = customerSSN + "," + productID + "," + purchaseDate.format(formatter);
            while ((lineC = br.readLine()) != null) {
                if (!lineC.startsWith(target)) {
                    linesC.add(lineC);
                } else {
                    flag = 1;
                }
            }
        }catch (IOException e) {
            System.out.println("error in reading from file(from employee role 5th method)");
        }

        double price = -1;
        boolean foundProduct = false;
        if(flag == 1){
            List<String> lines = new ArrayList<String>();
            try(BufferedReader br = new BufferedReader(new FileReader("Products.txt"))){
                String line;
                while((line = br.readLine()) != null){
                    String[] words = line.trim().split("\\s*,\\s*");
                    if(words[0].equals(productID)){
                        foundProduct = true;
                        int quantity = Integer.parseInt(words[4]);
                        ++quantity;
                        price = Double.parseDouble(words[5]);
                        words[4] = Integer.toString(quantity);
                        line = String.join(",",words);
                    }
                    lines.add(line);
                }
            } catch (IOException e) {
                System.out.println("error in reading from file(from employee role 5th method)");
            }
            if(!foundProduct){
                return -1;
            }
            if(foundProduct){
                try(BufferedWriter bw = new BufferedWriter(new FileWriter("CustomersProducts.txt"))){
                    for (int i = 0; i < linesC.size(); i++) {
                        bw.write(linesC.get(i));
                        bw.newLine();
                    }
                }catch (IOException e) {
                    System.out.println("error in writing to file(from employee role 5th method)");
                }
            }

            try(BufferedWriter bw = new BufferedWriter(new FileWriter("Products.txt"))){
                for (int i = 0; i < lines.size(); i++) {
                    bw.write(lines.get(i));
                    bw.newLine();
                }
            }catch (IOException e) {
                System.out.println("error in writing to file(from employee role 5th method)");
            }
        }
        else{
            return -1;
        }
        return price;
    }

    //written by sara zaghlool on friday 16/10
    public boolean applyPayment(String customerSSN, LocalDate purchaseDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = purchaseDate.format(formatter);

        boolean flag = false;
        List<String> lines = new ArrayList<String>();
        try(BufferedReader br = new BufferedReader(new FileReader("CustomersProducts.txt"))){
            String line;
            while((line = br.readLine()) != null){
                String[] words = line.trim().split("\\s*,\\s*");
                if(words[0].equals(customerSSN) && words[2].equals(date)){
                    if(words[3].equals("false")){
                        words[3] = "true";
                        flag = true;
                        line = String.join(",",words);
                    }
                }
                lines.add(line);
            }
        }catch(IOException e){
            System.out.println("error in opening file (from employee role method 6)");
        }
        if(!flag){
            System.out.println("No matching unpaid record found or payment already done.");
            return flag;
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("CustomersProducts.txt"))){
            for(int i = 0; i < lines.size(); i++){
                bw.write(lines.get(i));
                bw.newLine();
            }
        }catch(IOException e){
            System.out.println("error in opening file (from employee role method 6)");
        }
        return flag;
    }

    //written by sara zaghlool on friday 16/10
    public void logout() {
        //we will need it in part 2
        System.out.println("All data already saved. Logging out.");
    }

}
