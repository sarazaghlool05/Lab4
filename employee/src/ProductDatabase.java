import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.*;
import java.util.ArrayList;

//written by Bassant on wednesday 15/10 @23:50
public class ProductDatabase extends Database<Product> {

    public ProductDatabase(String filename) {
        super(filename);
    }


    public Product createRecordFrom(String line) {
        String[] data = line.split(",");
        if (data.length != 6) {
            throw new IllegalArgumentException("Invalid Record Format");
        }
        String Id = data[0];
        String productName = data[1];
        String manufacturer = data[2];
        String supplierName = data[3];
        int quantity = Integer.parseInt(data[4]);
        float price = Float.parseFloat(data[5]);
        return new Product(Id, productName, manufacturer, supplierName, quantity, price);
    }

    public Product getRecord(String key) {
        for (int i = 0; i < records.size(); i++) {
            Product x = records.get(i);
            if (x.getSearchKey().equals(key)) {
                return x;
            }
        }
        return null;
    }
    protected  public String lineRepresentation(Product p){
        return productID+","+productName+","+manufacturerName+","+supplierName+","+quantity+","+price;
    }
}