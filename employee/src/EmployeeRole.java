//written by sara zaghlool on Friday 16/10
import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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
            while((lineC = br.readLine()) != null){
                String[] words = lineC.trim().split("\\s*,\\s*");
                if(!words[0].equals(customerSSN)){
                    linesC.add(lineC);
                }
                else{
                    flag = 1;
                }
            }
        }catch (IOException e) {
            System.out.println("error in reading from file(from employee role 5th method)");
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("CustomersProducts.txt"))){
            for (int i = 0; i < linesC.size(); i++) {
                bw.write(linesC.get(i));
                bw.newLine();
            }
        }catch (IOException e) {
            System.out.println("error in writing to file(from employee role 5th method)");
        }

        double price = -1;
        if(flag == 1){
            List<String> lines = new ArrayList<String>();
            try(BufferedReader br = new BufferedReader(new FileReader("Products.txt"))){
                String line;
                while((line = br.readLine()) != null){
                    String[] words = line.trim().split("\\s*,\\s*");
                    if(words[0].equals(productID)){
                        int quantity = Integer.parseInt(words[4]);
                        ++quantity;
                        price = Double.parseDouble(words[5]);
                        words[4] = Integer.toString(quantity);
                        line = String.join(",",words);
                        lines.add(line);
                    }
                    else{
                        lines.add(line);
                    }
                }
            } catch (IOException e) {
                System.out.println("error in reading from file(from employee role 5th method)");
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
}
