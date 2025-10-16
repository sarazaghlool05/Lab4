//written by sara zaghlool on friday 16/10

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
}
