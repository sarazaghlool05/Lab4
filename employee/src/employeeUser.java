//written by Sara Mohamed on wednesday 10/15/2025 @3pm
public class EmployeeUser{
        private String employeeId;
        private String Name;
        private String Email;
        private String Address;
        private String PhoneNumber;

    public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber) {
        this.employeeId = employeeId;
        Name = name;
        Email = email;
        Address = address;
        PhoneNumber = phoneNumber;
    }
    public String lineRepresentation(){
        return employeeId+","+Name+","+Email+","+Address+","+PhoneNumber;
    }
    public String getSearchKey(){
        return employeeId;
    }
}