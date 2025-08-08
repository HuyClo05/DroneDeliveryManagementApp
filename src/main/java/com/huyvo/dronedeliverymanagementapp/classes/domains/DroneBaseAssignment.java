package com.huyvo.dronedeliverymanagementapp.classes.domains;

import com.huyvo.dronedeliverymanagementapp.classes.enums.IdType;
import jakarta.persistence.*;

@Entity
@Table(name = "drone_base_assignment")
public class DroneBaseAssignment {
    @EmbeddedId
    private IdentificationNumber droneBaseAssignmentId;

    @OneToOne
    @JoinColumn(name = "drone_id")
    private Drone drone;

    @OneToOne
    @JoinColumn(name = "drone_base_id")
    private DroneBase droneBase;

    public DroneBaseAssignment(){}

    public DroneBaseAssignment(Drone drone, DroneBase droneBase) {
        this.droneBaseAssignmentId = new IdentificationNumber(IdType.DRONE_BASE_ASSIGNMENT);
        this.drone = drone;
        this.droneBase = droneBase;
    }

    // Getters
    public Drone getDrone() { return drone; }
    public DroneBase getDroneBase() { return droneBase; }
}
