import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProduct {
    private String customerSSN;
    private String productID;
    private LocalDate purchaseDate;
    private boolean paid;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
        this.paid = false;
    }

    public String getCustomerSSN() {
        return customerSSN;
    }

    public String getProductID() {
        return productID;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String lineRepresentation() {
        return customerSSN + "," + productID + "," + purchaseDate.format(formatter) + "," + paid;
    }

    public String getSearchKey() {
        return customerSSN + "," + productID + "," + purchaseDate.format(formatter);
    }
}
