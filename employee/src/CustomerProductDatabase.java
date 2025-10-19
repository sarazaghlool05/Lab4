import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;

public class CustomerProductDatabase extends Database<CustomerProduct>{
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public CustomerProductDatabase(String filename) {
        super(filename);

    }

    @Override
    protected CustomerProduct createRecordFrom(String line) {
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


    @Override
    public CustomerProduct getRecord(String key) {
        for (int i = 0; i < records.size(); i++) {
            CustomerProduct cp = records.get(i);
            if (cp.getSearchKey().equals(key)) {
                return cp;
            }
        }
        return null;
    }
    @Override
    protected String lineRepresentation(CustomerProduct record){
        return record.lineRepresentation();
    }
}