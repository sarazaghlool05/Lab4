import javax.swing.*;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Testing {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Are you an Admin or an Employee? ");
        String user = sc.nextLine();
        if(user.equalsIgnoreCase("Admin")){
            AdminRole admin = new AdminRole();
            //print out a menu for the admin role user
            while(true){
                System.out.println("\n1.Add Employee\n2.Get List Of Employees\n3.Remove Employee\n4.Log Out");
                System.out.print("Enter your choice: ");
                String choiceAdmin = sc.nextLine();
                System.out.print("\n");
                switch(choiceAdmin){
                    case "1":
                        System.out.print("Enter employee id: ");
                        String employeeID = sc.nextLine();
                        System.out.print("Enter employee name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter employee email: ");
                        String email = sc.nextLine();
                        System.out.print("Enter employee address: ");
                        String address = sc.nextLine();
                        System.out.print("Enter employee phone number: ");
                        String phoneNumber = sc.nextLine();
                        admin.addEmployee(employeeID, name, email, address, phoneNumber);
                        break;
                    case "2":
                        EmployeeUser[] listOfEmployees = admin.getListOfEmployees();
                        for (int i = 0; i < listOfEmployees.length; i++) {
                            System.out.println("Data of employee "+(i+1)+":");
                            System.out.println(listOfEmployees[i].toString());
                        }
                        break;
                    case "3":
                        System.out.print("Enter the id of the employee that you want to remove: ");
                        String id = sc.nextLine();
                        admin.removeEmployee(id);
                        break;
                    case "4":
                        admin.logout();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Error choice not found!");
                        break;
                }
            }
        }
        else if(user.equalsIgnoreCase("Employee")){
            EmployeeRole employee = new EmployeeRole();
            //print out a menu for the employee role user
            while(true){
                System.out.println("\n1.Add Product\n2.Get List Of Products\n3.Get List Of Purchasing Operations\n4.Purchase Product\n5.Return Product\n6.Apply Payment\n7.Log Out");
                System.out.print("Enter your choice: ");
                String choiceEmployee = sc.nextLine();
                System.out.print("\n");
                switch(choiceEmployee){
                    case "1":
                        System.out.print("Enter product id: ");
                        String productID = sc.nextLine();
                        System.out.print("Enter Product name: ");
                        String productName = sc.nextLine();
                        System.out.print("Enter manufacturer name: ");
                        String manufacturerName = sc.nextLine();
                        System.out.print("Enter supplier name: ");
                        String supplierName = sc.nextLine();
                        System.out.print("Enter product quantity: ");
                        int quantity = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter product price: ");
                        int price = sc.nextInt();
                        sc.nextLine();
                        employee.addProduct(productID, productName, manufacturerName, supplierName, quantity, price);
                        break;
                    case "2":
                        Product[] listOfProducts = employee.getListOfProducts();
                        for (int i = 0; i < listOfProducts.length; i++) {
                            System.out.println("Data of product "+(i+1)+":");
                            System.out.println(listOfProducts[i].toString());
                        }

                        break;
                    case "3":
                        CustomerProduct[] listOfPurchasingOperations = employee.getListOfPurchasingOperations();
                        for (int i = 0; i < listOfPurchasingOperations.length; i++) {
                            System.out.println("Data of Purchasing Operations "+(i+1)+":");
                            System.out.println(listOfPurchasingOperations[i].toString());
                        }
                        break;
                    case "4":
                        System.out.print("Enter customerSSN: ");
                        String customerSSN = sc.nextLine();
                        System.out.print("Enter productID: ");
                        String purchasingProductID = sc.nextLine();
                        System.out.print("Enter purchaseDate: ");
                        String date = sc.nextLine();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        LocalDate localDate = LocalDate.parse(date, formatter);
                        boolean purcahsing = employee.purchaseProduct(customerSSN, purchasingProductID, localDate);
                        System.out.println("Purchasing: " + purcahsing);
                        break;
                    case "5":
                        System.out.print("Enter customerSSN: ");
                        String returnProductCustomerSSN = sc.nextLine();
                        System.out.print("Enter productID: ");
                        String returnProductPurchasingProductID = sc.nextLine();
                        System.out.print("Enter purchase date: ");
                        String purchasingDate = sc.nextLine();
                        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        LocalDate localPurchasingDate = LocalDate.parse(purchasingDate, formatter2);
                        System.out.print("Enter return date: ");
                        String returningDate = sc.nextLine();
                        LocalDate localReturningDate = LocalDate.parse(returningDate, formatter2);
                        double priceOfItem = employee.returnProduct(returnProductCustomerSSN, returnProductPurchasingProductID, localPurchasingDate, localReturningDate);
                        System.out.println("Price: " + priceOfItem);
                        break;
                    case "6":
                        System.out.print("Enter customerSSN: ");
                        String paymentCustomerSSN = sc.nextLine();
                        System.out.print("Enter purchase date: ");
                        String purchasingPaymentDate = sc.nextLine();
                        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        LocalDate localPurchasingPaymentDate = LocalDate.parse(purchasingPaymentDate, formatter3);
                        boolean payment = employee.applyPayment(paymentCustomerSSN, localPurchasingPaymentDate);
                        System.out.println("Payment: " + payment);
                        break;
                    case "7":
                        employee.logout();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Error choice not found!");
                        break;
                }
            }
        }
        else{
            System.out.println("Unfound user");
            System.exit(0);
        }
    }
}
