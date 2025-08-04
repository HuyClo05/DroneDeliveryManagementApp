package DroneDeliveryApp.src.domain;

import java.time.LocalDateTime;
import DroneDeliveryApp.src.domain.IdentificationNumber;
import DroneDeliveryApp.src.domain.ShippingPackage;
import DroneDeliveryApp.src.domain.Drone;
import DroneDeliveryApp.src.model.enums.DeliveryStatus;
import DroneDeliveryApp.src.model.enums.IdType;

public class Delivery {
    private final IdentificationNumber deliveryId;
    private final ShippingPackage packages;
    private final Drone drones;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final DeliveryStatus status;

    public Delivery(ShippingPackage packages,
                           Drone drones,
                           LocalDateTime startTime,
                           LocalDateTime endTime) {

        this.deliveryId = new IdentificationNumber(IdType.DELIVERY_HISTORY);
        this.packages = packages;
        this.drones = drones;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = DeliveryStatus.STARTED;
    }

    public IdentificationNumber getDeliveryId() { return this.deliveryId; }
    public ShippingPackage getPackages() { return this.packages; }
    public Drone getDrones() { return this.drones; }
    public LocalDateTime getStartTime() { return this.startTime; }
    public LocalDateTime getEndTime() { return this.endTime; }
    public DeliveryStatus getStatus() { return this.status; }
}
