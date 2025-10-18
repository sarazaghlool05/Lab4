import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

//written by Sara Mohamed on Thursday 10/16/2025 @11am
public class EmployeeUserDatabase extends Database<EmployeeUser>{

    public EmployeeUserDatabase(String filename){
        super(filename); //creates an empty list ready to store employee objects
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

        return new EmployeeUser(employeeId, name, email, address, phoneNumber, false);
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
    public String lineRepresentation(EmployeeUser p){

        return p.lineRepresentation();
    }
}
