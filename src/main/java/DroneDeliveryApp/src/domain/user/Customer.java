package DroneDeliveryApp.src.domain.user;

import DroneDeliveryApp.src.domain.Location;
import DroneDeliveryApp.src.domain.ShippingPackage;
import jakarta.persistence.*;

@Embeddable
public class Customer extends User {
    @Embedded
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