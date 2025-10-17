import javax.swing.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Testing {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Are you an Admin or an Employee? ");
        String user = sc.nextLine();
        if(user.equals("Admin")){
            AdminRole admin = new AdminRole();
            //print out a menu for the admin role user
            while(true){
                System.out.println("1.Add Employee\n2.Get List Of Employees\n3.Remove Employee\n4.Log Out");
                System.out.print("enter your choice: ");
                int choiceAdmin = sc.nextInt();
                switch(choiceAdmin){
                    case 1:
                        System.out.print("enter employee id: ");
                        String employeeID = sc.nextLine();
                        System.out.print("enter employee name: ");
                        String name = sc.nextLine();
                        System.out.print("enter employee email: ");
                        String email = sc.nextLine();
                        System.out.print("enter employee address: ");
                        String address = sc.nextLine();
                        System.out.print("enter employee phone number: ");
                        String phoneNumber = sc.nextLine();
                        admin.addEmployee(employeeID, name, email, address, phoneNumber);
                        break;
                    case 2:
                        EmployeeUser[] listOfEmployees = admin.getListOfEmployees();
                        break;
                    case 3:
                        System.out.print("enter the id of the employee that you want to remove: ");
                        String id = sc.nextLine();
                        admin.removeEmployee(id);
                        break;
                    case 4:
                        admin.logout();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("error choice not found!");
                        break;
                }
            }
        }
        else if(user.equals("Employee")){
            EmployeeRole employee = new EmployeeRole();
            //print out a menu for the employee role user
            while(true){
                System.out.println("1.Add Product\n2.Get List Of Products\n3.Get List Of Purchasing Operations\n4.Purchase Product\n5.Return Product\n6.Apply Payment\n7.Log Out");
                System.out.print("Enter your choice: ");
                int choiceEmployee = sc.nextInt();
                switch(choiceEmployee){
                    case 1:
                        System.out.print("enter product id: ");
                        String productID = sc.nextLine();
                        System.out.print("enter Product name: ");
                        String productName = sc.nextLine();
                        System.out.print("enter manufacturer name: ");
                        String manufacturerName = sc.nextLine();
                        System.out.print("enter supplier name: ");
                        String supplierName = sc.nextLine();
                        System.out.print("enter product quantity: ");
                        int quantity = sc.nextInt();
                        System.out.print("enter product price: ");
                        int price = sc.nextInt();
                        employee.addProduct(productID, productName, manufacturerName, supplierName, quantity, price);
                        break;
                    case 2:
                        Product[] listOfProducts = employee.getListOfProducts();
                        break;
                    case 3:
                        CustomerProduct[] listOfPurchasingOperations = employee.getListOfPurchasingOperations();
                        break;
                    case 4:
                        System.out.print("enter customerSSN: ");
                        String customerSSN = sc.nextLine();
                        System.out.print("enter productID: ");
                        String purchasingProductID = sc.nextLine();
                        System.out.print("enter purchaseDate: ");
                        String date = sc.nextLine();
                        System.out.print("enter supplier name: ");
                        String supplierName = sc.nextLine();
                }
            }
        }
        else{
            System.out.println("unfound user");
            System.exit(0);
        }
    }
}
