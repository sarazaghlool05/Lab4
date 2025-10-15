import java.io.*;
import java.util.ArrayList;

//written by Bassant on wednesday 15/10 @23:50
public class ProductDatabase {
    private ArrayList<Product> records;
    private String filename;

    public ProductDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }

    public void readFromFile() {
        records.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String data;
            while ((data = reader.readLine()) != null) {
                Product readproduct = createRecordFrom(data);
                records.add(readproduct);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
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

    public ArrayList<Product> returnAllRecords() {
        return records;
    }

    public boolean contains(String key) {
        for (int i = 0; i < records.size(); i++) {
            Product x = records.get(i);
            if (x.getSearchKey().equals(key))
                return true;
        }
        return false;
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

    public void insertRecord(Product record) {
        records.add(record);
    }

    public void deleteRecord(String key) {
        Product delete = getRecord(key);
        if (delete != null)
            records.remove(delete);
    }

    public void saveToFile() {
        try (BufferedWriter write = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < records.size(); i++) {
                Product q = records.get(i);
                write.write(q.lineRepresentation());
                write.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file" + e.getMessage());
        }
    }
    }
