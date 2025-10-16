import java.time.LocalDate;

public class Testing {
    public static void main(String[] args) {
        EmployeeRole emp = new EmployeeRole();

        // Example data
        String customerSSN = "7845345678";
        String productID = "P2568";
        LocalDate purchaseDate = LocalDate.of(2022, 2, 12);
        LocalDate returnDate = LocalDate.of(2022, 2, 22);

        double result = emp.returnProduct(customerSSN, productID, purchaseDate, returnDate);

        if (result == -1) {
            System.out.println("❌ Return failed (conditions not met or not found)");
        } else {
            System.out.println("✅ Return successful! Refunded amount = " + result);
        }
    }
}
