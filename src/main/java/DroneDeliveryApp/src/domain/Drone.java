package DroneDeliveryApp.src.domain;

import DroneDeliveryApp.src.domain.user.Customer;
import DroneDeliveryApp.src.model.enums.DroneStatus;
import DroneDeliveryApp.src.model.enums.IdType;
import DroneDeliveryApp.src.validation.DroneValidator;
import DroneDeliveryApp.src.validation.ValidationException;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a drone with specific attributes and functionality.
 * This class provides methods to manage and retrieve the state of a drone,
 * including its name, id, availability, battery level, and load capacity.
 */
@Embeddable
public class Drone {
    private final static DroneValidator droneValidator = new DroneValidator();
    private final static Location base = new Location("base1",
                                                    "HannaSt",
                                                    "Hannover",
                                                    "NRW",
                                                    "22459",
                                                    "Germany",
                                                    52.3738f,
                                                    9.7312f);
    @Id
    private IdentificationNumber droneId;
    private float maxPayload;
    private boolean isEmpty;
    private Location currentLocation;
    private int batteryLevel;
    private float mileage;
    private DroneStatus status;
    private ShippingPackage assignedPackage;

    public Drone(){}

    public Drone(float maxPayload,
                 int batteryLevel,
                 float mileage) throws ValidationException {

        this.droneId = new IdentificationNumber(IdType.DRONE);
        this.maxPayload = maxPayload;
        this.isEmpty = true;
        this.currentLocation = new Location("base1",
                                            "HannaSt",
                                            "Hannover",
                                            "NRW",
                                            "22459",
                                            "Germany",
                                            52.3738f,
                                            9.7312f);
        this.batteryLevel = batteryLevel;
        this.mileage = mileage;
        this.status = DroneStatus.IDLE;
        this.assignedPackage = null;
        droneValidator.validate(this);
    }


    // Getters
    public Location getBase() { return base; }
    public String getDroneId() { return this.droneId.getId(); }
    public float getMaxPayload() { return this.maxPayload; }
    public boolean isEmpty() { return this.isEmpty; }
    public Location getCurrentLocation() { return this.currentLocation; }
    public int getBatteryLevel() { return this.batteryLevel; }
    public float getMileage() { return this.mileage; }
    public DroneStatus getStatus() { return this.status; }
    public ShippingPackage getAssignedPackage() { return assignedPackage; }


    // Setters
    public void setEmpty(boolean isEmpty) throws ValidationException {
        this.isEmpty = isEmpty;
        droneValidator.validate(this);
    }

    public void setCurrentLocation(float latitude, float longitude) throws ValidationException {
        this.currentLocation.setLatitude(latitude);
        this.currentLocation.setLongitude(longitude);
        droneValidator.validate(this);
    }

    public void setBatteryLevel(int batteryLevel) throws ValidationException {
        this.batteryLevel = batteryLevel;
        droneValidator.validate(this);
    }

    public void setMileage(float mileage) throws ValidationException {
        this.mileage = mileage;
        droneValidator.validate(this);
    }

    public void setStatus(DroneStatus status) throws ValidationException {
        this.status = status;
        droneValidator.validate(this);
    }

    public void setAssignedPackage(ShippingPackage assignedPackage) throws ValidationException {
        this.assignedPackage = assignedPackage;
        droneValidator.validate(this);
    }


    // Action operations
    public void chargeBattery() {
        String DRONE_ID = this.getDroneId();

        droneValidator.validateDroneAtLocation(this, base, "charge");
        this.status = DroneStatus.CHARGING;
        System.out.printf("Drone %s is charging\n", DRONE_ID);
    }

    public void maintenance() {
        String DRONE_ID = this.getDroneId();

        droneValidator.validateDroneAtLocation(this, base, "perform maintenance");
        this.status = DroneStatus.MAINTENANCE;
        System.out.printf("Drone %s is under maintenance\n", DRONE_ID);
    }

    public void loadPackage(@NotNull ShippingPackage apackage) {
        String DRONE_ID = this.getDroneId();
        String PACKAGE_ID = apackage.getPackage_id();

        try {
            droneValidator.validateDroneAtLocation(this, base, "load a package");
            droneValidator.validateDroneStatus(this, DroneStatus.IDLE, "load a package");
            droneValidator.validateLoadedPackage(this, apackage);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.printf("Error loading package: %s", e.getMessage());
            throw e;
        }
        this.isEmpty = false;
        System.out.printf("Drone %s loaded package %s\n", DRONE_ID, PACKAGE_ID);
    }

    public void unloadPackage() {
        String DRONE_ID = this.getDroneId();
        String CUSTOMER_PACKAGE_ID = this.assignedPackage.getPackage_id();
        Location customerDestination = this.assignedPackage.getRecipient().getAddress();
        ShippingPackage customerPackage = this.assignedPackage;

        try {
            droneValidator.validateDroneAtLocation(this, customerDestination, "unload a package");
            droneValidator.validateDroneStatus(this, DroneStatus.IN_TRANSIT, "unload a package");
            droneValidator.validateUnloadedPackage(this, customerPackage);
        } catch (IllegalStateException e) {
            System.err.printf("Error unloading package: %s", e.getMessage());
            throw e;
        }
        this.isEmpty = true;
        System.out.printf("\nDrone %s unloaded package %s\n", DRONE_ID, CUSTOMER_PACKAGE_ID);
    }

    public void goTo(@NotNull Location destination) {
        String DRONE_ID = this.getDroneId();
        String DESTINATION_ADDRESS = destination.getLocationId();

        this.currentLocation.setLatitude(destination.getLatitude());
        this.currentLocation.setLongitude(destination.getLongitude());
        System.out.printf("Drone %s is going to %s", DRONE_ID, DESTINATION_ADDRESS);
    }


    // domain.Delivery operations
    public void performDelivery(@NotNull Customer assignedCustomer) {
        String DRONE_ID = this.getDroneId();
        String PACKAGE_ID = this.assignedPackage.getPackage_id();
        String CUSTOMER_ID = this.assignedPackage.getRecipient().getUserId();

        System.out.printf("\nDrone %s started deliver package %s to customer %s\n", DRONE_ID, PACKAGE_ID, CUSTOMER_ID);
        droneValidator.validateDroneAtLocation(this, base, "start delivering");
        droneValidator.validateDroneStatus(this, DroneStatus.IDLE, "start delivering");

        loadPackage(this.assignedPackage);
        this.status = DroneStatus.IN_TRANSIT;
        goTo(assignedCustomer.getAddress());
        unloadPackage();
        goTo(base);
        this.assignedPackage = null;

        droneValidator.validateDroneAtLocation(this, base, "finish delivering");
        droneValidator.validateDroneStatus(this, DroneStatus.IN_TRANSIT, "finish delivering");
        this.status = DroneStatus.IDLE;
        System.out.printf("\nDrone %s finished deliver package %s to customer %s\n", DRONE_ID, PACKAGE_ID, CUSTOMER_ID);
    }
}
