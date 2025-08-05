package DroneDeliveryApp.src.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class DroneBaseAssignment {
    @EmbeddedId
    private Drone drone;
    private DroneBase droneBase;

    public DroneBaseAssignment(){}

    public DroneBaseAssignment(Drone drone, DroneBase droneBase) {
        this.drone = drone;
        this.droneBase = droneBase;
    }

    // Getters
    public Drone getDrone() { return drone; }
    public DroneBase getDroneBase() { return droneBase; }
}
