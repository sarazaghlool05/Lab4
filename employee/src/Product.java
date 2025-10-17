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

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setPrice(float price) {
        this.price = price;
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

    @Override
    public String toString() {
        return "ID: " + productID + ", Product name: " + productName +
                ", Manufacturer name: " + manufacturerName + ", Supplier name: " + supplierName;
    }

}
