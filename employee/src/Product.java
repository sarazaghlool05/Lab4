//written by Bassant on wednesday 10/15/2025 @11:35
public class Product {
    private String  productID;
    private String productName;
    private String manufacturerName;
    private String supplierName;
    private int  quantity;
    private float  price;
    public Product(String productID, String productName, String manufacturerName, String
            supplierName, int quantity, float price){
        this.productID=productID;
        this.productName=productName;
        this.manufacturerName=manufacturerName;
        this.supplierName=supplierName;
        this.quantity=quantity;
        this.price=price;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String lineRepresentation(){
        return productID+","+productName+","+manufacturerName+","+supplierName+","+quantity+","+price;
    }
    public String getSearchKey(){
        return productID;
    }
}
