import java.util.Scanner;

public class Test {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        /*
        System.out.print("enter the product ID:");
        String productID = sc.nextLine();
        System.out.print("enter the product name:");
        String productName = sc.nextLine();
        System.out.print("enter the product manufacturer name:");
        String manufacturerName = sc.nextLine();
        System.out.print("enter the product supplier name:");
        String supplierName = sc.nextLine();
        System.out.print("enter the product quantity:");
        int quantity = sc.nextInt();
        System.out.print("enter the product price:");
        float price = sc.nextFloat();


        e.addProduct(productID, productName, manufacturerName, supplierName, quantity, price);
        */

        EmployeeRole e = new EmployeeRole();

        Product[] p = e.getListOfProducts();
        for(int i = 0; i < p.length; i++){
            System.out.println(p[i].toString());
        }
    }
}
