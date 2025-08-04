package DroneDeliveryApp.src.domain;

import DroneDeliveryApp.src.domain.user.Customer;
import DroneDeliveryApp.src.model.enums.IdType;
import DroneDeliveryApp.src.model.enums.PackageStatus;

public class ShippingPackage {
    private final IdentificationNumber packageId;
    private final double weight;
    private final String description;
    private Customer sender;
    private Customer recipient;
    private final Location pickupLocation;
    private final Location deliveryLocation;
    private PackageStatus status;


    public ShippingPackage(double weight,
                           String description,
                           Customer sender,
                           Customer recipient,
                           Location pickupLocation,
                           Location deliveryLocation) {

        this.packageId = new IdentificationNumber(IdType.PACKAGE);
        this.weight = weight;
        this.description = description;
        this.sender = sender;
        this.recipient = recipient;
        this.pickupLocation = pickupLocation;
        this.deliveryLocation = deliveryLocation;
        this.status = PackageStatus.PENDING;
    }

    // Getters
    public String getPackage_id(){ return packageId.getId(); }
    public double getWeight(){ return weight; }
    public String getDescription(){ return description; }
    public Customer getSender(){ return sender; }
    public Customer getRecipient(){ return recipient; }
    public Location getPickupLocation(){ return pickupLocation; }
    public Location getDeliveryLocation(){ return deliveryLocation; }
    public PackageStatus getStatus(){ return status; }

    // Setters
    public void setStatus(PackageStatus status){ this.status = status; }
    public void setSender(Customer sender){ this.sender = sender; }
    public void setRecipient(Customer recipient){ this.recipient = recipient; }
}
