import java.util.ArrayList;

//written by Sara Mohamed on Thursday 10/16/2025 @1:30pm
public class AdminRole {
    private EmployeeUserDatabase database;

    public AdminRole(){
        database = new EmployeeUserDatabase("Employees.txt");
        database.readFromFile(); //loads employees from file
    }

    public void addEmployee(String employeeId, String name, String email,
    String address, String phoneNumber){
        EmployeeUser employee = new EmployeeUser(employeeId,name,email,address,phoneNumber);
        database.insertRecord(employee);

        database.saveToFile();
        System.out.println("Employee saved to file successfully");
    }

    public EmployeeUser[] getListOfEmployees(){
        ArrayList<EmployeeUser> records = database.returnAllRecords();
        EmployeeUser[] recordsArray = new EmployeeUser[records.size()];

        for (int i = 0; i < records.size(); i++){
            recordsArray[i] = records.get(i);
        }

        return recordsArray;
    }

    public void removeEmployee(String key){
        database.deleteRecord(key);

        database.saveToFile();
        System.out.println("Employee removed from file successfully");
    }

    public void logout(){
        database.saveToFile();
        System.out.println("All data saved to file successfully");
    }

}
