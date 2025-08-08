package com.huyvo.dronedeliverymanagementapp.classes.domains.user;

import com.huyvo.dronedeliverymanagementapp.classes.domains.Location;
import jakarta.persistence.*;

@Entity
public class Customer extends User {
    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Location address;

    public Customer(){}

    public Customer(String name,
                    String email,
                    String phone,
                    String password,
                    Location address) {

        super(name, email, phone, password);

        this.address = address;
    }

    // Getters
    public Location getAddress() { return this.address; }
}