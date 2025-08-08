package com.huyvo.dronedeliverymanagementapp.classes.domains;

import com.huyvo.dronedeliverymanagementapp.classes.domains.user.Customer;
import com.huyvo.dronedeliverymanagementapp.classes.enums.IdType;
import jakarta.persistence.*;

@Entity
@Table(name = "shipping_package")
public class ShippingPackage {
    @EmbeddedId
    private IdentificationNumber packageId;

    private double weight;
    private String description;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Customer sender;


    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    private Customer recipient;


    @ManyToOne
    @JoinColumn(name = "pickup_location_id", nullable = false)
    private Location pickupLocation;

    @ManyToOne
    @JoinColumn(name = "delivery_location_id", nullable = false)
    private Location deliveryLocation;

    public ShippingPackage(){}

    public ShippingPackage(double weight,
                           String description,
                           Customer sender,
                           Customer recipient) {

        this.packageId = new IdentificationNumber(IdType.PACKAGE);
        this.weight = weight;
        this.description = description;
        this.sender = sender;
        this.recipient = recipient;
        this.pickupLocation = sender.getAddress();
        this.deliveryLocation = recipient.getAddress();
    }

    // Getters
    public String getPackage_id(){ return packageId.getId(); }
    public double getWeight(){ return weight; }
    public String getDescription(){ return description; }
    public Customer getSender(){ return sender; }
    public Customer getRecipient(){ return recipient; }
    public Location getPickupLocation(){ return pickupLocation; }
    public Location getDeliveryLocation(){ return deliveryLocation; }

    // Setters
    public void setSender(Customer sender){ this.sender = sender; }
    public void setRecipient(Customer recipient){ this.recipient = recipient; }
}
