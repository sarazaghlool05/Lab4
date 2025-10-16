import java.time.LocalDate;
public class Testing {
        public static void main(String[] args) {
            EmployeeRole emp = new EmployeeRole();

            String customerSSN = "7845345678";
            LocalDate purchaseDate = LocalDate.of(2025, 10, 1);

            boolean result = emp.applyPayment(customerSSN, purchaseDate);

            if (result) {
                System.out.println("✅ Payment applied successfully!");
            } else {
                System.out.println("❌ Payment not applied (record not found or already paid).");
            }
        }


}
