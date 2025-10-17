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


    public CustomerProduct getRecord(String key) {
        for (CustomerProduct cp : records) {
            if (cp.getSearchKey().equals(key)) {
                return cp;
            }
        }
        return null;
    }

    protected String lineRepresentation(CustomerProduct record){
        return record.lineRepresentation();
    }
}