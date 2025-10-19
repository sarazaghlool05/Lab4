import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

//written by sara zaghlool on wednesday 15/10
public class EmployeeRole {
    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;
    private static final float default_price = 500;

    public ProductDatabase getProductsDatabase() {
        return productsDatabase;
    }

    public CustomerProductDatabase getCustomerProductDatabase() {
        return customerProductDatabase;
    }

    public void setProductsDatabase(ProductDatabase productsDatabase) {
        this.productsDatabase = productsDatabase;
    }

    public void setCustomerProductDatabase(CustomerProductDatabase customerProductDatabase) {
        this.customerProductDatabase = customerProductDatabase;
    }

    public EmployeeRole() {
        productsDatabase = new ProductDatabase("Products.txt");
        customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
        productsDatabase.readFromFile();
        customerProductDatabase.readFromFile();
    }

    //written by sara zaghlool on wednesday 15/10
    //adding a new product to the file
    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity) {
        //ask why the price is not included in the function on the lab4 pdf
        if (productsDatabase.contains(productID)) {
            System.out.println("Product already exists!");
            return;
        }
        Product p = new Product(productID, productName, manufacturerName, supplierName, quantity, default_price);
        productsDatabase.insertRecord(p);
        productsDatabase.saveToFile();
        System.out.println("Product added successfully");
    }

    //written by sara zaghlool on wednesday 15/10
    public Product[] getListOfProducts(){
        return productsDatabase.returnAllRecords().toArray(new Product[0]);
    }

    //written by sara zaghlool on wednesday 15/10
    public CustomerProduct[] getListOfPurchasingOperations(){
        return customerProductDatabase.returnAllRecords().toArray(new CustomerProduct[0]);
    }

    //written by sara zaghlool on friday 16/10
    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate){
        Product product = productsDatabase.getRecord(productID);
        if (product == null) {
            System.out.println("Product not found!");
            return false;
        }
        if (product.getQuantity() == 0) {
            System.out.println("Out of stock!");
            return false;
        }

        // Decrease quantity
        product.setQuantity(product.getQuantity() - 1);
        productsDatabase.saveToFile();

        // Add purchase
        CustomerProduct purchase = new CustomerProduct(customerSSN, productID, purchaseDate);
        purchase.setPaid(false);
        customerProductDatabase.insertRecord(purchase);
        customerProductDatabase.saveToFile();

        return true;
    }


    //written by sara zaghlool on friday 16/10
    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate , LocalDate returnDate){
        if (returnDate.isBefore(purchaseDate) || ChronoUnit.DAYS.between(purchaseDate, returnDate) > 14)
            return -1;
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String key = customerSSN + "," + productID + "," +
                purchaseDate.format(formatter);
        CustomerProduct record = customerProductDatabase.getRecord(key);
        if (record == null) return -1;

        Product product = productsDatabase.getRecord(productID);
        if (product == null) return -1;

        // Increase quantity and save
        product.setQuantity(product.getQuantity() + 1);
        productsDatabase.saveToFile();

        // Remove purchase record
        customerProductDatabase.deleteRecord(key);
        customerProductDatabase.saveToFile();

        return product.getPrice();
    }

    //written by sara zaghlool on friday 16/10
    public boolean applyPayment(String customerSSN, LocalDate purchaseDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateKey = purchaseDate.format(formatter);

        boolean updated = false;
        for (CustomerProduct c : customerProductDatabase.returnAllRecords()) {
            if (c.getCustomerSSN().equals(customerSSN)
                    && c.getPurchaseDate().format(formatter).equals(dateKey)
                    && !c.isPaid()) {
                c.setPaid(true);
                updated = true;
                break;
            }
        }

        if (updated) customerProductDatabase.saveToFile();
        return updated;
    }

    //written by sara zaghlool on friday 16/10
    public void logout() {
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
        System.out.println("All data saved. Logging out.");
    }

}
