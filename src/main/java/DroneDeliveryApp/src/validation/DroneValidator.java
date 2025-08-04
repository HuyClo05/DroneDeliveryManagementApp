package DroneDeliveryApp.src.validation;

import domain.Drone;
import domain.Location;
import domain.ShippingPackage;
import model.enums.DroneStatus;
import org.jetbrains.annotations.NotNull;

public class DroneValidator implements Validator<Drone> {

    @Override
    public void validate(Drone drone) throws ValidationException {
        if (drone == null) {
            throw new ValidationException("Drone cannot be null.");
        }
        if (drone.getDroneId() == null) {
            throw new ValidationException("Drone ID must not be null.");
        }

        validatePayloadAndCustomer(drone);
        validateLocation(drone);
        validateBatteryAndMileage(drone);
        validateStatus(drone);
    }

    private void validatePayloadAndCustomer(@NotNull Drone drone) throws ValidationException {
        // Max payload validation
        if (drone.getMaxPayload() < 0) {
            throw new ValidationException("Max payload must be non-negative.");
        }
        if (!drone.isEmpty() && drone.getAssignedCustomer() == null) {
            throw new ValidationException("Drone with payload must have an assigned customer.");
        }
    }

    private void validateLocation(@NotNull Drone drone) throws ValidationException {
        // currentLocation validation
        if (drone.getCurrentLocation() == null || drone.getCurrentLocation().getCoordinates() == null) {
            throw new ValidationException("Drone location or coordinates must not be null.");
        }
    }

    private void validateBatteryAndMileage(@NotNull Drone drone) throws ValidationException {
        // Battery level and mileage validation
        if (drone.getBatteryLevel() < 0 || drone.getBatteryLevel() > 100) {
            throw new ValidationException("Battery level must be between 0 and 100.");
        }
        if (drone.getMileage() < 0) {
            throw new ValidationException("Mileage must be non-negative.");
        }
    }

    private void validateStatus(@NotNull Drone drone) throws ValidationException {
        // Status validation
        if (drone.getStatus() == null) {
            throw new ValidationException("Drone status must not be null.");
        }

        // assignedCustomer and status validation
        if (drone.getStatus() == DroneStatus.MAINTENANCE && drone.getAssignedCustomer() != null) {
            throw new ValidationException("Drone in maintenance mode should not have an assigned customer.");
        }
    }

    public void validateDroneAtLocation(Drone drone, Location location, String action) throws IllegalStateException {
        if (drone == null || location == null) {
            throw new IllegalArgumentException("Drone or location cannot be null for Location validation.");
        }
        if (drone.getCurrentLocation() == null || drone.getCurrentLocation().getCoordinates() == null) {
            throw new IllegalArgumentException("Drone current location cannot be null for Location validation.");
        }
        if (!location.equals(drone.getCurrentLocation())) {
            throw new IllegalStateException("Drone must be at " + location.getLocationId() + " to " + action + ".");
        }
    }

    public void validateDroneStatus(Drone drone, DroneStatus requiredStatus, String action) throws IllegalStateException {
        if (drone == null || requiredStatus == null) {
            throw new IllegalArgumentException("Drone and required status cannot be null for status validation.");
        }
        if (drone.getStatus() != requiredStatus) {
            throw new IllegalStateException("Drone must be " + requiredStatus + " to " + action + ".");
        }
    }

    public void validateLoadedPackage(Drone drone, ShippingPackage packageToLoad) throws IllegalStateException {
        if (packageToLoad.getWeight() > drone.getMaxPayload()) {
            throw new IllegalArgumentException("Package weight exceeds max payload");
        }
        if (!drone.isEmpty()) {
            throw new IllegalStateException("Drone must be empty to load a package");
        }
    }

    public void validateUnloadedPackage(Drone drone, ShippingPackage packageToUnload) throws IllegalStateException {
        if (drone.isEmpty()) {
            throw new IllegalStateException("Drone is empty");
        }
    }
}
