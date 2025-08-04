package DroneDeliveryApp.src.domain.user;

import DroneDeliveryApp.src.domain.IdentificationNumber;
import DroneDeliveryApp.src.model.enums.IdType;
import DroneDeliveryApp.src.model.enums.UserStatus;

public abstract class User {
    private final IdentificationNumber id;
    private String name;
    private String email;
    private String phone;
    private final String password;
    private UserStatus userStatus;

    public User(String name,
                String email,
                String phone,
                String password) {

        validateName(name);
        validateEmail(email);
        validatePhone(phone);
        validatePassword(password);

        this.id = new IdentificationNumber(IdType.CUSTOMER);  // Use the provided ID instead of creating a new one
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
    
    // Getters
    public String getUserId() { return id.getId(); }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    // Setters with validation
    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public void setEmail(String email) {
        validateEmail(email);
        this.email = email;
    }

    public void setPhone(String phone) {
        validatePhone(phone);
        this.phone = phone;
    }

    // Validation methods
    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (name.length() < 3 || name.length() > 20) {
            throw new IllegalArgumentException("Name must be between 3 and 20 characters");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    private void validatePhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone cannot be null or empty");
        }
        // Add phone format validation if needed
        String phoneRegex = "^\\+?[1-9]\\d{1,14}$";  // Basic international phone format
        if (!phone.matches(phoneRegex)) {
            throw new IllegalArgumentException("Invalid phone format");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
        // Add more password requirements if needed
    }

}