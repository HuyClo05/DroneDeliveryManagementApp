package DroneDeliveryApp.src.domain;

import java.time.LocalDateTime;
import DroneDeliveryApp.src.model.enums.DeliveryStatus;
import DroneDeliveryApp.src.model.enums.IdType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Delivery {
    @EmbeddedId
    private IdentificationNumber deliveryId;
    private ShippingPackage packages;
    private Drone drones;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private DeliveryStatus status;

    public Delivery(){}

    /**
     * Constructs a new Delivery with the specified shipping package.
     * Initializes the delivery ID, sets the current time as the start time,
     * and sets the delivery status to DeliveryStatus.STARTED.
     *
     * @param packages The shipping package to be delivered.
     * @throws IllegalArgumentException If the provided package is null.
     */
    public Delivery(ShippingPackage packages) throws IllegalArgumentException {
        this.deliveryId = new IdentificationNumber(IdType.DELIVERY_HISTORY);
        this.packages = packages;
        this.drones = null;
        this.startTime = LocalDateTime.now();
        this.endTime = null;
        this.status = DeliveryStatus.STARTED;
    }

    // Getters
    public IdentificationNumber getDeliveryId() { return this.deliveryId; }
    public ShippingPackage getPackages() { return this.packages; }
    public Drone getDrones() { return this.drones; }
    public LocalDateTime getStartTime() { return this.startTime; }
    public LocalDateTime getEndTime() { return this.endTime; }
    public DeliveryStatus getStatus() { return this.status; }

    // Setters
    public void setPackages(ShippingPackage packages) { this.packages = packages; }
    public void setDrones(Drone drones) { this.drones = drones; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    public void setStatus(DeliveryStatus status) { this.status = status; }

    // Public Methods
    /**
     * (For Simulation and Unit Test only.) Constructs a new Delivery with the specified parameters.
     * Initializes the delivery ID and sets the provided values for package, drone, start time, and end time.
     * The delivery status is set to {@code DeliveryStatus.STARTED}.
     *
     * @param packages  The shipping package to be delivered.
     * @param startTime The start time of the delivery.
     * @param endTime   The end time of the delivery.
     * @throws IllegalArgumentException If the provided package is null.
     */
    public Delivery(ShippingPackage packages,
                    LocalDateTime startTime,
                    LocalDateTime endTime) {

        this.deliveryId = new IdentificationNumber(IdType.DELIVERY_HISTORY);
        this.packages = packages;
        this.drones = null;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = DeliveryStatus.STARTED;
    }
}
