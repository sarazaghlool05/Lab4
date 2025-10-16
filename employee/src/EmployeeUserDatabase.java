import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
        records.clear(); //if I call the function more than once I start with an empty list

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
        if(parts.length != 5){
            return null;
        }
        String employeeId = parts[0];
        String name = parts[1];
        String email = parts[2];
        String address = parts[3];
        String phoneNumber = parts[4];

        EmployeeUser employee = new EmployeeUser(employeeId, name, email, address, phoneNumber);
        return employee;
    }

    public ArrayList<EmployeeUser> returnAllRecords(){
        return records;
    } //is a getter for records

    public boolean contains(String key ){
        for (int i = 0; i < records.size(); i++){
            EmployeeUser employee = records.get(i);
            if(employee.getSearchKey().equals(key)){
                return true;
            }
        }
        return false;
    }

    public EmployeeUser getRecord(String key){
        for (int i = 0; i < records.size(); i++){
            EmployeeUser employee = records.get(i);
            if(employee.getSearchKey().equals(key)){
                return employee;
            }
        }
        return null;
    }

    public void insertRecord(EmployeeUser record){
        if(contains(record.getSearchKey())){
            System.out.println("Employee with this id already exists");
        }
        else{
            records.add(record);
            System.out.println("Employee added successfully");
        }
    }

    public void deleteRecord(String key){
        for (int i = 0; i < records.size(); i++) {
            EmployeeUser employee = records.get(i);
            if (employee.getSearchKey().equals(key)) {
                records.remove(i);
                System.out.println("Employee deleted");
                return; //doesn't keep searching after deletion
            }
        }
        System.out.println("Employee with this id doesn't exists");
    }

    public void saveToFile(){
        try (FileWriter writer = new FileWriter(filename, false)){ //automatically closes writer
            for (int i = 0; i < records.size(); i++) {                    //false: overwrite file
                EmployeeUser employee = records.get(i);
                writer.write(employee.lineRepresentation());
                writer.write("\n");
            }
            System.out.println("File saved successfully");
        } catch (IOException e) {
            System.out.println("Error while saving file");
        }
    }
}
