public class EmployeeUser implements Record {
    private String employeeId;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber) {
        this(employeeId, name, email, address, phoneNumber, true);
    }

    public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber, boolean validate) {
        this.employeeId = employeeId;

        if (validate) {
            // collect all errors first
            StringBuilder errors = new StringBuilder();

            if (!ValidationUtils.validateName(name))
                errors.append("Name must contain only letters and not be empty.\n");
            else
                this.name = name;

            if (!ValidationUtils.emailValidation(email))
                errors.append("Invalid email format.\n");
            else
                this.email = email;

            if (address == null || address.trim().isEmpty())
                errors.append("Address cannot be empty.\n");
            else
                this.address = address;

            if (!ValidationUtils.validatePhoneNumber(phoneNumber))
                errors.append("Phone number must contain only digits and not be empty.\n");
            else
                this.phoneNumber = phoneNumber;

            if (errors.length() > 0) {
                System.out.println("\u001B[31m Errors found:\u001B[0m\n" + errors);
                throw new IllegalArgumentException("Invalid employee details!");
            }

            } else {
                this.name = name;
                this.email = email;
                this.address = address;
                this.phoneNumber = phoneNumber;
            }
        }
        public String getEmployeeId () {
            return employeeId;
        }
        public String getName () {
            return name;
        }
        public String getEmail () {
            return email;
        }
        public String getAddress () {
            return address;
        }
        public String getPhoneNumber () {
            return phoneNumber;
        }

        @Override
        public String lineRepresentation () {
            return employeeId + "," + name + "," + email + "," + address + "," + phoneNumber;
        }

        @Override
        public String getSearchKey () {
            return employeeId;
        }
        @Override
        public String toString () {
            return "ID: " + employeeId + ", Name: " + name +
                    ", Email: " + email + ", Address: " + address +
                    ", Phone: " + phoneNumber;
        }
    }

