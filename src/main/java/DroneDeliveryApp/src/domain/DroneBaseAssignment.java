package DroneDeliveryApp.src.domain;

import DroneDeliveryApp.src.domain.Drone;
import DroneDeliveryApp.src.domain.DroneBase;

public class DroneBaseAssignment {
    private final Drone drone;
    private final DroneBase droneBase;

    public DroneBaseAssignment(Drone drone, DroneBase droneBase) {
        this.drone = drone;
        this.droneBase = droneBase;
    }

    // Getters
    public Drone getDrone() { return drone; }
    public DroneBase getDroneBase() { return droneBase; }
}
