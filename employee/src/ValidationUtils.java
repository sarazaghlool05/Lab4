//written by bassant salah on 18/10 @15:51 (for validation)
public class ValidationUtils {
        public static boolean validateName(String name) {
            if (name == null || name.trim().isEmpty()) {
                return false;
            }
            if (!name.matches("[A-Za-z]+")) {
                return false;
            }
            return true;
        }

        public static boolean emailValidation(String email) {
            if (email == null || email.trim().isEmpty()) {
                return false;
            }
            return email.contains("@") && email.contains(".");
        }

        public static boolean validatePhoneNumber(String phoneNumber) {
            if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
                return false;
            }
            if (!phoneNumber.matches("\\d+")) {
                return false;
            }
            return true;
        }
    }

