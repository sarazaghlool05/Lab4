import java.io.*;
import java.util.ArrayList;
public abstract class Database<T> {
    protected ArrayList<T> records;
    protected String filename;
    public Database(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }
    protected abstract T createRecordFrom(String line);
    protected abstract String lineRepresentation(T record);
    public abstract T getRecord(String key);
    public void readFromFile() {
        records.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T record = createRecordFrom(line);
                if (record != null)
                    records.add(record);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + filename);
        }
    }
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (T record : records) {
                writer.println(lineRepresentation(record));
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + filename);
        }
    }
    public void insertRecord(T record) {
        records.add(record);
    }
    public void deleteRecord(String key) {
        T record = getRecord(key);
        if (record != null)
            records.remove(record);
        else
            System.out.println("Record not found: " + key);
    }
    public boolean contains(String key) {
        return getRecord(key) != null;
    }
    public ArrayList<T> returnAllRecords() {
        return records;
    }
    public static void main(String[] args) {
        ProductDatabase db = new ProductDatabase("products.txt");

        // Insert a few sample products
        db.insertRecord(new Product("P001", "Shampoo", "Dove", "Unilever", 100, 25.5f));
        db.insertRecord(new Product("P002", "Soap", "Lux", "Unilever", 200, 12.0f));
        db.insertRecord(new Product("P003", "Toothpaste", "Colgate", "P&G", 150, 18.75f));

        // Save all records to file
        db.saveToFile();
        System.out.println("✅ Products saved to file.");

        // Clear memory and reload from file
        db.returnAllRecords().clear();
        db.readFromFile();

        // Display all records
        System.out.println("\n📦 All Products from File:");
        for (Product p : db.returnAllRecords()) {
            System.out.println(
                    p.getSearchKey() + " | " +
                            p.getProductName() + " | " +
                            p.getManufacturerName() + " | " +
                            p.getSupplierName() + " | Qty: " +
                            p.getQuantity() + " | Price: " +
                            p.getPrice());
        }

        // Test getRecord
        System.out.println("\n🔍 Searching for product P002...");
        Product found = db.getRecord("P002");
        if (found != null)
            System.out.println("Found: " + found.getProductName() + " - Price: " + found.getPrice());
        else
            System.out.println("Product not found!");

        // Test deleteRecord
        db.deleteRecord("P003");
        System.out.println("\n🗑️ After deleting P003:");
        for (Product p : db.returnAllRecords()) {
            System.out.println(p.getSearchKey() + " - " + p.getProductName());
        }
        db.saveToFile();
    }

}
