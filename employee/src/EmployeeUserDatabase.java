import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//written by Sara Mohamed on Thursday 10/16/2025 @11am
public class EmployeeUserDatabase {
    private ArrayList<EmployeeUser> records;
    private String filename;

    public EmployeeUserDatabase(String filename){
        this.filename = filename;
        this.records = new ArrayList<>(); //creates an empty list ready to store employee objects
    }

    public void readFromFile(){
        records.clear(); //if i call the function more than once i start with an empty list

        try{
            File file = new File(filename);
            Scanner scan = new Scanner(file);

            while(scan.hasNextLine()){
                String line = scan.nextLine();
                EmployeeUser employee = createRecordFrom(line);
                records.add(employee);
            }
            scan.close(); //closes file
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    public EmployeeUser createRecordFrom(String line){
        String[] parts = line.split(",");
        String employeeId = parts[0];
        String name = parts[1];
        String email = parts[2];
        String address = parts[3];
        String phoneNumber = parts[4];

        EmployeeUser employee = new EmployeeUser(employeeId,name,email,address,phoneNumber);
        return employee;
    }
}
