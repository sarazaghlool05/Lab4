public class employeeUser {
        private String employeeId;
        private String Name;
        private String Email;
        private String Address;
        private String PhoneNumber;

    public employeeUser(String employeeId, String name, String email, String address, String phoneNumber) {
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