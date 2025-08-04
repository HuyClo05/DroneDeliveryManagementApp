package DroneDeliveryApp.src.domain.user;

import DroneDeliveryApp.src.domain.Location;
import DroneDeliveryApp.src.domain.ShippingPackage;

public class Customer extends User {
    private final Location address;
    private ShippingPackage userPackage;

    public Customer(String name,
                    String email,
                    String phone,
                    String password,
                    Location address,
                    ShippingPackage userPackage) {

        super(name, email, phone, password);

        this.address = address;
        this.userPackage = userPackage;
    }

    // Getters
    public Location getAddress() { return this.address; }
    public ShippingPackage getUserPackage() { return this.userPackage; }

    // Setters
    public void setUserPackage(ShippingPackage userPackage){
        this.userPackage = userPackage;
    }
}