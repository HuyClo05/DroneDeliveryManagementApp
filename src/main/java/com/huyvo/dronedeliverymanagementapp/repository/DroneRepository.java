package com.huyvo.dronedeliverymanagementapp.repository;

import com.huyvo.dronedeliverymanagementapp.classes.domains.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String>{
}
