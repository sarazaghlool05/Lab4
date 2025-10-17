import javax.swing.*;
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
                int choice = sc.nextInt();
                switch(choice){
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
                        admin.getListOfEmployees();
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
            
        }
        else{
            System.out.println("unfound user");
            System.exit(0);
        }
    }
}
