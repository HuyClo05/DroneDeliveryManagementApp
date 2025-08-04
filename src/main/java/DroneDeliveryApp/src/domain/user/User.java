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
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}