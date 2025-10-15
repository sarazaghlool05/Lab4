public class Product {
    private String  productID, productName, manufacturerName, supplierName;
    private int quantity;
    private float price;

    //constructor
    public Product(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price){
        this.productID = productID;
        this.productName = productName;
        this.manufacturerName = manufacturerName;
        this.supplierName = supplierName;
        this.quantity = quantity;
        this.price = price;
    }

    public Product() {
    }


    public String lineRepresentation(){
        //code
        return null;
    }
}
