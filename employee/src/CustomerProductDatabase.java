import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;

public class CustomerProductDatabase {
    private ArrayList<CustomerProduct> records;
    private String filename;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public CustomerProductDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }

    public void readFromFile() {
        records.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isEmpty = true;
            while ((line = br.readLine()) != null) {
                isEmpty = false;
                CustomerProduct cp = createRecordFrom(line);
                if (cp != null) {
                    records.add(cp);
                }
            }
            if (isEmpty) {
                System.out.println("File is empty: " + filename);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file " + filename);
        }
    }


    public CustomerProduct createRecordFrom(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }
        String[] parts = line.split(",");
        String customerSSN = parts[0];
        String productID = parts[1];
        LocalDate purchaseDate = LocalDate.parse(parts[2], formatter);
        boolean paid = Boolean.parseBoolean(parts[3]);
        CustomerProduct cp = new CustomerProduct(customerSSN, productID, purchaseDate);
        cp.setPaid(paid);
        return cp;
    }

    public ArrayList<CustomerProduct> returnAllRecords() {
        return records;
    }

    public boolean contains(String key) {
        for (CustomerProduct cp : records) {
            if (cp.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public CustomerProduct getRecord(String key) {
        for (CustomerProduct cp : records) {
            if (cp.getSearchKey().equals(key)) {
                return cp;
            }
        }
        return null;
    }

    public void insertRecord(CustomerProduct record) {
        records.add(record);
    }

    public void deleteRecord(String key) {
        records.removeIf(cp -> cp.getSearchKey().equals(key));
    }

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (CustomerProduct cp : records) {
                pw.println(cp.lineRepresentation());
            }
        } catch (Exception e) {
            System.out.println("Error saving records to " + filename);
        }
    }
}