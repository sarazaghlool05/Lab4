import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProduct implements Record{
    private String customerSSN;
    private String productID;
    private LocalDate purchaseDate;
    private boolean paid;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
        this.paid = false;
    }

    public void setCustomerSSN(String customerSSN) {
        this.customerSSN = customerSSN;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
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
@Override
    public String lineRepresentation() {
        return customerSSN + "," + productID + "," + purchaseDate.format(formatter) + "," + paid;
    }
@Override
    public String getSearchKey() {
        return customerSSN + "," + productID + "," + purchaseDate.format(formatter);
    }

    @Override
    public String toString() {
        return "Customer SSN: " + customerSSN + ", Product ID: " + productID +
                ", Purchase Date: " + purchaseDate + ", Status: " + paid;
    }
}
