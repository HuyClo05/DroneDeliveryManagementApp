package com.huyvo.dronedeliverymanagementapp.repository;

import com.huyvo.dronedeliverymanagementapp.classes.domains.DroneBaseAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneBaseAssignmentRepository extends JpaRepository<DroneBaseAssignment, String>{
}
