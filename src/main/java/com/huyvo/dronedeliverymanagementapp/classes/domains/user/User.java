package com.huyvo.dronedeliverymanagementapp.classes.domains.user;

import com.huyvo.dronedeliverymanagementapp.classes.domains.IdentificationNumber;
import com.huyvo.dronedeliverymanagementapp.classes.enums.IdType;
import com.huyvo.dronedeliverymanagementapp.classes.enums.UserStatus;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class User {
    @EmbeddedId
    private IdentificationNumber id;
    private String name;
    private String email;
    private String phone;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    protected User(){}

    protected User(String name,
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

    // Setters
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getPassword(Customer user) { return password; }
}