import java.time.LocalDate;

public class CustomerProduct {
    private String customerSSN, productID;
    private LocalDate purchaseDate;
    private boolean paid;

    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate){
        this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
        this.paid = false;
    }
    public void setPaid(boolean paid){
        this.paid = paid;
    }

}
